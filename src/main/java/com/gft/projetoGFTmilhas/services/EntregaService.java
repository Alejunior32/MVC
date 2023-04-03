package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Entrega;
import com.gft.projetoGFTmilhas.models.ParticipanteDoEvento;
import com.gft.projetoGFTmilhas.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    @Autowired
    EntregaRepository entregaRepository;

    public Entrega salvarEntrega(Entrega entrega){
        return entregaRepository.save(entrega);
    }

    public List<Entrega> listarEntregasDoParticipante(ParticipanteDoEvento participanteDoEvento){
        return entregaRepository.findByParticipante(participanteDoEvento);
    }

    public void deletarEntregaPeloObjeto(Entrega entrega) {
        entregaRepository.delete(entrega);
    }
}
