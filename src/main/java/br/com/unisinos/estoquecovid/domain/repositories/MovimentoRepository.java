package br.com.unisinos.estoquecovid.domain.repositories;

import br.com.unisinos.estoquecovid.domain.entities.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {
}