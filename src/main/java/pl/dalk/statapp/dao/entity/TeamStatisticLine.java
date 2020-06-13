package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
