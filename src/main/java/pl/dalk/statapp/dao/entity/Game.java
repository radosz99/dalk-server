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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;


    private Date date;

    public Game(TeamDetail homeTeamDetail, TeamDetail awayTeamDetail, RefereesCast refereesCast, Season season, League league, Date date) {
        this.homeTeamDetail = homeTeamDetail;
        this.awayTeamDetail = awayTeamDetail;
        this.refereesCast = refereesCast;
        this.season = season;
        this.league = league;
        this.date = date;
    }
}
