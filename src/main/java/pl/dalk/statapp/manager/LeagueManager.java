package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.LeagueRepo;
import pl.dalk.statapp.dao.entity.League;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueManager {
    private LeagueRepo leagueRepo;

    @Autowired
    public LeagueManager(LeagueRepo leagueRepo) {
        this.leagueRepo = leagueRepo;
    }

    public League save(League league){
        return leagueRepo.save(league);
    }

    public List<League> findAll(){
        return leagueRepo.findAll();
    }

    public Optional<League> findById(Long id){
        return leagueRepo.findById(id);
    }
}
