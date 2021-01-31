package pl.edu.pjwstk.simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.simulator.models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
