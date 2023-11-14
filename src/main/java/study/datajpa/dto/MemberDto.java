package study.datajpa.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import study.datajpa.entity.Team;

@Data
public class MemberDto {

    private Long id;
    private String username;
    private int age;
    private Team team;

    @QueryProjection
    public MemberDto(Long id, String username, int age, Team team) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.team = team;
    }
}
