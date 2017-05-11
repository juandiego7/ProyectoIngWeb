/**
 * 
 */
package co.edu.udea.iw.bl.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Pruebas de los métodos de la lógica de negocio de los usuarios
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class UserBLImplTest {

	@Autowired
	UserBL userBL;
	Logger logger = Logger.getLogger(MyException.class);//Para manejar los errores
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.UserBLImpl#getUsers()}.
	 */
	@Test
	public void testGetUsers() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<User> lista = null;//Lista donde se almacenan las ciudades
		
		try {
			lista = userBL.getUsers();
			for(User user: lista){
				System.out.println("Name: "+user.getName() + "\n" +
									"Apellidos: "+user.getLastName() + "\n" +
									"Email: "+user.getEmail());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.UserBLImpl#registerUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, co.edu.udea.iw.dto.User)}.
	 */
	@Test
	public void testRegisterUser() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		try {
			userBL.registerUser("camilo1","CC","123456778","Camilo","Durango","camilo@gmail.com","123","I",null);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.UserBLImpl#getUser(String)}.
	 */
	@Test
	public void testGetUser() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		User user = null;		
		try{
			user = userBL.getUser("admin"); 
			System.out.println("Usuario: "+user.getName()+" Nombre del Rol: "+user.getRole());
			assertTrue(user != null);		
		}catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.UserBLImpl#login(String, String))}.
	 */
	@Test
	public void testLogin() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		String result = "";
		String username = "";
		String password = "";
		try{
			username = "admin";
			password = "root";
			result = userBL.login(username, password); 
			System.out.println("Resultado Login: "+result);
			assertTrue(result != null && "Usuario validado".equals(result));		
		}catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

}
