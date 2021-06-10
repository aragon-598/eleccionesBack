package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.dto;

import java.io.Serializable;

public class UsuariosDTO implements Serializable{
    
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private int dui;
    private Character sexo;
    private boolean estadoVoto;
    private Integer idMunicipio;
    private int idRol;


    public UsuariosDTO() {
    }


    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDui() {
        return this.dui;
    }

    public void setDui(int dui) {
        this.dui = dui;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public boolean isEstadoVoto() {
        return this.estadoVoto;
    }

    public boolean getEstadoVoto() {
        return this.estadoVoto;
    }

    public void setEstadoVoto(boolean estadoVoto) {
        this.estadoVoto = estadoVoto;
    }

    public Integer getIdMunicipio() {
        return this.idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdRol() {
        return this.idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }


}
