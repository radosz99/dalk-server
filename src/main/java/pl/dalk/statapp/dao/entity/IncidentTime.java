package pl.dalk.statapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter @ToString
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "incident_time")
public class IncidentTime implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private int minutes;
    private int seconds;
    private int quarter;

    public IncidentTime(int minutes, int seconds, int quarter) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.quarter = quarter;
    }
}
