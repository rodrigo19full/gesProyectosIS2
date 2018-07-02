/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ro_fa
 */
@Entity
@Table(name = "permisosporroles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisosporroles.findAll", query = "SELECT p FROM Permisosporroles p")
    , @NamedQuery(name = "Permisosporroles.findByIdrol", query = "SELECT p FROM Permisosporroles p WHERE p.permisosporrolesPK.idrol = :idrol")
    , @NamedQuery(name = "Permisosporroles.findByIdpermiso", query = "SELECT p FROM Permisosporroles p WHERE p.permisosporrolesPK.idpermiso = :idpermiso")
    , @NamedQuery(name = "Permisosporroles.findByEstado", query = "SELECT p FROM Permisosporroles p WHERE p.estado = :estado")})
public class Permisosporroles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PermisosporrolesPK permisosporrolesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idpermiso", referencedColumnName = "idpermiso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Permisos permisos;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;

    public Permisosporroles() {
    }

    public Permisosporroles(PermisosporrolesPK permisosporrolesPK) {
        this.permisosporrolesPK = permisosporrolesPK;
    }

    public Permisosporroles(PermisosporrolesPK permisosporrolesPK, int estado) {
        this.permisosporrolesPK = permisosporrolesPK;
        this.estado = estado;
    }

    public Permisosporroles(int idrol, int idpermiso) {
        this.permisosporrolesPK = new PermisosporrolesPK(idrol, idpermiso);
    }

    public PermisosporrolesPK getPermisosporrolesPK() {
        return permisosporrolesPK;
    }

    public void setPermisosporrolesPK(PermisosporrolesPK permisosporrolesPK) {
        this.permisosporrolesPK = permisosporrolesPK;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Permisos getPermisos() {
        return permisos;
    }

    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permisosporrolesPK != null ? permisosporrolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisosporroles)) {
            return false;
        }
        Permisosporroles other = (Permisosporroles) object;
        if ((this.permisosporrolesPK == null && other.permisosporrolesPK != null) || (this.permisosporrolesPK != null && !this.permisosporrolesPK.equals(other.permisosporrolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Permisosporroles[ permisosporrolesPK=" + permisosporrolesPK + " ]";
    }
    
}
