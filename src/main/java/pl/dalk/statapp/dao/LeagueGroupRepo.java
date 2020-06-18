package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.LeagueGroup;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;

import java.util.List;

@Repository
public interface LeagueGroupRepo extends JpaRepository<LeagueGroup, Long> {
    List<LeagueGroup> findBySeasonId(Long seasonId);
}
