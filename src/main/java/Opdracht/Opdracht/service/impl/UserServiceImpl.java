package Opdracht.Opdracht.service.impl;

import Opdracht.Opdracht.entity.User;
import Opdracht.Opdracht.repository.UserRepository;
import Opdracht.Opdracht.service.UserService;
import lombok.AllArgsConstructor;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setVoorNaam(user.getVoorNaam());
        existingUser.setAchterNaam(user.getAchterNaam());
        existingUser.setEmail(user.getEmail());
        existingUser.setGeboorteDatum(user.getGeboorteDatum());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
