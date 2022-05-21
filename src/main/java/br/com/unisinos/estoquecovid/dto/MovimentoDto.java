package br.com.unisinos.estoquecovid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class MovimentoDto implements Serializable {

    private Integer vacina;
    private String operacao;
    private Integer qtde;

}
