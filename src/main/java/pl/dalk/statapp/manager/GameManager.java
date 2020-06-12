package pl.dalk.statapp.manager;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dalk.statapp.dao.GameRepo;
import pl.dalk.statapp.dao.SeasonRepo;
import pl.dalk.statapp.dao.entity.Game;
import pl.dalk.statapp.dao.entity.Season;

import java.util.List;
import java.util.Optional;

@Service
public class GameManager {
    private GameRepo gameRepo;

    @Autowired
    public GameManager(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Game save(Game game){
        try {
            System.out.println(game);
            return gameRepo.save(game);
        } catch (PropertyValueException p){
            return null;
        }
    }

    public List<Game> findAll(){
        return gameRepo.findAll();
    }

    public Optional<Game> findById(Long id){
        return gameRepo.findById(id);
    }
}
