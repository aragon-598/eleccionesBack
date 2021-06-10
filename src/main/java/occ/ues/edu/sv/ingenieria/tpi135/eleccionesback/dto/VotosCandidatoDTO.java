package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.dto;

import java.io.Serializable;

public class VotosCandidatoDTO implements Serializable {
    
    private Integer idUsuario;
    private Integer votos;
    private Integer idCargo;
    private Integer idPartido;


    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getVotos() {
        return this.votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Integer getIdCargo() {
        return this.idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Integer getIdPartido() {
        return this.idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }



}
