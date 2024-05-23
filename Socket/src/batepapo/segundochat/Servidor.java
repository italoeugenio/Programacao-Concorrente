package batepapo.segundochat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class Servidor {

    private static final int PORTA = 8089;

    private static Set<PrintWriter> escritores = new HashSet<>();

    public static void main(String[] args) {
        // Cria um servidor socket que escuta na porta especificada.(PASSAR O SOCKER COMO PARAMETRO AJUDA A TER UM CONTROLE MAIOR)
        try(ServerSocket server = new ServerSocket(PORTA)){
            System.out.println("Servidor está rodando na porta " + PORTA);
            while (true){
                //Esperar as conexões
                //criar threads sempre para lidar com cada conexão
                new Cliente(
                        server.accept(),
                        escritores
                ).start();
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
