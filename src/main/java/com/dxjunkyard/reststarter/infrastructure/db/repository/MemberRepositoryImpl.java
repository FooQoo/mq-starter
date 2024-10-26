package com.dxjunkyard.reststarter.infrastructure.db.repository;

import com.dxjunkyard.reststarter.domain.model.Count;
import com.dxjunkyard.reststarter.domain.model.Member;
import com.dxjunkyard.reststarter.domain.model.search.MemberSearchCondition;
import com.dxjunkyard.reststarter.domain.repository.member.MemberRepository;
import com.dxjunkyard.reststarter.infrastructure.db.entity.MemberEntity;
import com.dxjunkyard.reststarter.infrastructure.db.repository.crud.MemberCrudRepository;
import com.dxjunkyard.reststarter.infrastructure.db.repository.sql.MemberSearchSqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberCrudRepository memberCrudRepository;

    private final DatabaseClient databaseClient;

    @Override
    public Flux<Member> search(final MemberSearchCondition condition) {

        final var helper = new MemberSearchSqlHelper(condition);

        return databaseClient.sql(helper.generateSelectQuery())
                .bindValues(helper.generateParamMap())
                .map((row, rowMetadata) -> helper.mapRow(row))
                .all();
    }

    @Override
    public Mono<Count> count() {
        return memberCrudRepository.count()
                .map(Count::new);
    }

    @Override
    public Mono<String> getMemberName(Integer id) {
        return memberCrudRepository.findById(Long.valueOf(id))
                .map(MemberEntity::name);
    }
}
