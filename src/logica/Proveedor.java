package logica;

import dataType.DataProveedor;
import dataType.DataUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("proveedor")
public class Proveedor  extends Usuario{
	
	private String descripcion;
	
	
	public Proveedor() {
		super();
	}

	public Proveedor(String n, String ap, String ci,byte[] foto,String desc) {
		super(n, ap, ci,foto);
		this.descripcion=desc;
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public DataUsuario getDataUsuario() {
		return new DataProveedor(this.getNombre(),this.getApellido(),this.getCedulaIdentidad(),this.getFoto(),this.getDescripcion());
	}

}
