package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.service.TestService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/api/v1")
    public ResponseEntity testExample() {
        List<Member> result = testService.testExample();
        return ResponseEntity.ok(result);
    }

    //???
    @GetMapping("/api/page")
    public ResponseEntity getMainPage(MemberDto memberDto, Optional<Integer> page) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MemberDto> mainPage = testService.getMainPage(memberDto, pageable);
        return ResponseEntity.ok(mainPage);
    }

    @GetMapping("/api/member")
    public ResponseEntity getMember(MemberDto memberDto) {
        List<MemberDto> member = testService.getMember(memberDto);
        return ResponseEntity.ok(member);
    }


}
