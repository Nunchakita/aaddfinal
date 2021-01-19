import java.sql.SQLException;
import java.util.*;
public class Main {
	//se generan las variables de las cuales podemos hacer uso
	static Scanner scan = new Scanner(System.in);
	static Scanner scan2 = new Scanner(System.in);
	private static int menu;
	private static String menu2;
	private static boolean istrue;
	//nuestro main
			public static void main(String[] args) throws SQLException {
				//Desarrollamos un men�, para saber que operaci�n quiere realizar el usuario
				//el men� va dentro de un do-while para seguir ejecut�ndolo hasta que se introduzca la opci�n 5: Salir
				do {
					//mostramos el men� al usuario
					System.out.println("�Que desea hacer con la Base de Datos?");
					System.out.println("Opcion 1: Mostrar los datos de la tabla clientes");
					System.out.println("Opcion 2: Insertar un nuevo cliente a la tabla de clientes");
					System.out.println("Opcion 3: Eliminar un cliente a trav�s de la idcliente de la tabla de clientes");
					System.out.println("Opcion 4: Modificar un dato de un cliente a trav�s de la idcliente");
					System.out.println("Opcion 5: A�adir una nueva fecha de visita a un cliente a trav�s de la idcliente");
					System.out.println("Opcion 6: Salir");
					System.out.println("Pulse el n�mero de la opci�n y presione el bot�n enter del teclado.");
					menu=scan.nextInt();
					//se genera un switch con la variable menu. Cada case nos lleva a una funci�n de la clase ClaseSecundaria
					switch(menu)
					{
						case 1 :
							//si menu==1 se ejecuta Controlador.ClaseSecundaria.opcionUno()
							Controlador.ClaseSecundaria.opcionUno();
							break;
						case 2 :
							//si menu==2 se ejecuta Controlador.ClaseSecundaria.opcionDos()
							Controlador.ClaseSecundaria.opcionDos();
							break;
						case 3 :
							//si menu==3 se ejecuta Controlador.ClaseSecundaria.opcionTres()
							Controlador.ClaseSecundaria.opcionTres();
							break;
						case 4 :
							//si menu==4 se ejecuta Controlador.ClaseSecundaria.opcionCuatro()
							Controlador.ClaseSecundaria.opcionCuatro();
							break;
						case 5 :
							//si menu==5 se ejecuta Controlador.ClaseSecundaria.opcionCinco()
							Controlador.ClaseSecundaria.opcionCinco();
							break;
						case 6 :
							//si menu==6 se ejecuta Controlador.ClaseSecundaria.opcionCinco()
							Controlador.ClaseSecundaria.opcionSeis();
							break;
				   default :
					   //si es default te preguntar� si deseas realizar otra opci�n
					   System.out.println("Introduce una opci�n correcta");
					   break;
					}
					//si quieres realizar otra opci�n (menu2=="y") te vuelve al principio del do-while
					do{
						System.out.println("�Quieres realizar otra accion? y/n");
						menu2=scan2.nextLine();
						if (menu2.equalsIgnoreCase("y") || menu2.equalsIgnoreCase("n")) {istrue=true;}
					}while (istrue==false);
					
				} while(menu2.equalsIgnoreCase("y"));
				//si menu2 es diferente de Y o de y se sale del while y termina la ejecuci�n del programa
				System.exit(0);
		} 
}
