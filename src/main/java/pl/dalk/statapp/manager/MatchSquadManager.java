package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.MatchSquadRepo;
import pl.dalk.statapp.dao.PlayerInGameRepo;
import pl.dalk.statapp.dao.entity.MatchSquad;
import pl.dalk.statapp.dao.entity.PlayerInGame;

import java.util.List;
import java.util.Optional;

@Service
public class MatchSquadManager {
    private MatchSquadRepo matchSquadRepo;

    @Autowired
    public MatchSquadManager(MatchSquadRepo matchSquadRepo) {
        this.matchSquadRepo = matchSquadRepo;
    }

    public MatchSquad save(MatchSquad matchSquad){
        return matchSquadRepo.save(matchSquad);
    }

    public List<MatchSquad> findAll(){
        return matchSquadRepo.findAll();
    }

    public Optional<MatchSquad> findById(Long id){
        return matchSquadRepo.findById(id);
    }
}
