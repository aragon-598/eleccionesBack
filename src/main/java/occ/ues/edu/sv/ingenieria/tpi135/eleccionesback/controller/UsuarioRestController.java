/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.entity.Usuarios;
import occ.ues.edu.sv.ingenieria.tpi135.eleccionesback.repository.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author christian
 */
@RestController
@RequestMapping(path="/usuario")
@CrossOrigin
public class UsuarioRestController {

    @Autowired
    private RepositorioUsuarios repoUsuarios;

    @GetMapping(path="/findAll")
    public ResponseEntity<List<Usuarios>> findAll() {
        List<Usuarios> lista = new ArrayList<>();

        if (repoUsuarios == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                lista = repoUsuarios.findAll();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return new ResponseEntity<>(repoUsuarios.findAll(), HttpStatus.OK);
        }

    }
    
    @GetMapping(path="/findById/{id}")
    public ResponseEntity<Usuarios> getById(@PathVariable Integer id){

        boolean existe=false;
        Usuarios user = new Usuarios();

        try {
            
            if (id != null) {
                existe = repoUsuarios.existsById(id);

                if (existe) {
                    user = repoUsuarios.findById(id).get();

                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.notFound().build();
                }
            }

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path="/login")
    public ResponseEntity<Object> verificarLogeo(@RequestBody Usuarios usr){
        List<Usuarios> match = new ArrayList<>();
        if (usr == null || repoUsuarios == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            try {
                 match= repoUsuarios.findAll().stream().filter(u -> u.getDui() == usr.getDui()).collect(Collectors.toList());
            } catch (Exception e) {
                 Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
           
            if (match.isEmpty()) {
                return new ResponseEntity<>("El dui ingresado no existe", HttpStatus.NOT_FOUND);
            }else if (!match.isEmpty() && !match.get(0).getContrasenia().equals(usr.getContrasenia())) {
                return new ResponseEntity<>("La contraseña es invalida", HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(match.get(0), HttpStatus.ACCEPTED);
            }
        }
    }


    @PutMapping(path="/checkvoto")
    public ResponseEntity<Usuarios> checkVoto(@RequestBody Usuarios userChek){
        Usuarios existe=new Usuarios();
        try {
            if (userChek != null) {
                existe = repoUsuarios.findById(userChek.getIdUsuario()).get();
                if (existe != null && userChek.getEstadoVoto()) {
                    existe.setEstadoVoto(true);
                    repoUsuarios.save(existe);

                    return ResponseEntity.ok(existe);
                }
            }
            
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return ResponseEntity.badRequest().build();
    }

}
