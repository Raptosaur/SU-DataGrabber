package me.elijahrosier.cos.datagrabber;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.elijahrosier.cos.datagrabber.exceptions.InvalidLoginException;

public class User {
	
	private String csrfToken = "";
	private String sessionId = "";
	private String username = "";
	private String name = "";
	
	public User(String username, String password) throws InvalidLoginException{
		
		this.username = username;
		
		// First of all, connect to get our csrfToken for future use.
		try {
			
			Connection initialConnection = Jsoup.connect("https://science.swansea.ac.uk/intranet/accounts/login/?next=/intranet/");
			initialConnection.get();

			for(String cookieKey : initialConnection.response().cookies().keySet()){
				if(cookieKey.equals("csrftoken")){
					this.csrfToken = initialConnection.response().cookies().get(cookieKey);
				}else if(cookieKey.equals("sessionid")){
					this.sessionId = initialConnection.response().cookies().get(cookieKey);
				}
			}
		} catch (IOException e) {
			System.out.println("Error occurred whilst trying to get sessionId and csrfToken.");
		}
		
		// Now we have both csrfToken and sessionId.
		try{
			
			Connection loginConnection = Jsoup.connect("https://science.swansea.ac.uk/intranet/accounts/login/?next=/intranet/attendance/timetable");
			
			loginConnection.cookie("sessionid", this.sessionId);
			loginConnection.cookie("csrftoken", this.csrfToken);
			
			loginConnection.data("csrfmiddlewaretoken", this.csrfToken);
			loginConnection.data("username", username);
			loginConnection.data("password", password);
			loginConnection.data("next", "/intranet/attendance/timetable");
			
			Document doc = loginConnection.post();
			
			for(String cookieKey : loginConnection.response().cookies().keySet()){
				if(cookieKey.equals("csrftoken")){
					this.csrfToken = loginConnection.response().cookies().get(cookieKey);
				}else if(cookieKey.equals("sessionid")){
					this.sessionId = loginConnection.response().cookies().get(cookieKey);
				}
			}
			
			if(doc.select("#logout strong").size() > 0){
				this.name = doc.select("#logout strong").get(0).text();
			}else{
				throw new InvalidLoginException();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public Document getPage(String absolutePath) throws IOException{
		
		Connection getPage = Jsoup.connect("https://science.swansea.ac.uk/" + absolutePath);
		
		getPage.cookie("sessionid", this.sessionId);
		getPage.cookie("csrftoken", this.csrfToken);
		
		for(String cookieKey : getPage.response().cookies().keySet()){
			if(cookieKey.equals("csrftoken")){
				this.csrfToken = getPage.response().cookies().get(cookieKey);
			}else if(cookieKey.equals("sessionid")){
				this.sessionId = getPage.response().cookies().get(cookieKey);
			}
		}
		
		return getPage.get();
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getUsername(){
		return this.username;
	}

}
