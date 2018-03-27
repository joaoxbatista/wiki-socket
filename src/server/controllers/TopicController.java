package server.controllers;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.Main;
import server.models.Topic;

public class TopicController {
	
	private ArrayList<Topic> topics;
	
	public ArrayList<Topic> filter(String comando) {
		
		JSONParser jsonParser = new JSONParser(); //Instancia o conversor para JSON
		JSONObject comandos; //Objeto JSON que armazena os comandos
		
		ArrayList<Topic> resultTopics = new ArrayList<Topic>(); //ArrayList para armazenar os tópicos resultantes

		try {
			comandos = (JSONObject) jsonParser.parse(comando); //Transforma a String em Objetos JSON

			if (comandos.get("action").toString().equals("get")) {
				
				if (comandos.get("field").toString().equals("title")) {
					for (Topic topic : this.topics) {
						if (topic.getTitle().equals(
								comandos.get("value").toString())) {
							resultTopics.add(topic);
						}
					}

				}

				if (comandos.get("field").toString().equals("discipline")) {
					for (Topic topic : Main.topics) {
						if (topic.getDiscipline().getName()
								.equals(comandos.get("value").toString())) {
							resultTopics.add(topic);
						}
					}

				}

				if (comandos.get("field").toString().equals("area")) {

					for (Topic topic : Main.topics) {
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
}
