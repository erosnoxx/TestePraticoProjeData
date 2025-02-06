package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.ListAllFuncionarioUseCase;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllFuncionarioUseCaseImpl implements ListAllFuncionarioUseCase {
    private final FuncionarioRepository repository;

    public ListAllFuncionarioUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Funcionario> execute() {
        return this.repository.getAll();
    }
}
