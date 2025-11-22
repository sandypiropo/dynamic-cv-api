package com.dynamiccv.repository;

import com.dynamiccv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository já fornece métodos prontos:
    // save() -> cria ou atualiza
    // findAll() -> busca todos
    // findById() -> busca por id
    // deleteById() -> deleta por id
}
