package batepapo.primeirochat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private final List<GerenciadorCliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        new Servidor().iniciarServidor(8081);
    }

    public void iniciarServidor(int porta) {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            // COR AZUL
            System.out.println("\u001B[34m Servidor foi aberto na porta " + porta + "\u001B[0m");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("\u001B[32m Novo cliente conectado: " + clienteSocket + "\u001B[0m");

                GerenciadorCliente gerenciadorCliente = new GerenciadorCliente(clienteSocket);
                clientes.add(gerenciadorCliente);
                new Thread(gerenciadorCliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mensagensPublicas(String menssagem, GerenciadorCliente enviador) throws IOException {
        for (GerenciadorCliente cliente : clientes) {
            if (cliente != enviador) {
                cliente.saida.writeObject(enviador.getUsername() + ": " + menssagem);
            }
        }
    }

    private void mensagensPrivadas(String menssagem, GerenciadorCliente enviador) {
        String[] partes = menssagem.split(" ", 3);
        if (partes.length == 3) {
            String username = partes[1];
            String mensagemPrivada = partes[2];

            for (GerenciadorCliente cliente : clientes) {
                if (cliente != enviador && cliente.getUsername().equals(username)) {
                    try {
                        cliente.saida.writeObject("\u001B[35mMensagem privada de " + enviador.getUsername() + ": " + mensagemPrivada + "\u001B[0m");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }

            try {
                // COR VERMELHA
                enviador.saida.writeObject("\u001B[31m O usuário " + username + "não foi encontrado :( \u001B[0m");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void removerCliente(GerenciadorCliente cliente) {
        clientes.remove(cliente);
        System.out.println("\u001B[31m cliente desconectado: " + cliente.getclienteSocket() + "\u001B[0m");
    }

    private class GerenciadorCliente implements Runnable {
        private final Socket clienteSocket;
        private final ObjectOutputStream saida;
        private final ObjectInputStream entrada;
        private String username;

        public GerenciadorCliente(Socket socket) throws IOException {
            this.clienteSocket = socket;
            this.saida = new ObjectOutputStream(socket.getOutputStream());
            this.entrada = new ObjectInputStream(socket.getInputStream());
        }

        public Socket getclienteSocket() {
            return clienteSocket;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public void run() {
            try {
                saida.writeObject("Digite o seu username: \u001B[0m");
                this.username = (String) entrada.readObject();
                saida.writeObject("\u001B[32mBem vindo ao bate papo " + username + "! \u001B[0m");

                while (true) {
                    String menssagem = (String) entrada.readObject();
                    String[] partes = menssagem.split(" ", 3);
                    if (partes[0].equals("/sussurrar")) {
                        mensagensPrivadas(menssagem, this);
                    } else {
                        mensagensPublicas(menssagem, this);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                removerCliente(this);
            }
        }
    }
}