package com.umg.daniloPortillo.ApiDoctorStrange.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "Cita")
public class Cita {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private final Date fecha = new Date();

    @Column(name = "hora")
    private int hora;

    @Column(name = "paciente")
    private Long paciente;

    @Column(name = "estado")
    private String estado = "pendiente";

    @Column(name = "observaciones")
    private String observaciones;

    public Cita(Long id, int hora, Long paciente, String estado, String observaciones) {
        this.id = id;
        this.hora = hora;
        this.paciente = paciente;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Cita() {super();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
