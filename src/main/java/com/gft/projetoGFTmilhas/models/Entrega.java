package com.gft.projetoGFTmilhas.models;

import javax.persistence.*;

@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Atividade atividade;

    @ManyToOne
    private ParticipanteDoEvento participante;

    private boolean entrega;


    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public ParticipanteDoEvento getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteDoEvento participante) {
        this.participante = participante;
    }
}
