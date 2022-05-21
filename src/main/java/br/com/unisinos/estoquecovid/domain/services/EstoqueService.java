package br.com.unisinos.estoquecovid.domain.services;

import br.com.unisinos.estoquecovid.domain.entities.Estoque;
import br.com.unisinos.estoquecovid.domain.repositories.EstoqueRepository;
import br.com.unisinos.estoquecovid.domain.repositories.VacinaRepository;
import br.com.unisinos.estoquecovid.dto.EstoqueDto;
import br.com.unisinos.estoquecovid.dto.Inventario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Optional<Estoque> buscaEstoqueVacina(final Integer vacina, final String anomes) {
        return this.estoqueRepository.findByVacina_IdAndAnomes(vacina, anomes);
    }

    public Estoque salvar(final Estoque estoque) {
        return this.estoqueRepository.save(estoque);
    }

    public Inventario buscaEstoqueAnoMes(final String anomes) {
        final List<Estoque> lista = estoqueRepository.findAllByAnomes(anomes);
        final List<EstoqueDto> listaDto = new ArrayList<>(lista.size());

        for (final Estoque estoque : lista) {
            listaDto.add(new EstoqueDto(
                    estoque.getVacina().getId() + " - " + estoque.getVacina().getDescricaoMaterial(),
                    estoque.getQtde()
            ));
        }
        return new Inventario(anomes, listaDto);
    }

    public Integer estoquePorId(final Integer id){
        return this.estoqueRepository.estoquePorId(id);
    }

}
