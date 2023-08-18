package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataType.DataSalida;

public class Actividad {
	
	private String nombre;
	private Integer costo;
	private Date fechaHasta;
	private Proveedor prove;
	private List<Salida> salidas = new ArrayList<>();
	
	public Proveedor getProve() {
		return prove;
	}

	public void setProve(Proveedor prove) {
		this.prove = prove;
	}

	public Actividad(String nombre, Integer costo, Date fechaHasta,Proveedor prov ) {
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
