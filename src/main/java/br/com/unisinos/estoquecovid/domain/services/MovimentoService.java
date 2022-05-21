package br.com.unisinos.estoquecovid.domain.services;

import br.com.unisinos.estoquecovid.domain.entities.Estoque;
import br.com.unisinos.estoquecovid.domain.entities.Movimento;
import br.com.unisinos.estoquecovid.domain.entities.Operacao;
import br.com.unisinos.estoquecovid.domain.entities.Vacina;
import br.com.unisinos.estoquecovid.domain.repositories.MovimentoRepository;
import br.com.unisinos.estoquecovid.dto.MovimentoDto;
import br.com.unisinos.estoquecovid.exception.OperacaoNaoEncontradaException;
import br.com.unisinos.estoquecovid.exception.SaldoNegativoException;
import br.com.unisinos.estoquecovid.exception.VacinaNaoEncontradaException;
import br.com.unisinos.estoquecovid.exception.ValorNaoPermitidoException;
import br.com.unisinos.estoquecovid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class MovimentoService {

    private final MovimentoRepository repository;
    private final EstoqueService estoqueService;
    private final VacinaServices vacinaService;
    private final OperacaoService operacaoService;


    @Autowired
    public MovimentoService(MovimentoRepository repository, EstoqueService estoqueService, VacinaServices vacinaService, OperacaoService operacaoService) {
        this.repository = repository;
        this.estoqueService = estoqueService;
        this.vacinaService = vacinaService;
        this.operacaoService = operacaoService;
    }

    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = {Exception.class, RuntimeException.class})
    void atualizaEstoque(final Movimento movimento) throws SaldoNegativoException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        final String anoMes = movimento.getDataMovimento().format(formatter);

        Estoque estoque = null;
        Optional<Estoque> estoqueOptional = this.estoqueService.buscaEstoqueVacina(movimento.getVacina().getId(), anoMes);

        int saldoAtual = 0;

        if (estoqueOptional.isPresent()) {
            estoque = estoqueOptional.get();
            saldoAtual = estoque.getQtde();
        } else {
            estoque = new Estoque();
            estoque.setAnomes(anoMes);
            estoque.setVacina(movimento.getVacina());
        }

        int saldoResultante = saldoAtual + (movimento.getQtde() * movimento.getOperacao().getIndice());

        if (saldoResultante < 0) {
            String erro = StringUtils.formata("Vacina %s - %s ficará com saldo negativo. O saldo atual é: %s. Movimento não realizado!", movimento.getVacina().getId(), movimento.getVacina().getDescricaoMaterial(), saldoAtual);
            throw new SaldoNegativoException(erro, saldoAtual);
        }

        estoque.setQtde(saldoResultante);
        estoqueService.salvar(estoque);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, RuntimeException.class})
    public void registraMovimento(final MovimentoDto movimento) throws SaldoNegativoException, VacinaNaoEncontradaException, OperacaoNaoEncontradaException, ValorNaoPermitidoException {
        final Vacina vacina = vacinaService
                .buscaPorIr(movimento.getVacina())
                .orElseThrow(() -> new VacinaNaoEncontradaException("Vacina com id " + movimento.getVacina() + " não encontrada!"));

        final Operacao operacao = operacaoService
                .buscaPorId(movimento.getOperacao())
                .orElseThrow(() -> new OperacaoNaoEncontradaException("Operação " + movimento.getOperacao() + " não encontrada!"));

        if (movimento.getQtde() <= 0) {
            final String erro = StringUtils.formata("O valor informado %s não é permitido. Só serão permitidos valores maiores que 0.", movimento.getQtde());
            throw new ValorNaoPermitidoException(erro, movimento.getQtde());
        }

        final Movimento novoMovimento = new Movimento();
        novoMovimento.setDataMovimento(LocalDateTime.now());
        novoMovimento.setOperacao(operacao);
        novoMovimento.setVacina(vacina);
        novoMovimento.setQtde(movimento.getQtde());
        repository.save(novoMovimento);
        atualizaEstoque(novoMovimento);
    }

}
