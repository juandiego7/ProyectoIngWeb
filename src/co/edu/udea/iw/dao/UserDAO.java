package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public interface UserDAO {
	public List<User> getUsers() throws MyException;
	
	public void registerUser(User user) throws MyException;
	
	public User getUser(String username) throws MyException;
}
