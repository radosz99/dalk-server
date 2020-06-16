package pl.dalk.statapp.api;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dalk.statapp.dao.entity.PlayerInGame;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;
import pl.dalk.statapp.dao.entity.Season;
import pl.dalk.statapp.manager.PlayerSeasonInfoManager;
import pl.dalk.statapp.manager.SeasonManager;
import pl.dalk.statapp.statistic.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/season")
public class SeasonApi {

    private SeasonManager seasonManager;
    private PlayerSeasonInfoManager playerSeasonInfoManager;

    @Autowired
    public SeasonApi(SeasonManager seasonManager, PlayerSeasonInfoManager playerSeasonInfoManager) {
        this.seasonManager = seasonManager;
        this.playerSeasonInfoManager = playerSeasonInfoManager;
    }

    @GetMapping()
    public List<Season> getAll(){
        return seasonManager.findAll();
    }

    @PostMapping()
    public Season addSeason(@RequestBody Season season){
        return seasonManager.save(season);
    }


    @GetMapping("/{seasonId}")
    public Optional<Season> getById(@PathVariable Long seasonId){
        Optional<Season> season = seasonManager.findById(seasonId);
        if(season.isPresent()) {
            return seasonManager.findById(seasonId);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Season not found"
            );
        }
    }


    @GetMapping(value = "/{seasonId}/statistics/{leagueId}", produces = "application/json")
    //returns all players with game elements in the season from given league
    public String getStatistics(@PathVariable long leagueId, @PathVariable long seasonId,
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

        long start = System.nanoTime();
        JSONObject allStatistics = new JSONObject();
        List<PlayerSeasonInfo> playerInfoList = playerSeasonInfoManager.findAll();

        boolean average = false;
        Optional<Boolean> optAvg = Optional.ofNullable(avg);
        if(optAvg.isPresent() && optAvg.get()){
            average = true;
        }


        int playersCounter = 0;
        for(PlayerSeasonInfo playerSeasonInfo : playerInfoList) {
            if(playerSeasonInfo.getTeamInfo().getSeason().getId()!=seasonId || playerSeasonInfo.getTeamInfo().getLeague().getId()!=leagueId){
                continue;
            }

            playersCounter++;
            JSONObject statistics;
            statistics = Calculator.makeStatistics(points,assists,blocks,steals,turnovers,freeThrows,rebounds,twoPoints,threePoints,playerSeasonInfo,average);
            allStatistics.put(String.valueOf(playerSeasonInfo.getPlayer().getId()), statistics);
        }

        String response = new JSONObject()
                .put("players_amount", playersCounter)
                .put("status", "200")
                .put("result", allStatistics)
                .toString();

        return response;
    }

}
