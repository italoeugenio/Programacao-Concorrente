public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco();

        Loja loja1 = new Loja("Loja 1", banco);
        Funcionario funcionario1Loja1 = new Funcionario(new Conta(0.0, "Salário Funcionário 1 Loja 1"), new Conta(0.0, "Investimento Funcionário 1 Loja 1"), loja1);
        Funcionario funcionario2Loja1 = new Funcionario(new Conta(0.0, "Salário Funcionário 2 Loja 1"), new Conta(0.0, "Investimento Funcionário 2 Loja 1"), loja1);

        Loja loja2 = new Loja("Loja 2", banco);
        Funcionario funcionario1Loja2 = new Funcionario(new Conta(0.0, "Salário Funcionário 1 Loja 2"), new Conta(0.0, "Investimento Funcionário 1 Loja 2"), loja2);
        Funcionario funcionario2Loja2 = new Funcionario(new Conta(0.0, "Salário Funcionário 2 Loja 2"), new Conta(0.0, "Investimento Funcionário 2 Loja 2"), loja2);

        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < 5; i++) {
            clientes[i] = new Cliente(banco, loja1, loja2);
            clientes[i].start();
        }

        funcionario1Loja1.start();
        funcionario2Loja1.start();
        funcionario1Loja2.start();
        funcionario2Loja2.start();

        try {
            for (Cliente cliente : clientes) {
                cliente.join();
            }
            funcionario1Loja1.join();
            funcionario2Loja1.join();
            funcionario1Loja2.join();
            funcionario2Loja2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldos finais:");
        System.out.println("Conta Loja 1: R$" + loja1.getContaLoja().getSaldo());
        System.out.println("Conta Loja 2: R$" + loja2.getContaLoja().getSaldo());
        System.out.println("Conta Funcionário 1 Loja 1: R$" + Funcionario.getSalario());
        System.out.println("Conta Funcionário 2 Loja 1: R$" + Funcionario.getSalario());
        System.out.println("Conta Funcionário 1 Loja 2: R$" + Funcionario.getSalario());
        System.out.println("Conta Funcionário 2 Loja 2: R$" + Funcionario.getSalario());

    }
}
