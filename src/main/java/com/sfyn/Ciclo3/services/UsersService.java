package com.sfyn.Ciclo3.services;

import com.sfyn.Ciclo3.entitis.Users;
import com.sfyn.Ciclo3.repositorio.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

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

       String auth0Id = (String) userData.get("sub");
       String email = (String) userData.get("email");
       String name = (String) userData.get("nickname");
       String image = (String) userData.get("picture");

       Optional<Users> users = userRepository.findById(auth0Id);

         if(users.isEmpty()){
            Users newUser = new Users(email=email, image=image, auth0Id=auth0Id);
            return crearUsers(newUser);
        }
        return users.get();
   }

}
