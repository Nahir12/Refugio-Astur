package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.model.Usuario;
import com.dwes.ApiRestBackEnd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){this.usuarioRepository=usuarioRepository;}
    @Transactional(readOnly = true)
    public List<Usuario> obtenerListadoUsuarios(){
        return usuarioRepository.findAll();
    }

}
