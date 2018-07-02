package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 1/7/2018.
 */

public class SprintsPK {

    private Integer idsprint;
    private Integer idproyecto;

    public SprintsPK() {
    }

    public SprintsPK(Integer idsprint, Integer idproyecto) {
        this.idsprint = idsprint;
        this.idproyecto = idproyecto;
    }

    public Integer getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Integer idsprint) {
        this.idsprint = idsprint;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    @Override
    public String toString() {
        return "SprintsPK{" +
                "idsprint=" + idsprint +
                ", idproyecto=" + idproyecto +
                '}';
    }
}
