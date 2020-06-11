package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "game")
public class Game implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    private Team awayTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referees_cast_id", referencedColumnName = "id")
    private RefereesCast refereesCast;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    private League league;

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shot> shotList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Foul> foulList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Turnover> turnoverList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rebound> reboundList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeamRebound> teamReboundList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Substitution> substitutionList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Timeout> timeoutList = new ArrayList<>();

    private Date date;

    public Game(Team homeTeam, Team awayTeam, RefereesCast refereesCast, Season season, League league, Date date) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.refereesCast = refereesCast;
        this.season = season;
        this.league = league;
        this.date = date;
    }
}
