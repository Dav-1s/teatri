package com.dav.teatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dav.teatri.model.Teatro;

public interface TeatroRepository extends JpaRepository<Teatro, Long> {
}

