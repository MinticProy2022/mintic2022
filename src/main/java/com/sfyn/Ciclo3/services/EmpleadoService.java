package com.sfyn.Ciclo3.services;

import com.sfyn.Ciclo3.entitis.Empleado;
import com.sfyn.Ciclo3.entitis.Empresa;
import com.sfyn.Ciclo3.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    //Metodo para ver todos los empleados registrados
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList= new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //Metodo para buscar empleados por ID
    public Optional<Empleado> getEmpleadoById(Integer id){ //Existe optional y asi se podria usar

        return empleadoRepository.findById(id);
    }
    //**************************************************************************************************
   //**************************Metodo para buscar empleados por empresa**********************************

    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepository.findByEmpresa(id);
    }

    //Metodo para guardar o actualizar registros en Empleados
    public boolean saveOrUpdateEmpleado(Empleado empl) {
        Empleado emple = empleadoRepository.save(empl);
        if (empleadoRepository.findById(emple.getId()) != null) {
            return true;
        }
        return false;

    }

    //*************************************************************************************************
    //*********************** Metodo para eliminar un registro de Empleado por Id************************

    //Metodo para eliminar un registro de Empleado por Id
    public boolean deleteEmpleado(Integer id){
        empleadoRepository.deleteById(id);
        if(this.empleadoRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

}