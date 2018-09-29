package com.github.artemzi.hw09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private void connectToServer() throws IOException {

        try(Socket socket = new Socket("127.0.0.1", 19898);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);) {

            String response, msg = null;
            do {
                response = in.readLine();
                if (response == null) { // Turn of client if server closed
                    System.out.println("Connection closed by server.");
                } else {
                    System.out.println(response);

                    if (!(msg = scanner.nextLine()).equals("")) { // if got message from console send it to server
                        out.println(msg);
                        out.flush();
                    }
                }
            }
            while(response != null);
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connectToServer();
    }
}
