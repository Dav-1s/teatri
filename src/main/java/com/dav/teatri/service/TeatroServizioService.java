package com.dav.teatri.service;

import org.springframework.stereotype.Service;

import com.dav.teatri.dto.ServizioDTO;
import com.dav.teatri.dto.TeatroDTO;
import com.dav.teatri.dto.TeatroServizioDTO;
import com.dav.teatri.dto.VistsDTO;
import com.dav.teatri.mapper.ServizioMapper;
import com.dav.teatri.mapper.TeatroMapper;
import com.dav.teatri.mapper.TeatroServizioMapper;
import com.dav.teatri.model.Servizio;
import com.dav.teatri.model.Teatro;
import com.dav.teatri.model.TeatroServizio;
import com.dav.teatri.repository.ServizioRepository;
import com.dav.teatri.repository.TeatroRepository;
import com.dav.teatri.repository.TeatroServizioRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeatroServizioService {

    @Autowired
    private TeatroServizioRepository repository;

    @Autowired
    private TeatroRepository teatroRepository;

    @Autowired
    private ServizioRepository servizioRepository;

    public List<TeatroServizioDTO> findAll() {
        return repository.findAll().stream()
            .map(TeatroServizioMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    public List<TeatroServizio> findAllm() {
        return repository.findAll().stream()
            .collect(Collectors.toList());
    }
    
    public List<TeatroDTO> findTeatroName(List<TeatroServizioDTO> teatroServizi) {
        List<TeatroDTO> teatri = new ArrayList<>();

        for (TeatroServizioDTO teatroServizioDTO : teatroServizi) {
            Long idt = teatroServizioDTO.getTeatroId();
            teatroRepository.findById(idt).ifPresent(teatro -> {
                TeatroDTO tdto = TeatroMapper.toDTO(teatro);
                teatri.add(tdto);
            });
        }

        return teatri;
    }
    
    public List<ServizioDTO> findServizioTipo(List<TeatroServizioDTO> teatroServizi) {
        List<ServizioDTO> servizi = new ArrayList<>();

        for (TeatroServizioDTO teatroServizioDTO : teatroServizi) {
            Long id = teatroServizioDTO.getTipoServizioId();
            servizioRepository.findById(id).ifPresent(servizio -> {
                ServizioDTO sdto = ServizioMapper.toDTO(servizio);
                servizi.add(sdto);
            });
        }

        return servizi;
    }
    
    public List<VistsDTO> listForVis(List<TeatroServizioDTO> teatroServizi) {
    	List<VistsDTO> listForVis = new ArrayList<>();
    	
    	for (TeatroServizioDTO teatroServizioDTO : teatroServizi) {
    		VistsDTO dto = new VistsDTO();
    		dto.setId(teatroServizioDTO.getId());
    		Long idt = teatroServizioDTO.getTeatroId();
            teatroRepository.findById(idt).ifPresent(teatro -> {
                TeatroDTO tdto = TeatroMapper.toDTO(teatro);

                dto.setNomeTeatro(tdto.getNome());
            });
            Long ids = teatroServizioDTO.getTipoServizioId();
            servizioRepository.findById(ids).ifPresent(servizio -> {
                ServizioDTO sdto = ServizioMapper.toDTO(servizio);
                dto.setTipoServizio(sdto.getTipoServizio());
            });
            dto.setRichiedeOrarioArrivo(teatroServizioDTO.getRichiedeOrarioArrivo());
            dto.setNumAddetti(teatroServizioDTO.getNumAddetti());
            listForVis.add(dto);
    	}
    	return listForVis;
    }


    public TeatroServizioDTO findById(Long id) {
        TeatroServizio entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TeatroServizio non trovato con id: " + id));
        return TeatroServizioMapper.toDTO(entity);
    }

    public TeatroServizioDTO create(TeatroServizioDTO dto) {
        Teatro teatro = teatroRepository.findById(dto.getTeatroId())
            .orElseThrow(() -> new RuntimeException("Teatro non trovato con id: " + dto.getTeatroId()));
        Servizio servizio = servizioRepository.findById(dto.getTipoServizioId())
            .orElseThrow(() -> new RuntimeException("Servizio non trovato con id: " + dto.getTipoServizioId()));

        TeatroServizio entity = TeatroServizioMapper.toEntity(dto, teatro, servizio);
        return TeatroServizioMapper.toDTO(repository.save(entity));
    }

    public TeatroServizioDTO update(Long id, TeatroServizioDTO dto) {
        TeatroServizio entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TeatroServizio non trovato con id: " + id));

        Teatro teatro = teatroRepository.findById(dto.getTeatroId())
            .orElseThrow(() -> new RuntimeException("Teatro non trovato con id: " + dto.getTeatroId()));
        Servizio servizio = servizioRepository.findById(dto.getTipoServizioId())
            .orElseThrow(() -> new RuntimeException("Servizio non trovato con id: " + dto.getTipoServizioId()));

        entity.setTeatro(teatro);
        entity.setTipoServizio(servizio);
        entity.setRichiedeOrarioArrivo(dto.getRichiedeOrarioArrivo());
        entity.setNumAddetti(dto.getNumAddetti());

        return TeatroServizioMapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("TeatroServizio non trovato con id: " + id);
        }
        repository.deleteById(id);
    }
}

