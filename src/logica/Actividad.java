package logica;

import java.util.Date;

public class Actividad {
	
	private String nombre;
	private Integer costo;
	private Date fechaHasta;
	
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

	public Actividad(String nombre, Integer costo, Date fechaHasta) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.fechaHasta = fechaHasta;
	}
		
	

}
