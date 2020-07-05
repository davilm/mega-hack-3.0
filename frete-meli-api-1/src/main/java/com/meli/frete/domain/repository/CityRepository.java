package com.meli.frete.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.frete.domain.model.City;

@Repository 
public interface CityRepository extends JpaRepository<City, Long> {

	Optional<City> findByName(String nome);
}
