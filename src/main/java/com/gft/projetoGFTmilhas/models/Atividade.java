package com.gft.projetoGFTmilhas.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Digite um nome")
    private String nomeAtividade;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeInicioAtividade;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeEntregaAtividade;

    @ManyToOne
    private Evento evento;

    @OneToMany(mappedBy = "atividade")
    private Set<Entrega> listaDeEntrega;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public LocalDate getDataDeInicioAtividade() {
        return dataDeInicioAtividade;
    }

    public void setDataDeInicioAtividade(LocalDate dataDeInicioAtividade) {
        this.dataDeInicioAtividade = dataDeInicioAtividade;
    }

    public LocalDate getDataDeEntregaAtividade() {
        return dataDeEntregaAtividade;
    }

    public void setDataDeEntregaAtividade(LocalDate dataDeEntregaAtividade) {
        this.dataDeEntregaAtividade = dataDeEntregaAtividade;
    }

    public Set<Entrega> getListaDeEntrega() {
        return listaDeEntrega;
    }

    public void setListaDeEntrega(Set<Entrega> listaDeEntrega) {
        this.listaDeEntrega = listaDeEntrega;
    }

    @Override
    public String toString() {
        return  nomeAtividade;
    }
}
