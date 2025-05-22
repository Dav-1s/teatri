package com.dav.teatri.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrenotazioneDTO {
    private long id;
    private long compagniaId;
    private long teatroServizioId;
    private LocalDate data;
    private LocalTime orarioArrivo;

    public PrenotazioneDTO() {}

    public PrenotazioneDTO(long id, long compagniaId, long teatroServizioId, LocalDate data, LocalTime orarioArrivo) {
        this.id = id;
        this.compagniaId = compagniaId;
        this.teatroServizioId = teatroServizioId;
        this.data = data;
        this.orarioArrivo = orarioArrivo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompagniaId() {
        return compagniaId;
    }

    public void setCompagniaId(long compagniaId) {
        this.compagniaId = compagniaId;
    }

    public long getTeatroServizioId() {
        return teatroServizioId;
    }

    public void setTeatroServizioId(long teatroServizioId) {
        this.teatroServizioId = teatroServizioId;
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

