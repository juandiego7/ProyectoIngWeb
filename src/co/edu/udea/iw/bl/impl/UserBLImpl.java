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
							 String manager) throws MyException {
				
	}

	

}
