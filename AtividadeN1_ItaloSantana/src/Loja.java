import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Loja {
    Conta conta;
    Banco banco;
    Funcionario primeiroFuncionario;
    Funcionario segundoFuncionario;
    String nomeLoja;
    private Lock lock = new ReentrantLock();

    public Loja(Banco banco, String nomeLoja) {
        this.banco = banco;
        this.nomeLoja = nomeLoja;
        this.conta = new Conta();
        this.conta.creditarSaldo(0.0);
        this.primeiroFuncionario = new Funcionario(banco, "1° funcionário da loja " + this.nomeLoja);
        this.segundoFuncionario = new Funcionario(banco, "2° funcionário da loja " + this.nomeLoja);


        // Utilizando expressão lambda
        this.conta.addPropertyChangeListener(evt -> {
            lock.lock();
            try {
                if (evt.getPropertyName().equals("saldo")) {
                    if (conta.getSaldo() >= 1400) {
                        pagarFuncionario();
                    }
                }
            } finally {
                lock.unlock();
            }
        });
    }


    void pagarFuncionario() {
        if (this.conta.getSaldo() >= 1400) {
            System.out.println(this.nomeLoja + " com 1400 na conta.");
            System.out.println("Pagamento do funcionário: " + primeiroFuncionario.nome);
            System.out.println("\n");

            // Verifica qual funcionário tem saldo menor e transfere o pagamento para ele
            if (this.primeiroFuncionario.contaSalario.getSaldo() > this.segundoFuncionario.contaSalario.getSaldo()) {
                banco.transferir(this.conta, this.segundoFuncionario.contaSalario, 1400.00, this.nomeLoja, this.segundoFuncionario.nome);
                this.segundoFuncionario.investirDinheiro();
            } else {
                banco.transferir(this.conta, this.primeiroFuncionario.contaSalario, 1400.00, this.nomeLoja, this.primeiroFuncionario.nome);
                this.primeiroFuncionario.investirDinheiro();
            }
        }
    }
}
