package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.datajpa.dto.MemberDto;

import java.util.List;

public interface TestRepositoryCustom {

    Page<MemberDto> getMainPage(MemberDto memberDto, Pageable pageable);

    List<MemberDto> getMember(MemberDto memberDto);

}
