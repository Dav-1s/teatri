package com.dav.teatri.mapper;

import com.dav.teatri.dto.TeatroServizioDTO;
import com.dav.teatri.model.Servizio;
import com.dav.teatri.model.Teatro;
import com.dav.teatri.model.TeatroServizio;

public class TeatroServizioMapper {
    public static TeatroServizioDTO toDTO(TeatroServizio entity) {
        return new TeatroServizioDTO(
            entity.getId(),
            entity.getTeatro().getId(),
            entity.getTipoServizio().getId(),
            entity.getRichiedeOrarioArrivo(),
            entity.getNumAddetti()
        );
    }

    public static TeatroServizio toEntity(TeatroServizioDTO dto, Teatro teatro, Servizio servizio) {
        return new TeatroServizio(
            dto.getId(),
            teatro,
            servizio,
            dto.getRichiedeOrarioArrivo(),
            dto.getNumAddetti()
        );
    }
}

