package com.meli.frete.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.meli.frete.domain.exception.EntidadeEmUsoException;
import com.meli.frete.domain.exception.EntidadeNaoEncontradaException;
import com.meli.frete.domain.model.Uf;
import com.meli.frete.domain.repository.UfRepository;

@Service
public class UfService {

	private static final String MSG_ESTADO_EM_USO = "Uf de código %d nao pode ser removido, está em uso.";

	private static final String MSG_ESTADO_INEXISTENTE = "Não existe um cadastro de Uf com código %d";

	@Autowired
	private UfRepository ufRepository;
	
	public Uf salvar(Uf estado) {
		 Uf estadoE = ufRepository.save(estado);
		 return estadoE;
	}
	
	public void excluir(Long id) {
		try {
			ufRepository.deleteById(id);
		} catch (EmptyResultDataAccessException  e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_INEXISTENTE, id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, id));
		}
	}
	
	public List<Uf> listar() {
		return ufRepository.findAll();		
	}
	
	public Uf buscarOuFalhar(Long estadoId) {
		return ufRepository.findById(estadoId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_INEXISTENTE, estadoId)));
	}
	
	public Uf buscarPorSigla(String sigla) {
		return ufRepository.findByCode(sigla)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_INEXISTENTE, sigla)));
	}
}
