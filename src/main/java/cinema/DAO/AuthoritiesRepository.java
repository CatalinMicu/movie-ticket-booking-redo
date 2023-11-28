package cinema.DAO;

import cinema.entity.authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<authorities, Integer> {
    void deleteById(int id);
}
