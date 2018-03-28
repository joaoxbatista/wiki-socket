package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

import server.models.Area;
import server.models.Discipline;
import server.models.Concept;
import server.repository.ConceptRepositoy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//Aplicação Servidor
//TODO Comentar a classe
public class Main {

	private static int number_client = 0;
	private static ArrayList<Concept> topics = new ArrayList<Concept>();

	public static void main(String args[]) {
		Main.topics = Main.readTopics();
		//System.out.println(Main.topics);
		try {
			System.out.println(Main.log("Servidor iniciado na porta 12345"));
			ServerSocket servidor = new ServerSocket(12345);

			System.out.println(Main.log("Esperando por clientes"));
			Socket cliente = servidor.accept();

			ObjectOutputStream client_out = new ObjectOutputStream(
					cliente.getOutputStream());

			number_client = 0;

			Scanner scanner = new Scanner(cliente.getInputStream());

			while (scanner.hasNextLine()) {
				System.out.println(log("Cliente número " + number_client));
				String json = scanner.nextLine();
				ArrayList<Concept> topics = Main.filter(json);
				client_out.writeObject(topics.toString());
			}
			scanner.close();
			servidor.close();
			cliente.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public static ArrayList<Concept> filter(String comando) {
		
		// TODO Abstrair a parte de filtragem para uma classe Controller ou algo do tipo
		JSONParser jsonParser = new JSONParser();
		JSONObject comandos;
		ArrayList<Concept> resultTopics = new ArrayList<Concept>();

		try {
			comandos = (JSONObject) jsonParser.parse(comando);
			//TODO Desenvolver um filtro para cadastro de Tópicos
			//TODO Alterar a action get para "find"
			if (comandos.get("action").toString().equals("get")) {
				if (comandos.get("field").toString().equals("title")) {
					for (Concept topic : Main.topics) {
						if (topic.getTitle().equals(
								comandos.get("value").toString())) {
							resultTopics.add(topic);
						}
					}

				}

				if (comandos.get("field").toString().equals("discipline")) {
					for (Concept topic : Main.topics) {
						if (topic.getDiscipline().getName()
								.equals(comandos.get("value").toString())) {
							resultTopics.add(topic);
						}
					}

				}

				if (comandos.get("field").toString().equals("area")) {

					for (Concept topic : Main.topics) {
						if (topic.getDiscipline().getName()
								.equals(comandos.get("value").toString())) {
							resultTopics.add(topic);
						}
					}

				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultTopics;
	}

	public static ArrayList<Concept> readTopics() {
		ConceptRepositoy tr = new ConceptRepositoy();
		if (tr.all().size() < 1) {
			Main.createTopics();
		}

		ArrayList<Concept> topics = tr.all();
		return topics;
	}

	public static void createTopics() {
		Area a1 = new Area(
				"Computação",
				"Aliquam erat volutpat. Nam sagittis suscipit turpis nec facilisis. Nullam non posuere tellus. Quisque ornare rhoncus quam, nec elementum ex dictum.");
		Area a2 = new Area(
				"Biologia",
				"Aliquam erat volutpat. Nam sagittis suscipit turpis nec facilisis. Nullam non posuere tellus. Quisque ornare rhoncus quam, nec elementum ex dictum.");
		Area a3 = new Area(
				"Arquitetura",
				"Aliquam erat volutpat. Nam sagittis suscipit turpis nec facilisis. Nullam non posuere tellus. Quisque ornare rhoncus quam, nec elementum ex dictum.");

		Discipline d1 = new Discipline(
				"Computação Gráfica",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis nunc sem, eget ultrices nisl tristique eget. Vestibulum vestibulum pellentesque neque, ut scelerisque libero dictum eget. Sed libero sapien, elementum ut mollis ac, iaculis et quam. Cras maximus nibh diam, id aliquet est interdum non. Nam a odio eu ipsum imperdiet fermentum. Vestibulum sed dolor in purus tempor tempor. Donec massa neque, lobortis tincidunt nisi ut, accumsan lobortis purus. Fusce vel tellus imperdiet, elementum justo eget, sodales purus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce tellus libero, bibendum non ligula sit amet, pharetra auctor nibh. Duis sem velit, mollis non purus eu, laoreet rhoncus eros. Sed pretium euismod sodales. Praesent ullamcorper dui in dignissim vulputate.",
				a1);
		Discipline d2 = new Discipline(
				"Expreção Gráfica",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis nunc sem, eget ultrices nisl tristique eget. Vestibulum vestibulum pellentesque neque, ut scelerisque libero dictum eget. Sed libero sapien, elementum ut mollis ac, iaculis et quam. Cras maximus nibh diam, id aliquet est interdum non. Nam a odio eu ipsum imperdiet fermentum. Vestibulum sed dolor in purus tempor tempor. Donec massa neque, lobortis tincidunt nisi ut, accumsan lobortis purus. Fusce vel tellus imperdiet, elementum justo eget, sodales purus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce tellus libero, bibendum non ligula sit amet, pharetra auctor nibh. Duis sem velit, mollis non purus eu, laoreet rhoncus eros. Sed pretium euismod sodales. Praesent ullamcorper dui in dignissim vulputate.",
				a3);
		Discipline d3 = new Discipline(
				"Morfologia Vegetal",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis nunc sem, eget ultrices nisl tristique eget. Vestibulum vestibulum pellentesque neque, ut scelerisque libero dictum eget. Sed libero sapien, elementum ut mollis ac, iaculis et quam. Cras maximus nibh diam, id aliquet est interdum non. Nam a odio eu ipsum imperdiet fermentum. Vestibulum sed dolor in purus tempor tempor. Donec massa neque, lobortis tincidunt nisi ut, accumsan lobortis purus. Fusce vel tellus imperdiet, elementum justo eget, sodales purus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce tellus libero, bibendum non ligula sit amet, pharetra auctor nibh. Duis sem velit, mollis non purus eu, laoreet rhoncus eros. Sed pretium euismod sodales. Praesent ullamcorper dui in dignissim vulputate.",
				a3);

		Concept t1 = new Concept(
				"Coordenadas Homogeneas",
				d1,
				"Morbi tincidunt, lectus id molestie hendrerit, ante risus pharetra quam, at ultricies felis ex eu est. Nunc convallis nibh libero, eu euismod tortor sagittis in. Praesent id ex ac nisl semper luctus in id tellus. Cras dignissim ante ipsum. Fusce sit amet turpis ut diam consequat sagittis sed quis mi. Mauris ullamcorper dolor eget malesuada mollis. Sed eu facilisis ante. Praesent enim nisi, auctor nec sodales eu, posuere non magna. Nulla vel augue sed lorem viverra hendrerit. Suspendisse leo tortor, efficitur vitae mi in, euismod pharetra diam. Morbi gravida massa quis sapien eleifend elementum. In nec elit laoreet, tincidunt felis nec, pellentesque ipsum. Aenean feugiat auctor justo. In vel ornare justo. Maecenas sed facilisis eros. Integer consequat sit amet urna sed placerat.",
				"T1");
		Concept t2 = new Concept(
				"Transformações Geométricas",
				d2,
				"Morbi tincidunt, lectus id molestie hendrerit, ante risus pharetra quam, at ultricies felis ex eu est. Nunc convallis nibh libero, eu euismod tortor sagittis in. Praesent id ex ac nisl semper luctus in id tellus. Cras dignissim ante ipsum. Fusce sit amet turpis ut diam consequat sagittis sed quis mi. Mauris ullamcorper dolor eget malesuada mollis. Sed eu facilisis ante. Praesent enim nisi, auctor nec sodales eu, posuere non magna. Nulla vel augue sed lorem viverra hendrerit. Suspendisse leo tortor, efficitur vitae mi in, euismod pharetra diam. Morbi gravida massa quis sapien eleifend elementum. In nec elit laoreet, tincidunt felis nec, pellentesque ipsum. Aenean feugiat auctor justo. In vel ornare justo. Maecenas sed facilisis eros. Integer consequat sit amet urna sed placerat.",
				"T2");
		Concept t3 = new Concept(
				"Plantas Trópicais",
				d3,
				"Morbi tincidunt, lectus id molestie hendrerit, ante risus pharetra quam, at ultricies felis ex eu est. Nunc convallis nibh libero, eu euismod tortor sagittis in. Praesent id ex ac nisl semper luctus in id tellus. Cras dignissim ante ipsum. Fusce sit amet turpis ut diam consequat sagittis sed quis mi. Mauris ullamcorper dolor eget malesuada mollis. Sed eu facilisis ante. Praesent enim nisi, auctor nec sodales eu, posuere non magna. Nulla vel augue sed lorem viverra hendrerit. Suspendisse leo tortor, efficitur vitae mi in, euismod pharetra diam. Morbi gravida massa quis sapien eleifend elementum. In nec elit laoreet, tincidunt felis nec, pellentesque ipsum. Aenean feugiat auctor justo. In vel ornare justo. Maecenas sed facilisis eros. Integer consequat sit amet urna sed placerat.",
				"T2");

		ConceptRepositoy tr = new ConceptRepositoy();

		tr.store(t1);
		tr.store(t2);
		tr.store(t3);
	}

	public static String log(String msg) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return "[ time: " + dateFormat.format(date) + " ] - " + msg;
	}
}
