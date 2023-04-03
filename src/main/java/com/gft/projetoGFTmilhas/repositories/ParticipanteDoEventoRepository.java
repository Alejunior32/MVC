package com.gft.projetoGFTmilhas.repositories;

import com.gft.projetoGFTmilhas.models.Grupo;
import com.gft.projetoGFTmilhas.models.ParticipanteDoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteDoEventoRepository extends JpaRepository<ParticipanteDoEvento, Long> {
    List<ParticipanteDoEvento> findByGrupo(Grupo grupo);
}
