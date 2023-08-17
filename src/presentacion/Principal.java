package presentacion;

import java.awt.EventQueue;  // La clase EventQueue es para manejo de eventos, pone eventos en cola

import javax.swing.JFrame;   
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Fabrica;   // Uso Fabricas de la clase logica
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
        
        // Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.
        // Cada InternalFrame usa un layout diferente, simplemente para mostrar distintas opciones.
        creUsrInternalFrame = new CrearUsuario(ICU);
        creUsrInternalFrame.setLocation(30, 35);
        creUsrInternalFrame.setVisible(false);

        conUsrInternalFrame = new ConsultarUsuario(ICU);
        conUsrInternalFrame.setLocation(62, 11);
        conUsrInternalFrame.setVisible(false);

        lisUsrInternalFrame = new ListaUsuarios(ICU);
        lisUsrInternalFrame.setVisible(false);
        
        modUsrInternalFrame = new ModificarUsuario(ICU);
        modUsrInternalFrame.setVisible(false);
        
        frmGestionDeUsuarios.getContentPane().setLayout(null);

        frmGestionDeUsuarios.getContentPane().add(conUsrInternalFrame); /*Agrego los 4 internos al principal */
        frmGestionDeUsuarios.getContentPane().add(creUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(lisUsrInternalFrame);
        frmGestionDeUsuarios.getContentPane().add(modUsrInternalFrame); 
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        // Se crea el Frame con las dimensiones indicadas.
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
        frmGestionDeUsuarios.setBounds(100, 100, 450, 400);
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

        JMenuItem mntmListaUsuarios = new JMenuItem("ListarUsuarios");
        mntmListaUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver la lista de todos los usuarios,
                // cargando previamente la lista
                lisUsrInternalFrame.cargarUsuarios();   // Llamo al cargar Usuario en la clase de ese internal frame
                lisUsrInternalFrame.setVisible(true);  // Pongo visible el internalFrame visible
            }
        });
        menuUsuarios.add(mntmListaUsuarios);
        
        //Ahora el modificar usuario
        
        JMenuItem mntModificarUsuarios = new JMenuItem("ModificarUsuarios");
        mntModificarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para ver la lista de todos los usuarios,
                // cargando previamente la lista
            	//modUsrInternalFrame.cargarUsuarios();   // Llamo al cargar Usuario en la clase de ese internal frame
            	modUsrInternalFrame.setVisible(true);  // Pongo visible el internalFrame visible
            }
        });
        menuUsuarios.add(mntModificarUsuarios);
    }
}
