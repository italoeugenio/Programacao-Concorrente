public class Conta {
    private double saldo;
    private String titular;

    public Conta(double saldoInicial, String titular) {
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public synchronized void creditar(double valor) {
        saldo += valor;
    }

    public synchronized void debitar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente para debitar R$" + valor);
        }
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized String getTitular() {
        return titular;
    }
}
