package com.dxjunkyard.reststarter.presentation.consumer;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("logConsumer")
@RequiredArgsConstructor
@Slf4j
public class LogConsumer implements Consumer<String> {

    @Override
    public void accept(String payload) {
        log.info("payload: {}", payload);
    }
}
