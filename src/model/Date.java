package model;

public class Date {
	private short day;
	private short month;
	private short year;

	
	public Date(short day, short month, short year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public Date(){
		
	}

	public short getDay() {
		return day;
	}

	public void setDay(short day) {
		this.day = day;
	}

	public short getMonth() {
		return month;
	}

	public void setMonth(short month) {
		this.month = month;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return day + "/" + month + "/" + year;
	}

}
