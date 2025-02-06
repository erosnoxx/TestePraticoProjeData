package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.GetTotalSalariesUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GetTotalSalariesUseCaseImpl implements GetTotalSalariesUseCase {
    private final FuncionarioRepository repository;

    public GetTotalSalariesUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public BigDecimal execute() {
        var totalSalaries = BigDecimal.ZERO;

        for (var funcionario : this.repository.getAll()) {
            totalSalaries = totalSalaries.add(funcionario.getSalario());
        }

        return totalSalaries;
    }
}
