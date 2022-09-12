package com.sfyn.Ciclo3.controller;

import com.sfyn.Ciclo3.entitis.Empresa;
import com.sfyn.Ciclo3.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

//@RestController
@Controller

public class Controlador {
    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/Empresas", "/VerEmpresas"})
    public String viewEmpresas(Model model) {
        List<Empresa>listaEmpresas=empresaService.getAllEmpresas();
        model.addAttribute("emplist",listaEmpresas);
        return "verEmpresas";    //este es el nombre para ver en html

    }

}


