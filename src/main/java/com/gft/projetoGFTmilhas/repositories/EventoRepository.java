package com.gft.projetoGFTmilhas.repositories;

import com.gft.projetoGFTmilhas.models.Atividade;
import com.gft.projetoGFTmilhas.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
