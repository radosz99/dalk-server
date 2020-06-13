package pl.dalk.statapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dalk.statapp.dao.entity.PlayerInfo;
import pl.dalk.statapp.dao.entity.Shot;
import pl.dalk.statapp.manager.PlayerInfoManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerApi {
    private PlayerInfoManager playerInfoManager;

    @Autowired
    public PlayerApi(PlayerInfoManager playerInfoManager) {
        this.playerInfoManager = playerInfoManager;
    }

    @GetMapping()
    public List<PlayerInfo> getAll(){
        return playerInfoManager.findAll();
    }

    @GetMapping("/{playerId}/{seasonId}/points")
    public int getAllPlayerPointsInSeason(@PathVariable long playerId, @PathVariable long seasonId){
        int points=0;
        for(Shot shot : playerInfoManager.findById(playerId).get().getShotList()){
            if(shot.isHit() && shot.getGame().getSeason().getId()==seasonId){
                points+=shot.getType();
            }
        }
        return points;
    }

    @GetMapping("/{seasonId}/points")
    public List<HashMap<String, String>> getAllPointsInSeason(@PathVariable long seasonId){
        List<HashMap<String, String>> statistics = new ArrayList<>();
        List<PlayerInfo> playerInfoList = playerInfoManager.findAll();

        for(PlayerInfo playerInfo : playerInfoList) {
            long points = 0;
            HashMap<String, String> info = new HashMap<>();
            List<Shot> shotsInSeason = playerInfoManager.findById(playerInfo.getPlayer().getId()).get().getShotList().stream()
                    .filter(shot -> shot.isHit() && shot.getGame().getSeason().getId()==seasonId)
                    .collect(Collectors.toList());
            for(Shot shot : shotsInSeason){
                points += shot.getType();
            }
            info.put("points", String.valueOf(points));
            info.put("id", String.valueOf(playerInfo.getPlayer().getId()));
            info.put("name", playerInfo.getPlayer().getPerson().getName());
            info.put("surname", playerInfo.getPlayer().getPerson().getSurname());
            info.put("team", playerInfo.getTeamInfo().getTeam().getName());
            statistics.add(info);
        }
        return statistics;
    }


    @GetMapping("/{playerId}/points/{gameId}")
    public int getPlayerPointsInGame(@PathVariable long playerId, @PathVariable long gameId){
        int points=0;
        for(Shot shot : playerInfoManager.findById(playerId).get().getShotList()){
            if(shot.isHit() && shot.getGame().getId()==gameId){
                points+=shot.getType();
            }
        }
        return points;
    }
}
