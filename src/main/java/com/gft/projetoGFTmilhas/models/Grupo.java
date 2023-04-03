package com.gft.projetoGFTmilhas.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Digite um nome")
    private String nomeGrupo;
    private long quantPessoas;

    @OneToOne
    private Evento evento;

    @OneToMany(mappedBy = "grupo")
    private List<ParticipanteDoEvento> particpantes;

    @OneToOne
    private Pontuacao pontuacao;

    public Pontuacao getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
    }

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

    public List<ParticipanteDoEvento> getParticpantes() {
        return particpantes;
    }

    public void setParticpantes(List<ParticipanteDoEvento> particpantes) {
        this.particpantes = particpantes;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public long getQuantPessoas() {
        return quantPessoas;
    }

    public void setQuantPessoas(long quantPessoas) {
        this.quantPessoas = quantPessoas;
    }

    @Override
    public String toString() {
        return nomeGrupo;
    }
}
