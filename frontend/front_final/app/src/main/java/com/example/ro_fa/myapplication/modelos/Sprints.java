package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 21/6/2018.
 */

public class Sprints {

    private SprintsPK sprintsPK;
    private Integer duracion;
    private Integer estado;
    private String nombre;

    public Sprints() {
    }

    public Sprints(SprintsPK sprintsPK, Integer duracion, Integer estado, String nombre) {
        this.sprintsPK = sprintsPK;
        this.duracion = duracion;
        this.estado = estado;
        this.nombre = nombre;
    }

    public SprintsPK getSprintsPK() {
        return sprintsPK;
    }

    public void setSprintsPK(SprintsPK sprintsPK) {
        this.sprintsPK = sprintsPK;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Sprints{" +
                "sprintsPK=" + sprintsPK +
                ", duracion=" + duracion +
                ", estado=" + estado +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
