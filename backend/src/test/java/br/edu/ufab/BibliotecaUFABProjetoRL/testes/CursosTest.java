package br.edu.ufab.BibliotecaUFABProjetoRL.testes;

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

import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.CursoController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.Curso;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeCurso;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.CursoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CursoController.class)
public class CursosTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private CursoRepository cursoRepository;
	
	@Test
	public void buscarTest() throws Exception {
		Curso curso1 = new Curso();
		curso1.setArea("HUMANAS");
		curso1.setId((long) 3);
		curso1.setNome("geografia");
		curso1.setTipo(TipoDeCurso.GRADUACAO);
		
		List<Curso> mockcurso = Arrays.asList(curso1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockCursoJSON = ow.writeValueAsString(mockcurso);
		
		when(cursoRepository.findAll()).thenReturn(mockcurso);
		mock.perform(get("/cursos")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockCursoJSON));
	}
	
	@Test
	public void salvarTest() throws Exception {
		Curso curso1 = new Curso();
		curso1.setArea("HUMANAS");
		curso1.setId((long) 2);
		curso1.setNome("geografia");
		curso1.setTipo(TipoDeCurso.GRADUACAO);
		
		List<Curso> mockcurso = Arrays.asList(curso1);

		when(cursoRepository.save(any(Curso.class))).thenReturn(mockcurso.get(0));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockCursoJSON = ow.writeValueAsString(mockcurso.get(0));
		
		mock.perform(post("/cursos")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockCursoJSON))
		.andExpect(status().is(200))
		.andExpect(content().json(mockCursoJSON));
		verify(cursoRepository).save(any(Curso.class));		
	}
	
	@Test
	public void removeCurso() throws Exception {
		Curso curso1 = new Curso();
		curso1.setArea("HUMANAS");
		curso1.setId((long) 2);
		curso1.setNome("geografia");
		curso1.setTipo(TipoDeCurso.GRADUACAO);
		
		List<Curso> mockcurso = Arrays.asList(curso1);

		when(cursoRepository.save(any(Curso.class))).thenReturn(mockcurso.get(0));
		
		when(cursoRepository.getOne((long) 2)).thenReturn(curso1);
		cursoRepository.deleteById((long) 2);
		verify(cursoRepository, times(1)).deleteById((long) 2);
		verifyNoMoreInteractions(cursoRepository);
	}
	
}