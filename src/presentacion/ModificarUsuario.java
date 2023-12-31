package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dataType.DataProveedor;
import dataType.DataTurista;
import dataType.DataUsuario;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.IControladorUsuario;

@SuppressWarnings("serial")
public class ModificarUsuario extends JInternalFrame {
	
    private IControladorUsuario controlUsr;
   
    // Los componentes graficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes metodos de la misma.
    private JTextField textFieldCI;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JLabel lblIngresoCI;
    private JButton btnBuscar;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JButton btnCerrar;
    private JLabel lblInfoUsuario;
    private JTextField textFieldNacionalidad;
    private JLabel lbDescripcion;
    private JTextField textFieldDescripcion;

    public ModificarUsuario(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // Propiedades del JInternalFrame como dimension, posicion dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar un Usuario");
        setBounds(30, 30, 410, 311);

        // En este caso usaremos el Absolute Layout y deberemos indicar la posicion absoluta de todos los componentes
        getContentPane().setLayout(null);

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse  la cedula del usuario.
        lblIngresoCI = new JLabel("Ingrese CI:");
        lblIngresoCI.setBounds(10, 24, 170, 14);  // posicion x, posicion y, largo y alto
        getContentPane().add(lblIngresoCI);
       
        // Una campo de texto (JTextField) para ingresar la cedula de un usuario. 
        textFieldCI = new JTextField();
        textFieldCI.setBounds(104, 17, 140, 30);
        getContentPane().add(textFieldCI);

        // Un boton (JButton) con un evento asociado que permite buscar un usuario.
        // Dado que el codigo de registro tiene cierta complejidad, conviene delegarlo
        // a otro metodo en lugar de incluirlo directamente de el metodo actionPerformed 
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() { // ACA ASOCIO EL EVENTO AL BOTON
            public void actionPerformed(ActionEvent e) {
                cmdBuscarUsuarioActionPerformed(e);
            }
        });
        btnBuscar.setBounds(256, 16, 95, 30);
        getContentPane().add(btnBuscar);
        
