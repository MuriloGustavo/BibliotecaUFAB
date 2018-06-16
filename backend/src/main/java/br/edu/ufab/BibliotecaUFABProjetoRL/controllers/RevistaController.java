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
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Revista;

import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.RevistaRepository;

@RestController
@RequestMapping("/revistas")
@CrossOrigin(origins = {"http://localhost:8090"})
public class RevistaController {

	static final Logger logger = LoggerFactory.getLogger(RevistaController.class);

	@Autowired
	private RevistaRepository revistaRepository;
	
	@GetMapping
	public List<Revista> listar() {
		logger.info("Revista listados com sucesso.");
		return revistaRepository.findAll();
	}
	
	@PostMapping
	public Revista salvar(@Valid @RequestBody Revista revista) {
		logger.info("Revista salvo com sucesso.");
		return revistaRepository.save(revista);
	}
	
	@GetMapping("/{id}")
	public Revista buscarById(@PathVariable(value = "id") Long revistaId) {
		logger.info("Revista buscado com sucesso.");
		return revistaRepository.findById(revistaId)
				.orElseThrow(() -> new ResourceNotFoundException("revista", "id", revistaId));	
	}
	
	@PutMapping("/{id}")
	public Revista atualizar(@PathVariable(value = "id") Long revistaId,
	                                        @Valid @RequestBody Revista revistaDetails) {
		Revista revista = revistaRepository.findById(revistaId)
				.orElseThrow(() -> new ResourceNotFoundException("revista", "id", revistaId));
		
		revista.setAutores(revistaDetails.getAutores());
		revista.setDataDePublicacao(revistaDetails.getDataDePublicacao());
		revista.setEdicao(revistaDetails.getEdicao());
		revista.setNumeroDePaginas(revistaDetails.getNumeroDePaginas());
		revista.setTitulo(revistaDetails.getTitulo());

		Revista updatedNote = revistaRepository.save(revista);
		logger.info("Revista atualizado com sucesso.");
	    return updatedNote;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long revistaId) {
		Revista revista = revistaRepository.findById(revistaId)
	            .orElseThrow(() -> new ResourceNotFoundException("revista", "id", revistaId));
		revistaRepository.delete(revista);
		logger.info("Revista deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
	
}
