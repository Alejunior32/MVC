package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Evento;
import com.gft.projetoGFTmilhas.models.Grupo;
import com.gft.projetoGFTmilhas.models.ParticipanteDoEvento;
import com.gft.projetoGFTmilhas.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    ParticipanteDoEventoService participanteDoEventoService;
    @Autowired
    GrupoService grupoService;

    public Evento salvarEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos(){
        return eventoRepository.findAll();
    }

    public void deletarEvento(Long id){
        eventoRepository.deleteById(id);
    }

    public Evento retornaEvento(Long id){
        Optional<Evento> evento = eventoRepository.findById(id);

        return evento.get();
    }

    public void deletarGruposPeloEvento(Evento evento){


        for (int i = 0;i<evento.getGrupos().size();i++){
            if (evento.getGrupos().get(i).getQuantPessoas() != 0){
                for (int j = 0;j<evento.getGrupos().get(i).getParticpantes().size();j++){
                    participanteDoEventoService.deletarParticipanteDoEventoPeloObjeto(evento.getGrupos().get(j).getParticpantes().get(j));
                }
            }
           grupoService.deletarGrupoPeloObjeto(evento.getGrupos().get(i));
        }

    }

    public void deletarAtividadePeloEvento(Evento evento){
        for (int i = 0;i<evento.getAtividades().size();i++){
            atividadeService.deletarAtividadePeloObjeto(evento.getAtividades().get(i));
        }
    }


    public Long numeroDeDiasDoEvento(LocalDate dataInicio,LocalDate datafinal){
        Long periodo = ChronoUnit.DAYS.between(dataInicio,datafinal);
        return periodo;
    }
}
