package com.sfyn.Ciclo3.controller;

import com.sfyn.Ciclo3.entitis.Empleado;
import com.sfyn.Ciclo3.entitis.Empresa;
import com.sfyn.Ciclo3.entitis.MovimientoDinero;
import com.sfyn.Ciclo3.repositorio.MovimientosRepository;
import com.sfyn.Ciclo3.services.EmpleadoService;
import com.sfyn.Ciclo3.services.EmpresaService;
import com.sfyn.Ciclo3.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    @Autowired
    MovimientosRepository movimientosRepository;
    @Autowired
    MovimientosService movimientosService;

    //***************************************************************
    //***********controlador login**********************************
    @GetMapping("/")
    public String login(Model model, @AuthenticationPrincipal OidcUser principal){
        return "login";
    }


    //***************************************************************
    //***********controlador Empresa**********************************
    @GetMapping("/verEmpresas")
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("emplist", listaEmpresas);
        model.addAttribute("mensaje", mensaje);
        return "mostrarEmpresas";    //aqu llamamos al html

    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje) {
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
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        Empresa emp = empresaService.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        //
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    //aqui se activa el boton actualizar

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("emp") Empresa emp, RedirectAttributes redirectAttributes) {
        if (empresaService.saveOrUpdateEmpresa(emp) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");

            return "redirect:/verEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarEmpresa";
    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (empresaService.deleteEmpresa(id) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/verEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/verEmpresas";
    }
//**************************************************************************
    //************controlador Movimiento dinero*********************************

    @RequestMapping("/mostrarMovimientos")// Controlador que nos lleva al template donde veremos todos los movimientos
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<MovimientoDinero> listaMovimientos = movimientosRepository.findAll();
        /*
        model.addAttribute("movlist", paginaMovimientos.getContent());
        model.addAttribute("paginas", new int[paginaMovimientos.getTotalPages()]);


        model.addAttribute("paginaActual", NumeroPagina);

         */
        model.addAttribute("movlist", listaMovimientos);
        model.addAttribute("mensaje", mensaje);
        Long sumaMonto = movimientosService.obtenerSumaMontos();
        model.addAttribute("SumaMontos", sumaMonto);//Mandamos la suma de todos los montos a la plantilla
        return "mostrarMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/AgregarMovimiento") //Controlador que nos lleva al template donde podremos crear un nuevo movimiento
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero movimiento = new MovimientoDinero();
        model.addAttribute("mov", movimiento);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "agregarMovimiento";

        /*
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String correo=auth.getName();
        Integer idEmpleado=movimientosService.IdPorCorreo(correo);
        model.addAttribute("idEmpleado",idEmpleado);
        return "agregarMovimiento"; //Llamar HTML

         */
    }

    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes) {
        if (movimientosService.saveOrUpdateMovimiento(mov)) {
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/mostrarMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero mov = movimientosService.getMovimientoById(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("mov", mov);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "editarMovimiento";
    }

    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes) {
        if (movimientosService.saveOrUpdateMovimiento(mov)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/mostrarMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarMovimiemto/" + mov.getId();

    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (movimientosService.deleteMovimiento(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/mostrarMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/mostrarMovimientos";
    }

    @GetMapping("/Empleado/{id}/Movimientos") //Filtro de movimientos por empleados
    public String movimientosPorEmpleado(@PathVariable("id") Integer id, Model model) {
        List<MovimientoDinero> movlist = movimientosService.obtenerPorEmpleado(id);
        model.addAttribute("movlist", movlist);
        Long sumaMonto = movimientosService.MontosPorEmpleado(id);
        model.addAttribute("SumaMontos", sumaMonto);
        return "mostrarMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/Empresa/{id}/Movimientos") //Filtro de movimientos por empresa
    public String movimientosPorEmpresa(@PathVariable("id") Integer id, Model model) {
        List<MovimientoDinero> movlist = movimientosService.obtenerPorEmpresa(id);
        model.addAttribute("movlist", movlist);
        Long sumaMonto = movimientosService.MontosPorEmpresa(id);
        model.addAttribute("SumaMontos", sumaMonto);
        return "mostrarMovimientos"; //Llamamos al HTML
    }

    //Controlador que me lleva al template de No autorizado
    //@RequestMapping(value="/Denegado")
    // public String accesoDenegado(){
    //   return "accessDenied";
    //}


    //***************************************************************************
    //**************controlador Empleado**************************************
    @GetMapping({"/verEmpleados"})
    public String viewEmpleados(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        model.addAttribute("mensaje", mensaje);
        return "mostrarEmpleados";    //aqui llamamo al html el que esta en templates llamado mostrarEmpleado

    }

    //***********************agregar empleado************************************
    @GetMapping("/AgregarEmpleado") //esta es la ruta con la que se hace el llamado de este bloque en el host
    public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje) {
        Empleado emple = new Empleado();
        model.addAttribute("empl", emple);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresa = empresaService.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresa);

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

    //**************************Editar*******************************************************
    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        Empleado empl = empleadoService.getEmpleadoById(id).get();
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("empl", empl);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "editarEmpleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String updateEmpleado(@ModelAttribute("empl") Empleado empl, RedirectAttributes redirectAttributes) {
        Integer id = empl.getId(); //Sacamos el id del objeto empl
        String Oldpass = empleadoService.getEmpleadoById(id).get().getPassword(); //Con ese id consultamos la contraseña que ya esta en la base
        if (!empl.getPassword().equals(Oldpass)) {
            // String passEncriptada=passwordEncoder().encode(empl.getPassword());
            // empl.setPassword(passEncriptada);
        }
        if (empleadoService.saveOrUpdateEmpleado(empl)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/verEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarEmpleado/" + empl.getId();

    }

    //*********************Eliminar************************
    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (empleadoService.deleteEmpleado(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/verEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/verEmpleados";
    }

    @GetMapping("/Empresa/{id}/Empleados")
    public String verEmpleadosPorEmpresa(@PathVariable("id") Integer id, Model model) {
        List<Empleado> listaEmpleados = empleadoService.obtenerPorEmpresa(id);
        model.addAttribute("emplelist", listaEmpleados);
        return "verEmpleados";
    }


    //Metodo para encriptar contraseñas
    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //  return new BCryptPasswordEncoder();
    //  }

    @GetMapping("/Empresa/{id}/AgregarMovimiento") //Controlador que nos lleva al template donde podremos crear un nuevo movimiento
    public String nuevoMovimientoEmpresa(@PathVariable("id") Integer id, Model model, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero movimiento = new MovimientoDinero();
        model.addAttribute("mov", movimiento);
        model.addAttribute("mensaje", mensaje);
        Empresa empresa = empresaService.getEmpresaById(id);
        model.addAttribute("emprelist", empresa);
        return "agregarMovimiento";
    }
}
