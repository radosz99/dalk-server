package pl.dalk.statapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dalk.statapp.dao.entity.Game;
import pl.dalk.statapp.dao.entity.PlayerInGame;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;
import pl.dalk.statapp.dao.entity.PlayerStatisticLine;
import pl.dalk.statapp.manager.PlayerSeasonInfoManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerApi {
    private PlayerSeasonInfoManager playerSeasonInfoManager;

    @Autowired
    public PlayerApi(PlayerSeasonInfoManager playerInfoManager) {
        this.playerSeasonInfoManager = playerInfoManager;
    }

    @GetMapping()
    public List<PlayerSeasonInfo> getAll(){
        return playerSeasonInfoManager.findAll();
    }

    @GetMapping("/{playerId}/{seasonId}/points")
    //returns all player points in the season TODO : should be player instead playerSeasonInfo or made other endpoint
    public int getAllPlayerPointsInSeason(@PathVariable long playerId, @PathVariable long seasonId){
        List<PlayerSeasonInfo> playerSeasonInfoList = playerSeasonInfoManager.findAll()
                .stream()
                .filter(playerSeasonInfo -> playerSeasonInfo.getPlayer().getId()==playerId)
                .collect(Collectors.toList());
        int points = 0;
        if(playerSeasonInfoList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That player has no game history!"
            );
        }
        else{
            for(PlayerSeasonInfo playerSeasonInfo : playerSeasonInfoList) {
                for (PlayerInGame playerInGame : playerSeasonInfo.getPlayerInGameList()) {
                    Optional<Game> game = Optional.ofNullable(playerInGame.getTeamDetail().getGameAsAway());
                    if(!game.isPresent()){
                        game = Optional.ofNullable(playerInGame.getTeamDetail().getGameAsHome());
                    }
                    if (game.get().getSeason().getId() == seasonId) {
                        points += playerInGame.getPlayerStatisticLine().getPoints();
                    }
                }
            }
        }
        return points;
    }


    @GetMapping("/{playerId}/points/{gameId}")
    //returns player points in the game
    public int getPlayerPointsInGame(@PathVariable long playerId, @PathVariable long gameId){
        List<PlayerSeasonInfo> playerSeasonInfoList = playerSeasonInfoManager.findAll()
                .stream().filter(playerSeasonInfo -> playerSeasonInfo.getPlayer().getId()==playerId)
                .collect(Collectors.toList());
        int points = 0;
        if(playerSeasonInfoList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "That player has no game history!"
            );
        }
        else{
            for(PlayerSeasonInfo playerSeasonInfo: playerSeasonInfoList) {
                for (PlayerInGame playerInGame : playerSeasonInfo.getPlayerInGameList()) {
                    Optional<Game> game = Optional.ofNullable(playerInGame.getTeamDetail().getGameAsAway());
                    if(!game.isPresent()){
                        game = Optional.ofNullable(playerInGame.getTeamDetail().getGameAsHome());
                    }
                    if (game.get().getId() == gameId) {
                        points = playerInGame.getPlayerStatisticLine().getPoints();
                    }
                }
            }
        }
        return points;
    }
}
