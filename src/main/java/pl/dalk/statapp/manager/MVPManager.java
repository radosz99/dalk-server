package pl.dalk.statapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.MVPRepo;
import pl.dalk.statapp.dao.entity.MVP;
import java.util.List;
import java.util.Optional;

@Service
public class MVPManager {
    private MVPRepo mvpRepo;

    @Autowired
    public MVPManager(MVPRepo mvpRepo) {
        this.mvpRepo = mvpRepo;
    }

    public MVP save(MVP mvp){
        return mvpRepo.save(mvp);
    }

    public List<MVP> findAll(){
        return mvpRepo.findAll();
    }

    public Optional<MVP> findById(int id){
        return mvpRepo.findById((long) id);
    }

    public MVP findByActive(){
        return mvpRepo.findByActive(true).get(0);
    }


}
