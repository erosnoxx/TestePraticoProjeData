package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.GetSortedByNameUseCase;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GetSortedByNameUseCaseImpl implements GetSortedByNameUseCase {
    private final FuncionarioRepository repository;

    public GetSortedByNameUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Funcionario> execute() {
        List<Funcionario> sortedFuncionarios = new ArrayList<>(repository.getAll());
        sortedFuncionarios.sort(Comparator.comparing(Funcionario::getNome));
        return sortedFuncionarios;
    }
}
