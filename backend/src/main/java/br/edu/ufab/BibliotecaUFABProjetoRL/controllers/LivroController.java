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
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufab.BibliotecaUFABProjetoRL.exception.ResourceNotFoundException;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Livro;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.LivroRepositorio;

/**
 * Classe responsável por manipular as informações dos objetos tipo livro.
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = {"http://localhost:8090"})
public class LivroController {

	static final Logger logger = LoggerFactory.getLogger(LivroController.class);

	@Autowired
	private LivroRepositorio livroRepository;
	
	@GetMapping
	public Iterable<Livro> listar() {
		logger.info("Livros listados com sucesso.");
		return livroRepository.findAll();	
	}
	
	@PostMapping
	public Livro salvar(@Valid @RequestBody Livro livro) {
		logger.info("Livro salvo com sucesso.");
		return livroRepository.save(livro);	
	}
	
	@GetMapping("/{id}")
	public Livro buscarById(@PathVariable(value = "id") Long livroId) {
		logger.info("Livro buscado com sucesso.");
		return livroRepository.findById(livroId)
				.orElseThrow(() -> new ResourceNotFoundException("livro", "id", livroId));	
	}
	
	@PutMapping("/{id}")
	public Livro atualizar(@PathVariable(value = "id") Long livroId,
	                                        @Valid @RequestBody Livro livroDetails) {
		Livro livro = livroRepository.findById(livroId)
				.orElseThrow(() -> new ResourceNotFoundException("livro", "id", livroId));
		
		livro.setAnoDePublicacao(livroDetails.getAnoDePublicacao());
		livro.setArea(livroDetails.getArea());
		livro.setAutores(livroDetails.getAutores());
		livro.setEdicao(livroDetails.getEdicao());
		livro.setEditora(livroDetails.getEditora());
		livro.setIsbn(livroDetails.getIsbn());
		livro.setItem_tipo(livroDetails.getItem_tipo());
		livro.setNumeroDePaginas(livroDetails.getNumeroDePaginas());
		livro.setTema(livroDetails.getTema());
		livro.setTitulo(livroDetails.getTitulo());

		Livro updatedNote = livroRepository.save(livro);
		logger.info("Livro atualizado com sucesso.");
	    return updatedNote;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long livroId) {
		Livro livro = livroRepository.findById(livroId)
	            .orElseThrow(() -> new ResourceNotFoundException("livro", "id", livroId));
		livroRepository.delete(livro);
		logger.info("Livro deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
	
}
