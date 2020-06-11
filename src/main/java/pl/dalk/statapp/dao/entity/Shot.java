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
@Table(name = "shot")
public class Shot implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private short type; // value 1,2,3

    private boolean hit;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shot", orphanRemoval = true, cascade = CascadeType.ALL)
    private Assist assist;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shot", orphanRemoval = true, cascade = CascadeType.ALL)
    private Block block;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incident_time_id", referencedColumnName = "id")
    private IncidentTime incidentTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;

    public Shot(short type, boolean hit, IncidentTime incidentTime, Game game, Player player) {
        this.type = type;
        this.hit = hit;
        this.incidentTime = incidentTime;
        this.game = game;
        this.player = player;
    }
}
