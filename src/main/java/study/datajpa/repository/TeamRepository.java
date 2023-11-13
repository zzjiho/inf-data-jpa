package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

/**
 * 개발자가 구현체를 만들지 않아도 spring이 알아서 해줌
 * proxy만들어서 주입해준것
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

}
