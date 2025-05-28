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
    	CompagniaAttoriale entity = null;
    	if (repository.findByNomeAndCodiceIscrizione(nome, codice).isPresent()) {
    		entity = repository.findByNomeAndCodiceIscrizione(nome, codice).get();
    	}
        
//        if (entity != null) {
//         	return CompagniaAttorialeMapper.toDTO(entity);
//        } else {
//         	return null;
//        }
        
		return entity != null ? CompagniaAttorialeMapper.toDTO(entity) : null;
//  ":" = "altrimenti"
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
        
//        if (repository.findById(id).isPresent()) {
//        	CompagniaAttoriale entity = repository.findById(id).get();
//        	return CompagniaAttorialeMapper.toDTO(entity);
//        } else {
//        	throw new RuntimeException("Compagnia non trovata con id: " + id);
//        }

    }
    
    public Optional<CompagniaAttoriale> findByIdOpt(Long id) {
        
        if (repository.findById(id).isPresent()) {
        	return repository.findById(id);
        } else {
        	return null;
        }
        
        
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
        
//    	if (repository.findById(id).isPresent()) {
//    		CompagniaAttoriale existing = repository.findById(id).get();
//    		existing.setNome(dto.getNome());
//    		existing.setCodiceIscrizione(dto.getCodiceIscrizione());
//    		return CompagniaAttorialeMapper.toDTO(existing);
//    	} else {
//    		throw new RuntimeException("Compagnia non trovata con id: " + id);
//    	}
        
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Compagnia non trovata con id: " + id);
        }
//        "repository.existsById(id)" = controlla se esiste un elemento con id e ritorna un buleano
//        "!" = "!true"  diventa false
//        	    "!false" diventa  true
        repository.deleteById(id);
    }
    
    public Optional<CompagniaAttoriale> findByNomeAndCodiceIscrizione(String nome, String codiceIscrizione) {
        return repository.findByNomeAndCodiceIscrizione(nome, codiceIscrizione);
    }
}
