package pl.dalk.statapp.api;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dalk.statapp.dao.entity.*;
import pl.dalk.statapp.manager.LeagueGroupManager;
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
    private LeagueGroupManager leagueGroupManager;

    @Autowired
    public SeasonApi(SeasonManager seasonManager, PlayerSeasonInfoManager playerSeasonInfoManager,LeagueGroupManager leagueGroupManager) {
        this.seasonManager = seasonManager;
        this.playerSeasonInfoManager = playerSeasonInfoManager;
        this.leagueGroupManager = leagueGroupManager;
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


    @GetMapping(value = "/statistics/{seasonId}/{leagueId}", produces = "application/json")
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

        JSONObject allStatistics = new JSONObject();
        List<PlayerSeasonInfo> playerInfoList = playerSeasonInfoManager.findAll();

        boolean average = false;
        Optional<Boolean> optAvg = Optional.ofNullable(avg);
        if(optAvg.isPresent() && optAvg.get()){
            average = true;
        }


        int playersCounter = 0;
        for(PlayerSeasonInfo playerSeasonInfo : playerInfoList) {
            if(playerSeasonInfo.getTeamInfo().getLeagueGroup().getSeason().getId()!=seasonId || playerSeasonInfo.getTeamInfo().getLeagueGroup().getLeagueInfo().getLeague().getId()!=leagueId){
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


    @GetMapping(value = "/table/{seasonId}", produces = "application/json")
    //returns all players with game elements in the season from given league
    public String getTable(@PathVariable long seasonId){
        JSONObject allTables = new JSONObject();
                Optional<Season> season = seasonManager.findById(seasonId);

        List<LeagueInfo> leagueInfoList = season.get().getLeagueInfoList();


        List<JSONObject> leaguesList = new ArrayList<>();
        for(LeagueInfo leagueInfo : leagueInfoList){
            JSONObject league = new JSONObject();
            league.put("name", leagueInfo.getLeague().getName());
            List<JSONObject> groupsList = new ArrayList<>();
            for(LeagueGroup leagueGroup : leagueInfo.getLeagueGroupList()){
                JSONObject group = new JSONObject();
                group.put("name", leagueGroup.getName());
                List<JSONObject> teamsList = new ArrayList<>();
                for(TeamInfo teamInfo : leagueGroup.getTeamInfoList()){
                    JSONObject team = new JSONObject();
                    team.put("name", teamInfo.getTeam().getName());
                    team.put("points", teamInfo.getPoints());
                    team.put("matchesPlayed", teamInfo.getMatchesPlayed());
                    team.put("matchesLost", teamInfo.getMatchesLost());
                    team.put("matchesWon", teamInfo.getMatchesWon());
                    team.put("littlePointsLost", teamInfo.getLittlePointsLost());
                    team.put("littlePointsScored", teamInfo.getLittlePointsScored());
                    teamsList.add(team);
                }
                group.put("teams", teamsList);
                groupsList.add(group);
            }
            league.put("groups", groupsList);
            leaguesList.add(league);
        }
        String response = new JSONObject()
                .put("status", "200")
                .put("result", leaguesList)
                .toString();

        return response;
    }

}
