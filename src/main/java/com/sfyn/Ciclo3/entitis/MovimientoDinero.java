package com.sfyn.Ciclo3.entitis;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Movimientos")
public class MovimientoDinero {
    @Id //este es el primary key y es autogenerado
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long monto;
    private String concepto;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;    //en lugar de usuario , se crea un odjeto de la clase empleado
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;


    //***********constructor*************************************************************

    public MovimientoDinero() {
    }

    public MovimientoDinero(int id, long monto, String concepto, Empresa empresa, Date fecha) {
        this.id = id;
        this.monto = monto;
        this.concepto = concepto;
        this.empresa = empresa;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}