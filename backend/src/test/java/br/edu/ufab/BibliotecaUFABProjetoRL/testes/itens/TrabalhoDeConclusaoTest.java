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


import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.TrabalhoDeConclusaoController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.TrabalhoDeConclusao;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeTrabalhoDeConclusao;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeItemDoAcervo;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.TrabalhoDeConclusaoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TrabalhoDeConclusaoController.class)
public class TrabalhoDeConclusaoTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private TrabalhoDeConclusaoRepository trabalhoDeConclusaoRepository;
	
	@Test
	public void buscarTest() throws Exception {
		TrabalhoDeConclusao trabalhoDeConclusao1 = new TrabalhoDeConclusao();
		trabalhoDeConclusao1.setAutores("Taynar Sousa");
		trabalhoDeConclusao1.setId((long) 1);
		trabalhoDeConclusao1.setAnoDeDefesa(2017);
		trabalhoDeConclusao1.setLocal("UEPB");
		trabalhoDeConclusao1.setOrientadores("Thiago Batista");
		trabalhoDeConclusao1.setTitulo("TCC de Taynar");
		trabalhoDeConclusao1.setTipo(TipoDeTrabalhoDeConclusao.MONOGRAFIA);
		trabalhoDeConclusao1.setItem_tipo(TipoDeItemDoAcervo.TRABALHOSDECONCLUSAO);
		
		List<TrabalhoDeConclusao> mockTrabalhoDeConclusao = Arrays.asList(trabalhoDeConclusao1);	
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockTrabalhoDeConclusaoJSON = ow.writeValueAsString(mockTrabalhoDeConclusao);
		
		when(trabalhoDeConclusaoRepository.findAll()).thenReturn(mockTrabalhoDeConclusao);
		mock.perform(get("/tccs")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockTrabalhoDeConclusaoJSON));
	}
	
	@Test
	public void salvarTest() throws Exception {
		TrabalhoDeConclusao trabalhoDeConclusao1 = new TrabalhoDeConclusao();
		trabalhoDeConclusao1.setAutores("Taynar Sousa");
		trabalhoDeConclusao1.setId((long) 1);
		trabalhoDeConclusao1.setAnoDeDefesa(2017);
		trabalhoDeConclusao1.setLocal("UEPB");
		trabalhoDeConclusao1.setOrientadores("Thiago Batista");
		trabalhoDeConclusao1.setTitulo("TCC de Taynar");
		trabalhoDeConclusao1.setTipo(TipoDeTrabalhoDeConclusao.MONOGRAFIA);
		trabalhoDeConclusao1.setItem_tipo(TipoDeItemDoAcervo.TRABALHOSDECONCLUSAO);
		
		List<TrabalhoDeConclusao> mockTrabalhoDeConclusao = Arrays.asList(trabalhoDeConclusao1);

		when(trabalhoDeConclusaoRepository.save(any(TrabalhoDeConclusao.class))).thenReturn(mockTrabalhoDeConclusao.get(0));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockTrabalhoDeConclusaoJSON = ow.writeValueAsString(mockTrabalhoDeConclusao.get(0));
		
		mock.perform(post("/tccs")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockTrabalhoDeConclusaoJSON))
		.andExpect(status().is(200))
		.andExpect(content().json(mockTrabalhoDeConclusaoJSON));
		verify(trabalhoDeConclusaoRepository).save(any(TrabalhoDeConclusao.class));		
	}
	
	@Test
	public void removeTrabalhoDeConclusao() throws Exception {
		TrabalhoDeConclusao trabalhoDeConclusao1 = new TrabalhoDeConclusao();
		trabalhoDeConclusao1.setAutores("Taynar Sousa");
		trabalhoDeConclusao1.setId((long) 1);
		trabalhoDeConclusao1.setAnoDeDefesa(2017);
		trabalhoDeConclusao1.setLocal("UEPB");
		trabalhoDeConclusao1.setOrientadores("Thiago Batista");
		trabalhoDeConclusao1.setTitulo("TCC de Taynar");
		trabalhoDeConclusao1.setTipo(TipoDeTrabalhoDeConclusao.MONOGRAFIA);
		trabalhoDeConclusao1.setItem_tipo(TipoDeItemDoAcervo.TRABALHOSDECONCLUSAO);
		
		List<TrabalhoDeConclusao> mockTrabalhoDeConclusao = Arrays.asList(trabalhoDeConclusao1);

		when(trabalhoDeConclusaoRepository.save(any(TrabalhoDeConclusao.class))).thenReturn(mockTrabalhoDeConclusao.get(0));
	
		when(trabalhoDeConclusaoRepository.getOne((long) 1)).thenReturn(trabalhoDeConclusao1);
		trabalhoDeConclusaoRepository.deleteById((long) 1);
		verify(trabalhoDeConclusaoRepository, times(1)).deleteById((long) 1);
		verifyNoMoreInteractions(trabalhoDeConclusaoRepository);
	}
	
}