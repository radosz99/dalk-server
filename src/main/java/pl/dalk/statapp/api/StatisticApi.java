package pl.dalk.statapp.api;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dalk.statapp.dao.entity.PlayerInGame;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;
import pl.dalk.statapp.manager.PlayerSeasonInfoManager;
import pl.dalk.statapp.statistic.Calculator;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticApi{
    private PlayerSeasonInfoManager playerSeasonInfoManager;

    public StatisticApi(PlayerSeasonInfoManager playerSeasonInfoManager) {
        this.playerSeasonInfoManager = playerSeasonInfoManager;
    }

    @GetMapping(value = "/{seasonId}/{leagueId}", produces = "application/json")
    //returns all players with game elements in the season from given league
    public String getAllPointsInSeason(@PathVariable long seasonId, @PathVariable long leagueId,
                                                              @RequestParam(name = "points", required = false) Boolean points,
                                                              @RequestParam(name = "assists", required = false) Boolean assists,
                                                              @RequestParam(name = "two_points", required = false) Boolean twoPoints,
                                                              @RequestParam(name = "three_points", required = false) Boolean threePoints,
                                                              @RequestParam(name = "blocks", required = false) Boolean blocks,
                                                              @RequestParam(name = "steals", required = false) Boolean steals,
                                                              @RequestParam(name = "turnovers", required = false) Boolean turnovers,
                                                              @RequestParam(name = "free_throws", required = false) Boolean freeThrows
                                                             ){
        JSONObject allStatistics = new JSONObject();
        List<PlayerSeasonInfo> playerInfoList = playerSeasonInfoManager.findAll();

        for(PlayerSeasonInfo playerSeasonInfo : playerInfoList) {
            JSONObject statistics = new JSONObject();
            if(playerSeasonInfo.getTeamInfo().getSeason().getId()!=seasonId || playerSeasonInfo.getTeamInfo().getLeague().getId()!=leagueId){
                continue;
            }
            JSONObject info = new JSONObject();

            Optional<Boolean> ifPoints = Optional.ofNullable(points);
            if(ifPoints.isPresent() && ifPoints.get()) {
                info.put("points", Calculator.getPlayerPoints(playerSeasonInfo.getPlayerInGameList()));
            }

            Optional<Boolean> ifAssists = Optional.ofNullable(assists);
            if(ifAssists.isPresent() && ifAssists.get()) {
                info.put("assists", Calculator.getPlayerAssists(playerSeasonInfo.getPlayerInGameList()));
            }

            Optional<Boolean> ifBlocks = Optional.ofNullable(blocks);
            if(ifBlocks.isPresent() && ifBlocks.get()) {
                info.put("blocks", Calculator.getPlayerBlocks(playerSeasonInfo.getPlayerInGameList()));
            }

            Optional<Boolean> ifTwoPoints = Optional.ofNullable(twoPoints);
            if(ifTwoPoints.isPresent() && ifTwoPoints.get()) {
                info.put("twoPoints", Calculator.getPlayerTwoPointsShot(playerSeasonInfo.getPlayerInGameList()));
            }

            statistics.put("matches", playerSeasonInfo.getPlayerInGameList().size());
            statistics.put("name", playerSeasonInfo.getPlayer().getPerson().getName());
            statistics.put("surname", playerSeasonInfo.getPlayer().getPerson().getSurname());
            statistics.put("team", playerSeasonInfo.getTeamInfo().getTeam().getName());
            statistics.put("statistics", info);
            allStatistics.put(String.valueOf(playerSeasonInfo.getPlayer().getId()), statistics);
        }

        String response = new JSONObject()
                .put("players_amount", playerInfoList.size())
                .put("status", "200")
                .put("result", allStatistics)
                .toString();

        return response;
    }
}
