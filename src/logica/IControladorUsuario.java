package logica;

import excepciones.DataUsuario;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

/**
 * @author TProg2017
 *
 */
public interface IControladorUsuario {
    
    /**
     * Registra al usuario en el sistema.
     * @param n Nombre del usuario.
     * @param ap Apellido del usuario.
     * @param ci Cédula del usuario.
     * @throws UsuarioRepetidoException Si la cédula del usuario se encuentra registrada en el sistema.
     */
    public abstract void registrarUsuario(DataUsuario usuario) throws UsuarioRepetidoException;

    /**
     * Retorna la informacon de un usuario con la cedula indicada.
     * @param ci Cédula del usuario.
     * @return Información del usuario.
     * @throws UsuarioNoExisteException Si la cédula del usuario no está registrada en el sistema.
     */
    public abstract DataUsuario verInfoUsuario(String ci) throws UsuarioNoExisteException;

   
    
    /**
     * Retorna la informacion de todos los usuarios registrados en el sistema.
     * @return Informacion de los usuarios del sistema.
     * @throws UsuarioNoExisteException Si no existen usuarios registrados en el sistema.
     */
    public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
    
    // Modificar usuario
    public void modificarUsuario(DataUsuario usuario);
}
