package com.gft.projetoGFTmilhas.controllers;

import com.gft.projetoGFTmilhas.models.Atividade;
import com.gft.projetoGFTmilhas.models.Evento;
import com.gft.projetoGFTmilhas.services.AtividadeService;
import com.gft.projetoGFTmilhas.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    EventoService eventoService;

//    @RequestMapping(method = RequestMethod.GET,path = "/cadastrar")
//    public ModelAndView cadastrarAtividade(){
//        ModelAndView modelAndView = new ModelAndView("/atividade/form.html");
//        modelAndView.addObject("atividade",new Atividade());
//        return modelAndView;
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvarAtividade(@Valid Atividade atividade, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView;
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("mensagem","Verifique os campos");
            modelAndView = new ModelAndView("redirect:/atividade/cadastrar");
        }else modelAndView= new ModelAndView("redirect:/atividade");

        atividadeService.salvarAtividades(atividade);
        modelAndView.addObject("atividade",atividade);
        redirectAttributes.addFlashAttribute("mensagem","Atividade adicionada com sucesso");
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView listarAtividades(){
        ModelAndView modelAndView = new ModelAndView("/atividade/listar.html");
        modelAndView.addObject("listaDeAtividades",atividadeService.listarAtividades());
        return modelAndView;
    }

    @RequestMapping("/editar")
    public ModelAndView editarAtividades(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("/atividade/form.html");

        try{
            Atividade atividade = atividadeService.retornaAtividade(id);
            Evento evento = eventoService.retornaEvento(atividade.getEvento().getId());
            modelAndView.addObject("atividade",atividade);
            modelAndView.addObject("evento",evento);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return modelAndView;
    }

    @RequestMapping("/deletar")
    public ModelAndView deletarAtividade(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/evento/detalhes-evento");
        Atividade atividade = atividadeService.retornaAtividade(id);
        Evento evento = eventoService.retornaEvento(atividade.getEvento().getId());

        try {
            atividadeService.deletarAtividade(id);
            modelAndView.addObject("id",evento);
            redirectAttributes.addFlashAttribute("mensagem","Atividade excluida com sucesso");
        }catch (Exception e ){
            modelAndView.addObject("mensagem",e.getMessage());
        }

        return modelAndView;
    }

}
