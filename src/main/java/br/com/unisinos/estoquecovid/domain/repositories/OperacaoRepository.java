package br.com.unisinos.estoquecovid.domain.repositories;

import br.com.unisinos.estoquecovid.domain.entities.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoRepository extends JpaRepository<Operacao, String> {
}