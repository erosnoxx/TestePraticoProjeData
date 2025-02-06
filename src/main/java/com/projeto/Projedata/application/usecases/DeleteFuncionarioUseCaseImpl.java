package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.DeleteFuncionarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteFuncionarioUseCaseImpl implements DeleteFuncionarioUseCase {
    private final FuncionarioRepository repository;

    public DeleteFuncionarioUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String nome) {
        this.repository.delete(nome);
    }
}
