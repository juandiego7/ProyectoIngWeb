package co.edu.udea.prestamos.ws;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.LoanBL;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.exception.MyException;
import co.edu.udea.prestamos.dto.Answer;

/**
 * Implementacion de los servicios web de la logica de negocio para los dispositivos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 1
 */

@Path("loan")//Definicion de la ruta con que va a responder el servicio
@Component//Para reconocer el proyecto de Spring
public class LoanWS {
	
	@Autowired
	LoanBL loanBL;
	
	/**
	 * Servicio para retornar todos los préstamos
	 * @return Lista de Préstamos
	 * @throws RemoteException
	 */
	@GET//Metodo http con que responde este metodo
	@Path("all")//Definicion de la ruta para invocar este metodo
	@Produces(MediaType.APPLICATION_JSON)//Formato de respuesta
	public List<Loan> getAll() throws RemoteException{
		try {
			return loanBL.getLoans();
		} catch (MyException e) {
			throw new RemoteException("Problema consultando");
		}
	}
	
	/**
	 * Servicio para registrar un prestamo
	 * @param username
	 * @param startDate
	 * @param endDate
	 * @param returnDate
	 * @param status
	 * @param code
	 * @param copy
	 * @return Answer
	 * @throws RemoteException
	 */
	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	public Answer registerLoan(
			@QueryParam("username")String username,
			@QueryParam("startDate")String startDate,
			@QueryParam("endDate")String endDate,
			@QueryParam("returnDate")String returnDate,
			@QueryParam("status")String status,
			@QueryParam("code")String code,
			@QueryParam("copy")String copy) throws RemoteException{
		
		String message = null;
		String type = null;
		Date startD = null;
		Date endD = null;
		Date returnD = null; 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
		try {
			startD = simpleDateFormat.parse(startDate);
			endD = simpleDateFormat.parse(endDate);
			returnD = simpleDateFormat.parse(returnDate);
			loanBL.registerLoan(username, startD, endD, returnD, status, code, copy);
			type = "ok";
			message = "Prestamo registrado";
		}catch (MyException e) {
			type = "error";
			message = e.getMessage();
			//message = message + " starDate " + startD + " endDate " + endD + " returnDate " + returnD;
		}catch (ParseException e) {
			type = "error";
			message = "Error, verifique el formato de la fecha ingresada";
		}		
		return new Answer(type, message);
	}
	
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Loan> getLoans(
			@QueryParam("code")String code, 
			@QueryParam("copy")String copy, 
			@QueryParam("date")String date) throws RemoteException{
		Date d = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			d = simpleDateFormat.parse(date);
			return loanBL.getLoans(code, copy, d);
		} catch (MyException e) {
			throw new RemoteException("Problema consultando");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	} 
	
	@PUT
	@Path("lend")
	@Produces(MediaType.APPLICATION_JSON)
	public Answer updateStatusLoan(
			@QueryParam("username")String username, 
			@QueryParam("startDate")String startDate,  
			@QueryParam("code")String code, 
			@QueryParam("copy")String copy){
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
		String message = null;
		String type = null;
		try {
			if (startDate != null && !"".equals(startDate)){
				date = simpleDateFormat.parse(startDate);
			}
			loanBL.updateLoan(username, date, null, "PRESTADO", code, copy);
			type = "ok";
			message = "Prestamo actualizado";
		} catch (MyException e) {
			type = "error";
			message = e.getMessage();
		} catch (ParseException e) {
			type = "error";
			message = "Error, verifique el formato de la fecha ingresada";
		}
		return new Answer(type, message);
	}
	
	@PUT
	@Path("return")
	@Produces(MediaType.APPLICATION_JSON)
	public Answer updateReturnLoan(
			@QueryParam("username")String username, 
			@QueryParam("startDate")String startDate,  
			@QueryParam("code")String code, 
			@QueryParam("copy")String copy){
		String message = null;
		String type = null;
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
		try {
			if (startDate != null && !"".equals(startDate)){
				date = simpleDateFormat.parse(startDate);
			}
			//System.out.println("Date WS"+ date);
			loanBL.updateLoan(username, date, new Date(), "DEVUELTO", code, copy);
			type = "ok";
			message = "Prestamo actualizado ";
		} catch (MyException e) {
			type = "error";
			message = e.getMessage();
		} catch (ParseException e) {
			type = "error";
			message = "Error, verifique el formato de la fecha ingresada";
		}
		return new Answer(type, message);
	}
	

}
