package controller;

import exceptions.DuplicateException;
import model.Business;
import model.Date;
import model.Employee;
import persistence.MyFile;
import view.IoManager;

public class Control {
	private IoManager io;
	private Business business;
	private MyFile myFile;

	public Control() {
		io = new IoManager();
		myFile = new MyFile("C:/Users/Julian/Documents/Programacion I/P_employeeV2/data/employees.csv");

	}

	public void init() {
		business = new Business();
		business.setName(io.readGraphicString("Nombre del negocio"));
		business.setCity(io.readGraphicString("Ciudad"));
		business.loadEmployees(myFile);
		this.menu();
	}

	public void menu() {
		int option = 0;
		do {
			try {
				option = io.readMenu();
				switch (option) {
					case 1:
						this.printBusinessData();
						break;
					case 2:
						this.createEmployee();
						break;
					case 3:
						this.deleteEmployee();
						break;
					case 4:
						this.modifyEmployee();
						break;
					case 5:
						this.printEmployeeData();
						break;
					case 6:
						this.printEmployeesData();
						break;
					case 7:
						this.liquidateEmployee();
						break;
					case 8:
						io.showGraphicMessage("El programa ha finalizado");
						business.recordEmployees(myFile);
						break;
					default:
						io.showErrorGraphicMessage("Opción incorrecta");
				}
			} catch (NumberFormatException e) {
				io.showErrorGraphicMessage("Debe ingresar un numero entero");
			}
		} while (option != 8);
	}

	private void printBusinessData() {
		io.showGraphicMessage(business.toString());
	}

	private void createEmployee() {
		Employee employee = new Employee();
		try {
			short id = io.readGraphicShort("Id del empleado");
			business.checkNewEmployee(id);
			employee.setId(id);
			employee.setFirstName(io.readGraphicString("Nombre del empleado"));
			employee.setLastName(io.readGraphicString("Apellido del empleado"));
			employee.setSalary(io.readGraphicDouble("Salario del empleado"));
			employee.setNumberChildren(io.readGraphicByte("Números de hijos del empleado"));
			employee.setCommission(io.readGraphicDouble("Comisión del empleado"));
			io.showGraphicMessage("Digite la fecha de nacimiento del empleado");
			employee.setBirthDate(this.getDate());
			io.showGraphicMessage("Digite la fecha de contratación del empleado");
			employee.setHireDate(this.getDate());
			business.addEmployee(employee);
		} catch (DuplicateException duplicateException) {
			io.showErrorGraphicMessage(duplicateException.getMessage());
		}
	}

	public Date getDate() {
		Date date = new Date();
		date.setDay(io.readGraphicShort("Dia"));
		date.setMonth(io.readGraphicShort("Mes"));
		date.setYear(io.readGraphicShort("Año"));
		return date;
	}

	private void deleteEmployee() {
		business.deleteEmployee(io.readGraphicShort("Digite el ID del empleado a borrar: "));
	}

	private void modifyEmployee() {
		short id = io.readGraphicShort("Digite el ID del empleado a modificar: ");
		int option = 0;
		do {
			option = io.readModifyOptions();
			switch (option) {
				case 1:
					business.findEmployee(id)
							.setFirstName(io.readGraphicString("Digite el nuevo nombre del empleado: "));
					break;
				case 2:
					business.findEmployee(id)
							.setLastName(io.readGraphicString("Digite el nuevo apellido del empleado: "));
					break;
				case 3:
					business.findEmployee(id).setSalary(io.readGraphicDouble("Digite el nuevo salario del empleado: "));
					break;
				case 4:
					business.findEmployee(id)
							.setNumberChildren(io.readGraphicByte("Digite el nuevo numero de hijos del empleado: "));
					break;
				case 5:
					business.findEmployee(id).setCommission(io.readDouble("Digite la nueva comisión del empleado"));
					break;
				case 6:
					business.findEmployee(id).setBirthDate(this.getDate());
					break;
				case 7:
					business.findEmployee(id).setHireDate(this.getDate());
					break;
				case 8:
					io.showGraphicMessage("Los datos del empleado han sido modificados con éxito");
					break;
			}
		} while (option != 8);
	}

	private void printEmployeeData() {
		io.showGraphicMessage(
				business.findEmployee(io.readGraphicShort("Digite el ID del empleado para imprimir la información: "))
						.toString());
	}

	private void printEmployeesData() {
		io.showGraphicMessage(business.myGetStringEmployeesArrayListV2());
	}

	private void liquidateEmployee() {
		short id = io.readGraphicShort("Digite el ID del empleado a liquidar: ");
		double bonus = io.readGraphicDouble("Ingrese alguna bonificación extra: ");
		double deductions = io.readGraphicDouble("Ingrese el valor de la multa: ");
		double liquidation = business.calculateLiquidation(id, bonus, deductions);
		io.showGraphicMessage("El empleado " + business.findEmployee(id).getFirstName() + " " + business.findEmployee(id).getLastName() + " con ID " + id + " ha sido liquidado con un valor de: " + liquidation);
	}
}
