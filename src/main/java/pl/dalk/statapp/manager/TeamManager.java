package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.TeamRepo;
import pl.dalk.statapp.dao.entity.Team;

import java.util.List;
import java.util.Optional;

@Service
public class TeamManager {
    private TeamRepo teamRepo;

    @Autowired
    public TeamManager(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    public Team save(Team team){
        return teamRepo.save(team);
    }

    public List<Team> findAll(){
        return teamRepo.findAll();
    }

    public Optional<Team> findById(Long id){
        return teamRepo.findById(id);
    }
}
