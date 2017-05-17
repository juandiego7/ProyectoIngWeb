package co.edu.udea.prestamos.ws;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.DeviceBL;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;
import co.edu.udea.prestamos.dto.Answer;

/**
 * Implementacion de los servicios web de la logica de negocio para los dispositivos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 1
 */

@Path("device")//Definicion de la ruta con que va a responder el servicio
@Component//Para reconocer el proyecto de Spring
public class DeviceWS {
	
	@Autowired
	DeviceBL deviceBL;
	
	@POST//Metodo http con que responde este metodo
	@Path("all")//Definicion de la ruta para invocar este metodo
	@Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
	public List<Device> getAll() throws RemoteException{

		try {
			return deviceBL.getDevices();
		} catch (MyException e) {
			throw new RemoteException("Problema consultando");
		}
	}

}
