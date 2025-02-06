package com.projeto.Projedata.application.contracts.usecases;

import com.projeto.Projedata.domain.entities.Funcionario;

import java.util.List;
import java.util.Map;

public interface GroupByRoleUseCase {
    Map<String, List<Funcionario>> execute();
}
