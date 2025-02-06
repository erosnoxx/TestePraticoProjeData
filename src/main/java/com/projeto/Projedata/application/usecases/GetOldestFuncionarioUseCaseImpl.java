package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.GetOldestFuncionarioUseCase;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class GetOldestFuncionarioUseCaseImpl implements GetOldestFuncionarioUseCase {
    private final FuncionarioRepository repository;

    public GetOldestFuncionarioUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Funcionario execute() {
        var funcionarios = this.repository.getAll();

        return funcionarios.stream()
            .min(Comparator.comparing(Funcionario::getDataNascimento))
            .orElse(null);
    }
}
