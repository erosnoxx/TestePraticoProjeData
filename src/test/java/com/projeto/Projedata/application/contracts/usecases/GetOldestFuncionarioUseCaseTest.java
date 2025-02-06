package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.usecases.GetOldestFuncionarioUseCaseImpl;
import com.projeto.Projedata.domain.entities.Funcionario;
import com.projeto.Projedata.infrastructure.persistence.FuncionarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GetOldestFuncionarioUseCaseTest {

    private FuncionarioRepository funcionarioRepository;
    private GetOldestFuncionarioUseCaseImpl getOldestFuncionarioUseCase;

    private Funcionario funcionario1;
    private Funcionario funcionario2;
    private Funcionario funcionario3;

    @BeforeEach
    void setUp() {
        funcionarioRepository = new FuncionarioRepositoryImpl();
        getOldestFuncionarioUseCase = new GetOldestFuncionarioUseCaseImpl(funcionarioRepository);

        funcionario1 = new Funcionario(
                "João", LocalDate.of(1990, 5, 20),
                new BigDecimal("2500.00"), "Analista");

        funcionario2 = new Funcionario(
                "Maria", LocalDate.of(1985, 7, 15),
                new BigDecimal("3000.00"), "Desenvolvedor");

        funcionario3 = new Funcionario(
                "Carlos", LocalDate.of(1992, 3, 10),
                new BigDecimal("2800.00"), "Analista");

        funcionarioRepository.save(funcionario1);
        funcionarioRepository.save(funcionario2);
        funcionarioRepository.save(funcionario3);
    }

    @Test
    void testGetOldestFuncionario() {
        // Act:
        Funcionario oldestFuncionario = getOldestFuncionarioUseCase.execute();

        // Assert:
        assertNotNull(oldestFuncionario, "O funcionário mais velho não pode ser nulo.");
        assertEquals(funcionario2, oldestFuncionario, "Maria deveria ser o funcionário mais velho.");
    }
}
