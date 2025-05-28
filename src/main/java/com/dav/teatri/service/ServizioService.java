package com.dav.teatri.service;

import org.springframework.stereotype.Service;

import com.dav.teatri.dto.ServizioDTO;
import com.dav.teatri.mapper.ServizioMapper;
import com.dav.teatri.model.Servizio;
import com.dav.teatri.repository.ServizioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServizioService {

    @Autowired
    private ServizioRepository repository;

    public List<ServizioDTO> findAll() {
        return repository.findAll().stream()
            .map(ServizioMapper::toDTO)
            .collect(Collectors.toList());
    }

    public ServizioDTO findById(Long id) {
    	
        Servizio entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Servizio non trovato con id: " + id));
        return ServizioMapper.toDTO(entity);
        
//      if (repository.findById(id).isPresent()) {
//    		Servizio entity = repository.findById(id).get();
//    		return ServizioMapper.toDTO(entity);
//    	} else {
//    		throw new RuntimeException("Servizio non trovato con id: " + id);
//    	}
        
    }

    public ServizioDTO create(ServizioDTO dto) {
        Servizio entity = ServizioMapper.toEntity(dto);
        return ServizioMapper.toDTO(repository.save(entity));
    }

    public ServizioDTO update(Long id, ServizioDTO dto) {
    	
        Servizio entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Servizio non trovato con id: " + id));
        
        entity.setTipoServizio(dto.getTipoServizio());
        return ServizioMapper.toDTO(repository.save(entity));
        
//      if (repository.findById(id).isPresent()) {
//    		Servizio entity = repository.findById(id).get();
//        	entity.setTipoServizio(dto.getTipoServizio());
//    		return ServizioMapper.toDTO(repository.save(entity));
//    	} else {
//    		throw new RuntimeException("Servizio non trovato con id: " + id);
//    	}
        
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Servizio non trovato con id: " + id);
        }
        repository.deleteById(id);
    }
}

