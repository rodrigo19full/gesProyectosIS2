/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

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
 * @author ro_fa
 */
@Entity
@Table(name = "tareasbacklog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tareasbacklog.findAll", query = "SELECT t FROM Tareasbacklog t")
    , @NamedQuery(name = "Tareasbacklog.findByIdtarea", query = "SELECT t FROM Tareasbacklog t WHERE t.idtarea = :idtarea")
    , @NamedQuery(name = "Tareasbacklog.findByNombre", query = "SELECT t FROM Tareasbacklog t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tareasbacklog.findByEstado", query = "SELECT t FROM Tareasbacklog t WHERE t.estado = :estado")
    , @NamedQuery(name = "Tareasbacklog.findByDescripcion", query = "SELECT t FROM Tareasbacklog t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tareasbacklog.findByValornegocio", query = "SELECT t FROM Tareasbacklog t WHERE t.valornegocio = :valornegocio")})
public class Tareasbacklog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtarea")
    private Integer idtarea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valornegocio")
    private int valornegocio;
    @JoinColumn(name = "idbacklog", referencedColumnName = "idbacklog")
    @ManyToOne(optional = false)
    private Backlog idbacklog;

    public Tareasbacklog() {
    }

    public Tareasbacklog(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public Tareasbacklog(Integer idtarea, String nombre, int estado, String descripcion, int valornegocio) {
        this.idtarea = idtarea;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.valornegocio = valornegocio;
    }

    public Integer getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValornegocio() {
        return valornegocio;
    }

    public void setValornegocio(int valornegocio) {
        this.valornegocio = valornegocio;
    }

    public Backlog getIdbacklog() {
        return idbacklog;
    }

    public void setIdbacklog(Backlog idbacklog) {
        this.idbacklog = idbacklog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarea != null ? idtarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareasbacklog)) {
            return false;
        }
        Tareasbacklog other = (Tareasbacklog) object;
        if ((this.idtarea == null && other.idtarea != null) || (this.idtarea != null && !this.idtarea.equals(other.idtarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "back.Tareasbacklog[ idtarea=" + idtarea + " ]";
    }
    
}
