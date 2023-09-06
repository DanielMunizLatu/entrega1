package logica;

import java.util.ArrayList;
import java.util.List;

import dataType.DataActividad;
import dataType.DataInscripcion;
import dataType.DataSalida;
import dataType.DataTurista;
import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.SalidaNoExisteException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


public class ControladorActividad implements IControladorActividad{
	
	 public ControladorActividad() {
	    }
	 
	 public void registrarActividad(DataActividad actividad) throws ActividadRepetidaException {
	        
		    ManejadorActividad ma = ManejadorActividad.getinstance();
	        Actividad a = ma.obtenerActividad(actividad.getNombre());  // Lo voy a buscar a la coleccion
	        
	        if (a != null)  // Si lo encontre es porque ya existe
	            throw new ActividadRepetidaException("La actividad " + actividad.getNombre() + " ya esta registrada");

	        Actividad nuevaActividad=null;
	        nuevaActividad = new Actividad (actividad.getNombre(),actividad.getCosto(),actividad.getFechaHasta(),actividad.getProve());
	        
	        // Agrego la actividad a la coleccion
	        ma.addActividad(nuevaActividad);
	        
	        // Ahora persisto
	        ma.addActividadPersistencia(nuevaActividad);
	       
	    }
	// Devuelvo una DataActividad para ser mostrado en la capa de presentacion
	    
	    public DataActividad verInfoActividad(String nombre) throws ActividadNoExisteException {
	      
	    	ManejadorActividad ma = ManejadorActividad.getinstance();  // ma tiene la coleccion
	        //Actividad a = ma.obtenerActividad(nombre);                 // a obtiene el usuario pasado por parametro 
	        Actividad a = ma.obtenerActividadPersistencia(nombre);   // de la base traigo
	    	
	        if (a != null) { // Si lo encontre es porque ya existe, solo traigo sus datos
	        	
	        	DataActividad da =a.getDataActividad();  // Cargo en un dataActividad, que esta sobrescrito
	        	return da;                               // segun el tipo de usuario que sea
	        		
	          // return new DataUsuario(u.getNombre(), u.getApellido(), u.getCedulaIdentidad(),((Turista) u).getNacionalidad());
	        }    
	        else
	            throw new ActividadNoExisteException("La Actividad " + nombre + " no existe");

	    } 
	    
	    // Get de actividades, pero no de los objetos sino de los Dataactividades
	    // Devuelve la coleccion de actividades en un array de DataActividades
	    
	    public DataActividad[] getActividad() throws ActividadNoExisteException {
	        ManejadorActividad ma = ManejadorActividad.getinstance();
	        Actividad[] acts = ma.getActividades();  // Usa el getActividades que devuelve array de objetos

	        if (acts != null) {
	            DataActividad[] da = new DataActividad[acts.length];
	            Actividad actividad;    // Variables para ir cargando los objetos en el for

	            // Para separar logica de presentacion, no se deben devolver las actividades, sino los DataActividad
	            for (int i = 0; i < acts.length; i++) {
	                actividad = acts[i];
	                da[i]=actividad.getDataActividad();  // Cargo el dataType desde el objeto
	         
	            }

	            return da;
	        } else
	            throw new ActividadNoExisteException("No existen actividaes registradas");

	    }
	    
	    public void modificarActividad(DataActividad actividad)  {
	        
	    	ManejadorActividad ma = ManejadorActividad.getinstance();
	      //  Usuario u = mu.obtenerUsuario(usuario.getCedulaIdentidad());  // Lo voy a buscar a la coleccion
	    
	        Actividad nuevaActividad=null;
	        
	        // Cargo el objeto con el Datatype
	        nuevaActividad = new Actividad (actividad.getNombre(),actividad.getCosto(),actividad.getFechaHasta(),actividad.getProve());
	       
	        // Agrego el usuario a la coleccion
	        ma.addActividad(nuevaActividad);
	    }
	    
	    public List<String> getActividadesPersistencia() { // Devuelve la tabla completa de las actividades en array
	       	    
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
			EntityManager em = emf.createEntityManager();  
	        
	         //Query con JPQL. Obtenemos la informacion de todos los usuario.
	        Query query = em.createQuery("SELECT u.nombre FROM Actividad u");
	      
	        List<String> result = query.getResultList();
	     	      
	      	return result;	 
	    }
	    
	    public DataSalida verInfoSalida(String actividad,String salida) throws SalidaNoExisteException {
		      
	    	 DataSalida ds = null;
	    	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
	  		 EntityManager em = emf.createEntityManager();
	       
	  		System.out.println("Antes de consulta"); 
	          TypedQuery<Salida> query = em.createQuery("SELECT s FROM Actividad a JOIN a.salidas s "
	          		+ "WHERE a.nombre =:nombreAct "
	          		+ "and s.nombre=:nombreSal",Salida.class);
	          query.setParameter("nombreAct", actividad);
	          query.setParameter("nombreSal", salida);
	          List<Salida> result = query.getResultList();
	          
	  		  // Cargo el combo de salida con el resultado de jpql
	  		  for (Salida dato : result) {
	  			System.out.println("Nombre de salida");  
	  			System.out.println(dato.getNombre());
	            ds=dato.getDataSalida();                 // Cargo el datasalida
	          }
	  		 em.close();
	  		 emf.close();
	       
	  		return ds;

	    } 
	    public void agregarInscripcion(String actividadCombo,String salidaCombo,DataInscripcion di) {
	    	// Tengo que buscar la actividad por el nombre HAY QUE RECUPERAR LOS OBJETOS
	     	 ManejadorActividad ma = ManejadorActividad.getinstance();
	         Actividad a = ma.obtenerActividadPersistencia(actividadCombo);  // Lo voy a buscar con find
	         
	         // Tengo que buscar la salida por el nombre y a la actividad
	         
	         Salida s=a.obtenerSalida(salidaCombo); 
	          
	          // Esto registra al objeto pasandole el DataType a la lista de inscripciones       
	    		 s.agregarInscripcion(di,s); 
	     }
	    public List<String> cargarSalidasPersistencia(String seleccion) {
	    	
	    	 List<String> ds= new ArrayList<>();
	         EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega1");
	  		 EntityManager em = emf.createEntityManager();
	       
	          TypedQuery<Salida> query = em.createQuery("SELECT s FROM Actividad a JOIN a.salidas s WHERE a.nombre =:nombreAct",Salida.class);
	          query.setParameter("nombreAct", seleccion);
	          List<Salida> result = query.getResultList();
	           
	          
	  		// Cargo el combo de salida con el resultado de jpql
	  		for (Salida dato : result) {
	  			System.out.println(dato.getNombre());
	  			ds.add(dato.getNombre());         // Cargo el combo de salidas
	          }
	  		em.close();
	  		emf.close();
	  		return ds;
	    }
	     
}
