package br.edu.ufab.BibliotecaUFABProjetoRL.repositories.itens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufab.BibliotecaUFABProjetoRL.models.entities.itens.ItemDoAcervo;

@Repository
public interface ItemDoAcervoRepository extends JpaRepository<ItemDoAcervo, Long> {
	

}

