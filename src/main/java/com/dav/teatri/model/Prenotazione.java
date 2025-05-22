package com.dav.teatri.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compagnia_id")
    private CompagniaAttoriale compagnia;

    @ManyToOne
    @JoinColumn(name = "teatro_servizio_id")
    private TeatroServizio teatroServizio;

    private LocalDate data;
    private LocalTime orarioArrivo;

    public Prenotazione() {}

    public Prenotazione(Long id, CompagniaAttoriale compagnia, TeatroServizio teatroServizio,
                        LocalDate data, LocalTime orarioArrivo) {
        this.id = id;
        this.compagnia = compagnia;
        this.teatroServizio = teatroServizio;
        this.data = data;
        this.orarioArrivo = orarioArrivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompagniaAttoriale getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(CompagniaAttoriale compagnia) {
        this.compagnia = compagnia;
    }

    public TeatroServizio getTeatroServizio() {
        return teatroServizio;
    }

    public void setTeatroServizio(TeatroServizio teatroServizio) {
        this.teatroServizio = teatroServizio;
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

