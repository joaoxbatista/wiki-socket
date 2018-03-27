package server.controllers;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.Main;
import server.models.Topic;
import java.lang.reflect.*;

public class ReflectionTopicController {

	public static void filter(String json_comand)
	{
		JSONParser jsonParser = new JSONParser(); //Instancia o conversor para JSON
		JSONObject comands; //Objeto JSON que armazena os comandos
		String comand_action;
		String comand_field;
		String comand_value;
		Method save_method;
		String class_name = "server.controllers.TopicController";
		
		try {
			comands = (JSONObject) jsonParser.parse(json_comand); //Transforma a String em Objetos JSON
			comand_action = comands.get("action").toString();
			
			Class<?> tcClass = Class.forName(class_name);
			Object tc = tcClass.newInstance();
			
			if(comand_action.equals("delete"))
			{
				comand_field = "code";
				comand_value = comands.get("value").toString();
				save_method = tc.getClass().getMethod(comand_action, String.class);
				save_method.invoke(tc, comand_value);
			}
			else if(comand_action.equals("all"))
			{
				save_method = tc.getClass().getMethod(comand_action);
				save_method.invoke(tc);
			}
			else
			{
				comand_field = comands.get("field").toString();
				comand_value = comands.get("value").toString();
				save_method = tc.getClass().getMethod(comand_action, String.class, String.class);
				save_method.invoke(tc, comand_field, comand_value);
			}
			
			
			

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[])
	{
		ReflectionTopicController.filter("{\"action\":\"find\", \"field\":\"title\", \"value\":\"Plantas Trópicais\"}");
		ReflectionTopicController.filter("{\"action\":\"delete\", \"value\":\"T4\"}");
		ReflectionTopicController.filter("{\"action\":\"all\"}");
	}
}
