import java.util.ArrayList;
import java.util.List;

public class Loja {
    private String nome;
    private Conta contaLoja;
    private List<Funcionario> funcionarios;

    public Loja(String nome, Banco banco) {
        this.nome = nome;
        this.contaLoja = new Conta(0.0, "Titular da Conta Loja");
        this.funcionarios = new ArrayList<>();
    }

    public synchronized void receberPagamento(double valor) {
        contaLoja.creditar(valor);
        System.out.printf("Loja %s recebeu um pagamento de R$ %.2f\n", nome, valor);
        notifyAll();
    }

    public synchronized void pagarFuncionario(Funcionario funcionario) {
        while (contaLoja.getSaldo() < Funcionario.getSalario()) {
            try {
                System.out.printf("Loja %s aguardando saldo suficiente para pagar funcionário %s\n", nome, funcionario.getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        contaLoja.debitar(Funcionario.getSalario());
        System.out.printf("Loja %s pagou R$ %.2f para o funcionário %s\n", nome, Funcionario.getSalario(), funcionario.getName());
    }

    public void contrataFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public int numeroDeFuncionarios() {
        return this.funcionarios.size();
    }

    public String getNome() {
        return nome;
    }

    public Conta getContaLoja() {
        return contaLoja;
    }
}
