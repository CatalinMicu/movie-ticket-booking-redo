package cinema.service;

import cinema.entity.authorities;
import cinema.entity.movie;
import cinema.entity.user;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthoritiesService {

    List<authorities> findAll();

    authorities findById(int id);

    authorities save(authorities authority);

    void deleteById(int id);
    void saveAuthorityForUser(user savedUser, String roleUser);

    void saveAuthorityForUser(user dbUser);
}
