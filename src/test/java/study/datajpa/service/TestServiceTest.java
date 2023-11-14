package study.datajpa.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.repository.TestRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class TestServiceTest {

    @Autowired
    TestRepository testRepository;

    @Test
    void testExample() throws Exception {

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        testRepository.save(member1);
        testRepository.save(member2);

        List<Member> result = testRepository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}