package com.gft.projetoGFTmilhas.repositories;

import com.gft.projetoGFTmilhas.models.Evento;
import com.gft.projetoGFTmilhas.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo,Long> {
    List<Grupo> findByEvento(Evento evento);
}
