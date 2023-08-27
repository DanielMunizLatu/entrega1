package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.IControladorUsuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dataType.DataUsuario;
import excepciones.UsuarioNoExisteException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * JInternalFrame que permite listar todos los usuarios del sistema.
 */
@SuppressWarnings("serial")
public class ListaUsuarios extends JInternalFrame {

    // Controlador de usuarios que se utiliza para las acciones del JFrame
    private IControladorUsuario controlUsr;
    
    // Los componentes graficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes metodos de la misma.
    //private JComboBox<DataUsuario> comboBoxUsuarios;
    private JComboBox<String> comboBoxUsuarios;  // CAMBIE PARA TRAER DE LA PERSISTENCIA A UN STRING
    private JLabel lblUsuarios; 
    private JButton btnCerrar;

    /**
     * Create the frame.
     */
    public ListaUsuarios(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // TODAS ESTAS SON PROPIEDADES DEL INTERNAL FRAME LISTAR USUARIOS
        // Propiedades del JInternalFrame como dimension, posicion dentro del frame, etc.
        setResizable(true);   // Puede ser redimensionadeo
        setIconifiable(true); // Puede ser minimizada y maximizada
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 300, 100);
        
        // En este caso se utiliza un BorderLayout en donde los componentes se ubican segun una orientacion.
        getContentPane().setLayout(new BorderLayout(0, 0));

        // Una etiqueta (JLabel) muestra el titulo de la lista que vendra despues.
        // Se ubica al norte del layout y el texto se centra horizontalmente.
        lblUsuarios = new JLabel("Usuarios Registrados");
        lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblUsuarios, BorderLayout.NORTH);

        // Un combo (JComboBox) muestra la lista de usuarios registrados en el sistema.
        // Es posible utilizar otros componentes graficos, esta es salto una opcion.
        // Se ubica al centro del layout.
        //comboBoxUsuarios = new JComboBox<DataUsuario>();
        comboBoxUsuarios = new JComboBox<String>();
        getContentPane().add(comboBoxUsuarios, BorderLayout.CENTER);

        // Un boton (JButton) con un evento asociado que permite limpiar la lista 
        // de usuarios y cerrar la ventana (solo la oculta).
        // Se ubica al sur del layout.
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBoxUsuarios.removeAllItems();
                setVisible(false);
            }
        });
        getContentPane().add(btnCerrar, BorderLayout.SOUTH);
        
    }  //Aca termina el constructor

    // Metodo que permite cargar un nuevo modelo para el combo con la informacion
    // actualizada de usuarios, provista por la operacion del sistema getUsuarios(). 
    // Se invoca el metodo antes de hacer visible el JInternalFrame
    
   /* public void cargarUsuarios() {
    	
        DefaultComboBoxModel<DataUsuario> model; // Este modelo se crea para carga el combo 
        try {                                    // En model esta lo que vamos a carga al combo
            model = new DefaultComboBoxModel<DataUsuario>(controlUsr.getUsuarios()); //Aca se carga
            comboBoxUsuarios.setModel(model);        //VER EN LA API DefaultComboBoxModel
        } catch (UsuarioNoExisteException e) {
            // No se imprime mensaje de error sino que simplemente no se muestra ningun elemento
        }
      
    }*/

 public void cargarUsuariosPersistencia()  {
    	
        DefaultComboBoxModel<String> model;              // Este modelo se crea para carga el combo 
        model = new DefaultComboBoxModel<String>();                   //Aca se carga
		for (String opcion : controlUsr.getUsuariosPersistencia()) {
		    model.addElement(opcion);
		}
		comboBoxUsuarios.setModel(model);

       
    }
}
