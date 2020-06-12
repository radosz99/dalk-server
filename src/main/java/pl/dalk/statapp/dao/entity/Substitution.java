package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @ToString
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="substitution")
public class Substitution implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_info_in", referencedColumnName = "id")
    private PlayerInfo playerInfoIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_info_out", referencedColumnName = "id")
    private PlayerInfo playerInfoOut;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incident_time_id", referencedColumnName = "id")
    private IncidentTime incidentTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    public Substitution(PlayerInfo playerInfoIn, PlayerInfo playerInfoOut, IncidentTime incidentTime, Game game) {
        this.playerInfoIn = playerInfoIn;
        this.playerInfoOut = playerInfoOut;
        this.incidentTime = incidentTime;
        this.game = game;
    }
}
