package com.dav.teatri.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dav.teatri.model.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
	
	List<Prenotazione> findByCompagniaId(Long compagniaId);
	
	boolean existsByTeatroServizio_Teatro_IdAndData(Long teatroId, LocalDate data);
	
}

