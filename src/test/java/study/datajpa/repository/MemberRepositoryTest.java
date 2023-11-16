package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember() throws Exception {

        System.out.println("memberRepository = " + memberRepository.getClass());
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        //있을수도 있고 없을수도 있으므로 Optional로 제공이됨
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() throws Exception {

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get(); //xvbxvbc
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public void findByUsernameAndAgeGreaterThen() throws Exception {

        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findTop3HelloBy() throws Exception {
        List<Member> top3HelloBy = memberRepository.findTop3HelloBy();
    }

    @Test
    public void testQuery() throws Exception {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        assertThat(result.get(0)).isEqualTo(m1);  //result.get(0)의 뜻은 첫번째 데이터를 가져온다는 뜻이다.
    }

    @Test
    public void findUsernameList() throws Exception {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }
    }

    /*@Test
    public void findMemberDto() throws Exception {
        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);

        Team team = new Team("teamA");
        m1.setTeam(team);
        teamRepository.save(team);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }*/

    @Test
    public void findByNames() throws Exception {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void returnType() throws Exception {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);


        //List는 null이없다. Empty Collection 으로 반환을 해준다.
        //그래서 result != null 이런거 안좋은 코드다. 내 얘기를 하는듯.ㅠ
        //list는 그냥 받으면 된다.
        List<Member> result = memberRepository.findListByUsername("AasdfAA");
        System.out.println("result.size() = " + result.size());

        //단건인 경우는 null이 나온다.
        Member member = memberRepository.findMemberByUsername("AAA");

        //내가 DB에서 조회했는데 데이터가 있을수도있고 없을수도 있다 = Optional 을 쓰자.
        //근데 결과가 2개이상이면 예외가 터진다.
        Optional<Member> aaa = memberRepository.findOptionalByUsername("AAA");
    }

    @Test
    public void paging() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));//spring data jpa는 페이지를 0부터 시작 ,
                                                                                                                        // 사용자이름으로 desc, sorting조건이 복잡하면 jpql에 넣어주자.

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null)); //api로 날려주기위한 Entity -> DTO 변환

        //then
        List<Member> content = page.getContent(); //페이지에 있는 데이터를 꺼내려먼 getContent() 사용
        long totalElements = page.getTotalElements();

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0); //페이지 번호 갖고올 수 있음
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue(); // 첫 페이지인지
        assertThat(page.hasNext()).isTrue(); //다음 페이지 있는지
    }

    @Test
    public void bulkUpdate() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 11));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 30));

        //when
        int resultCount = memberRepository.bulkAgePlus(20);

        //then
        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    public void findMemberLazy() throws Exception {
        // given
        //member1 -> teamA
        //member2 -> teamB
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //영속성 컨텍스트 다 날려버리기
        em.flush();
        em.clear();

        // when
        //쿼리 한번 날렸는데 그 결과가 2개나옴 (N+1문제)
//        List<Member> members = memberRepository.findAll();

        /**
         * 페치조인 : 한번에 다 갖고옴 (proxy말고 진짜로)
         * 조인을 하고 추가로 select절에 그 데이터를 다 넣어줌
         * member.team 이런걸 객체 그래프라고 그러는데 이런걸 조인해서 한번에 다 가져옴
         */
//        List<Member> members = memberRepository.findMemberFetchJoin();

        List<Member> members = memberRepository.findAll();


        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.teamClass = " + member.getTeam().getClass());
            System.out.println("member.team = " + member.getTeam().getName()); //getName()실행될때 지연로딩 발생
        }

        // then
    }

    @Test
    public void queryHint() throws Exception {

        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush(); // JPA안에 영속성 컨텍스트 내부에는 1차캐시가 존재하는데 여기 있는 결과를 DB에 동기화 함.
        em.clear(); // clear 를 해줘야 영속성 컨텍스트가 날라감. 그래서 그다음부터 1차캐시가 없기 때문에 DB에서 가져옴

        //when



    }











}