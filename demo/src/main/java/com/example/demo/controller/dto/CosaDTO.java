package com.example.demo.controller.dto;

public class  CosaDTO  {

    private Long  idCosa;
    private String  tipo;
    private String nombre;
    private String descripcion;
    private String propietario;
    private String status;
    public CosaDTO(String propietario) {
        this.propietario = propietario;
    }



    public CosaDTO(Long idCosa, String tipo, String nombre, String descripcion, String status) {
        this.idCosa = idCosa;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.status = status;
    }



    public CosaDTO(){

    }

    public Long getIdCosa() {
        return idCosa;
    }

    public void setIdCosa(Long idCosa) {
        this.idCosa = idCosa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getPropietario() {return propietario;}

    public void setPropietario(String propietario) {this.propietario = propietario;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

