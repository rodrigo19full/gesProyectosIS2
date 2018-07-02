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
import javax.persistence.JoinColumns;
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
@Table(name = "us")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Us.findAll", query = "SELECT u FROM Us u")
    , @NamedQuery(name = "Us.findByIdus", query = "SELECT u FROM Us u WHERE u.usPK.idus = :idus")
    , @NamedQuery(name = "Us.findByIdproyecto", query = "SELECT u FROM Us u WHERE u.usPK.idproyecto = :idproyecto")
    , @NamedQuery(name = "Us.findByIdsprint", query = "SELECT u FROM Us u WHERE u.usPK.idsprint = :idsprint")
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
    @EmbeddedId
    protected UsPK usPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "us")
    private Collection<Notasus> notasusCollection;
    @JoinColumns({
        @JoinColumn(name = "idsprint", referencedColumnName = "idsprint", insertable = false, updatable = false)
        , @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Sprints sprints;

    public Us() {
    }

    public Us(UsPK usPK) {
        this.usPK = usPK;
    }

    public Us(UsPK usPK, int tiempoejecucion, String descripcion, String nombre, int horastrabajadas, int estado, int numerous, int idusuario, int valortecnico, int valornegocio) {
        this.usPK = usPK;
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

    public Us(int idus, int idproyecto, int idsprint) {
        this.usPK = new UsPK(idus, idproyecto, idsprint);
    }

    public UsPK getUsPK() {
        return usPK;
    }

    public void setUsPK(UsPK usPK) {
        this.usPK = usPK;
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
    public Collection<Notasus> getNotasusCollection() {
        return notasusCollection;
    }

    public void setNotasusCollection(Collection<Notasus> notasusCollection) {
        this.notasusCollection = notasusCollection;
    }

    public Sprints getSprints() {
        return sprints;
    }

    public void setSprints(Sprints sprints) {
        this.sprints = sprints;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usPK != null ? usPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Us)) {
            return false;
        }
        Us other = (Us) object;
        if ((this.usPK == null && other.usPK != null) || (this.usPK != null && !this.usPK.equals(other.usPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Us[ usPK=" + usPK + " ]";
    }
    
}
