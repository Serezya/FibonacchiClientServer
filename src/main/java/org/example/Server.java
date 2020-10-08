package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(19527);
        System.out.println("Server started!");
        try (Socket socket = serverSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String msg;
            if ((msg = in.readLine()) != null) {
                int n = Integer.parseInt(msg.trim());
                int result = getFibonacci(n);
                out.println("n-ое число ряда Фибоначчи: " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static int getFibonacci(int n) {
       return Stream.iterate(new int[] {1, 1}, f -> new int[] {f[1], f[0] + f[1]})
                .limit(n)
                .reduce((a, b) -> b)
                .get()[0];
    }
}