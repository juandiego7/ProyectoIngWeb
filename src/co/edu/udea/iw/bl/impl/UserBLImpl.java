package co.edu.udea.iw.bl.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.dao.UserDAO;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementacion de la logica de negocio para los usuarios
 * @author Raul Antonio Martinez - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@Transactional
public class UserBLImpl implements UserBL {

	private UserDAO userDAO;

	@Override
	public List<User> getUsers() throws MyException {
		return userDAO.getUsers();
	}
	
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
		if (typeId == null | "".equals(typeId)) {
			throw new MyException("El tipo de identificacion no puede estar vacio");
		}
		if (numberId == null || "".equals(numberId)) {
			throw new MyException("El numero de identificacion no puede estar vacio");
		}
		if (name == null | "".equals(name)) {
			throw new MyException("El nombre no puede estar vacio");
		}
		if (password == null || "".equals(password)) {
			throw new MyException("La contraseña no puede ser vacia");
		}
		if (role == null | "".equals(role)) {
			throw new MyException("El role no puede estar vacio");
		}
		User user = new User(username, typeId, numberId, name, lastName, email, password, role, manager);
		userDAO.registerUser(user);
	}

	@Override
	public User getUser(String username) throws MyException {
		if (username == null || "".equals(username)) {
			throw new MyException("El nombre de usario no puede estar vacio");
		}
		return userDAO.getUser(username);
	}
	@Override
	public String login(String username, String password) throws MyException {
		User user = getUser(username);
		if (user != null) {
			if(user.getPassword().equals(password)){
				return "Usuario validado";
			}
		}
		throw new MyException("Usuario o contraseña incorrecto");
	}	
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}	

}
