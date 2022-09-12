package com.sfyn.Ciclo3.repositorio;

import com.sfyn.Ciclo3.entitis.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //anotacion que le dice a spring que esta clase es un repositorio
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {



}
