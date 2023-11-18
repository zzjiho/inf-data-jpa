package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    /**
     * 도메인 클래스 컨버터
     */
    @GetMapping("/members2/{id}")
       public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
       }

    /**
     * 웹 계층에서도 페이징 할 수 있음
     */
    @GetMapping("/members")
    public Page<MemberDto> list(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        //반환 타입 member에서  memberDto로 변환
        Page<MemberDto> map = page.map(MemberDto::new);
        return map;

//        return memberRepository.findAll(pageable) 줄인코드
//                        .map(MemberDto::new);
    }

//    @PostConstruct //스프링 실행시 한번 실행됨
    public void init() {

        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }

    }






}
