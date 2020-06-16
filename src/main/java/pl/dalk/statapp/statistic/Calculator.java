package pl.dalk.statapp.statistic;

import org.json.JSONObject;
import pl.dalk.statapp.dao.entity.PlayerInGame;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class Calculator {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static String getNumberInString(int number, int divider){
        if(divider > 1) {
            return DECIMAL_FORMAT.format((float) number / divider);
        }
        else{
            return String.valueOf(number);
        }
    }
    public static String getPlayerPoints(int divider, List<PlayerInGame> playerInGameList){
        int points = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            points += playerInGame.getPlayerStatisticLine().getPoints();
        }

        return getNumberInString(points, divider);
    }

    public static String getPlayerAssists(int divider, List<PlayerInGame> playerInGameList){
        int assists = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            assists += playerInGame.getPlayerStatisticLine().getAssists();
        }
        return getNumberInString(assists, divider);
    }

    public static String getPlayerBlocks(int divider, List<PlayerInGame> playerInGameList){
        int blocks = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            blocks += playerInGame.getPlayerStatisticLine().getBlocks();
        }
        return getNumberInString(blocks, divider);
    }

    public static String getPlayerSteals(int divider, List<PlayerInGame> playerInGameList){
        int steals = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            steals += playerInGame.getPlayerStatisticLine().getSteals();
        }
        return getNumberInString(steals, divider);
    }

    public static String getPlayerTurnovers(int divider, List<PlayerInGame> playerInGameList){
        int turnovers = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            turnovers += playerInGame.getPlayerStatisticLine().getTurnovers();
        }
        return getNumberInString(turnovers, divider);
    }

    public static JSONObject getPlayerRebounds(int divider, List<PlayerInGame> playerInGameList){
        int offensive = 0, defensive = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            defensive += playerInGame.getPlayerStatisticLine().getDefensiveRebound();
            offensive += playerInGame.getPlayerStatisticLine().getOffensiveRebound();
        }
        return getReboundInfo(defensive, offensive,divider);
    }

     public static JSONObject getReboundInfo(int defensive, int offensive, int divider){
        String _offensive = getNumberInString(offensive, divider);
        String _defensive = getNumberInString(defensive, divider);
        return new JSONObject()
                .put("offensive", _offensive)
                .put("defensive", _defensive);
     }

    public static JSONObject getShotInfo(int made, int attempts, int divider){
        String _made = getNumberInString(made, divider);
        String _attempts = getNumberInString(attempts, divider);
        return new JSONObject()
                .put("attempts", _attempts)
                .put("made", _made);
    }
    public static JSONObject getPlayerTwoPointsShot(int divider, List<PlayerInGame> playerInGameList){
        int twoPointsAll = 0, twoPointsMade = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            twoPointsAll += playerInGame.getPlayerStatisticLine().getTwoPointShots();
            twoPointsMade += playerInGame.getPlayerStatisticLine().getTwoPointShotsMade();
        }
        return getShotInfo(twoPointsMade, twoPointsAll,divider);
    }

    public static JSONObject getPlayerThreePointsShot(int divider, List<PlayerInGame> playerInGameList){
        int threePointsAll = 0, threePointsMade = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            threePointsAll += playerInGame.getPlayerStatisticLine().getThreePointShots();
            threePointsMade += playerInGame.getPlayerStatisticLine().getThreePointShotsMade();
        }

        return getShotInfo(threePointsMade, threePointsAll,divider);
    }

    public static JSONObject getPlayerFreeThrows(int divider, List<PlayerInGame> playerInGameList){
        int freeThrowsAll = 0, freeThrowsMade = 0;
        for(PlayerInGame playerInGame : playerInGameList){
            freeThrowsAll += playerInGame.getPlayerStatisticLine().getFreeThrows();
            freeThrowsMade += playerInGame.getPlayerStatisticLine().getFreeThrowsMade();
        }
        return getShotInfo(freeThrowsMade, freeThrowsAll,divider);
    }

    public static JSONObject makeStatistics(boolean points, boolean assists, boolean blocks, boolean steals, boolean turnovers,
                                              boolean freeThrows, boolean rebounds, boolean twoPoints, boolean threePoints,
                                              PlayerSeasonInfo playerSeasonInfo, boolean avg){
        JSONObject statistics = new JSONObject();
        statistics.put("matches", playerSeasonInfo.getPlayerInGameList().size());
        statistics.put("name", playerSeasonInfo.getPlayer().getPerson().getName());
        statistics.put("surname", playerSeasonInfo.getPlayer().getPerson().getSurname());
        statistics.put("team", playerSeasonInfo.getTeamInfo().getTeam().getName());
        int divider = 1;
        if(avg){
            divider = playerSeasonInfo.getPlayerInGameList().size();
        }
        JSONObject info = new JSONObject();

        Optional<Boolean> ifPoints = Optional.ofNullable(points);
        if(ifPoints.isPresent() && ifPoints.get()) {
            info.put("points", Calculator.getPlayerPoints(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifAssists = Optional.ofNullable(assists);
        if(ifAssists.isPresent() && ifAssists.get()) {
            info.put("assists", Calculator.getPlayerAssists(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifBlocks = Optional.ofNullable(blocks);
        if(ifBlocks.isPresent() && ifBlocks.get()) {
            info.put("blocks", Calculator.getPlayerBlocks(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifSteals = Optional.ofNullable(steals);
        if(ifSteals.isPresent() && ifSteals.get()) {
            info.put("steals", Calculator.getPlayerSteals(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifTurnovers = Optional.ofNullable(turnovers);
        if(ifTurnovers.isPresent() && ifTurnovers.get()) {
            info.put("turnovers", Calculator.getPlayerTurnovers(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifRebounds = Optional.ofNullable(rebounds);
        if(ifRebounds.isPresent() && ifRebounds.get()) {
            info.put("rebounds", Calculator.getPlayerRebounds(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifTwoPoints = Optional.ofNullable(twoPoints);
        if(ifTwoPoints.isPresent() && ifTwoPoints.get()) {
            info.put("twoPoints", Calculator.getPlayerTwoPointsShot(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifThreePoints = Optional.ofNullable(threePoints);
        if(ifThreePoints.isPresent() && ifThreePoints.get()) {
            info.put("threePoints", Calculator.getPlayerThreePointsShot(divider, playerSeasonInfo.getPlayerInGameList()));
        }

        Optional<Boolean> ifFreeThrows = Optional.ofNullable(freeThrows);
        if(ifFreeThrows.isPresent() && ifFreeThrows.get()) {
            info.put("freeThrows", Calculator.getPlayerFreeThrows(divider, playerSeasonInfo.getPlayerInGameList()));
        }
        statistics.put("statistics", info);
        return statistics;
    }


}
