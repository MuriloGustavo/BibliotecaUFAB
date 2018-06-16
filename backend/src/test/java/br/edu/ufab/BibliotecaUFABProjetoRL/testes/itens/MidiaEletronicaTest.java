package br.edu.ufab.BibliotecaUFABProjetoRL.testes.itens;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
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

import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.MidiaEletronicaController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.MidiaEletronica;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeItemDoAcervo;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeMidia;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.MidiaEletronicaRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MidiaEletronicaController.class)
public class MidiaEletronicaTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private MidiaEletronicaRepository MidiaEletronicaRepository;
	
	@Test
	public void buscarTest() throws Exception {
		MidiaEletronica midiaEletronica1 = new MidiaEletronica();
		midiaEletronica1.setAutores("Raça negra");
		midiaEletronica1.setDataDeGravacao(Date.valueOf("2008-04-20") );
		midiaEletronica1.setId((long) 1);
		midiaEletronica1.setItem_tipo(TipoDeItemDoAcervo.MIDIAELETRONICA);
		midiaEletronica1.setTipo(TipoDeMidia.DVD);
		midiaEletronica1.setTitulo("só sucessos");
		
		List<MidiaEletronica> mockMidiaEletronica = Arrays.asList(midiaEletronica1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockMidiaEletronicaJSON = ow.writeValueAsString(mockMidiaEletronica);
		
		when(MidiaEletronicaRepository.findAll()).thenReturn(mockMidiaEletronica);
		mock.perform(get("/midia")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockMidiaEletronicaJSON));
	}
	
	@Test
	public void salvarTest() throws Exception {
		MidiaEletronica midiaEletronica1 = new MidiaEletronica();
		midiaEletronica1.setAutores("Raça negra");
		midiaEletronica1.setDataDeGravacao(Date.valueOf("2008-04-20"));
		midiaEletronica1.setId((long) 1);
		midiaEletronica1.setItem_tipo(TipoDeItemDoAcervo.MIDIAELETRONICA);
		midiaEletronica1.setTipo(TipoDeMidia.DVD);
		midiaEletronica1.setTitulo("só sucessos");
		
		List<MidiaEletronica> mockMidiaEletronica = Arrays.asList(midiaEletronica1);

		when(MidiaEletronicaRepository.save(any(MidiaEletronica.class))).thenReturn(mockMidiaEletronica.get(0));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockMidiaEletronicaJSON = ow.writeValueAsString(mockMidiaEletronica.get(0));
		
		mock.perform(post("/midia")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockMidiaEletronicaJSON))
		.andExpect(status().is(200))
		.andExpect(content().json(mockMidiaEletronicaJSON));
		verify(MidiaEletronicaRepository).save(any(MidiaEletronica.class));		
	}

	public void removeMidiaEletronica() throws Exception {
	
		mock.perform(delete("/midia" + "/{id}", new Long(2)))
			.andExpect(status().is(200));
	}
	
}