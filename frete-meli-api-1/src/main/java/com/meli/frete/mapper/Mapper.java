package com.meli.frete.mapper;


public interface Mapper<D, M> {

	M converterDtoToModelo(D dto);
	D converterModeloToDto(M modelo);

}
