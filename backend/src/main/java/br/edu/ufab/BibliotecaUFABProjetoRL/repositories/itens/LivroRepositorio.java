package br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.Livro;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {

}
