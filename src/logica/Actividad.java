package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataType.DataActividad;
import dataType.DataSalida;
import dataType.DataTurista;
import dataType.DataUsuario;

public class Actividad {
	
	private String nombre;
	private Integer costo;
	private Date fechaHasta;
	private String prove;
	private List<Salida> salidas = new ArrayList<>();
	
	public String getProve() {
		return prove;
	}

	public void setProve(String prove) {
		this.prove = prove;
	}

	public Actividad(String nombre, Integer costo, Date fechaHasta,String prov ) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.fechaHasta = fechaHasta;
		this.prove=prov;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public DataActividad getDataActividad() {
		return new DataActividad(this.getNombre(),this.getCosto(),this.getFechaHasta(),this.getProve());
	}
	
	// Gestion de la coleccion de salidas
	public void agregarSalida(DataSalida sali) {
		Salida i = new Salida(sali.getNombre(),sali.getFecha(),sali.getHora(),sali.getTuristaMax(),sali.getLugar(),sali.getFechaAlta());
		salidas.add(i);
	}
	
	public void borrarSalida(String nombre){
		int i = 0;
		Salida sali;
		boolean encontre=false;
		while ((i < salidas.size()) && (!encontre)) {
		    sali=salidas.get(i);                      // sali tiene el objeto de la poscion i
		    if (sali.getNombre().equals(nombre)) {
		    	salidas.remove(i);
		    	encontre=true;
		    }	
		    i++;
		}
	}	
	// Devuelve un arreglo con los datos de todas las salidas
	public ArrayList<DataSalida> obtenerSalidas(){
		ArrayList<DataSalida> lista = new ArrayList<>();
		for(Salida i:salidas) {
			lista.add(i.getDataSalida());
		}
		return lista;
	}

}
