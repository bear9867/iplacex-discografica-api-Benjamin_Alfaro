package org.iplacex.proyectos.discografia.dicografia.discos;


import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.dicografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class DiscoController {
    @Autowired
    private IDiscoRepository discoRepository ;
     @Autowired
    private IArtistaRepository artistaRepository;


    @PostMapping(
        value = "/disco",
        consumes= MediaType.APPLICATION_JSON_VALUE,
        produces= MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Disco> HandlePostDiscoRequest(
        @RequestBody Disco disco) {
        if(!artistaRepository.existsById(disco.idArtista)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Disco temp = discoRepository.insert(disco);
        return new ResponseEntity<>(temp, HttpStatus.CREATED);
    }

    
    @GetMapping(
        value = "/discos",
        produces= MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<List<Disco>> HandleGetDiscosRequest(){
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos,HttpStatus.OK);
    }


    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandleGetDiscoRequest
    (@PathVariable("id") String id){

        if(!discoRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Disco> temp = discoRepository.findById(id);
        return new ResponseEntity<>(temp.get(),HttpStatus.OK);
    }

    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest
    (@PathVariable("id") String id){

        if(!artistaRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Disco> temp = discoRepository.findDiscosByIdArtista(id);
        return new ResponseEntity<>(temp,HttpStatus.OK);
    }


    
}
