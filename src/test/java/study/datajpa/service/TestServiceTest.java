/*
package study.datajpa.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.QMember;
import study.datajpa.entity.Team;
import study.datajpa.repository.TestRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static study.datajpa.entity.QMember.member;

@SpringBootTest
@Transactional
@Rollback(false)
class TestServiceTest {

    @Autowired
    TestRepository testRepository;

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

//    QMember qMember = new QMember("m"); //별칭 직접 지정
    QMember qmember = member; //기본 인스턴스 사용

    @BeforeEach
    public void before() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }


    @Test
    void testExample() throws Exception {

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        testRepository.save(member1);
        testRepository.save(member2);

        List<Member> result = testRepository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    void startQuerydsl() throws Exception {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMember m = new QMember("m");

        Member result = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"),
                        m.age.eq(10))
                .fetchOne();

        assertThat(result.getUsername().equals("member1"));
    }

    @Test
    void sort() throws Exception {

        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);
        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

    @Test
    void paging1() throws Exception {

        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) //0부터 시작
                .limit(2) //최대2건조회
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }



}*/
