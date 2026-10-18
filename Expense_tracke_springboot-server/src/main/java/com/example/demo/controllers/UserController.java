package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserServices;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserServices.class.getName());


    @Autowired
    private UserServices userServices;
//
//    @GetMapping
//    public ResponseEntity<User> getUserById(@RequestParam int userId){
//        System.out.println(">>> HANDLER REACHED, userId = " + userId);
//        logger.info("Getting user by id: "+ userId);
//        Optional<User> userOptional = userServices.getUserById(userId);
//        if (userOptional.isEmpty())
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        else
//            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
//    }

    @GetMapping
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        System.out.println(">>> HANDLER REACHED, userId = " + email);
        logger.info("Getting user by email: "+ email);
        Optional<User> userOptional = userServices.getUserByEmail(email);
        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password){
Optional <User> userOptional = userServices.getUserByEmail(email);

if(userOptional.isEmpty()){
    return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
if(!password.equals(userOptional.get().getPassword())){
    return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}
return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping

    public ResponseEntity<User> signUpUser(@RequestBody User user){
        logger.info("creating new USer");
        User newUser = userServices.createUSer(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
