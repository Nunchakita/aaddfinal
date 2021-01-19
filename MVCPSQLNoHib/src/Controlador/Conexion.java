package Controlador;
import java.sql.*;
public class Conexion{
static Connection conexion1=null;
private static Statement secc;
	 public static Connection accesoBD(){
		 try{
			 // Se carga el driver postresql de la siguiente forma:
			 Class.forName("org.postgresql.Driver");
			 // Crear un objeto conexión:
			 conexion1 = DriverManager.getConnection ("jdbc:postgresql://localhost:5432/aaddcp1ud4","postgres", "laura");
			 // Se crea un objeto de tipo Statement, para realizar la consulta:
			 secc = conexion1.createStatement();
		 }catch (Exception e){
			 e.printStackTrace();
		}
		 return conexion1;
	 }
}