/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ro_fa
 */
@Entity
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findByIdpermiso", query = "SELECT p FROM Permisos p WHERE p.idpermiso = :idpermiso")
    , @NamedQuery(name = "Permisos.findByPermiso", query = "SELECT p FROM Permisos p WHERE p.permiso = :permiso")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpermiso")
    private Integer idpermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "permiso")
    private String permiso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permisos")
    private Collection<Permisosporroles> permisosporrolesCollection;

    public Permisos() {
    }

    public Permisos(Integer idpermiso) {
        this.idpermiso = idpermiso;
    }

    public Permisos(Integer idpermiso, String permiso) {
        this.idpermiso = idpermiso;
        this.permiso = permiso;
    }

    public Integer getIdpermiso() {
        return idpermiso;
    }

    public void setIdpermiso(Integer idpermiso) {
        this.idpermiso = idpermiso;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    @XmlTransient
    public Collection<Permisosporroles> getPermisosporrolesCollection() {
        return permisosporrolesCollection;
    }

    public void setPermisosporrolesCollection(Collection<Permisosporroles> permisosporrolesCollection) {
        this.permisosporrolesCollection = permisosporrolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpermiso != null ? idpermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idpermiso == null && other.idpermiso != null) || (this.idpermiso != null && !this.idpermiso.equals(other.idpermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Permisos[ idpermiso=" + idpermiso + " ]";
    }
    
}
