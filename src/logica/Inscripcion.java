package logica;

import java.util.Date;

import dataType.DataInscripcion;

public class Inscripcion {
	
	private Date fechaInscripcion;
	private Integer CantidadTuristas;
	private Integer Costo;
	private String nickTurista;
	
	public Inscripcion(Date fechaInscripcion, Integer cantidadTuristas, Integer costo,String Turista) {
		super();
		this.fechaInscripcion = fechaInscripcion;
		CantidadTuristas = cantidadTuristas;
		Costo = costo;
		nickTurista=Turista;
	}

	public String getnickTurista() {
		return nickTurista;
	}

	public void setTuri(String Turista) {
		this.nickTurista = Turista;
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
	
	public DataInscripcion getInscripcion() {
		return new DataInscripcion(this.getFechaInscripcion(),this.getCantidadTuristas(),this.getCosto(),this.getnickTurista());
	}
}
