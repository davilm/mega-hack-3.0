package com.meli.frete.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name="city")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CITY")
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	//@JsonIgnoreProperties(value = {"sigla", "nome" }, allowGetters = true)
	@JoinColumn(name = "ID_UF", nullable = false)
	@ManyToOne
	private Uf uf;

}
