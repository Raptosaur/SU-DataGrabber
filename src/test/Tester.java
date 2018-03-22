package test;

import me.elijahrosier.cos.datagrabber.User;
import me.elijahrosier.cos.datagrabber.exceptions.InvalidLoginException;
import me.elijahrosier.cos.datagrabber.timetable.Timetable;
import me.elijahrosier.cos.datagrabber.timetable.TimetableSlot;

public class Tester {

	public static void main(String[] args){
		User u = null;
		try {
			u = new User("123456", "45678910");
		} catch (InvalidLoginException e) {
			e.printStackTrace();
		}
		if(u != null){
			System.out.println("Logged in as "+u.getUsername()+" ("+u.getName()+")");
			
			TimetableSlot[][] timetable = Timetable.getUsersTimetable(u);
			
			for(int i = 0; i< 5; i++){
				System.out.println("--------------------------------------");
				for(int j = 0; j < 9; j++){
					System.out.println((j+9)+":00 " + (timetable[j][i] != null ? timetable[j][i] : ""));
				}
			}
			
		}
	}
	
}
