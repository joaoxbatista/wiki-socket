package server.models;

import java.util.Date;
import java.util.Random;

public class Concept {

	private String name;
	private String autor;
	private String desciption;
	private String area;
	private String code;

	public Concept() {
		// Data utilizada para gerar um código único
		Date date = new Date();
		this.code = "C" + Long.toString(date.getTime() / 100000);
	}

	public Concept(String name, String autor, String desciption, String area) {
		// Data utilizada para gerar um código único
		Date date = new Date();
		this.name = name;
		this.autor = autor;
		this.desciption = desciption;
		this.area = area;

		// Pega as 3 primeiras letras dá area e concatena com o timestamps atual
		this.code = this.area.substring(0, 3)
				+ Long.toString(date.getTime() / 100000);
		this.code = this.code.toUpperCase();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
		Date date = new Date();
		// Pega as 3 primeiras letras dá area e concatena com o timestamps atual
		this.code = this.area.substring(0, 3)
				+ Long.toString(date.getTime() / 100000);
		this.code = this.code.toUpperCase();
	}

	@Override
	public String toString() {
		return "Concept [name=" + name + ", autor=" + autor + ", desciption="
				+ desciption + ", area=" + area + ", code=" + code + "]";
	}

}
