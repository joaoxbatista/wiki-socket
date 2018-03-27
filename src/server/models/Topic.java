package server.models;

public class Topic {
	private String title;
	private Discipline discipline;
	private String text;
	private String code;
	
	public Topic() {}

	public Topic(String title, Discipline discipline, String text, String code) {
		super();
		this.title = title;
		this.discipline = discipline;
		this.text = text;
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Topico " + code + "\nTítulo: " +  title + "\nDisciplina: " + discipline.getName() + "\nConteúdo: " + text;
		
	}
	
	
}
