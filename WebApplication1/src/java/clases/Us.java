/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
 * @author Rodrigo Acuña
 */
@Entity
@Table(name = "us")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Us.findAll", query = "SELECT u FROM Us u")
    , @NamedQuery(name = "Us.findByIdus", query = "SELECT u FROM Us u WHERE u.idus = :idus")
    , @NamedQuery(name = "Us.findByTiempoejecucion", query = "SELECT u FROM Us u WHERE u.tiempoejecucion = :tiempoejecucion")
    , @NamedQuery(name = "Us.findByDescripcion", query = "SELECT u FROM Us u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "Us.findByNombre", query = "SELECT u FROM Us u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Us.findByHorastrabajadas", query = "SELECT u FROM Us u WHERE u.horastrabajadas = :horastrabajadas")
    , @NamedQuery(name = "Us.findByEstado", query = "SELECT u FROM Us u WHERE u.estado = :estado")
    , @NamedQuery(name = "Us.findByNumerous", query = "SELECT u FROM Us u WHERE u.numerous = :numerous")
    , @NamedQuery(name = "Us.findByIdusuario", query = "SELECT u FROM Us u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Us.findByValortecnico", query = "SELECT u FROM Us u WHERE u.valortecnico = :valortecnico")
    , @NamedQuery(name = "Us.findByValornegocio", query = "SELECT u FROM Us u WHERE u.valornegocio = :valornegocio")})
public class Us implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idus")
    private Integer idus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempoejecucion")
    private int tiempoejecucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horastrabajadas")
    private int horastrabajadas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numerous")
    private int numerous;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuario")
    private int idusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valortecnico")
    private int valortecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valornegocio")
    private int valornegocio;
    @ManyToMany(mappedBy = "usCollection")
    private Collection<Sprints> sprintsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idus")
    private Collection<Historialus> historialusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idus")
    private Collection<Notasus> notasusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idus")
    private Collection<Adjuntous> adjuntousCollection;

    public Us() {
    }

    public Us(Integer idus) {
        this.idus = idus;
    }

    public Us(Integer idus, int tiempoejecucion, String descripcion, String nombre, int horastrabajadas, int estado, int numerous, int idusuario, int valortecnico, int valornegocio) {
        this.idus = idus;
        this.tiempoejecucion = tiempoejecucion;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.horastrabajadas = horastrabajadas;
        this.estado = estado;
        this.numerous = numerous;
        this.idusuario = idusuario;
        this.valortecnico = valortecnico;
        this.valornegocio = valornegocio;
    }

    public Integer getIdus() {
        return idus;
    }

    public void setIdus(Integer idus) {
        this.idus = idus;
    }

    public int getTiempoejecucion() {
        return tiempoejecucion;
    }

    public void setTiempoejecucion(int tiempoejecucion) {
        this.tiempoejecucion = tiempoejecucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorastrabajadas() {
        return horastrabajadas;
    }

    public void setHorastrabajadas(int horastrabajadas) {
        this.horastrabajadas = horastrabajadas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getNumerous() {
        return numerous;
    }

    public void setNumerous(int numerous) {
        this.numerous = numerous;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getValortecnico() {
        return valortecnico;
    }

    public void setValortecnico(int valortecnico) {
        this.valortecnico = valortecnico;
    }

    public int getValornegocio() {
        return valornegocio;
    }

    public void setValornegocio(int valornegocio) {
        this.valornegocio = valornegocio;
    }

    @XmlTransient
    public Collection<Sprints> getSprintsCollection() {
        return sprintsCollection;
    }

    public void setSprintsCollection(Collection<Sprints> sprintsCollection) {
        this.sprintsCollection = sprintsCollection;
    }

    @XmlTransient
    public Collection<Historialus> getHistorialusCollection() {
        return historialusCollection;
    }

    public void setHistorialusCollection(Collection<Historialus> historialusCollection) {
        this.historialusCollection = historialusCollection;
    }

    @XmlTransient
    public Collection<Notasus> getNotasusCollection() {
        return notasusCollection;
    }

    public void setNotasusCollection(Collection<Notasus> notasusCollection) {
        this.notasusCollection = notasusCollection;
    }

    @XmlTransient
    public Collection<Adjuntous> getAdjuntousCollection() {
        return adjuntousCollection;
    }

    public void setAdjuntousCollection(Collection<Adjuntous> adjuntousCollection) {
        this.adjuntousCollection = adjuntousCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idus != null ? idus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Us)) {
            return false;
        }
        Us other = (Us) object;
        if ((this.idus == null && other.idus != null) || (this.idus != null && !this.idus.equals(other.idus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Us[ idus=" + idus + " ]";
    }
    
}
