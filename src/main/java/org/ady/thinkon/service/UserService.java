package org.ady.thinkon.service;

import org.ady.thinkon.entity.User;
import org.ady.thinkon.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class to manage the operations between the controller and the repository
 *
 * @author Ady Paulino
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public User updateUser(User user, User userToBeUpdated) {
        // map the fields from the user object to the userToBeUpdated object

        if (user.getUsername() != null) {
            userToBeUpdated.setUsername(user.getUsername());
        }
        if (user.getFirstName() != null) {
            userToBeUpdated.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userToBeUpdated.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            userToBeUpdated.setEmail(user.getEmail());
        }
        if (user.getPhoneNumber() != null) {
            userToBeUpdated.setPhoneNumber(user.getPhoneNumber());
        }

        return userRepository.save(userToBeUpdated);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
