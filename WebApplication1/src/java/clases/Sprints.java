/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo Acuña
 */
@Entity
@Table(name = "sprints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprints.findAll", query = "SELECT s FROM Sprints s")
    , @NamedQuery(name = "Sprints.findByIdsprint", query = "SELECT s FROM Sprints s WHERE s.idsprint = :idsprint")
    , @NamedQuery(name = "Sprints.findByDuracion", query = "SELECT s FROM Sprints s WHERE s.duracion = :duracion")
    , @NamedQuery(name = "Sprints.findByEstado", query = "SELECT s FROM Sprints s WHERE s.estado = :estado")
    , @NamedQuery(name = "Sprints.findByNombre", query = "SELECT s FROM Sprints s WHERE s.nombre = :nombre")})
public class Sprints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsprint")
    private Integer idsprint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private int duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "us_sprints", joinColumns = {
        @JoinColumn(name = "idsprint", referencedColumnName = "idsprint")}, inverseJoinColumns = {
        @JoinColumn(name = "idus", referencedColumnName = "idus")})
    @ManyToMany
    private Collection<Us> usCollection;
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyectos idproyecto;

    public Sprints() {
    }

    public Sprints(Integer idsprint) {
        this.idsprint = idsprint;
    }

    public Sprints(Integer idsprint, int duracion, int estado, String nombre) {
        this.idsprint = idsprint;
        this.duracion = duracion;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Integer getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Integer idsprint) {
        this.idsprint = idsprint;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Us> getUsCollection() {
        return usCollection;
    }

    public void setUsCollection(Collection<Us> usCollection) {
        this.usCollection = usCollection;
    }

    public Proyectos getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Proyectos idproyecto) {
        this.idproyecto = idproyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsprint != null ? idsprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprints)) {
            return false;
        }
        Sprints other = (Sprints) object;
        if ((this.idsprint == null && other.idsprint != null) || (this.idsprint != null && !this.idsprint.equals(other.idsprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Sprints[ idsprint=" + idsprint + " ]";
    }
    
}
