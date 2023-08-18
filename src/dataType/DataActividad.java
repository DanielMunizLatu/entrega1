package dataType;

import java.util.Date;

import logica.Proveedor;

public class DataActividad {
	
	private String nombre;
	private Integer costo;
	private Date fechaHasta;
	private String prove;
	
	public DataActividad(String nombre, Integer costo, Date fechaHasta, String prove) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.fechaHasta = fechaHasta;
		this.prove = prove;
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

	public String getProve() {
		return prove;
	}

	public void setProve(String prove) {
		this.prove = prove;
	}


	
}
