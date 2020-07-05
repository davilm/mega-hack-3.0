package com.meli.frete.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meli.frete.domain.enumeration.Category;
import com.meli.frete.domain.exception.EntidadeNaoEncontradaException;
import com.meli.frete.domain.model.Adress;
import com.meli.frete.domain.model.City;
import com.meli.frete.domain.model.Merchandise;
import com.meli.frete.domain.model.Uf;
import com.meli.frete.domain.repository.CityRepository;
import com.meli.frete.domain.repository.UfRepository;
import com.meli.frete.dto.AdressDTO;
import com.meli.frete.dto.MerchandiseDTO;

@Component
public class MerchandiseMapper implements Mapper<MerchandiseDTO, Merchandise> {
	
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";
	
	private static final String MSG_UF_NAO_ENCONTRADA = "Não existe um cadastro de UF com a sigla %d";
	
	
	@Autowired
	private CityRepository cidadeRepository;
	
	@Autowired
	private UfRepository ufRepository;
	
	@Override
	public Merchandise converterDtoToModelo(MerchandiseDTO dto) {
		Merchandise modelo = new Merchandise();
	
		Category categoria = Category.valueOf(dto.getCategory());
		modelo.setCategory(categoria == null ? null : categoria);
		
		modelo.setWeight(dto.getWeight() == null ? null : dto.getWeight());
		modelo.setVolume(dto.getVolume() == null ? null : dto.getVolume());
		modelo.setExpected(dto.getExpected() == null ? null :  LocalDate.parse(dto.getExpected(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		Adress endereco = new Adress();
		
		endereco.setStreet(dto.getEndereco().getStreet() == null ? null : dto.getEndereco().getStreet());
		endereco.setNumber(dto.getEndereco().getNumber() == null ? null : dto.getEndereco().getNumber() );
		
		Optional<City> cidade = cidadeRepository.findByName(dto.getEndereco().getCity());
		
		if (!cidade.isPresent()) {
			throw new EntidadeNaoEncontradaException(
				String.format(MSG_CIDADE_NAO_ENCONTRADA, dto.getEndereco().getCity()));
		}
		
		endereco.setCity(cidade.get());
		
		Optional<Uf> uf = ufRepository.findByCode(dto.getEndereco().getUf());
		
		if (!uf.isPresent()) {
			throw new EntidadeNaoEncontradaException(
				String.format(MSG_UF_NAO_ENCONTRADA, dto.getEndereco().getUf()));
		}
		
		endereco.getCity().setUf(uf.get());
		modelo.setAdress(endereco);
		
		modelo.setLatitudeCurrent(dto.getLatitudeCurrent() == null ? null : dto.getLatitudeCurrent());
		modelo.setLongitudeCurrent(dto.getLongitudeCurrent() == null ? null : dto.getLongitudeCurrent());
		
		modelo.setLatitudeDestination(dto.getLatitudeDestination() == null ? null : dto.getLatitudeDestination());
		modelo.setLongitudeDestination(dto.getLongitudeDestination() == null ? null : dto.getLongitudeDestination());
		
		modelo.setFreight(new BigDecimal(dto.getFreight()));
		
		return modelo;
	}

	@Override
	public MerchandiseDTO converterModeloToDto(Merchandise modelo) {
		
		MerchandiseDTO dto  = new MerchandiseDTO();
		
		 	dto.setIdMerchandise(modelo.getId());
			dto.setCategory(modelo.getCategory().getDescription()  == null ? null : modelo.getCategory().getDescription());
			dto.setWeight(modelo.getWeight()  == null ? null : modelo.getWeight());
			dto.setVolume(modelo.getVolume()  == null ? null : modelo.getVolume());
			
			DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dto.setExpected(modelo.getExpected()  == null ? null : modelo.getExpected().format(sf));
			
			AdressDTO endereco = new AdressDTO();
			
			endereco.setStreet(modelo.getAdress().getStreet());
			endereco.setNumber(modelo.getAdress().getNumber());
			endereco.setCity(modelo.getAdress().getCity().getName());
			endereco.setUf(modelo.getAdress().getCity().getUf().getName());
			
			dto.setEndereco(endereco);
			
			dto.setLatitudeCurrent(modelo.getLatitudeCurrent()  == null ? null : modelo.getLatitudeCurrent());
			dto.setLongitudeCurrent(modelo.getLongitudeCurrent() == null ? null : modelo.getLongitudeCurrent());
			
			dto.setLatitudeDestination(modelo.getLatitudeDestination()  == null ? null : modelo.getLatitudeDestination());
			dto.setLongitudeDestination(modelo.getLongitudeDestination() == null ? null : modelo.getLongitudeDestination());
			
			dto.setFreight( modelo.getFreight().doubleValue());		
			
		return dto;
	}
	
	public List<MerchandiseDTO> toCollectionModel(List<Merchandise> pessoas) {
		return pessoas.stream().map(pessoa -> converterModeloToDto(pessoa)).collect(Collectors.toList());
	}
}
