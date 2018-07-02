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
public class NotasusPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idnota")
    private int idnota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproyecto")
    private int idproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idus")
    private int idus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsprint")
    private int idsprint;

    public NotasusPK() {
    }

    public NotasusPK(int idnota, int idproyecto, int idus, int idsprint) {
        this.idnota = idnota;
        this.idproyecto = idproyecto;
        this.idus = idus;
        this.idsprint = idsprint;
    }

    public int getIdnota() {
        return idnota;
    }

    public void setIdnota(int idnota) {
        this.idnota = idnota;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public int getIdus() {
        return idus;
    }

    public void setIdus(int idus) {
        this.idus = idus;
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
        hash += (int) idnota;
        hash += (int) idproyecto;
        hash += (int) idus;
        hash += (int) idsprint;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotasusPK)) {
            return false;
        }
        NotasusPK other = (NotasusPK) object;
        if (this.idnota != other.idnota) {
            return false;
        }
        if (this.idproyecto != other.idproyecto) {
            return false;
        }
        if (this.idus != other.idus) {
            return false;
        }
        if (this.idsprint != other.idsprint) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.NotasusPK[ idnota=" + idnota + ", idproyecto=" + idproyecto + ", idus=" + idus + ", idsprint=" + idsprint + " ]";
    }
    
}
