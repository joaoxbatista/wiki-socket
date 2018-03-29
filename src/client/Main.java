package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//Aplicação Cliente
public class Main {

	public static void main(String args[])
	{
		Socket server;
		try {
			server = new Socket("localhost", 12345);
			PrintStream saida = new PrintStream(server.getOutputStream());
			Scanner scanner = new Scanner(System.in);
			ObjectInputStream server_out = new ObjectInputStream(server.getInputStream());
			System.out.println(log("Cliente conectado com sucesso! Servidor aguardando requisições:"));
			while(scanner.hasNextLine())
			{
				saida.println(scanner.nextLine());

				try {
					//TODO Fazer um JOPtionPane para exibir os resultados
					System.out.println(server_out.readObject());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				System.out.println(log("Cliente conectado com sucesso! Servidor aguardando requisições:"));
			}

			saida.close();
			scanner.close();
			server.close();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static String log(String msg) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return "[ time: " + dateFormat.format(date) + " ] - " + msg;
	}
}
