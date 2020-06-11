package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="team_info")
public class TeamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="league_id", referencedColumnName = "id")
    private League league;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="season", referencedColumnName = "id")
    private Season season;

    public TeamInfo(Team team, League league, Season season) {
        this.team = team;
        this.league = league;
        this.season = season;
    }
}
