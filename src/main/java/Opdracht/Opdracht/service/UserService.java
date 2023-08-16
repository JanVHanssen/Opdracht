package Opdracht.Opdracht.service;

import Opdracht.Opdracht.entity.User;
import Opdracht.Opdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {


    User createUser(User user);

    User getUserById(Long userid);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long Userid);

}
