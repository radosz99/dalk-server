package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.Season;

@Repository
public interface SeasonRepo extends JpaRepository<Season, Long> {
}
