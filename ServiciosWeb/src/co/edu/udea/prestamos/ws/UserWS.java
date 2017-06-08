package co.edu.udea.prestamos.ws;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;
import co.edu.udea.prestamos.dto.Response;

/**
 * Implementacion de los servicios web de la logica de negocio para los usuarios
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 1
 */

@Path("user")//Definicion de la ruta con que va a responder el servicio
@Component//Para reconocer el proyecto de Spring
public class UserWS {
	
	@Autowired
	UserBL userBL;
	
	/**
	 * Servicio para registrar un usuario
	 * @see RF01
	 * @param username
	 * @param typeId
	 * @param numberId
	 * @param name
	 * @param lastName
	 * @param email
	 * @param password
	 * @param role
	 * @param manager
	 * @return Response - Confirmación de creación
	 * @throws RemoteException
	 */
	@POST//Metodo http con que responde este metodo
	@Path("register")//Definicion de la ruta para invocar este metodo
	@Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
	public Response registerUser(
			@QueryParam("username")String username,
			@QueryParam("typeId")String typeId,
			@QueryParam("numberId")String numberId,
			@QueryParam("name")String name,
			@QueryParam("lastName")String lastName,
			@QueryParam("email")String email,
			@QueryParam("password")String password,
			@QueryParam("role")String role,
			@QueryParam("manager")String manager) throws RemoteException{

		String message = null;
		String type = null;
		User managerU = null;
		System.out.println("Rol: " + role + " Man: "+ manager);
		try {
			if (manager != null && manager != "") {
				managerU = userBL.getUser(manager);
				//managerU.setUsername(manager);
				System.out.println("Man: "+ managerU.getUsername());
			}
			userBL.registerUser(username, typeId, numberId, name, lastName, email, password, role, managerU);
			type = "ok";
			message = "Usuario registrado";
		} catch (MyException e) {
			type = "error";
			message = e.getMessage();
		}
		return new Response(type,message);
	}
	
	
	/**
	 * Permite validar a un usuario en el sistema (iniciar sesión)
	 * @see RF03
	 * @param username
	 * @param password
	 * @return Response - Confirmación del login
	 */
	@GET//metodo al que responde
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public co.edu.udea.prestamos.dto.User login(@QueryParam("username")String username,
							 @QueryParam("password")String password){
		String message = null;
		String type = null;
		co.edu.udea.prestamos.dto.User u = new co.edu.udea.prestamos.dto.User();
		User user = null;
		try {
			message =  userBL.login(username, password);
			type = "ok";
			user = userBL.getUser(username);
			u.setEmail(user.getEmail());
			u.setName(user.getName());
			u.setUsername(user.getUsername());
			u.setRole(user.getRole());
			u.setTypeId(user.getTypeId());
			u.setNumberId(user.getNumberId());
		} catch (MyException e) {
			message = e.getMessage();
			type = "error";	
		}
		return u;
	}
}
