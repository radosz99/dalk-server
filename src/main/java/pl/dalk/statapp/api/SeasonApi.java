package pl.dalk.statapp.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dalk.statapp.dao.entity.Season;
import pl.dalk.statapp.manager.SeasonManager;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/season")
public class SeasonApi {

    private SeasonManager seasonManager;

    @Autowired
    public SeasonApi(SeasonManager seasonManager) {
        this.seasonManager = seasonManager;
    }

    @GetMapping()
    public List<Season> getAll(){
        return seasonManager.findAll();
    }

    @PostMapping()
    public Season addSeason(@RequestBody Season season){
        return seasonManager.save(season);
    }


    @GetMapping("/{index}")
    public Optional<Season> getById(@PathVariable Long index){
        Optional<Season> season = seasonManager.findById(index);
        if(season.isPresent()) {
            return seasonManager.findById(index);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Season not found"
            );
        }
    }
}
