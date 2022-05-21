package br.com.unisinos.estoquecovid.domain.repositories;

import br.com.unisinos.estoquecovid.domain.entities.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacinaRepository extends JpaRepository<Vacina,Integer> {

    List<Vacina> findMaterialByDescricaoMaterial(String descricaoMaterial);

}
