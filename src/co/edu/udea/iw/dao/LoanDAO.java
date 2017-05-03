package co.edu.udea.iw.dao;

import java.util.Date;
import java.util.List;

import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.LoanId;
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
}
