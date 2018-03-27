package server.models;

public class Discipline {
	private String name;
	private String desciption;
	private Area area;

	public Discipline(String name, String desciption, Area area) {
		super();
		this.name = name;
		this.desciption = desciption;
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String toString() {
		return "Discipline [name=" + name + ", desciption=" + desciption
				+ ", area=" + area + "]";
	}

}
