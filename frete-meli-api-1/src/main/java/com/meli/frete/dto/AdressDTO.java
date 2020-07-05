package com.meli.frete.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdressDTO {
	
	private String street;
	private String number;
	private String city;
	private String uf;
}
