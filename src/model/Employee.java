package model;

import java.util.Calendar;

public class Employee {
	private short id;
	private String firstName;
	private String lastName;
	private double salary;
	private byte numberChildren;
	private double commission;
	private Date birthDate;
	private Date hireDate;

	public Employee(short id, String firstName, String lastName, Date birthDate, Date hireDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
	}
	public Employee(short id, String firstName, String lastName, double salary, byte numberChildren, double commission, Date birthDate, Date hireDate) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.numberChildren = numberChildren;
		this.commission = commission;
		this.birthDate = birthDate;
		this.hireDate = hireDate;

	}

	public Employee(){
		
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

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		return new Date((short) calendar.get(Calendar.DAY_OF_MONTH), (short) (calendar.get(Calendar.MONTH) + 1),
				(short) calendar.get(Calendar.YEAR));
	}

	public int getDistanceBetweenDates(Date date) {
		Date currentDate = this.getCurrentDate();
		int distance = 0;
		if (currentDate.getMonth() < date.getMonth() || (currentDate.getMonth() == date.getMonth() && currentDate.getDay() < date.getDay())) {
			distance = (currentDate.getYear() - date.getYear()) - 1;
		} else {
			distance = (currentDate.getYear() - date.getYear());
		}
		return distance;
	}
	
	public int getAge() {
		return this.getDistanceBetweenDates(birthDate);
	}

	public int getAntiquity() {
		return this.getDistanceBetweenDates(hireDate);
	}

	public String toString() {
		return "ID: " + id + "\n" + "Nombre: " + this.firstName + " " + lastName + "\n" + "Salario: " + salary + "\n"
				+ "Numero de hijos: " + numberChildren + "\n" + "Comisión: " + commission + "%" + "\n" + "Fecha de nacimiento:  " + birthDate + "\n"
				+ "Fecha de contratación: " + hireDate + "\n";
	}

}
