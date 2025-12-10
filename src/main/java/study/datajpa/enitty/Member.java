package study.datajpa.enitty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"io", "usename", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id", nullable = false, updatable = false)
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username){
        this.username = username;
    }

    public Member(String member1, int i, Team team) {
        this.username = member1;
        this.age = i;
        if(team != null){
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
