package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.controller;

import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Partidos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioPartidos;
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
@RequestMapping("/partidos")
@CrossOrigin
public class PartidoRestController {

    private final static Logger logger = LoggerFactory.getLogger(PartidoRestController.class);

    @Autowired
    RepositorioPartidos partidosRepository;

    @GetMapping("/findAll")
    public ResponseEntity<List<Partidos>> findAll(){
        List<Partidos> registros = new ArrayList<>();

        try {
            registros = partidosRepository.findAll();

            if (registros != null && !registros.isEmpty()){
                return ResponseEntity.ok(registros);
            }
        }catch (Exception e){
            logger.error("Error partidosFindAll",e);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<Partidos> getById(@PathVariable Integer id){

        Partidos partido = new Partidos();
        boolean existe=false;

        try {
            
            if (id != null) {
                existe = partidosRepository.existsById(id);

                if (existe) {
                    partido = partidosRepository.findById(id).get();

                    return ResponseEntity.ok(partido);
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

        } catch (Exception e) {
            logger.error("Error en findById Partidos", e);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
