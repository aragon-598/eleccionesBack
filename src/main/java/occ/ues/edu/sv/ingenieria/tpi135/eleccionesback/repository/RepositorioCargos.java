/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository;

import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Cargos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author christian
 */
@Repository
public interface RepositorioCargos extends JpaRepository<Cargos, Integer>{
    
}
