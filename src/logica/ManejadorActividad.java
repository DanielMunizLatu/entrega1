package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ManejadorActividad {
	
	 private Map<String, Actividad> actividadNombre;               // Coleccion
	 private static ManejadorActividad instancia = null;          // Instancia unica de manejador usando Singleton

	 private ManejadorActividad() {                                // Constructor privado
	        actividadNombre = new HashMap<String, Actividad>();    // La coleccion es del tipo HashMap 

	 }
	 public static ManejadorActividad getinstance() {
	        if (instancia == null)
	            instancia = new ManejadorActividad();      // Constructor solo se llama de aca
	        return instancia;
	    }
	 
	 
	 public void addActividad(Actividad act) {
	        String nom = act.getNombre();                 // Get de la nombre (clave)
	        actividadNombre.put(nom, act);                // agrego la actividad a la coleccion
	    }
	 
	 
	  public void addActividadPersistencia(Actividad act) {
          
		     // ENTITY PARA PERSISTENCIA
		    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
		    	EntityManager em = emf.createEntityManager();

		    	EntityTransaction tx = em.getTransaction();
		    	
		    	  // PERSISTO USUARIO
		        
		 		tx.begin();
		 		
		 		em.persist(act);
		 
		 		tx.commit();
		 		
		 		em.close();
		 		emf.close();
		    }
	  
	 public Actividad obtenerActividad(String nom) {           // Recibo un nombre y devuelvo el objeto Actividad
	        return ((Actividad) actividadNombre.get(nom));   // Este get es de la API
	    }
	 
	 public Actividad[] getActividades() {      // Devuelve la coleccion completa de las actividades en array
	        if (actividadNombre.isEmpty())      // isEmpty tambien es de la API
	            return null;
	        else {
	            Collection<Actividad> activs = actividadNombre.values(); // Metodo values devuelve la coleccion entera
	            Object[] o = activs.toArray();                           // Devuelve los objetos a una array     
	            Actividad[] actividades = new Actividad[o.length];       // Creo  un array de actividades
	            for (int i = 0; i < o.length; i++) {
	                actividades[i] = (Actividad) o[i];                   // Cargo con la salida de toArray
	            }

	            return actividades;
	        }
	    }
}