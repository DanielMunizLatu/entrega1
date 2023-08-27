package logica;

import dataType.DataActividad;
import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;


public interface IControladorActividad {
	
	 public abstract void registrarActividad(DataActividad actividad) throws ActividadRepetidaException;
	 
	 public DataActividad verInfoActividad(String nombre) throws ActividadNoExisteException;
	 
	 public DataActividad[] getActividad() throws ActividadNoExisteException;
	 
	 public void modificarActividad(DataActividad actividad);
	 
	

}
