package com.sfyn.Ciclo3.entitis;

import javax.persistence.*;

@Entity
@Table(name="Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private  String correo;

    //asocio  muchos empleados a una sola empresa y se unen en empresa por id el solo crea el get y set
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private  Empresa empresa;

    private String password;
    private Boolean estado;

    private String rol;

//****************constructor***************************************************


    public Empleado() {
    }

    public Empleado( String nombre, String correo, Empresa empresa, String password, Boolean estado, String rol) {

        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
    }

    //***********get and set**************************************

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    //get y set automaticos de empresa, generados automaticamente cuando se genero @manytoOne

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
