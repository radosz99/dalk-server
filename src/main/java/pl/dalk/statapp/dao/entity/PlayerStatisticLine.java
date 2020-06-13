package pl.dalk.statapp.dao.entity;

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
@Table(name="player_statistic_line")
public class PlayerStatisticLine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_in_game_id")
    private PlayerInGame playerInGame;

    private int points;
    private int minutesPlayed;
    private int secondsPlayed;
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
    private int plusMinus;


    public PlayerStatisticLine(int minutesPlayed, int secondsPlayed, int twoPointShotsMade, int twoPointShots,
                               int threePointShotsMade, int threePointShots, int freeThrowsMade, int freeThrows,
                               int offensiveRebound, int defensiveRebound, int assists, int steals, int turnovers,
                               int blocks, int fouls, int foulsOn, int plusMinus, int points) {
        this.minutesPlayed = minutesPlayed;
        this.secondsPlayed = secondsPlayed;
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
        this.plusMinus = plusMinus;
        this.points = points;
    }
}
