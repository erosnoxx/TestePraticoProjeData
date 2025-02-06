package com.projeto.Projedata.presenters;

import com.projeto.Projedata.application.contracts.services.SetupService;
import com.projeto.Projedata.application.contracts.usecases.*;
import com.projeto.Projedata.domain.entities.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class FuncionarioPresenter {
    @Autowired
    private SetupService setupService;
    @Autowired
    private CreateFuncionarioUseCase createFuncionarioUseCase;
    @Autowired
    private ListAllFuncionarioUseCase listAllFuncionarioUseCase;
    @Autowired
    private DeleteFuncionarioUseCase deleteFuncionarioUseCase;
    @Autowired
    private GetByBirthdayMonthUseCase getByBirthdayMonthUseCase;
    @Autowired
    private GetOldestFuncionarioUseCase getOldestFuncionarioUseCase;
    @Autowired
    private GetSalariesInMinimumWagesUseCase getSalariesInMinimumWagesUseCase;
    @Autowired
    private GetSortedByNameUseCase getSortedByNameUseCase;
    @Autowired
    private GetTotalSalariesUseCase getTotalSalariesUseCase;
    @Autowired
    private GroupByRoleUseCase groupByRoleUseCase;
    @Autowired
    private IncreaseSalaryUseCase increaseSalaryUseCase;

    public void run() {
        var scanner = new Scanner(System.in);
        setupService.setupProject();

        while (true) {
            System.out.println("\nBem-vindo ao sistema! O que deseja fazer?");
            System.out.println("1. Consultar funcionários");
            System.out.println("2. Adicionar funcionário");
            System.out.println("3. Deletar funcionário João");
            System.out.println("4. Consultar funcionários nascidos em Outubro e Dezembro");
            System.out.println("5. Consultar funcionário mais velho");
            System.out.println("6. Calcular salários mínimos por funcionário");
            System.out.println("7. Consultar funcionários em ordem alfabética");
            System.out.println("8. Consultar total gasto em salários");
            System.out.println("9. Consultar funcionários agrupados por função");
            System.out.println("10. Aumentar o salário geral em 10%");
            System.out.println("12. Sair");

            System.out.print("Digite sua opção: ");
            String input = scanner.nextLine().trim();

            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            clearScreen();

            switch (option) {
                case 1 -> listAllFuncionarios(scanner);
                case 2 -> createFuncionario(scanner);
                case 3 -> deleteFuncionario(scanner);
                case 4 -> getBornOctoberAndDecember(scanner);
                case 5 -> getOldestFuncionario(scanner);
                case 6 -> getSalariesInMinimumWages(scanner);
                case 7 -> getSortedByName(scanner);
                case 8 -> getTotalSalaries(scanner);
                case 9 -> getGroupedByRole(scanner);
                case 10 -> increaseSalary(scanner);
                case 12 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void listAllFuncionarios(Scanner scanner) {
        var funcionarios = this.listAllFuncionarioUseCase.execute();
        formatFuncionarioList(funcionarios);
        backScreen(scanner);
    }

    private void createFuncionario(Scanner scanner) {
        System.out.print("Nome: ");
        var nome = scanner.nextLine();

        System.out.print("Data de Nascimento (yyyy-MM-dd): ");
        var dataStr = scanner.nextLine();
        var dataNascimento = LocalDate.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Salário: ");
        var salario = new BigDecimal(scanner.nextLine());

        System.out.print("Função: ");
        var funcao = scanner.nextLine();

        var funcionario = new Funcionario(nome, dataNascimento, salario, funcao);
        createFuncionarioUseCase.execute(funcionario);
        System.out.println("Funcionário " + funcionario.getNome() + " criado com sucesso!");
        backScreen(scanner);
    }

    private void deleteFuncionario(Scanner scanner) {
        deleteFuncionarioUseCase.execute("João");
        System.out.println("Funcionário João deletado com sucesso!");
        backScreen(scanner);
    }

    private void getBornOctoberAndDecember(Scanner scanner) {
        var funcionarios = getByBirthdayMonthUseCase.execute();
        formatFuncionarioList(funcionarios);
        backScreen(scanner);
    }

    private void getOldestFuncionario(Scanner scanner) {
        clearScreen();
        var funcionario = getOldestFuncionarioUseCase.execute();
        System.out.println("Funcionário mais velho: " + funcionario.getNome() + "| Idade: " + funcionario.getIdade());
        backScreen(scanner);
    }

    private void getSalariesInMinimumWages(Scanner scanner) {
        clearScreen();
        var salariosEmMinimos = getSalariesInMinimumWagesUseCase.execute();

        if (salariosEmMinimos.isEmpty()) {
            System.out.println("Nenhum salário encontrado.");
            return;
        }

        System.out.println("===== Salários em Salários Mínimos =====");
        for (var entry : salariosEmMinimos.entrySet()) {
            System.out.println("Funcionário: " + entry.getKey() +
                    " | Salário em Salários Mínimos: " + entry.getValue());
        }
        backScreen(scanner);
    }

    private void getSortedByName(Scanner scanner) {
        var funcionarios = getSortedByNameUseCase.execute();
        formatFuncionarioList(funcionarios);
        backScreen(scanner);
    }

    private void getTotalSalaries(Scanner scanner) {
        var totalSalaries = getTotalSalariesUseCase.execute();
        System.out.println("Total gasto em salários: R$ " + totalSalaries);
        backScreen(scanner);
    }

    private void getGroupedByRole(Scanner scanner) {
        var funcionarios = this.groupByRoleUseCase.execute();
        formatFuncionarioGroupView(funcionarios);
        backScreen(scanner);
    }

    private void increaseSalary(Scanner scanner) {
        clearScreen();
        increaseSalaryUseCase.execute();
        System.out.println("Aumento de salário realizado com sucesso!");
        backScreen(scanner);
    }

    private void formatFuncionarioList(List<Funcionario> funcionarios) {
        clearScreen();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        var dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var currencyFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        System.out.println("Lista de Funcionários:");
        for (Funcionario f : funcionarios) {
            String dataNascimentoFormatada = f.getDataNascimento().format(dateFormatter);
            String salarioFormatado = currencyFormat.format(f.getSalario());

            System.out.println("Nome: " + f.getNome() +
                    " | Data de Nascimento: " + dataNascimentoFormatada +
                    " | Salário: R$ " + salarioFormatado +
                    " | Função: " + f.getFuncao());
        }
    }

    private void formatFuncionarioGroupView(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        clearScreen();
        if (funcionariosPorFuncao.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        var dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var currencyFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        System.out.println("===== Funcionários Agrupados por Função =====");

        for (var entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("\n📌 Função: " + entry.getKey());
            System.out.println("-------------------------------------------");

            for (var f : entry.getValue()) {
                var dataNascimentoFormatada = f.getDataNascimento().format(dateFormatter);
                var salarioFormatado = currencyFormat.format(f.getSalario());

                System.out.println("Nome: " + f.getNome() +
                        " | Data de Nascimento: " + dataNascimentoFormatada +
                        " | Salário: R$ " + salarioFormatado);
            }
        }

    }

    private void clearScreen() {
        char c = '\n';
        int length = 25;
        char[] chars = new char[length];
        Arrays.fill(chars, c);
        System.out.print(String.valueOf(chars));
    }

    private void backScreen(Scanner scanner) {
        System.out.println("\n\n\n\nPressione enter para voltar para o menu...");
        scanner.nextLine();
        clearScreen();
    }
}
