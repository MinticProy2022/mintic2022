package com.sfyn.Ciclo3.services;


import com.sfyn.Ciclo3.entitis.Empresa;
import com.sfyn.Ciclo3.repositorio.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class  EmpresaService {


    @Autowired  //conectamos esta clase con el repositorio de empresa

    EmpresaRepository empresaRepository;  //creamos un objeto tipo empresaRepositorio para poder usar los metodos que dicha clase hereda

    //Metodo que retornara la lista de empresas usando metodos heredados del jpaRepository
    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa)); //recorremos la interable que regresa el metodo FindAll del jpa y los guardamos en la lista
        return empresaList;
    }

    //metodo que trae un objeto de tipo empresa cuando cuento con el id de la misma
    public Empresa getEmpresaById(Integer id) {
        return empresaRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo Empresa
    public boolean saveOrUpdateEmpresa(Empresa empresa) {
        Empresa emp = empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getId()) != null) {
            return true;
        }
        return false;

    }

    //metodo para eliminar empresas registradas teniendo el id
    //Metodo para eliminar empresas registradas teniendo el id
    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);  //Eliminar

        if (empresaRepository.findById(id)!=null){  //Verificacion del servicio eliminacion
            return true;
        }
        return false;
    }





    }





