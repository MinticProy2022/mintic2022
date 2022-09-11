package com.sfyn.Ciclo3.entitis;


import javax.persistence.*;

@Entity
@Table(name="MovimientoDinero")
public class MovimientoDinero {
    @Id //este es el primary key y es autogenerado
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int in;
    private long monto;
    private  String concepto;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado usuario;    //en lugar de usuario , se crea un odjeto de la clase empleado



    //***********constructor*************************************************************

    public MovimientoDinero() {
    }

    public MovimientoDinero(long monto, String concepto, Empleado empleado) {
        this.monto = monto;
        this.concepto = concepto;
        this.usuario = empleado;
    }

    //***********set and get**************************************************************


    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
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

    //*********get y set creados automaticamente cuando se genero la relacion  @ManyToOne
    public Empleado getUsuario() {
        return usuario;
    }

    public void setUsuario(Empleado empleado) {
        this.usuario = empleado;
    }
}
