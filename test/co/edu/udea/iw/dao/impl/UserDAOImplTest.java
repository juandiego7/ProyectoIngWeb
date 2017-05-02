package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.LoanDAO;
import co.edu.udea.iw.dao.UserDAO;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class UserDAOImplTest {

	@Autowired//Inyectar datos desde la base de datos
	UserDAO userDAO;
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.UserDAOImpl#getUsers()}.
	 */
	@Test
	public void testGetUsers() {
		List<User> lista = null;//Lista donde se almacenan las ciudades
		
		try {
			lista = userDAO.getUsers();
			for(User user: lista){
				System.out.println("Name: "+user.getName() + "\n" +
									"Apellidos: "+user.getLastName() + "\n" +
									"Email: "+user.getEmail());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegisterUser() {
		User user = null;		
		try {
			user = new User("camilo1","CC","123456778","Camilo","Durango","camilo@gmail.com","123","I",null);
			userDAO.registerUser(user);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUser() {
		User user = null;		
		try{
			user = userDAO.getUser("admin"); 
			System.out.println("Usuario: "+user.getName()+" Nombre del Rol: "+user.getRole());
			assertTrue(user != null);		
		}catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	
}