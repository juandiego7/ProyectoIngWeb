/**
 * 
 */
package co.edu.udea.iw.bl.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class UserBLImplTest {

	@Autowired
	UserBL userBL;
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.UserBLImpl#registerUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, co.edu.udea.iw.dto.User)}.
	 */
	@Test
	public void testRegisterUser() {
		try {
			userBL.registerUser("camilo1","CC","123456778","Camilo","Durango","camilo@gmail.com","123","I",null);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

}
