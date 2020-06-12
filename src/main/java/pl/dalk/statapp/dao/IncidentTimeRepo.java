package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.IncidentTime;

@Repository
public interface IncidentTimeRepo extends JpaRepository<IncidentTime, Long> {
}
