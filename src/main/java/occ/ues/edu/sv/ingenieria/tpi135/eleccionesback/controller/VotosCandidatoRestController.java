package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.VotosCandidato;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioCargos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioPartidos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioUsuarios;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioVotosCandidato;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/votosCandidatos")
public class VotosCandidatoRestController {

    @Autowired
    RepositorioVotosCandidato votosCandidatoRepository;
    @Autowired
    RepositorioUsuarios repoUsuarios;
    @Autowired
    RepositorioPartidos repoPartidos;
    @Autowired
    RepositorioCargos repoCargo;

    private final static Logger logger = LoggerFactory.getLogger(VotosCandidatoRestController.class);

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<VotosCandidato>> findAll() {

        try {
            List<VotosCandidato> registros = new ArrayList<>();

            registros = votosCandidatoRepository.findAll();

            if (registros != null && !registros.isEmpty()) {
                return ResponseEntity.ok(registros);
            }
        } catch (Exception e) {
            logger.error("Error en findAll", e);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<VotosCandidato> getById(@PathVariable Integer id){

        VotosCandidato vCandidato=new VotosCandidato();
        boolean existe = false;

        try {
            
            if (id != null) {
                existe=votosCandidatoRepository.existsById(id);
                if (existe) {
                    vCandidato = votosCandidatoRepository.findById(id).get();

                    return ResponseEntity.ok(vCandidato);
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

        } catch (Exception e) {
            logger.error("Error finById VotosCandidato", e);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping(path = "/crearVotoscandidatos")
    public ResponseEntity<Object> crearEntity(@RequestBody VotosCandidato votosCandidato) {
        boolean existe;

        List<VotosCandidato> listaCandidatos = new ArrayList<>();
        try {

            existe = votosCandidatoRepository.existsByidUsuario(votosCandidato.getIdUsuario());
            //partidos, usuarios y cargos
            votosCandidato.setIdCargo(repoCargo.getById(votosCandidato.getIdCargo().getIdCargo()));
            votosCandidato.setIdPartido(repoPartidos.getById(votosCandidato.getIdPartido().getIdPartido()));
            votosCandidato.setIdUsuario(repoUsuarios.getById(votosCandidato.getIdUsuario().getIdUsuario()));
            if (votosCandidato != null && !existe && !votosCandidatoRepository.existsByidUsuario(votosCandidato.getIdUsuario())) {
                listaCandidatos = votosCandidatoRepository.cargoOcupado(votosCandidato.getIdCargo().getCargo(), votosCandidato.getIdPartido().getNombre());//obtenemos los candidatos de un determinado partido
                long cantidad = listaCandidatos.stream().filter(d -> d.getIdUsuario().getIdMunicipio().getIdDepartamento() == votosCandidato.getIdUsuario().getIdMunicipio().getIdDepartamento()).count();//filtramos para tener un conteo de los diputados que estan postulados hasta el momento para un departamento de un partido
                for (VotosCandidato votos : listaCandidatos) {
                    
                    //verificamos si el candidato va para alcalde, que no haya alguien mas con el mismo cargo de su partido en el mismo municipio
                    if (votos.getIdUsuario().getIdMunicipio() == votosCandidato.getIdUsuario().getIdMunicipio() && votosCandidato.getIdCargo().getCargo().equals("alcalde")) {
                        return new ResponseEntity<>("Ya se encuentra un candidato postulado del mismo partido", HttpStatus.NOT_ACCEPTABLE);
                        
                        //verificamos si ya estan completos los candidatos del mismo partido para determinado departamento
                    } else if (votos.getIdUsuario().getIdMunicipio().getIdDepartamento() == votosCandidato.getIdUsuario().getIdMunicipio().getIdDepartamento() && votosCandidato.getIdCargo().getCargo().equals("diputado") && ((cantidad + 1) > votosCandidato.getIdUsuario().getIdMunicipio().getIdDepartamento().getCantidadDiputados())) {
                        return new ResponseEntity<>("se alcanzo el limite de postulaciones para el partido", HttpStatus.NOT_ACCEPTABLE);
                        
                        //verificamos si ya hay un candidato para presidente del mismo partido
                    } else if (votos.getIdCargo().getCargo().equals("presidente")) {
                        return new ResponseEntity<>("Ya se encuentra un candidato postulado para presidente del mismo partido", HttpStatus.NOT_ACCEPTABLE);
                    }
                }

            } else {
                return new ResponseEntity<>("El usuario no existe o el candidato ya se postuló anteriormente", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error creando VotosCandidato", e);
        }
        votosCandidatoRepository.save(votosCandidato);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping(path = "/actualizarvotos")
    public ResponseEntity<VotosCandidato> actualizarVotos(@RequestBody VotosCandidato votosCandidato) {

        try {
            if (votosCandidato != null) {

                boolean existe = votosCandidatoRepository.existsByidUsuario(votosCandidato.getIdUsuario());

                if (existe) {
                    VotosCandidato votosExistente = votosCandidatoRepository.findByIdUsuario(votosCandidato.getIdUsuario()).get();
                    Integer votosActualizados = votosExistente.getVotos() + votosCandidato.getVotos();
                    votosExistente.setVotos(votosActualizados);
                    votosCandidatoRepository.save(votosExistente);
                    return ResponseEntity.ok(votosExistente);
                }
            }
        } catch (Exception e) {
            logger.error("Error actualizando VotosCandidato", e);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
