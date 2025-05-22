package com.dav.teatri.dto;

import java.time.LocalTime;

public class TeatroDTO {
    private long id;
    private String nome;
    private String tipo;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public TeatroDTO() {}

    public TeatroDTO(long id, String nome, String tipo, LocalTime orarioApertura, LocalTime orarioChiusura) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

    public void setOrarioChiusura(LocalTime orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }
}

