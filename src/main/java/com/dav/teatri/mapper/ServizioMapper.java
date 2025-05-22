package com.dav.teatri.mapper;

import com.dav.teatri.dto.ServizioDTO;
import com.dav.teatri.model.Servizio;

public class ServizioMapper {
    public static ServizioDTO toDTO(Servizio entity) {
        return new ServizioDTO(
            entity.getId(),
            entity.getTipoServizio()
        );
    }

    public static Servizio toEntity(ServizioDTO dto) {
        return new Servizio(
            dto.getId(),
            dto.getTipoServizio()
        );
    }
}

