package me.elijahrosier.cos.datagrabber.timetable;

public class TimetableSlot {

	private String moduleName;
	private String lecturer;
	private String location;
	
	public TimetableSlot(String moduleName, String lecturer, String location){
		this.moduleName = moduleName;
		this.lecturer = moduleName;
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}

	public String getLecturer() {
		return lecturer;
	}

	public String getModuleName() {
		return moduleName;
	}
	
	public String toString(){
		return this.moduleName + " by " + this.lecturer + " @ " + this.location;
	}
	
}
