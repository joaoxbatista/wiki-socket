package server.controllers;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.Main;
import server.helpers.Logger;
import server.models.Concept;
import java.lang.reflect.*;

public class ReflectionConceptController {

	public static void filter(String json_comand) {
		// Instancia o conversor para JSON
		JSONParser jsonParser = new JSONParser();
		// Objeto JSON que armazena os comandos
		JSONObject json_comands;

		// Parametros da requisição
		String comand_action = "";
		String comand_field;
		String comand_value;
		Method comand_method = null;

		// String com a localisação do controller
		String controller_class = "server.controllers.ConceptController";

		try {

			// Transforma a String em Objetos JSON
			json_comands = (JSONObject) jsonParser.parse(json_comand);

			// Obtem a actions da requisição
			comand_action = json_comands.get("action").toString();

			// Instancia a classe do controller
			Class<?> tcClass = Class.forName(controller_class);
			Object tc = tcClass.newInstance();

			// Filtragem das ações
			if (!comand_action.equals("")) {
				switch (comand_action) {
				case "all":
					// Chama o método all do ConceptController
					comand_method = tc.getClass().getMethod(comand_action);
					comand_method.invoke(tc);
					break;

				case "find":

					if (ReflectionConceptController.getConcept(json_comands) != null) {
						// Chama o método all do ConceptController
						Concept concept_find = ReflectionConceptController
								.getConcept(json_comands);
						comand_method = tc.getClass().getMethod(comand_action,
								Concept.class);
						comand_method.invoke(tc, concept_find);
					} else {
						Logger.log("503: parametros inválidos,  verifique o JSON enviado!");
					}

					break;

				case "store":
					if (ReflectionConceptController.getConcept(json_comands) != null) {
						// Chama o método all do ConceptController
						Concept concept_store = ReflectionConceptController
								.getConcept(json_comands);
						comand_method = tc.getClass().getMethod(comand_action,
								Concept.class);
						comand_method.invoke(tc, concept_store);
					} else {
						Logger.log("503: parametros inválidos,  verifique o JSON enviado!");
					}

					break;

				case "update":
					// Chama o método all do ConceptController
					if (ReflectionConceptController.getConcept(json_comands) != null) {
						// Chama o método all do ConceptController
						Concept concept_update = ReflectionConceptController
								.getConcept(json_comands);
						comand_method = tc.getClass().getMethod(comand_action,
								Concept.class);
						comand_method.invoke(tc, concept_update);
					} else {
						Logger.log("503: parametros inválidos,  verifique o JSON enviado!");
					}
					break;

				case "delete":
					// Chama o método all do ConceptController
					if (ReflectionConceptController.getConcept(json_comands) != null) {
						// Chama o método all do ConceptController
						Concept concept_remove = ReflectionConceptController
								.getConcept(json_comands);
						comand_method = tc.getClass().getMethod(comand_action,
								Concept.class);
						comand_method.invoke(tc, concept_remove.getCode());
					} else {
						Logger.log("503: parametros inválidos,  verifique o JSON enviado!");
					}

				default:
					Logger.log("401: action inexistente, por favor verifique o JSON enviado!");
					break;
				}

			}

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | ParseException e) {
			Logger.log("500: operação mal sucessedida, erro interno no servidor!");
			e.printStackTrace();
		}

	}

	public static Concept getConcept(JSONObject json_comands) {
		if (json_comands.get("params") != null) {
			// Obtem os parametros do conceito
			JSONObject comand_params = (JSONObject) json_comands.get("params");

			Concept concept_find = new Concept();
			if (comand_params.get("name") != null)
				concept_find.setName(comand_params.get("name").toString());

			if (comand_params.get("author") != null)
				concept_find.setAutor(comand_params.get("author").toString());

			if (comand_params.get("description") != null)
				concept_find.setDesciption(comand_params.get("description")
						.toString());

			if (comand_params.get("area") != null)
				concept_find.setArea(comand_params.get("area").toString());

			if (comand_params.get("code") != null)
				concept_find.setCode(comand_params.get("code").toString());

			return concept_find;
		}

		return null;

	}

	public static void main(String args[]) {
		ReflectionConceptController
				.filter("{\"action\":\"find\", \"params\": { \"name\": \"Processador\"} }");
		// ReflectionConceptController.filter("{\"action\":\"all\"}");
		// ReflectionConceptController.filter("{\"action\":\"delete\", \"params\": { \"name\": \"Processador\"} }");
		// ReflectionConceptController.filter("{\"action\":\"store\", \"params\": { \"name\": \"Processador\", \"author\": \"João Batista\", \"description\": \"circuito integrado capaz de efetuar o processamento de dados, obedecendo a um conjunto predeterminado de instruções.\", \"area\": \"computação\"} }");
	}
}
