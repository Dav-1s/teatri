package com.dav.teatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dav.teatri.model.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}

