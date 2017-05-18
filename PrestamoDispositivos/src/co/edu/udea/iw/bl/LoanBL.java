package co.edu.udea.iw.bl;

import java.util.Date;
import java.util.List;

import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.exception.MyException;

/**
 * Interfaz para definir los métodos de la lógica
 * de negocio para los préstamos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.1
 */
public interface LoanBL {
	
	/**
	 * Obtiene la lista de todos los préstamos que existan en la base de datos
	 * @return Lista de Préstamos
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public  List<Loan> getLoans() throws MyException;
	
	/**
	 * Inserta un préstamo en la base de datos con la información ingresada como parámetro
	 * @param username
	 * @param startDate
	 * @param endDate
	 * @param returnDate
	 * @param status
	 * @param code
	 * @param copy
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public void registerLoan(String username,
							 Date startDate,
							 Date endDate,
							 Date returnDate,
							 String status,
							 String code,
							 String copy ) throws MyException;
	
	/**
	 * Obtiene los préstamos para el día(date) ingresado en los que el código, el nombre sea igual a los datos enviados como parámetros
	 * @param code
	 * @param copy
	 * @param date
	 * @return Lista de Préstamos
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public List<Loan> getLoans(String code, String copy, Date date) throws MyException;
	
	/**
	 * Obtiene el préstamo para el usuario indicado en la fecha expresada(startDate) 
	 * @param username
	 * @param code
	 * @param copy
	 * @param startDate
	 * @return Un Préstamo
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public Loan getLoan(String username, String code, String copy, Date startDate) throws MyException; 
	
	/**
	 * Actualiza la información del préstamo que corresponde a los datos ingresados como parámetro
	 * @param username
	 * @param startDate
	 * @param endDate
	 * @param returnDate
	 * @param status
	 * @param code
	 * @param copy
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public void updateLoan(String username,
						   Date startDate,
						   Date returnDate,
						   String status,
						   String code,
						   String copy) throws MyException;
	
	/**
	 * Obtiene la lista de préstamos para un usuario y estado en específico(por ejemplo los préstamos ACTIVOS) 
	 * @param username
	 * @param status
	 * @return Lista de Préstamos
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public List<Loan> getLoansUser(String username, String status) throws MyException;
	
	/**
	 * Busca los préstamos de acuerdo al tipo y número de identificación ingresados como parámetros
	 * @param typeId
	 * @param numberId
	 * @return Lista de Préstamos
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public List<Loan> getLoansDevice(String typeId, String numberId) throws MyException;
	
	/**
	 * Elimina el préstamo que corresponde a los datos ingresados como parámetros
	 * @param username
	 * @param code
	 * @param copy
	 * @param startDate
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public void deleteLoan(String username, String code, String copy, Date startDate) throws MyException;
}
