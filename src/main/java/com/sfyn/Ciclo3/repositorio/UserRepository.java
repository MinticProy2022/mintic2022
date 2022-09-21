package com.sfyn.Ciclo3.repositorio;

import com.sfyn.Ciclo3.entitis.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
