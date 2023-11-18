package study.datajpa.repository;

import study.datajpa.entity.Member;

import java.util.List;

/**
 * Querydsl을 custom해서 많이 쓴다.
 */
public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
