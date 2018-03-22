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
			
			if(table != null){
				Elements rows = table.select("tr");
				
				for(int i = 0; i < rows.size(); i++){
					if(i != 0){
						Element row = rows.get(i);
						
						Elements days = row.select("td");
						
						for(int j = 0; j < days.size(); j++){
							
							Element slot = days.get(j).selectFirst(".lecture");
							if(slot != null){
								String moduleName = slot.selectFirst("strong").text();
								String moduleLecturer = slot.selectFirst("span") != null ? slot.selectFirst("span").text() : "Unknown";
								String moduleLocation = slot.selectFirst(".lectureinfo.room") != null ? slot.selectFirst(".lectureinfo.room").text() : "Unknown";
								
								modules[i-1][j] = new TimetableSlot(moduleName, moduleLecturer, moduleLocation);
							}
							
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
