/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
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
 * @author christian
 */
@Entity
@Table(name = "usuarios", schema = "eleccionesDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "dui", nullable = false)
    private int dui;
    @Basic(optional = false)
    @Column(name = "sexo", nullable = false)
    private Character sexo;
    @Basic(optional = false)
    @Column(name = "contrasenia", nullable = false, length = 20)
    private String contrasenia;
    @Basic(optional = false)
    @Column(name = "estado_voto", nullable = false)
    private boolean estadoVoto;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio", nullable = false)
    @ManyToOne(optional = false)
    private Municipios idMunicipio;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false)
    @ManyToOne(optional = false)
    private Rol idRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    @JsonIgnore
    private List<VotosCandidato> votosCandidatoList;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String nombres, String apellidos, int dui, Character sexo, String contrasenia, boolean estadoVoto) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dui = dui;
        this.sexo = sexo;
        this.contrasenia = contrasenia;
        this.estadoVoto = estadoVoto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDui() {
        return dui;
    }

    public void setDui(int dui) {
        this.dui = dui;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean getEstadoVoto() {
        return estadoVoto;
    }

    public void setEstadoVoto(boolean estadoVoto) {
        this.estadoVoto = estadoVoto;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
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
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
