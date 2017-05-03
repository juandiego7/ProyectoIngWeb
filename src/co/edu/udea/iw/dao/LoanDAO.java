package co.edu.udea.iw.dao;

import java.util.Date;
import java.util.List;

import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.LoanId;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public interface LoanDAO {
	
	public  List<Loan> getLoans() throws MyException;
	
	public void registerLoan(Loan loan) throws MyException;
	
	public List<Loan> getLoans(DeviceId deviceId, Date date) throws MyException;
	
	public Loan getLoan(LoanId loanId) throws MyException; 
	
	public void updateLoan(Loan loan) throws MyException;
	
	public List<Loan> getLoans(User user, String status) throws MyException;
	
	public List<Loan> getLoans(String typeId, String numberId) throws MyException;
	
	public void deleteLoan(Loan loan) throws MyException;
}
