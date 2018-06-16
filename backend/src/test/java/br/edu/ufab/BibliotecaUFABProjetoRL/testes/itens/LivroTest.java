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

import br.edu.ufab.BibliotecaUFABProjetoRL.controllers.LivroController;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Livro;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeItemDoAcervo;
import br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens.LivroRepositorio;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LivroController.class)
public class LivroTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private LivroRepositorio livroRepository;
	
	@Test
	public void buscarTest() throws Exception {
		Livro Livro1 = new Livro();
		Livro1.setAutores("Lourdes Ramalho");
		Livro1.setId((long) 1);
		Livro1.setAnoDePublicacao(2007);
		Livro1.setTitulo("As viagens");
		Livro1.setArea("poesias");
		Livro1.setTitulo("As viagens");
		Livro1.setIsbn("123456");
		Livro1.setEdicao(1);
		Livro1.setEditora("Cultura");
		Livro1.setItem_tipo(TipoDeItemDoAcervo.LIVRO);
		Livro1.setNumeroDePaginas(120);
		Livro1.setTema("Poesias e contos");
		
		List<Livro> mockLivro = Arrays.asList(Livro1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockLivroJSON = ow.writeValueAsString(mockLivro);
		
		when(livroRepository.findAll()).thenReturn(mockLivro);
		mock.perform(get("/livros")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockLivroJSON));
	}
	
	@Test
	public void salvarTest() throws Exception {
		Livro Livro1 = new Livro();
		Livro1.setAutores("Lourdes Ramalho");
		Livro1.setId((long) 1);
		Livro1.setAnoDePublicacao(2007);
		Livro1.setTitulo("As viagens");
		Livro1.setArea("poesias");
		Livro1.setTitulo("As viagens");
		Livro1.setIsbn("123456");
		Livro1.setEdicao(1);
		Livro1.setEditora("Cultura");
		Livro1.setItem_tipo(TipoDeItemDoAcervo.LIVRO);
		Livro1.setNumeroDePaginas(120);
		Livro1.setTema("Poesias e contos");
		
		List<Livro> mockLivro = Arrays.asList(Livro1);

		when(livroRepository.save(any(Livro.class))).thenReturn(mockLivro.get(0));
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockLivroJSON = ow.writeValueAsString(mockLivro.get(0));
		
		mock.perform(post("/livros")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(mockLivroJSON))
		.andExpect(status().is(200))
		.andExpect(content().json(mockLivroJSON));
		verify(livroRepository).save(any(Livro.class));		
	}
	@Test
	public void removeLivro() throws Exception {
		Livro Livro1 = new Livro();
		Livro1.setAutores("Lourdes Ramalho");
		Livro1.setId((long) 1);
		Livro1.setAnoDePublicacao(2007);
		Livro1.setTitulo("As viagens");
		Livro1.setArea("poesias");
		Livro1.setTitulo("As viagens");
		Livro1.setIsbn("123456");
		Livro1.setEdicao(1);
		Livro1.setEditora("Cultura");
		Livro1.setItem_tipo(TipoDeItemDoAcervo.LIVRO);
		Livro1.setNumeroDePaginas(120);
		Livro1.setTema("Poesias e contos");
		
		List<Livro> mockLivro = Arrays.asList(Livro1);
		
		when(livroRepository.save(any(Livro.class))).thenReturn(mockLivro.get(0));

		when(livroRepository.getOne((long) 1)).thenReturn(Livro1);
		livroRepository.deleteById((long) 1);
		verify(livroRepository, times(1)).deleteById((long) 1);
		verifyNoMoreInteractions(livroRepository);
	}
	
}