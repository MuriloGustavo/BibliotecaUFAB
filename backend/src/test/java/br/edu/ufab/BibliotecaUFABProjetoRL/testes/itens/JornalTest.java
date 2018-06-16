package br.edu.ufab.BibliotecaUFABProjetoRL.testes.itens;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.sql.Date;
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

import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.JornalController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Jornal;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeItemDoAcervo;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.JornalRepositorio;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = JornalController.class)
public class JornalTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private JornalRepositorio jornalRepository;
	
	@Test
	public void buscarTest() throws Exception {
		Jornal Jornal1 = new Jornal();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 Date data =  new java.sql.Date(format.parse("12/03/2009").getTime());
		
		Jornal1.setAutores("Taynar Sousa");
		Jornal1.setId((long) 1);
		Jornal1.setTitulo("Correio da Paraiba");
		Jornal1.setItem_tipo(TipoDeItemDoAcervo.JORNAL);
		Jornal1.setData(data);
		
		List<Jornal> mockJornal = Arrays.asList(Jornal1);
		
		when(jornalRepository.findAll()).thenReturn(mockJornal);
		mock.perform(get("/jornais")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200));
	}
	
	@Test
	public void salvarTest() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new java.sql.Date(format.parse("12/03/2009").getTime());
		Jornal Jornal1 = new Jornal();
		Jornal1.setAutores("Taynar Sousa");
		Jornal1.setId((long) 1);
		Jornal1.setTitulo("Correio da Paraiba");
		Jornal1.setItem_tipo(TipoDeItemDoAcervo.JORNAL);
		Jornal1.setData(data);
		
		List<Jornal> mockJornal = Arrays.asList(Jornal1);
		
		when(jornalRepository.save(any(Jornal.class))).thenReturn(mockJornal.get(0));

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockJornalJSON = ow.writeValueAsString(mockJornal);
		
		when(jornalRepository.findAll()).thenReturn(mockJornal);
		mock.perform(post("/jornais")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockJornalJSON))
		.andExpect(status().is(200));
		verify(jornalRepository).save(any(Jornal.class));		
	}
	@Test
	public void removeJornal() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date data = new java.sql.Date(format.parse("12/03/2009").getTime());
		
		Jornal Jornal1 = new Jornal();
		Jornal1.setAutores("Murilo Gustavo");
		Jornal1.setId((long) 1);
		Jornal1.setTitulo("Correio da Paraiba");
		Jornal1.setItem_tipo(TipoDeItemDoAcervo.JORNAL);
		Jornal1.setData(data);
	
		when(jornalRepository.getOne((long) 1)).thenReturn(Jornal1);
		jornalRepository.deleteById((long) 1);
		verify(jornalRepository, times(1)).deleteById((long) 1);
		verifyNoMoreInteractions(jornalRepository);
	}
	
}