package pl.edu.pjwstk.simulator.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.repository.CompartmentRepository;
import pl.edu.pjwstk.simulator.repository.PassengerRepository;

import java.util.Optional;

import static pl.edu.pjwstk.simulator.util.Utils.fallbackIfNull;

@Service
public class CompartmentService extends CrudService<Compartment>{
    private final PassengerRepository passengerRepository;

    public CompartmentService(CompartmentRepository compartmentRepository, PassengerRepository passengerRepository) {
        super(compartmentRepository);
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Compartment createOrUpdate(Compartment updateEntity) {
        if (updateEntity.getId() == null) {
            return repository.save(updateEntity);
        }

        Optional<Compartment> compartmentInDb = repository.findById(updateEntity.getId());

        if (compartmentInDb.isPresent()) {
            Compartment dbEntity = compartmentInDb.get();
            dbEntity.setSize(fallbackIfNull(updateEntity.getSize(), dbEntity.getSize()));
            dbEntity.setTrain(fallbackIfNull(updateEntity.getTrain(), dbEntity.getTrain()));
            Compartment insertedCompartment = repository.save(dbEntity);

            return insertedCompartment;
        } else {
            updateEntity = repository.save(updateEntity);
            return updateEntity;
        }
    }
}
