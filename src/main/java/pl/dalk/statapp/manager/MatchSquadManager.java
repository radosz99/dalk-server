package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.MatchSquadRepo;
import pl.dalk.statapp.dao.entity.TeamDetail;

import java.util.List;
import java.util.Optional;

@Service
public class MatchSquadManager {
    private MatchSquadRepo matchSquadRepo;

    @Autowired
    public MatchSquadManager(MatchSquadRepo matchSquadRepo) {
        this.matchSquadRepo = matchSquadRepo;
    }

    public TeamDetail save(TeamDetail matchSquad){
        return matchSquadRepo.save(matchSquad);
    }

    public List<TeamDetail> findAll(){
        return matchSquadRepo.findAll();
    }

    public Optional<TeamDetail> findById(Long id){
        return matchSquadRepo.findById(id);
    }
}
