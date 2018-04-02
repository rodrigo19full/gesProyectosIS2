package com.example.ro_fa.gesproyectos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Usuarios {

    @SerializedName("nombreusuario")
    @Expose
    private String nombreusuario;

    @SerializedName("estado")
    @Expose
    private int estado;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("fechacreacion")
    @Expose
    private Date fechacreacion;

    @SerializedName("idUsuario")
    @Expose
    private int idUsuario;

    @SerializedName("usernombre")
    @Expose
    private String usernombre;

    public String getNombreUsuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaCreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUserNombre() {
        return usernombre;
    }

    public void setUsernombre(String usernombre) {
        this.usernombre = usernombre;
    }
}