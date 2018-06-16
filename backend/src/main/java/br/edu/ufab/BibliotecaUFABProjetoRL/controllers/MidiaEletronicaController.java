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
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.MidiaEletronica;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.MidiaEletronicaRepository;

@RestController
@RequestMapping("/midias")
@CrossOrigin(origins = {"http://localhost:8090"})
public class MidiaEletronicaController {
	static final Logger logger = LoggerFactory.getLogger(MidiaEletronicaController.class);

	@Autowired
	private MidiaEletronicaRepository MidiaEletronicaRepository;
	
	@GetMapping
	public List<MidiaEletronica> listar() {
		logger.info("Midias listados com sucesso.");
		return MidiaEletronicaRepository.findAll();	
	}
	
	@PostMapping
	public MidiaEletronica salvar(@Valid @RequestBody MidiaEletronica MidiaEletronica) {
		logger.info("Midia salvo com sucesso.");
		return MidiaEletronicaRepository.save(MidiaEletronica);	
	}
	
	@GetMapping("/{id}")
	public MidiaEletronica buscarById(@PathVariable(value = "id") Long MidiaEletronicaId) {
		logger.info("Midia buscado com sucesso.");
		return MidiaEletronicaRepository.findById(MidiaEletronicaId)
				.orElseThrow(() -> new ResourceNotFoundException("MidiaEletronica", "id", MidiaEletronicaId));	
	}
	
	@PutMapping("/{id}")
	public MidiaEletronica atualizar(@PathVariable(value = "id") Long MidiaEletronicaId,
	                                        @Valid @RequestBody MidiaEletronica MidiaEletronicaDetails) {
		MidiaEletronica MidiaEletronica = MidiaEletronicaRepository.findById(MidiaEletronicaId)
				.orElseThrow(() -> new ResourceNotFoundException("MidiaEletronica", "id", MidiaEletronicaId));
		
		MidiaEletronica.setAutores(MidiaEletronicaDetails.getAutores());
		MidiaEletronica.setTitulo(MidiaEletronicaDetails.getTitulo());
		MidiaEletronica.setDataDeGravacao(MidiaEletronicaDetails.getDataDeGravacao());
		MidiaEletronica.setItem_tipo(MidiaEletronicaDetails.getItem_tipo());
		MidiaEletronica.setTipo(MidiaEletronicaDetails.getTipo());

		MidiaEletronica updatedMidia = MidiaEletronicaRepository.save(MidiaEletronica);
		logger.info("Midia atualizado com sucesso.");
	    return updatedMidia;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable(value = "id") Long MidiaEletronicaId) {
		MidiaEletronica MidiaEletronica = MidiaEletronicaRepository.findById(MidiaEletronicaId)
	            .orElseThrow(() -> new ResourceNotFoundException("MidiaEletronica", "id", MidiaEletronicaId));
		MidiaEletronicaRepository.delete(MidiaEletronica);
		logger.info("Midia deletado com sucesso.");
	    return ResponseEntity.ok().build();
	}
}
