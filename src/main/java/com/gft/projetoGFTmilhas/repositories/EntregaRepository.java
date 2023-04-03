package com.gft.projetoGFTmilhas.repositories;

import com.gft.projetoGFTmilhas.models.Entrega;
import com.gft.projetoGFTmilhas.models.ParticipanteDoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByParticipante(ParticipanteDoEvento participanteDoEvento);
}
