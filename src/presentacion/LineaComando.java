package presentacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import excepciones.UsuarioRepetidoException;
import logica.Fabrica;
import logica.IControladorUsuario;


public class LineaComando {
	
		//static Scanner entrada = new Scanner(System.in);
		static void menu() {
			System.out.println("USUARIOS\n"
					+ "1. Alta usuario\n"					+ "2. Consulta usuario\n"
					+ "3. Lista Usuario\n"
					+ "4. Salir\n"
					+ "5. OPCION: ");	
			
		}
		static void agregarUsuario() throws UsuarioRepetidoException {
			Scanner entrada = new Scanner(System.in);
			
			System.out.print("Nombre: ");
	    	String nombre = null;

	    	nombre = entrada.nextLine();

	    	System.out.print("Apellido: ");
	    	String apellido = null;

	    	apellido = entrada.nextLine();
	    	
	    	System.out.print("CI: ");
			String ci = null;

			ci=entrada.nextLine();
			
		  	Fabrica f = Fabrica.getInstance();                       // Crea instancia fabrica
	    	IControladorUsuario icon = f.getIControladorUsuario();   // Controlador devuelto por la fabrica
	    	//icon.registrarUsuario(nombre,apellido,ci);              // Controladro llama a registrar usuario	    	
	    	entrada.close();
	    	
	   }
		
		public static void main(String[] args) throws UsuarioRepetidoException {
			Scanner entrada = new Scanner(System.in);
			menu();
			int opcion = Integer.parseInt(entrada.nextLine());
			while(opcion!=4) {
				switch (opcion) {
			  		case 1:
			  			agregarUsuario();
			  			break;
			  		case 2:
			  			//consultaUsuario();
			  			break;
			  		case 3:
			  			//listarUsuario();
			  			break;
			  	
				}
				
				menu();
			   
					
				if(entrada.hasNextLine()) {
				
					opcion = Integer.parseInt(entrada.nextLine());
					
				}
				else
					System.out.println("No hay una linea disponible");
			}
			entrada.close();
		}
		

}
