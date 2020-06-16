package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.Player;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;

import java.util.List;

@Repository
public interface PlayerSeasonInfoRepo extends JpaRepository<PlayerSeasonInfo, Long> {
    List<PlayerSeasonInfo> findByPlayerId(Long playerId);
}
