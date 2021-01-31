package pl.edu.pjwstk.simulator.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.repository.CompartmentRepository;
import pl.edu.pjwstk.simulator.repository.StationRepository;
import pl.edu.pjwstk.simulator.repository.TrainRepository;

import java.util.Optional;

import static pl.edu.pjwstk.simulator.util.Utils.fallbackIfNull;

@Service
public class TrainService extends CrudService<Train>{
    private final CompartmentRepository compartmentRepository;
    private final StationRepository stationRepository;

    public TrainService(TrainRepository trainRepository, CompartmentRepository compartmentRepository, StationRepository stationRepository) {
        super(trainRepository);
        this.compartmentRepository = compartmentRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public Train createOrUpdate(Train updateEntity) {
        if (updateEntity.getId() == null) {
            return repository.save(updateEntity);
        }

        Optional<Train> trainInDb = repository.findById(updateEntity.getId());

        if (trainInDb.isPresent()) {
            Train dbEntity = trainInDb.get();
            dbEntity.setStation(fallbackIfNull(updateEntity.getStation(), dbEntity.getStation()));
            dbEntity.setDirection(fallbackIfNull(updateEntity.getDirection(), dbEntity.getDirection()));
            dbEntity.setCooldown(fallbackIfNull(updateEntity.getCooldown(), dbEntity.getCooldown()));
            Train insertedTrain = repository.save(dbEntity);

            return insertedTrain;
        } else {
            updateEntity = repository.save(updateEntity);
            return updateEntity;
        }
    }
}
