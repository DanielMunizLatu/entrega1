package presentacion;

import java.awt.EventQueue;  // La clase EventQueue es para manejo de eventos, pone eventos en cola

import javax.swing.JFrame;   
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Fabrica;   // Uso Fabricas de la clase logica
import logica.IControladorActividad;
import logica.IControladorUsuario;  // Lo mismo para controlador de usuarios

import javax.swing.JMenu;
import java.awt.event.ActionEvent;    // Para indicar que el usuario quiere que ocurra alguna accion.
import java.awt.event.ActionListener; // Permite quedar a la espera de una accion

/**
 * Clase principal (Frame) con el metodo Main.

 */
public class Principal {

    private JFrame frmGestionDeUsuarios;          // Frame principal
    private IControladorUsuario ICU;              // Objeto de tipo controlador para manipulacion de objetos
    private CrearUsuario creUsrInternalFrame;     // Frame interno para dar de alta usuario
    private ConsultarUsuario conUsrInternalFrame; // Frame interno para consultar por usuario
    private ListaUsuarios lisUsrInternalFrame;    // Frame interno para listar usuario 
    private ModificarUsuario modUsrInternalFrame; // Frame para modificar el usuario
	private IControladorActividad ICA;
    private CrearActividad creActiInternalFrame;  // Frame para alta de actividad
    private CrearSalida creSaliInternalFrame;     // Frame de salida
    private CrearInscripcion creInscInternalFrame;  // Frame de inscripcion
    private ConsultarSalida conSalidaInternalFrame;  // Frame consulta de salida
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	//System.out.print("Entre al main");
        EventQueue.invokeLater(new Runnable() {  /* se utiliza para poner en cola una tarea */
            public void run() {                  /* para ser ejecutada en el hilo de eventos EDT */
                try {
                    Principal window = new Principal();           // Crea una instancia del objeto principal
                    window.frmGestionDeUsuarios.setVisible(true); // Pone la ventana visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Principal() {   // Constructor de la clase
        initialize();     // Inicializa la interface

        // Inicializacion
        Fabrica fabrica = Fabrica.getInstance();  // Se crea una instancia unica de fabrica, se guarda en la variable fabrica
        ICU = fabrica.getIControladorUsuario();   // Se devuelve una instancia unica controlador de usuario
        
        ICA = fabrica.getIControladorActividad();
        // Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecucion.
        // Cada InternalFrame usa un layout diferente, simplemente para mostrar distintas opciones.
        creUsrInternalFrame = new CrearUsuario(ICU);
        creUsrInternalFrame.setLocation(30, 35);
        creUsrInternalFrame.setVisible(false);

        conUsrInternalFrame = new ConsultarUsuario(ICU);
        conUsrInternalFrame.setLocation(62, 11);
        conUsrInternalFrame.setVisible(false);

        lisUsrInternalFrame = new ListaUsuarios(ICU);
        lisUsrInternalFrame.setVisible(false);
        
        // Tambien el de modificar Usuario
        modUsrInternalFrame = new ModificarUsuario(ICU);
        modUsrInternalFrame.setVisible(false);
        
        // El de crear Actividad
        creActiInternalFrame = new CrearActividad(ICA,ICU);
        creActiInternalFrame.setLocation(30, 35);
        creActiInternalFrame.setVisible(false);
        
              
        // El de crear Salida
        creSaliInternalFrame = new CrearSalida(ICA);
        creSaliInternalFrame.setLocation(30, 35);
        creSaliInternalFrame.setVisible(false);
        
        // El de consultar Salida
        conSalidaInternalFrame = new ConsultarSalida(ICA);
        conSalidaInternalFrame.setLocation(30, 35);
        conSalidaInternalFrame.setVisible(false);
        
        // El de crear Inscripcion
        creInscInternalFrame = new CrearInscripcion(ICA,ICU);
        creInscInternalFrame.setLocation(30, 35);
        creInscInternalFrame.setVisible(false);
        
        frmGestionDeUsuarios.getContentPane().setLayout(null);

        /*Agrego los  internos al principal */
        frmGestionDeUsuarios.getContentPane().add(conUsrInternalFrame); 
        frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(lisUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(modUsrInternalFrame); 
        frmGestionDeUsuarios.getContentPane().add(creActiInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creSaliInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(creInscInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(conSalidaInternalFrame);
               
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        // Se crea el Frame con las dimensiones indicadas.
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
        frmGestionDeUsuarios.setBounds(100, 100, 496, 414);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menu (JMenuBar) con dos menu (JMenu) desplegables.
        // Cada menu contiene diferentes opciones (JMenuItem), los cuales tienen un 
        // evento asociado que permite realizar una accion una vez se seleccionan. 
        JMenuBar menuBar = new JMenuBar();
        frmGestionDeUsuarios.setJMenuBar(menuBar);

        JMenu menuSistema = new JMenu("Sistema");  // Este es un item del menu
        menuBar.add(menuSistema);                  // Esto lo agrega al menu principal

        JMenuItem menuSalir = new JMenuItem("Salir");   // Este es un item de salir
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {  // Esto dispara el evento de salir
                // Salgo de la aplicacion
                frmGestionDeUsuarios.setVisible(false);   // Pone no visible el Menu
                frmGestionDeUsuarios.dispose();           // Libera la memoria de los componentes
            }
        });
        menuSistema.add(menuSalir);                      // Lo agrega al MenuSalir

        JMenu menuUsuarios = new JMenu("Usuarios");      // Al mismo nivel que Sistema
        menuBar.add(menuUsuarios);

        JMenuItem menuItemRegistrar = new JMenuItem("Registrar Usuario");
        menuItemRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
                creUsrInternalFrame.setVisible(true);       // Pongo visible el internalFrame visible
            }
        });
        menuUsuarios.add(menuItemRegistrar);                // Agrego el registrar Usuario al usuarios

        JMenuItem menuItemVerInfo = new JMenuItem("Ver Informacion");
        menuItemVerInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver informacion de un usuario
                conUsrInternalFrame.setVisible(true);       // Pongo visible el internalFrame visible
            }
        });
        menuUsuarios.add(menuItemVerInfo);               // Agregar Info al usuarios

        JMenuItem mntmListaUsuarios = new JMenuItem("Listar Usuarios");
        mntmListaUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver la lista de todos los usuarios,
                // cargando previamente la lista
                lisUsrInternalFrame.cargarUsuariosPersistencia();   // Llamo al cargar Usuario en la clase de ese internal frame
                lisUsrInternalFrame.setVisible(true);  // Pongo visible el internalFrame visible
            }
        });
        menuUsuarios.add(mntmListaUsuarios);
        
        //Ahora el modificar usuario
        
        JMenuItem mntModificarUsuarios = new JMenuItem("Modificar Usuario");
        mntModificarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver la lista de todos los usuarios,
                // cargando previamente la lista
            	//modUsrInternalFrame.cargarUsuarios();   // Llamo al cargar Usuario en la clase de ese internal frame
            	modUsrInternalFrame.setVisible(true);  // Pongo visible el internalFrame visible
            }
        });
        menuUsuarios.add(mntModificarUsuarios);
        
        // Ahora uno nuevo menu para Actividades
        
        JMenu menuActividades = new JMenu("Actividades");      // Al mismo nivel que Sistema
        menuBar.add(menuActividades);
        
        // Abajo de este el registrar Actividades
        
        JMenuItem creRegistrarActividad = new JMenuItem("Registrar y Consultar");
        creRegistrarActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar actividad
            	creActiInternalFrame.cargarProveedoresPersistencia();     // PARA CARGAR COMBO PROVEEDORES
            	creActiInternalFrame.setVisible(true);                   // Pongo visible el internalFrame 
            	
            }
        });
        menuActividades.add(creRegistrarActividad);                     // Agrego el registrar a Actividades
            
       // Ahora uno nuevo menu para Salidas
        
        JMenu menuSalidas = new JMenu("Salidas");      // Al mismo nivel que Sistema
        menuBar.add(menuSalidas);   
        
      // Abajo de este el registrar Salida
        
        JMenuItem creRegistrarSalida = new JMenuItem("Registrar");
        creRegistrarSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	   // Muestro el InternalFrame para registrar salida
            	creSaliInternalFrame.cargarActividadesPersistencia();     // PARA CARGAR COMBO ACTIVIDADES
            	creSaliInternalFrame.setVisible(true);
            	System.out.println("Hola salida");
            }
        });
        menuSalidas.add(creRegistrarSalida);         // Agrego el registrar a Actividades
  
        JMenuItem conConsultarSalida = new JMenuItem("Consultar");
        conConsultarSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	// Muestro el InternalFrame para registrar salida
            	conSalidaInternalFrame.cargarActividadesPersistencia();     // PARA CARGAR COMBO ACTIVIDADES
            	conSalidaInternalFrame.setVisible(true);
            	System.out.println("Hola CONSULTA salida");
            	
            }
        });
        menuSalidas.add(conConsultarSalida);         // Agrego el registrar a Actividades
        
        // Nuevo menu para Inscripciones
        
        JMenu menuInscripciones = new JMenu("Inscripciones");      // Al mismo nivel que Sistema
        menuBar.add(menuInscripciones);   
        
      // Abajo de este el registrar Inscripcion
        
        JMenuItem creRegistrarInscripcion = new JMenuItem("Registrar y Consultar");
        creRegistrarInscripcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	// Muestro el InternalFrame para registrar salida
            	creInscInternalFrame.cargarActividadPersistencia();     // PARA CARGAR COMBO ACTIVIDADES
            	creInscInternalFrame.cargarTuristaPersistencia();
            	creInscInternalFrame.setVisible(true);
            	
            }
        });
        menuInscripciones.add(creRegistrarInscripcion);         // Agrego el registrar a Menu Inscripciones
    }
}
