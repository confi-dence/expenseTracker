package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.respositories.UserRepository;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;
//import java.util.logging.Logger;

@Service
public class UserServices {

    private final Logger logger = Logger.getLogger(UserServices.class.getName());
    @Autowired
    private UserRepository userRepository;
// get
    public  Optional<User> getUserById(int userId){
        logger.info("Getting the user by id " +userId);

        return userRepository.findById(userId);
    }
    public  Optional<User> getUserByEmail(String email){
        logger.info("Getting the user by email " +email);
        return userRepository.findByEmail(email);
    }

//    post

    public User createUSer(String name, String username, String password){
        User user = new User();
        user.setName(name);
        user.setEmail(username);
        user.setPassword(password);
        user.setCreated_at(LocalDateTime.now());
        return  userRepository.save(user);
    }
}
