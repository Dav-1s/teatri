package com.dav.teatri.service;

import org.springframework.stereotype.Service;

import com.dav.teatri.dto.CompagniaAttorialeDTO;
import com.dav.teatri.mapper.CompagniaAttorialeMapper;
import com.dav.teatri.model.CompagniaAttoriale;
import com.dav.teatri.repository.CompagniaAttorialeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompagniaAttorialeService {

    @Autowired
    private CompagniaAttorialeRepository repository;
    
    public CompagniaAttorialeDTO trovaPerNomeECodice(String nome, String codice) {
        Optional<CompagniaAttoriale> entityOpt = repository.findByNomeAndCodiceIscrizione(nome, codice);
        CompagniaAttoriale entity = entityOpt.get();
		return entity != null ? CompagniaAttorialeMapper.toDTO(entity) : null;
    }

    public List<CompagniaAttorialeDTO> findAll() {
        return repository.findAll().stream()
            .map(CompagniaAttorialeMapper::toDTO)
            .collect(Collectors.toList());
    }

    public CompagniaAttorialeDTO findById(Long id) {
        CompagniaAttoriale entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compagnia non trovata con id: " + id));
        return CompagniaAttorialeMapper.toDTO(entity);
    }

    public CompagniaAttorialeDTO create(CompagniaAttorialeDTO dto) {
        CompagniaAttoriale entity = CompagniaAttorialeMapper.toEntity(dto);
        entity = repository.save(entity);
        return CompagniaAttorialeMapper.toDTO(entity);
    }

    public CompagniaAttorialeDTO update(Long id, CompagniaAttorialeDTO dto) {
        CompagniaAttoriale existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compagnia non trovata con id: " + id));
        
        existing.setNome(dto.getNome());
        existing.setCodiceIscrizione(dto.getCodiceIscrizione());
        
        return CompagniaAttorialeMapper.toDTO(repository.save(existing));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Compagnia non trovata con id: " + id);
        }
        repository.deleteById(id);
    }
    
    public Optional<CompagniaAttoriale> findByNomeAndCodiceIscrizione(String nome, String codiceIscrizione) {
        return repository.findByNomeAndCodiceIscrizione(nome, codiceIscrizione);
    }
}
