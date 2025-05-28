package com.dav.teatri.service;

import org.springframework.stereotype.Service;

import com.dav.teatri.dto.TeatroDTO;
import com.dav.teatri.mapper.TeatroMapper;
import com.dav.teatri.model.Teatro;
import com.dav.teatri.repository.TeatroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeatroService {

    @Autowired
    private TeatroRepository repository;

    public List<TeatroDTO> findAll() {
        return repository.findAll().stream()
            .map(TeatroMapper::toDTO)
            .collect(Collectors.toList());
    }

    public TeatroDTO findById(Long id) {
    	
        Teatro entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Teatro non trovato con id: " + id));
        return TeatroMapper.toDTO(entity);
        
//      if (repository.findById(id).isPresent()) {
//    		Teatro entity = repository.findById(id).get();
//    		return TeatroMapper.toDTO(entity);
//    	} else {
//    		throw new RuntimeException("Teatro non trovato con id: " + id);
//    	}
        
    }

    public TeatroDTO create(TeatroDTO dto) {
        Teatro entity = TeatroMapper.toEntity(dto);
        return TeatroMapper.toDTO(repository.save(entity));
    }

    public TeatroDTO update(Long id, TeatroDTO dto) {
    	
        Teatro entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Teatro non trovato con id: " + id));
        
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        entity.setOrarioApertura(dto.getOrarioApertura());
        entity.setOrarioChiusura(dto.getOrarioChiusura());

        return TeatroMapper.toDTO(repository.save(entity));
        
//      if (repository.findById(id).isPresent()) {
//    		Teatro entity = repository.findById(id).get();
//      	entity.setNome(dto.getNome());
//      	entity.setTipo(dto.getTipo());
//      	entity.setOrarioApertura(dto.getOrarioApertura());
//      	entity.setOrarioChiusura(dto.getOrarioChiusura());
//    		return TeatroMapper.toDTO(repository.save(entity));
//    	} else {
//    		throw new RuntimeException("Teatro non trovato con id: " + id);
//    	}
        
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Teatro non trovato con id: " + id);
        }
        repository.deleteById(id);
    }
}

