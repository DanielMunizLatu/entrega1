package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/**
 * Clase que conserva la coleccion global de los usuarios del sistema.
 * Los usuarios se identifican por su cedula de identidad.
 * Se implementa en base al patron Singleton.
 * 
 */ 
public class ManejadorUsuario {
    private Map<String, Usuario> usuariosCI;           // Coleccion
    private static ManejadorUsuario instancia = null;  // Instancia unica de manejador usando Singleton

    private ManejadorUsuario() {                       // Constructor privado
        usuariosCI = new HashMap<String, Usuario>();   // La coleccion es del tipo HashMap 
    }

    public static ManejadorUsuario getinstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();      // Constructor solo se llama de aca
        return instancia;
    }

    public void addUsuario(Usuario usu) {
        String ci = usu.getCedulaIdentidad();    // Get de la cedula
        usuariosCI.put(ci, usu);                 // agrego al usuario a la coleccion, o modifico
    }

    public Usuario obtenerUsuario(String ci) {     // Recibo una cedula y devuelvo el objeto Usuario
        return ((Usuario) usuariosCI.get(ci));     // Este get es de la API
    }

    public Usuario[] getUsuarios() {      // Devuelve la coleccion completa de los usuarios en array
        if (usuariosCI.isEmpty())
            return null;
        else {
            Collection<Usuario> usrs = usuariosCI.values(); // Metodo values devuelve la coleccion entera
            Object[] o = usrs.toArray();                    // Devuelve los objetos a una array     
            Usuario[] usuarios = new Usuario[o.length];     // Creo  un array de usuarios
            for (int i = 0; i < o.length; i++) {
                usuarios[i] = (Usuario) o[i];              // Cargo con la salida de toArray
            }

            return usuarios;
        }
    }
    
    public void addUsuarioPersistencia(Usuario usu) {
               
     // ENTITY PARA PERSISTENCIA
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
    	EntityManager em = emf.createEntityManager();

    	EntityTransaction tx = em.getTransaction();
    	
    	  // PERSISTO USUARIO
        
 		tx.begin();
 		System.out.println("Voy a persisti usuario");
 		em.persist(usu);
 		System.out.println("Persisti usuario");
 		tx.commit();
 		
 		em.close();
 		emf.close();
    }
    public Usuario obtenerUsuarioPersistencia(String ci) {     // Recibo una cedula y devuelvo el objeto Usuario

    	  // ENTITY PARA PERSISTENCIA
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
    	EntityManager em = emf.createEntityManager();

    	EntityTransaction tx = em.getTransaction();
    	
    	tx.begin();
 		System.out.println("Voy a buscar a la base");
        Usuario u = em.find(logica.Usuario.class,ci);        // como anterior pero de la base          
                                                              
        System.out.println("Persisti usuario");
 		tx.commit();
 		
 		em.close();
 		emf.close();
 		
 		return u;
    }

    public void modificarUsuarioPersistencia(String ci,Usuario newDatos) {     // Recibo una cedula y devuelvo el objeto Usuario

  	  // ENTITY PARA PERSISTENCIA
  	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
  	EntityManager em = emf.createEntityManager();

  	EntityTransaction tx = em.getTransaction();
  	
  	tx.begin();
		System.out.println("Voy a buscar a la base");
		 Usuario u = em.find(logica.Usuario.class,ci);      
         
		 u=newDatos;
		 
        em.merge(u);
        System.out.println("Persisti usuario");
		tx.commit();
		
		em.close();
		emf.close();
		
		//return u;
  }
 
}
