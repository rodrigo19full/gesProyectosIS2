/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ro_fa
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p")
    , @NamedQuery(name = "Proyectos.findByIdproyecto", query = "SELECT p FROM Proyectos p WHERE p.idproyecto = :idproyecto")
    , @NamedQuery(name = "Proyectos.findByFechainicio", query = "SELECT p FROM Proyectos p WHERE p.fechainicio = :fechainicio")
    , @NamedQuery(name = "Proyectos.findByAnho", query = "SELECT p FROM Proyectos p WHERE p.anho = :anho")
    , @NamedQuery(name = "Proyectos.findByDescripcion", query = "SELECT p FROM Proyectos p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Proyectos.findByFechafin", query = "SELECT p FROM Proyectos p WHERE p.fechafin = :fechafin")
    , @NamedQuery(name = "Proyectos.findByCodproyecto", query = "SELECT p FROM Proyectos p WHERE p.codproyecto = :codproyecto")
    , @NamedQuery(name = "Proyectos.findByNombre", query = "SELECT p FROM Proyectos p WHERE p.nombre = :nombre")})
public class Proyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproyecto")
    private Integer idproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anho")
    private int anho;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codproyecto")
    private int codproyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyectos")
    private Collection<Usuariosproyectos> usuariosproyectosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproyecto")
    private Collection<Backlog> backlogCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyectos")
    private Collection<Sprints> sprintsCollection;

    public Proyectos() {
    }

    public Proyectos(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Proyectos(Integer idproyecto, Date fechainicio, int anho, String descripcion, Date fechafin, int codproyecto, String nombre) {
        this.idproyecto = idproyecto;
        this.fechainicio = fechainicio;
        this.anho = anho;
        this.descripcion = descripcion;
        this.fechafin = fechafin;
        this.codproyecto = codproyecto;
        this.nombre = nombre;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getCodproyecto() {
        return codproyecto;
    }

    public void setCodproyecto(int codproyecto) {
        this.codproyecto = codproyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Usuariosproyectos> getUsuariosproyectosCollection() {
        return usuariosproyectosCollection;
    }

    public void setUsuariosproyectosCollection(Collection<Usuariosproyectos> usuariosproyectosCollection) {
        this.usuariosproyectosCollection = usuariosproyectosCollection;
    }

    @XmlTransient
    public Collection<Backlog> getBacklogCollection() {
        return backlogCollection;
    }

    public void setBacklogCollection(Collection<Backlog> backlogCollection) {
        this.backlogCollection = backlogCollection;
    }

    @XmlTransient
    public Collection<Sprints> getSprintsCollection() {
        return sprintsCollection;
    }

    public void setSprintsCollection(Collection<Sprints> sprintsCollection) {
        this.sprintsCollection = sprintsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproyecto != null ? idproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.idproyecto == null && other.idproyecto != null) || (this.idproyecto != null && !this.idproyecto.equals(other.idproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Proyectos[ idproyecto=" + idproyecto + " ]";
    }
    
}
