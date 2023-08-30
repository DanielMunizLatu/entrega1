package logica;
import java.time.LocalDate;
//
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataType.DataInscripcion;
import dataType.DataSalida;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Salida {

	@Id
	private String nombre;
	private LocalDate fecha;
	private Integer hora;
	private Integer turistaMax;
	private String lugar;
	private LocalDate fechaAlta;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Inscripcion> inscripciones = new ArrayList<>();
	
	public Salida() {
		super();
	}
	
	public Salida(String nombre, LocalDate fecha, Integer hora, Integer turistaMax, String lugar, LocalDate fechaAlta) {
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
	// Devolver un DataSalida para la capa de presentacion
	public DataSalida getDataSalida() {
		return new DataSalida(this.getNombre(),this.getFecha(),this.getHora(),this.getTuristaMax(), this.getLugar(),this.getFechaAlta());
	}
	
	// AHORA LOS METODOS PARA MANEJAR LA LISTA DE INSCRIPCIONES
	
	public void agregarInscripcion(DataInscripcion insc) {
		Inscripcion i = new Inscripcion(insc.getId(),insc.getFechaInscripcion(),insc.getCantidadTuristas(),insc.getCosto(),insc.getTurista());
		inscripciones.add(i);
	}
	
	public ArrayList<DataInscripcion> obtenerInscripciones(){
		ArrayList<DataInscripcion> lista = new ArrayList<>();
		for(Inscripcion i:inscripciones) {
			lista.add(i.getInscripcion());
		}
		return lista;
	}
  
}
