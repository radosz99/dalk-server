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
@Table(name="block")
public class Block implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shot_id", referencedColumnName = "id")
    private Shot shot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="player_info_id", referencedColumnName = "id")
    private PlayerInfo playerInfo;

    public Block(Shot shot, PlayerInfo playerInfo) {
        this.shot = shot;
        this.playerInfo = playerInfo;
    }
}
