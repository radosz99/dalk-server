package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "home_match_squad")
    private MatchSquad homeMatchSquad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_match_squad")
    private MatchSquad awayMatchSquad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referees_cast_id")
    private RefereesCast refereesCast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Shot> shotList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Foul> foulList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Turnover> turnoverList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rebound> reboundList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TeamRebound> teamReboundList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Substitution> substitutionList = new ArrayList<>();

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Timeout> timeoutList = new ArrayList<>();

    private Date date;

    public Game(MatchSquad homeMatchSquad, MatchSquad awayMatchSquad, RefereesCast refereesCast, Season season, League league, Date date) {
        this.homeMatchSquad = homeMatchSquad;
        this.awayMatchSquad = awayMatchSquad;
        this.refereesCast = refereesCast;
        this.season = season;
        this.league = league;
        this.date = date;
    }
}
