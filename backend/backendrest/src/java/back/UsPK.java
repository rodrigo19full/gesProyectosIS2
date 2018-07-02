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
public class UsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idus")
    private int idus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproyecto")
    private int idproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsprint")
    private int idsprint;

    public UsPK() {
    }

    public UsPK(int idus, int idproyecto, int idsprint) {
        this.idus = idus;
        this.idproyecto = idproyecto;
        this.idsprint = idsprint;
    }

    public int getIdus() {
        return idus;
    }

    public void setIdus(int idus) {
        this.idus = idus;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public int getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(int idsprint) {
        this.idsprint = idsprint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idus;
        hash += (int) idproyecto;
        hash += (int) idsprint;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsPK)) {
            return false;
        }
        UsPK other = (UsPK) object;
        if (this.idus != other.idus) {
            return false;
        }
        if (this.idproyecto != other.idproyecto) {
            return false;
        }
        if (this.idsprint != other.idsprint) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.UsPK[ idus=" + idus + ", idproyecto=" + idproyecto + ", idsprint=" + idsprint + " ]";
    }
    
}
