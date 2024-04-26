public class Main {
    public static void main(String[] args) {

        // O método main apenas instancia objetos e inicia as threads
        Banco banco = new Banco();

        Loja loja1 = new Loja(banco, "Loja1");
        Loja loja2 = new Loja(banco, "Loja2");

        Cliente cliente1 = new Cliente(banco, loja1, loja2, "Cliente 1");
        Cliente cliente2 = new Cliente(banco, loja1, loja2, "Cliente 2");
        Cliente cliente3 = new Cliente(banco, loja1, loja2, "Cliente 3");
        Cliente cliente4 = new Cliente(banco, loja1, loja2, "Cliente 4");
        Cliente cliente5 = new Cliente(banco, loja1, loja2, "Cliente 5");

        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();
        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
            cliente5.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Verifica o saldo final das lojas
        System.out.println("Saldo final das lojas: ");
        System.out.println("Saldo da " + loja1.nomeLoja + ": " + loja1.conta.getSaldo());
        System.out.println("Saldo da " + loja2.nomeLoja + ": " + loja2.conta.getSaldo());

        // Verifica o saldo final das contas dos funcionários e de investimento
        System.out.println("\n------------- Saldo final das contas dos funcionários -------------: ");
        System.out.println("Saldo da conta de " + loja1.primeiroFuncionario.nome + ": " + loja1.primeiroFuncionario.contaSalario.getSaldo());
        System.out.println("Saldo da conta de investimento de " + loja1.primeiroFuncionario.nome + ": " + loja1.primeiroFuncionario.contaInvestimento.getSaldo());
        System.out.println("Saldo da conta de " + loja1.segundoFuncionario.nome + ": " + loja1.segundoFuncionario.contaSalario.getSaldo());
        System.out.println("Saldo da conta de investimento de " + loja1.segundoFuncionario.nome + ": " + loja1.segundoFuncionario.contaInvestimento.getSaldo());
        System.out.println("Saldo da conta de " + loja2.primeiroFuncionario.nome + ": " + loja2.primeiroFuncionario.contaSalario.getSaldo());
        System.out.println("Saldo da conta de investimento de " + loja2.primeiroFuncionario.nome + ": " + loja2.primeiroFuncionario.contaInvestimento.getSaldo());
        System.out.println("Saldo da conta de " + loja2.segundoFuncionario.nome + ": " + loja2.segundoFuncionario.contaSalario.getSaldo());
        System.out.println("Saldo da conta de investimento de " + loja2.segundoFuncionario.nome + ": " + loja2.segundoFuncionario.contaInvestimento.getSaldo());

        // Verifica o saldo final de cada cliente
        System.out.println("\n------------- Saldo final dos clientes -------------: ");
        System.out.println("Saldo do " + cliente1.nome + ": " + cliente1.conta.getSaldo());
        System.out.println("Saldo do " + cliente2.nome + ": " + cliente2.conta.getSaldo());
        System.out.println("Saldo do " + cliente3.nome + ": " + cliente3.conta.getSaldo());
        System.out.println("Saldo do " + cliente4.nome + ": " + cliente4.conta.getSaldo());
        System.out.println("Saldo do " + cliente5.nome + ": " + cliente5.conta.getSaldo());
    }
}
