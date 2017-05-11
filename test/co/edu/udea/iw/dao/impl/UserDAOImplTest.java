package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
 * Pruebas de los métodos del DAO de los usuarios
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class UserDAOImplTest {

	@Autowired//Inyectar datos desde la base de datos
	UserDAO userDAO;
	Logger logger = Logger.getLogger(MyException.class);//Para manejar los errores
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.UserDAOImpl#getUsers()}.
	 */
	@Test
	public void testGetUsers() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
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
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.UserDAOImpl#registerUser(User)}.
	 */
	@Test
	public void testRegisterUser() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		User user = null;		
		try {
			user = new User("camilo1","CC","123456778","Camilo","Durango","camilo@gmail.com","123","I",null);
			userDAO.registerUser(user);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.UserDAOImpl#getUser(String)}.
	 */
	@Test
	public void testGetUser() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
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
