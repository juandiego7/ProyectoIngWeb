package co.edu.udea.prestamos.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;
import co.edu.udea.prestamos.dto.Answer;

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
	 * @param username
	 * @param typeId
	 * @param numberId
	 * @param name
	 * @param lastName
	 * @param email
	 * @param password
	 * @param role
	 * @param manager
	 * @return Answer
	 * @throws RemoteException
	 */
	@POST//Metodo http con que responde este metodo
	@Path("register")//Definicion de la ruta para invocar este metodo
	@Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
	public Answer registerUser(
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
		try {
			if (manager != null) {
				managerU = new User();
				managerU.setUsername(username);	
			}
			userBL.registerUser(username, typeId, numberId, name, lastName, email, password, role, managerU);
			type = "ok";
			message = "Usuario registrado";
		} catch (MyException e) {
			type = "error";
			message = e.getMessage();
		}
		return new Answer(type,message);
	}
	
	
	
	@GET//metodo al que responde
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Answer autenticar(@QueryParam("username")String username,
							 @QueryParam("password")String password){
		String message = null;
		String type = null;
		try {
			message =  userBL.login(username, password);
			type = "ok";
		} catch (MyException e) {
			message = e.getMessage();
			type = "error";	
		}
		return new Answer(type,message);
	}
}
