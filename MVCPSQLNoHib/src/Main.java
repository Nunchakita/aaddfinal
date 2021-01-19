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
				//Desarrollamos un menú, para saber que operación quiere realizar el usuario
				//el menú va dentro de un do-while para seguir ejecutándolo hasta que se introduzca la opción 5: Salir
				do {
					//mostramos el menú al usuario
					System.out.println("¿Que desea hacer con la Base de Datos?");
					System.out.println("Opcion 1: Mostrar los datos de la tabla clientes");
					System.out.println("Opcion 2: Insertar un nuevo cliente a la tabla de clientes");
					System.out.println("Opcion 3: Eliminar un cliente a través de la idcliente de la tabla de clientes");
					System.out.println("Opcion 4: Modificar un dato de un cliente a través de la idcliente");
					System.out.println("Opcion 5: Añadir una nueva fecha de visita a un cliente a través de la idcliente");
					System.out.println("Opcion 6: Salir");
					System.out.println("Pulse el número de la opción y presione el botón enter del teclado.");
					menu=scan.nextInt();
					//se genera un switch con la variable menu. Cada case nos lleva a una función de la clase ClaseSecundaria
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
					   //si es default te preguntará si deseas realizar otra opción
					   System.out.println("Introduce una opción correcta");
					   break;
					}
					//si quieres realizar otra opción (menu2=="y") te vuelve al principio del do-while
					do{
						System.out.println("¿Quieres realizar otra accion? y/n");
						menu2=scan2.nextLine();
						if (menu2.equalsIgnoreCase("y") || menu2.equalsIgnoreCase("n")) {istrue=true;}
					}while (istrue==false);
					
				} while(menu2.equalsIgnoreCase("y"));
				//si menu2 es diferente de Y o de y se sale del while y termina la ejecución del programa
				System.exit(0);
		} 
}
