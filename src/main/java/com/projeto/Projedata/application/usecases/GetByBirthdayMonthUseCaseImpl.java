package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.GetByBirthdayMonthUseCase;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetByBirthdayMonthUseCaseImpl implements GetByBirthdayMonthUseCase {
    private final FuncionarioRepository repository;

    public GetByBirthdayMonthUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Funcionario> execute() {
        var funcionarios = this.repository.getAll();

        return funcionarios.stream()
                .filter(funcionario -> {
                    int mesNascimento = funcionario.getDataNascimento().getMonthValue();
                    return mesNascimento == 10 || mesNascimento == 12;
                }).toList();
    }
}
