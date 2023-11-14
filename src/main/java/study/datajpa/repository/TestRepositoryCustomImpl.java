package study.datajpa.repository;


import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.QMember;

import javax.persistence.EntityManager;
import java.util.List;

public class TestRepositoryCustomImpl implements TestRepositoryCustom {

    private JPQLQueryFactory queryFactory;

    public TestRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> getMemberInfo(MemberDto memberDto) {

        return queryFactory
                .selectFrom(Member)
                .where(memberDto.getAge())
                .fetch();

    }
}










}
