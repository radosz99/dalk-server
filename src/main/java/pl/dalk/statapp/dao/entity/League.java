package pl.dalk.statapp.dao.entity;


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
@Table(name="league")
public class League implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String name;

    @OneToMany(mappedBy = "league" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> gameList = new ArrayList<>();

    @OneToMany(mappedBy = "league" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeamInfo> teamInfoList = new ArrayList<>();



    public League(String name) {
        this.name = name;
    }
}
