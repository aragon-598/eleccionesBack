/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author christian
 */
@Entity
@Table(name = "votos_candidato", schema = "eleccionesDB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VotosCandidato.findAll", query = "SELECT v FROM VotosCandidato v")})
public class VotosCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_voto_candidato", nullable = false)
    private Integer idVotoCandidato;
    @Column(name = "votos")
    private Integer votos;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo", nullable = false)
    @ManyToOne(optional = false)
    private Cargos idCargo;
    @JoinColumn(name = "id_partido", referencedColumnName = "id_partido", nullable = false)
    @ManyToOne(optional = false)
    private Partidos idPartido;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public VotosCandidato() {
    }

    public VotosCandidato(Integer idVotoCandidato) {
        this.idVotoCandidato = idVotoCandidato;
    }

    public Integer getIdVotoCandidato() {
        return idVotoCandidato;
    }

    public void setIdVotoCandidato(Integer idVotoCandidato) {
        this.idVotoCandidato = idVotoCandidato;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }

    public Partidos getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Partidos idPartido) {
        this.idPartido = idPartido;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVotoCandidato != null ? idVotoCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotosCandidato)) {
            return false;
        }
        VotosCandidato other = (VotosCandidato) object;
        if ((this.idVotoCandidato == null && other.idVotoCandidato != null) || (this.idVotoCandidato != null && !this.idVotoCandidato.equals(other.idVotoCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.VotosCandidato[ idVotoCandidato=" + idVotoCandidato + " ]";
    }
    
}
