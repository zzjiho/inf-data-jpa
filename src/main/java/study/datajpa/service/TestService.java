package study.datajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.TestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {

    private final TestRepository testRepository;

    public List<Member> testExample() {
        List<Member> result = testRepository.findAll();
        return result;
    }

    @Transactional(readOnly = true)
    public Page<MemberDto> getMainPage(MemberDto memberDto, Pageable pageable) {
        return testRepository.getMainPage(memberDto, pageable);
    }

    @Transactional(readOnly = true)
    public List<MemberDto> getMember(MemberDto memberDto) {
        return testRepository.getMember(memberDto);
    }


}
