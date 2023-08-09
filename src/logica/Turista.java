package logica;

public class Turista extends Usuario{
	
	private String nacionalidad;
	
	public Turista(String n, String ap, String ci,String nacio) {
		super(n, ap, ci);
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
		return new DataTurista(this.getNombre(),this.getApellido(),this.getCedulaIdentidad(),this.getNacionalidad());
	}
}
