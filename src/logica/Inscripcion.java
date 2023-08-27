package logica;

import java.util.Date;

import dataType.DataInscripcion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Inscripcion {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria autogenerada
	private long id;
	private Date fechaInscripcion;
	private Integer CantidadTuristas;
	private Integer Costo;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="Turista_Nick")
	private Turista nickTurista;
	
	public Inscripcion() {
		super();
	}
	
	public Inscripcion(long id,Date fechaInscripcion, Integer cantidadTuristas, Integer costo,Turista Turi) {
		super();
		this.id=id;
		this.fechaInscripcion = fechaInscripcion;
		this.CantidadTuristas = cantidadTuristas;
		this.Costo = costo;
		this.nickTurista=Turi;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Turista getNickTurista() {
		return nickTurista;
	}

	public void setNickTurista(Turista nickTurista) {
		this.nickTurista = nickTurista;
	}

	public Turista getnickTurista() {
		return nickTurista;
	}

	public void setTuri(Turista Turista) {
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
		return new DataInscripcion(this.getId(),this.getFechaInscripcion(),this.getCantidadTuristas(),this.getCosto(),this.getnickTurista());
	}
}
