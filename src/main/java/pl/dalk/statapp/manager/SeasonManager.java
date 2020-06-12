package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.SeasonRepo;
import pl.dalk.statapp.dao.entity.Season;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonManager {
    private SeasonRepo seasonRepo;

    @Autowired
    public SeasonManager(SeasonRepo seasonRepo) {
        this.seasonRepo = seasonRepo;
    }

    public Season save(Season season){
        return seasonRepo.save(season);
    }

    public List<Season> findAll(){
        return seasonRepo.findAll();
    }

    public Optional<Season> findById(Long id){
        return seasonRepo.findById(id);
    }
}
