package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public interface LoanDAO {
	
	public  List<Loan> getLoans() throws MyException;
}
