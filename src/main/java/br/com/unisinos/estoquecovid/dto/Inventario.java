package br.com.unisinos.estoquecovid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Inventario implements Serializable {

    private String anomes;
    private List<EstoqueDto> itens;

}
