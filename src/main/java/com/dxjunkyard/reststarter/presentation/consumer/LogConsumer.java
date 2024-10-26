package com.dxjunkyard.reststarter.presentation.consumer;

import io.micrometer.observation.ObservationRegistry;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.observability.micrometer.Micrometer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("logConsumer")
@RequiredArgsConstructor
@Slf4j
public class LogConsumer implements Function<Flux<String>, Mono<Void>> {

    private final ObservationRegistry registry;

    @Override
    public Mono<Void> apply(Flux<String> stringFlux) {
        return stringFlux
                .delayUntil(this::process)
                .then();
    }

    private Mono<Void> process(String string) {
        return Mono.just(string)
                .doOnNext(s -> log.info("string: {}", s))
                .tap(Micrometer.observation(registry))
                .then();
    }
}
