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
	private JButton btnAceptar;
	private IControladorActividad controlAct;
	private IControladorUsuario controlUsr;
	
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
        setBounds(10, 40, 408, 266);   // Sin esto no levanta, no se sabe donde va
        
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30,120, 120, 0};
		gridBagLayout.rowHeights = new int[]{10, 36, 39, 36, 39, 28, 0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Crear Actividad Turistica");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		 textFieldNombre = new JTextField();
	     GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
	        gbc_textFieldNombre.gridwidth = 2;
	        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
	        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
	        gbc_textFieldNombre.gridx = 1;
	        gbc_textFieldNombre.gridy = 1;
	        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
	        textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Costo");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldCosto = new JTextField();
        GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
        gbc_textFieldCosto.gridwidth = 2;
        gbc_textFieldCosto.fill = GridBagConstraints.BOTH;
        gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldCosto.gridx = 1;
        gbc_textFieldCosto.gridy = 2;
        getContentPane().add(textFieldCosto, gbc_textFieldCosto);
        textFieldCosto.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Hasta");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		 textFieldFechaHasta = new JTextField();
		 
	     textFieldFechaHasta.setToolTipText("Ingrese una Fecha dd/mm/aaaa");
	     textFieldFechaHasta.setColumns(10);
	     GridBagConstraints gbc_textFieldFechaHasta = new GridBagConstraints();
	     gbc_textFieldFechaHasta.gridwidth = 2;
	     gbc_textFieldFechaHasta.fill = GridBagConstraints.BOTH;
	     gbc_textFieldFechaHasta.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldFechaHasta.gridx = 1;
	     gbc_textFieldFechaHasta.gridy = 3;
	     getContentPane().add(textFieldFechaHasta, gbc_textFieldFechaHasta);
	    
	   
		
		JLabel lblNewLabel_4 = new JLabel("Proveedor");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		// Combo Box para el proveedor
        proveedor = new JComboBox<String>();
        GridBagConstraints gbc_proveedor = new GridBagConstraints();
        gbc_proveedor.gridwidth = 2;
        gbc_proveedor.insets = new Insets(0, 0, 5, 5);
        gbc_proveedor.fill = GridBagConstraints.HORIZONTAL;
        gbc_proveedor.gridx = 1;
        gbc_proveedor.gridy = 4;
        //GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
        gbc_proveedor.gridwidth = 2;
        gbc_proveedor.gridx = 1;
        getContentPane().add(proveedor, gbc_proveedor);
        
       	
        System.out.println("Di click en el combo 1");
        //Ahora cargamos el comboBox proveedores
       //proveedor.addItemListener(new ItemListener() {
        	
        
    proveedor.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseClicked(MouseEvent e) {
    		
    		   //System.out.println("Di click en el combo 1xxx");
    		   cargarProveedoresPersistencia();
    	  }
     
    	  
	  });
      
    // Boton agregar
  
    btnAceptar = new JButton("Agregar");
    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton.gridx = 1;
    gbc_btnNewButton.gridy = 5;
    getContentPane().add(btnAceptar, gbc_btnNewButton);
    
    btnAceptar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            cmdRegistroUsuarioActionPerformed(arg0);
        }
    });
	 }// Aca se cierra el constructor
		    
    
    // Metodo que carga el combo con los proveedores
    
	   	          
       public void cargarProveedoresPersistencia() {
        	
    	   DefaultComboBoxModel<String> model;              // Este modelo se crea para carga el combo 
           model = new DefaultComboBoxModel<String>();                   //Aca se carga
   		   for (String opcion : controlUsr.getProveedoresPersistencia()) {
   		        model.addElement(opcion);
   		    }
   		   proveedor.setModel(model);

        }
       protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
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

