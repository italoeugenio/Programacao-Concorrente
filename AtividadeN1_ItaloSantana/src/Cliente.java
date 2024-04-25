import java.util.List;

public class Cliente extends Thread {

    private Banco banco;
    private final Conta conta;
    private Loja[] lojas;

    public Cliente(Banco banco, Loja ... lojas) {
        this.banco = banco;
        this.lojas = lojas;
        this.conta = new Conta(1000.0, "Titular da Conta do Cliente");
    }

    public void comprar(Loja loja, double valorCompra) {
        try {
            synchronized (conta) {
                banco.transferir(conta, loja.getContaLoja(), valorCompra);
            }
        } catch (Exception e) {
            System.out.printf("Cliente %s: Erro durante a compra na loja %s (Valor: R$ %.2f)\n", this.getName(), loja.getNome(), valorCompra);
        }
    }

    @Override
    public void run() {
        while (conta.getSaldo() > 0) {
            Loja lojaCompra = Math.random() < 0.5 ? lojas[0] : lojas[1];
            double valorCompra = Math.random() < 0.5 ? 100 : 200;

            System.out.printf("Cliente %s comprando R$ %.2f na loja %s\n", this.getName(), valorCompra, lojaCompra.getNome());
            comprar(lojaCompra, valorCompra);
        }

        System.out.printf("Cliente %s finalizou as compras. Saldo final: R$ %.2f\n", this.getName(), conta.getSaldo());
    }
}
