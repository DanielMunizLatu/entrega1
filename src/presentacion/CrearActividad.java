package presentacion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import dataType.DataActividad;
import dataType.DataProveedor;
import dataType.DataTurista;
import dataType.DataUsuario;
import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.IControladorActividad;
import logica.IControladorUsuario;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ItemEvent;
import java.awt.Font;
import java.text.ParseException;
import java.util.Date;
import java.time.LocalDate;
//import java.time.DateTimeFormatter;  // Esto no anda, no la reconoce
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class CrearActividad extends JInternalFrame {
	
	 // Controlador de proveedor que se utilizara¡ para las acciones del JFrame
  
	private JTextField textFieldNombre;
	private JTextField textFieldCosto;
	private JTextField textFieldFechaHasta;
	private JComboBox<String> proveedor;
	private IControladorActividad controlAct;
	private IControladorUsuario controlUsr;
	private JButton btnAceptar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnSalidas;
	private JLabel lblNewLabel;
	
	public CrearActividad(IControladorActividad ica,IControladorUsuario icu) {
		
		// Se inicializa con el controlador de actividades y usuarios
        controlAct = ica;
        controlUsr = icu;
		
     // Propiedades del JInternalFrame como dimension, posicion dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar una actividad Turistica");
        setBounds(10, 40, 409, 287);   // Sin esto no levanta, no se sabe donde va
        
        // Grid
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30,120, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 10, 26, 23, 39, 28, 0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel = new JLabel("Crear Actividad Turistica");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		 textFieldNombre = new JTextField();
	     GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
	        gbc_textFieldNombre.gridwidth = 2;
	        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
	        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
	        gbc_textFieldNombre.gridx = 1;
	        gbc_textFieldNombre.gridy = 2;
	        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
	        textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Costo");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldCosto = new JTextField();
        GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
        gbc_textFieldCosto.gridwidth = 2;
        gbc_textFieldCosto.fill = GridBagConstraints.BOTH;
        gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldCosto.gridx = 1;
        gbc_textFieldCosto.gridy = 3;
        getContentPane().add(textFieldCosto, gbc_textFieldCosto);
        textFieldCosto.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Hasta");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		 textFieldFechaHasta = new JTextField();
		 
	     textFieldFechaHasta.setToolTipText("Ingrese una Fecha dd/mm/aaaa");
	     textFieldFechaHasta.setColumns(10);
	     GridBagConstraints gbc_textFieldFechaHasta = new GridBagConstraints();
	     gbc_textFieldFechaHasta.gridwidth = 2;
	     gbc_textFieldFechaHasta.fill = GridBagConstraints.BOTH;
	     gbc_textFieldFechaHasta.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldFechaHasta.gridx = 1;
	     gbc_textFieldFechaHasta.gridy = 4;
	     getContentPane().add(textFieldFechaHasta, gbc_textFieldFechaHasta);
	    
	   
		
		JLabel lblNewLabel_4 = new JLabel("Proveedor");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		// Combo Box para el proveedor
        proveedor = new JComboBox<String>();
        GridBagConstraints gbc_proveedor = new GridBagConstraints();
        gbc_proveedor.gridwidth = 2;
        gbc_proveedor.insets = new Insets(0, 0, 5, 5);
        gbc_proveedor.fill = GridBagConstraints.HORIZONTAL;
        gbc_proveedor.gridx = 1;
        gbc_proveedor.gridy = 5;
        //GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
        gbc_proveedor.gridwidth = 2;
        gbc_proveedor.gridx = 1;
        getContentPane().add(proveedor, gbc_proveedor);
        
  
      
    // Boton agregar
  
    btnAceptar = new JButton("Agregar");
    GridBagConstraints gbc_agregar = new GridBagConstraints();
    gbc_agregar.insets = new Insets(0, 0, 5, 5);
    gbc_agregar.gridx = 1;
    gbc_agregar.gridy = 6;
    getContentPane().add(btnAceptar, gbc_agregar);
    
    btnAceptar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            cmdRegistroActividadActionPerformed(arg0);
        }
    });
    
    // Boton consultar
    
    btnConsultar = new JButton("Consultar");
    GridBagConstraints gbc_consultar = new GridBagConstraints();
    gbc_consultar.insets = new Insets(0, 0, 5, 5);
    gbc_consultar.gridx = 2;
    gbc_consultar.gridy = 6;
    getContentPane().add(btnConsultar, gbc_consultar);
    
    btnModificar = new JButton("Modificar");
    GridBagConstraints gbc_Modificar = new GridBagConstraints();
    gbc_Modificar.insets = new Insets(0, 0, 0, 5);
    gbc_Modificar.gridx = 1;
    gbc_Modificar.gridy = 7;
    getContentPane().add(btnModificar, gbc_Modificar);
    
    btnSalidas = new JButton("Salidas");
    GridBagConstraints gbc_Salidas = new GridBagConstraints();
    gbc_Salidas.insets = new Insets(0, 0, 0, 5);
    gbc_Salidas.gridx = 2;
    gbc_Salidas.gridy = 7;
    getContentPane().add(btnSalidas, gbc_Salidas);
    
    btnConsultar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		cmdConsultaActividadActionPerformed(e);
    	}
    });
   
    }// Aca se cierra el constructor
		    
    // Metodo que carga el combo con los proveedores
    
	   	          
       public void cargarProveedoresPersistencia() {
        	
    	   DefaultComboBoxModel<String> model;                  // Este modelo se crea para carga el combo 
           model = new DefaultComboBoxModel<String>();                   //Aca se carga
   		   for (String opcion : controlUsr.getProveedoresPersistencia()) {
   		        model.addElement(opcion);
   		    }
   		   proveedor.setModel(model);
 
        }
       protected void cmdRegistroActividadActionPerformed(ActionEvent arg0) {
           // TODO Auto-generated method stub

           // Obtengo datos de los controles Swing
           String nombreA = this.textFieldNombre.getText();
           String costoA = this.textFieldCosto.getText();
           int intCosto=Integer.parseInt(costoA);
           String fechaHastaA = this.textFieldFechaHasta.getText();
           
     	  // Definir el formato de fecha deseado
           DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

           // Formatear la fecha y establecerla en el JTextField
           LocalDate date = LocalDate.parse(fechaHastaA, dateFormatter);
           textFieldFechaHasta.setText(dateFormatter.format(date));
           
           
           // Combo
           String valorCombo=(String) proveedor.getSelectedItem();
      
           if (checkFormulario()) {
               try {
               
            	DataActividad da=null;   
            	da = new DataActividad(nombreA,intCosto,date,valorCombo);   
   				controlAct.registrarActividad(da); // Esto registra al objeto pasandole el DataType
   				

                // Muestro exito de la operacion
                JOptionPane.showMessageDialog(this, "La actividad se ha creado con Exito", "Registrar Usuario",
                           JOptionPane.INFORMATION_MESSAGE);

               } catch (ActividadRepetidaException e) {
                   // Muestro error de registro
                   JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Actividad", JOptionPane.ERROR_MESSAGE);
               }

               // Limpio el internal frame antes de cerrar la ventana
               limpiarFormulario();
               setVisible(false);
           }
       }
       public void cmdConsultaActividadActionPerformed(ActionEvent e) {
    	   
    	   DataActividad da;
    	   try {
               da = controlAct.verInfoActividad(textFieldNombre.getText());
               textFieldNombre.setText(da.getNombre());
               textFieldCosto.setText(Integer.toString(da.getCosto()));
               // Formateo la fecha
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               String fechaComoString = da.getFechaHasta().format(formatter);

               textFieldFechaHasta.setText(fechaComoString);
               
               proveedor.setSelectedItem(da.getProve());
               
           } catch (ActividadNoExisteException e1) {  //  Esta es la exception controlada por nosotros
               // Si el usuario no existe, se muestra mensaje de error y se limpia el formulario.
               JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar Actividad", JOptionPane.ERROR_MESSAGE);
               limpiarFormulario();
           }
       }
       private boolean checkFormulario() {
    	   String nombreA = this.textFieldNombre.getText();
           String costoA = this.textFieldCosto.getText();
           String fechaHastaA = this.textFieldFechaHasta.getText();

           if (nombreA.isEmpty() || costoA.isEmpty() || fechaHastaA.isEmpty()) {
               JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Registrar Usuario",
                       JOptionPane.ERROR_MESSAGE);
               return false;
           }

           try {
               Integer.parseInt(costoA);
           } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(this, "El costo debe ser un numero", "Registrar Usuario",
                       JOptionPane.ERROR_MESSAGE);
               return false;
           }

           return true;
       }
       private void limpiarFormulario() {
           textFieldNombre.setText("");
           textFieldCosto.setText("");
           textFieldFechaHasta.setText("");
        
       }
       
      
     }

