package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="match_squad")
public class MatchSquad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public MatchSquad(Team team) {
        this.team = team;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "homeMatchSquad", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Game gameAsHome;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "awayMatchSquad", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Game gameAsAway;

    @OneToMany(mappedBy = "matchSquad", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlayerInGame> playerInGameList = new ArrayList<>();
}
