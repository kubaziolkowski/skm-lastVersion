package pl.edu.pjwstk.simulator.models;

import pl.edu.pjwstk.simulator.service.DbEntity;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "trains")
public class Train implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station", referencedColumnName = "id")
    Station station;

    @Column(name = "direction")
    int direction;

    @Column(name = "cooldown")
    int cooldown;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    List<Compartment> compartment;


    public Train() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCooldown() {
        return cooldown;
    }

    public List<Compartment> getCompartmentList() {
        return compartment;
    }

    public void setCompartmentList(List<Compartment> compartment) {
        this.compartment = compartment;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

}
