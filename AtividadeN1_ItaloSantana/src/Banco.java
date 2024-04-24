public class Banco {

    public synchronized void transferir(Conta remetente, Conta destinatario, double valor) {
        if (remetente.getSaldo() >= valor) {
            remetente.debitar(valor);
            destinatario.creditar(valor);
            System.out.println("Transferência de R$" + valor + " realizada de " + remetente.getTitular() + " para " + destinatario.getTitular());
        } else {
            System.out.println("Saldo insuficiente em " + remetente.getTitular() + " para transferência de R$" + valor);
        }
    }
}
