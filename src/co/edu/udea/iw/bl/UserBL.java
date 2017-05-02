/**
 * 
 */
package co.edu.udea.iw.bl;

import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public interface UserBL {
	public void registerUser(String username,
							 String typeId,
							 String numberId,
							 String name,
							 String lastName,
							 String email,
							 String password,
							 String role,
							 String manager) throws MyException; 
}
