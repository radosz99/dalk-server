package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.LeagueGroupRepo;
import pl.dalk.statapp.dao.LeagueRepo;
import pl.dalk.statapp.dao.entity.League;
import pl.dalk.statapp.dao.entity.LeagueGroup;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueGroupManager {
    private LeagueGroupRepo leagueGroupRepo;

    @Autowired
    public LeagueGroupManager(LeagueGroupRepo leagueGroupRepo) {
        this.leagueGroupRepo = leagueGroupRepo;
    }

    public LeagueGroup save(LeagueGroup leagueGroup){
        return leagueGroupRepo.save(leagueGroup);
    }

    public List<LeagueGroup> findAll(){
        return leagueGroupRepo.findAll();
    }

    public List<LeagueGroup> findBySeasonId(Long seasonId){
        return leagueGroupRepo.findBySeasonId(seasonId);
    }

    public Optional<LeagueGroup> findById(Long id){
        return leagueGroupRepo.findById(id);
    }
}
