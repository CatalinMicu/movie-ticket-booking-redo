package cinema.DAO;

import cinema.entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<movie, Integer> {
    movie findByName(String theName);

    @Query("SELECT m FROM movie m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<movie> findByNameContainingIgnoreCase(@Param("name") String theName);
}
