package com.sfyn.Ciclo3.services;

import com.sfyn.Ciclo3.repositorio.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UserRepository userRepository;

    public UsersService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



}
