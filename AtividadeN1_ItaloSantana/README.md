## Projeto de Sistema Bancário em Java 17 utilizando Threads
- O projeto consiste em criar um sistema bancário em Java 17 utilizando o conceito de threads para representar as entidades envolvidas no sistema, como banco, loja, funcionário, cliente e conta. Cada entidade possui suas próprias responsabilidades e interações, e as transações são realizadas de forma síncrona e coordenada pelo banco para garantir a consistência dos saldos das contas.
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
  - Modularização do código, utilizando métodos sempre que possível;
  - Organização/clareza do código (nomes significativos de variáveis, indentação, etc.).

### Organização do GitHub:

- Certifique-se de que seu repositório esteja público.
- Inclua um README explicando o projeto e suas funcionalidades (0.1 pontos).
- Adicione comentários relevantes ao código para facilitar a compreensão.
- Faça commits organizados e com mensagens descritivas.
- Evite enviar o projeto zipado para o GitHub. O link do repositório deve ser enviado diretamente.

### Observações:

- Este projeto é uma atividade prática individual, portanto qualquer forma de plágio ou cópia será penalizada com nota zero.
- Certifique-se de que o código seja livre de erros de sintaxe e executável.
- A entrega deve ser feita até o dia 25/04, até 23:59, pelo Ambiente Virtual de Aprendizagem (AVA).
- O envio deve conter apenas o link para o repositório público do GitHub.
