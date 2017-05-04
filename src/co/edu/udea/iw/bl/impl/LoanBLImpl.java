package co.edu.udea.iw.bl.impl;

import java.util.Date;
import java.util.List;

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
 * @author Juan Diego
 *
 */
public class LoanBLImpl implements LoanBL {

	private LoanDAO loanDAO;
	private UserDAO userDAO;
	private DeviceDAO deviceDAO;

	@Override
	public List<Loan> getLoans() throws MyException {
		return loanDAO.getLoans();
	}

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
		LoanId loanId = new LoanId(user, device, startDate);
		Loan loan = new Loan(loanId, endDate, returnDate, status);
		loanDAO.registerLoan(loan);
	}

	@Override
	public List<Loan> getLoans(String code, String copy, Date date) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan getLoan(String username, String code, String copy, Date startDate) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLoan(String username, Date startDate, Date endDate, Date returnDate, String status, String code,
			String copy) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Loan> getLoansUser(String username, String status) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> getLoansDevice(String typeId, String numberId) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLoan(String username, String code, String copy, Date startDate) throws MyException {
		// TODO Auto-generated method stub
		
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
