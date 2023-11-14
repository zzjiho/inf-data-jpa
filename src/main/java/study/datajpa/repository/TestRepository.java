package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

/**
 * Custom이 붙은 인터페이스를 상속한 Impl 클래스의 코드는 Custom 인터페이스를 상속한 JpaRepository에서도 사용할 수 있다.
 * reference: https://batory.tistory.com/496
 */
public interface TestRepository extends JpaRepository<Member, Long>, TestRepositoryCustom {

}
