package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Presenca;
import com.gft.projetoGFTmilhas.repositories.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PresencaService {

    @Autowired
    PresencaRepository presencaRepository;

    public Presenca salvarPresenca(Presenca presenca){
        return presencaRepository.save(presenca);
    }

    public List<Presenca> listarPresencas(){
        return presencaRepository.findAll();
    }

    public void deletarPresenca(Long id){
        presencaRepository.deleteById(id);
    }

    public void listarDiasDoEvento(){

    }


    public void salvarListaDePresenca(Set<Presenca> listaDePresenca) {

        for (Presenca presenca:
             listaDePresenca) {
            presencaRepository.save(presenca);
        }
    }
}
