package br.com.unisinos.estoquecovid.webController;

import br.com.unisinos.estoquecovid.domain.entities.Vacina;
import br.com.unisinos.estoquecovid.domain.services.EstoqueService;
import br.com.unisinos.estoquecovid.domain.services.VacinaServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/vacina")
public class VacinaController {

    private final VacinaServices vacinaServices;
    private final EstoqueService estoqueService;

    public VacinaController(VacinaServices vacinaServices, EstoqueService estoqueService) {
        this.vacinaServices = vacinaServices;
        this.estoqueService = estoqueService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Vacina> cadastro(@RequestBody Vacina vacina) {
        return ResponseEntity.ok(vacinaServices.cadastrar(vacina));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Vacina>> listarTodos(){
        return ResponseEntity.ok(vacinaServices.lista());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Integer id){
     if(!vacinaServices.existePorId(id)){
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vacina inexistente!");
     }else if(vacinaServices.existePorId(id) && estoqueService.estoquePorId(id)>0){
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vacina não pode ser deletada! Vacina com quantidade maior que 0");
     } else
         vacinaServices.excluir(id);
     return ResponseEntity.status(HttpStatus.OK).body("Salvo com sucesso!");
    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    private static class Resposta {
        private boolean ok;
        private String erro;
    }
}
