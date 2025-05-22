package com.dav.teatri.mapper;

import com.dav.teatri.dto.TeatroDTO;
import com.dav.teatri.model.Teatro;

public class TeatroMapper {
    public static TeatroDTO toDTO(Teatro entity) {
        return new TeatroDTO(
            entity.getId(),
            entity.getNome(),
            entity.getTipo(),
            entity.getOrarioApertura(),
            entity.getOrarioChiusura()
        );
    }

    public static Teatro toEntity(TeatroDTO dto) {
        return new Teatro(
            dto.getId(),
            dto.getNome(),
            dto.getTipo(),
            dto.getOrarioApertura(),
            dto.getOrarioChiusura()
        );
    }
}

