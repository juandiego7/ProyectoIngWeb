/**
 * 
 */
package co.edu.udea.iw.bl;

import java.util.List;

import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Raul Antonio Martinez - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
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
	
	public String login(String username, String password) throws MyException;
}
