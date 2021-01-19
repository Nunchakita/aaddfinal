package Modelo;
//import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Date;

@SuppressWarnings("serial")
public class Cliente implements java.io.Serializable{
	//variables que mapean la table cliente
	private int idcliente;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String comercial;
	private int idempresa;
	private ArrayList<LocalDate> fechas;
	//constructor con todos los parámetros menos el idcliente que no es requerido 
	public Cliente(String nombre, String apellido1, String apellido2, String comercial, int idempresa, ArrayList<LocalDate> fechas) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.comercial = comercial;
		this.idempresa = idempresa;
		this.fechas = fechas;
	}
	//constructor sin parametros
	public Cliente() {
	}
	//getter y setter de la clase Cliente
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getComercial() {
		return comercial;
	}
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}
	public int getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}
	public ArrayList<LocalDate> getFechas() {
		return fechas;
	}
	public void setFechas(ArrayList<LocalDate> fechas) {
		this.fechas = fechas;
	}
	@Override
	public String toString() {
		return "Cliente [idcliente=" + idcliente + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", comercial=" + comercial + ", idempresa=" + idempresa + "]";
	}
}
