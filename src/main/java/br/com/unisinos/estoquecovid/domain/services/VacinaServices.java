package br.com.unisinos.estoquecovid.domain.services;


import br.com.unisinos.estoquecovid.domain.entities.Vacina;
import br.com.unisinos.estoquecovid.domain.repositories.VacinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class VacinaServices {

    VacinaRepository vacinaRepository;

    public VacinaServices(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    public Optional<Vacina> buscaPorIr(final Integer id) {
        return this.vacinaRepository.findById(id);
    }

     public List<Vacina> lista(){
        return vacinaRepository.findAll();
    }

    public Vacina cadastrar(Vacina vacina){
        return vacinaRepository.save(vacina);
    }

     public String excluir(Integer id){
        vacinaRepository.deleteById(id);
        return "OK";
     }

    public boolean existePorId(Integer id){
        return this.vacinaRepository.existsById(id);
    }

    public Integer existeQuantidadePorID(Integer id){
        return this.vacinaRepository.
    }


}



