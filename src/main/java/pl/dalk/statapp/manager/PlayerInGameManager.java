package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.PlayerInGameRepo;
import pl.dalk.statapp.dao.RefereesCastRepo;
import pl.dalk.statapp.dao.entity.PlayerInGame;
import pl.dalk.statapp.dao.entity.RefereesCast;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerInGameManager
{
    private PlayerInGameRepo playerInGameRepo;

    @Autowired
    public PlayerInGameManager(PlayerInGameRepo playerInGameRepo) {
        this.playerInGameRepo = playerInGameRepo;
    }

    public PlayerInGame save(PlayerInGame playerInGame){
        return playerInGameRepo.save(playerInGame);
    }

    public List<PlayerInGame> findAll(){
        return playerInGameRepo.findAll();
    }

    public Optional<PlayerInGame> findById(Long id){
        return playerInGameRepo.findById(id);
    }
}
