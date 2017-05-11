package co.edu.udea.iw.dao;

import java.util.Date;
import java.util.List;

import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.LoanId;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Interfaz para declarar los métodos de los préstamos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * version 2.0
 */
public interface LoanDAO {
	
	/**
	 * Metodo para obtener todos los prestamos 
	 * @return List<Loan> 
	 * @throws MyException
	 */
	public  List<Loan> getLoans() throws MyException;
	
	/**
	 * Metodo para registrar un prestamo
	 * @param loan
	 * @throws MyException
	 */
	public void registerLoan(Loan loan) throws MyException;
	
	/**
	 * Metodo para obtener los prestamos de un dispositivo en cierta fecha 
	 * @param deviceId
	 * @param date
	 * @return List<Loan>
	 * @throws MyException
	 */
	public List<Loan> getLoans(DeviceId deviceId, Date date) throws MyException;
	
	/**
	 * Metodo para obtener un prestamo por medio de su clave primaria
	 * @param loanId
	 * @return Loan
	 * @throws MyException
	 */
	public Loan getLoan(LoanId loanId) throws MyException; 
	
	/**
	 * Metodo para actualizar un prestamo
	 * @param loan
	 * @throws MyException
	 */
	public void updateLoan(Loan loan) throws MyException;
	
	/**
	 * Metodo para obtener los prestamos de un usuario con cierto estado
	 * @param user
	 * @param status
	 * @return List<Loan>
	 * @throws MyException
	 */
	public List<Loan> getLoans(User user, String status) throws MyException;
	
	/**
	 * Metodo para obtener los prestamos asociados a
	 * un tipo y numero de identificaion de un investigador
	 * @param typeId
	 * @param numberId
	 * @return List<Loan>
	 * @throws MyException
	 */
	public List<Loan> getLoans(String typeId, String numberId) throws MyException;
	
	/**
	 * Metodo para borrar un prestamo
	 * @param loan
	 * @throws MyException
	 */
	public void deleteLoan(Loan loan) throws MyException;
}
