package cinema.service;

import cinema.entity.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<user> findAll();

    user findById(int theId);

    user save(user theUser);

    void deleteById(int theId);
    user findByUsername(String username);
}
