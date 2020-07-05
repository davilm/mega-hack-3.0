package com.meli.frete.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.meli.frete.domain.exception.EntidadeEmUsoException;
import com.meli.frete.domain.exception.EntidadeNaoEncontradaException;
import com.meli.frete.domain.model.Merchandise;
import com.meli.frete.domain.repository.MerchandiseRepository;
import com.meli.frete.dto.MerchandiseDTO;
import com.meli.frete.mapper.MerchandiseMapper;

@Service
public class MerchandiseService {
	
	private static final String MERCADORIA_NAO_PODE_SER_REMOVIDA_ESTA_EM_USO = "Mercadoria de código %d nao pode ser removida, está em uso.";

	private static final String MSG_MERCADORIA_NAO_ENCONTRADA = "Não existe um cadastro de Mercadoria com código %d";

	@Autowired
	private MerchandiseRepository merchandiseRepository;
	
	@Autowired 
	private MerchandiseMapper merchandiseMapper;
	
	public List<Merchandise> listar() {
		return merchandiseRepository.findAll();
	}

	public Merchandise salvar(MerchandiseDTO merchandiseDto) {
		
		Merchandise merchan = merchandiseMapper.converterDtoToModelo(merchandiseDto);
		return merchandiseRepository.save(merchan);
	}

	public void excluir(Long id) {
		try {
			merchandiseRepository.deleteById(id);
		} catch (EmptyResultDataAccessException  e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_MERCADORIA_NAO_ENCONTRADA, id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MERCADORIA_NAO_PODE_SER_REMOVIDA_ESTA_EM_USO, id));
		}
	}
	
	public Merchandise buscarOuFalhar(Long merchandiseId) {
		return merchandiseRepository.findById(merchandiseId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format(MSG_MERCADORIA_NAO_ENCONTRADA, merchandiseId)));
	}

}
