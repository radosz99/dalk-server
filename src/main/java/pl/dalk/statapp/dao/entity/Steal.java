package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="steal")
public class Steal implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="turnover_id", referencedColumnName = "id")
    private Turnover turnover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;

    public Steal(Turnover turnover, Player player) {
        this.turnover = turnover;
        this.player = player;
    }
}
