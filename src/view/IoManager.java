package view;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class IoManager {

	Scanner scanner = new Scanner(System.in);

	public void showMessage(String message) {
		System.out.println(message);
	}

	public String readString(String message) {
		this.showMessage(message);
		return scanner.nextLine();
	}

	public byte readByte(String message) {
		return Byte.parseByte(this.readString(message));
	}

	public short readShort(String message) {
		return Short.parseShort(this.readString(message));
	}

	public int readInt(String message) {
		return Integer.parseInt(this.readString(message));
	}

	public long readLong(String message) {
		return Long.parseLong(this.readString(message));
	}

	public float readFloat(String message) {
		return Float.parseFloat(this.readString(message));
	}

	public double readDouble(String message) {
		return Double.parseDouble(this.readString(message));
	}

	public char readChar(String message) {
		return message.charAt(0);
	}

	public void showGraphicMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public String readGraphicString(String message) {
		return JOptionPane.showInputDialog(message);
	}

	public byte readGraphicByte(String message) {
		return Byte.parseByte(this.readGraphicString(message));
	}

	public short readGraphicShort(String message) {
		return Short.parseShort(this.readGraphicString(message));
	}

	public int readGraphicInt(String message) {
		return Integer.parseInt(this.readGraphicString(message));
	}

	public long readGraphicLong(String message) {
		return Long.parseLong(this.readGraphicString(message));
	}

	public float readGraphicFloat(String message) {
		return Float.parseFloat(this.readGraphicString(message));
	}

	public double readGraphicDouble(String message) {
		return Double.parseDouble(this.readGraphicString(message));
	}

	public char readGraphicChar(String message) {
		String output = this.readGraphicString(message);
		return output.charAt(0);
	}

	public int readMenu() {
		String menu = "--------------Menu--------------\n" +
				"1. Imprimir datos de la empresa \n" +
				"2. Adicionar un empleado \n" +
				"3. Borrar un empleado \n" +
				"4. Modificar un empleado \n" +
				"5. Mostrar datos de un empleado \n" +
				"6. Mostrar todos los empleados \n" +
				"7. Liquidar \n" +
				"8. Salir \n";
		return this.readGraphicInt(menu);
	}

	public int readModifyOptions(){
		String options ="--------------Menu--------------\n" +
				"1. Modificar nombre \n" +
				"2. Modificar apellido \n" +
				"3. Modificar salario \n" +
				"4. Modificar numero de hijos \n" +
				"5. Modificar comisión \n" +
				"6. Modificar la fecha de nacimiento \n" +
				"7. Añadir la fecha de contratación \n" +
				"8. Salir \n";
		return this.readGraphicInt(options);
	}

	public void showErrorGraphicMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}
