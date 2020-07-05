package com.meli.frete.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.meli.frete.domain.enumeration.Category;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Merchandise")
public class Merchandise implements Serializable {

	/**
	 * Serial number UID.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MERCHANDISE")
	private Long id;		
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY", length = 15, nullable = false)
	private Category category;
	
	@NotNull
	@Column(name = "WEIGHT", length = 10, nullable = false)
	private String  weight;
	
	@NotNull
	@Column(name = "VOLUME", length = 10, nullable = false)
	private String  volume;
	
	@NotNull
	@Column(name = "EXPECTED", nullable = false)
	private LocalDate expected;
	
	@Embedded
	private Adress adress;
	
	@NotNull
	@Column(name = "LATITUDE_CURRENT", length = 20, nullable = false)
	private String latitudeCurrent;
	
	@NotNull
	@Column(name = "LONGITUDE_CURRENT", length = 20, nullable = false)
	private String longitudeCurrent;
	
	@NotNull
	@Column(name = "LATITUDE_DESTINATION", length = 20, nullable = false)
	private String latitudeDestination;
	
	@NotNull
	@Column(name = "LONGITUDE_DESTINATION", length = 20, nullable = false)
	private String longitudeDestination;
	
	@NotNull
	@Column(name = "FREIGHT", length = 20, nullable = false)
	private BigDecimal freight;
	
}
