package com.gft.projetoGFTmilhas.controllers;


import com.gft.projetoGFTmilhas.models.Grupo;
import com.gft.projetoGFTmilhas.models.ParticipanteDoEvento;
import com.gft.projetoGFTmilhas.services.EntregaService;
import com.gft.projetoGFTmilhas.services.EventoService;
import com.gft.projetoGFTmilhas.services.GrupoService;
import com.gft.projetoGFTmilhas.services.ParticipanteDoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @Autowired
    ParticipanteDoEventoService participanteDoEventoService;

    @Autowired
    EntregaService entregaService;

    @Autowired
    EventoService eventoService;

    @RequestMapping(method = RequestMethod.GET,path= "/cadastrar")
    public ModelAndView cadastrarGrupo(){
        ModelAndView modelAndView = new ModelAndView("/grupo/form.html");
        modelAndView.addObject("grupo", new Grupo());
        modelAndView.addObject("listaDeEventos",eventoService.listarEventos());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvarGrupo(Grupo grupo){
        ModelAndView modelAndView = new ModelAndView("redirect:/grupo");
        grupoService.salvarGrupo(grupo);
        modelAndView.addObject("grupo",grupo);
        modelAndView.addObject("listaDeEventos",eventoService.listarEventos());
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView listarGrupos(){
        ModelAndView modelAndView = new ModelAndView("/grupo/listar.html");
        modelAndView.addObject("grupos",grupoService.listarGrupos());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/editar")
    public ModelAndView editarGrupo(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("/grupo/form.html");
        try{
            Grupo grupo = grupoService.retornaGrupo(id);
            modelAndView.addObject("grupo",grupo);
            modelAndView.addObject("listaDeEventos",eventoService.listarEventos());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return modelAndView;
    }

    @RequestMapping("/deletar")
    public ModelAndView deletarGrupo(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/grupo");
        Grupo grupo = grupoService.retornaGrupo(id);


        if (grupo.getQuantPessoas() != 0){
            for (int i = 0;i<grupo.getParticpantes().size();i++){
                for (int j = 0;j< grupo.getParticpantes().size();j++) {
                    entregaService.deletarEntregaPeloObjeto(grupo.getParticpantes().get(i).getListaDeEntrega().get(j));
                }
                participanteDoEventoService.deletarParticipanteDoEventoPeloObjeto(grupo.getParticpantes().get(i));
            }
        }

        try{
            grupoService.deletarGrupo(id);
            redirectAttributes.addFlashAttribute("mensagem","Grupo excluido com sucesso");
        }catch (Exception e){
            modelAndView.addObject("mensagem",e.getMessage());
        }

        return modelAndView;
    }

    @RequestMapping(method =RequestMethod.GET,path = "/detalhes-do-grupo{id}")
    public ModelAndView addParticipantesNoGrupo(@RequestParam Long id){
        Grupo grupo = grupoService.retornaGrupo(id);
        ModelAndView modelAndView = new ModelAndView("/grupo/detalhesGrupo.html");
        modelAndView.addObject("grupo",grupo);
        modelAndView.addObject("participante",new ParticipanteDoEvento());
        modelAndView.addObject("listaDeParticipantesDoGrupo",participanteDoEventoService.listarParticipantesDoGrupo(grupo));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/detalhes-do-grupo")
    public ModelAndView salvarParticipantesNoGrupo(@RequestParam Long id, @Valid ParticipanteDoEvento participanteDoEvento){
        ModelAndView modelAndView = new ModelAndView("/grupo/detalhesGrupo.html");
        Grupo grupo = grupoService.retornaGrupo(id);

        participanteDoEvento.setGrupo(grupo);
        grupo.setQuantPessoas((grupo.getQuantPessoas() +1));
        participanteDoEventoService.salvarParticipante(participanteDoEvento);
        modelAndView.addObject("grupo",grupo);
        modelAndView.addObject("participante",participanteDoEvento);
        modelAndView.addObject("listaDeParticipantesDoGrupo",participanteDoEventoService.listarParticipantesDoGrupo(grupo));
        return modelAndView;
    }

}
