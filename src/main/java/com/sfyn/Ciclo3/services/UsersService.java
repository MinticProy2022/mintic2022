package com.sfyn.Ciclo3.services;

import com.sfyn.Ciclo3.entitis.Users;
import com.sfyn.Ciclo3.repositorio.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UsersService {

    private UserRepository userRepository;

    public UsersService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Users crearUsers(Users newUser){
        return this.userRepository.save(newUser);
    }

   public Users ingresarOrCrearUsuario(Map<String, Object> userData){

        String email = (String) userData.get("email");
        String name = (String) userData.get("nickname");
        String image = (String) userData.get("picture");
        String auth0Id = (String) userData.get("sub");

        Users newUser = new Users(email=email, image=image, auth0Id=auth0Id);
        return crearUsers(newUser);
   }

}
