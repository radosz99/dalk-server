package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="player_in_game")
public class PlayerInGame {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_info_id")
    private PlayerInfo playerInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_squad_id")
    private MatchSquad matchSquad;

    public PlayerInGame(PlayerInfo playerInfo, MatchSquad matchSquad) {
        this.playerInfo = playerInfo;
        this.matchSquad = matchSquad;
    }
}
