package pl.dalk.statapp.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dalk.statapp.dao.entity.*;
import pl.dalk.statapp.dao.simple_entity.GameSimple;
import pl.dalk.statapp.manager.*;

import java.util.*;

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

    @GetMapping("/{gameId}/score")
    public Map<String, Integer> getScore(@PathVariable Long gameId){
        Optional<Game> game = gameManager.findById(gameId);
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

    @GetMapping(value = "/{gameId}/boxscore", produces = "application/json")
    public String getBoxScore(@PathVariable Long gameId){
        Optional<Game> game = gameManager.findById(gameId);
        JSONObject allStatistics = new JSONObject();
        if(game.isPresent()) {

            JSONObject homeTeamInfo = new JSONObject();
            TeamDetail teamDetail = game.get().getHomeTeamDetail();
            homeTeamInfo.put("name", teamDetail.getTeamInfo().getTeam().getName());
            homeTeamInfo.put("statistics", teamDetail.getTeamStatisticLine().getJSON());
            List<PlayerInGame> playerInGameList = teamDetail.getPlayerInGameList();
            List<JSONObject> playerStatisticLineList = new ArrayList<>();
            for(PlayerInGame playerInGame : playerInGameList){
                JSONObject player = new JSONObject();
                player.put("name", playerInGame.getPlayerSeasonInfo().getPlayer().getPerson().getName());
                player.put("surname", playerInGame.getPlayerSeasonInfo().getPlayer().getPerson().getSurname());
                player.put("jersey", playerInGame.getJerseyNo());
                player.put("statistics", playerInGame.getPlayerStatisticLine().getJSON());
                playerStatisticLineList.add(player);
            }
            homeTeamInfo.put("players", playerStatisticLineList);

            JSONObject awayTeamInfo = new JSONObject();
            awayTeamInfo.put("name", game.get().getAwayTeamDetail().getTeamInfo().getTeam().getName());
            awayTeamInfo.put("statistics", game.get().getAwayTeamDetail().getTeamStatisticLine().getJSON());
            playerInGameList = game.get().getAwayTeamDetail().getPlayerInGameList();
            playerStatisticLineList = new ArrayList<>();
            for(PlayerInGame playerInGame : playerInGameList){
                JSONObject player = new JSONObject();
                player.put("name", playerInGame.getPlayerSeasonInfo().getPlayer().getPerson().getName());
                player.put("surname", playerInGame.getPlayerSeasonInfo().getPlayer().getPerson().getSurname());
                player.put("jersey", playerInGame.getJerseyNo());
                player.put("statistics", playerInGame.getPlayerStatisticLine().getJSON());
                playerStatisticLineList.add(player);
            }
            awayTeamInfo.put("players", playerStatisticLineList);

            allStatistics.put("home", homeTeamInfo);
            allStatistics.put("away", awayTeamInfo);

        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That game doesn't exist!");
        }

        String response = new JSONObject()
                .put("status", "200")
                .put("result", allStatistics)
                .toString();


        return response;
    }

//    @PostMapping()
//    public Game addGame(@RequestBody GameSimple game){
//        Optional<Team> homeTeam = teamManager.findById(game.getHome_team_id());
//        Optional<Team> awayTeam = teamManager.findById(game.getAway_team_id());
//        Optional<RefereesCast> refereesCast = refereesCastManager.findById(game.getReferees_cast_id());
//        Optional<Season> season = seasonManager.findById(game.getSeason_id());
//        Optional<League> league = leagueManager.findById(game.getLeague_id());
//        if(!homeTeam.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "That home team doesn't exist!"
//            );
//        }
//        if(!awayTeam.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "That away team doesn't exist!"
//            );
//        }
//        if(!refereesCast.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "That referees cast doesn't exist!"
//            );
//        }
//        if(!season.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "That season doesn't exist!"
//            );
//        }
//        if(!league.isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "That league doesn't exist!"
//            );
//        }
//        TeamDetail homeSquad = new TeamDetail(homeTeam.get());
//        TeamDetail awaySquad = new TeamDetail(awayTeam.get());
//        Game newGame = new Game(homeSquad, awaySquad, refereesCast.get(), season.get(), league.get(), game.getDate());
//        Game addedGame = gameManager.save(newGame);
//        return addedGame;
//    }

    @GetMapping("/{gameId}")
    public Optional<Game> getById(@PathVariable Long gameId){
        Optional<Game> game = gameManager.findById(gameId);
        if(game.isPresent()) {
            return gameManager.findById(gameId);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Game not found"
            );
        }
    }


}
