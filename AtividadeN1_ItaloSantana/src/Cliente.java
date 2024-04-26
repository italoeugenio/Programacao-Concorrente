import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente extends Thread {
    Banco banco;
    Conta conta;
    Loja loja1;
    Loja loja2;
    String nome;
    Random random = new Random();
    private Lock lock = new ReentrantLock();

    // Construtor da classe Cliente
    public Cliente(Banco banco, Loja loja1, Loja loja2, String nome) {
        this.banco = banco;
        this.conta = new Conta();
        this.conta.creditarSaldo(1000.0); // Inicializa a conta com saldo de 1000.0
        this.loja1 = loja1;
        this.loja2 = loja2;
        this.nome = nome;
    }

    // Método run, que será executado quando a thread for iniciada
    public void run() {
        lock.lock();
        try {
            while (this.conta.getSaldo() > 0.00) {
                // Gera um valor aleatório de transferência entre 100.00 e 200.00
                Double valorTransferencia = random.nextInt(2) == 0 ? 100.00 : 200.00;
                // Seleciona aleatoriamente uma das duas lojas
                Loja lojaAleatoria = random.nextInt(2) == 0 ? this.loja1 : this.loja2;
                // Transfere o valor para a loja selecionada
                banco.transferir(this.conta, lojaAleatoria.conta, valorTransferencia, this.nome, lojaAleatoria.nomeLoja);
            }
        } finally {
            lock.unlock(); // Libera o bloqueio após a execução do trecho crítico
        }
    }
}
