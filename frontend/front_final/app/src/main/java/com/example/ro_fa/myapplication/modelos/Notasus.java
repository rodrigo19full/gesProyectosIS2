package com.example.ro_fa.myapplication.modelos;

/**
 * Created by ro_fa on 2/7/2018.
 */

public class Notasus {

    private NotausPK notausPK;
    private String nota;

    public Notasus() {
    }

    public Notasus(NotausPK notausPK, String nota) {
        this.notausPK = notausPK;
        this.nota = nota;
    }

    public NotausPK getNotausPK() {
        return notausPK;
    }

    public void setNotausPK(NotausPK notausPK) {
        this.notausPK = notausPK;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Notasus{" +
                "notausPK=" + notausPK +
                ", nota='" + nota + '\'' +
                '}';
    }
}
