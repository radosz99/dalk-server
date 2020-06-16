package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import pl.dalk.statapp.statistic.Calculator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="team_statistic_line")
public class TeamStatisticLine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private int points;
    private int twoPointShotsMade;
    private int twoPointShots;
    private int threePointShotsMade;
    private int threePointShots;
    private int freeThrowsMade;
    private int freeThrows;
    private int offensiveRebound;
    private int defensiveRebound;
    private int assists;
    private int steals;
    private int turnovers;
    private int blocks;
    private int fouls;
    private int foulsOn;

    public JSONObject getJSON(){
        return new JSONObject()
                .put("points", points)
                .put("twoPoints", Calculator.getShotInfo(twoPointShotsMade, twoPointShots,1))
                .put("threePoints", Calculator.getShotInfo(threePointShotsMade, threePointShots,1))
                .put("freeThrows", Calculator.getShotInfo(freeThrowsMade, freeThrows,1))
                .put("rebounds", Calculator.getReboundInfo(defensiveRebound, offensiveRebound,1))
                .put("assists", assists)
                .put("steals", steals)
                .put("turnovers", turnovers)
                .put("foulsOn", foulsOn)
                .put("fouls", fouls)
                .put("blocks", blocks);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "teamStatisticLine", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private TeamDetail teamDetail;

    public TeamStatisticLine(int twoPointShotsMade, int twoPointShots,
                               int threePointShotsMade, int threePointShots, int freeThrowsMade, int freeThrows,
                               int offensiveRebound, int defensiveRebound, int assists, int steals, int turnovers,
                               int blocks, int fouls, int foulsOn, int points) {
        this.twoPointShotsMade = twoPointShotsMade;
        this.twoPointShots = twoPointShots;
        this.threePointShotsMade = threePointShotsMade;
        this.threePointShots = threePointShots;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrows = freeThrows;
        this.offensiveRebound = offensiveRebound;
        this.defensiveRebound = defensiveRebound;
        this.assists = assists;
        this.steals = steals;
        this.turnovers = turnovers;
        this.blocks = blocks;
        this.fouls = fouls;
        this.foulsOn = foulsOn;
        this.points = points;
    }
}
