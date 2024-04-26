import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Banco {

    private Lock lock = new ReentrantLock();

    public void transferir(Conta remetente, Conta destinatario, Double valor,
                           String nomeRemetente, String nomeDestinatario) {

        // Bloqueia o acesso para que apenas uma thread faça a transferência por vez
        lock.lock();

        try {
            // Verifica se o saldo do remetente é suficiente para a transferência
            if (remetente.getSaldo() >= valor) {
                System.out.println("Destinatário: " + nomeDestinatario);
                System.out.println("Remetente: " + nomeRemetente);
                System.out.println("Comprovante de transferência:");
                System.out.println("-------------------------------------------------------");
                System.out.println("Saldo do remetente: " + remetente.getSaldo());
                System.out.println("Saldo do destinatário: " + destinatario.getSaldo());
                System.out.println("Valor da transferência: " + valor);
                System.out.println("-------------------------------------------------------");
                System.out.println("\n");

                remetente.debitarSaldo(valor);
                destinatario.creditarSaldo(valor);
            }
        } finally {
            lock.unlock();
        }
    }
}
