/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.List;

import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public interface UserBL {
	
	public List<User> getUsers() throws MyException;
	
	public void registerUser(String username,
							 String typeId,
							 String numberId,
							 String name,
							 String lastName,
							 String email,
							 String password,
							 String role,
							 User manager) throws MyException; 
	
	
	public User getUser(String username) throws MyException;
}
