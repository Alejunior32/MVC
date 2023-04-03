package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Atividade;
import com.gft.projetoGFTmilhas.models.Evento;
import com.gft.projetoGFTmilhas.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    AtividadeRepository atividadeRepository;


    public Atividade salvarAtividades(Atividade atividade){
        return atividadeRepository.save(atividade);
    }

    public List<Atividade> listarAtividades(){
        return atividadeRepository.findAll();
    }

    public void deletarAtividade(Long id){
        atividadeRepository.deleteById(id);
    }

    public void deletarAtividadePeloObjeto(Atividade atividade){
        atividadeRepository.delete(atividade);
    }
    public Atividade retornaAtividade(Long id){
        Optional<Atividade> atividade = atividadeRepository.findById(id);

        return atividade.get();
    }

    public List<Atividade> listarAtividadesDoEvento(Evento evento){
        return atividadeRepository.findByEvento(evento);
    }


}
