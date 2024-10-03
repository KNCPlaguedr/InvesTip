package com.atv.pi.repository;

import com.atv.pi.model.cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoRepository extends JpaRepository<cadastro, Long>{
          boolean existsByEmail(String email);    // Método para verificar se o e-mail já está cadastrado
    boolean existsByUsername(String username);  // Método para verificar se o nome de usuário já está em uso

}
