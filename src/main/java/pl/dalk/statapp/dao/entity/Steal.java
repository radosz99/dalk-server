package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter @ToString
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="steal")
public class Steal implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="turnover_id", referencedColumnName = "id")
    private Turnover turnover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_info_id", referencedColumnName = "id")
    private PlayerInfo playerInfo;

    public Steal(Turnover turnover, PlayerInfo playerInfo) {
        this.turnover = turnover;
        this.playerInfo = playerInfo;
    }
}
