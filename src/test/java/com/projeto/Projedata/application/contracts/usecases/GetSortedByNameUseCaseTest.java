package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.usecases.GetSortedByNameUseCaseImpl;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GetSortedByNameUseCaseTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private GetSortedByNameUseCaseImpl getSortedByNameUseCase;

    private Funcionario funcionario1;
    private Funcionario funcionario2;
    private Funcionario funcionario3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        funcionario1 = new Funcionario(
                "João", LocalDate.of(1990, 5, 20),
                new BigDecimal("2500.00"), "Analista");
        funcionario2 = new Funcionario(
                "Maria", LocalDate.of(1985, 7, 15),
                new BigDecimal("3000.00"), "Desenvolvedor");
        funcionario3 = new Funcionario(
                "Carlos", LocalDate.of(1992, 3, 10),
                new BigDecimal("2800.00"), "Analista");
    }

    @Test
    void testGetSortedByName() {
        // Arrange:
        when(funcionarioRepository.getAll()).thenReturn(List.of(funcionario1, funcionario2, funcionario3));

        // Act:
        List<Funcionario> sortedFuncionarios = getSortedByNameUseCase.execute();

        // Assert:
        assertEquals("Carlos", sortedFuncionarios.get(0).getNome());
        assertEquals("João", sortedFuncionarios.get(1).getNome());
        assertEquals("Maria", sortedFuncionarios.get(2).getNome());
    }
}
