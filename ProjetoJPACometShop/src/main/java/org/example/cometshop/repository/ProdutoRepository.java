package org.example.cometshop.repository;

import org.example.cometshop.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    void salvar(Produto produto);

    void excluir(Long id);

    Object listarTodos();

    Object buscarPorId(Long id);
}
