package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.MVP;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;

import java.util.List;

@Repository
public interface MVPRepo extends JpaRepository<MVP, Long> {
    List<MVP> findByActive(boolean active);
}
