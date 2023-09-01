package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class prueba_combo {

	

	
	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Ejemplo de JComboBox con mensaje en consola");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(new FlowLayout());

	        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Opción 1", "Opción 2", "Opción 3"});
	        frame.add(comboBox);

	        // Agregar un ActionListener al JComboBox para imprimir un mensaje en consola
	        comboBox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Imprimir un mensaje en la consola cuando se hace clic en el JComboBox
	                System.out.println("Clic en el JComboBox");
	            }
	        });

	        frame.pack();
	        frame.setVisible(true);
	    }
	
}
