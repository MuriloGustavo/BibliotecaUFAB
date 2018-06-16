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
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.Aluno;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.AlunoRepository;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = {"http://localhost:8090"})
public class AlunoController {
	static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

	@Autowired
	private AlunoRepository AlunoRepository;
	
	@GetMapping
	public List<Aluno> listar() {
		logger.info("Alunos listados com sucesso.");
		return AlunoRepository.findAll();	
	}
	
	@PostMapping
	public Aluno salvar(@Valid @RequestBody Aluno Aluno) {
		logger.info("Aluno salvo com sucesso.");
		return AlunoRepository.save(Aluno);	
	}
	
	@GetMapping("/{id}")
	public Aluno buscarById(@PathVariable(value = "id") Long AlunoId) {
		logger.info("Aluno buscado com sucesso.");
		return AlunoRepository.findById(AlunoId)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno", "id", AlunoId));	
	}
	
	@PutMapping("/{id}")
	public Aluno atualizar(@PathVariable(value = "id") Long AlunoId,
	                                        @Valid @RequestBody Aluno AlunoDetails) {
		Aluno Aluno = AlunoRepository.findById(AlunoId)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno", "id", AlunoId));
		
		Aluno.setNome(AlunoDetails.getNome());
		Aluno.setNomeDaMae(AlunoDetails.getNomeDaMae());
		Aluno.setNaturalidade(AlunoDetails.getNaturalidade());
		Aluno.setCurso(AlunoDetails.getCurso());
		Aluno.setNome(AlunoDetails.getNome());

		Aluno updatedAluno = AlunoRepository.save(Aluno);
		logger.info("Aluno atualizado com sucesso.");
	    return updatedAluno;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long AlunoId) {
		Aluno Aluno = AlunoRepository.findById(AlunoId)
	            .orElseThrow(() -> new ResourceNotFoundException("Aluno", "id", AlunoId));

		AlunoRepository.delete(Aluno);
		logger.info("Aluno deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
}