        // Una etiqueta (JLabel) indicando que a continuacion se vera la informacion del usuarios buscado.
        lblInfoUsuario = new JLabel("Informacion de Usuario");
        lblInfoUsuario.setBounds(104, 68, 180, 14);
        getContentPane().add(lblInfoUsuario);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se vera el nombre del usuario encontrado.
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 102, 65, 14);
        getContentPane().add(lblNombre);

        // Una campo de texto (JTextField) para mostrar el nombre del usuario. 
        // El campo se hace no editable para impedir que se modifique.
        textFieldNombre = new JTextField();
        textFieldNombre.setEditable(true);
        textFieldNombre.setBounds(104, 94, 271, 30);
        getContentPane().add(textFieldNombre);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se vera el apellido del usuario encontrado.
        lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(10, 144, 65, 14);
        getContentPane().add(lblApellido);
        
        // Una campo de texto (JTextField) para mostrar el apellido del usuario. 
        // El campo se hace no editable para impedir que se modifique.
        textFieldApellido = new JTextField();
        textFieldApellido.setEditable(true);
        textFieldApellido.setBounds(104, 136, 271, 30);
        getContentPane().add(textFieldApellido);

        // Un boton (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(262, 247, 89, 23);
        getContentPane().add(btnCerrar);
        
        // Etiqueta de Nacionalidad, si es turista
        JLabel lblNacionalidad = new JLabel("Nacionalidad:");
        lblNacionalidad.setBounds(10, 184, 79, 14);
        getContentPane().add(lblNacionalidad);
        
        // Caja de texto para nacionalidad
        textFieldNacionalidad = new JTextField();
        textFieldNacionalidad.setEditable(true);
        textFieldNacionalidad.setBounds(104, 176, 271, 30);
        getContentPane().add(textFieldNacionalidad);
        textFieldNacionalidad.setColumns(10);
        
        // Etiqueta de descripcion, si es proveedor
        lbDescripcion = new JLabel("Descripcion:");
        lbDescripcion.setBounds(10, 223, 65, 14);
        getContentPane().add(lbDescripcion);
        
     // Caja de texto para descripcion
        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setEditable(true);
        textFieldDescripcion.setBounds(104, 217, 271, 23);
        getContentPane().add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);
        
        JButton btnNewButton = new JButton("Modificar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		cmdModificoUsuarioActionPerformed(arg0);
        	}
        });
      
        btnNewButton.setBounds(144, 247, 89, 23);
        getContentPane().add(btnNewButton);
    }

    // Este metodo es invocado al querer buscar un usuario, funcionalidad 
    // provista por la operacion del sistem verInfoUsuario().
    // En caso de que haya un error de busqueda se despliega
    // un mensaje utilizando un panel de mensaje (JOptionPane).
    // No es necesario verificar que el campo con la cedula sea un numero ya
    // que internamente el sistema almacena la cedula como un string.
    protected void cmdBuscarUsuarioActionPerformed(ActionEvent e) {
        DataUsuario du;
        
        try {
            du = controlUsr.verInfoUsuario(textFieldCI.getText());
            textFieldNombre.setText(du.getNombre());
            textFieldApellido.setText(du.getApellido());
            
            if (du instanceof DataTurista) {
            	textFieldNacionalidad.setText(((DataTurista) du).getNacionalidad());
            	textFieldDescripcion.setEditable(false);
            }
            if (du instanceof DataProveedor) {
            	textFieldDescripcion.setText(((DataProveedor) du).getDescripcion());
            	textFieldNacionalidad.setEditable(false);
            }
            
        } catch (UsuarioNoExisteException e1) {  //  Esta es la exception controlada por nosotros
            // Si el usuario no existe, se muestra mensaje de error y se limpia el formulario.
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar Usuario", JOptionPane.ERROR_MESSAGE);
            limpiarFormulario();
        }

    }
    private boolean checkFormulario() {
        String nombreU = this.textFieldNombre.getText();
        String apellidoU = this.textFieldApellido.getText();
        String ciU = this.textFieldCI.getText();

        if (nombreU.isEmpty() || apellidoU.isEmpty() || ciU.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(ciU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La CI debe ser un numero", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    protected void cmdModificoUsuarioActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        // Obtengo datos de los controles Swing
        String nombreU = this.textFieldNombre.getText();
        String apellidoU = this.textFieldApellido.getText();
        String ciU = this.textFieldCI.getText();
        String nac =this.textFieldNacionalidad.getText(); // Nacionalidad para TUrista
        String desc = this.textFieldDescripcion.getText(); // Descripcion para Proveedor
        File file = new File("C:/Users/instalacion/Pictures/Saved Pictures/2.jpg");  
        byte[] foto = new byte[(int) file.length()];

        if (checkFormulario()) {
          
            	// Cargar el DataUsuario
             	DataUsuario du=null;
				if (nac!="") {
					du = new DataTurista(nombreU, apellidoU,ciU,foto,nac);
				
				}	
				else  {
					du = new DataProveedor(nombreU, apellidoU,ciU,foto,desc); 	
					
				}
				
				controlUsr.modificarUsuario(du);  // Esto modifica al objeto pasandole el DataType

                // Muestro exito de la operacion
                JOptionPane.showMessageDialog(this, "El Usuario se ha modificado con Exito", "Modificar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);

             // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);
        }
    }
 
    // Permite borrar el contenido de un formulario antes de cerrarlo.
    // Recordar que las ventanas no se destruyen, sino que simplemente 
    // se ocultan, por lo que conviene borrar la información para que 
    // no aparezca al mostrarlas nuevamente.
    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldCI.setText("");
        textFieldNacionalidad.setText("");
        textFieldDescripcion.setText("");
    }
    
}
