package com.gft.projetoGFTmilhas.controllers;

import com.gft.projetoGFTmilhas.models.Usuario;
import com.gft.projetoGFTmilhas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;



    @RequestMapping(method = RequestMethod.GET, path = "/cadastrar")
    public ModelAndView formUsuario(){
        ModelAndView modelAndView = new ModelAndView("/usuario/form.html");
        modelAndView.addObject("usuario",new Usuario());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvarUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario");
        usuarioService.salvarUsuario(usuario);
        redirectAttributes.addFlashAttribute("message","Usuario salvo com sucesso");
        return modelAndView;
    }


    @RequestMapping()
    public ModelAndView listarUsuarios(){
        ModelAndView modelAndView = new ModelAndView("usuario/listar.html");
        modelAndView.addObject("usuarios",usuarioService.listarUsuarios());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/editar")
    public ModelAndView editarUsuario(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("/usuario/form.html");
        try{
            Usuario usuario = usuarioService.retornaUsuario(id);
            modelAndView.addObject("usuario",usuario);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return modelAndView;
    }

    @RequestMapping("/deletar")
    public ModelAndView deletarUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario");
        try{
            usuarioService.deletarUsuario(id);
            redirectAttributes.addFlashAttribute("mensagem","Usuario excluido com sucesso");
        }catch (Exception e){
            modelAndView.addObject("mensagem",e.getMessage());
        }

        return modelAndView;
    }


}
