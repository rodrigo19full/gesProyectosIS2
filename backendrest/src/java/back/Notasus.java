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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ro_fa
 */
@Entity
@Table(name = "notasus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notasus.findAll", query = "SELECT n FROM Notasus n")
    , @NamedQuery(name = "Notasus.findByIdnota", query = "SELECT n FROM Notasus n WHERE n.notasusPK.idnota = :idnota")
    , @NamedQuery(name = "Notasus.findByIdproyecto", query = "SELECT n FROM Notasus n WHERE n.notasusPK.idproyecto = :idproyecto")
    , @NamedQuery(name = "Notasus.findByIdus", query = "SELECT n FROM Notasus n WHERE n.notasusPK.idus = :idus")
    , @NamedQuery(name = "Notasus.findByIdsprint", query = "SELECT n FROM Notasus n WHERE n.notasusPK.idsprint = :idsprint")
    , @NamedQuery(name = "Notasus.findByNota", query = "SELECT n FROM Notasus n WHERE n.nota = :nota")})
public class Notasus implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotasusPK notasusPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nota")
    private String nota;
    @JoinColumns({
        @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto", insertable = false, updatable = false)
        , @JoinColumn(name = "idsprint", referencedColumnName = "idsprint", insertable = false, updatable = false)
        , @JoinColumn(name = "idus", referencedColumnName = "idus", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Us us;

    public Notasus() {
    }

    public Notasus(NotasusPK notasusPK) {
        this.notasusPK = notasusPK;
    }

    public Notasus(NotasusPK notasusPK, String nota) {
        this.notasusPK = notasusPK;
        this.nota = nota;
    }

    public Notasus(int idnota, int idproyecto, int idus, int idsprint) {
        this.notasusPK = new NotasusPK(idnota, idproyecto, idus, idsprint);
    }

    public NotasusPK getNotasusPK() {
        return notasusPK;
    }

    public void setNotasusPK(NotasusPK notasusPK) {
        this.notasusPK = notasusPK;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Us getUs() {
        return us;
    }

    public void setUs(Us us) {
        this.us = us;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notasusPK != null ? notasusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notasus)) {
            return false;
        }
        Notasus other = (Notasus) object;
        if ((this.notasusPK == null && other.notasusPK != null) || (this.notasusPK != null && !this.notasusPK.equals(other.notasusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Notasus[ notasusPK=" + notasusPK + " ]";
    }
    
}
