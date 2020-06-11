package pl.dalk.statapp.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "referees_cast")
public class RefereesCast implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_referee_id", referencedColumnName = "id")
    private Referee mainReferee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_referee_id", referencedColumnName = "id")
    private Referee secondReferee;

//    @OneToMany(mappedBy = "refereesCast", fetch = FetchType.LAZY)
//    private List<Game> gameList = new ArrayList<>();

    public RefereesCast(Referee mainReferee, Referee secondReferee) {
        this.mainReferee = mainReferee;
        this.secondReferee = secondReferee;
    }
}
