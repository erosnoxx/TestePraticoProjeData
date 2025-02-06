# Teste Prático - ProjeData

## Descrição
Este projeto foi desenvolvido como parte do teste prático para a vaga de desenvolvedor na ProjeData. O sistema implementa funcionalidades para gerenciar um conjunto de funcionários, como adicionar, remover, atualizar e agrupar por função, além de exibir informações formatadas e calcular totais.

## Estrutura do Projeto

O projeto é dividido em classes de acordo com os requisitos fornecidos, com a seguinte estrutura:

- **Pessoa**: Classe base que contém os atributos `nome` e `dataNascimento` de tipo `LocalDate`.
- **Funcionario**: Classe que estende `Pessoa` e adiciona os atributos `salario` (BigDecimal) e `funcao` (String).
- **Main**: Classe responsável por executar as ações principais do sistema (inserir, remover, agrupar, imprimir, etc).

### Decisões de Design

1. **Uso do Spring**:
    - Escolhi usar o **Spring Boot** principalmente para aproveitar a integração com **JUnit** e **Mockito**, que facilitam a criação de testes automatizados. Como o Spring já vem configurado com essas dependências, a configuração de testes ficou mais simples e rápida.
    - A princípio, pensei em criar uma **API REST** para tornar a aplicação mais dinâmica, mas decidi seguir com uma **aplicação de linha de comando**. Isso simplifica a navegação e faz com que o foco fosse mais na lógica de negócios do que em configuracoes e chamadas HTTP.

2. **Clean Architecture**:
    - Utilizei **Clean Architecture** porque, além de ser uma prática que gosto muito, ela traz uma excelente organização para o projeto. Embora seja um projeto simples, prefiro trabalhar com algo mais **modularizado e estruturado** desde o início. Isso facilita manutenções futuras e permite que eu escale o código facilmente, caso necessário.
    - A persistência dos dados foi feita em **arrays em memória** para simplificar a aplicação e atender aos requisitos iniciais do teste. No entanto, devido à modularização da arquitetura, essa implementação poderia ser facilmente trocada por **qualquer banco de dados** no futuro sem impactar as funcionalidades.

3. **Estrutura Modular**:
    - **Clean Architecture** também me permitiu manter o código bem organizado, com **separação clara de responsabilidades** entre as camadas (entidades, casos de uso, interfaces de persistência, etc.). Isso não só melhora a legibilidade do código, mas também facilita a adição de novos requisitos no futuro.

4. **Formatação de Dados**:
    - Para exibir os **salários** com separador de milhar como ponto e decimal como vírgula, usei **DecimalFormat**, que é muito útil para formatação numérica. Já para as **datas**, utilizei o **DateTimeFormatter** para garantir que as datas seguissem o formato `dd/MM/yyyy`, conforme exigido.

5. **Operações e Uso de Streams**:
    - Para operações como **ordenar os funcionários** e **filtragem por aniversário**, usei **Streams**. Isso mantém o código simples, expressivo e fácil de entender.
    - A lógica para **agrupamento por função** foi implementada com um **Map**, o que permite organizar facilmente os funcionários por seus cargos.

6. **Persistência e Substituição de Banco de Dados**:
    - Como mencionei, a persistência foi feita usando **arrays em memória** para manter a aplicação simples. Contudo, devido à arquitetura que adotei, a troca para um banco de dados relacional (como MySQL ou PostgreSQL) seria direta, com mudanças mínimas no código, graças à separação clara entre a camada de dados e as demais camadas da aplicação.

## Requisitos Implementados

- **Inserção de Funcionários**: Todos os funcionários foram inseridos conforme a tabela fornecida.
- **Remoção de Funcionário**: O funcionário "João" foi removido da lista de funcionários.
- **Impressão das Informações dos Funcionários**: Exibição das informações com o formato de data e salários conforme especificado.
- **Aumento Salarial**: Todos os funcionários receberam um aumento de 10% no salário.
- **Agrupamento por Função**: Funcionários foram agrupados por função em um `Map`.
- **Filtragem por Aniversário**: Funcionários com aniversários nos meses de outubro e dezembro foram filtrados e exibidos.
- **Funcionário mais Velho**: O funcionário com a maior idade foi identificado e exibido.
- **Ordenação Alfabética**: Os funcionários foram ordenados em ordem alfabética por nome.
- **Cálculo de Salários Totais e Quantidade de Salários Mínimos**: O total dos salários foi calculado e exibido, assim como o número de salários mínimos que cada funcionário ganha.

## Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/erosnoxx/TestePraticoProjeData.git
