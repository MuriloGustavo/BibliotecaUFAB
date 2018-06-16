package br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotEmpty;



import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeAnais;

/**
 * Classe responsável por definir a organização dos dados dos objetos
 * Anais.
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */

@Entity
public class Anais extends ItemDoAcervo {

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoDeAnais tipo;

	@NotNull
	@NotEmpty
	private String nomeDoCongresso;

	@NotNull
	private int anoDePublicacao;

	@NotNull
	@NotEmpty
	private String local;

	/**
	 * Retorna a variável tipo da entidade Anais
	 * 
	 * @return tipo
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public TipoDeAnais getTipo() {
		return tipo;
	}

	/**
	 * Inicializa a variável tipo da entidade Anais
	 * 
	 * @param tipo
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setTipo(TipoDeAnais tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna a variável nomeDoCongresso da entidade Anais
	 * 
	 * @return nomeDoCongresso
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public String getNomeDoCongresso() {
		return nomeDoCongresso;
	}

	/**
	 * Inicializa a variável nomeDoCongresso da entidade Anais
	 * 
	 * @param nomeDoCongresso
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setNomeDoCongresso(String nomeDoCongresso) {
		this.nomeDoCongresso = nomeDoCongresso;
	}

	/**
	 * Retorna a variável anoDePublicacao da entidade Anais
	 * 
	 * @return anoDePublicacao
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public int getAnoDePublicacao() {
		return anoDePublicacao;
	}

	/**
	 * Inicializa a variável anoDePublicacao da entidade Anais
	 * 
	 * @param anoDePublicacao
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setAnoDePublicacao(int anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	/**
	 * Retorna a variável local da entidade Anais
	 * 
	 * @return local
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * Inicializa a variável local da entidade Anais
	 * 
	 * @param local
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * Retorna a variável hashcode da entidade Anais
	 * 
	 * @return result
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + anoDePublicacao;
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((nomeDoCongresso == null) ? 0 : nomeDoCongresso.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	/**
	 * Verifica a igualdade de atributos com um objeto recebido como parâmetro
	 * 
	 * @param obj
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anais other = (Anais) obj;
		if (anoDePublicacao != other.anoDePublicacao)
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (nomeDoCongresso == null) {
			if (other.nomeDoCongresso != null)
				return false;
		} else if (!nomeDoCongresso.equals(other.nomeDoCongresso))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}
