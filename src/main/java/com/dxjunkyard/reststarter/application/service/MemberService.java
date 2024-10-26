package com.dxjunkyard.reststarter.application.service;

import com.dxjunkyard.reststarter.domain.model.Count;
import com.dxjunkyard.reststarter.domain.model.Member;
import com.dxjunkyard.reststarter.domain.model.search.MemberSearchCondition;
import com.dxjunkyard.reststarter.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Flux<Member> search(final MemberSearchCondition condition) {
        return memberRepository.search(condition);
    }

    public Mono<Count> count() {
        return memberRepository.count();
    }

    public Mono<String> getMemberName(final Integer id) {
        return memberRepository.getMemberName(id);
    }
}
