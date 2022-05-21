package br.com.unisinos.estoquecovid.domain.services;

import br.com.unisinos.estoquecovid.domain.entities.Operacao;
import br.com.unisinos.estoquecovid.domain.repositories.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class OperacaoService {

    private final OperacaoRepository repository;

    @Autowired
    public OperacaoService(OperacaoRepository repository) {
        this.repository = repository;
    }

    public Optional<Operacao> buscaPorId(final String id) {
        return this.repository.findById(id);
    }

}
