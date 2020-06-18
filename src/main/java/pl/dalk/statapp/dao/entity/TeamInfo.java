package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="team_info")
public class TeamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int points;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int littlePointsScored;
    private int littlePointsLost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    @OneToMany(mappedBy = "teamInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlayerSeasonInfo> playerInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "teamInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TeamDetail> gamesList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="league_group_id", referencedColumnName = "id")
    @JsonBackReference
    private LeagueGroup leagueGroup;

    public TeamInfo(int points, int matchesPlayed, int matchesWon, int matchesLost, int littlePointsScored, int littlePointsLost, Team team, LeagueGroup leagueGroup) {
        this.points = points;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.littlePointsScored = littlePointsScored;
        this.littlePointsLost = littlePointsLost;
        this.team = team;
        this.leagueGroup = leagueGroup;
    }
}
