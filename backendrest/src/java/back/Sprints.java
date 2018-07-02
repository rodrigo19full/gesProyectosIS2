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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sprints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprints.findAll", query = "SELECT s FROM Sprints s")
    , @NamedQuery(name = "Sprints.findByIdsprint", query = "SELECT s FROM Sprints s WHERE s.sprintsPK.idsprint = :idsprint")
    , @NamedQuery(name = "Sprints.findByIdproyecto", query = "SELECT s FROM Sprints s WHERE s.sprintsPK.idproyecto = :idproyecto")
    , @NamedQuery(name = "Sprints.findByDuracion", query = "SELECT s FROM Sprints s WHERE s.duracion = :duracion")
    , @NamedQuery(name = "Sprints.findByEstado", query = "SELECT s FROM Sprints s WHERE s.estado = :estado")
    , @NamedQuery(name = "Sprints.findByNombre", query = "SELECT s FROM Sprints s WHERE s.nombre = :nombre")})
public class Sprints implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SprintsPK sprintsPK;
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
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyectos proyectos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprints")
    private Collection<Us> usCollection;

    public Sprints() {
    }

    public Sprints(SprintsPK sprintsPK) {
        this.sprintsPK = sprintsPK;
    }

    public Sprints(SprintsPK sprintsPK, int duracion, int estado, String nombre) {
        this.sprintsPK = sprintsPK;
        this.duracion = duracion;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Sprints(int idsprint, int idproyecto) {
        this.sprintsPK = new SprintsPK(idsprint, idproyecto);
    }

    public SprintsPK getSprintsPK() {
        return sprintsPK;
    }

    public void setSprintsPK(SprintsPK sprintsPK) {
        this.sprintsPK = sprintsPK;
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

    public Proyectos getProyectos() {
        return proyectos;
    }

    public void setProyectos(Proyectos proyectos) {
        this.proyectos = proyectos;
    }

    @XmlTransient
    public Collection<Us> getUsCollection() {
        return usCollection;
    }

    public void setUsCollection(Collection<Us> usCollection) {
        this.usCollection = usCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sprintsPK != null ? sprintsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprints)) {
            return false;
        }
        Sprints other = (Sprints) object;
        if ((this.sprintsPK == null && other.sprintsPK != null) || (this.sprintsPK != null && !this.sprintsPK.equals(other.sprintsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Sprints[ sprintsPK=" + sprintsPK + " ]";
    }
    
}
