import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {

    // Declaração do objeto de bloqueio para garantir que apenas uma thread execute a transferência por vez
    private Lock lock = new ReentrantLock();

    // Método para transferir dinheiro entre contas
    public void transferir(Conta remetente, Conta destinatario, Double valor,
                           String nomeRemetente, String nomeDestinatario) {

        lock.lock();

        try {
            // Verifica se pode fazer transferência de acordo com o que tem na conta
            if (remetente.getSaldo() >= valor) {
                // Mensagens a serem exibidas como comprovante de transferência
                String destinatarioStr = "Recebedor: " + nomeDestinatario;
                String remetenteStr = "Pagador: " + nomeRemetente;
                String comprovante = "TRANSFERÊNCIA:";
                String separador = "-------------------------------------------------------";
                String saldoRemetente = "Saldo do Pagador: " + remetente.getSaldo();
                String saldoDestinatario = "Saldo do Recebedor: " + destinatario.getSaldo();
                String valorTransferencia = "Valor da transferência: " + valor;
                String linhaSeparadora = "-------------------------------------------------------";

                // Impressão do comprovante de transferência
                System.out.println(destinatarioStr);
                System.out.println(remetenteStr);
                System.out.println(comprovante);
                System.out.println(separador);
                System.out.println(saldoRemetente);
                System.out.println(saldoDestinatario);
                System.out.println(valorTransferencia);
                System.out.println(linhaSeparadora);
                System.out.println("\n");

                // Deduz o valor transferido do saldo do remetente e adiciona ao saldo do destinatário
                remetente.debitarSaldo(valor);
                destinatario.creditarSaldo(valor);
            }
        } finally {
            // Libera o bloqueio para permitir que outras threads acessem o método
            lock.unlock();
        }
    }
}
