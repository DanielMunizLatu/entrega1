package logica;

import dataType.DataUsuario;
import jakarta.persistence.*;

/**Representa a un usuario en el sistema con nombre, apellido y cedula de identidad. */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class  Usuario {

	
    private String nombre;
    private String apellido;
    @Id
    private String cedulaIdentidad;
    @Lob
    @Column(name = "FOTO")
    private byte[] foto;

    
    public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Usuario() {
		super();
	}

	public Usuario(String n, String ap, String ci,byte[] foto) {
        this.nombre = n;
        this.apellido = ap;
        this.cedulaIdentidad = ci;
        this.foto=foto;
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

    public void setNombre(String n) {
        nombre = n;
    }

    public void setApellido(String ap) {
        apellido = ap;
    }

    public void setCedulaIdentidad(String ci) {
        cedulaIdentidad = ci;
    }

    public abstract DataUsuario getDataUsuario();
}
