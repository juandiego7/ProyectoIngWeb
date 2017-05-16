package co.edu.udea.iw.bl.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.LoanBL;
import co.edu.udea.iw.dao.DeviceDAO;
import co.edu.udea.iw.dao.LoanDAO;
import co.edu.udea.iw.dao.UserDAO;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.LoanId;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la lógica de negocio para los préstamos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@Transactional
public class LoanBLImpl implements LoanBL {

	private LoanDAO loanDAO;
	private UserDAO userDAO;
	private DeviceDAO deviceDAO;
	
	/**
	 * Obtiene la lista de todos los préstamos que existan en la base de datos
	 * @return Lista de Préstamos
	 * @see co.edu.udea.iw.bl.LoanBL#getLoans()
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public List<Loan> getLoans() throws MyException {
		return loanDAO.getLoans();
	}
	
	/**
	 * Inserta un préstamo en la base de datos con la información ingresada como parámetro
	 * @param username
	 * @param startDate
	 * @param endDate
	 * @param returnDate
	 * @param status
	 * @param code
	 * @param copy
	 * @see co.edu.udea.iw.bl.LoanBL#registerLoan(String, Date, Date, Date, String, String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public void registerLoan(String username, Date startDate, Date endDate, Date returnDate, String status, String code,
			String copy) throws MyException {
		if (username==null || "".equals(username)) {
			throw new MyException("El usuario no puede estar vacio");
		}
		if (startDate==null || "".equals(startDate)) {
			throw new MyException("La fecha de inicio no puede estar vacia");
		}
		if (endDate==null || "".equals(endDate)) {
			throw new MyException("La fecha de entrega no puede estar vacia");
		}
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		if (status == null || "".equals(status)) {
			throw new MyException("El estado no puede ser vacio");
		}
		if (getLoan(username, code, copy, startDate) != null) {
			throw new MyException("El prestamo ya se encuentra registrado");
		}
		User user = userDAO.getUser(username);
		if (user == null) {
			throw new MyException("El usuario no esta registrado");
		}
		DeviceId deviceId = new DeviceId(code, copy); 
		Device device = deviceDAO.getDevice(deviceId);
		if (device == null) {
			throw new MyException("El dispositivo no esta registrado");
		}
		LoanId loanId = new LoanId(user, device, startDate);
		Loan loan = new Loan(loanId, endDate, returnDate, status);
		
		loanDAO.registerLoan(loan);
	}

	/**
	 * Obtiene los préstamos para el día(date) ingresado en los que el código, el nombre sea igual a los datos enviados como parámetros
	 * @param code
	 * @param copy
	 * @param date
	 * @return Lista de Préstamos
	 * @see co.edu.udea.iw.bl.LoanBL#getLoans(String, String, Date)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public List<Loan> getLoans(String code, String copy, Date date) throws MyException {
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		if (date == null || "".equals(date)) {
			throw new MyException("La fecha no puede estar vacia");
		}
		DeviceId deviceId = null;
		deviceId = new DeviceId(code, copy);		
		return loanDAO.getLoans(deviceId, date);
	}
	
	/**
	 * Obtiene el préstamo para el usuario indicado en la fecha expresada(startDate) 
	 * @param username
	 * @param code
	 * @param copy
	 * @param startDate
	 * @return Un Préstamo
	 * @see co.edu.udea.iw.bl.LoanBL#getLoan(String, String, String, Date)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public Loan getLoan(String username, String code, String copy, Date startDate) throws MyException {
		if (username==null || "".equals(username)) {
			throw new MyException("El usuario no puede estar vacio");
		}
		if (startDate==null || "".equals(startDate)) {
			throw new MyException("La fecha de inicio no puede estar vacia");
		}
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		User user = new User();
		user.setUsername(username);
		DeviceId deviceId = new DeviceId(code, copy);
		Device device = new Device();
		device.setDeviceId(deviceId);
		LoanId loanId = new LoanId(user, device, startDate);
		return loanDAO.getLoan(loanId);
	}

	/**
	 * Actualiza la información del préstamo que corresponde a los datos ingresados como parámetro
	 * @param username
	 * @param startDate
	 * @param endDate
	 * @param returnDate
	 * @param status
	 * @param code
	 * @param copy
	 * @see co.edu.udea.iw.bl.LoanBL#updateLoan(String, Date, Date, Date, String, String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public void updateLoan(String username, Date startDate, Date endDate, Date returnDate, String status, String code,
			String copy) throws MyException {
		if (username==null || "".equals(username)) {
			throw new MyException("El usuario no puede estar vacio");
		}
		if (startDate==null || "".equals(startDate)) {
			throw new MyException("La fecha de inicio no puede estar vacia");
		}
		if (endDate==null || "".equals(endDate)) {
			throw new MyException("La fecha de entrega no puede estar vacia");
		}
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		if (status == null || "".equals(status)) {
			throw new MyException("El estado no puede ser vacio");
		}
		if (getLoan(username, code, copy, startDate) == null) {
			throw new MyException("El prestamo no se encuentra registrado");
		}
		User user = userDAO.getUser(username);
		if (user == null) {
			throw new MyException("El usuario no esta registrado");
		}
		DeviceId deviceId = new DeviceId(code, copy); 
		Device device = deviceDAO.getDevice(deviceId);
		if (device == null) {
			throw new MyException("El dispositivo no esta registrado");
		}
		LoanId loanId = new LoanId(user, device, startDate);
		Loan loan = loanDAO.getLoan(loanId); 
		if (loan == null) {
			throw new MyException("El préstamo no esta registrado");
		}
		loan.setReturnDate(returnDate);
		loan.setStatus(status); //Duda
		loanDAO.updateLoan(loan);
	}

	/**
	 * Obtiene la lista de préstamos para un usuario y estado en específico(por ejemplo los préstamos ACTIVOS) 
	 * @param username
	 * @param status
	 * @return Lista de Préstamos
	 * @see co.edu.udea.iw.bl.LoanBL#getLoansUser(String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public List<Loan> getLoansUser(String username, String status) throws MyException {
		if (username==null || "".equals(username)) {
			throw new MyException("El usuario no puede estar vacio");
		}
		if (status == null || "".equals(status)) {
			throw new MyException("El estado no puede ser vacio");
		}
		User user = userDAO.getUser(username);
		if (user == null) {
			throw new MyException("El usuario no esta registrado");
		}
		return loanDAO.getLoans(user, status);
	}

	/**
	 * Busca los préstamos de acuerdo al tipo y número de identificación ingresados como parámetros
	 * @param typeId
	 * @param numberId
	 * @return Lista de Préstamos
	 * @see co.edu.udea.iw.bl.LoanBL#getLoansDevice(String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public List<Loan> getLoansDevice(String typeId, String numberId) throws MyException {
		if (typeId==null || "".equals(typeId)) {
			throw new MyException("El usuario no puede estar vacio");
		}
		if (numberId == null || "".equals(numberId)) {
			throw new MyException("El estado no puede ser vacio");
		}
		return loanDAO.getLoans(typeId, numberId);
	}

	/**
	 * Elimina el préstamo que corresponde a los datos ingresados como parámetros
	 * @param username
	 * @param code
	 * @param copy
	 * @param startDate
	 * @see co.edu.udea.iw.bl.LoanBL#deleteLoan(String, String, String, Date)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public void deleteLoan(String username, String code, String copy, Date startDate) throws MyException {
		if (username==null || "".equals(username)) {
			throw new MyException("El usuario no puede estar vacio");
		}
		if (startDate==null || "".equals(startDate)) {
			throw new MyException("La fecha de inicio no puede estar vacia");
		}
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		if (getLoan(username, code, copy, startDate) == null) {
			throw new MyException("El prestamo no se encuentra registrado");
		}
		User user = userDAO.getUser(username);
		if (user == null) {
			throw new MyException("El usuario no esta registrado");
		}
		DeviceId deviceId = new DeviceId(code, copy); 
		Device device = deviceDAO.getDevice(deviceId);
		if (device == null) {
			throw new MyException("El dispositivo no esta registrado");
		}
		LoanId loanId = new LoanId(user, device, startDate);
		Loan loan = loanDAO.getLoan(loanId);
		if(loan == null){ //Duda
			throw new MyException("El Préstamo no esta registrado");
		}
		loanDAO.deleteLoan(loan);
	}
	
	/**
	 * @return the loanDAO
	 */
	public LoanDAO getLoanDAO() {
		return loanDAO;
	}

	/**
	 * @param loanDAO the loanDAO to set
	 */
	public void setLoanDAO(LoanDAO loanDAO) {
		this.loanDAO = loanDAO;
	}
	
	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return the deviceDAO
	 */
	public DeviceDAO getDeviceDAO() {
		return deviceDAO;
	}

	/**
	 * @param deviceDAO the deviceDAO to set
	 */
	public void setDeviceDAO(DeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

}
