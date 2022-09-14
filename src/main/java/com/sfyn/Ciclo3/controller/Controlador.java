package com.sfyn.Ciclo3.controller;

import com.sfyn.Ciclo3.entitis.Empresa;
import com.sfyn.Ciclo3.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Controller

public class Controlador {
    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/", "/verEmpresas"})
    public String viewEmpresas(Model model) {
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("emplist", listaEmpresas);
        return "mostrarEmpresas";    //este es el nombre para ver en html

    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model) {
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        return "agregarEmpresa";

    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(emp) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/verEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/mostrarEmpresa";
    }

}
