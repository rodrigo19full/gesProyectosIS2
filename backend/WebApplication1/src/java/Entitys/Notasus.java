/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lramirez
 */
@Entity
@Table(name = "notasus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notasus.findAll", query = "SELECT n FROM Notasus n")
    , @NamedQuery(name = "Notasus.findByIdnota", query = "SELECT n FROM Notasus n WHERE n.idnota = :idnota")
    , @NamedQuery(name = "Notasus.findByNota", query = "SELECT n FROM Notasus n WHERE n.nota = :nota")})
public class Notasus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnota")
    private Integer idnota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nota")
    private String nota;
    @JoinColumn(name = "idus", referencedColumnName = "idus")
    @ManyToOne(optional = false)
    private Us idus;

    public Notasus() {
    }

    public Notasus(Integer idnota) {
        this.idnota = idnota;
    }

    public Notasus(Integer idnota, String nota) {
        this.idnota = idnota;
        this.nota = nota;
    }

    public Integer getIdnota() {
        return idnota;
    }

    public void setIdnota(Integer idnota) {
        this.idnota = idnota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Us getIdus() {
        return idus;
    }

    public void setIdus(Us idus) {
        this.idus = idus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnota != null ? idnota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notasus)) {
            return false;
        }
        Notasus other = (Notasus) object;
        if ((this.idnota == null && other.idnota != null) || (this.idnota != null && !this.idnota.equals(other.idnota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Notasus[ idnota=" + idnota + " ]";
    }
    
}
