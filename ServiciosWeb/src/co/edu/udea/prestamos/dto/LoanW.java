package co.edu.udea.prestamos.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoanW {
	private LoanId loanId;
	private Date endDate;
	private Date returnDate;
	private String status;
	/**
	 * @param loanId
	 * @param endDate
	 * @param returnDate
	 * @param status
	 */
	public LoanW(LoanId loanId, Date endDate, Date returnDate, String status) {
		this.loanId = loanId;
		this.endDate = endDate;
		this.returnDate = returnDate;
		this.status = status;
	}

	public LoanW() {

	}

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

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
