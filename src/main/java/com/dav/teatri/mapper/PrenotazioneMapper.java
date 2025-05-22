package com.dav.teatri.mapper;

import com.dav.teatri.dto.PrenotazioneDTO;
import com.dav.teatri.model.CompagniaAttoriale;
import com.dav.teatri.model.Prenotazione;
import com.dav.teatri.model.TeatroServizio;

public class PrenotazioneMapper {
    public static PrenotazioneDTO toDTO(Prenotazione entity) {
        return new PrenotazioneDTO(
            entity.getId(),
            entity.getCompagnia().getId(),
            entity.getTeatroServizio().getId(),
            entity.getData(),
            entity.getOrarioArrivo()
        );
    }

    public static Prenotazione toEntity(PrenotazioneDTO dto, CompagniaAttoriale compagnia, TeatroServizio teatroServizio) {
        return new Prenotazione(
            dto.getId(),
            compagnia,
            teatroServizio,
            dto.getData(),
            dto.getOrarioArrivo()
        );
    }
}
