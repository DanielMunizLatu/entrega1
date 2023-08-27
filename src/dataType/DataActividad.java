package dataType;

import java.time.LocalDate;
import java.util.Date;

import logica.Proveedor;

public class DataActividad {
	
	private String nombre;
	private Integer costo;
	private LocalDate fechaHasta;
	private String prove;
	
	public DataActividad(String nombre, Integer costo, LocalDate fechaHasta, String prove) {
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

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getProve() {
		return prove;
	}

	public void setProve(String prove) {
		this.prove = prove;
	}


	
}
