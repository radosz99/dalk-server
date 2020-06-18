package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="league_group")
public class LeagueGroup {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_info_id")
    private LeagueInfo leagueInfo;

    @OneToMany(mappedBy = "leagueGroup", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Game> gameList = new ArrayList<>();

    @OneToMany(mappedBy = "leagueGroup",orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TeamInfo> teamInfoList = new ArrayList<>();

    public LeagueGroup(Season season, LeagueInfo leagueInfo, String name) {
        this.season = season;
        this.leagueInfo = leagueInfo;
        this.name = name;
    }
}
