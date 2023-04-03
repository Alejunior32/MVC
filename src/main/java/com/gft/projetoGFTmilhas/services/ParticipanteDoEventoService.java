package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Grupo;
import com.gft.projetoGFTmilhas.models.ParticipanteDoEvento;
import com.gft.projetoGFTmilhas.repositories.ParticipanteDoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteDoEventoService {

    @Autowired
    ParticipanteDoEventoRepository participanteDoEventoRepository;

    public ParticipanteDoEvento salvarParticipante(ParticipanteDoEvento participanteDoEvento){
        return participanteDoEventoRepository.save(participanteDoEvento);
    }

    public List<ParticipanteDoEvento> listarParticipantes(){
        return participanteDoEventoRepository.findAll();
    }

    public void deletarParticipanteDoEventoPeloObjeto(ParticipanteDoEvento participanteDoEvento) {
        participanteDoEventoRepository.delete(participanteDoEvento);
    }

    public void deletarParticipanteDoEventoPeloId(Long id){
        participanteDoEventoRepository.deleteById(id);
    }

    public ParticipanteDoEvento retornaParticipanteDoEvento(Long id){
        Optional<ParticipanteDoEvento> participanteDoEvento = participanteDoEventoRepository.findById(id);
        return participanteDoEvento.get();
    }

    public List<ParticipanteDoEvento> listarParticipantesDoGrupo(Grupo grupo){
        return participanteDoEventoRepository.findByGrupo(grupo);
    }


}
