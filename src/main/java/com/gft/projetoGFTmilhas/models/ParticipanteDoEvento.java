package com.gft.projetoGFTmilhas.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class ParticipanteDoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Digite um nome")
    private String nome;
    private String nivel;

    @ManyToOne
    private Grupo grupo;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 4,max = 4,message = "Devem ser quatro letras")
    private String quatroLetras;

    @OneToMany(mappedBy = "participanteDoEvento")
   private List<Presenca> listaDePresenca;

    @OneToMany(mappedBy = "participante")
    private List<Entrega> listaDeEntrega;

    public List<Entrega> getListaDeEntrega() {
        return listaDeEntrega;
    }

    public void setListaDeEntrega(List<Entrega> listaDeEntrega) {
        this.listaDeEntrega = listaDeEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuatroLetras() {
        return quatroLetras;
    }

    public void setQuatroLetras(String quatroLetras) {
        this.quatroLetras = quatroLetras;
    }

    public List<Presenca> getListaDePresenca() {
        return listaDePresenca;
    }

    public void setListaDePresenca(List<Presenca> listaDePresenca) {
        this.listaDePresenca = listaDePresenca;
    }

    @Override
    public String toString() {
        return nome;
    }


}
