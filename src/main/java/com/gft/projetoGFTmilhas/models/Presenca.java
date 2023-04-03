package com.gft.projetoGFTmilhas.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ParticipanteDoEvento participanteDoEvento;

    private boolean presente;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipanteDoEvento getParticipanteDoEvento() {
        return participanteDoEvento;
    }

    public void setParticipanteDoEvento(ParticipanteDoEvento participanteDoEvento) {
        this.participanteDoEvento = participanteDoEvento;
    }

    public boolean getPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
