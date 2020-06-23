package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="mvp")
public class MVP {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_season_info_id", referencedColumnName = "id")
    private PlayerSeasonInfo playerSeasonInfo;

    private String weekInfo;

    private String playerLine;

    private boolean active;

    public MVP(PlayerSeasonInfo playerSeasonInfo, String weekInfo, String playerLine, boolean active) {
        this.playerSeasonInfo = playerSeasonInfo;
        this.weekInfo = weekInfo;
        this.playerLine = playerLine;
        this.active = active;
    }
}
