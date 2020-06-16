package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.PlayerSeasonInfoRepo;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerSeasonInfoManager {
    private PlayerSeasonInfoRepo playerInfoRepo;

    @Autowired
    public PlayerSeasonInfoManager(PlayerSeasonInfoRepo playerInfoRepo) {
            this.playerInfoRepo = playerInfoRepo;
            }

//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    public PlayerSeasonInfo getPlayersSeasonInfo(Long id){
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("playerId", id);
//        String sql = " select c.id, c.player_id,c.team_info_id  "
//                + " from player_season_info c"
//                + " where  c.player_id = :playerId ";
//
//        return (PlayerSeasonInfo)jdbcTemplate.queryForObject(
//                sql, parameters, BeanPropertyRowMapper.newInstance(PlayerSeasonInfo.class));
//
//    }


    public PlayerSeasonInfo save(PlayerSeasonInfo league){
            return playerInfoRepo.save(league);
    }

    public Page<PlayerSeasonInfo> findPart(){
        return playerInfoRepo.findAll(PageRequest.of(0, 2, Sort.by("id").descending()));
    }
    public List<PlayerSeasonInfo> findAll(){
        return playerInfoRepo.findAll();
    }

    public List<PlayerSeasonInfo> findByPlayerId(Long playerId){
        return playerInfoRepo.findByPlayerId(playerId);
    }

    public Optional<PlayerSeasonInfo> findById(Long id){
            return playerInfoRepo.findById(id);
            }
}
