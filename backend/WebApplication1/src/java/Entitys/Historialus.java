/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lramirez
 */
@Entity
@Table(name = "historialus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialus.findAll", query = "SELECT h FROM Historialus h")
    , @NamedQuery(name = "Historialus.findByIdhistorial", query = "SELECT h FROM Historialus h WHERE h.idhistorial = :idhistorial")
    , @NamedQuery(name = "Historialus.findByComentario", query = "SELECT h FROM Historialus h WHERE h.comentario = :comentario")
    , @NamedQuery(name = "Historialus.findByFecha", query = "SELECT h FROM Historialus h WHERE h.fecha = :fecha")
    , @NamedQuery(name = "Historialus.findByIdusuario", query = "SELECT h FROM Historialus h WHERE h.idusuario = :idusuario")})
public class Historialus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistorial")
    private Integer idhistorial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuario")
    private int idusuario;
    @JoinColumn(name = "idus", referencedColumnName = "idus")
    @ManyToOne(optional = false)
    private Us idus;

    public Historialus() {
    }

    public Historialus(Integer idhistorial) {
        this.idhistorial = idhistorial;
    }

    public Historialus(Integer idhistorial, String comentario, Date fecha, int idusuario) {
        this.idhistorial = idhistorial;
        this.comentario = comentario;
        this.fecha = fecha;
        this.idusuario = idusuario;
    }

    public Integer getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Integer idhistorial) {
        this.idhistorial = idhistorial;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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
        hash += (idhistorial != null ? idhistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialus)) {
            return false;
        }
        Historialus other = (Historialus) object;
        if ((this.idhistorial == null && other.idhistorial != null) || (this.idhistorial != null && !this.idhistorial.equals(other.idhistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Historialus[ idhistorial=" + idhistorial + " ]";
    }
    
}
