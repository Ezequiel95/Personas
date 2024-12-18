package com.example.demo.service;

import com.example.demo.controller.dto.CosaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cosas-service", url = "http://localhost:8080") // URL de la API de Cosas

public interface CosaClient {


    @RequestMapping(method = RequestMethod.GET, value = "/api/cosas")
    List<CosaDTO> getCosaPorPropiedad(@RequestParam("propietario") Long propietario);

    @GetMapping("/{id}")
    CosaDTO getCosa(@PathVariable("id") Long idCosa);
}
