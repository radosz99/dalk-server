package pl.dalk.statapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dalk.statapp.dao.entity.PlayerInfo;
import pl.dalk.statapp.dao.entity.Shot;
import pl.dalk.statapp.manager.PlayerInfoManager;

import java.util.List;

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

    @GetMapping("/{player}/points")
    public int getAllPoints(@PathVariable long player){
        int points=0;
        for(Shot shot : playerInfoManager.findById(player).get().getShotList()){
            if(shot.isHit()){
                points+=shot.getType();
            }
        }
        return points;
    }

    @GetMapping("/{player}/points/{game}")
    public int getPointsInGame(@PathVariable long player, @PathVariable long game){
        int points=0;
        for(Shot shot : playerInfoManager.findById(player).get().getShotList()){
            if(shot.isHit() && shot.getGame().getId()==game){
                points+=shot.getType();
            }
        }
        return points;
    }
}
