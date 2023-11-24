package cinema.DAO;

import cinema.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Integer> {
}
