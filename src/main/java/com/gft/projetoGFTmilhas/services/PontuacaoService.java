package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Pontuacao;
import com.gft.projetoGFTmilhas.repositories.PontuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PontuacaoService {

    @Autowired
    PontuacaoRepository pontuacaoRepository;

    public Pontuacao salvarPontuacao(Pontuacao pontuacao){
        return pontuacaoRepository.save(pontuacao);
    }

}
