package Controlador;
import Modelo.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//CRUD es la clase que va a interactuar directamente con la Base de Datos y la clase de Conexion que se necesita para conectarse a la BBDD
public class CRUD {
	//Variarable que vamos a utilizar de forma global en nuestra clase CRUD
	static Conexion conex;
	private static ResultSet rs2;
	private static PreparedStatement pst, pst1;
	static Connection conn;
	public CRUD() {
	conex = new Conexion();}
	//Funcion de CRUD que nos va a mostrar los clientes de la BBDD
	public static void consultaSQL() {
		//try-catch para recoger excepciones
		 try {
			 //Se realiza la conexión y se guarda en conn el objeto Connection que devuelve Conexion.AccesoBBDD();
			 	conn = Conexion.accesoBD();
	            System.out.println("Se procede a iniciar conexión con la Base de datos para mostrar la información.");
	            //Se guarda en consulta la query
	            String consulta = "SELECT * FROM cliente;";
	            //se realiza un executeQuery con la query de la variable String consulta
	            rs2 = conn.createStatement().executeQuery (consulta);
	            ResultSetMetaData rsMetaData = rs2.getMetaData();
	            //se muestran algunos datos de la BBDD: el numero de columnas y el nombre de nuestra tabla
	            System.out.println("Numero de columnas: " + rsMetaData.getColumnCount());
	            System.out.println("Nuestras tabla se llama: " + rsMetaData.getTableName(1));
	            System.out.println("\n");
	            //Se imprimen los nombres de cada una de las columnas para justo debajo imprimir todas las filas de la tabla cliente
	            System.out.println(rsMetaData.getColumnName(1) + " " + rsMetaData.getColumnName(2) +
	             " " + rsMetaData.getColumnName(3) + " " + rsMetaData.getColumnName(4) + " " + rsMetaData.getColumnName(5) + " " + rsMetaData.getColumnName(6) + " " + rsMetaData.getColumnName(7));
	            //Se imprime con un while los datos de la tabla, es decir, los clientes 
	            while(rs2.next()){
	                System.out.println (rs2.getObject(1)+ " " +rs2.getObject(2)+ " " +rs2.getObject(3)+ " " +rs2.getObject(4)+ " " +rs2.getObject(5)+ " " +rs2.getObject(6)+ " " +rs2.getObject(7));
	            }
	            //nos desconectamos de la bbdd
	                rs2.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            } }
	public static Cliente consultaporidSQL(int p) {String nombre = null;
	 String apellido1=null;
	 String apellido2=null;
	 String comercial=null;
	 int idempresa=0;
	 Array fselect = null;
	 Date[] f2;
	 ArrayList<LocalDate> fechas = null;
		//try-catch para recoger excepciones
		 try {
			 
			 //Se realiza la conexión y se guarda en conn el objeto Connection que devuelve Conexion.AccesoBBDD();
			 	conn = Conexion.accesoBD();
	            //Se guarda en consulta la query
	            String consulta = "SELECT * FROM cliente WHERE idcliente=?;";
	            //se realiza un executeQuery con la query de la variable String consulta
	            pst = Conexion.accesoBD().prepareStatement(consulta);
	            pst.setInt(1, p);
	            rs2 = pst.executeQuery();
	            Array fechinas = null;
	            while (rs2.next()) {
	           nombre=rs2.getString(2);
	           apellido1=rs2.getString(3);
	           apellido2=rs2.getString(4);
	           comercial=rs2.getString(5);
	           idempresa=rs2.getInt(6);
	           fechinas=rs2.getArray(7);}
	            System.out.println("El cliente seleccionado es:");
	            System.out.println(p + " " + nombre + " "+ apellido1 + " "+ apellido2+ " "+ comercial + " "+ idempresa + " " + fechinas);
	           rs2.close();
	           pst.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            } 
		 Cliente cliente1 = new Cliente(nombre, apellido1, apellido2, comercial, idempresa, fechas);
		 return cliente1;	 
	}
	//funcion para convertir Date en LocalDate
		public static LocalDate convertToLocalDateTimeViaInstant(Date dateToConvert) {
		    return dateToConvert.toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDate();
		}
		//funcion para convertir LocalDate en Date
		public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		    return java.sql.Date.valueOf(dateToConvert);
		}
		//función para agregar una nueva fila a nuestra BBDD
		public static void insertaSQL(Cliente clienteint) throws SQLException {
			//instanciamos la clase Cliente para guardar en cliente1 el valor de clienteint, que es el que nos llega por parametro
			Cliente cliente1 =clienteint;
			//try-catch para recoger excepciones
			try {
				//guardamos en la varaible String ssql la query
				String ssql= "INSERT INTO cliente (nombre, apellido1, apellido2, comercial, idempresa, fechas) VALUES (?, ?, ?, ?, ?, ?);";
				//se realiza un prepareStatement con la varaible de la sentencia SQL
				pst=Conexion.accesoBD().prepareStatement(ssql);
				//se determina los valores de cada ? de la sentencia SQL
				pst.setString(1, cliente1.getNombre());
				pst.setString(2, cliente1.getApellido1());
				pst.setString(3, cliente1.getApellido2());
				pst.setString(4, cliente1.getComercial());
				pst.setInt(5, cliente1.getIdempresa());
				//Hacemos conversiones para finalmente obtener un date[] que requiere SQL en la columna 6, como se puede leer en pst.setArray(6, array2);
				Object[] miarray = cliente1.getFechas().toArray();
				Array array2 = Conexion.accesoBD().createArrayOf("date",miarray);
				pst.setArray(6, array2);
				//ejecutamos la query que se trabaja la función
				pst.executeUpdate();
				System.out.println("El cliente ha sido añadido de forma correcta");
			}  catch (SQLException throwables) {
	            throwables.printStackTrace();
	        } finally {
	            //cerramos las conexiones de la bbdd
	            try {
	                pst.close();
	            } catch (SQLException throwables) {
	                throwables.printStackTrace();
	            }
	        }
		}
		//función para borrar una fila de la tabla cliente de nuestra bbdd
		public static void borrarFilaSQL(int p) throws SQLException {
			//try-catch para recoger excepciones
			try {
				//guardamos en la varaible String ssql la sentencia DELETE
				String ssql= "DELETE FROM cliente WHERE idcliente = ?;";
				//realizamos un prepareStatement con la sentencia ssql
				pst=Conexion.accesoBD().prepareStatement(ssql);
				//mandamos a la bbbdd que la id del cliente a borrar es igual a idcliente=p (columna 1)
				pst.setInt(1, p);
				//ejecutamos la query que se trabaja la función
				pst.executeUpdate();
				System.out.println("El cliente ha sido eliminado de forma correcta");
			}  catch (SQLException throwables) {
	            throwables.printStackTrace();
	        } finally {
	            //Cerramos conexión con la bbdd
	            try {
	                pst.close();
	            } catch (SQLException throwables) {
	                throwables.printStackTrace();
	            }
	        }	
		}
		//función que nos permite modificar el nombre del cliente dada una id y el nuevo nombre
	    public static void modificarnombre(int p, String nombre)throws SQLException{
	    	//programamos la sentencia sql que guardamos en String consulta1
	        String consulta1="UPDATE cliente SET nombre=? WHERE idcliente=?;";
	        //creamos un try-catch que nos permitirá capturar excepciones
	        try {
	        	//introducimos un prepareStatement con nuestra sentencia sql: consulta1
	            pst = Conexion.accesoBD().prepareStatement(consulta1);
	            //determinamos que el nuevo nombre es: nombre y que la id es: p
	            pst.setString(1, nombre);
	            pst.setInt(2, p);
	            //ejecutamos la sentencia 
	            pst.executeUpdate();
	            System.out.println("Se ha modificado de forma correcta el dato nomnre");
	                pst.close();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	  }
	  //función que nos permite modificar el apellido1 del cliente dada una id y el nuevo apellido1
	    //el funcionamiento es exatamente igual que la función modificarnombre();
	    public static void modificarapellido1(int p, String apellido1)throws SQLException{
	        String consulta1="UPDATE cliente SET apellido1=? WHERE idcliente=?;";
	        try {
	            pst = Conexion.accesoBD().prepareStatement(consulta1);
	            pst.setString(1, apellido1);
	            pst.setInt(2, p);
	            pst.executeUpdate();
	            System.out.println("Se ha modificado de forma correcta el dato apellido1");
	                pst.close();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }  
	  }
	  //función que nos permite modificar el apellido2 del cliente dada una id y el nuevo apellido2
	    //el funcionamiento es exatamente igual que la función modificarnombre();
	    public static void modificarapellido2(int p, String apellido2)throws SQLException{
	        String consulta1="UPDATE cliente SET apellido2=? WHERE idcliente=?;";
	        try {
	            pst = Conexion.accesoBD().prepareStatement(consulta1);
	            pst.setString(1, apellido2);
	            pst.setInt(2, p);
	            pst.executeUpdate();
	            System.out.println("Se ha modificado de forma correcta el dato apellido2");
	                pst.close();
	            
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	  }
	  //función que nos permite modificar el comercial del cliente dada una id y el nuevo comercial
	    //el funcionamiento es exatamente igual que la función modificarnombre();
	    public static void modificarcomercial(int p, String comercial)throws SQLException{
	        String consulta1="UPDATE cliente SET comercial=? WHERE idcliente=?;";
	        try {
	            pst = Conexion.accesoBD().prepareStatement(consulta1);
	            pst.setString(1, comercial);
	            pst.setInt(2, p);
	            pst.executeUpdate();
	            System.out.println("Se ha modificado de forma correcta el dato comercial");
	                pst.close();
	            
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	  }
	  //función que nos permite modificar el idempresa del cliente dada una id y la nueva idempresa
	    //el funcionamiento es exatamente igual que la función modificarnombre();
	    public static void modificaridempresa(int p, int idempresa)throws SQLException{
	        String consulta1="UPDATE cliente SET idempresa=? WHERE idcliente=?;";
	        try {
	            pst = Conexion.accesoBD().prepareStatement(consulta1);
	            pst.setInt(1, idempresa);
	            pst.setInt(2, p);
	            pst.executeUpdate();
	            System.out.println("Se ha modificado de forma correcta el dato idempresa");
	                pst.close();
	            
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	    }
	    //función que nos permite modificar las fechas de visita de un cliente, dada la id y las fechas nuevas
	    public static void modificarfechas(int p, ArrayList<LocalDate> fechas)throws SQLException{
	    	//String con la query para el update
	        String consulta1="UPDATE cliente SET fechas=? WHERE idcliente=?;";
	        //try-catch para recoger excepciones
	        try {
	        	//se crea un prepareStatement con la consulta1 del UPDATE
	            pst = Conexion.accesoBD().prepareStatement(consulta1);
	            //se hacen las conversiones pertinentes para transforma la ArrayList<LocalDate> fechas en un array de fechas que se puedan introducir a la BBDD
	            Object[] miarray = fechas.toArray();
				Array array2 = Conexion.accesoBD().createArrayOf("date",miarray);
				//se indica a la query cuales son las fechas nuevas y la idcliente a modificar
	           pst.setArray(1, array2);
	            pst.setInt(2, p);
	            //se ejecuta la sentencia mediante pst.executeUpdate();
	            pst.executeUpdate();
	            System.out.println("Se ha modificado de forma correcta el dato fechas");
	            //se cierran conexiones con la BBDD
	                pst.close();
	            
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	  }
	  //función que nos permite añadir nuevas fechas de visita de un cliente, dada la id y las fechas nuevas
	    public static void anadirfechas(LocalDate date, int p)throws SQLException{
	    	//Las sentencias que necesitamos para realizar la operación y variables a declarar
	        String consulta = "UPDATE cliente SET fechas[?] = ? WHERE idcliente = ?;";
	        String consulta1 = "SELECT fechas FROM cliente WHERE idcliente = ?;";
	        Array fselect = null;
	        Date[] f2;
	        //Lo representamos en un try-catch para recoger excepxiones
	        try {
	        	//ejecutamos el select y guardamos en visitas
	            pst = Conexion.accesoBD().prepareStatement(consulta1);
	            pst.setInt(1, p);
	            rs2 = pst.executeQuery();
	            while (rs2.next()) {
	                fselect = rs2.getArray(1);
	            }
	            f2 = (Date[]) fselect.getArray();
	            //Creamos un pS para ejecutar el UPDATE
	            pst = Conexion.accesoBD().prepareStatement(consulta);
	            //Proponemos los valores de cada ? de la sentencia UPDATE
	             pst.setInt(1, f2.length+1);
	             //utilizamos dos funciones de conversión
	             //Primero convertimos date a fechita3 LocalDate - Date
	             Date fechita3 = convertToDateViaSqlDate(date);
	             //Despues convertimos fechita3 a fefinal util.Date a sql.Date
	             java.sql.Date fefinal = convert (fechita3);
	            //ahora continuamos dando valor a las ? de la sentencia UPDATE cuando ya tenemos el valor de fefinal como sql.Date
	             pst.setDate(2, fefinal);
	             pst.setInt(3, p);
	             //ejecutamos la sentencia UPDATE
	             pst.executeUpdate();
	             System.out.println("Se ha agregado la visita del cliente de forma correcta");
	        	} catch (SQLException throwables) {
	        		throwables.printStackTrace();
	        	} finally {
	            try {
	            	//cerramos conexiones
	                pst.close();
	                rs2.close();
	            } catch (SQLException throwables) {
	                throwables.printStackTrace();
	            }
	        }
	    }
	    //convierte util.DAte en sql.Date
	    private static java.sql.Date convert(java.util.Date uDate) {
	            java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	            return sDate;
	    }

}
