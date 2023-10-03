package logica;

import dataType.DataTurista;
import dataType.DataUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("turista")
public class Turista extends Usuario{
	
	private String nacionalidad;
	
	public Turista() {
		super();
	}

	public Turista(String n, String ap, String ci,byte[] foto, String nacio) {
		super(n, ap, ci,foto);
		this.nacionalidad=nacio;
		
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public DataUsuario getDataUsuario() {
		return new DataTurista(this.getNombre(),this.getApellido(),this.getCedulaIdentidad(),this.getFoto(),this.getNacionalidad());
	}
}
