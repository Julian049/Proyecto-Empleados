package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Employee {
	private short id;
	private String firstName;
	private String lastName;
	private double salary;
	private byte numberChildren;
	private double commission;
	private Date birthDate;
	public ArrayList<Date> hireDates;

	public Employee(short id, String firstName, String lastName, Date birthDate, ArrayList<Date> hireDates) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.hireDates = hireDates;
	}

	public Employee(short id, String firstName, String lastName, double salary, byte numberChildren, double commission,
			Date birthDate, ArrayList<Date> hireDates) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.numberChildren = numberChildren;
		this.commission = commission;
		this.birthDate = birthDate;
		this.hireDates = hireDates;

	}

	public Employee() {
		hireDates = new ArrayList<Date>();
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public byte getNumberChildren() {
		return numberChildren;
	}

	public void setNumberChildren(byte numberChildren) {
		this.numberChildren = numberChildren;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public ArrayList<Date> getHireDates() {
		return hireDates;
	}

	public Date getHireDate(int index) {
		return hireDates.get(index);
	}

	public void setHireDate(ArrayList<Date> hireDates) {
		this.hireDates = hireDates;
	}

	public String getStringHireDates() {
		String output = "";
		Iterator<Date> hireDate = hireDates.iterator();
		while (hireDate.hasNext()) {
			Date date = hireDate.next();
			output += date.toString() + "\n";
		}
		return output;
	}

	public Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		return new Date((short) calendar.get(Calendar.DAY_OF_MONTH), (short) (calendar.get(Calendar.MONTH) + 1),
				(short) calendar.get(Calendar.YEAR));
	}

	public int getDistanceBetweenDates(Date date) {
		Date currentDate = this.getCurrentDate();
		int distance = 0;
		if (currentDate.getMonth() < date.getMonth()
				|| (currentDate.getMonth() == date.getMonth() && currentDate.getDay() < date.getDay())) {
			distance = (currentDate.getYear() - date.getYear()) - 1;
		} else {
			distance = (currentDate.getYear() - date.getYear());
		}
		return distance;
	}

	public int getAge() {
		return this.getDistanceBetweenDates(birthDate);
	}

	public void addHireDate(Date date) {
		hireDates.add(date);
	}

	// public int getAntiquity() {
	// 	return this.getDistanceBetweenDates(hireDates);
	// }

	public String toString() {
		return "ID: " + id + "\n" + "Nombre: " + this.firstName + " " + lastName + "\n" + "Salario: " + salary + "\n"
				+ "Numero de hijos: " + numberChildren + "\n" + "Comisión: " + commission + "%" + "\n"
				+ "Fecha de nacimiento:  " + birthDate + "\n"
				+ "Fecha de contratación: " + hireDates + "\n";
	}

}
