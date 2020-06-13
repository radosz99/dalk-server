package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.PlayerSeasonInfoRepo;
import pl.dalk.statapp.dao.entity.PlayerSeasonInfo;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerSeasonInfoManager {
    private PlayerSeasonInfoRepo playerInfoRepo;

    @Autowired
    public PlayerSeasonInfoManager(PlayerSeasonInfoRepo playerInfoRepo) {
            this.playerInfoRepo = playerInfoRepo;
            }

    public PlayerSeasonInfo save(PlayerSeasonInfo league){
            return playerInfoRepo.save(league);
    }

    public Page<PlayerSeasonInfo> findPart(){
        return playerInfoRepo.findAll(PageRequest.of(0, 2, Sort.by("id").descending()));
    }

    public List<PlayerSeasonInfo> findAll(){
            return playerInfoRepo.findAll();
            }

    public Optional<PlayerSeasonInfo> findById(Long id){
            return playerInfoRepo.findById(id);
            }
}
