package org.iplacex.proyectos.discografia.dicografia.discos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Disco")
public class Disco {
    @Id
    public String id;
    
    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
}
