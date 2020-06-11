package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "foul")
public class Foul implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="fouled_player")
    private Player fouledPlayer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incident_time_id", referencedColumnName = "id")
    private IncidentTime incidentTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="fouling_player", referencedColumnName = "id")
    private Player foulingPlayer;


    public Foul(Player fouledPlayer, Player foulingPlayer, IncidentTime incidentTime, Game game) {
        this.fouledPlayer = fouledPlayer;
        this.incidentTime = incidentTime;
        this.foulingPlayer = foulingPlayer;
        this.game = game;
    }
}
