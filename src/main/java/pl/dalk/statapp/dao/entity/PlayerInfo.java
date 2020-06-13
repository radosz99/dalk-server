package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "player_info")
public class PlayerInfo implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private int jerseyNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_info_id", referencedColumnName = "id")
    private TeamInfo teamInfo;

    @OneToMany(mappedBy = "playerInfo")
    @JsonIgnore
    private List<Assist> assistList = new ArrayList<>();

    @OneToMany(mappedBy = "playerInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Shot> shotList = new ArrayList<>();

    @OneToMany(mappedBy = "fouledPlayerInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Foul> foulOnList = new ArrayList<>();

    @OneToMany(mappedBy = "foulingPlayerInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Foul> foulsList = new ArrayList<>();

    @OneToMany(mappedBy = "playerInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Turnover> turnoverList = new ArrayList<>();

    @OneToMany(mappedBy = "playerInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rebound> reboundList = new ArrayList<>();

    @OneToMany(mappedBy = "playerInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Block> blockList = new ArrayList<>();

    @OneToMany(mappedBy = "playerInfoIn", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Substitution> substitutionInList = new ArrayList<>();

    @OneToMany(mappedBy = "playerInfoOut", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Substitution> substitutionOutList = new ArrayList<>();

    @OneToMany(mappedBy = "playerInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlayerInGame> playerInGameList = new ArrayList<>();

    public PlayerInfo(Player player, TeamInfo teamInfo, int jerseyNo) {
        this.player = player;
        this.jerseyNo = jerseyNo;
        this.teamInfo = teamInfo;
    }
}
