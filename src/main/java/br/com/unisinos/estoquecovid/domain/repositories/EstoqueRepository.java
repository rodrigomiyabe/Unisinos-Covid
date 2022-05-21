package br.com.unisinos.estoquecovid.domain.repositories;

import br.com.unisinos.estoquecovid.domain.entities.Estoque;
import br.com.unisinos.estoquecovid.dto.EstoqueDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    Optional<Estoque> findByVacina_IdAndAnomes(final Integer vacinaId, final String anoMes);

    List<Estoque> findAllByAnomes(final String anoMes);

}
