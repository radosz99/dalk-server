package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.PlayerRepo;
import pl.dalk.statapp.dao.entity.Player;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerManager {
    private PlayerRepo playerRepo;

    @Autowired
    public PlayerManager(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Player save(Player player){
        return playerRepo.save(player);
    }

    public List<Player> findAll(){
        return playerRepo.findAll();
    }

    public Optional<Player> findById(Long id){
        return playerRepo.findById(id);
    }
}
