public class Funcionario extends Thread {
    public static final double SALARIO = 1400.0;
    private static final double PERCENTUAL_INVESTIMENTO = 0.2;

    private Conta contaSalario;
    private Conta contaInvestimento;
    private Loja loja;

    public Funcionario(Conta contaSalario, Conta contaInvestimento, Loja loja) {
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
        this.loja = loja;
    }

    @Override
    public void run() {
        while (true) {
            receberSalario();
            investir();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static double getSalario() {
        return SALARIO;
    }

    private synchronized void receberSalario() {
        contaSalario.creditar(SALARIO);
        System.out.printf("Funcionário %s recebeu salário de R$ %.2f da loja %s\n", this.getName(), SALARIO, loja.getNome());
    }

    private synchronized void investir() {
        double valorInvestimento = SALARIO * PERCENTUAL_INVESTIMENTO;
        contaSalario.debitar(valorInvestimento);
        contaInvestimento.creditar(valorInvestimento);
        System.out.printf("Funcionário %s investiu R$ %.2f na conta de investimento\n", this.getName(), valorInvestimento);
    }
}
