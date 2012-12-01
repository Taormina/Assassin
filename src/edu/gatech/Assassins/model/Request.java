package edu.gatech.Assassins.model;

public class Request {

	public static final String kill = "KILL", register = "REGISTER",
			target = "TARGET", ack = "ACK", nack = "NACK", status = "STATUS",
			start = "START", restart = "RESTART";

	private String type;
	private User user;
	private User victim;
	private int remaining;

	public Request() {
		// no-args constructor
	}

	public Request(String type, User user) {
		this.type = type;
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public User getUser() {
		return user;
	}

}
