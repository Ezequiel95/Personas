package com.example.demo.controller.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class PersonaDTO {

    @Id
    private Long idPersona;
    private String genero;
    private String nombres;
    private String apellidos;
    private String edad;
    private String status;


    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<CosaDTO> cosas;
    // Constructor sin argumentos
    public PersonaDTO() {
    }
    public Long getIdPersona() {
        return idPersona;
    }

    public PersonaDTO(List<CosaDTO> cosas) {
        this.cosas = cosas;
    }


    public List<CosaDTO> getCosas() {
        return cosas;
    }

    public void setCosas(List<CosaDTO> cosas) {
        this.cosas = cosas;
    }



    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public PersonaDTO(Long idPersona, String nombres, String apellidos, String edad, String genero, String status) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.status = status;
    }



}
