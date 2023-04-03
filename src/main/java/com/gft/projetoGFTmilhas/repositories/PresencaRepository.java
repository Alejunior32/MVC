package com.gft.projetoGFTmilhas.repositories;


import com.gft.projetoGFTmilhas.models.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {
}
