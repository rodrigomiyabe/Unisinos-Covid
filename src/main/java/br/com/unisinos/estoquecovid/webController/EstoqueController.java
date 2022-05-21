package br.com.unisinos.estoquecovid.webController;
import br.com.unisinos.estoquecovid.domain.entities.Vacina;
import br.com.unisinos.estoquecovid.domain.services.EstoqueService;
import br.com.unisinos.estoquecovid.domain.services.MovimentoService;
import br.com.unisinos.estoquecovid.dto.EstoqueDto;
import br.com.unisinos.estoquecovid.dto.Inventario;
import br.com.unisinos.estoquecovid.dto.MovimentoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    private final MovimentoService service;
    private final EstoqueService estoqueService;

    public EstoqueController(MovimentoService service, EstoqueService estoqueService) {
        this.service = service;
        this.estoqueService = estoqueService;
    }


    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resposta> movimentaEstoque(@RequestBody final MovimentoDto movimento) {
        try {
            this.service.registraMovimento(movimento);
            return ResponseEntity.ok(new Resposta(true, ""));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new Resposta(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/listar/{anomes}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventario> listarPorMesAno(@PathVariable String anomes) {
        return ResponseEntity.ok().body(estoqueService.buscaEstoqueAnoMes(anomes.replace("-", "/")));
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


