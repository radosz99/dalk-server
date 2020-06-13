package pl.dalk.statapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dalk.statapp.dao.entity.*;
import pl.dalk.statapp.dao.simple_entity.GameSimple;
import pl.dalk.statapp.manager.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/game")
public class GameApi {
    private GameManager gameManager;
    private RefereesCastManager refereesCastManager;
    private TeamManager teamManager;
    private SeasonManager seasonManager;
    private LeagueManager leagueManager;
    private MatchSquadManager matchSquadManager;

    @Autowired
    public GameApi(GameManager gameManager, RefereesCastManager refereesCastManager, TeamManager teamManager, SeasonManager seasonManager, LeagueManager leagueManager, MatchSquadManager matchSquadManager) {
        this.gameManager = gameManager;
        this.refereesCastManager = refereesCastManager;
        this.teamManager = teamManager;
        this.seasonManager = seasonManager;
        this.leagueManager = leagueManager;
        this.matchSquadManager = matchSquadManager;
    }

    @GetMapping()
    public List<Game> getAll(){
        return gameManager.findAll();
    }

    @GetMapping("/{index}/score")
    public Map<String, Integer> getScore(@PathVariable Long index){
        Optional<Game> game = gameManager.findById(index);
        Map<String, Integer> score = new HashMap<>();
        if(game.isPresent()) {
            score.put("home", game.get().getHomeTeamDetail().getTeamStatisticLine().getPoints());
            score.put("away", game.get().getAwayTeamDetail().getTeamStatisticLine().getPoints());
            return score;
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That game doesn't exist!");
        }

    }

    @PostMapping()
    public Game addGame(@RequestBody GameSimple game){
        Optional<Team> homeTeam = teamManager.findById(game.getHome_team_id());
        Optional<Team> awayTeam = teamManager.findById(game.getAway_team_id());
        Optional<RefereesCast> refereesCast = refereesCastManager.findById(game.getReferees_cast_id());
        Optional<Season> season = seasonManager.findById(game.getSeason_id());
        Optional<League> league = leagueManager.findById(game.getLeague_id());
        if(!homeTeam.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That home team doesn't exist!"
            );
        }
        if(!awayTeam.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That away team doesn't exist!"
            );
        }
        if(!refereesCast.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That referees cast doesn't exist!"
            );
        }
        if(!season.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That season doesn't exist!"
            );
        }
        if(!league.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That league doesn't exist!"
            );
        }
        TeamDetail homeSquad = new TeamDetail(homeTeam.get());
        TeamDetail awaySquad = new TeamDetail(awayTeam.get());
        Game newGame = new Game(homeSquad, awaySquad, refereesCast.get(), season.get(), league.get(), game.getDate());
        Game addedGame = gameManager.save(newGame);
        return addedGame;
    }

    @GetMapping("/{index}")
    public Optional<Game> getById(@PathVariable Long index){
        Optional<Game> game = gameManager.findById(index);
        if(game.isPresent()) {
            return gameManager.findById(index);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Game not found"
            );
        }
    }


}
