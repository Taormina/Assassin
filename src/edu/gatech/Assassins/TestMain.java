package edu.gatech.Assassins;


import com.google.gson.Gson;

import edu.gatech.Assassins.model.Request;
import edu.gatech.Assassins.model.User;

public class TestMain {
	
	public static void main(String[] args) {
		User u = new User("Tony", "ABCD$");
		Request r = new Request(Request.kill, u);
		Gson gson = new Gson();
		String js = gson.toJson(r);
		System.out.println(js);
	}

}
