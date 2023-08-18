package logica;


import excepciones.DataProveedor;
import excepciones.DataTurista;
import excepciones.DataUsuario;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

/**
 * Controlador de usuarios.
 *
 */
public class ControladorUsuario implements IControladorUsuario {

    public ControladorUsuario() {
    }
                                  // con throws digo el metodo puede larga una exception verificada
    
    public void registrarUsuario(DataUsuario usuario) throws UsuarioRepetidoException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario u = mu.obtenerUsuario(usuario.getCedulaIdentidad());  // Lo voy a buscar a la coleccion
        
        if (u != null)  // Si lo encontre es porque ya existe
            throw new UsuarioRepetidoException("El usuario " + usuario.getCedulaIdentidad() + " ya esta registrado");

        // Tengo que saber si es Turista o proveedor para luego agregarlo
        Usuario nuevoUsuario=null;
        if (usuario instanceof DataTurista)
        	nuevoUsuario = new Turista (usuario.getNombre(),usuario.getApellido(),usuario.getCedulaIdentidad(),((DataTurista) usuario).getNacionalidad());
        if (usuario instanceof DataProveedor)
        	nuevoUsuario = new Proveedor (usuario.getNombre(),usuario.getApellido(),usuario.getCedulaIdentidad(),((DataProveedor) usuario).getDescripcion());
        		
        // Agrego el usuario a la coleccion
        mu.addUsuario(nuevoUsuario);
        if (nuevoUsuario instanceof Turista)
        	System.out.println("Es turista nomas");
    }

    // Devuelvo una DataUsuario para ser mostrado en la capa de presentacion
    
    public DataUsuario verInfoUsuario(String ci) throws UsuarioNoExisteException {
      
    	ManejadorUsuario mu = ManejadorUsuario.getinstance();  // mu tiene la coleccion
        Usuario u = mu.obtenerUsuario(ci);                     // u obtiene el usuario pasado por parametro 
       
        if (u != null) { // Si lo encontre es porque ya existe, solo traigo sus datos
        	
        	DataUsuario du =u.getDataUsuario();  // Cargo en un dataUsuario, que esta sobrescrito
        	return du;                           // segun el tipo de usuario que sea
        		
          // return new DataUsuario(u.getNombre(), u.getApellido(), u.getCedulaIdentidad(),((Turista) u).getNacionalidad());
        }    
        else
            throw new UsuarioNoExisteException("El usuario " + ci + " no existe");

    } 
    // Get de usuarios, pero no de los objetos sino de los DataUsuarios
    // Devuelve la coleccion de usuario en un array de DataUsuarios
    
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario[] usrs = mu.getUsuarios();  // Usa el getUsuarios que devuelve array de objetos

        if (usrs != null) {
            DataUsuario[] du = new DataUsuario[usrs.length];
            Usuario usuario;

            // Para separar logica de presentacion, no se deben devolver los Usuario, sino los DataUsuario
            for (int i = 0; i < usrs.length; i++) {
                usuario = usrs[i];
                du[i]=usuario.getDataUsuario();
                //du[i] = new DataUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getCedulaIdentidad());
                // pasa el array de objetos a array de DataUsuaRios
            }

            return du;
        } else
            throw new UsuarioNoExisteException("No existen usuarios registrados");

    }
    public void modificarUsuario(DataUsuario usuario)  {
        
    	ManejadorUsuario mu = ManejadorUsuario.getinstance();
      //  Usuario u = mu.obtenerUsuario(usuario.getCedulaIdentidad());  // Lo voy a buscar a la coleccion
    
        Usuario nuevoUsuario=null;
        if (usuario instanceof DataTurista) {
        	nuevoUsuario = new Turista (usuario.getNombre(),usuario.getApellido(),usuario.getCedulaIdentidad(),((DataTurista) usuario).getNacionalidad());
        	System.out.println("A modificar entro con un data type de turista");
        }
        	if (usuario instanceof DataProveedor)
        	nuevoUsuario = new Proveedor (usuario.getNombre(),usuario.getApellido(),usuario.getCedulaIdentidad(),((DataProveedor) usuario).getDescripcion());
        		
        System.out.println(nuevoUsuario.getNombre());
		System.out.println(nuevoUsuario.getApellido());
		
		if (usuario instanceof DataTurista)
		   //System.out.println(((Turista) nuevoUsuario).getNacionalidad());
			System.out.println("Soy Turista");
		if (usuario instanceof DataProveedor)
			   //System.out.println(((Turista) nuevoUsuario).getNacionalidad());
				System.out.println("Soy proveedor");
		
        // Agrego el usuario a la coleccion
        mu.addUsuario(nuevoUsuario);
    }
   
}
