package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="team")
public class Team implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @NotNull
    private String name;

//    @OneToMany(mappedBy = "homeTeam" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Game> gameAsHomeList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "awayTeam" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Game> gameAsAwayList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "team" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<TeamRebound> teamReboundList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "team" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Timeout> timeoutList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "team" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<PlayerInfo> playerInfoList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "team" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<TeamInfo> teamInfoList = new ArrayList<>();


    public Team(@NotNull String name) {
        this.name = name;
    }
}
