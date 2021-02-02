package model;

public class Operator {

	private String name;
	private String surname;
	private int rights;
	
	public Operator(String name, String surname, int rights) {
		super();
		this.name = name;
		this.surname = surname;
		this.rights = rights;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
		
}
