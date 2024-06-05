import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        new Cliente().iniciarCliente(8081);
    }

    public void iniciarCliente(int porta) {
        try (Socket socket = new Socket("localhost", porta);
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado ao servidor. Enviando mensagem...");

            String mensagem = "SECRETA";
            saida.println(mensagem);

            String resposta = entrada.readLine();
            System.out.println("Resposta do servidor: " + resposta);

        } catch (IOException e) {
            System.out.println("Servidor não está ligado");
        }
    }
}