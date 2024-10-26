package com.dxjunkyard.reststarter.presentation.consumer;

import com.dxjunkyard.reststarter.application.service.MemberService;
import com.dxjunkyard.reststarter.presentation.dto.payload.MemberPayload;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("memberConsumer")
@RequiredArgsConstructor
@Slf4j
public class MemberConsumer implements Consumer<MemberPayload> {

    private final MemberService memberService;

    @Override
    public void accept(MemberPayload memberPayloadFlux) {
        memberService.getMemberName(memberPayloadFlux.id())
                .doOnNext(name -> log.info("name: {}", name))
                .subscribe();
    }
}
