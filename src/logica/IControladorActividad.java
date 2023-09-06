package logica;

import java.util.List;

import dataType.DataActividad;
import dataType.DataInscripcion;
import dataType.DataSalida;
import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.SalidaNoExisteException;


public interface IControladorActividad {
	
	 public abstract void registrarActividad(DataActividad actividad) throws ActividadRepetidaException;
	 
	 public DataActividad verInfoActividad(String nombre) throws ActividadNoExisteException;
	 
	 public DataActividad[] getActividad() throws ActividadNoExisteException;
	 
	 public void modificarActividad(DataActividad actividad);
	 
	 public List<String> getActividadesPersistencia();
	 
	 public DataSalida verInfoSalida(String actividad,String salida) throws SalidaNoExisteException;
	 
	 public void agregarInscripcion(String actividadCombo,String salidaCombo,DataInscripcion di);
	 
	  public List<String> cargarSalidasPersistencia(String seleccion);

}
