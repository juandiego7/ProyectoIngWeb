package co.edu.udea.iw.dto;

import java.util.Date;

/**
 * @author Juan Diego
 *
 */
public class Loan {

	private LoanId loanId;
	private Date endDate;
	private Date returnDate;
	private String status;

	/**
	 * @return the loanId
	 */
	public LoanId getLoanId() {
		return loanId;
	}
	/**
	 * @param loanId the loanId to set
	 */
	public void setLoanId(LoanId loanId) {
		this.loanId = loanId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
