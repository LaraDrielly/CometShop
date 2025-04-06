package org.example.cometshop.controller;

import org.example.cometshop.models.Usuario;
import org.example.cometshop.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario adicionar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
