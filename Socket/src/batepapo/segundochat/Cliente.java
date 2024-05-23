package batepapo.segundochat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public class Cliente extends Thread {

   //SOCKET ESPECIFICO DO CLIENTE
   private Socket socket;

   //ESCREVER PARA ESSE CLIENTE
   private PrintWriter escritor;

   private Set<PrintWriter> listaDeEscritores;

   Cliente(Socket socket, Set<PrintWriter> escritores){
       this.socket = socket;
       listaDeEscritores = escritores;
   }

    //SERVE PARA GERENCIAR O BATE PAPO
    @Override
    public void run() {
       try{
           BufferedReader leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           escritor = new PrintWriter(socket.getOutputStream(),true); //autoFlush ajuda com o buffer

           synchronized (listaDeEscritores){
               listaDeEscritores.add(escritor);
           }

           String usuario = leitor.readLine();
           transmitir(usuario + " entrou no bate-papo"); // Se utilizar o system.out.println só vai está "printando" para o servidor, por isso precisamos utilizar o transmitir
           System.out.println(usuario + "cpnectpu-se ao servidor");
           String mensagem;
            while ((mensagem = leitor.readLine()) != null){
                if (mensagem.equals("sair")) break;

                transmitir(usuario + ": " + mensagem);
            }

            synchronized (listaDeEscritores){
                listaDeEscritores.remove(escritor);
            }

            transmitir(usuario + " saiu do bate-papo");
           System.out.println(usuario + "desconectouse");
       } catch (IOException e){
           e.printStackTrace();
       }
    }

    //PRIVADO, POIS SÓ VAI SER UTILIZAOD AQUI DENTRO
    private void transmitir(String mensagem){
       synchronized (listaDeEscritores){
           listaDeEscritores.forEach(escritor -> {
               escritor.println(mensagem);
           });
       }
    }
}