package dataType;

/**
 * Datatype para transportar la informacion de un usuario entre capa logica y de presentacion.
 * En Java los datatypes se definen con setters y getters, y se denominan JavaBean.
 * @author TProg2017
 *
 */
public abstract class DataUsuario {

    private String nombre;
    private String apellido;
    private String cedulaIdentidad;
    private byte[] foto;

    public DataUsuario() {
        this.setNombre(new String());
        this.setApellido(new String());
        this.setCedulaIdentidad(new String());
        this.setFoto(foto);
        
    }

    public DataUsuario(String nombre, String apellido, String cedulaIdentidad,byte[] foto) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCedulaIdentidad(cedulaIdentidad);
        this.setFoto(foto);
        
    }

    public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    /* Sirve para mostrar textualmente la informacion del usuario, por ejemplo en un ComboBox
     
    public String toString() {
        return getCedulaIdentidad() + " (" + getNombre() + " " + getApellido() + ")";
    }*/
    
    public abstract String toString();
    
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }
    
  

}
