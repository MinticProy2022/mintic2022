package com.sfyn.Ciclo3.controller;

import com.sfyn.Ciclo3.entitis.Empresa;
import com.sfyn.Ciclo3.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Service
@Controller
@RequestMapping("/sfyn")
public class Controlador {
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/login")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        return "login";
    }

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
        return "redirect:/mostrarEmpresa"; //aqui hace retorno a la lista de empresas pasando por el html
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp=empresaService.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    //aqui se activa el boton actualizar

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa (@ModelAttribute("emp") Empresa emp){
        if(empresaService.saveOrUpdateEmpresa(emp)==true){

            return "redirect:/verEmpresas";
        }
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



}
