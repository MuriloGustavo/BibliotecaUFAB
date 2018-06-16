package br.edu.ufab.BibliotecaUFABProjetoRL.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufab.BibliotecaUFABProjetoRL.exception.ResourceNotFoundException;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.TrabalhoDeConclusao;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.TrabalhoDeConclusaoRepository;

@RestController
@RequestMapping("/tccs")
@CrossOrigin(origins = {"http://localhost:8090"})
public class TrabalhoDeConclusaoController {

	static final Logger logger = LoggerFactory.getLogger(TrabalhoDeConclusaoController.class);

	@Autowired
	private TrabalhoDeConclusaoRepository TrabalhoDeConclusaoRepository;
	
	@GetMapping
	public List<TrabalhoDeConclusao> listar() {
		logger.info("TCCS listados com sucesso.");
		return TrabalhoDeConclusaoRepository.findAll();
		
	}
	
	@PostMapping
	public TrabalhoDeConclusao salvar(@Valid @RequestBody TrabalhoDeConclusao TrabalhoDeConclusao) {
		logger.info("TCC salvo com sucesso.");
		return TrabalhoDeConclusaoRepository.save(TrabalhoDeConclusao);	
	}
	
	@GetMapping("/{id}")
	public TrabalhoDeConclusao buscarById(@PathVariable(value = "id") Long TrabalhoDeConclusaoId) {
		logger.info("TCC buscado com sucesso.");
		return TrabalhoDeConclusaoRepository.findById(TrabalhoDeConclusaoId)
				.orElseThrow(() -> new ResourceNotFoundException("TrabalhoDeConclusao", "id", TrabalhoDeConclusaoId));
	}
	
	@PutMapping("/{id}")
	public TrabalhoDeConclusao atualizar(@PathVariable(value = "id") Long TrabalhoDeConclusaoId,
	                                        @Valid @RequestBody TrabalhoDeConclusao TrabalhoDeConclusaoDetails) {
		TrabalhoDeConclusao TrabalhoDeConclusao = TrabalhoDeConclusaoRepository.findById(TrabalhoDeConclusaoId)
				.orElseThrow(() -> new ResourceNotFoundException("TrabalhoDeConclusao", "id", TrabalhoDeConclusaoId));
		
		TrabalhoDeConclusao.setAutores(TrabalhoDeConclusaoDetails.getAutores());
		TrabalhoDeConclusao.setTitulo(TrabalhoDeConclusaoDetails.getTitulo());
		TrabalhoDeConclusao.setTipo(TrabalhoDeConclusaoDetails.getTipo());
		TrabalhoDeConclusao.setLocal(TrabalhoDeConclusaoDetails.getLocal());
		TrabalhoDeConclusao.setAnoDeDefesa(TrabalhoDeConclusaoDetails.getAnoDeDefesa());
		TrabalhoDeConclusao.setOrientadores(TrabalhoDeConclusaoDetails.getOrientadores());
		TrabalhoDeConclusao.setItem_tipo(TrabalhoDeConclusaoDetails.getItem_tipo());

		TrabalhoDeConclusao updatedTCC = TrabalhoDeConclusaoRepository.save(TrabalhoDeConclusao);
		logger.info("TCC atualizado com sucesso.");
	    return updatedTCC;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long TrabalhoDeConclusaoId) {
		TrabalhoDeConclusao TrabalhoDeConclusao = TrabalhoDeConclusaoRepository.findById(TrabalhoDeConclusaoId)
	            .orElseThrow(() -> new ResourceNotFoundException("TrabalhoDeConclusao", "id", TrabalhoDeConclusaoId));

		TrabalhoDeConclusaoRepository.delete(TrabalhoDeConclusao);
		logger.info("TCC deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
}
