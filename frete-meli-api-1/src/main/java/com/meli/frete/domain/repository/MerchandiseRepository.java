package com.meli.frete.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.frete.domain.model.Merchandise;

@Repository
public interface MerchandiseRepository  extends JpaRepository<Merchandise, Long> {

}
