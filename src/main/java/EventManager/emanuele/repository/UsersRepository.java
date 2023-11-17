package EventManager.emanuele.repository;

import EventManager.emanuele.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository  extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
