package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.PlayerInfoRepo;
import pl.dalk.statapp.dao.entity.PlayerInfo;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerInfoManager {
    private PlayerInfoRepo playerInfoRepo;

    @Autowired
    public PlayerInfoManager(PlayerInfoRepo playerInfoRepo) {
            this.playerInfoRepo = playerInfoRepo;
            }

    public PlayerInfo save(PlayerInfo league){
            return playerInfoRepo.save(league);
            }

    public List<PlayerInfo> findAll(){
            return playerInfoRepo.findAll();
            }

    public Optional<PlayerInfo> findById(Long id){
            return playerInfoRepo.findById(id);
            }
}
