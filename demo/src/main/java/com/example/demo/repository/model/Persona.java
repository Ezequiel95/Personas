package com.example.demo.repository.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


@Document(collection = "persona")  // Especifica el nombre de la colección en MongoDB
public class Persona {

    @Id
    @Field("idpersona")
    private Long idPersona;  // MongoDB manejará el ID automáticamente

    @Field("genero")  // Mapea el campo 'genero' en el documento MongoDB
    private String genero;

    @Field("nombres")  // Si quieres especificar un nombre diferente para el campo en MongoDB
    private String nombres;

    @Field("apellidos")
    private String apellidos;

    @Field("edad")
    private String edad;

    @Field("status")  // Mapea el campo 'status' en el documento MongoDB
    private String status;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Long generateNextId() {
        // Lógica para generar el siguiente ID de tipo Long
        // Usando un contador o alguna lógica similar
        Query query = new Query(Criteria.where("collection").is("personas"));
        Update update = new Update().inc("seq", 1);
        return mongoTemplate.findAndModify(query, update, options().returnNew(true), Long.class);
    }

    public Long getIdPersona() {
        return idPersona;
    }


    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    // Getters and Setters

    // Constructor sin parámetros
    public Persona() {}

    // Constructor con parámetros
    public Persona(Long idPersona, String nombres, String apellidos, String edad, String genero, String status) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.status = status;
    }


}
