package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="player_in_game")
public class PlayerInGame {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private int jerseyNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_season_info_id")
    private PlayerSeasonInfo playerSeasonInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_detail_id")
    private TeamDetail teamDetail;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "playerInGame", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private PlayerStatisticLine playerStatisticLine;

    public PlayerInGame(int jerseyNo, PlayerSeasonInfo playerSeasonInfo, TeamDetail teamDetail) {
        this.playerSeasonInfo = playerSeasonInfo;
        this.teamDetail = teamDetail;
        this.jerseyNo = jerseyNo;
    }
}
