package br.com.unisinos.estoquecovid.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@Entity
@Table(name = "operacao")
public class Operacao {

    @Id
    @Column(name = "id", nullable = false, length = 25)
    private String id;

    @Column(name = "indice")
    private Integer indice;


    //TODO Reverse Engineering! Migrate other columns to the entity
}