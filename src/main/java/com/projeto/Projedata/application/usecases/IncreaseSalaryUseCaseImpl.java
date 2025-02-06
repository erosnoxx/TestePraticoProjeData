package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.IncreaseSalaryUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IncreaseSalaryUseCaseImpl implements IncreaseSalaryUseCase {
    private final FuncionarioRepository repository;

    public IncreaseSalaryUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        var funcionarios = this.repository.getAll();

        for (var funcionario : funcionarios) {
            var newSalary = funcionario.getSalario().multiply(BigDecimal.valueOf(1.10));
            funcionario.setSalario(newSalary);
            this.repository.update(funcionario);
        }
    }
}
