package br.edu.ufab.BibliotecaUFABProjetoRL.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufab.BibliotecaUFABProjetoRL.exception.ResourceNotFoundException;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Jornal;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.JornalRepositorio;

/**
 * Classe responsável por manipular as informações dos objetos tipo jornal.
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */

@RestController
@RequestMapping("/jornais")
@CrossOrigin(origins = {"http://localhost:8090"})
public class JornalController {

	static final Logger logger = LoggerFactory.getLogger(JornalController.class);

	@Autowired
	private JornalRepositorio jornalRepository;
	
	@GetMapping
	public Iterable<Jornal> listar() {
		logger.info("Jornais listados com sucesso.");
		return jornalRepository.findAll();	
	}
	
	@PostMapping
	public Jornal salvar(@Valid @RequestBody Jornal jornal) {
		logger.info("Jornal salvo com sucesso.");
		return jornalRepository.save(jornal);	
	}
	
	@GetMapping("/{id}")
	public Jornal buscarById(@PathVariable(value = "id") Long jornalId) {
		logger.info("Jornal buscado com sucesso.");
		return jornalRepository.findById(jornalId)
				.orElseThrow(() -> new ResourceNotFoundException("jornal", "id", jornalId));	
	}
	
	@PutMapping("/{id}")
	public Jornal atualizar(@PathVariable(value = "id") Long jornalId,
	                                        @Valid @RequestBody Jornal jornalDetails) {
		Jornal jornal = jornalRepository.findById(jornalId)
				.orElseThrow(() -> new ResourceNotFoundException("jornal", "id", jornalId));
		
		jornal.setAutores(jornalDetails.getAutores());
		jornal.setData(jornalDetails.getData());
		jornal.setEdicao(jornalDetails.getEdicao());
		jornal.setItem_tipo(jornalDetails.getItem_tipo());
		jornal.setTitulo(jornalDetails.getTitulo());

		Jornal updatedNote = jornalRepository.save(jornal);
		logger.info("Jornal atualizado com sucesso.");
	    return updatedNote;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> deletar(@PathVariable(value = "id") Long jornalId) {
		Jornal jornal = jornalRepository.findById(jornalId)
	            .orElseThrow(() -> new ResourceNotFoundException("jornal", "id", jornalId));
		jornalRepository.delete(jornal);
		logger.info("Jornal deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
	
}
