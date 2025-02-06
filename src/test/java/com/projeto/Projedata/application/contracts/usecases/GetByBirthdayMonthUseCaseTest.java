package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.usecases.GetByBirthdayMonthUseCaseImpl;
import com.projeto.Projedata.domain.entities.Funcionario;
import com.projeto.Projedata.infrastructure.persistence.FuncionarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetByBirthdayMonthUseCaseTest {

    private FuncionarioRepository funcionarioRepository;
    private GetByBirthdayMonthUseCaseImpl getByBirthdayMonthUseCase;

    private Funcionario funcionario1;
    private Funcionario funcionario2;
    private Funcionario funcionario3;
    private Funcionario funcionario4;

    @BeforeEach
    void setUp() {
        funcionarioRepository = new FuncionarioRepositoryImpl();
        getByBirthdayMonthUseCase = new GetByBirthdayMonthUseCaseImpl(funcionarioRepository);

        funcionario1 = new Funcionario(
                "João", LocalDate.of(1990, 10, 15),
                new BigDecimal("2500.00"), "Analista");

        funcionario2 = new Funcionario(
                "Maria", LocalDate.of(1985, 7, 15),
                new BigDecimal("3000.00"), "Desenvolvedor");

        funcionario3 = new Funcionario(
                "Carlos", LocalDate.of(1992, 12, 10),
                new BigDecimal("2800.00"), "Analista");

        funcionario4 = new Funcionario(
                "Ana", LocalDate.of(1995, 10, 5),
                new BigDecimal("3500.00"), "Gerente");

        funcionarioRepository.save(funcionario1);
        funcionarioRepository.save(funcionario2);
        funcionarioRepository.save(funcionario3);
        funcionarioRepository.save(funcionario4);
    }

    @Test
    void testGetByBirthdayMonth() {
        // Act:
        List<Funcionario> funcionarios = getByBirthdayMonthUseCase.execute();

        // Assert:
        assertEquals(3, funcionarios.size(),
                "Deveriam existir 3 funcionários com aniversários nos meses de Outubro ou Dezembro.");

        assertTrue(funcionarios.contains(funcionario1),
                "João deveria estar na lista de aniversariantes de Outubro.");
        assertTrue(funcionarios.contains(funcionario3),
                "Carlos deveria estar na lista de aniversariantes de Dezembro.");
        assertTrue(funcionarios.contains(funcionario4),
                "Ana deveria estar na lista de aniversariantes de Outubro.");
        assertFalse(funcionarios.contains(funcionario2),
                "Maria não deveria estar na lista, pois seu aniversário é em Julho.");
    }
}
