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
public class SprintsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idsprint")
    private int idsprint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproyecto")
    private int idproyecto;

    public SprintsPK() {
    }

    public SprintsPK(int idsprint, int idproyecto) {
        this.idsprint = idsprint;
        this.idproyecto = idproyecto;
    }

    public int getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(int idsprint) {
        this.idsprint = idsprint;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idsprint;
        hash += (int) idproyecto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SprintsPK)) {
            return false;
        }
        SprintsPK other = (SprintsPK) object;
        if (this.idsprint != other.idsprint) {
            return false;
        }
        if (this.idproyecto != other.idproyecto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.SprintsPK[ idsprint=" + idsprint + ", idproyecto=" + idproyecto + " ]";
    }
    
}
