package dataType;

public class DataTurista extends DataUsuario{
	
	private String nacionalidad;

	public DataTurista(String n, String ap, String ci,byte[] foto,String nacionalidad) {
		super(n,ap,ci,foto);
		this.nacionalidad = nacionalidad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	  /* Sirve para mostrar textualmente la informacion del usuario, por ejemplo en un ComboBox     */
    
	public String toString() {
        return getCedulaIdentidad() + " (" + getNombre() + " " + getApellido() + " "+getNacionalidad()+")";
    }
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	

}
