package com.dav.teatri.dto;

public class CompagniaAttorialeDTO {
    private long id;
    private String nome;
    private String codiceIscrizione;

    public CompagniaAttorialeDTO() {}

    public CompagniaAttorialeDTO(long id, String nome, String codiceIscrizione) {
        this.id = id;
        this.nome = nome;
        this.codiceIscrizione = codiceIscrizione;
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

    public String getCodiceIscrizione() {
        return codiceIscrizione;
    }

    public void setCodiceIscrizione(String codiceIscrizione) {
        this.codiceIscrizione = codiceIscrizione;
    }
}

