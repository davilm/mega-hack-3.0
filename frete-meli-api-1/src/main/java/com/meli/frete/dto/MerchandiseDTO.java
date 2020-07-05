package com.meli.frete.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseDTO {
	
	private Long idMerchandise;
	private String category;
	private String weight;
	private String volume;
	private String expected;
	
	private AdressDTO endereco;
	
	private String latitudeCurrent;
	private String longitudeCurrent;
	
	private String latitudeDestination;
	private String longitudeDestination;
	
	private double freight;

}
