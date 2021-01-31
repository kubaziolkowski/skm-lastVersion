package pl.edu.pjwstk.simulator.models;

import pl.edu.pjwstk.simulator.service.DbEntity;

import javax.persistence.*;
import java.util.concurrent.ThreadLocalRandom;


@Entity
@Table(name = "passengers")
public class Passenger implements DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_station", referencedColumnName = "id")
    Station targetStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compartment_id", referencedColumnName = "id")
    Compartment compartment;


    @Transient
    int station_id;

    public Passenger() {

        firstName = String.valueOf(FirstName.VALUES.get(ThreadLocalRandom.current().nextInt(0, 100)));
        lastName = String.valueOf(LastName.VALUES.get(ThreadLocalRandom.current().nextInt(0, 94)));
        station_id = ThreadLocalRandom.current().nextInt(1, 15);
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(Station targetStation) {
        this.targetStation = targetStation;
    }

    public long getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public void setCompartment(Compartment compartment) {
        this.compartment = compartment;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " | " + getTargetStation().getName();
    }
}
