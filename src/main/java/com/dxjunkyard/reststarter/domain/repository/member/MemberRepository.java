package com.dxjunkyard.reststarter.domain.repository.member;

import com.dxjunkyard.reststarter.domain.model.Count;
import com.dxjunkyard.reststarter.domain.model.Member;
import com.dxjunkyard.reststarter.domain.model.search.MemberSearchCondition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemberRepository {

    Flux<Member> search(MemberSearchCondition condition);

    Mono<Count> count();

    Mono<String> getMemberName(Integer id);
}
