package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    /*@Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();*/

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

    /**
     * spring data jap의 페이징
     *
     * 실무에서 쿼리가 복잡해지면 카운트 쿼리도 그대로 복잡해지므로 성능이 안나온다.
     * 그래서 이렇게 따로 구해줘야 한다
     */
//    Page<Member> findByAge(int age, Pageable pageable); //반환타입이 Page이면 total count 쿼리까지 같이 날려준다.

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    /**
     * 벌크성 업데이트
     *
     * clearAutomatically = true 옵션을 줘서
     * 벌크 연산 이후에는 영속성 컨텍스트를 다 날려줘야한다. 왜냐하면 벌크 연산은 영속성 컨텍스트를 무시하고 바로 DB에 쿼리를 날리기 때문.
     */
    @Modifying(clearAutomatically = true) //뭐 변경할때 넣어줘야함
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();









}
