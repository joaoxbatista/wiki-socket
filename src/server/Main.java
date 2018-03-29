package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

import server.controllers.ReflectionConceptController;
import server.helpers.Logger;
import server.models.Concept;
import server.repository.ConceptRepositoy;

//Aplicação Servidor
//TODO Comentar a classe
public class Main {

	private static int number_client = 0;
	private static ArrayList<Concept> concepts = new ArrayList<Concept>();

	public static void main(String args[]) {
		Main.concepts = Main.hydratate();
		
		try {
			
			//Cria um socket para o servidor se comunicar
			ServerSocket server_socket = new ServerSocket(12345);
			
			Logger.log("Servidor iniciado na porta 12345! Esperando por client_sockets:");
			
			//Cria um socket para o client_socket se comunicar 
			Socket client_socket = server_socket.accept();

			//Cria um objeto para imprimir os resultados para o cliente
			ObjectOutputStream oos_client = new ObjectOutputStream(client_socket.getOutputStream());

			//Obtem um scanner das entradas do cliente
			Scanner scanner = new Scanner(client_socket.getInputStream());
			
			//Em quanto o cliente digitar alguma coisa
			while (scanner.hasNextLine()) {
				Logger.log("Cliente " + client_socket.getInetAddress().getHostName() + " conectado com sucesso!");
				//Faz o filtro da entrada e retorna o resultado da operação
				String json = scanner.nextLine();
				String result = ReflectionConceptController.filter(json);

				//Escreve para o cliente
				oos_client.writeObject(result);
			}
			scanner.close();
			server_socket.close();
			client_socket.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static ArrayList<Concept> hydratate() {
		ConceptRepositoy tr = new ConceptRepositoy();
		if (tr.all().size() < 1) {
			Main.seeds();
		}

		ArrayList<Concept> topics = tr.all();
		return topics;
	}

	public static void seeds() {

		ConceptRepositoy cp = new ConceptRepositoy();
		Concept con1 = new Concept("Sistema Operacional", "José Marcos", "Sistema operativo (português europeu) ou operacional (português brasileiro) (em inglês: Operating System - OS) é um programa ou um conjunto de programas cuja função é gerenciar os recursos do sistema (definir qual programa recebe atenção do processador, gerenciar memória, criar um sistema de arquivos, etc.),", "Computação");
		Concept con2 = new Concept("Hardware", "José Marcos", "equipamento mecânico necessário para realização de uma determinada atividade.", "Computação");
		
		cp.store(con1);
		cp.store(con2);
	}

}
