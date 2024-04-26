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
            System.out.println("Erro ao criar as contas do funcionário. Verifique se as contas podem ser inicializadas corretamente na classe Funcionário.");
        }

        this.contaSalario.creditarSaldo(0.0);
        this.contaInvestimento.creditarSaldo(0.0);
    }

    // Método para investir 20% do saldo da conta salário na conta de investimento
    void investirDinheiro() {
        double valorInvestido = this.contaSalario.getSaldo() * 0.2;
        System.out.println("O " + this.nome + " está investindo 20% do saldo da conta salário.");
        System.out.println("Valor investido: R$" + valorInvestido);
        System.out.println("Saldo atual da conta salário: R$" + this.contaSalario.getSaldo());
        System.out.println("\n");

        contaInvestimento.creditarSaldo(valorInvestido);
        contaSalario.debitarSaldo(valorInvestido);
    }
}
