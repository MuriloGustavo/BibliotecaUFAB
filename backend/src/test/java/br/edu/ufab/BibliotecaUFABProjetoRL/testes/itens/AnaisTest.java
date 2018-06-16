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

import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.AnaisController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Anais;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeAnais;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeItemDoAcervo;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.AnaisRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AnaisController.class)
public class AnaisTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private AnaisRepository anaisRepository;
	
	@Test
	public void buscarTest() throws Exception {
		Anais anais1 = new Anais();
		anais1.setAutores("Murilo Gustavo");
		anais1.setItem_tipo(TipoDeItemDoAcervo.ANAISDECONGRESSO);
		anais1.setId((long) 1);
		anais1.setAnoDePublicacao(2007);
		anais1.setLocal("Campina Grande");
		anais1.setNomeDoCongresso("ENECT");
		anais1.setTipo(TipoDeAnais.ARTIGO);
		anais1.setTitulo("Projeto Web com Spring boot e VueJS");
		
		List<Anais> mockAnais = Arrays.asList(anais1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockAnaisJSON = ow.writeValueAsString(mockAnais);
		
		when(anaisRepository.findAll()).thenReturn(mockAnais);
		mock.perform(get("/anais")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockAnaisJSON));
	}
	
	@Test
	public void salvarTest() throws Exception {
		Anais anais1 = new Anais();
		anais1.setAutores("Murilo Gustavo");
		anais1.setItem_tipo(TipoDeItemDoAcervo.ANAISDECONGRESSO);
		anais1.setId((long) 1);
		anais1.setAnoDePublicacao(2007);
		anais1.setLocal("Campina Grande");
		anais1.setNomeDoCongresso("ENECT");
		anais1.setTipo(TipoDeAnais.ARTIGO);
		anais1.setTitulo("Projeto Web com Spring boot e VueJS");
		
		List<Anais> mockAnais = Arrays.asList(anais1);

		when(anaisRepository.save(any(Anais.class))).thenReturn(mockAnais.get(0));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockAnaisJSON = ow.writeValueAsString(mockAnais.get(0));
		
		mock.perform(post("/anais")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockAnaisJSON))
		.andExpect(status().is(200))
		.andExpect(content().json(mockAnaisJSON));
		verify(anaisRepository).save(any(Anais.class));		
	}
	@Test
	public void removeAnais() throws Exception {
		Anais anais1 = new Anais();
		anais1.setAutores("Murilo Gustavo");
		anais1.setItem_tipo(TipoDeItemDoAcervo.ANAISDECONGRESSO);
		anais1.setId((long) 1);
		anais1.setAnoDePublicacao(2007);
		anais1.setLocal("Campina Grande");
		anais1.setNomeDoCongresso("ENECT");
		anais1.setTipo(TipoDeAnais.ARTIGO);
		anais1.setTitulo("Projeto Web com Spring boot e VueJS");
		
		List<Anais> mockAnais = Arrays.asList(anais1);

		when(anaisRepository.save(any(Anais.class))).thenReturn(mockAnais.get(0));
	
		when(anaisRepository.getOne((long) 1)).thenReturn(anais1);
		anaisRepository.deleteById((long) 1);
		verify(anaisRepository, times(1)).deleteById((long) 1);
		verifyNoMoreInteractions(anaisRepository);
	}
	
}
