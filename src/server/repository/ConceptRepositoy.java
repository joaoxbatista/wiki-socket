package server.repository;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.db4o.ObjectSet;

import server.models.*;
import server.database.*;
import server.helpers.Logger;

public class ConceptRepositoy {
	/*
	 * Method: store Return: Concept Description: Salva um concept no banco de
	 * dados e retorna o objeto salvo
	 */
	public Concept store(Concept concept) {
		try {
			Database.getInstance().store(concept);
		} catch (Exception e) {

		}
		Database.close();
		Logger.log("200: operação realizada com sucesso!");
		return concept;
	}

	/*
	 * Method: update Return: Concept Description: Atualiza os atributos de um
	 * concept
	 */
	public Concept update(Concept concept) {
		Concept conceptresult = null;
		Concept conceptBusca = new Concept();
		conceptBusca.setCode(concept.getCode());

		try {
			ObjectSet<?> resultObject = Database.getInstance().queryByExample(
					conceptBusca);
			conceptresult = (Concept) resultObject.next();
			conceptresult.setName(concept.getName());
			conceptresult.setArea(concept.getArea());
			conceptresult.setAutor(concept.getAutor());
			conceptresult.setDesciption(concept.getDesciption());
			Logger.log("200: operação realizada com sucesso!");
			Database.getInstance().store(conceptresult);
			
		} catch (Exception e) {
			Logger.log("500: operação mal sucessedida, erro interno no servidor!"
					+ e.getMessage());
		}

		Database.close();

		return conceptresult;
	}

	/*
	 * Method: all Return: ArrayList<Concept> Description: Retorna uma lista com
	 * os concepts cadastrados
	 */
	public ArrayList<Concept> all() {
		ArrayList<Concept> listaConcepts = new ArrayList<>();
		try {
			ObjectSet<?> resultObject = Database.getInstance().queryByExample(
					Concept.class);
			for (Object concept : resultObject) {
				listaConcepts.add((Concept) concept);
			}
			Logger.log("200: operação realizada com sucesso!");
		} catch (Exception e) {
			Logger.log("500: operação mal sucessedida, erro interno no servidor!"
					+ e.getMessage());
		}
		Database.close();

		return listaConcepts;
	}

	/*
	 * Method: find Return: Concept Description: Procura um concept pelo código
	 * do concept no banco de dados
	 */
	public Concept find(Concept concept) {
		Concept conceptresult = null;
		try {
			ObjectSet<Concept> resultObject = Database.getInstance().queryByExample(concept);
			if(!resultObject.isEmpty())
			{
				conceptresult = (Concept) resultObject.next();
				Logger.log("200: operação realizada com sucesso!");
			}
			else
			{
				Logger.log("404: não foi possível encontrar nenhum conceito com este código!");
			}
			

		} catch (Exception e) {
			Logger.log("500: operação mal sucessedida, erro interno no servidor!");
			e.printStackTrace();
		}
		Database.close();

		return conceptresult;
	}

	/*
	 * Method: remove Return: Concept Description: Remove um concept do banco de
	 * dados
	 */
	public Concept remove(String code) {
		Concept concept = new Concept();
		Concept conceptresult = null;
		try {
			concept.setCode(code);
			ObjectSet<?> resultObejct = Database.getInstance().queryByExample(
					concept);
			if(!resultObejct.isEmpty())
			{
				conceptresult = (Concept) resultObejct.next();
				Logger.log("Conceito encontrado para remção: "+ conceptresult);
				Database.getInstance().delete(conceptresult);
				Logger.log("200: operação realizada com sucesso!");
			}
			
			
		} catch (Exception e) {
			Logger.log("500: operação mal sucessedida, erro interno no servidor!"
					+ e.getMessage());;
					e.printStackTrace();
		}

		Database.close();
		return conceptresult;
	}
}
