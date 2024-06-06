import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        new Cliente().iniciarCliente(12345);
    }

    public void iniciarCliente(int porta) {
        try (Socket socket = new Socket("10.130.129.103", porta);
             OutputStream saida = socket.getOutputStream();
             InputStream entrada = socket.getInputStream()) {

            System.out.println("Conectado ao servidor. Enviando mensagem...");

            //MjAwMg==
            String mensagem = "MjAwMg==";
            byte[] mensagemBytes = mensagem.getBytes();
            saida.write(mensagemBytes);
            saida.flush();

            byte[] buffer = new byte[1024];
            int bytesRead = entrada.read(buffer);

            String resposta = new String(buffer, 0, bytesRead);
            System.out.println("Resposta do servidor: " + resposta);

        } catch (IOException e) {
            System.out.println("Servidor não está ligado");
        }
    }
}
