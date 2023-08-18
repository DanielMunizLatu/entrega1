package logica;

import dataType.DataActividad;
import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;


public class ControladorActividad implements IControladorActividad{
	
	 public ControladorActividad() {
	    }
	 
	 public void registrarActividad(DataActividad actividad, String prove) throws ActividadRepetidaException {
	        ManejadorActividad ma = ManejadorActividad.getinstance();
	        Actividad a = ma.obtenerActividad(actividad.getNombre());  // Lo voy a buscar a la coleccion
	        
	        if (a != null)  // Si lo encontre es porque ya existe
	            throw new ActividadRepetidaException("La actividad " + actividad.getNombre() + " ya esta registrada");

	     
	        // Agrego la actividad a la coleccion
	        ma.addActividad(a);
	       
	    }
	// Devuelvo una DataActividad para ser mostrado en la capa de presentacion
	    
	    public DataActividad verInfoActividad(String nombre) throws ActividadNoExisteException {
	      
	    	ManejadorActividad ma = ManejadorActividad.getinstance();  // ma tiene la coleccion
	        Actividad a = ma.obtenerActividad(nombre);                 // a obtiene el usuario pasado por parametro 
	       
	        if (a != null) { // Si lo encontre es porque ya existe, solo traigo sus datos
	        	
	        	DataActividad da =a.getDataActividad();  // Cargo en un dataActividad, que esta sobrescrito
	        	return da;                           // segun el tipo de usuario que sea
	        		
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
}
