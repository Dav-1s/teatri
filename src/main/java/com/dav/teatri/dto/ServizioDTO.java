package com.dav.teatri.dto;

public class ServizioDTO {
    private long id;
    private String tipoServizio;

    public ServizioDTO() {}

    public ServizioDTO(long id, String tipoServizio) {
        this.id = id;
        this.tipoServizio = tipoServizio;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoServizio() {
        return tipoServizio;
    }

    public void setTipoServizio(String tipoServizio) {
        this.tipoServizio = tipoServizio;
    }
}

