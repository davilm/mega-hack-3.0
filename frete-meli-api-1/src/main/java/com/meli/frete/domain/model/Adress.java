package com.meli.frete.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adress implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "STREET", length = 30, nullable = false)
	private String street;
	
	@Column(name = "NUMBER", length = 5)
	private String number;
	
	@JoinColumn(name = "ID_CITY", nullable = false)
	@ManyToOne
	private City city;
	 
}