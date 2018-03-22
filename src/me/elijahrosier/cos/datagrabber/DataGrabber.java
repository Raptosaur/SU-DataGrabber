package me.elijahrosier.cos.datagrabber;

import me.elijahrosier.cos.datagrabber.exceptions.InvalidLoginException;

public class DataGrabber {

	private User user;
	
	public DataGrabber(User u){
		
		this.user = u;
	
	}
	
	public DataGrabber(String username, String password) throws InvalidLoginException{
			
		this.user = new User(username, password);
		
	}
	
}
