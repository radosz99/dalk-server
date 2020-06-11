package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.dalk.statapp.dao.enumerates.TurnoverEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="turnover")
public class Turnover implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incident_time_id", referencedColumnName = "id")
    private IncidentTime incidentTime;

    private TurnoverEnum type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="player", referencedColumnName = "id")
    private Player player;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "turnover", cascade = CascadeType.ALL)
    private Steal steal;
    
    public Turnover(IncidentTime incidentTime, TurnoverEnum type, Game game, Player player) {
        this.incidentTime = incidentTime;
        this.type = type;
        this.game = game;
        this.player = player;
    }
}
