package pl.edu.pjwstk.simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.simulator.models.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
}
