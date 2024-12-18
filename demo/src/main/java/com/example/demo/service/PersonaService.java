package com.example.demo.service;

import com.example.demo.controller.dto.CosaDTO;
import com.example.demo.controller.dto.PersonaDTO;
import com.example.demo.repository.model.Persona;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;

import java.util.List;

public interface PersonaService {
    PersonaDTO getPersona(Long id);
    PersonaDTO setPersona(PersonaDTO persona);
    void deletePersona(Long id);

    AggregationExpression getPersonaById(long l);

    AggregationExpression createPersona(Persona any);

    AggregationExpression getAllPersonas();

    AggregationExpression updatePersona(long eq, Persona any);
}
