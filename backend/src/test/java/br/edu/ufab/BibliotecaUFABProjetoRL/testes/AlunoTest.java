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

import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.AlunoController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.Aluno;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.Curso;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeCurso;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.AlunoRepository;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.CursoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AlunoController.class)
public class AlunoTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private AlunoRepository alunoRepository;
	@MockBean
	private CursoRepository cursoRepository;
	
	@Test
	public void buscarTest() throws Exception {
		Curso curso1 = new Curso();
		curso1.setArea("HUMANAS");
		curso1.setId((long) 3);
		curso1.setNome("geografia");
		curso1.setTipo(TipoDeCurso.GRADUACAO);
		cursoRepository.save(curso1);
		
		Aluno Aluno1 = new Aluno();
		Aluno1.setId((long) 3);
		Aluno1.setNome("Maria Clara");
		Aluno1.setCpf("123.456.789-00");
		Aluno1.setCurso(curso1);
		Aluno1.setNaturalidade("Campina Grande");
		Aluno1.setNomeDaMae("Josefa");
		Aluno1.setPeriodo(1);
		Aluno1.setAno("2017");
		Aluno1.setNaturalidade("Campina Grande");
		Aluno1.setEndereco("Rua do campo");
		Aluno1.setMatricula("GEG171-016");
		Aluno1.setRg("3.256.256");
		Aluno1.setTelefone("(83)99999-9999");

		List<Aluno> mockAluno = Arrays.asList(Aluno1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockAlunoJSON = ow.writeValueAsString(mockAluno);
		
		when(alunoRepository.findAll()).thenReturn(mockAluno);
		mock.perform(get("/alunos")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockAlunoJSON));
	}
	
	@Test
	public void salvarTest() throws Exception {
		Curso curso1 = new Curso();
		curso1.setArea("HUMANAS");
		curso1.setId((long) 3);
		curso1.setNome("geografia");
		curso1.setTipo(TipoDeCurso.GRADUACAO);
		cursoRepository.save(curso1);
		
		Aluno Aluno1 = new Aluno();
		Aluno1.setId((long) 3);
		Aluno1.setNome("Maria Clara");
		Aluno1.setCpf("123.456.789-00");
		Aluno1.setCurso(curso1);
		Aluno1.setNaturalidade("Campina Grande");
		Aluno1.setNomeDaMae("Josefa");
		Aluno1.setPeriodo(1);
		Aluno1.setAno("2017");
		Aluno1.setNaturalidade("Campina Grande");
		Aluno1.setEndereco("Rua do campo");
		Aluno1.setMatricula("GEG171-016");
		Aluno1.setRg("3.256.256");
		Aluno1.setTelefone("(83)99999-9999");
		Aluno1.setTelefone("(83)99999-9999");
		
		List<Aluno> mockAluno = Arrays.asList(Aluno1);

		when(alunoRepository.save(any(Aluno.class))).thenReturn(mockAluno.get(0));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockAlunoJSON = ow.writeValueAsString(mockAluno.get(0));
		
		mock.perform(post("/alunos")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockAlunoJSON))
		.andExpect(status().is(200))
		.andExpect(content().json(mockAlunoJSON));
		verify(alunoRepository).save(any(Aluno.class));		
	}

	@Test
	public void removeCurso() throws Exception {
		Curso curso1 = new Curso();
		curso1.setArea("HUMANAS");
		curso1.setId((long) 4);
		curso1.setNome("geografia");
		curso1.setTipo(TipoDeCurso.GRADUACAO);
		cursoRepository.save(curso1);
		
		Aluno Aluno1 = new Aluno();
		Aluno1.setId((long) 3);
		Aluno1.setNome("Maria Clara");
		Aluno1.setCpf("123.456.789-00");
		Aluno1.setCurso(curso1);
		Aluno1.setNaturalidade("Campina Grande");
		Aluno1.setNomeDaMae("Josefa");
		Aluno1.setPeriodo(1);
		Aluno1.setAno("2017");
		Aluno1.setNaturalidade("Campina Grande");
		Aluno1.setEndereco("Rua do campo");
		Aluno1.setMatricula("GEG171-016");
		Aluno1.setRg("3.256.256");
		Aluno1.setTelefone("(83)99999-9999");
		Aluno1.setTelefone("(83)99999-9999");
		
		List<Aluno> mockAluno = Arrays.asList(Aluno1);
		when(alunoRepository.save(any(Aluno.class))).thenReturn(mockAluno.get(0));
		
		when( alunoRepository.getOne((long) 3)).thenReturn(Aluno1);
		alunoRepository.deleteById((long) 3);
		verify(alunoRepository, times(1)).deleteById((long) 3);
		verifyNoMoreInteractions(alunoRepository);
	}
	
}
