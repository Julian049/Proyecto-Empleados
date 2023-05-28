package model;

import java.util.ArrayList;
import java.util.Iterator;
import exceptions.DuplicateException;
import persistence.MyFile;

public class Business {
	private String name;
	private String city;
	private ArrayList<Employee> employeesArrayList;

	public ArrayList<Employee> readEmployeesFile() {
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ArrayList<Employee> getEmployeesArrayList() {
		return employeesArrayList;
	}

	public String myGetStringEmployeesArrayList() {
		String output = "";
		Iterator<Employee> iteratorEmployees = employeesArrayList.iterator();
		while (iteratorEmployees.hasNext()) {
			Employee employee = iteratorEmployees.next();
			output += employee.toString() + "\n";
		}
		return output;
	}

	public String myGetStringEmployeesArrayListV2() {
		String output = "";
		for (Employee employee : employeesArrayList) {
			if (employee != null)
				output += employee + "\n";
		}
		return output;
	}

	public String getStringEmployeesArray() {
		String output = "";
		return output;
	}

	public void setEmployeesArrayList(ArrayList<Employee> employeesArrayList) {
		this.employeesArrayList = employeesArrayList;
	}

	public Business() {
		employeesArrayList = new ArrayList<Employee>();
	}

	public void addEmployee(Employee employee) {
		employeesArrayList.add(employee);
	}

	public void deleteEmployee(short id) {
		Iterator<Employee> iteratorEmployees = employeesArrayList.iterator();
		while (iteratorEmployees.hasNext()) {
			Employee employee = iteratorEmployees.next();
			if (employee.getId() == id) {
				iteratorEmployees.remove();
			}
		}
	}

	public Employee checkNewEmployee(short id) throws DuplicateException {
		DuplicateException duplicateException;
		Employee employee = null;
		for (Employee employeeList : employeesArrayList) {
			if (employeeList != null && employeeList.getId() == id) {
				duplicateException = new DuplicateException("El ID ya se encuentra registrado");
				throw duplicateException;
			}
		}
		return employee;
	}

	public Employee findEmployee(short id) {
		int start = 0;
		int finalPos = employeesArrayList.size() - 1;
		int mid;
		Employee output = null;

		while (employeesArrayList.get(start).getId() != id && start < finalPos) {
			mid = (finalPos + start) / 2;
			if (employeesArrayList.get(mid).getId() < id) {
				start = mid + 1;
			} else {
				finalPos = mid - 1;
			}
		}

		output = employeesArrayList.get(start);
		return output;
	}

	public Date createDate(String dateString) {
		String[] dateRead = dateString.split("/");
		Date date = new Date(Short.parseShort(dateRead[0]), Short.parseShort(dateRead[1]),
				Short.parseShort(dateRead[2]));
		return date;
	}

	public void loadEmployees(MyFile myFile) {
		myFile.openFile('r');
		String input = "";
		String[] employeeData;
		while ((input = myFile.read()) != null) {
			employeeData = input.split(",");
			Employee employee = new Employee(Short.parseShort(employeeData[0]), employeeData[1], employeeData[2],
					Double.parseDouble(employeeData[3]), Byte.parseByte(employeeData[4]),
					Double.parseDouble(employeeData[5]), this.createDate(employeeData[6]), new ArrayList<Date>());

			//Se añade un ciclo for para leer las distintas fechas de contratación
			for (int i = 7; i < employeeData.length; i++) {
				employee.addHireDate(this.createDate(employeeData[i]));
			}
			this.addEmployee(employee);
		}
		myFile.closeFile();
	}

	public void recordEmployees(MyFile myFile) {
		myFile.openFile('w');
		String output;
		for (Employee employee : employeesArrayList) {
			output = "";
			output += employee.getId() + ",";
			output += employee.getFirstName() + ",";
			output += employee.getLastName() + ",";
			output += employee.getSalary() + ",";
			output += employee.getNumberChildren() + ",";
			output += employee.getCommission() + ",";
			output += employee.getBirthDate() + ",";

			//Se añade un ciclo for para añadir las distintas fechas de contratación
			ArrayList<Date> hireDates = employee.getHireDates();
			for (int i = 0; i < hireDates.size(); i++) {
				output += hireDates.get(i);
				if (i < hireDates.size() - 1) {
					output += ",";
				}
			}
			myFile.record(output);
		}
		myFile.closeFile();
	}

	public int monthsWorked(Date date) {
		Employee employee = new Employee();
		int output = 0;
		output = (employee.getDistanceBetweenDates(date) * 12);
		return output;
	}

	public double calculateLiquidation(short id, double bonus, double reductions) {
		double output = 0;
		Employee employee = this.findEmployee(id);
		output = ((employee.getSalary() * this.monthsWorked(employee.getHireDate(employee.hireDates.size() - 1)))
				+ (employee.getSalary() * employee.getCommission()) + bonus) - reductions;
		this.deleteEmployee(id);
		return output;
	}

	public void addHireDate(short id, Date date) {
		Employee employee = this.findEmployee(id);
		employee.addHireDate(date);
	}

	@Override
	public String toString() {
		return "Empresa: Nombre: " + name + ", Ciudad: " + city;
	}

}
