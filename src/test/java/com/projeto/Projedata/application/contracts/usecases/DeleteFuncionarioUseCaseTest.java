package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.application.contracts.gateway.FuncionarioRepository;
import com.projeto.Projedata.application.usecases.DeleteFuncionarioUseCaseImpl;
import com.projeto.Projedata.domain.entities.Funcionario;
import com.projeto.Projedata.infrastructure.persistence.FuncionarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFuncionarioUseCaseTest {

    private FuncionarioRepository funcionarioRepository;
    private DeleteFuncionarioUseCaseImpl deleteFuncionarioUseCase;
    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        funcionarioRepository = new FuncionarioRepositoryImpl();
        deleteFuncionarioUseCase = new DeleteFuncionarioUseCaseImpl(funcionarioRepository);

        funcionario = new Funcionario(
                "João",
                LocalDate.of(1990, 5, 20),
                new BigDecimal("2500.00"),
                "Analista"
        );

        funcionarioRepository.save(funcionario);
    }

    @Test
    void testDeleteFuncionario() {
        deleteFuncionarioUseCase.execute("João");

        List<Funcionario> funcionariosAtualizados = funcionarioRepository.getAll();
        assertTrue(funcionariosAtualizados.isEmpty(), "A lista de funcionários não está vazia após a remoção de João.");
    }
}
