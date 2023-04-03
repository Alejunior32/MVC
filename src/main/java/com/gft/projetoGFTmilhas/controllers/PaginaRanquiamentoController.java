package com.gft.projetoGFTmilhas.controllers;

import com.gft.projetoGFTmilhas.repositories.GrupoRepository;
import com.gft.projetoGFTmilhas.services.EventoService;
import com.gft.projetoGFTmilhas.services.PontuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaginaRanquiamentoController {

    @Autowired
    EventoService eventoService;

    @Autowired
    PontuacaoService pontuacaoService;

    @Autowired
    GrupoRepository grupoRepository;

    @RequestMapping
    public ModelAndView paginaPrincipal(){
        ModelAndView modelAndView = new ModelAndView("principal.html");
        modelAndView.addObject("listaDeEventos",eventoService.listarEventos());
        return modelAndView;
    }

//    @RequestMapping("/ranking")
//    public ModelAndView paginaDePontucao(@RequestParam Long id){
//        Evento evento = eventoService.retornaEvento(id);
//        List<Grupo> listaGrupoEvento = grupoRepository.findByEvento(evento);
//        for (Grupo grupo:listaGrupoEvento) {
//            if (grupo.getPontuacao() != null){
//                Pontuacao pontuacao = new Pontuacao();
//                pontuacao.setGrupo(grupo);
//                Long pontos=0L;
//                for (int i=0;i<grupo.getParticpantes().size();i++) {
//                    for (int j =0;j<grupo.getParticpantes().get(i).getListaDeEntrega().size();j++){
//                        if(grupo.getParticpantes().get(i).getListaDeEntrega().get(j).isEntrega()){
//
//                        }
//                    }
//
//                }
//
//                    pontuacao.setPontos(pontos);
//                pontuacaoService.salvarPontuacao(pontuacao);
//            }
//
//        }
//
//    }

}
