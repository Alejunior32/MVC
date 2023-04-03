package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Evento;
import com.gft.projetoGFTmilhas.models.Grupo;
import com.gft.projetoGFTmilhas.models.ParticipanteDoEvento;
import com.gft.projetoGFTmilhas.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    List<ParticipanteDoEvento> participantes;

    public Grupo salvarGrupo(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    public List<Grupo> listarGrupos(){
        return grupoRepository.findAll();
    }

    public void deletarGrupo(Long id){
        grupoRepository.deleteById(id);
    }

    public Grupo retornaGrupo(Long id){
        Optional<Grupo> grupo = grupoRepository.findById(id);

        return grupo.get();
    }

    public void deletarGrupoPeloObjeto(Grupo grupo) {
        grupoRepository.delete(grupo);
    }

    public List<Grupo> listarGruposSemEvento(List<Grupo> grupoSemEvento){

       grupoSemEvento = new ArrayList<>();

        for (Grupo grupo: grupoSemEvento) {
            if (grupo.getEvento() != null){
                grupoSemEvento.remove(grupo);
            }
        }

        return grupoSemEvento;
    }

    public List<Grupo> listarGruposDoEvento(Evento evento){
        return grupoRepository.findByEvento(evento);
    }


}
