package study.datajpa.repository;

import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;

public interface TestRepositoryCustom {

    List<Member> getMemberInfo(MemberDto memberDto);

}
