/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ro_fa
 */
@Entity
@Table(name = "usuariosproyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariosproyectos.findAll", query = "SELECT u FROM Usuariosproyectos u")
    , @NamedQuery(name = "Usuariosproyectos.findByIdproyecto", query = "SELECT u.usuarios FROM Usuariosproyectos u WHERE u.usuariosproyectosPK.idproyecto = :idproyecto")
    , @NamedQuery(name = "Usuariosproyectos.findByIdusuario", query = "SELECT u.proyectos FROM Usuariosproyectos u WHERE u.usuariosproyectosPK.idusuario = :idusuario")})
public class Usuariosproyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariosproyectosPK usuariosproyectosPK;
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyectos proyectos;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol")
    @ManyToOne(optional = false)
    private Roles idrol;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuarios usuarios;

    public Usuariosproyectos() {
    }

    public Usuariosproyectos(UsuariosproyectosPK usuariosproyectosPK) {
        this.usuariosproyectosPK = usuariosproyectosPK;
    }

    public Usuariosproyectos(UsuariosproyectosPK usuariosproyectosPK, Roles idrol) {
        this.usuariosproyectosPK = usuariosproyectosPK;
        this.idrol = idrol;
    }
    

    public Usuariosproyectos(int idproyecto, int idusuario) {
        this.usuariosproyectosPK = new UsuariosproyectosPK(idproyecto, idusuario);
    }

    public UsuariosproyectosPK getUsuariosproyectosPK() {
        return usuariosproyectosPK;
    }

    public void setUsuariosproyectosPK(UsuariosproyectosPK usuariosproyectosPK) {
        this.usuariosproyectosPK = usuariosproyectosPK;
    }

    public Proyectos getProyectos() {
        return proyectos;
    }

    public void setProyectos(Proyectos proyectos) {
        this.proyectos = proyectos;
    }

    public Roles getIdrol() {
        return idrol;
    }

    public void setIdrol(Roles idrol) {
        this.idrol = idrol;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariosproyectosPK != null ? usuariosproyectosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariosproyectos)) {
            return false;
        }
        Usuariosproyectos other = (Usuariosproyectos) object;
        if ((this.usuariosproyectosPK == null && other.usuariosproyectosPK != null) || (this.usuariosproyectosPK != null && !this.usuariosproyectosPK.equals(other.usuariosproyectosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Usuariosproyectos[ usuariosproyectosPK=" + usuariosproyectosPK + " ]";
    }
    
}
