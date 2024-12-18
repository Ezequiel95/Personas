package com.example.demo.service;

import com.example.demo.controller.dto.CosaDTO;
import com.example.demo.controller.dto.PersonaDTO;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public  class PersonaServicelmpl implements PersonaService {

    private  PersonaRepository personaRepository;
    private CosaClient cosaClient;


    @Autowired
    public PersonaServicelmpl( PersonaRepository personaRepository, @Lazy CosaClient cosaClient) {
        this.personaRepository = personaRepository;
        this.cosaClient = cosaClient;
    }

    @Override
    public PersonaDTO getPersona(Long id) {
        PersonaDTO persona = toDTO(personaRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona not found")));
        List<CosaDTO> cosas = cosaClient.getCosaPorPropiedad((persona.getIdPersona()));
        persona.setCosas(cosas);
        return persona;
    }


    public PersonaDTO setPersona(PersonaDTO personaDTO) {
        // Convertir el DTO a la entidad de Persona
        Persona persona = toEntity(personaDTO);

        // Guardar (crear o actualizar) la persona en la base de datos
        Persona savedPersona = personaRepository.save(persona);

        // Convertir la entidad de nuevo al DTO y devolverlo
        return toDTO(savedPersona);
    }
    @Override
    public void deletePersona(Long id) {personaRepository.deleteById(id);}

    @Override
    public AggregationExpression getPersonaById(long l) {
        return null;
    }

    @Override
    public AggregationExpression createPersona(Persona any) {
        return null;
    }

    @Override
    public AggregationExpression getAllPersonas() {
        return null;
    }

    @Override
    public AggregationExpression updatePersona(long eq, Persona any) {
        return null;
    }


    private Persona toEntity(PersonaDTO dto) {
        return new Persona(
          dto.getIdPersona(),
          dto.getNombres(),
          dto.getApellidos(),
          dto.getEdad(),
          dto.getGenero(),
          dto.getStatus()
        );
    }
    private PersonaDTO toDTO(Persona entity) {
        // Usamos el constructor de CosaDTO para inicializar un nuevo objeto con los valores de la entidad
        return new PersonaDTO(
                entity.getIdPersona(),         // Asignar idCosa
                entity.getNombres(),           // Asignar tipo
                entity.getApellidos(),         // Asignar nombre
                entity.getEdad(),    // Asignar descripcion
                entity.getGenero(),    // Asignar propietario
                entity.getStatus()          // Asignar status
        );
    }
}
