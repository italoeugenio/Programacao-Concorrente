public class Funcionario extends Thread {
    Conta contaSalario;
    Conta contaInvestimento;
    Banco banco;
    String nome;

    public Funcionario(Banco banco, String nome) {
        this.banco = banco;
        this.nome = nome;

        try {
            this.contaInvestimento = new Conta();
            this.contaSalario = new Conta();
        } catch (Exception e) {
            System.out.println("Problema ao criar a conta na classe Funcionário.");
        }

        this.contaSalario.creditarSaldo(0.0);
        this.contaInvestimento.creditarSaldo(0.0);
    }

    void investirDinheiro() {
        System.out.println("Investindo 20% da conta, totalizando: " + this.contaSalario.getSaldo() * 0.2);
        System.out.println("\n");
        contaInvestimento.creditarSaldo(this.contaSalario.getSaldo() * 0.2);
        contaSalario.debitarSaldo(this.contaSalario.getSaldo() * 0.2);
    }
}
