package logica;

import java.util.Date;

public class Salida {

	private String nombre;
	private Date fecha;
	private Integer hora;
	private Integer turistaMax;
	private String lugar;
	private Date fechaAlta;
	
	public Salida(String nombre, Date fecha, Integer hora, Integer turistaMax, String lugar, Date fechaAlta) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.turistaMax = turistaMax;
		this.lugar = lugar;
		this.fechaAlta = fechaAlta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getTuristaMax() {
		return turistaMax;
	}

	public void setTuristaMax(Integer turistaMax) {
		this.turistaMax = turistaMax;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
	
}
