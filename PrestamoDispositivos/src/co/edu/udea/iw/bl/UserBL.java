/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.List;

import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Interfaz para definir los métodos de la lógica
 * de negocio para los usuarios
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.1
 */
public interface UserBL {
	
	/**
	 * Obtiene la lista de todos los usuarios que existan en la base de datos
	 * @return Lista de Usuarios
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public List<User> getUsers() throws MyException;
	
	/**
	 * Inserta un usuario en la base de datos con la información ingresada como parámetro
	 * @param username
	 * @param typeId
	 * @param numberId
	 * @param name
	 * @param lastName
	 * @param email
	 * @param password
	 * @param role
	 * @param manager
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public void registerUser(String username,
							 String typeId,
							 String numberId,
							 String name,
							 String lastName,
							 String email,
							 String password,
							 String role,
							 User manager) throws MyException; 
	
	/**
	 * Obtiene el usuario correspondiente al username ingresado como parámetro
	 * @param username
	 * @return Un Usuario
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public User getUser(String username) throws MyException;
	
	/**
	 * Valida un usuario por medio de su username y su contrasena(iniciar sesion)
	 * @param username
	 * @param password
	 * @return String("Usuario validado" o "Usuario o contraseña incorrecto")
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public String login(String username, String password) throws MyException;
}
