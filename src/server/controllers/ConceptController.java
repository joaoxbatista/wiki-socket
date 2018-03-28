package server.controllers;

import server.helpers.Logger;
import server.models.Concept;
import server.repository.ConceptRepositoy;

public class ConceptController {

	public void find(Concept concept) {
		ConceptRepositoy tr = new ConceptRepositoy();
		try {
			Logger.log(tr.find(concept).toString());
		} catch (Exception e) {
			
		}
	}

	public void delete(Concept concept) {
		ConceptRepositoy tr = new ConceptRepositoy();
		
		try {
			Logger.log(tr.remove(concept.getCode()).toString());
		} catch (Exception e) {
			
		}
		
	}

	public void store(Concept concept) {
		ConceptRepositoy tr = new ConceptRepositoy();
		Logger.log(tr.store(concept).toString());
	}

	public void all() {
		ConceptRepositoy tr = new ConceptRepositoy();
		Logger.log(tr.all().toString());
	}

}
