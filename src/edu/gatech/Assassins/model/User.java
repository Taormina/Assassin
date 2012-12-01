package edu.gatech.Assassins.model;

public class User {
	
	private String name;
	private String pic;
	
	public User(){
	    // no-args constructor
	}
	
	public User(String name, String pic){
		this.name = name;
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public String getPic() {
		return pic;
	}
	
}
