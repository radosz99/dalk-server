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
    @JoinColumn(name="player_info_id", referencedColumnName = "id")
    private PlayerInfo playerInfo;

    public Shot(short type, boolean hit, IncidentTime incidentTime, Game game, PlayerInfo playerInfo) {
        this.type = type;
        this.hit = hit;
        this.incidentTime = incidentTime;
        this.game = game;
        this.playerInfo = playerInfo;
    }
}
