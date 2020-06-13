package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="team_detail")
public class TeamDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    public TeamDetail(Team team) {
        this.team = team;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "homeTeamDetail", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Game gameAsHome;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "awayTeamDetail", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Game gameAsAway;

    @OneToMany(mappedBy = "teamDetail", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlayerInGame> playerInGameList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private TeamStatisticLine teamStatisticLine;
}
