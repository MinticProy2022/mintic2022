package com.sfyn.Ciclo3;

import com.sfyn.Ciclo3.entitis.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class Ciclo3Application {

	//con este bloque se hace un test a hola ciclo 3
	@GetMapping("/hello")
	public String hello(){
		return"hola ciclo 3 fuerza aqui no vamos con toda";
	}

	//con este bloque se hace un test a empresa
@GetMapping("/test")
	public String test(){
	Empresa emp = new Empresa("Asistec SAS","Calle la 22","321321321", "80031214-1");
		emp.setNombre("Asistec LTDA");
		//System.out.println("Aqui ya se creo la empresa y se renombro");
		return emp.getNombre();
	}

	public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);

	}

}
