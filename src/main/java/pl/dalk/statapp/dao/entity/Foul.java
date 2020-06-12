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
@Table(name = "foul")
public class Foul implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="fouled_player_info")
    private PlayerInfo fouledPlayerInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incident_time_id", referencedColumnName = "id")
    private IncidentTime incidentTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="fouling_player_info", referencedColumnName = "id")
    private PlayerInfo foulingPlayerInfo;


    public Foul(PlayerInfo fouledPlayerInfo, PlayerInfo foulingPlayerInfo, IncidentTime incidentTime, Game game) {
        this.fouledPlayerInfo = fouledPlayerInfo;
        this.incidentTime = incidentTime;
        this.foulingPlayerInfo = foulingPlayerInfo;
        this.game = game;
    }
}
