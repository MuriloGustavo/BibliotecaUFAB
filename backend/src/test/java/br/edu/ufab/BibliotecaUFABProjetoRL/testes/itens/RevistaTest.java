package br.edu.ufab.BibliotecaUFABProjetoRL.testes.itens;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
//import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.RevistaController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Revista;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeItemDoAcervo;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.RevistaRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RevistaController.class)
public class RevistaTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private RevistaRepository RevistaRepository;
	
	@Test
	public void buscarTest() throws Exception {
		Revista revista1 = new Revista();
		revista1.setAutores("Taynar Sousa");
		//revista1.setDataDePublicacao(Date.valueOf("2008-04-20") );
		revista1.setNumeroDePaginas(35);
		revista1.setTitulo("Revista anual do software");
		revista1.setEdicao(2);
		revista1.setId((long) 1);
		revista1.setItem_tipo(TipoDeItemDoAcervo.REVISTA);
		
		
		List<Revista> mockRevista = Arrays.asList(revista1);
		
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockRevistaJSON = ow.writeValueAsString(mockRevista);
		
		when(RevistaRepository.findAll()).thenReturn(mockRevista);
		mock.perform(get("/revistas")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockRevistaJSON));
	}
	
	@Test
	public void salvarTest() throws Exception {
		Revista revista1 = new Revista();
		revista1.setAutores("Taynar Sousa");
		//revista1.setDataDePublicacao(Date.valueOf("2008-04-20"));
		revista1.setNumeroDePaginas(35);
		revista1.setTitulo("Revista anual do software");
		revista1.setEdicao(2);
		revista1.setId((long) 1);
		revista1.setItem_tipo(TipoDeItemDoAcervo.REVISTA);
		
		List<Revista> mockRevista = Arrays.asList(revista1);

		when(RevistaRepository.save(any(Revista.class))).thenReturn(mockRevista.get(0));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockRevistaJSON = ow.writeValueAsString(mockRevista.get(0));
		
		mock.perform(post("/revistas")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockRevistaJSON))
		.andExpect(status().is(200))
		.andExpect(content().json(mockRevistaJSON));
		verify(RevistaRepository).save(any(Revista.class));		
	}
	@Test
	public void removeRevista() throws Exception {
		Revista revista1 = new Revista();
		revista1.setAutores("Taynar Sousa");
		//revista1.setDataDePublicacao(Date.valueOf("2008-04-20"));
		revista1.setNumeroDePaginas(35);
		revista1.setTitulo("Revista anual do software");
		revista1.setEdicao(2);
		revista1.setId((long) 1);
		revista1.setItem_tipo(TipoDeItemDoAcervo.REVISTA);
		
		List<Revista> mockRevista = Arrays.asList(revista1);
		
		when(RevistaRepository.save(any(Revista.class))).thenReturn(mockRevista.get(0));

		when(RevistaRepository.getOne((long) 1)).thenReturn(revista1);
		RevistaRepository.deleteById((long) 1);
		verify(RevistaRepository, times(1)).deleteById((long) 1);
		verifyNoMoreInteractions(RevistaRepository);
	}
	
}