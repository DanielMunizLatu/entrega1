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

import dataType.DataProveedor;
import dataType.DataSalida;
import dataType.DataTurista;
import dataType.DataUsuario;
import excepciones.SalidaNoExisteException;
import excepciones.SalidaRepetidaException;
import excepciones.UsuarioNoExisteException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import logica.Actividad;
import logica.IControladorActividad;

import logica.ManejadorActividad;
import logica.Salida;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ConsultarSalida extends JInternalFrame{
	

	private JTextField textFieldNombre;
	private JTextField textFieldFecha;
	private JTextField textFieldHora;
	private JTextField textFieldTuristaMax;
	private JTextField textFieldLugar;
	private JTextField textFieldFechaAlta;
	
	private JComboBox<String> actividad;
	private JComboBox<String> salida;
	
	private IControladorActividad controlAct;
	

	public ConsultarSalida(IControladorActividad ica) {
		
		// Se inicializa con el controlador de actividades
        controlAct = ica;
		
		  // Propiedades del JInternalFrame como dimension, posicion dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar una Salida");
        setBounds(10, 40, 409, 287);   // Sin esto no levanta, no se sabe donde va
        
        // Grid
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30,120, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 29, 32, 26, 31, 28, 0, 0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_5 = new JLabel("Consultar Salida");
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
			
			// Combo Box para el proveedor
	     actividad = new JComboBox<String>();
	     GridBagConstraints gbc_actividad = new GridBagConstraints();
	     gbc_actividad.gridwidth = 2;
	     gbc_actividad.insets = new Insets(0, 0, 5, 5);
	     gbc_actividad.fill = GridBagConstraints.HORIZONTAL;
	     gbc_actividad.gridx = 1;
	     gbc_actividad.gridy = 1;
	     //GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
	     gbc_actividad.gridwidth = 2;
	     gbc_actividad.gridx = 1;
	     getContentPane().add(actividad, gbc_actividad);
	     
	     // Carga de combo proveedor se hace desde principal
	     // Tengo que cargar las salidas
	
	     salida = new JComboBox<String>();
	     // Escucha en el combo actividad
	    
	     actividad.addActionListener(new ActionListener() {
		     	
		         public void actionPerformed(ActionEvent e) {
		    	    // Cargar Salida
		    		 String seleccion = (String) actividad.getSelectedItem(); //esta variable carga la eleccion de actividad
		    		 cargarSalidasPersistencia(seleccion); 
		          
		         }
		     });
		
		JLabel nombre = new JLabel("Salida");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(nombre, gbc_lblNewLabel_1);
		
		// Combo Box para salida
        salida = new JComboBox<String>();
        GridBagConstraints gbc_salida = new GridBagConstraints();
        gbc_salida.gridwidth = 2;
        gbc_salida.insets = new Insets(0, 0, 5, 5);
        gbc_salida.fill = GridBagConstraints.HORIZONTAL;
        gbc_salida.gridx = 1;
        gbc_salida.gridy = 2;
        gbc_salida.gridwidth = 2;
        gbc_salida.gridx = 1;
        getContentPane().add(salida, gbc_salida);
		
	    JLabel lblNewLabel_9 = new JLabel("Fecha");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 3;
		getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		 textFieldFecha = new JTextField();
	     GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
	     gbc_textFieldFecha.gridwidth = 2;
	     gbc_textFieldFecha.fill = GridBagConstraints.BOTH;
	     gbc_textFieldFecha.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldFecha.gridx = 1;
	     gbc_textFieldFecha.gridy = 3;
	     getContentPane().add(textFieldFecha, gbc_textFieldFecha);
	     textFieldFecha.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Hora");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		

		 textFieldHora = new JTextField();
	     GridBagConstraints gbc_textFieldHora = new GridBagConstraints();
	     gbc_textFieldHora.gridwidth = 2;
	     gbc_textFieldHora.fill = GridBagConstraints.BOTH;
	     gbc_textFieldHora.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldHora.gridx = 1;
	     gbc_textFieldHora.gridy = 4;
	     getContentPane().add(textFieldHora, gbc_textFieldHora);
	     textFieldHora.setColumns(10);
		
	     JLabel lblNewLabel_45 = new JLabel("Turista Max");
			GridBagConstraints gbc_lblNewLabel_45 = new GridBagConstraints();
			gbc_lblNewLabel_45.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_45.gridx = 0;
			gbc_lblNewLabel_45.gridy = 5;
			getContentPane().add(lblNewLabel_45, gbc_lblNewLabel_45);
			
			 textFieldTuristaMax = new JTextField();
		     GridBagConstraints gbc_textFieldTuristaMax = new GridBagConstraints();
		     gbc_textFieldTuristaMax.gridwidth = 2;
		     gbc_textFieldTuristaMax.fill = GridBagConstraints.BOTH;
		     gbc_textFieldTuristaMax.insets = new Insets(0, 0, 5, 5);
		     gbc_textFieldTuristaMax.gridx = 1;
		     gbc_textFieldTuristaMax.gridy = 5;
		     getContentPane().add(textFieldTuristaMax, gbc_textFieldTuristaMax);
		     textFieldTuristaMax.setColumns(10);
	     
		JLabel lblNewLabel_4 = new JLabel("Lugar");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 6;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		 textFieldLugar = new JTextField();
	     GridBagConstraints gbc_textFieldLugar = new GridBagConstraints();
	     gbc_textFieldLugar.gridwidth = 2;
	     gbc_textFieldLugar.fill = GridBagConstraints.BOTH;
	     gbc_textFieldLugar.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldLugar.gridx = 1;
	     gbc_textFieldLugar.gridy = 6;
	     getContentPane().add(textFieldLugar, gbc_textFieldLugar);
	     textFieldLugar.setColumns(10);
	     
	 	JLabel lblNewLabel_46 = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblNewLabel_46 = new GridBagConstraints();
		gbc_lblNewLabel_46.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_46.gridx = 0;
		gbc_lblNewLabel_46.gridy = 7;
		getContentPane().add(lblNewLabel_46, gbc_lblNewLabel_46);
		
		 textFieldFechaAlta = new JTextField();
	     GridBagConstraints gbc_textFechaAlta = new GridBagConstraints();
	     gbc_textFechaAlta.gridwidth = 2;
	     gbc_textFechaAlta.fill = GridBagConstraints.BOTH;
	     gbc_textFechaAlta.insets = new Insets(0, 0, 5, 5);
	     gbc_textFechaAlta.gridx = 1;
	     gbc_textFechaAlta.gridy = 7;
	     getContentPane().add(textFieldFechaAlta, gbc_textFechaAlta);
	     textFieldFechaAlta.setColumns(10);
	     
	     JButton btnNewButton = new JButton("Consultar");
	     btnNewButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		 try {
					cmdConsultarSalidaActionPerformed(e);
				} catch (SalidaNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	     	}
	     });
	     GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	     gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
	     gbc_btnNewButton.gridx = 1;
	     gbc_btnNewButton.gridy = 8;
	     getContentPane().add(btnNewButton, gbc_btnNewButton);
		
	}  // Aca termina el constructor
	
	 // Metodo que carga el combo con las actividades
        
   	
    public void cargarActividadesPersistencia() {
     	
    	System.out.println("Hola ENTRE A CARGAR ACTIIVDAD");	
  	    DefaultComboBoxModel<String> model;                // Este modelo se crea para carga el combo 
         model = new DefaultComboBoxModel<String>();                   //Aca se carga
 		 for (String opcion : controlAct.getActividadesPersistencia()) {
 		        model.addElement(opcion);
 		    }
 		   actividad.setModel(model);
     }
    
    public void cargarSalidasPersistencia(String seleccion) {
    	
   	 salida.removeAllItems();
         
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
 		  EntityManager em = emf.createEntityManager();
      
         TypedQuery<Salida> query = em.createQuery("SELECT s FROM Actividad a JOIN a.salidas s WHERE a.nombre =:nombreAct",Salida.class);
         query.setParameter("nombreAct", seleccion);
         List<Salida> result = query.getResultList();
         
 		// Cargo el combo de salida con el resultado de jpql
 		for (Salida dato : result) {
 			System.out.println(dato.getNombre());
            salida.addItem(dato.getNombre());         // Cargo el combo de salidas
         }
 		em.close();
 		emf.close();
   }

    public void cmdConsultarSalidaActionPerformed(ActionEvent e) throws SalidaNoExisteException {
    	  DataSalida ds=null;
          
          try {
              ds = controlAct.verInfoSalida((String)actividad.getSelectedItem(),(String)salida.getSelectedItem());
              
              LocalDate fecha = ds.getFecha();
              // Definir el formato de fecha deseado
             // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
              DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          
             //Fecha
              String fechaComoString = fecha.format(formatoFecha);
              textFieldFecha.setText(fechaComoString);   // Puse la fecha en caja de texto
              
              // Hora
              int hora = ds.getHora();
              String horaString=String.valueOf(hora) ;
              textFieldHora.setText(horaString);
              
              // Maximo de Turistas
              int TuristaMax=ds.getTuristaMax();
              String TuristaMaxString=String.valueOf(TuristaMax);
              textFieldTuristaMax.setText(TuristaMaxString);
          	 
              // Lugar 
              textFieldLugar.setText(ds.getLugar());
          	  
              // Fecha Alta
              String fechaAltaString = fecha.format(formatoFecha);
              textFieldFechaAlta.setText(fechaAltaString);
              
              
          } catch (SalidaNoExisteException e1) {  //  Esta es la exception controlada por nosotros
              // Si el usuario no existe, se muestra mensaje de error y se limpia el formulario.
              JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar Usuario", JOptionPane.ERROR_MESSAGE);
              limpiarFormulario();
          }
    }
    
    
    private boolean checkFormulario() {
 	   String nombreA = this.textFieldNombre.getText();
        String fecha = this.textFieldFecha.getText();
        String hora = this.textFieldHora.getText();

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
        textFieldNombre.setText("");
        textFieldFecha.setText("");
        textFieldHora.setText("");
        textFieldLugar.setText("");
        textFieldTuristaMax.setText("");
        
     
    }
}
