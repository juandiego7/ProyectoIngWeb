package co.edu.udea.prestamos.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.UserBL;
import co.edu.udea.iw.exception.MyException;

@Path("usuario")
@Component//Para reconocer el proyecto de Spring
public class UserWS {
	
	@Autowired
	UserBL userBL;
	
	@GET//metodo al que responde
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public void autenticar(@QueryParam("username")String username,
							 @QueryParam("password")String password){
		//return "sssss";
		try {
			//System.out.println("username _: " + userBL.login(username, password));
			userBL.login(username, password);
		} catch (MyException e) {
				e.getMessage();
		}
	}
}
