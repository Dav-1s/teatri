package com.dav.teatri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TeatroServizio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teatro_id")
    private Teatro teatro;

    @ManyToOne
    @JoinColumn(name = "tipo_servizio_id")
    private Servizio tipoServizio;

    private Boolean richiedeOrarioArrivo;
    private Integer numAddetti;

    public TeatroServizio() {}

    public TeatroServizio(Long id, Teatro teatro, Servizio tipoServizio, Boolean richiedeOrarioArrivo, Integer numAddetti) {
        this.id = id;
        this.teatro = teatro;
        this.tipoServizio = tipoServizio;
        this.richiedeOrarioArrivo = richiedeOrarioArrivo;
        this.numAddetti = numAddetti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teatro getTeatro() {
        return teatro;
    }

    public void setTeatro(Teatro teatro) {
        this.teatro = teatro;
    }

    public Servizio getTipoServizio() {
        return tipoServizio;
    }

    public void setTipoServizio(Servizio tipoServizio) {
        this.tipoServizio = tipoServizio;
    }

    public Boolean getRichiedeOrarioArrivo() {
        return richiedeOrarioArrivo;
    }

    public void setRichiedeOrarioArrivo(Boolean richiedeOrarioArrivo) {
        this.richiedeOrarioArrivo = richiedeOrarioArrivo;
    }

    public Integer getNumAddetti() {
        return numAddetti;
    }

    public void setNumAddetti(Integer numAddetti) {
        this.numAddetti = numAddetti;
    }
}

