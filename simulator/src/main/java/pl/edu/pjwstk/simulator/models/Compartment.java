package pl.edu.pjwstk.simulator.models;

import pl.edu.pjwstk.simulator.service.DbEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compartments")
public class Compartment implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "size")
    int size;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "compartment_id")
    List<Passenger> passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    Train train;

    public Compartment() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Passenger> getPassengerList() {
        return passenger;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passenger = passengerList;
    }

    @Override
    public Long getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
