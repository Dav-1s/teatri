package com.dav.teatri.dto;

public class TeatroServizioDTO {
    private long id;
    private long teatroId;
    private long tipoServizioId;
    private boolean richiedeOrarioArrivo;
    private int numAddetti;

    public TeatroServizioDTO() {}

    public TeatroServizioDTO(long id, long teatroId, long tipoServizioId, boolean richiedeOrarioArrivo, int numAddetti) {
        this.id = id;
        this.teatroId = teatroId;
        this.tipoServizioId = tipoServizioId;
        this.richiedeOrarioArrivo = richiedeOrarioArrivo;
        this.numAddetti = numAddetti;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTeatroId() {
        return teatroId;
    }

    public void setTeatroId(long teatroId) {
        this.teatroId = teatroId;
    }

    public long getTipoServizioId() {
        return tipoServizioId;
    }

    public void setTipoServizioId(long tipoServizioId) {
        this.tipoServizioId = tipoServizioId;
    }

    public boolean getRichiedeOrarioArrivo() {
        return richiedeOrarioArrivo;
    }

    public void setRichiedeOrarioArrivo(boolean richiedeOrarioArrivo) {
        this.richiedeOrarioArrivo = richiedeOrarioArrivo;
    }

    public int getNumAddetti() {
        return numAddetti;
    }

    public void setNumAddetti(int numAddetti) {
        this.numAddetti = numAddetti;
    }
}

