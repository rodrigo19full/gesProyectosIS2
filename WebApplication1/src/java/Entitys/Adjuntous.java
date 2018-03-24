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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lramirez
 */
@Entity
@Table(name = "adjuntous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adjuntous.findAll", query = "SELECT a FROM Adjuntous a")
    , @NamedQuery(name = "Adjuntous.findByIdadjunto", query = "SELECT a FROM Adjuntous a WHERE a.idadjunto = :idadjunto")})
public class Adjuntous implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadjunto")
    private Integer idadjunto;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "adjunto")
    private byte[] adjunto;
    @JoinColumn(name = "idus", referencedColumnName = "idus")
    @ManyToOne(optional = false)
    private Us idus;

    public Adjuntous() {
    }

    public Adjuntous(Integer idadjunto) {
        this.idadjunto = idadjunto;
    }

    public Adjuntous(Integer idadjunto, byte[] adjunto) {
        this.idadjunto = idadjunto;
        this.adjunto = adjunto;
    }

    public Integer getIdadjunto() {
        return idadjunto;
    }

    public void setIdadjunto(Integer idadjunto) {
        this.idadjunto = idadjunto;
    }

    public byte[] getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(byte[] adjunto) {
        this.adjunto = adjunto;
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
        hash += (idadjunto != null ? idadjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adjuntous)) {
            return false;
        }
        Adjuntous other = (Adjuntous) object;
        if ((this.idadjunto == null && other.idadjunto != null) || (this.idadjunto != null && !this.idadjunto.equals(other.idadjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Adjuntous[ idadjunto=" + idadjunto + " ]";
    }
    
}
