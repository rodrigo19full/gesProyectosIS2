/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lramirez
 */
@Entity
@Table(name = "backlog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Backlog.findAll", query = "SELECT b FROM Backlog b")
    , @NamedQuery(name = "Backlog.findByIdbacklog", query = "SELECT b FROM Backlog b WHERE b.idbacklog = :idbacklog")})
public class Backlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbacklog")
    private Integer idbacklog;
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyectos idproyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbacklog")
    private Collection<Tareasbacklog> tareasbacklogCollection;

    public Backlog() {
    }

    public Backlog(Integer idbacklog) {
        this.idbacklog = idbacklog;
    }

    public Integer getIdbacklog() {
        return idbacklog;
    }

    public void setIdbacklog(Integer idbacklog) {
        this.idbacklog = idbacklog;
    }

    public Proyectos getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Proyectos idproyecto) {
        this.idproyecto = idproyecto;
    }

    @XmlTransient
    public Collection<Tareasbacklog> getTareasbacklogCollection() {
        return tareasbacklogCollection;
    }

    public void setTareasbacklogCollection(Collection<Tareasbacklog> tareasbacklogCollection) {
        this.tareasbacklogCollection = tareasbacklogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbacklog != null ? idbacklog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Backlog)) {
            return false;
        }
        Backlog other = (Backlog) object;
        if ((this.idbacklog == null && other.idbacklog != null) || (this.idbacklog != null && !this.idbacklog.equals(other.idbacklog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Backlog[ idbacklog=" + idbacklog + " ]";
    }
    
}
