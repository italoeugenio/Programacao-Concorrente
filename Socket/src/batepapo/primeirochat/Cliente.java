package batepapo.primeirochat;

import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        new Cliente().iniciarCliente(8081);
    }

    public void iniciarCliente(int porta) {
        //TROQUE O HOST PELO IP DO PC QUE VAI INICIAR O SERVIDOR
        try (Socket socket = new Socket("localhost", porta);
             ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            // COR VERDE
            System.out.println("\u001B[32mDigite /sair caso deseje se desconectar");
            System.out.println("Caso deseje enviar uma mensagem privada, digite /sussurar (username) (mensagem)");

            Thread ThreadMensagens = new Thread(() -> {
                try {
                    while (true) {
                        String menssagem = scanner.nextLine();
                        saida.writeObject(menssagem);

                        if ("/sair".equals(menssagem)) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ThreadMensagens.start();

            while (true) {
                String menssagem = (String) entrada.readObject();
                System.out.println(menssagem);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Servidor não está ligado");
        }
    }
}