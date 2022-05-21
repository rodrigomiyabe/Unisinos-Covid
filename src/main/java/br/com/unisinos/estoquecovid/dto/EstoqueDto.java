package br.com.unisinos.estoquecovid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EstoqueDto implements Serializable {

    //private String anoMes;
    private String vacina;
    private Integer quantidade;
}
