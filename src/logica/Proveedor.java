package logica;

public class Proveedor  extends Usuario{
	
	private String descripcion;
	
	public Proveedor(String n, String ap, String ci,String desc) {
		super(n, ap, ci);
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
		return new DataProveedor(this.getNombre(),this.getApellido(),this.getCedulaIdentidad(),this.getDescripcion());
	}

}
