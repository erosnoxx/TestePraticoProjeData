package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.CreateFuncionarioUseCase;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.stereotype.Service;

@Service
public class CreateFuncionarioUseCaseImpl implements CreateFuncionarioUseCase {
    private final FuncionarioRepository repository;

    public CreateFuncionarioUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Funcionario funcionario) {
        this.repository.save(funcionario);
    }
}
