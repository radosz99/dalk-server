package pl.dalk.statapp.api;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dalk.statapp.dao.entity.*;
import pl.dalk.statapp.manager.MVPManager;
import pl.dalk.statapp.manager.PlayerSeasonInfoManager;
import pl.dalk.statapp.statistic.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerApi {
    private PlayerSeasonInfoManager playerSeasonInfoManager;
    private MVPManager mvpManager;

    @Autowired
    public PlayerApi(PlayerSeasonInfoManager playerInfoManager, MVPManager mvpManager) {
        this.playerSeasonInfoManager = playerInfoManager;
        this.mvpManager = mvpManager;
    }

    @GetMapping()
    public List<PlayerSeasonInfo> getAll(){
        return playerSeasonInfoManager.findAll();
    }

    @GetMapping(value = "/mvp", produces =  "application/json")
    public String getCurrentMVP(){

        MVP mvp = mvpManager.findByActive();

        JSONObject mvpInfo = new JSONObject();
        mvpInfo.put("name", mvp.getPlayerSeasonInfo().getPlayer().getPerson().getName());
        mvpInfo.put("surname", mvp.getPlayerSeasonInfo().getPlayer().getPerson().getSurname());
        mvpInfo.put("team", mvp.getPlayerSeasonInfo().getTeamInfo().getTeam().getName());
        mvpInfo.put("statisticLine", mvp.getPlayerLine());
        mvpInfo.put("weekInfo", mvp.getWeekInfo());
        mvpInfo.put("playerImageURL", mvp.getPlayerSeasonInfo().getPlayer().getImageUrl());
        mvpInfo.put("teamImageURL", mvp.getPlayerSeasonInfo().getTeamInfo().getTeam().getImageUrl());


        String response = new JSONObject()
                .put("status", "200")
                .put("result", mvpInfo)
                .toString();

        return response;
    }

    @GetMapping(value = "/statistics/{playerId}", produces = "application/json")
    //returns all players with game elements in the season from given league
    public String getAllPlayerStatistics(@PathVariable long playerId,
                                         @RequestParam(name = "avg", required = false) boolean avg,
                                         @RequestParam(name = "points", required = false) boolean points,
                                         @RequestParam(name = "assists", required = false) boolean assists,
                                         @RequestParam(name = "two_points", required = false) boolean twoPoints,
                                         @RequestParam(name = "three_points", required = false) boolean threePoints,
                                         @RequestParam(name = "blocks", required = false) boolean blocks,
                                         @RequestParam(name = "steals", required = false) boolean steals,
                                         @RequestParam(name = "turnovers", required = false) boolean turnovers,
                                         @RequestParam(name = "rebounds", required = false) boolean rebounds,
                                         @RequestParam(name = "free_throws", required = false) boolean freeThrows
    ){
        JSONObject allStatistics = new JSONObject();
        List<PlayerSeasonInfo> playerInfoList = playerSeasonInfoManager.findByPlayerId(playerId);

        boolean average = false;
        Optional<Boolean> optAvg = Optional.ofNullable(avg);
        if(optAvg.isPresent() && optAvg.get()){
            average = true;
        }

        for(PlayerSeasonInfo playerSeasonInfo : playerInfoList) {
            JSONObject statistics = Calculator.makeStatistics(points,assists,blocks,steals,turnovers,freeThrows,rebounds,twoPoints,threePoints,playerSeasonInfo,average);
            allStatistics.put(String.valueOf(playerSeasonInfo.getTeamInfo().getLeagueGroup().getSeason().getId()), statistics);
        }

        String response = new JSONObject()
                .put("seasons_amount", playerInfoList.size())
                .put("status", "200")
                .put("result", allStatistics)
                .toString();

        return response;
    }

    @GetMapping(value = "/statistics/{playerId}/{seasonId}",produces = "application/json")
    //returns all players with game elements in the season from given league
    public String getPlayerStatisticsFromSeason(@PathVariable long playerId, @PathVariable long seasonId,
                                                @RequestParam(name = "avg", required = false) boolean avg,
                                                @RequestParam(name = "points", required = false) boolean points,
                                                @RequestParam(name = "assists", required = false) boolean assists,
                                                @RequestParam(name = "two_points", required = false) boolean twoPoints,
                                                @RequestParam(name = "three_points", required = false) boolean threePoints,
                                                @RequestParam(name = "blocks", required = false) boolean blocks,
                                                @RequestParam(name = "steals", required = false) boolean steals,
                                                @RequestParam(name = "turnovers", required = false) boolean turnovers,
                                                @RequestParam(name = "rebounds", required = false) boolean rebounds,
                                                @RequestParam(name = "free_throws", required = false) boolean freeThrows
    ){
        List<PlayerSeasonInfo> playerInfoList = playerSeasonInfoManager.findByPlayerId(playerId);
        JSONObject statistics = null;

        boolean average = false;
        Optional<Boolean> optAvg = Optional.ofNullable(avg);
        if(optAvg.isPresent() && optAvg.get()){
            average = true;
        }

        for(PlayerSeasonInfo playerSeasonInfo : playerInfoList) {
            if(playerSeasonInfo.getTeamInfo().getLeagueGroup().getSeason().getId()!=seasonId){
                continue;
            }
            statistics = Calculator.makeStatistics(points,assists,blocks,steals,turnovers,freeThrows,rebounds,twoPoints,threePoints,playerSeasonInfo,average);
            break;
        }

        String response = new JSONObject()
                .put("status", "200")
                .put("result", statistics)
                .toString();

        return response;
    }

    @GetMapping(value = "/statistics/{playerId}/game/{gameId}",produces = "application/json")
    //returns all players with game elements in the season from given league
    public String getPlayerStatisticsFromGame(@PathVariable long playerId, @PathVariable long gameId){
        List<PlayerSeasonInfo> playerInfoList = playerSeasonInfoManager.findByPlayerId(playerId);
        JSONObject statistics = new JSONObject();
        for(PlayerSeasonInfo playerSeasonInfo : playerInfoList) {
            List<PlayerInGame> playerInGameList = playerSeasonInfo.getPlayerInGameList();
            for(PlayerInGame playerInGame : playerInGameList){
                Game game;
                if(playerInGame.getTeamDetail().getGameAsAway()==null){
                    game = playerInGame.getTeamDetail().getGameAsHome();
                }
                else{
                    game = playerInGame.getTeamDetail().getGameAsAway();
                }
                if(game.getId()==gameId){
                    PlayerStatisticLine playerStatisticLine = playerInGame.getPlayerStatisticLine();
                    statistics.put("points", playerStatisticLine.getPoints());
                    statistics.put("assists", playerStatisticLine.getAssists());
                    statistics.put("blocks", playerStatisticLine.getBlocks());
                    statistics.put("steal", playerStatisticLine.getSteals());
                    statistics.put("turnovers", playerStatisticLine.getTurnovers());
                    statistics.put("fouls", playerStatisticLine.getFouls());
                    statistics.put("foulsOn", playerStatisticLine.getFoulsOn());
                    statistics.put("twoPoints", Calculator.getShotInfo(playerStatisticLine.getTwoPointShotsMade(), playerStatisticLine.getTwoPointShots(),1));
                    statistics.put("threePoints", Calculator.getShotInfo(playerStatisticLine.getThreePointShotsMade(), playerStatisticLine.getThreePointShots(),1));
                    statistics.put("freeThrows", Calculator.getShotInfo(playerStatisticLine.getFreeThrowsMade(), playerStatisticLine.getFreeThrows(),1));
                    statistics.put("time", playerInGame.getPlayerStatisticLine().getMinutesPlayed() +":" + playerInGame.getPlayerStatisticLine().getSecondsPlayed());
                    break;
                }
            }
        }

        String response = new JSONObject()
                .put("status", "200")
                .put("result", statistics)
                .toString();

        return response;
    }
}
