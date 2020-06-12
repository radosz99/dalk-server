package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.RefereesCastRepo;
import pl.dalk.statapp.dao.entity.RefereesCast;

import java.util.List;
import java.util.Optional;

@Service
public class RefereesCastManager {
    private RefereesCastRepo refereesCastRepo;

    @Autowired
    public RefereesCastManager(RefereesCastRepo refereesCastRepo) {
        this.refereesCastRepo = refereesCastRepo;
    }

    public RefereesCast save(RefereesCast refereesCast){
        return refereesCastRepo.save(refereesCast);
    }

    public List<RefereesCast> findAll(){
        return refereesCastRepo.findAll();
    }

    public Optional<RefereesCast> findById(Long id){
        return refereesCastRepo.findById(id);
    }
}
