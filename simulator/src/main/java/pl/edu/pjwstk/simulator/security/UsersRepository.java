package pl.edu.pjwstk.simulator.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByLogin(String login);
}
