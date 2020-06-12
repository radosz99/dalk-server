package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.RefereesCast;

@Repository
public interface RefereesCastRepo extends JpaRepository<RefereesCast, Long> {
}
