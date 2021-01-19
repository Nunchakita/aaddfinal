package Controlador;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Modelo.Cliente;
//Comenzamos la clase después de importar librerías y clases
public class ClaseSecundaria {
	//declaramos varaibles globales de la clase
	static boolean fechaverificada;
	static private String opc, opc2;
	static ArrayList<LocalDate> fechas=new ArrayList<LocalDate>();
	static ArrayList<String> fechas2 = new ArrayList<String>();
	private static String columna;
	private static int p;
	//función opción uno. LLama a CRUD.consultaSQL() y muestra los clientes de la tabla a través de esa funcion
	public static void opcionUno() {
		System.out.println("Ha seleccionado la opción uno: mostrar los clientes de la base de datos");
		System.out.println("A continuación se lista los clientes de la base de datos");
		CRUD.consultaSQL();
	}
	//Opcion2: inserta un nuevo cliente llama a la funcion CRUD.insertaSQL(cliente2);
	public static void opcionDos() throws SQLException {
		//declaramos variables
		boolean check=false;
		boolean check1=false;
		Scanner scan = new Scanner (System.in);
		Scanner scan2 = new Scanner (System.in);
		Scanner scan3 = new Scanner (System.in);
		String fechita;
		//te pide que introduzcas los datos de cada columna
		//no pide idcliente, ya que este se agrega de forma automática al incluir un nuevo cliente en la BBDD
		System.out.println("Ha seleccionado la opción dos: insertar los clientes de la base de datos");
		System.out.println("Nos encontramos en mantenimiento el usuario que se va a generar por defecto es:");
		System.out.println("Introduzca el nombre del cliente");
		String nombre = scan.nextLine();
		System.out.println("El nombre seleccionado es: " + nombre);
		System.out.println("Introduzca el primer apellido del cliente");
		String apellido1=scan.nextLine();
		System.out.println("El primer apellido seleccionado es: " + apellido1);
		System.out.println("Introduzca el segundo apellido del cliente");
		String apellido2=scan.nextLine();
		System.out.println("El segundo apellido seleccionado es: " + apellido2);
		System.out.println("Introduzca el comercial que atiende al cliente");
		String comercial=scan.nextLine();
		System.out.println("El comercial seleccionado es: " + comercial);
		System.out.println("Introduzca la ID de la empresa");
		int idempresa=scan.nextInt();
		System.out.println("La ID de empresa seleccionada es: " + idempresa);
		int j=0;
		//do-while que pregunta si deseas incluir nuevas fechas (para meter más de una fecha en el array date[]
		do {
			int n=0;
			int m=0;
			//do-while que determina si el año está entre 1980 y 2021, si el mes está entre 1 y 12 y los días entre 1 y 31
			//para comprobarlo llama a la funcion validarFechaDos(fechita);
			do {
				//do-while que determina si el formato de la fecha es aaaa-MM-dd sino tiene este formata ta manda volver a introducir la fecha
				do {
				if(n==0 && m==0) {
				System.out.println("Introduzca la fecha de visita del cliente con el siguiente formato (yyyy-mm-dd)");
				}
				if(n>=1 || m>=1) {
					System.out.println("La fecha introducida no es válida (yyyy-mm-dd). El año debe ser superior a 1980");	
				}
				fechita=scan2.nextLine();
				check=validarFecha(fechita);
				n++;
			}while (!check);
				check1=validarFechaDos(fechita);
				m++;
		}while(!check1);
			fechas2.add(fechita);
			System.out.println(fechas2.get(j));
			j++;
			System.out.println("¿Quieres introducir más visitas al cliente " + nombre + "?");
			opc=scan3.nextLine();
		}while(opc.equalsIgnoreCase("y"));
		System.out.println("Las fechas seleccionadas son: \n");
		//convierte string fechas en ArrayList<LocalDate> fechas
		int y=0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		for(int k=0; k<j;k++) {
			String date = fechas2.get(k);
			 fechas.add(LocalDate.parse(date, formatter));
			 System.out.println(fechas.get(k));
			y++;
		}
		//instancia Cliente con los datos que hemos introducido
		Cliente cliente2 = new Cliente (nombre, apellido1, apellido2, comercial, idempresa, fechas);
		//llama a insertarSQL(cliente2) para introducir el cliente en la BBDD
		CRUD.insertaSQL(cliente2);
		fechas.clear();
		fechas2.clear();
	}
	//opcion tres: función para borrar un cliente de la BBDD
	public static void opcionTres() throws SQLException{
		String op4;
		Scanner scan4 = new Scanner (System.in);
		Scanner scan5= new Scanner(System.in);
		int p=0;
		int z =0;
		System.out.println("Has escogido la tercera opción: Eliminar un cliente a partir de su idcliente");
		//te pide el idcliente y llama a CRUD.consultaporidSQL(p) siendo p el idcliente
		//desde la función CRUD.consutaporidSQL(p) te muestra el cliente
		//con el do-while te pregunta si quieres realmente eliminar ese cliente
		do {
		if(z==0) {System.out.println("Por favor, introduzca la id del cliente que desea borrar. La idcliente son números enteros");}
		if(z>=1) {System.out.println("Vuelva a introducir la idcliente del cliente a eliminar de forma correcta");}
		p=scan4.nextInt();
		Cliente clienteauxiliar = CRUD.consultaporidSQL(p);
		System.out.println("¿Está usted seguro de querer eliminar este cliente?" + " (Y/N)");
		op4=scan5.nextLine();
		}while(!op4.equalsIgnoreCase("Y"));
		//finalmente, borra el cliente
		CRUD.borrarFilaSQL(p);
		}
	//opción cuatro: a través del idcliente podras modificar cualquier otro dato del cliente
	//primero te pedira que introduzcas la idcliente y te muestra los datos.
	//cuando virifica que es usuario, deberás escribir el campo que deseas modificar
	// y finalmente, te pedirá que introduzcas el dato nuevo
	public static void opcionCuatro() throws SQLException{
		boolean check=false;
		boolean check1=false;
		boolean itstrue=false;
		Scanner scan5 = new Scanner (System.in);
		Scanner scan6 = new Scanner (System.in);
		Scanner scan7 = new Scanner (System.in);
		Scanner scan8 = new Scanner (System.in);
		Scanner scan9 = new Scanner (System.in);
		int q=0;
		int p=0;
		String op4 =null;
		System.out.println("Has escogido la cuarta opción: modificar una columna de un cliente a partir de su idcliente");
		int z =0;
		//te pide el idcliente y llama a CRUD.consultaporidSQL(p) siendo p el idcliente
				//desde la función CRUD.consutaporidSQL(p) te muestra el cliente
				//con el do-while te pregunta si quieres realmente eliminar ese cliente
		do {
			if(z==0) {System.out.println("Por favor, introduzca la id del cliente que desea modificar. La idcliente son números enteros");}
			if(z>=1) {
				JOptionPane.showMessageDialog(null, "HOLA HOLITA");
				System.out.println("Vuelva a introducir la idcliente del cliente a eliminar de forma correcta");}
			p=scan6.nextInt();
			Cliente clienteauxiliar = CRUD.consultaporidSQL(p);
			System.out.println("¿Está usted seguro de querer modificar este usuario?" + " (Y/N)");
			op4=scan9.nextLine();
		}while(!op4.equalsIgnoreCase("y"));
		int b=0;
		//do-while para que introduzcas cual campo deseas introducir. Debes introducir cualquiera de las siguientes palabras:
		// nombre, apellido1, apellido2, comercial, idempresa, fechas
		//si no introduces uno de esos tag, te mostrará que ha dado error, y te pedirá de nuevo que lo introduzcas
		do{
			if(b>=1) {
				System.out.println("Ha ocurrido un error, no ha introducido el campo correcto.");
			}
			System.out.println("¿Qué dato quiere modificar del cliente con idcliente: " +p+ "?");
			System.out.println("Escriba el nombre del campo que desea modificar");
			System.out.println("Los diversos campos a modificar son: nombre apellido1 apellido2 comercial idempresa fechas");
			System.out.println("¿Cuál desea modificar?");
			columna=scan7.nextLine();
			b++;
			if(columna.equalsIgnoreCase("nombre") || columna.equalsIgnoreCase("apellido1") || columna.equalsIgnoreCase("apellido2") || columna.equalsIgnoreCase("comercial") || columna.equalsIgnoreCase("idempresa") || columna.equalsIgnoreCase("fechas")) {
				itstrue=true;
			}
		}while(itstrue==false);
		//Posteriormente irá a una if-ifelse en función del valor de la varaible columna
		//si colummna== "nombre" se deseará modificar el valor de nombre
		//te pide que introduzcas un nombre y llama a la función CRUD.modificarnombre(p, nombre) siendo p el idcliente y nombre el nuevo nombre
			if(columna.equalsIgnoreCase("nombre")) {
				System.out.println("El campo a modificar es: nombre");
				System.out.println("Introduzca el nuevo nombre");
				String nombre=scan8.nextLine();
				System.out.println("Al cliente con id " + p + "se le cambiara el nombre a " + nombre);
				CRUD.modificarnombre( p, nombre);
				//si colummna== "apellido1" se deseará modificar el valor de apellido1
				//te pide que introduzcas el apellido1 y llama a la función CRUD.modificarapellido1(p, apellido1) siendo p el idcliente y apellido1 el nuevo apellido1
			} else if (columna.equalsIgnoreCase("apellido1")) {
				System.out.println("El campo a modificar es: apellido1");
				System.out.println("Introduzca el nuevo apellido");
				String apellido1=scan8.nextLine();
				System.out.println("Al cliente con id " + p + "se le cambiara el apellido a " + apellido1);
				CRUD.modificarapellido1( p, apellido1);
				//si colummna== "apellido2" se deseará modificar el valor de apellido2
				//te pide que introduzcas el apellido2 y llama a la función CRUD.modificarapellido2(p, apellido2) siendo p el idcliente y apellido2 el nuevo apellido2
			}else if (columna.equalsIgnoreCase("apellido2")) {
				System.out.println("El campo a modificar es: apellido2");
				System.out.println("Introduzca el nuevo apellido");
				String apellido2=scan8.nextLine();
				System.out.println("Al cliente con id " + p + "se le cambiara el apellido a " + apellido2);
				CRUD.modificarapellido2( p, apellido2);
				//si colummna== "comercial" se deseará modificar el valor de comercial
				//te pide que introduzcas el comercial y llama a la función CRUD.modificarcomercial(p, comercial) siendo p el idcliente y comercial el nuevo comercial
			}else if (columna.equalsIgnoreCase("comercial")) {
				System.out.println("El campo a modificar es: comercial");
				System.out.println("Introduzca el nuevo comercial");
				String comercial=scan8.nextLine();
				System.out.println("Al cliente con id " + p + "se le cambiara el comercial a " + comercial);
				CRUD.modificarcomercial( p, comercial);
				//si colummna== "idempresa" se deseará modificar el valor de idempresa
				//te pide que introduzcas el idempresa y llama a la función CRUD.modificaridempresa(p, idempresa) siendo p el idcliente y idempresa el nuevo idempresa
			}else if (columna.equals("idempresa")) {
				System.out.println("El campo a modificar es: idempresa");
				System.out.println("Introduzca la nueva idempresa");
				int idempresa=scan8.nextInt();
				System.out.println("Al cliente con id " + p + "se le cambiara la idempresa a " + idempresa);
				CRUD.modificaridempresa( p, idempresa);
				//si colummna== "fechas" se deseará modificar el valor de fechas (de fecha en fecha deberás introducir, de una en una)
				//te pide que introduzcas el fechas y llama a la función CRUD.modificarfechas(p, fechas) siendo p el idcliente y fechas el nuevo fechas
			} else if (columna.equalsIgnoreCase("fechas")) {
				Scanner scan10= new Scanner(System.in);
				String fechita;
				System.out.println("El campo a modificar es: fechas");
				int n=0;
				//do-while que pregunta si deseas introducir una fecha más
				do {
					int r=0;
					int m=0;
					//do-while que verifique que la fecha tiene formato yyyy-MM-dd
					do {
						//do-while que confirma que yyyy va entre 1980 y 2021, MM entre 1 y 12 y dd entre 1 y 31 para confirmar que es una fecha válida
						//si no es válida te pedirá que la introduzcas de nuevo
						do {
							if(r==0 && m==0) {
								System.out.println("Introduzca la fecha de visita del cliente con el siguiente formato (yyyy-mm-dd)");
							}
							if(r>=1 || m>=1) {
								System.out.println("La fecha introducida no es válida (yyyy-mm-dd). El año debe ser superior a 1980");	
							}
							fechita=scan5.nextLine();
							check=validarFecha(fechita);
							r++;
						}while (!check);
						check1=validarFechaDos(fechita);
						m++;
					}while(!check1);
					fechas2.add(fechita);
					n++;
					System.out.println("¿Quieres introducir más visitas al cliente con id" + p + "?");
					opc2=scan10.nextLine();
				}while(opc2.equalsIgnoreCase("y"));
				//transforma las fechas String en ArrayList<LocalDate> 
				int y=0;
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-d");
				for(int k=0; k<n;k++) {
					String date = fechas2.get(k);
					fechas.add(LocalDate.parse(date, formatter2));
					y++;
				}
				//modifica las fechas y las limpia con clear() para que no almacene información en los punteros
				CRUD.modificarfechas( p, fechas);
				fechas.clear();
				fechas2.clear();
				fechita="";
			}else {
				System.out.println("Ha ocurrido un error inesperado");
			}
	}
	//la opción cinco trata de añadir nuevas fechas
public static void opcionCinco() throws SQLException{
	LocalDate date;
	String date2;
	Scanner scan11 = new Scanner(System.in);
	Scanner scan12=new Scanner (System.in);
	Scanner scan9 = new Scanner(System.in);
	Scanner scan10 = new Scanner(System.in);
	String opc3, fechita;
	String op4=null;
	int j=0;
	int z=0;
	boolean check = false;
	boolean check1= false;
	System.out.println("Ha elegido la opción cinco: desea añadir nuevas fechas de visita al cliente.");
	//do-while que consulta si el cliente que desea modificarse a través de p (idcliente) lo busca en la tabla y lo muestra pantalla
	//una vez mostrado te pregunta si de verdad se desea añadir más fechas en él
	do {
		if(z==0) {System.out.println("Por favor, introduzca la id del cliente que desea modificar. La idcliente son números enteros");}
		if(z>=1) {System.out.println("Vuelva a introducir la idcliente del cliente a modificar de forma correcta");}
		p=scan11.nextInt();
		Cliente clienteauxiliar = CRUD.consultaporidSQL(p);
		System.out.println("¿Está usted seguro de querer añadir mas fechas en este usuario?" + " (Y/N)");
		op4=scan12.nextLine();
		}while(op4.equalsIgnoreCase("n"));
	int n=0;
	int m=0;
	//do while a través del cual te pedirá que introduzcas fecha a fecha las nuevas fechas a añadir
	do {
		//do-while que determina si el año está entre 1980 y 2021, si el mes está entre 1 y 12 y los días entre 1 y 31
		//para comprobarlo llama a la funcion validarFechaDos(fechita);
		do {
			//do-while que determina si el formato de la fecha es aaaa-MM-dd sino tiene este formata ta manda volver a introducir la fecha
			do {
			if(n==0 && m==0) {
			System.out.println("Introduzca la fecha de visita del cliente con el siguiente formato (yyyy-mm-dd)");
			}
			if(n>=1 || m>=1) {
				System.out.println("La fecha introducida no es válida (yyyy-mm-dd). El año debe ser superior a 1980");	
			}
			date2=scan9.nextLine();
			check=validarFecha(date2);
			n++;
		}while (!check);
			check1=validarFechaDos(date2);
			m++;
	}while(!check1);
		//como es una sola fecha, no necesita array pero si realizar conversión a LocalDate
        //convirtiendolo a java.time.LocalDate
        date = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        CRUD.anadirfechas(date, p);
        System.out.println("¿desea instroducir nuevas fechas?");
        opc3=scan9.nextLine();
		}while(opc3.equalsIgnoreCase("y"));
		fechas.clear();
		fechas2.clear();
		System.out.println("¿Quieres introducir más visitas al cliente con idcliente " + p + "?");
		opc3=scan10.nextLine();
	}
//funcion que termina la ejecucion del programa
public static void opcionSeis() throws SQLException{
	System.out.println("La aplicación va a finalizar. Esperamos haber sido de ayuda.");
	System.exit(0);
	}
//funcion que valida que la fecha tenga formato yyyy-MM-dd
//si el formato es el correcto devuelve un true
private static boolean validarFecha(String fecha) {
	 Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
	 String email = fecha;
	 Matcher mather = pattern.matcher(email);
	 if (mather.find() == true) {
		 fechaverificada=true;
      } else {
      	fechaverificada=false;
      }
	return fechaverificada;
}
//funcion que divide la fecha con formato yyyy-MM-dd en tres partes con StringTokenizer.
//primero comprueba que la primera parte sea un digito que va entre 1980 y 2021
//segundo comprueba que la parte segunda MM va entre 1 y 12
//finalmente comprueba que la tercera parte dd va entre 1 y 31.
//si los tres son true, devuelve un true la función
private static boolean validarFechaDos(String fechita) {
	int [] fechavalidar= new int[3];
	boolean check1=false;
	boolean check2=false;
	boolean check3=false;
	boolean checkf=false;
	StringTokenizer st = new StringTokenizer(fechita, "-");
	fechavalidar[0]=Integer.parseInt (st.nextToken());
	fechavalidar[1]=Integer.parseInt (st.nextToken());
	fechavalidar[2]=Integer.parseInt (st.nextToken());
	if(fechavalidar[0]>=1980 && fechavalidar[0]<=2021){
		check1=true;
	}
	if (fechavalidar[1]>0 && fechavalidar[1]<=12) {
		check2=true;
	}
	if(fechavalidar[2]>0 && fechavalidar[2]<=31){
		check3=true;
	}
	if(check1==true && check2==true && check3==true) {
		checkf=true;
		return checkf;
	} else return checkf;
}
}