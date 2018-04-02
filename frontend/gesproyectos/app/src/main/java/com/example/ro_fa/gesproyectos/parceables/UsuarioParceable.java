package com.example.ro_fa.gesproyectos.parceables;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ro_fa.gesproyectos.models.Usuarios;

import java.util.Date;

public class UsuarioParceable implements Parcelable {

    private String nombreusuario;

    private int estado;

    private String email;

    private Date fechacreacion;

    private int idUsuario;

    private String usernombre;

    public UsuarioParceable() {
    }

    public UsuarioParceable(Usuarios usuario) {
        this.nombreusuario = usuario.getNombreUsuario();
        this.estado = usuario.getEstado();
        this.email = usuario.getEmail();
        this.idUsuario = usuario.getIdUsuario();
        this.fechacreacion = usuario.getFechaCreacion();
        this.usernombre= usuario.getUserNombre();
    }

    @Override
    public String toString() {
        return "UsuarioParceable{" +
                "nombreUsuario='" + nombreusuario + '\'' +
                ", estado=" + estado +
                ", email='" + email + '\'' +
                ", fechacreacion=" + fechacreacion +
                ", idUsuario=" + idUsuario +
                ", usernombre='" + usernombre + '\'' +
                '}';
    }

    protected UsuarioParceable(Parcel in) {
        nombreusuario = in.readString();
        estado = in.readInt();
        email = in.readString();
        long tmpFechaCreacion = in.readLong();
        fechacreacion = tmpFechaCreacion!= -1 ? new Date(tmpFechaCreacion) : null;
        idUsuario = in.readInt();
        usernombre = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreusuario);
        dest.writeInt(estado);
        dest.writeString(email);
        dest.writeLong(fechacreacion != null ? fechacreacion.getTime() : -1L);
        dest.writeInt(idUsuario);
        dest.writeString(usernombre);
    }

    @SuppressWarnings("unused")
    public static final Creator<UsuarioParceable> CREATOR = new Creator<UsuarioParceable>() {
        @Override
        public UsuarioParceable createFromParcel(Parcel in) {
            return new UsuarioParceable(in);
        }

        @Override
        public UsuarioParceable[] newArray(int size) {
            return new UsuarioParceable[size];
        }
    };
}
