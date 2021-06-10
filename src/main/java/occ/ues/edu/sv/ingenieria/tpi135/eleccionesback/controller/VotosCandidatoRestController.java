package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.VotosCandidato;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioVotosCandidato;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/votosCandidatos")
public class VotosCandidatoRestController {

    @Autowired
    RepositorioVotosCandidato votosCandidatoRepository;

    private final static Logger logger = LoggerFactory.getLogger(VotosCandidatoRestController.class);

    public VotosCandidatoRestController() {
    }

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

    @PostMapping(path = "/crearVotoscandidatos")
    public ResponseEntity<VotosCandidato> crearEntity(@RequestBody VotosCandidato votosCandidato) {
        boolean existe, ocupado = false;

        List<VotosCandidato> listaCandidatos = new ArrayList<>();
        try {
            existe = votosCandidatoRepository.existsByidUsuario(votosCandidato.getIdUsuario());
            if (votosCandidato != null && !existe) {
                listaCandidatos = votosCandidatoRepository.cargoOcupado(votosCandidato.getIdCargo().getCargo(), votosCandidato.getIdPartido().getNombre());//obtenemos los candidatos de un determinado partido
                long cantidad = listaCandidatos.stream().filter(d -> d.getUsuarios().getIdMunicipio().getIdDepartamento() == votosCandidato.getUsuarios().getIdMunicipio().getIdDepartamento()).count();//filtramos para tener un conteo de los diputados de un departamento de un partido
                for (VotosCandidato votos : listaCandidatos) {//verificamos si el candidato va para alcalde, que no haya alguien mas con el mismo cargo de su partido en el mismo municipio
                    if (votos.getUsuarios().getIdMunicipio() == votosCandidato.getUsuarios().getIdMunicipio() && votosCandidato.getIdCargo().getCargo().equals("alcalde")) {
                        ocupado = true;
                    } else if (votos.getUsuarios().getIdMunicipio().getIdDepartamento() == votosCandidato.getUsuarios().getIdMunicipio().getIdDepartamento() && votosCandidato.getIdCargo().getCargo().equals("diputado") && ((cantidad + 1) > votosCandidato.getUsuarios().getIdMunicipio().getIdDepartamento().getCantidadDiputados())) {
                        ocupado = true;//verificamos si ya estan completos los candidatos del mismo partido para determinado departtamento
                    }
                    else if (votos.getIdCargo().getCargo().equals("presidente")) {//verificamos si ya hay un candidato para presidente del mismo partido
                        ocupado = true;
                    }
                }

            }
        } catch (Exception e) {
            logger.error("Error creando VotosCandidato", e);
        }
        if (ocupado) {
            return ResponseEntity.badRequest().build();
        } else {
            votosCandidatoRepository.save(votosCandidato);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

    }

    @PutMapping(path = "/actualizarvotos")
    public ResponseEntity<VotosCandidato> actualizarVotos(@RequestBody VotosCandidato votosCandidato) {

        try {
            boolean existe = votosCandidatoRepository.existsByidUsuario(votosCandidato.getIdUsuario());

            if (existe) {
                VotosCandidato votosExistente = votosCandidatoRepository.findByIdUsuario(votosCandidato.getIdUsuario()).get();

                Integer votosActualizados = votosExistente.getVotos() + votosCandidato.getVotos();

                votosExistente.setVotos(votosActualizados);

                votosCandidatoRepository.save(votosExistente);

                return ResponseEntity.ok(votosExistente);
            }
        } catch (Exception e) {
            logger.error("Error actualizando VotosCandidato", e);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
