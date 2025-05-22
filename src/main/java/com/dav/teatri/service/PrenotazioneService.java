package com.dav.teatri.service;

import org.springframework.stereotype.Service;

import com.dav.teatri.dto.CompagniaAttorialeDTO;
import com.dav.teatri.dto.PrenotazioneDTO;
import com.dav.teatri.dto.ServizioDTO;
import com.dav.teatri.dto.TeatroDTO;
import com.dav.teatri.dto.VisprDTO;
import com.dav.teatri.mapper.CompagniaAttorialeMapper;
import com.dav.teatri.mapper.PrenotazioneMapper;
import com.dav.teatri.mapper.ServizioMapper;
import com.dav.teatri.mapper.TeatroMapper;
import com.dav.teatri.model.CompagniaAttoriale;
import com.dav.teatri.model.Prenotazione;
import com.dav.teatri.model.TeatroServizio;
import com.dav.teatri.repository.CompagniaAttorialeRepository;
import com.dav.teatri.repository.PrenotazioneRepository;
import com.dav.teatri.repository.ServizioRepository;
import com.dav.teatri.repository.TeatroRepository;
import com.dav.teatri.repository.TeatroServizioRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository repository;

    @Autowired
    private CompagniaAttorialeRepository compagniaRepository;

    @Autowired
    private TeatroServizioRepository teatroServizioRepository;
    
    @Autowired
    private TeatroRepository teatroRepository;

    @Autowired
    private ServizioRepository servizioRepository;

    public List<PrenotazioneDTO> findAll() {
        return repository.findAll().stream()
            .map(PrenotazioneMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    public List<VisprDTO> listForVis(List<PrenotazioneDTO> prenotazioni) {
    	List<VisprDTO> listForVispr = new ArrayList<>();
    	for (PrenotazioneDTO pr : prenotazioni) {
    		VisprDTO dto = new VisprDTO();
    		dto.setId(pr.getId());
			Long idc = pr.getCompagniaId();
			compagniaRepository.findById(idc).ifPresent(compagnia -> {
                CompagniaAttorialeDTO cdto = CompagniaAttorialeMapper.toDTO(compagnia);
                dto.setNomeCompagnia(cdto.getNome());
			});
			Long idts = pr.getTeatroServizioId();
			Long idt = teatroServizioRepository.findById(idts).get().getTeatro().getId();
			
			teatroRepository.findById(idt).ifPresent(teatro -> {
                TeatroDTO tdto = TeatroMapper.toDTO(teatro);

                dto.setNomeTeatro(tdto.getNome());
            });
			
			Long ids = teatroServizioRepository.findById(idts).get().getTipoServizio().getId();
			
			servizioRepository.findById(ids).ifPresent(servizio -> {
                ServizioDTO sdto = ServizioMapper.toDTO(servizio);
                dto.setTipoServizio(sdto.getTipoServizio());
            });
			
			dto.setData(pr.getData());
			dto.setOrarioArrivo(pr.getOrarioArrivo());
			listForVispr.add(dto);
		}
    	return listForVispr;
    }

    public PrenotazioneDTO findById(Long id) {
        Prenotazione entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Prenotazione non trovata con id: " + id));
        return PrenotazioneMapper.toDTO(entity);
    }

    public PrenotazioneDTO create(PrenotazioneDTO dto) {
        CompagniaAttoriale compagnia = compagniaRepository.findById(dto.getCompagniaId())
            .orElseThrow(() -> new RuntimeException("Compagnia non trovata con id: " + dto.getCompagniaId()));
        TeatroServizio teatroServizio = teatroServizioRepository.findById(dto.getTeatroServizioId())
            .orElseThrow(() -> new RuntimeException("TeatroServizio non trovato con id: " + dto.getTeatroServizioId()));

        Prenotazione entity = PrenotazioneMapper.toEntity(dto, compagnia, teatroServizio);
        return PrenotazioneMapper.toDTO(repository.save(entity));
    }

    public PrenotazioneDTO update(Long id, PrenotazioneDTO dto) {
        Prenotazione entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Prenotazione non trovata con id: " + id));

        CompagniaAttoriale compagnia = compagniaRepository.findById(dto.getCompagniaId())
            .orElseThrow(() -> new RuntimeException("Compagnia non trovata con id: " + dto.getCompagniaId()));
        TeatroServizio teatroServizio = teatroServizioRepository.findById(dto.getTeatroServizioId())
            .orElseThrow(() -> new RuntimeException("TeatroServizio non trovato con id: " + dto.getTeatroServizioId()));

        entity.setCompagnia(compagnia);
        entity.setTeatroServizio(teatroServizio);
        entity.setData(dto.getData());
        entity.setOrarioArrivo(dto.getOrarioArrivo());

        return PrenotazioneMapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Prenotazione non trovata con id: " + id);
        }
        repository.deleteById(id);
    }
}
