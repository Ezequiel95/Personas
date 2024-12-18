package com.example.demo.controller;

import com.example.demo.controller.dto.CosaDTO;
import com.example.demo.controller.dto.PersonaDTO;
import com.example.demo.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@Tag(name = "Persona Controller", description = "Controller for managing Personas entities")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Operation(summary = "Get a Persona by ID", description = "Fetch a single Persona entity by its ID")
    @GetMapping("/{id}")
    public PersonaDTO getPersona(
            @Parameter(description = "ID of the Persona to fetch", required = true) @PathVariable Long id) {
        return personaService.getPersona(id);
    }


    @Operation(summary = "Create or update a Persona", description = "Create a new Persona or update an existing one")
    @PostMapping
    public PersonaDTO createPersona(
            @Parameter(description = "Persona object to create or update", required = true) @RequestBody PersonaDTO persona) {
        return personaService.setPersona(persona);
    }

    @Operation(summary = "Create or update a Persona", description = "Create a new Persona or update an existing one")
    @PutMapping
    public PersonaDTO updatePersona(
            @Parameter(description = "Persona object to create or update", required = true) @RequestBody PersonaDTO persona) {
        return personaService.setPersona(persona);
    }

    @Operation(summary = "Delete a Persona by ID", description = "Delete an existing Persona entity by its ID")
    @DeleteMapping("/{id}")
    public void deletePersona(
            @Parameter(description = "ID of the Persona to delete", required = true) @PathVariable Long id) {
        personaService.deletePersona(id);
    }
}