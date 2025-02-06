package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.usecases.CreateFuncionarioUseCaseImpl;
import com.projeto.Projedata.domain.entities.Funcionario;
import com.projeto.Projedata.infrastructure.persistence.FuncionarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateFuncionarioUseCaseTest {

    private FuncionarioRepository funcionarioRepository;
    private CreateFuncionarioUseCaseImpl createFuncionarioUseCase;
    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        funcionarioRepository = new FuncionarioRepositoryImpl();
        createFuncionarioUseCase = new CreateFuncionarioUseCaseImpl(funcionarioRepository);

        funcionario = new Funcionario(
                "João",
                LocalDate.of(1990, 5, 20),
                new BigDecimal("2500.00"),
                "Analista"
        );
    }

    @Test
    void testCreateFuncionario() {
        createFuncionarioUseCase.execute(funcionario);

        assertEquals(1, funcionarioRepository.getAll().size(),
                "O funcionário não foi adicionado ao repositório.");
        assertTrue(funcionarioRepository.getAll().contains(funcionario),
                "O funcionário não está presente na lista de funcionários.");
    }
}
