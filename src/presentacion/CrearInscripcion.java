package presentacion;

import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import dataType.DataInscripcion;

import logica.IControladorActividad;
import logica.IControladorUsuario;



import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CrearInscripcion extends JInternalFrame{
	
	private JTextField textFieldFechaIns;
	private JTextField textFieldCantTur;
	private JTextField textFieldCosto;
	
	private JComboBox<String> actividad;
	private JComboBox<String> salida;
	private JComboBox<String> turista;
	
	private IControladorUsuario controlUsu;
	private IControladorActividad controlAct;
	
	public CrearInscripcion(IControladorActividad ica,IControladorUsuario icu) {
		
		// Se inicializa con el controlador de actividades
        controlAct = ica;
        controlUsu=icu;
		
		  // Propiedades del JInternalFrame como dimension, posicion dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar una Salida");
        setBounds(10, 40, 409, 287);   // Sin esto no levanta, no se sabe donde va
        
        // Grid
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30,120, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 29, 32, 36, 31, 28, 0, 0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_5 = new JLabel("Registrar Inscripcion");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 0;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
								
	     
	    JLabel lblNewLabel_2 = new JLabel("Actividad");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		// Combo Box para actividad
        actividad = new JComboBox<String>();
        salida = new JComboBox<String>();
        // Escucha en el combo actividad
        actividad.addActionListener(new ActionListener() {
	     	
	    	// @Override
	         public void actionPerformed(ActionEvent e) {
	    		 
	    		// Cargar Salida
	    		 String seleccion = (String) actividad.getSelectedItem();
	    		 cargarSalidas(seleccion); 
	           
	         }
	     });
        GridBagConstraints gbc_proveedor = new GridBagConstraints();
        gbc_proveedor.gridwidth = 2;
        gbc_proveedor.insets = new Insets(0, 0, 5, 5);
        gbc_proveedor.fill = GridBagConstraints.HORIZONTAL;
        gbc_proveedor.gridx = 1;
        gbc_proveedor.gridy = 1;
        //GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
        gbc_proveedor.gridwidth = 2;
        gbc_proveedor.gridx = 1;
        getContentPane().add(actividad, gbc_proveedor);
        
		
		JLabel lblNewLabel_9 = new JLabel("Salida");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 2;
		getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
		
	  		 
        GridBagConstraints gbc_salida = new GridBagConstraints();
        gbc_salida.gridwidth = 2;
        gbc_salida.insets = new Insets(0, 0, 5, 5);
        gbc_salida.fill = GridBagConstraints.HORIZONTAL;
        gbc_salida.gridx = 1;
        gbc_salida.gridy = 2;
        //GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
        gbc_salida.gridwidth = 2;
        gbc_salida.gridx = 1;
        
        getContentPane().add(salida, gbc_salida);
		
       
    	JLabel lblNewLabel_95 = new JLabel("Turista");
		GridBagConstraints gbc_lblNewLabel_95 = new GridBagConstraints();
		gbc_lblNewLabel_95.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_95.gridx = 0;
		gbc_lblNewLabel_95.gridy = 3;
		getContentPane().add(lblNewLabel_95, gbc_lblNewLabel_95);
		
		// Combo Box para para turista
        turista = new JComboBox<String>();
        GridBagConstraints gbc_turista = new GridBagConstraints();
        gbc_turista.gridwidth = 2;
        gbc_turista.insets = new Insets(0, 0, 5, 5);
        gbc_turista.fill = GridBagConstraints.HORIZONTAL;
        gbc_turista.gridx = 1;
        gbc_turista.gridy = 3;
        //GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
              
        getContentPane().add(turista, gbc_turista);
        
        
     	JLabel lblNewLabel_46 = new JLabel("Fecha Inscripcion");
    		GridBagConstraints gbc_lblNewLabel_46 = new GridBagConstraints();
    		gbc_lblNewLabel_46.insets = new Insets(0, 0, 5, 5);
    		gbc_lblNewLabel_46.gridx = 0;
    		gbc_lblNewLabel_46.gridy = 4;
    		getContentPane().add(lblNewLabel_46, gbc_lblNewLabel_46);
    		
    		// Caja Texto Fecha Inscripcion
    		 textFieldFechaIns = new JTextField();
    	     GridBagConstraints gbc_textFechaIns = new GridBagConstraints();
    	     gbc_textFechaIns.gridwidth = 2;
    	     gbc_textFechaIns.fill = GridBagConstraints.BOTH;
    	     gbc_textFechaIns.insets = new Insets(0, 0, 5, 5);
    	     gbc_textFechaIns.gridx = 1;
    	     gbc_textFechaIns.gridy = 4;
    	     getContentPane().add(textFieldFechaIns, gbc_textFechaIns);
    	     textFieldFechaIns.setColumns(10);
       
	     JLabel lblNewLabel_45 = new JLabel("Cantidad Turistas");
			GridBagConstraints gbc_lblNewLabel_45 = new GridBagConstraints();
			gbc_lblNewLabel_45.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_45.gridx = 0;
			gbc_lblNewLabel_45.gridy = 5;
			getContentPane().add(lblNewLabel_45, gbc_lblNewLabel_45);
			
			 textFieldCantTur = new JTextField();
		     GridBagConstraints gbc_textFieldCantTur = new GridBagConstraints();
		     gbc_textFieldCantTur.gridwidth = 2;
		     gbc_textFieldCantTur.fill = GridBagConstraints.BOTH;
		     gbc_textFieldCantTur.insets = new Insets(0, 0, 5, 5);
		     gbc_textFieldCantTur.gridx = 1;
		     gbc_textFieldCantTur.gridy = 5;
		     getContentPane().add(textFieldCantTur, gbc_textFieldCantTur);
		     textFieldCantTur.setColumns(10);
	     
		JLabel lblNewLabel_4 = new JLabel("Costo");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 6;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		 textFieldCosto = new JTextField();
	     GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
	     gbc_textFieldCosto.gridwidth = 2;
	     gbc_textFieldCosto.fill = GridBagConstraints.BOTH;
	     gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldCosto.gridx = 1;
	     gbc_textFieldCosto.gridy = 6;
	     getContentPane().add(textFieldCosto, gbc_textFieldCosto);
	     textFieldCosto.setColumns(10);
	     
	     
	     JButton btnNewButton = new JButton("Agregar");
	     btnNewButton.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent e) {
	     		 cmdRegistroInscripcionActionPerformed(e);
	     	}
	     });
	     GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	     gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
	     gbc_btnNewButton.gridx = 1;
	     gbc_btnNewButton.gridy = 8;
	     getContentPane().add(btnNewButton, gbc_btnNewButton);
	     
	     JButton btnNewButtonC = new JButton("Consultar");
	     btnNewButtonC.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent e) {
	     		 cmdConsultarInscripcionActionPerformed(e);
	     	}
	     });
	     GridBagConstraints gbc_btnNewButtonC = new GridBagConstraints();
	     gbc_btnNewButtonC.insets = new Insets(0, 0, 0, 5);
	     gbc_btnNewButtonC.gridx = 2;
	     gbc_btnNewButtonC.gridy = 8;
	     getContentPane().add(btnNewButtonC, gbc_btnNewButtonC);
	     
	    System.out.println("Se termino el constructor");
	}  // Aca termina el constructor
	
	 // Metodo que carga el combo con las actividades y el de salidas segun el de actividades
          
    public void cargarActividadPersistencia() {
     	
 	   DefaultComboBoxModel<String> model;                // Este modelo se crea para carga el combo 
        model = new DefaultComboBoxModel<String>();                   //Aca se carga
		   for (String opcion : controlAct.getActividadesPersistencia()) {
		        model.addElement(opcion);
		    }
		   actividad.setModel(model);
    }
  
    public void cargarSalidas(String seleccion) {
    	
    	 salida.removeAllItems();
         List<String> ds=null;   
         // Llamo al metodo en capa logica, controlador de Actividad que me devuelve lista de string
         ds=controlAct.cargarSalidasPersistencia(seleccion) ;
  	    // Cargo el combo de salida con el resultado de jpql
  		for  (int i = 0; i < ds.size(); i++) {
  			System.out.println(ds.get(i));
             salida.addItem(ds.get(i));         // Cargo el combo de salidas
          }
  		
    }
    
    // Metodo para cargar a los turistas
    public void cargarTuristaPersistencia() {
     	
  	   DefaultComboBoxModel<String> model;                // Este modelo se crea para carga el combo 
       model = new DefaultComboBoxModel<String>();                   //Aca se carga
 		   for (String opcion : controlUsu.getProveedoresPersistencia()) {
 		        model.addElement(opcion);
 		    }
 		   turista.setModel(model);

      }
	
    protected void cmdRegistroInscripcionActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    	    	
        // Obtengo datos de los controles Swing
    	String actividadCombo= (String ) actividad.getSelectedItem();
        String salidaCombo=(String) salida.getSelectedItem();
        String turistaCombo=(String) turista.getSelectedItem();
    	
        String fecha = this.textFieldFechaIns.getText();
        // Definir el formato de fecha deseado
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formatear la fecha y establecerla en el JTextField
        LocalDate date = LocalDate.parse(fecha, dateFormatter);
        textFieldFechaIns.setText(dateFormatter.format(date)); 
        
        // CantTur Integer
        String cantTur=this.textFieldCantTur.getText();
        int intTur=Integer.parseInt(cantTur);
        
        // Costo Integer
        String costo=this.textFieldCosto.getText();
        int intCosto=Integer.parseInt(costo);
              
        if (checkFormulario()) {
            DataInscripcion di=null;   
         	di = new DataInscripcion(date,intTur,intCosto,turistaCombo);
            // Agrego con el controlador de Inscripcion
         	controlAct.agregarInscripcion(actividadCombo,salidaCombo,di);
    
             // Muestro exito de la operacion
             JOptionPane.showMessageDialog(this, "La inscripcion se ha creado con Exito", "Registrar Inscripcion",
                        JOptionPane.INFORMATION_MESSAGE);

            // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);
        }
    }
    //Consultar una inscripcion
    protected void cmdConsultarInscripcionActionPerformed(ActionEvent arg0) {
    	 
    	DataInscripcion di;  // Tengo cargar un Data Inscripcion para mandar a capa Logica
    	
    	// Obtengo datos de los controles Swing
    	String actividadCombo= (String ) actividad.getSelectedItem();
        String salidaCombo=(String) salida.getSelectedItem();
        String turistaCombo=(String) turista.getSelectedItem();
        
     //   di=controlAct.verInfoInscripcion(actividadCombo,salidaCombo,turistaCombo);
       
        
    }
    
    private boolean checkFormulario() {
 	   String nombreA = this.textFieldFechaIns.getText();
        String fecha = this.textFieldCosto.getText();
        String hora = this.textFieldCantTur.getText();

        if (nombreA.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Registrar Salida",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
           
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El costo debe ser un numero", "Registrar Salida",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    private void limpiarFormulario() {
        textFieldFechaIns.setText("");
        textFieldCosto.setText("");
        textFieldCantTur.setText("");
       
     
    }
}
