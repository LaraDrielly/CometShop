package org.example.cometshop.controller;

import org.example.cometshop.models.Pedido;
import org.example.cometshop.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido adicionar(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
