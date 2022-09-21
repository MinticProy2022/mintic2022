package com.sfyn.Ciclo3.services;

import com.sfyn.Ciclo3.entitis.User;
import com.sfyn.Ciclo3.repositorio.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(User newUser){
        return this.userRepository.save(newUser);
    }
    public User findUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User getOrCreateUser(Map<String, Object> userData) {
        String email = (String) userData.get("email");

        User user = findUserByEmail(email);

        if (user == null) {
            String name = (String) userData.get("nickname");
            String auth0Id = (String) userData.get("sub");

            User newUser = new User(email = email, auth0Id = auth0Id);
            return createUser(newUser);
        }
        System.out.println(user.getEmail());
        return user;
    }
}
