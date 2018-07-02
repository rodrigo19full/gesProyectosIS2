package com.example.ro_fa.myapplication.modelos;

import java.util.Date;

/**
 * Created by ro_fa on 18/6/2018.
 */

public class Usuarios {
    private Integer idusuario;
    private Integer cedula;
    private String nombre;
    private String apellido;
    private Date fechanacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String usernombre;
    private String userpass;
    private Date fechacreacion;
    private Integer estado;

    public Usuarios() {
    }

    public Usuarios(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuarios(Integer idusuario, Integer cedula, String nombre, String apellido, String direccion, String telefono, String email, String usernombre, String userpass, int estado) {
        this.idusuario = idusuario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.usernombre = usernombre;
        this.userpass = userpass;
        this.estado = estado;
    }

    public Usuarios(Integer cedula, String nombre, String apellido, String direccion, String telefono, String email, String usernombre, String userpass, int estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.usernombre = usernombre;
        this.userpass = userpass;
        this.estado = estado;

    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsernombre() {
        return usernombre;
    }

    public void setUsernombre(String usernombre) {
        this.usernombre = usernombre;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "idusuario=" + idusuario +
                ", cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechanacimiento=" + fechanacimiento +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", usernombre='" + usernombre + '\'' +
                ", userpass='" + userpass + '\'' +
                ", fechacreacion=" + fechacreacion +
                ", estado=" + estado +
                '}';
    }
}
