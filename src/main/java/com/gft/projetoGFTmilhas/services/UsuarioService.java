package com.gft.projetoGFTmilhas.services;

import com.gft.projetoGFTmilhas.models.Usuario;
import com.gft.projetoGFTmilhas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario retornaUsuario(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.get();
    }
}
