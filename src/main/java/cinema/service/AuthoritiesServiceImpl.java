package cinema.service;

import cinema.DAO.AuthoritiesRepository;
import cinema.entity.authorities;
import cinema.entity.movie;
import cinema.entity.user;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private AuthoritiesRepository authoritiesRepository;

    public AuthoritiesServiceImpl(AuthoritiesRepository THEauthoritiesRepository) {
        authoritiesRepository = THEauthoritiesRepository;
    }
    @Override
    public List<authorities> findAll() {
        return authoritiesRepository.findAll();
    }

    @Override
    public authorities findById(int id) {
        Optional<authorities> rezultat = authoritiesRepository.findById(id);
        authorities theAuthority = null;

        if (rezultat.isPresent()) {
            theAuthority = rezultat.get();
        } else {
            throw new RuntimeException("BULEALA");
        }

        return theAuthority;
    }

    @Override
    public authorities save(authorities authority) {
        return authoritiesRepository.save(authority);
    }

    @Override
    public void deleteById(int id) {
        authoritiesRepository.deleteById(id);
    }

    @Override
    public void saveAuthorityForUser(user user, String authorityName) {
        authorities authority = new authorities(authorityName, user);
        authoritiesRepository.save(authority);
    }


    @Transactional
    public void saveAuthorityForUser(user user) {
        authorities authority = new authorities();
        authority.setUser(user);
        authority.setAuthority("USER");
        authoritiesRepository.save(authority);
    }
}
