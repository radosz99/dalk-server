package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.dalk.statapp.dao.enumerates.ReboundEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="team_rebound")
public class TeamRebound implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private ReboundEnum type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incident_time_id", referencedColumnName = "id")
    private IncidentTime incidentTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    public TeamRebound(ReboundEnum type, IncidentTime incidentTime, Game game, Team team) {
        this.type = type;
        this.incidentTime = incidentTime;
        this.game = game;
        this.team = team;
    }
}
