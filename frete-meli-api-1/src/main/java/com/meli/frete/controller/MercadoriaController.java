package com.meli.frete.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meli.frete.domain.model.Merchandise;
import com.meli.frete.dto.MerchandiseDTO;
import com.meli.frete.mapper.MerchandiseMapper;
import com.meli.frete.service.MerchandiseService;

@CrossOrigin("*")
@RestController
@RequestMapping("mercadorias")
public class MercadoriaController {

	@Autowired
	private MerchandiseService merchandiseCadastro;
	
	@Autowired 
	private MerchandiseMapper merchandiseMapper;
	
	@GetMapping
	public List<MerchandiseDTO> list() {
		return merchandiseMapper.toCollectionModel(merchandiseCadastro.listar());
	}
	
	@GetMapping("/{merchandiseId}")
	public MerchandiseDTO buscar(@PathVariable Long merchandiseId) {
		return merchandiseMapper.converterModeloToDto(merchandiseCadastro.buscarOuFalhar(merchandiseId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MerchandiseDTO adicionar(@RequestBody MerchandiseDTO merchandise) {
		return merchandiseMapper.converterModeloToDto(merchandiseCadastro.salvar(merchandise));
	}
	
	@PutMapping("/{merchandiseId}")
	public MerchandiseDTO atualizar(@PathVariable Long merchandiseId, @RequestBody MerchandiseDTO merchandise) {
		Merchandise merchandiseAtual = merchandiseCadastro.buscarOuFalhar(merchandiseId);
		BeanUtils.copyProperties(merchandise, merchandiseMapper.converterModeloToDto(merchandiseAtual), "id");
		return merchandiseMapper.converterModeloToDto(merchandiseCadastro.salvar(merchandise));
	} 	
	
	@DeleteMapping("/{merchandiseId}") 
	public void remover(@PathVariable Long merchandiseId ) {
		merchandiseCadastro.excluir(merchandiseId);
	}

}
