package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    //지연로딩 : Member조회할때 Team은 가짜 객체로 조회를 해놓고 실제 Team의 어떤 데이터를 사용하는 시점에 Team을 꺼내기 위한 쿼리가 별도로 날라감
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    /**
     * 연관관계 세팅 메소드
     */
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);

    }





}
