public class Cliente extends Thread {

    private Banco banco;
    private Conta conta;

    public Cliente(Banco banco) {
        this.banco = banco;
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
        Loja loja1 = new Loja("Loja 1", banco);
        Loja loja2 = new Loja("Loja 2", banco);

        while (conta.getSaldo() > 0) {
            Loja lojaCompra = (conta.getSaldo() >= 200.00) ? loja2 : loja1;
            double valorCompra = (conta.getSaldo() >= 200.00) ? 200.00 : 100.00;

            System.out.printf("Cliente %s comprando R$ %.2f na loja %s\n", this.getName(), valorCompra, lojaCompra.getNome());
            comprar(lojaCompra, valorCompra);
        }

        System.out.printf("Cliente %s finalizou as compras. Saldo final: R$ %.2f\n", this.getName(), conta.getSaldo());
    }
}
