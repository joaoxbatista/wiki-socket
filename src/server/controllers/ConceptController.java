package server.controllers;

import server.helpers.Logger;
import server.models.Concept;
import server.repository.ConceptRepositoy;

public class ConceptController {

	public String find(Concept concept) {
		String result = "";
		ConceptRepositoy tr = new ConceptRepositoy();
		try {
			result += tr.find(concept).toString();
				
		} catch (Exception e) {
			
		}
		
		if(!result.equals("")){
			Logger.log(result);
			return "200 - " + result;
		}
		else
		{
			return "404: não foi possível encontrar nenhum conceito com estes parametros";
		}
		
	}

	public String delete(Concept concept) {
		String result = "";
		Logger.log("conceito informado para remoção" + concept.getCode());
		ConceptRepositoy tr = new ConceptRepositoy();
		try {
			result = tr.remove(concept.getCode()).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!result.equals("")){
			Logger.log(result);
			return "200 - " + result;
		}
		else
		{
			return "404: não foi possível encontrar nenhum conceito com estes parametros";
		}
	}

	public String store(Concept concept) {
		String result = "";
		ConceptRepositoy tr = new ConceptRepositoy();
		result += tr.store(concept).toString();
		Logger.log(result);
		return result;
	}
	
	//TODO criar a função de update
	public String update(Concept concept) {
		String result = "";
		ConceptRepositoy tr = new ConceptRepositoy();
		result += tr.update(concept).toString();
		Logger.log(result);
		return result;
	}
	
	public String all() {
		String result = "";
		ConceptRepositoy tr = new ConceptRepositoy();
		result += tr.all().toString();
		Logger.log(result);
		return result;
	}

}
