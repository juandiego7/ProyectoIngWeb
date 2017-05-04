package co.edu.udea.iw.bl;

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
public interface LoanBL {
	
	public  List<Loan> getLoans() throws MyException;
	
	public void registerLoan(String username,
							 Date startDate,
							 Date endDate,
							 Date returnDate,
							 String status,
							 String code,
							 String copy ) throws MyException;
	
	public List<Loan> getLoans(String code, String copy, Date date) throws MyException;
	
	public Loan getLoan(String username,String code,String copy,Date startDate) throws MyException; 
	
	public void updateLoan(String username,
						   Date startDate,
						   Date endDate,
						   Date returnDate,
						   String status,
						   String code,
						   String copy) throws MyException;
	
	public List<Loan> getLoansUser(String username, String status) throws MyException;
	
	public List<Loan> getLoansDevice(String typeId, String numberId) throws MyException;
	
	public void deleteLoan(String username, String code, String copy, Date startDate) throws MyException;
}
