package batepapo.segundochat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Escreva seu nickname: ");
        Scanner scanner = new Scanner(System.in);
        String usuario = scanner.nextLine();

        try(Socket socket = new Socket("localhost", 8089);
            BufferedReader leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter escritor = new PrintWriter(socket.getOutputStream());
        ){

            escritor.println(usuario);

            Thread receptor = new Thread(() ->{
                try{
                    String mensagemRecebida;
                    while ((mensagemRecebida = leitor.readLine()) != null){
                        System.out.println(mensagemRecebida);
                    }
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            });
            receptor.start();

            while(true){
                System.out.println("Escreva uma mensagem: ");
                String mensagem = scanner.nextLine();

                if (mensagem.equalsIgnoreCase("sair")) break;

                escritor.println(mensagem);
            }
            System.out.println("Saindo do bate-papo...");
            receptor.interrupt();

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
