package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 1/7/2018.
 */

public class Usuariosproyectos {
    private UsuariosproyectosPK usuariosproyectosPK;
    private Roles idrol;

    public Usuariosproyectos() {
    }

    public Usuariosproyectos(UsuariosproyectosPK usuariosproyectosPK, Roles idrol) {
        this.usuariosproyectosPK = usuariosproyectosPK;
        this.idrol = idrol;
    }

    public Usuariosproyectos(UsuariosproyectosPK usuariosproyectosPK) {
        this.usuariosproyectosPK = usuariosproyectosPK;
    }

    public UsuariosproyectosPK getUsuariosproyectosPK() {
        return usuariosproyectosPK;
    }

    public void setUsuariosproyectosPK(UsuariosproyectosPK usuariosproyectosPK) {
        this.usuariosproyectosPK = usuariosproyectosPK;
    }

    public Roles getIdrol() {
        return idrol;
    }

    public void setIdrol(Roles idrol) {
        this.idrol = idrol;
    }

    @Override
    public String toString() {
        return "Usuariosproyectos{" +
                "usuariosproyectosPK=" + usuariosproyectosPK +
                ", idrol=" + idrol +
                '}';
    }
}
