package com.example.ro_fa.myapplication.modelos;

import java.util.Date;

/**
 * Created by ro_fa on 21/6/2018.
 */

public class Proyectos {
    private Integer idproyecto;
    private Date fechainicio;
    private Integer anho;
    private String descripcion;
    private Date fechafin;
    private Integer codproyecto;
    private String nombre;

    public Proyectos(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Proyectos(Integer idproyecto, Date fechainicio, Integer anho, String descripcion, Date fechafin, Integer codproyecto, String nombre) {
        this.idproyecto = idproyecto;
        this.fechainicio = fechainicio;
        this.anho = anho;
        this.descripcion = descripcion;
        this.fechafin = fechafin;
        this.codproyecto = codproyecto;
        this.nombre = nombre;
    }

    public Proyectos(Date fechainicio, Integer anho, String descripcion, Date fechafin, Integer codproyecto, String nombre) {
        this.fechainicio = fechainicio;
        this.anho = anho;
        this.descripcion = descripcion;
        this.fechafin = fechafin;
        this.codproyecto = codproyecto;
        this.nombre = nombre;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Integer getCodproyecto() {
        return codproyecto;
    }

    public void setCodproyecto(Integer codproyecto) {
        this.codproyecto = codproyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Proyectos{" +
                "idproyecto=" + idproyecto +
                ", fechainicio=" + fechainicio +
                ", anho=" + anho +
                ", descripcion='" + descripcion + '\'' +
                ", fechafin=" + fechafin +
                ", codproyecto=" + codproyecto +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
