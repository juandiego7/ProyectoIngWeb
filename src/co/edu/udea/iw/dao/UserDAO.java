package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Interfaz para definir los metodos para los usuarios
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
public interface UserDAO {
	
	/**
	 * Metodo para obtener todo los usuarios del sistema
	 * @return List<User>
	 * @throws MyException
	 */
	public List<User> getUsers() throws MyException;
	
	/**
	 * Metodo para registrar un usuario en el sistema
	 * @param user
	 * @throws MyException
	 */
	public void registerUser(User user) throws MyException;
	
	/**
	 * Metodo para obtener un usuario por medio de su nombre de usuario
	 * @param username
	 * @return User
	 * @throws MyException
	 */
	public User getUser(String username) throws MyException;
}
