package co.edu.udea.iw.bl.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.dao.UserDAO;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la lógica de negocio para los usuarios
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.1
 */
@Transactional
public class UserBLImpl implements UserBL {

	private UserDAO userDAO;

	/**
	 * Obtiene la lista de todos los usuarios que existan en la base de datos
	 * @return Lista de Usuarios
	 * @see co.edu.udea.iw.bl.UserBL#getUsers()
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public List<User> getUsers() throws MyException {
		return userDAO.getUsers();
	}
	
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
	 * @see co.edu.udea.iw.bl.UserBL#registerUser(String, String, String, String, String, String, String, String, User)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public void registerUser(String username,
							 String typeId,
							 String numberId,
							 String name,
							 String lastName,
							 String email,
							 String password,
							 String role,
							 User manager) throws MyException {
		if (username == null || "".equals(username)) {
			throw new MyException("El nombre de usario no puede estar vacio");
		}
		if (typeId == null || "".equals(typeId)) {
			throw new MyException("El tipo de identificacion no puede estar vacio");
		}
		if (numberId == null || "".equals(numberId)) {
			throw new MyException("El numero de identificacion no puede estar vacio");
		}
		if (name == null || "".equals(name)) {
			throw new MyException("El nombre no puede estar vacio");
		}
		if (password == null || "".equals(password)) {
			throw new MyException("La contrase�a no puede ser vacia");
		}
		if (role == null || "".equals(role)) {
			throw new MyException("El role no puede estar vacio");
		}
		if(manager != null){
			if (userDAO.getUser(manager.getUsername())==null){
				throw new MyException("El manager no existe");
			}
		}
		User user = new User(username, typeId, numberId, name, lastName, email, password, role, manager);
		userDAO.registerUser(user);
	}

	/**
	 * Obtiene el usuario correspondiente al username ingresado como parámetro
	 * @param username
	 * @return Un Usuario
	 * @see co.edu.udea.iw.bl.UserBL#getUser(String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public User getUser(String username) throws MyException {
		if (username == null || "".equals(username)) {
			throw new MyException("El nombre de usario no puede estar vacio");
		}
		return userDAO.getUser(username);
	}
	
	/**
	 * Valida un usuario con por medio de su username y su contraseña(iniciar sesión)
	 * @param username
	 * @param password
	 * @return String("Usuario validado" o "Usuario o contraseña incorrecto")
	 * @see co.edu.udea.iw.bl.UserBL#login(String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public String login(String username, String password) throws MyException {
		User user = getUser(username);
		if (user != null) {
			if(user.getPassword().equals(password)){
				return "Usuario validado";
			}
		}
		throw new MyException("Usuario o contrasena incorrecto");
		//return "Usuario o contrasena incorrecto";
	}	
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}	

}
