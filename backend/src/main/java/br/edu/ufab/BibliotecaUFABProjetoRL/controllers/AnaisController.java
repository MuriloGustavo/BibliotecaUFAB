package br.edu.ufab.BibliotecaUFABProjetoRL.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Anais;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.AnaisRepository;

/**
 * Classe responsável por manipular as informações dos objetos tipo
 * Anais.
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */

@RestController
@RequestMapping("/anais")
@CrossOrigin(origins = {"http://localhost:8090"})
public class AnaisController {

	static final Logger logger = LoggerFactory.getLogger(AnaisController.class);

	@Autowired
	private AnaisRepository AnaisRepository;

	@GetMapping
	public List<Anais> listar(Model model) {
		logger.info("Anais listados com sucesso.");		
		return AnaisRepository.findAll();
	}
	
	@PostMapping
	public Anais salvar(@Valid @RequestBody Anais anais) {
		logger.info("Anais salvo com sucesso.");
		return AnaisRepository.save(anais);
	}	

	@GetMapping("/{id}")
	public Anais buscarById(@PathVariable(value = "id") Long anaisId) {
		logger.info("Anais buscado com sucesso.");
		return AnaisRepository.findById(anaisId)
				.orElseThrow(() -> new ResourceNotFoundException("Anais", "id", anaisId));
	}
	
	@PutMapping("/{id}")
	public Anais atualizar(@PathVariable(value = "id") Long anaisId,
	                                        @Valid @RequestBody Anais anaisDetails) {
		Anais anais = AnaisRepository.findById(anaisId)
				.orElseThrow(() -> new ResourceNotFoundException("Anais", "id", anaisId));
		
		anais.setAutores(anaisDetails.getAutores());
		anais.setAnoDePublicacao(anaisDetails.getAnoDePublicacao());
		anais.setTipo(anaisDetails.getTipo());
		anais.setLocal(anaisDetails.getLocal());
		anais.setNomeDoCongresso(anaisDetails.getNomeDoCongresso());
		anais.setItem_tipo(anaisDetails.getItem_tipo());
		anais.setTitulo(anaisDetails.getTitulo());
		
		Anais updatedAnais = AnaisRepository.save(anais);
		logger.info("Anais atualizado com sucesso.");
	    return updatedAnais;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long anaisId) {
		Anais anais = AnaisRepository.findById(anaisId)
	            .orElseThrow(() -> new ResourceNotFoundException("anais", "id", anaisId));

		AnaisRepository.delete(anais);
		logger.info("Anais deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
	
}
