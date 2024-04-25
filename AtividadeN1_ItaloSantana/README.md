## Projeto de Sistema Bancário em Java 17 utilizando Threads

### Entidades:

- **Banco**
- **Loja**
- **Funcionário**
- **Cliente**
- **Conta**

### Quantidades:

- **1 Banco**
- **2 Lojas**
- **4 Funcionários (2 por loja)**
- **5 Clientes**

### Funcionalidades por Entidade:

#### Cliente:

- Representado como uma thread.
- Possui uma conta com saldo inicial de R$ 1.000,00.
- Realiza compras alternando entre as lojas, de R$ 100,00 ou R$ 200,00, até que o saldo da conta esteja vazio.

#### Loja:

- Possui uma conta para receber pagamentos dos clientes.
- Paga os funcionários assim que tiver o valor dos salários (R$ 1.400,00).

#### Funcionário:

- Representado como uma thread.
- Possui duas contas: uma para receber o salário da loja e outra de investimentos.
- Investe 20% do salário na conta de investimentos logo após receber o pagamento.

#### Banco:

- Intermedia as transações de forma síncrona e coordenada, garantindo a consistência dos saldos das contas.

### OUTPUT:

- Além disso, o sistema deve exibir o valor das transferências e o saldo final de cada conta, garantindo que os saldos estejam consistentes ao fim da execução, independente da ordem em que as transações foram feitas.

### Critérios de Avaliação:

O projeto vale até 2 pontos na nota do bimestre (N1), com os seguintes critérios:

- **Criação das Entidades Propostas (0.5 pontos)**
    - Cada classe criada corretamente com possíveis métodos implementados.

- **Implementação das Funcionalidades (1 ponto)**
    - Avaliada a lógica e a corretude das operações de cada entidade.

- **Estruturação e Organização do Código (0.4 pontos)**
    - Divisão adequada do projeto em classes.
