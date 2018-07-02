package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 1/7/2018.
 */

public class Roles {
    private Integer idrol;
    private String rol;
    private Integer estado;

    public Roles() {
    }

    public Roles(Integer idrol) {
        this.idrol = idrol;
    }

    public Roles(Integer idrol, String rol, Integer estado) {
        this.idrol = idrol;
        this.rol = rol;
        this.estado = estado;
    }

    public Integer getIdRol() {
        return idrol;
    }

    public void setIdRol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "idrol=" + idrol +
                ", rol='" + rol + '\'' +
                ", estado=" + estado +
                '}';
    }
}
