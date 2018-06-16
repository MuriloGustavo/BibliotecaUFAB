package br.edu.ufab.BibliotecaUFABProjetoRL.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
