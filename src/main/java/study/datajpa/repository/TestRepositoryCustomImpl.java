package study.datajpa.repository;


import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import study.datajpa.dto.MemberDto;
import study.datajpa.dto.QMemberDto;
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
    public Page<MemberDto> getMainPage(@ModelAttribute MemberDto memberDto, Pageable pageable) {

        QMember member = QMember.member;

        QueryResults<MemberDto> result = queryFactory
                .select(
                        new QMemberDto(
                                member.id,
                                member.username,
                                member.age)
                )
                .from(member)
                .where(member.age.eq(10))
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MemberDto> content = result.getResults();
        long totalCnt = result.getTotal();

        return new PageImpl<>(content, pageable, totalCnt);
    }

    @Override
    public List<MemberDto> getMember(MemberDto memberDto) {

        QMember member = QMember.member;

        QueryResults<MemberDto> result = queryFactory
                .select(
                        new QMemberDto(
                                member.id,
                                member.username,
                                member.age)
                )
                .from(member)
                .where(member.age.eq(10))
                .fetchResults();

        return result.getResults();
    }
}










