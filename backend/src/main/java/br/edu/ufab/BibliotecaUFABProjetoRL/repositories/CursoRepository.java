package br.edu.ufab.BibliotecaUFABProjetoRL.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.Curso;
import br.edu.ufab.BibliotecaUFABProjetoRL.models.enums.TipoDeCurso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	@Query("SELECT p FROM Curso p WHERE p.tipo = :tipo")
	public List<Curso> findByTipo(@Param("tipo") TipoDeCurso tipo);

}
