package pl.dalk.statapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dalk.statapp.dao.entity.Timeout;

@Repository
public interface TimeoutRepo extends JpaRepository<Timeout, Long> {
}
