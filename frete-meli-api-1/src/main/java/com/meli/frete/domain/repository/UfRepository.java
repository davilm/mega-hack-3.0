package com.meli.frete.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.frete.domain.model.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long>{

	Optional<Uf> findByCode(String nome);
}
