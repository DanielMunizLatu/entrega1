package dataType;

import java.util.Date;

import logica.Turista;

public class DataInscripcion {
	
	private long id;
	private Date fechaInscripcion;
	private Integer CantidadTuristas;
	private Integer Costo;
	private Turista Turi;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	
	public DataInscripcion(long id,Date fechaInscripcion, Integer cantidadTuristas, Integer costo, Turista turista) {
		super();
		this.id=id;
		this.fechaInscripcion = fechaInscripcion;
		this.CantidadTuristas = cantidadTuristas;
		this.Costo = costo;
		this.Turi = turista;
	}

	public Turista getTurista() {
		return Turi;
	}

	public void setTurista(Turista turista) {
		Turi = turista;
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
