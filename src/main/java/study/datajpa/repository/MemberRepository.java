package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * <쿼리메소드>
     * 메소드 이름으로 쿼리를 생성해준다.
     * 근데 두개 이상 조건이 넘어가면 너무 길어지므로
     * 다른 방법을 쓰는걸 추천한다.!
     *
     * 실무에서 짤막짤막한 쿼리 칠때 많이 쓴다.
     */
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    /**
     * 실무에서 많이 씀
     *
     * 조금 복잡해지면 Query를 직접 정의해서 쓰고 메소드명을 심플하게 가져가자.
     * 동적 쿼리는 QueryDsl을 쓰자
     */
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    /**
     * Query를 통해서 값이나 DTO를 조회하는 방법
     * 위에랑 뭐가 다르지 ?
     */
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //DTO를 조회해서 쓰려면 이렇게 객체 생성해서 반환하는 것 같은 문법이다.
    //jpql이 제공하는 DTO로 반환하는 문법이다.
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    /**
     * in절로 여러개를 조회하고 싶을때 많이 쓰는 기능 (파라미터 바인딩)
     */
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    /**
     * 여러 반환 타입
     * 반환 타입을 유연하게 쓰는걸 spring data jpa가 지원을 해준다.
     */
    List<Member> findListByUsername(String username); //컬렉션
    Member findMemberByUsername(String username); //단건
    Optional<Member> findOptionalByUsername(String username); //단건 Optional





}
