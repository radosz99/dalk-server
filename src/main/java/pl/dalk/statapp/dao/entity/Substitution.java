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
@Table(name="substitution")
public class Substitution implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_in_id", referencedColumnName = "id")
    private Player playerIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_out_id", referencedColumnName = "id")
    private Player playerOut;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incident_time_id", referencedColumnName = "id")
    private IncidentTime incidentTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    public Substitution(Player playerIn, Player playerOut, IncidentTime incidentTime, Game game) {
        this.playerIn = playerIn;
        this.playerOut = playerOut;
        this.incidentTime = incidentTime;
        this.game = game;
    }
}
