package co.edu.udea.iw.bl.impl;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.dao.UserDAO;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
@Transactional
public class UserBLImpl implements UserBL {

	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
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

	

}
