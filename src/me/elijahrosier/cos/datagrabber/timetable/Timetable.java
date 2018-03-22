package me.elijahrosier.cos.datagrabber.timetable;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.elijahrosier.cos.datagrabber.User;

public class Timetable {
	
	public static TimetableSlot[][] getUsersTimetable(User user){
		TimetableSlot[][] modules = new TimetableSlot[9][5];
		
		try {
			Document doc = user.getPage("intranet/attendance/timetable");
			
			Element table = doc.selectFirst("#timetable");
			
			//System.out.println(doc.html());
			
			if(table != null){
				Elements rows = table.select("tr");
				System.out.println("Test");
				
				for(int i = 0; i < rows.size(); i++){
					if(i != 0){
						Element row = rows.get(i);
						
						Elements days = row.select("td");
						
						for(int j = 0; j < days.size(); j++){
							modules[i-1][j] = new TimetableSlot("Something","Something","Something");
						}
						
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return modules;
	}
	
}
