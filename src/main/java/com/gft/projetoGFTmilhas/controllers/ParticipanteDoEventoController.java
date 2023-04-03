package com.gft.projetoGFTmilhas.controllers;


import com.gft.projetoGFTmilhas.models.*;
import com.gft.projetoGFTmilhas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/participantes")
public class ParticipanteDoEventoController {

    @Autowired
    ParticipanteDoEventoService participanteDoEventoService;

    @Autowired
    PresencaService presencaService;

    @Autowired
    EventoService eventoService;

    @Autowired
    GrupoService grupoService;

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    EntregaService entregaService;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    List<LocalDate> listaDeDias;

//    @RequestMapping(method = RequestMethod.GET,path = "/cadastrar")
//    public ModelAndView cadastrarParticipantes(){
//        ModelAndView modelAndView = new ModelAndView("/participante/form.html");
//        modelAndView.addObject("participante", new ParticipanteDoEvento());
//        modelAndView.addObject("listaDeGrupos",grupoService.listarGrupos());
//        return modelAndView;
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvarParticipantes(ParticipanteDoEvento participanteDoEvento){
        ModelAndView modelAndView = new ModelAndView("redirect:/participantes");
        Grupo grupo = grupoService.retornaGrupo(participanteDoEvento.getGrupo().getId());
        modelAndView.addObject("grupo", grupo);
        modelAndView.addObject("listaDeGrupos",grupoService.listarGrupos());
        participanteDoEventoService.salvarParticipante(participanteDoEvento);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/editar")
    public ModelAndView editarParticipantes(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("/participante/form.html");
        try{
            ParticipanteDoEvento participante = participanteDoEventoService.retornaParticipanteDoEvento(id);
            Grupo grupo = grupoService.retornaGrupo(participante.getGrupo().getId());
            modelAndView.addObject("listaDeGrupos",grupoService.listarGrupos());
            modelAndView.addObject("participante",participante);
            modelAndView.addObject("grupo", grupo);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return modelAndView;
    }
    @RequestMapping("/deletar")
    public ModelAndView deletarParticipantes(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/grupo/detalhes-do-grupo");
        ParticipanteDoEvento participanteDoEvento= participanteDoEventoService.retornaParticipanteDoEvento(id);
        Grupo grupo = grupoService.retornaGrupo(participanteDoEvento.getGrupo().getId());
        grupo.setQuantPessoas((grupo.getQuantPessoas() -1));

        if(participanteDoEvento.getListaDeEntrega().size() > 0){
            for (int i = 0;i<participanteDoEvento.getListaDeEntrega().size();i++){
                entregaService.deletarEntregaPeloObjeto(participanteDoEvento.getListaDeEntrega().get(i));
            }
        }

        try{
            participanteDoEventoService.deletarParticipanteDoEventoPeloId(id);
            grupo.setQuantPessoas((grupo.getQuantPessoas() -1));
            modelAndView.addObject("id",grupo);
            redirectAttributes.addFlashAttribute("mensagem","Participante excluido com sucesso");
        }catch (Exception e){
            modelAndView.addObject("mensagem",e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(method =RequestMethod.GET,path = "/marcar-presença")
    public ModelAndView addParticipantesNoGrupo(@RequestParam Long id){
        ParticipanteDoEvento participanteDoEvento = participanteDoEventoService.retornaParticipanteDoEvento(id);
        Grupo grupo = grupoService.retornaGrupo(participanteDoEvento.getGrupo().getId());
        ModelAndView modelAndView = new ModelAndView("/presença/marcarPresença.html");

        Evento evento = eventoService.retornaEvento(grupo.getEvento().getId());
        LocalDate dataInicio = evento.getDataDeInicio();
        LocalDate dataFinal = evento.getDataFinal();
        Long periodoEntreDatas = eventoService.numeroDeDiasDoEvento(dataInicio,dataFinal);
        listaDeDias = new ArrayList<>();

        for (int i =0;i<=periodoEntreDatas;i++){
            listaDeDias.add(dataInicio.plusDays(i));
        }

        modelAndView.addObject("grupo",grupo);
        modelAndView.addObject("presenca",new Presenca());
        modelAndView.addObject("listaDeDias",listaDeDias);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/marcar-presença")
    public ModelAndView salvarParticipantesNoGrupo(@RequestParam Long id,Presenca presenca){
        ModelAndView modelAndView = new ModelAndView("redirect:/grupo/detalhes-do-grupo");
        ParticipanteDoEvento participanteDoEvento = participanteDoEventoService.retornaParticipanteDoEvento(id);
        Grupo grupo = grupoService.retornaGrupo(participanteDoEvento.getGrupo().getId());


        Evento evento = eventoService.retornaEvento(grupo.getEvento().getId());
        LocalDate dataInicio = evento.getDataDeInicio();
        LocalDate dataFinal = evento.getDataFinal();
        Long periodoEntreDatas = eventoService.numeroDeDiasDoEvento(dataInicio,dataFinal);

        listaDeDias = new ArrayList<>();

        for (int i =0;i<=periodoEntreDatas;i++){
            listaDeDias.add(dataInicio.plusDays(i));
        }

        presenca.setParticipanteDoEvento(participanteDoEvento);
        presencaService.salvarPresenca(presenca);
        modelAndView.addObject("grupo",grupo);
        modelAndView.addObject("presenca",presenca);
        modelAndView.addObject("listaDeDias",listaDeDias);

        return modelAndView;
    }




    @RequestMapping(method = RequestMethod.GET,path = "/entregar-atividade{id}")
    public ModelAndView entregarAtividade(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("/participante/entregarAtividade.html");

        ParticipanteDoEvento participanteDoEvento = participanteDoEventoService.retornaParticipanteDoEvento(id);
        Grupo grupo = grupoService.retornaGrupo(participanteDoEvento.getGrupo().getId());
        Evento evento = eventoService.retornaEvento(grupo.getEvento().getId());
        List<Atividade> atividadesDoEvento =atividadeService.listarAtividadesDoEvento(evento);
        modelAndView.addObject("grupo",grupo);
        modelAndView.addObject("listaDeAtividades",atividadesDoEvento);
        modelAndView.addObject("participante",participanteDoEvento);
        modelAndView.addObject("entrega",new Entrega());
        modelAndView.addObject("listaDeEntregasDoParticipante",entregaService.listarEntregasDoParticipante(participanteDoEvento));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/entregar-atividade")
    public ModelAndView salvarEntregaAtividade(@RequestParam Long id, Entrega entrega){
        ModelAndView modelAndView = new ModelAndView("/participante/entregarAtividade.html");

        ParticipanteDoEvento participanteDoEvento = participanteDoEventoService.retornaParticipanteDoEvento(id);
        Grupo grupo = grupoService.retornaGrupo(participanteDoEvento.getGrupo().getId());
        Evento evento = eventoService.retornaEvento(grupo.getEvento().getId());
        List<Atividade> atividadesDoEvento =atividadeService.listarAtividadesDoEvento(evento);
        modelAndView.addObject("grupo",grupo);
        modelAndView.addObject("listaDeAtividades",atividadesDoEvento);
        modelAndView.addObject("participante",participanteDoEvento);
        entrega.setParticipante(participanteDoEvento);
        entrega.setEntrega(true);
        entregaService.salvarEntrega(entrega);
        modelAndView.addObject("listaDeEntregasDoParticipante",entregaService.listarEntregasDoParticipante(participanteDoEvento));
        return modelAndView;
    }
}
