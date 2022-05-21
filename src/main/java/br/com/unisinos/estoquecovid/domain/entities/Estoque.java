package br.com.unisinos.estoquecovid.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Entity
@Table(name = "estoque")
@Getter
@Setter
public class Estoque implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;

    @Column(name = "ano_mes")
    private String anomes;

    @Column(name = "qtde")
    private Integer qtde;

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Estoque estoque = (Estoque) o;
        return id != null && Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
