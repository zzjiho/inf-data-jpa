package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

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
     */






}
