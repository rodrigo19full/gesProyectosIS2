package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 1/7/2018.
 */

public class Us {
    private UsPK usPK;
    private Integer tiempoejecucion;
    private String descripcion;
    private String nombre;
    private Integer horastrabajadas;
    private Integer estado;
    private Integer numerous;
    private Integer idusuario;
    private Integer valortecnico;
    private Integer valornegocio;

    public Us() {
    }

    public Us(UsPK usPK, Integer tiempoejecucion, String descripcion, String nombre, Integer horastrabajadas, Integer estado, Integer numerous, Integer idusuario, Integer valortecnico, Integer valornegocio) {
        this.usPK = usPK;
        this.tiempoejecucion = tiempoejecucion;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.horastrabajadas = horastrabajadas;
        this.estado = estado;
        this.numerous = numerous;
        this.idusuario = idusuario;
        this.valortecnico = valortecnico;
        this.valornegocio = valornegocio;
    }

    public UsPK getUsPK() {
        return usPK;
    }

    public void setUsPK(UsPK usPK) {
        this.usPK = usPK;
    }

    public Integer getTiempoejecucion() {
        return tiempoejecucion;
    }

    public void setTiempoejecucion(Integer tiempoejecucion) {
        this.tiempoejecucion = tiempoejecucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getHorastrabajadas() {
        return horastrabajadas;
    }

    public void setHorastrabajadas(Integer horastrabajadas) {
        this.horastrabajadas = horastrabajadas;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getNumerous() {
        return numerous;
    }

    public void setNumerous(Integer numerous) {
        this.numerous = numerous;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getValortecnico() {
        return valortecnico;
    }

    public void setValortecnico(Integer valortecnico) {
        this.valortecnico = valortecnico;
    }

    public Integer getValornegocio() {
        return valornegocio;
    }

    public void setValornegocio(Integer valornegocio) {
        this.valornegocio = valornegocio;
    }

    @Override
    public String toString() {
        return "Us{" +
                "usPK=" + usPK +
                ", tiempoejecucion=" + tiempoejecucion +
                ", descripcion='" + descripcion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horastrabajadas=" + horastrabajadas +
                ", estado=" + estado +
                ", numerous=" + numerous +
                ", idusuario=" + idusuario +
                ", valortecnico=" + valortecnico +
                ", valornegocio=" + valornegocio +
                '}';
    }
}
