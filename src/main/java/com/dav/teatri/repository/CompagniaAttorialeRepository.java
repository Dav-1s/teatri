package com.dav.teatri.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dav.teatri.model.CompagniaAttoriale;

public interface CompagniaAttorialeRepository extends JpaRepository<CompagniaAttoriale, Long> {

	Optional<CompagniaAttoriale> findByNomeAndCodiceIscrizione(String nome, String codiceIscrizione);
}
