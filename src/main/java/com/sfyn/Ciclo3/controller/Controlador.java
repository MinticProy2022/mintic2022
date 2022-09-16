package com.sfyn.Ciclo3.controller;

import com.sfyn.Ciclo3.entitis.Empleado;
import com.sfyn.Ciclo3.entitis.Empresa;
import com.sfyn.Ciclo3.services.EmpleadoService;
import com.sfyn.Ciclo3.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Controller

public class Controlador {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;


//***************************************************************
    //***********controlador Empresa**********************************
    @GetMapping({"/", "/verEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje")String mensaje) {
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("emplist", listaEmpresas);
        model.addAttribute("mensaje", mensaje);
        return "mostrarEmpresas";    //aqu llamamos al html

    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model,@ModelAttribute("mensaje")String mensaje) {
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";

    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(emp) == true) {

            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/verEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/mostrarEmpresa"; //aqui hace retorno a la lista de empresas pasando por el html
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp=empresaService.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        //
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    //aqui se activa el boton actualizar

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa (@ModelAttribute("emp") Empresa emp, RedirectAttributes redirectAttributes) {
        if(empresaService.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");

            return "redirect:/verEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarEmpresa";
    }
    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empresaService.deleteEmpresa(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/verEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/verEmpresas";
    }
//**************************************************************************
    //************controlador Movimiento dinero*********************************





    //***************************************************************************
    //**************controlador Empleado**************************************
    @GetMapping({ "/verEmpleados"})
    public String viewEmpleados(Model model, @ModelAttribute("mensaje")String mensaje) {
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        model.addAttribute("mensaje", mensaje);
        return "mostrarEmpleados";    //aqui llamamo al html el que esta en templates llamado mostrarEmpleado

    }
    //***********************agregar empleado************************************
    @GetMapping("/AgregarEmpleado") //esta es la ruta con la que se hace el llamado de este bloque en el host
    public String nuevoEmpleado(Model model,@ModelAttribute("mensaje")String mensaje) {
        Empleado emple = new Empleado();
        model.addAttribute("emple", emple);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresa= empresaService.getAllEmpresas();
        model.addAttribute("emprelist",listaEmpresa);

        return "agregarEmpleado";//aqui llamamos a el html AgregarEmpleado

    }
//*****************Guardar empleado***************************
    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado emple, RedirectAttributes redirectAttributes) {
        if (empleadoService.saveOrUpdateEmpleado(emple) == true) {

            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/verEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/AgregarEmpleado"; //aqui hace retorno a la lista de empresas pasando por el html
    }




}
