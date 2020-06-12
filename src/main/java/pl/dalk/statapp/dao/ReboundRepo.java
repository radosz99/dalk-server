package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.Rebound;

@Repository
public interface ReboundRepo extends JpaRepository<Rebound, Long> {
}
