package br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeItemDoAcervo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemDoAcervo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nullable
	@Enumerated(EnumType.STRING)
	private TipoDeItemDoAcervo item_tipo;
	
	@NotNull
	@NotEmpty
	private String titulo;
	
	@Nullable
	private String autores;

	/**
	 * Retorna a variável id da entidade ItemDoAcervo
	 * 
	 * @return id
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Inicializa a variável id da entidade ItemDoAcervo
	 * 
	 * @param id
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retorna a variável item_tipo da entidade ItemDoAcervo
	 * 
	 * @return item_Tipo
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public TipoDeItemDoAcervo getItem_tipo() {
		return item_tipo;
	}

	/**
	 * Inicializa a variável item_tipo da entidade ItemDoAcervo
	 * 
	 * @param item_tipo
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setItem_tipo(TipoDeItemDoAcervo item_tipo) {
		this.item_tipo = item_tipo;
	}

	/**
	 * Retorna a variável titulo da entidade ItemDoAcervo
	 * 
	 * @return titulo
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Inicializa a variável titulo da entidade ItemDoAcervo
	 * 
	 * @param titulo
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Retorna a variável autores da entidade ItemDoAcervo
	 * 
	 * @return autores
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public String getAutores() {
		return autores;
	}

	/**
	 * Inicializa a variável autores da entidade ItemDoAcervo
	 * 
	 * @param autores
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setAutores(String autores) {
		this.autores = autores;
	}

}

