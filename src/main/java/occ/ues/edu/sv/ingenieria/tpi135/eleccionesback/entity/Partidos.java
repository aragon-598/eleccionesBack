/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author christian
 */
@Entity
@Table(name = "partidos", schema = "eleccionesDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partidos.findAll", query = "SELECT p FROM Partidos p")})
public class Partidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPartido", nullable = false)
    private Integer idPartido;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;
    @Column(name = "descripcion", length = 300)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPartido")
    private List<VotosCandidato> votosCandidatoList;

    public Partidos() {
    }

    public Partidos(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public Partidos(Integer idPartido, String nombre) {
        this.idPartido = idPartido;
        this.nombre = nombre;
    }

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<VotosCandidato> getVotosCandidatoList() {
        return votosCandidatoList;
    }

    public void setVotosCandidatoList(List<VotosCandidato> votosCandidatoList) {
        this.votosCandidatoList = votosCandidatoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartido != null ? idPartido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partidos)) {
            return false;
        }
        Partidos other = (Partidos) object;
        if ((this.idPartido == null && other.idPartido != null) || (this.idPartido != null && !this.idPartido.equals(other.idPartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Partidos[ idPartido=" + idPartido + " ]";
    }
    
}
