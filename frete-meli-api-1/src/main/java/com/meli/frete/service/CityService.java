package com.meli.frete.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.meli.frete.domain.exception.EntidadeEmUsoException;
import com.meli.frete.domain.exception.EntidadeNaoEncontradaException;
import com.meli.frete.domain.model.City;
import com.meli.frete.domain.model.Uf;
import com.meli.frete.domain.repository.CityRepository;
import com.meli.frete.domain.repository.UfRepository;

@Service
public class CityService {

	private static final String MSG_CIDADE_ESTA_EM_USO = "Cidade de código %d nao pode ser removida, está em uso.";

	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";

	@Autowired
	private CityRepository cidadeRepository;
	
	@Autowired
	private UfRepository estadoRepository;
	
	public City salvar(City cidade) {
		   Long estadoId = cidade.getUf().getId(); 
		   Uf estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
					  String.format("Não existe cadastro de estado com código %d", estadoId)));
		  cidade.setUf(estado);
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException  e) {
				throw new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA, id));			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_ESTA_EM_USO, id));
		}
	}
	
	public List<City> listar() {
		return cidadeRepository.findAll();
	}
	
	public City buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
	
	public City buscarPorNome(String cidade) {
		return cidadeRepository.findByName(cidade)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA, cidade)));
	}
}