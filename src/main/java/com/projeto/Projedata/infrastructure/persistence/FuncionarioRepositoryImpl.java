package com.projeto.Projedata.infrastructure.persistence;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionarioRepositoryImpl implements FuncionarioRepository {
    private List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void save(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    @Override
    public void delete(String nome) {
        this.funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    @Override
    public List<Funcionario> getAll() {
        return new ArrayList<>(funcionarios);
    }

    @Override
    public void update(Funcionario funcionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getNome().equals(funcionario.getNome())) {
                funcionarios.set(i, funcionario);
                break;
            }
        }
    }
}
