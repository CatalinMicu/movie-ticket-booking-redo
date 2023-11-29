package cinema.DAO;

import cinema.entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<movie, Integer> {
    movie findByName(String theName);

}
