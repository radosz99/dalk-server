package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="player")
public class Player implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @NotNull
    private String dalkId;
    private String imageUrl;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(mappedBy = "player", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PlayerSeasonInfo> playerInfoList = new ArrayList<>();



    public Player(@NotNull String dalkId,Person person) {
        this.dalkId = dalkId;
        this.person = person;
    }

}
