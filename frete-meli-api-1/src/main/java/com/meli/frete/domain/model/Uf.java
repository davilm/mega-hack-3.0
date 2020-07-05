package com.meli.frete.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name="uf")
public class Uf implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_UF")
	private Long id;
	
	@Column(name = "CODE", nullable = false)
	private String code;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "uf") 
	private List<City> cities;
	 
}