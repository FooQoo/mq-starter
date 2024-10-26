package com.dxjunkyard.reststarter.presentation.consumer;

import com.dxjunkyard.reststarter.application.service.MemberService;
import com.dxjunkyard.reststarter.presentation.dto.payload.MemberPayload;
import io.micrometer.observation.ObservationRegistry;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.observability.micrometer.Micrometer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("memberConsumer")
@RequiredArgsConstructor
@Slf4j
public class MemberConsumer implements Function<Flux<MemberPayload>, Mono<Void>> {

    private final MemberService memberService;

    private final ObservationRegistry registry;

    @Override
    public Mono<Void> apply(Flux<MemberPayload> memberPayloadFlux) {
        return memberPayloadFlux
                .delayUntil(this::process)
                .then();
    }

    private Mono<Void> process(MemberPayload memberPayload) {
        return memberService.getMemberName(memberPayload.id())
                .doOnNext(name -> log.info("name: {}", name))
                .tap(Micrometer.observation(registry))
                .then();
    }
}
