package pl.dalk.statapp.dao.simple_entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameSimple {
    private long home_team_id;
    private long away_team_id;
    private long league_id;
    private long referees_cast_id;
    private long season_id;
    private Date date;

    public GameSimple(int home_team_id, int away_team_id, int league_id, int referees_cast_id, int season_id, Date date) {
        this.home_team_id = home_team_id;
        this.away_team_id = away_team_id;
        this.league_id = league_id;
        this.referees_cast_id = referees_cast_id;
        this.season_id = season_id;
        this.date = date;
    }
}
