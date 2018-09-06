package com.github.artemzi.hw09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Разработать клиент-серверный чат
 * 1. Система состоит из двух приложений: клиента и сервера
 * 2. В рантайме может быть любое количество экземпляров клиента. При старте каждому присваивается идентификатор
 * 3. Клиент из консоли отправляет сообщения, которые получают все остальные клиенты и выводят у себя в консоль
 * 4. Сервер отвечает за координацию клиентов и присвоение им идентификаторов
 * 5. Сервер в консоли логирует все сообщения всех клиентов
 */
public class ChatServer {

    public static void main(String[] args) throws Exception {

        int clientNumber = 0x12345;

        try(ServerSocket listener = new ServerSocket(19898);) {
            while (true) {
                new WorkerThread(listener.accept(), clientNumber++).start();
            }
        }
    }

    /**
     * A private thread to handle requests on a particular
     * socket.
     */
    private static class WorkerThread extends Thread {
        private Socket socket;
        private int clientNumber;

        WorkerThread(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            System.out.printf("[new connection client# %s] at %s%n", clientNumber, socket);
        }

        @Override
        public void run() {
            try(BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

                // Send a welcome message to the client.
                out.println("Hello, your id are #" + clientNumber + ".");

                while (true) {
                    String input = in.readLine();
                    System.out.printf("[LOG] client #%s send:%n\t %s%n", clientNumber, input);
                    out.println("Got your message");
                }
            } catch (IOException e) {
                System.err.printf("[error with client# %s] %s%n", clientNumber, e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Can't close a socket");
                }
                System.out.printf("[client# %s] closed%n", clientNumber);
            }
        }
    }
}
