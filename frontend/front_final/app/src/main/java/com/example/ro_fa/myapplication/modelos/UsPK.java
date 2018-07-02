package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 1/7/2018.
 */

public class UsPK {
    private Integer idus;
    private Integer idproyecto;
    private Integer idsprint;

    public UsPK() {
    }

    public UsPK(Integer idus, Integer idproyecto, Integer idsprint) {
        this.idus = idus;
        this.idproyecto = idproyecto;
        this.idsprint = idsprint;
    }

    public Integer getIdus() {
        return idus;
    }

    public void setIdus(Integer idus) {
        this.idus = idus;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Integer getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Integer idsprint) {
        this.idsprint = idsprint;
    }

    @Override
    public String toString() {
        return "UsPK{" +
                "idus=" + idus +
                ", idproyecto=" + idproyecto +
                ", idsprint=" + idsprint +
                '}';
    }
}
