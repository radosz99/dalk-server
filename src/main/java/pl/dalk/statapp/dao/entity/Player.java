package pl.dalk.statapp.dao.entity;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="player")
public class Player implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @NotNull
    private String dalkId;

    private int jerseyNo;

    @OneToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person person;

//    @OneToMany(mappedBy = "player")
//    private List<Assist> assistList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "player" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Shot> shotList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "fouledPlayer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Foul> foulOnList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "foulingPlayer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Foul> foulsList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "player" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Turnover> turnoverList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "player" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Rebound> reboundList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "player" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Block> blockList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "playerIn" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Substitution> substitutionInList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "playerOut" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Substitution> substitutionOutList = new ArrayList<>();
//
    @OneToMany(mappedBy = "player" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlayerInfo> playerInfoList = new ArrayList<>();

    public Player(@NotNull String dalkId, int jerseyNo, Person person) {
        this.dalkId = dalkId;
        this.jerseyNo = jerseyNo;
        this.person = person;
    }

}
