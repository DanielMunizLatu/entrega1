package logica;

import java.util.Date;

public class DataInscripcion {
	
	private Date fechaInscripcion;
	private Integer CantidadTuristas;
	private Integer Costo;
	
	public DataInscripcion(Date fechaInscripcion, Integer cantidadTuristas, Integer costo) {
		super();
		this.fechaInscripcion = fechaInscripcion;
		CantidadTuristas = cantidadTuristas;
		Costo = costo;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Integer getCantidadTuristas() {
		return CantidadTuristas;
	}

	public void setCantidadTuristas(Integer cantidadTuristas) {
		CantidadTuristas = cantidadTuristas;
	}

	public Integer getCosto() {
		return Costo;
	}

	public void setCosto(Integer costo) {
		Costo = costo;
	}
	
	

}
