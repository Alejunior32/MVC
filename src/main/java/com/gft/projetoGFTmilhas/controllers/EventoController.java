package com.gft.projetoGFTmilhas.controllers;

import com.gft.projetoGFTmilhas.models.Atividade;
import com.gft.projetoGFTmilhas.models.Evento;
import com.gft.projetoGFTmilhas.services.AtividadeService;
import com.gft.projetoGFTmilhas.services.EventoService;
import com.gft.projetoGFTmilhas.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    GrupoService grupoService;

    @RequestMapping(method = RequestMethod.GET,path = "/cadastrar")
    public ModelAndView cadastrarEvento(){
        ModelAndView modelAndView = new ModelAndView("/evento/form.html");
        modelAndView.addObject("evento",new Evento());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvarEvento(Evento evento){
        ModelAndView modelAndView = new ModelAndView("redirect:/evento");
        eventoService.salvarEvento(evento);
        modelAndView.addObject("evento",evento);
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView listarEvento(){
        ModelAndView modelAndView = new ModelAndView("/evento/listar.html");
        modelAndView.addObject("eventos",eventoService.listarEventos());
        return modelAndView;
    }

    @RequestMapping("/editar")
    public ModelAndView editarEventos(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("/evento/form.html");

        try{
            Evento evento = eventoService.retornaEvento(id);
            modelAndView.addObject("evento",evento);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return modelAndView;
    }

    @RequestMapping("/deletar")
    public ModelAndView deletarEvento(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/evento");

        Evento evento = eventoService.retornaEvento(id);
        if (evento.getAtividades().size() != 0  && evento.getGrupos().size() != 0){
            eventoService.deletarGruposPeloEvento(evento);
            eventoService.deletarAtividadePeloEvento(evento);
        }

        try {
            eventoService.deletarEvento(id);
            redirectAttributes.addFlashAttribute("mensagem","Evento excluido com sucesso");
        }catch (Exception e ){
            modelAndView.addObject("mensagem",e.getMessage());
        }
        return modelAndView;
    }
    @RequestMapping(path = "/detalhes-evento{id}")
    public ModelAndView addAtividadesNoEvento(@RequestParam Long id){
        Evento evento = eventoService.retornaEvento(id);
        ModelAndView modelAndView = new ModelAndView("/evento/detalhesEvento.html");
        modelAndView.addObject("evento",evento);
        modelAndView.addObject("atividade",new Atividade());
        modelAndView.addObject("listaDeAtividadesDoEvento",atividadeService.listarAtividadesDoEvento(evento));

        modelAndView.addObject("listaDeGrupoDoEvento",grupoService.listarGruposDoEvento(evento));
        modelAndView.addObject("listaDeGrupoSemEvento",grupoService.listarGruposSemEvento(grupoService.listarGrupos()));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/detalhes-evento")
    public ModelAndView salvarAtividadeNoEvento(@RequestParam Long id, Atividade atividade){
        ModelAndView modelAndView = new ModelAndView("/evento/detalhesEvento.html");
        Evento evento= eventoService.retornaEvento(id);
        atividade.setEvento(evento);
        atividadeService.salvarAtividades(atividade);
        modelAndView.addObject("evento",evento);
        modelAndView.addObject("atividade",atividade);
        modelAndView.addObject("listaDeAtividadesDoEvento",atividadeService.listarAtividadesDoEvento(evento));



        modelAndView.addObject("listaDeGrupoDoEvento",grupoService.listarGruposDoEvento(evento));
        modelAndView.addObject("listaDeGrupoSemEvento",grupoService.listarGruposSemEvento(grupoService.listarGrupos()));


        return modelAndView;
    }
}
