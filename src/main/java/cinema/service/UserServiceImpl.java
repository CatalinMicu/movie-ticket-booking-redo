package cinema.service;

import cinema.DAO.AuthoritiesRepository;
import cinema.DAO.UserRepository;
import cinema.entity.user;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;

    public UserServiceImpl(UserRepository theuserRepository, AuthoritiesRepository theAuthoritiesRepostiroy) {
        userRepository = theuserRepository;
        authoritiesRepository = theAuthoritiesRepostiroy;
    }

    @Override
    public List<user> findAll() {
        return userRepository.findAll();
    }

    @Override
    public user findById(int id) {
        Optional<user> result = userRepository.findById(id);

        user theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find user id - " + id);
        }

        return theUser;
    }

    @Override
    public user save(user theUser) {
        return userRepository.save(theUser);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

}