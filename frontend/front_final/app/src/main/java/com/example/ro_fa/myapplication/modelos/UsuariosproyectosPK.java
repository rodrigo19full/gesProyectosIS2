package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 1/7/2018.
 */

public class UsuariosproyectosPK {
    private Integer idproyecto;
    private Integer idusuario;

    public UsuariosproyectosPK() {
    }

    public UsuariosproyectosPK(Integer idproyecto, Integer idusuario) {
        this.idproyecto = idproyecto;
        this.idusuario = idusuario;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public String toString() {
        return "UsuariosproyectosPK{" +
                "idproyecto=" + idproyecto +
                ", idusuario=" + idusuario +
                '}';
    }
}
