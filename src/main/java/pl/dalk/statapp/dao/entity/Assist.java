package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="assist")
public class Assist implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="shot_id", referencedColumnName = "id")
    private Shot shot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;

    public Assist(Shot shot, Player player) {
        this.shot = shot;
        this.player = player;
    }
}
