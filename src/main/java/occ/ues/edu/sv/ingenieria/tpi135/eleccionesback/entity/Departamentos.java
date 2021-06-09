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
@Table(name = "departamentos", schema = "eleccionesDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d")})
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDepartamento", nullable = false)
    private Integer idDepartamento;
    @Basic(optional = false)
    @Column(name = "departamento", nullable = false, length = 60)
    private String departamento;
    @Basic(optional = false)
    @Column(name = "cantidadDiputados", nullable = false)
    private int cantidadDiputados;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamento")
    private List<Municipios> municipiosList;

    public Departamentos() {
    }

    public Departamentos(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Departamentos(Integer idDepartamento, String departamento, int cantidadDiputados) {
        this.idDepartamento = idDepartamento;
        this.departamento = departamento;
        this.cantidadDiputados = cantidadDiputados;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getCantidadDiputados() {
        return cantidadDiputados;
    }

    public void setCantidadDiputados(int cantidadDiputados) {
        this.cantidadDiputados = cantidadDiputados;
    }

    @XmlTransient
    public List<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    public void setMunicipiosList(List<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Departamentos[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
