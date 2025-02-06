package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.domain.entities.Funcionario;

import java.util.List;

public interface GetSortedByNameUseCase {
    List<Funcionario> execute();
}
