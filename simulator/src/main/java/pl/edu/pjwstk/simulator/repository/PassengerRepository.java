package pl.edu.pjwstk.simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.simulator.models.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
