package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Cargos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Departamentos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Municipios;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.VotosCandidato;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioCargos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioDepartamentos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioMunicipios;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioPartidos;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioUsuarios;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioVotosCandidato;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @Autowired
    RepositorioDepartamentos repoDepartamentos;

    @Autowired
    RepositorioMunicipios repoMunicipios;

    private final static Logger logger = LoggerFactory.getLogger(VotosCandidatoRestController.class);

    /**
     * Devuelve todos los registros de la DB
     */
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

    /**
     * Devuelve un candidato por su id
     * @param id
     * @return
     */
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

    /**
     * Devuelve una lista de diputados por departamento
     * @param id_departamento
     * @return
     */
    @GetMapping(path = "/findDiputadosDepartamento/{id_departamento}")
    public ResponseEntity<Object> findDiputadosDepartamento (@PathVariable Integer id_departamento){
        List<VotosCandidato> diputadosDepartamento = new ArrayList<>();
        Departamentos idDepartamento = new Departamentos();
        Cargos cargo = repoCargo.findByCargo("diputado").get();
        
        try {
            //Lista de diputados de todo el país
            diputadosDepartamento = votosCandidatoRepository.findByIdCargo(cargo);
            

            if(id_departamento != null){
                idDepartamento = repoDepartamentos.findById(id_departamento).get();
                if (idDepartamento != null) {
                    for (int i=0;i<diputadosDepartamento.size();i++) {
                        if (diputadosDepartamento.get(i).getIdUsuario().getIdMunicipio().getIdDepartamento().equals(idDepartamento)) {
                            diputadosDepartamento.remove(diputadosDepartamento.get(i));
                        }
                    }

                    //Lista de diputados por el departamento ingresado
                    return ResponseEntity.ok(diputadosDepartamento);
                }

            }

        } catch (Exception e) {
            logger.error("Error diputados por departamentos", e);
        }
        
        return ResponseEntity.badRequest().build();
    }

    /**
     * Devuelve una lista de alcaldes por municipio
     * @param id_municipio
     * @return
     */
    @GetMapping(path = "/findAlcaldesMunicipio/{id_municipio}")
    public ResponseEntity<Object> findAlcaldesMunicipio (@PathVariable Integer id_municipio){
        List<VotosCandidato> alcaldesMunicipio = new ArrayList<>();
        Municipios municipio =new Municipios();
        Cargos cargo = repoCargo.findById(2).get();
        
        try {
            //Lista de diputados de todo el país
            alcaldesMunicipio = votosCandidatoRepository.findByIdCargo(cargo);
            System.out.println("LISTA DE ALCALDES"+alcaldesMunicipio.size());

            if(id_municipio != null){
                municipio = repoMunicipios.getById(id_municipio);
                System.out.println("Municipioooooooooooooooooooooooooooooooooo"+municipio.getMunicipio());
                if (municipio != null) {
                    for (int i=0;i<alcaldesMunicipio.size();i++) {
                        if(!alcaldesMunicipio.get(i).getIdUsuario().getIdMunicipio().equals(municipio)){
                            alcaldesMunicipio.remove(alcaldesMunicipio.get(i));
                        }
                    }

                    //Lista de diputados por el departamento ingresado
                    return ResponseEntity.ok(alcaldesMunicipio);
                }

            }

        } catch (Exception e) {
            logger.error("Error Alcaldes por municipios", e);
        }
        
        return ResponseEntity.badRequest().build();
    }

    /**
     * Devuelve una lista con los candidatos a presidente
     * @return
     */
    @GetMapping(path = "/findPresidentes")
    public ResponseEntity<Object> findPresidente(){

        List<VotosCandidato> listaPresidentes=new ArrayList<>();
        
        try {
            Cargos cargo = repoCargo.findByCargo("presidente").get();

            listaPresidentes = votosCandidatoRepository.findByIdCargo(cargo);

            return ResponseEntity.ok(listaPresidentes);
            
        } catch (Exception e) {
            logger.error("Error en devolver Presidentes",e);
        }        


        return ResponseEntity.badRequest().build();
    }

    /**
     * Devuelve la lista de diputados escogidos por cada departamento que se indique con su ID
     * @param idDepartamento
     * @return
     */
    @GetMapping(path="/diputadosElegidos/{idDepartamento}")
    public ResponseEntity<Object> diputadosDepartamento(@PathVariable Integer idDepartamento) {
        
        List<VotosCandidato> listaDiputados = new ArrayList<>();
        List<VotosCandidato> listaCandidatos = new ArrayList<>();
        Cargos cargo = repoCargo.findByCargo("diputado").get();

        try {
            
            if (idDepartamento != null) {
                Departamentos departamento = repoDepartamentos.findById(idDepartamento).get();
                listaCandidatos = votosCandidatoRepository.findByIdCargo(cargo);
                if (departamento != null && !listaCandidatos.isEmpty()) {
                    System.out.println(cargo.getCargo());
                    
                    for (int i=0;i<listaCandidatos.size();i++) {
                        if (listaCandidatos.get(i).getIdUsuario().getIdMunicipio().getIdDepartamento().equals(departamento)) {
                            listaDiputados.add(listaCandidatos.get(i));
                        }
                        
                    }
                    
                    if (listaCandidatos.size()>0) {
                        Collections.sort(listaCandidatos, Collections.reverseOrder());


                        return ResponseEntity.ok(listaCandidatos);                        
                    } else {
                        return new ResponseEntity<>("Aún no hay diputados por este departamento",HttpStatus.ACCEPTED);
                    }


                }
            }

        } catch (Exception e) {
            logger.error("Error diputadosElegidos VotosCandidaos", e);
        }

        return ResponseEntity.badRequest().build();
    }
    
    @GetMapping(path = "/alcaldeElectoMunicipio/{id_municipio}")
    public ResponseEntity<Object> alcaldeElecto(@PathVariable Integer id_municipio){

        List<VotosCandidato> listaAlcaldes = new ArrayList<>();
        List<VotosCandidato> alcaldesMunicipios = new ArrayList<>();
        Cargos cargo = new Cargos();

        try {
            cargo = repoCargo.findByCargo("alcalde").get();
            if (id_municipio != null) {
                Municipios municipio = new Municipios();
                municipio=repoMunicipios.findById(id_municipio).get();
                System.out.println("MUNICIPIOOOOOOOOOOOOOOOOOOOO"+municipio.getMunicipio());
                listaAlcaldes = votosCandidatoRepository.findByIdCargo(cargo);
                System.out.println("Tamaño lista de alcaldes "+listaAlcaldes.size());

                if (municipio!= null && listaAlcaldes.size()>0) {
                    for (int i =0;i<listaAlcaldes.size();i++){
                        if (listaAlcaldes.get(i).getIdUsuario().getIdMunicipio().getMunicipio().equals(municipio.getMunicipio())) {
                            System.out.println("MUNICIPIOOOOOOOOOOOOO ["+i+"] "+listaAlcaldes.get(i).getIdUsuario().getIdMunicipio().getMunicipio());
                            alcaldesMunicipios.add(listaAlcaldes.get(i));
                        }
                    }
                    if (alcaldesMunicipios.size()>0) {
                        Collections.sort(alcaldesMunicipios, Collections.reverseOrder());

                    return ResponseEntity.ok(alcaldesMunicipios.get(0));
                    }else{
                        return new ResponseEntity<>("Aún no hay alcaldes postulados",HttpStatus.ACCEPTED);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error en alcalde electo", e);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/presidenteElecto")
    public ResponseEntity<Object> presidenteElecto(){

        List<VotosCandidato> listaPresidentes=new ArrayList<>();

        try {
            
            Cargos cargo = new Cargos();
            cargo = repoCargo.findByCargo("presidente").get();

            listaPresidentes =votosCandidatoRepository.findByIdCargo(cargo);

            if (!listaPresidentes.isEmpty()) {
                Collections.sort(listaPresidentes,Collections.reverseOrder());

                return ResponseEntity.ok(listaPresidentes.get(0));
            }else{
                return new ResponseEntity<>("Aun no hay candidatos postulados",HttpStatus.ACCEPTED);
            }

        } catch (Exception e) {
            logger.error("Error en presidenteElecto",e);
        }
        
        return ResponseEntity.badRequest().build();
    }

    /**
     * Persiste en la DB un nuevo Candidato
     * @param votosCandidato
     * @return
     */
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

    /**
     * Actualiza la cantidad de votos de cada candidato
     * @param votosCandidato
     * @return
     */
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
