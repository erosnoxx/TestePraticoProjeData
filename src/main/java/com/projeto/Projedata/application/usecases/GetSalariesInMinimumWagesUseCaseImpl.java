package com.projeto.Projedata.application.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.contracts.usecases.GetSalariesInMinimumWagesUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetSalariesInMinimumWagesUseCaseImpl implements GetSalariesInMinimumWagesUseCase {
    private final FuncionarioRepository repository;
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public GetSalariesInMinimumWagesUseCaseImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Double> execute() {
        var salariesInMinimumWages = new HashMap<String, Double>();

        for (var funcionario : repository.getAll()) {
            double salaryInMinimumWages = funcionario.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            salariesInMinimumWages.put(funcionario.getNome(), salaryInMinimumWages);
        }

        return salariesInMinimumWages;
    }
}
