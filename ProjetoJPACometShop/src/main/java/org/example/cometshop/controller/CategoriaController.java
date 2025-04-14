package org.example.cometshop.controller;

import org.example.cometshop.models.Categoria;
import org.example.cometshop.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria adicionar(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}