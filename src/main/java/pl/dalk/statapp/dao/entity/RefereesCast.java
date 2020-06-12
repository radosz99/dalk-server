package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @OneToMany(mappedBy = "refereesCast", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Game> gameList = new ArrayList<>();

    public RefereesCast(Referee mainReferee, Referee secondReferee) {
        this.mainReferee = mainReferee;
        this.secondReferee = secondReferee;
    }
}
