package presentacion;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import logica.IControladorUsuario;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

@SuppressWarnings("serial")
public class CrearActividad extends JInternalFrame {
	
	 // Controlador de usuarios que se utilizaraˇ para las acciones del JFrame
    private IControladorUsuario controlUsr;
	private JTextField textFieldNombre;
	private JTextField textFieldCosto;
	private JTextField textFieldFechaHasta;
	private JComboBox proveedor;
    
	public CrearActividad(IControladorUsuario icu) {
		
		// Se inicializa con el controlador de usuarios
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
	        textFieldFechaHasta.setToolTipText("Ingrese una Fecha");
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
        proveedor = new JComboBox();
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
		
	}
	
		
	
     
   
     
     }

