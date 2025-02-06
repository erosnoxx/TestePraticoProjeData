package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.GroupByRoleUseCase;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroupByRoleUseCaseImpl implements GroupByRoleUseCase {
    private final FuncionarioRepository repository;

    public GroupByRoleUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, List<Funcionario>> execute() {
        var funcionarios = this.repository.getAll();
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }
}
