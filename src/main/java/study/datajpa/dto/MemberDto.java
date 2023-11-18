package study.datajpa.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import study.datajpa.entity.Member;

@Data //dto에는 @Data 쓰지말자
public class MemberDto {

    private Long id;
    private String username;
    private Integer age;
    private String teamName;

    /*@QueryProjection
    public MemberDto(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }*/

     public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public MemberDto(Member member) {
         this.id = member.getId();
         this.username = member.getUsername();
    }




}
