package pl.edu.pjwstk.simulator.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Passenger;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.repository.PassengerRepository;
import pl.edu.pjwstk.simulator.repository.StationRepository;
import pl.edu.pjwstk.simulator.repository.TrainRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SimulatorService {
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private final PassengerRepository passengerRepository;

    public SimulatorService(TrainRepository trainRepository, StationRepository stationRepository, PassengerRepository passengerRepository) {
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
        this.passengerRepository = passengerRepository;
    }

    public void move() {
        for (Train train : trainRepository.findAll()) {
            Long nextStationId = train.getStation().getId();
            removePassengers(train);
            if (train.getDirection() == 0 && train.getCooldown() == 0) {
                nextStationId = nextStationId - 1;
                addPassengers(train);
                if (nextStationId == 1) {
                    train.setCooldown(2);
                    train.setDirection(1);
                }
            } else if (train.getCooldown() == 0) {
                addPassengers(train);
                nextStationId = nextStationId + 1;
                if (nextStationId == 15) {
                    train.setCooldown(2);
                    train.setDirection(0);
                }
            } else {
                train.setCooldown(train.getCooldown() - 1);
            }
            train.setStation(stationRepository.findById(nextStationId).get());
            trainRepository.save(train);
        }
    }

    public void addPassengers(Train train) {
        int numOfPassengers = ThreadLocalRandom.current().nextInt(2, 9);
        for (Compartment compartment : train.getCompartmentList()) {
            if (numOfPassengers == 0) {
                break;
            }
            int passengersInCompartment = compartment.getPassengerList().size();
            int freeSpace = compartment.getSize() - passengersInCompartment;
            if (freeSpace != 0) {
                int i = 0;
                while (i < freeSpace && numOfPassengers > 0) {
                    Passenger passenger = new Passenger();
                    passenger.setTargetStation(stationRepository.getOne(passenger.getStation_id()));
                    passenger.setCompartment(compartment);
                    numOfPassengers -= 1;
                    passengerRepository.save(passenger);
                }
            }
        }
    }

    public void removePassengers(Train train) {
        List<Passenger> passengersToDelete = new ArrayList<>();
        for (Compartment compartment : train.getCompartmentList()) {
            for (Passenger passenger : compartment.getPassengerList()) {
                if (passenger.getTargetStation().equals(train.getStation())) {
                    passengersToDelete.add(passenger);
                }
            }
        }
        passengerRepository.deleteInBatch(passengersToDelete);
    }

}
