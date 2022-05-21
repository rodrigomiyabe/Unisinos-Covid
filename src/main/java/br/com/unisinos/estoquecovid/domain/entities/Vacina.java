package br.com.unisinos.estoquecovid.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Objects;


@ToString
@Getter
@Setter
@Entity
@Table(name = "vacina")
//Trocar para vacina
public class Vacina implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricaoMaterial;

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vacina vacina = (Vacina) o;
        return id != null && Objects.equals(id, vacina.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

