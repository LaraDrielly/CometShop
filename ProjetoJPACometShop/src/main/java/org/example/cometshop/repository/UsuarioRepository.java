package org.example.cometshop.repository;

import org.example.cometshop.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email); // Verifica se o e-mail já está registrado
    Usuario findByEmail(String email);   // Encontra o usuário pelo e-mail
}
