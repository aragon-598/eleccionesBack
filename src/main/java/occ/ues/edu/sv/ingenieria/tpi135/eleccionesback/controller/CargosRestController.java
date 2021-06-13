package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.controller;

import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Cargos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioCargos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cargos")
@CrossOrigin
public class CargosRestController {

    private final static Logger logger = LoggerFactory.getLogger(CargosRestController.class);

    @Autowired
    RepositorioCargos cargosRepository;

    @GetMapping("/findAll")
    public ResponseEntity<List<Cargos>> findAll(){

        List<Cargos> registros = new ArrayList<>();

        try {
            registros = cargosRepository.findAll();

            if (registros != null && !registros.isEmpty()){
                return ResponseEntity.ok(registros);
            }
        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/findById/{idCargo}")
    public ResponseEntity<Cargos> findById(@PathVariable Integer idCargo){
        boolean existe;
        Cargos cargo;
        try {

            if (idCargo != null){
                existe = cargosRepository.existsByIdCargo(idCargo);
                if (existe){
                    cargo = cargosRepository.findById(idCargo).get();
                    return ResponseEntity.ok(cargo);
                }else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

        }catch (Exception e){
            logger.error("Error findByid Cargos ",e);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
