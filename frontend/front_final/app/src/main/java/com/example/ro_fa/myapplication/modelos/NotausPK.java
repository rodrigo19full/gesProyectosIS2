package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 2/7/2018.
 */

public class NotausPK {

    private Integer idnota;
    private Integer idproyecto;
    private Integer idus;
    private Integer idsprint;

    public NotausPK() {
    }

    public NotausPK(Integer idnota, Integer idproyecto, Integer idus, Integer idsprint) {
        this.idnota = idnota;
        this.idproyecto = idproyecto;
        this.idus = idus;
        this.idsprint = idsprint;
    }

    public Integer getIdnota() {
        return idnota;
    }

    public void setIdnota(Integer idnota) {
        this.idnota = idnota;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Integer getIdus() {
        return idus;
    }

    public void setIdus(Integer idus) {
        this.idus = idus;
    }

    public Integer getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Integer idsprint) {
        this.idsprint = idsprint;
    }

    @Override
    public String toString() {
        return "NotausPK{" +
                "idnota=" + idnota +
                ", idproyecto=" + idproyecto +
                ", idus=" + idus +
                ", idsprint=" + idsprint +
                '}';
    }
}
