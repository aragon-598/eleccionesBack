/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository;

import java.util.List;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.VotosCandidato;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author christian
 */
@Repository
public interface RepositorioVotosCandidato extends JpaRepository<VotosCandidato, Integer>{
    public Optional<VotosCandidato> findByIdUsuario(Integer idUsuario);
    public boolean existsByidUsuario(Integer idUsuario);
    
    @Query("select u from VotosCandidato u where u.idCargo.cargo = :cargo")
    public List<VotosCandidato> cargoOcupado(@Param("cargo") String cargo);
}
