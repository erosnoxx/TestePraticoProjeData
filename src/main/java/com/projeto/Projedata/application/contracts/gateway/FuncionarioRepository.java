package com.projeto.Projedata.application.contracts.gateway;

import com.projeto.Projedata.domain.entities.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    void save(Funcionario funcionario);
    void delete(String nome);
    List<Funcionario> getAll();
    void update(Funcionario funcionario);
}
