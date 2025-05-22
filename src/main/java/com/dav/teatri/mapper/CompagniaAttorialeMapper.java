package com.dav.teatri.mapper;

import com.dav.teatri.dto.CompagniaAttorialeDTO;
import com.dav.teatri.model.CompagniaAttoriale;

public class CompagniaAttorialeMapper {
    public static CompagniaAttorialeDTO toDTO(CompagniaAttoriale entity) {
        return new CompagniaAttorialeDTO(
            entity.getId(),
            entity.getNome(),
            entity.getCodiceIscrizione()
        );
    }

    public static CompagniaAttoriale toEntity(CompagniaAttorialeDTO dto) {
        return new CompagniaAttoriale(
            dto.getId(),
            dto.getNome(),
            dto.getCodiceIscrizione()
        );
    }
}

