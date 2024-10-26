package com.dxjunkyard.reststarter.presentation.consumer;

import com.dxjunkyard.reststarter.application.service.MemberService;
import com.dxjunkyard.reststarter.presentation.dto.payload.MemberPayload;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component("memberConsumer")
@RequiredArgsConstructor
@Slf4j
public class MemberConsumer implements Consumer<Flux<MemberPayload>> {

    private final MemberService memberService;

    @Override
    public void accept(Flux<MemberPayload> memberPayloadFlux) {
        memberPayloadFlux
                .map(MemberPayload::id)
                .flatMap(memberService::getMemberName)
                .log()
                .subscribe();
    }
}
