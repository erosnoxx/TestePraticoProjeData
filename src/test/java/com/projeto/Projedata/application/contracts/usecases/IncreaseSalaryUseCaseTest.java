package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.usecases.IncreaseSalaryUseCaseImpl;
import com.projeto.Projedata.domain.entities.Funcionario;
import com.projeto.Projedata.infrastructure.persistence.FuncionarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IncreaseSalaryUseCaseTest {

    private FuncionarioRepository funcionarioRepository;
    private IncreaseSalaryUseCaseImpl increaseSalaryUseCase;

    private Funcionario funcionario1;
    private Funcionario funcionario2;
    private Funcionario funcionario3;

    @BeforeEach
    void setUp() {
        // Instanciando as implementações reais
        funcionarioRepository = new FuncionarioRepositoryImpl();
        increaseSalaryUseCase = new IncreaseSalaryUseCaseImpl(funcionarioRepository);

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
    void testIncreaseSalary() {
        // Act:
        increaseSalaryUseCase.execute();

        // Assert:
        assertEquals(new BigDecimal("2750.000"), funcionario1.getSalario()); // 2500 * 1.10
        assertEquals(new BigDecimal("3300.000"), funcionario2.getSalario()); // 3000 * 1.10
        assertEquals(new BigDecimal("3080.000"), funcionario3.getSalario()); // 2800 * 1.10
    }
}
