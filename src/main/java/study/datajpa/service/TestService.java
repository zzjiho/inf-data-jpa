package study.datajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.datajpa.entity.Member;
import study.datajpa.repository.TestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public List<Member> testExample() {
        List<Member> result = testRepository.findAll();
        return result;
    }


}
