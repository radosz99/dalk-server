package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_detail")
    private TeamDetail homeTeamDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_detail")
    private TeamDetail awayTeamDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referees_cast_id")
    private RefereesCast refereesCast;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "league_group_id")
    private LeagueGroup leagueGroup;


    private Date date;

    public Game(TeamDetail homeTeamDetail, TeamDetail awayTeamDetail, RefereesCast refereesCast, LeagueGroup leagueGroup, Date date) {
        this.homeTeamDetail = homeTeamDetail;
        this.awayTeamDetail = awayTeamDetail;
        this.refereesCast = refereesCast;
        this.leagueGroup = leagueGroup;
        this.date = date;
    }
}
