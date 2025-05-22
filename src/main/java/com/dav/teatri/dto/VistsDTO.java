package com.dav.teatri.dto;

public class VistsDTO {
    private long id;
    private String nomeTeatro;
    private String tipoServizio;
    private boolean richiedeOrarioArrivo;
    private int numAddetti;

    public VistsDTO() {}

    public VistsDTO(long id, String nomeTeatro, String tipoServizio, boolean richiedeOrarioArrivo, int numAddetti) {
        this.id = id;
        this.nomeTeatro = nomeTeatro;
        this.tipoServizio = tipoServizio;
        this.richiedeOrarioArrivo = richiedeOrarioArrivo;
        this.numAddetti = numAddetti;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

