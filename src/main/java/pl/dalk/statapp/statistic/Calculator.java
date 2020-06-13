package pl.dalk.statapp.statistic;

import org.json.JSONObject;
import pl.dalk.statapp.dao.entity.PlayerInGame;

import java.util.List;

public class Calculator {
    public static int getPlayerPoints(List<PlayerInGame> playerInGameList){
        int points = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            points += playerInGame.getPlayerStatisticLine().getPoints();
        }
        return points;
    }

    public static int getPlayerAssists(List<PlayerInGame> playerInGameList){
        int assists = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            assists += playerInGame.getPlayerStatisticLine().getAssists();
        }
        return assists;
    }

    public static int getPlayerBlocks(List<PlayerInGame> playerInGameList){
        int blocks = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            blocks += playerInGame.getPlayerStatisticLine().getBlocks();
        }
        return blocks;
    }

    public static JSONObject getPlayerTwoPointsShot(List<PlayerInGame> playerInGameList){
        int twoPointsAll = 0, twoPointsMade = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            twoPointsAll += playerInGame.getPlayerStatisticLine().getTwoPointShots();
            twoPointsMade += playerInGame.getPlayerStatisticLine().getTwoPointShotsMade();
        }
        JSONObject result = new JSONObject()
                .put("all", twoPointsAll)
                .put("made", twoPointsMade);
        return result;
    }
}
