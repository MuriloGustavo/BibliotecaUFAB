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
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.Curso;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.CursoRepository;

/**
 * Classe responsável por manipular as informações dos objetos tipo curso.
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = {"http://localhost:8090"})
public class CursoController {

	static final Logger logger = LoggerFactory.getLogger(CursoController.class);

	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<Curso> listar() {
		logger.info("Cursos listados com sucesso.");
		return cursoRepository.findAll();
	}
	
	@PostMapping
	public Curso salvar(@Valid @RequestBody Curso curso) {
		logger.info("Curso salvo com sucesso.");
		return cursoRepository.save(curso);
	}
	
	@GetMapping("/{id}")
	public Curso buscarById(@PathVariable(value = "id") Long cursoId) {
		logger.info("Curso buscado com sucesso.");
		return cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));	
	}
	
	@PutMapping("/{id}")
	public Curso atualizar(@PathVariable(value = "id") Long cursoId,
	                                        @Valid @RequestBody Curso cursoDetails) {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));
		
		curso.setNome(cursoDetails.getNome());
		curso.setArea(cursoDetails.getArea());
		curso.setTipo(cursoDetails.getTipo());

		Curso updatedCurso = cursoRepository.save(curso);
		logger.info("Curso atualizado com sucesso.");
	    return updatedCurso;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long cursoId) {
		Curso curso = cursoRepository.findById(cursoId)
	            .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));
		cursoRepository.delete(curso);
		logger.info("Curso deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
	
}
