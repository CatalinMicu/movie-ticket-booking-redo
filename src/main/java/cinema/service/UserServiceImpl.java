package cinema.service;

import cinema.DAO.UserRepository;
import cinema.entity.user;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserService) {
        userRepository = theUserService;
    }

    @Override
    public List<user> findAll() {
        return userRepository.findAll();
    }

    @Override
    public user findById(int theId) {
        Optional<user> rezultat = userRepository.findById(theId);

        user theUser = null;

        if (rezultat.isPresent()) {
            theUser = rezultat.get();
        }
        else {
            throw new RuntimeException("Did not find user with id - " + theId);
        }
        return theUser;
    }

    @Override
    public user save(user theUser) {
       return userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

}
