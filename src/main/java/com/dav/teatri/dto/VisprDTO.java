package com.dav.teatri.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisprDTO {
    private long id;
    private String nomeCompagnia;
    private String nomeTeatro;
    private String tipoServizio;
    private LocalDate data;
    private LocalTime orarioArrivo;

    public VisprDTO() {}

    public VisprDTO(long id, String nomeCompagnia, String nomeTeatro, String tipoServizio, LocalDate data, LocalTime orarioArrivo) {
        this.id = id;
        this.nomeCompagnia = nomeCompagnia;
        this.nomeTeatro = nomeTeatro;
        this.tipoServizio = tipoServizio;
        this.data = data;
        this.orarioArrivo = orarioArrivo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCompagnia() {
        return nomeCompagnia;
    }

    public void setNomeCompagnia(String nomeCompagnia) {
        this.nomeCompagnia = nomeCompagnia;
    }

    public String getNomeTeatro() {
        return nomeTeatro;
    }

    public void setNomeTeatro(String nomeTeatro) {
        this.nomeTeatro = nomeTeatro;
    }

    public String getTipoServizio() {
        return tipoServizio;
    }

    public void setTipoServizio(String tipoServizio) {
        this.tipoServizio = tipoServizio;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(LocalTime orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }
}
