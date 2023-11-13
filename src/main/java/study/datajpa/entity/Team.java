package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") //FK없는쪽에 mappedBy하는거 권장 (즉 FK있는게 연관관계 주인)
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
