/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ro_fa
 */
@Embeddable
public class UsuariosproyectosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idproyecto")
    private int idproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuario")
    private int idusuario;

    public UsuariosproyectosPK() {
    }

    public UsuariosproyectosPK(int idproyecto, int idusuario) {
        this.idproyecto = idproyecto;
        this.idusuario = idusuario;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idproyecto;
        hash += (int) idusuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosproyectosPK)) {
            return false;
        }
        UsuariosproyectosPK other = (UsuariosproyectosPK) object;
        if (this.idproyecto != other.idproyecto) {
            return false;
        }
        if (this.idusuario != other.idusuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.UsuariosproyectosPK[ idproyecto=" + idproyecto + ", idusuario=" + idusuario + " ]";
    }
    
}
