package co.edu.udea.iw.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Proxy;

/**
 * Modelo relacional (pojo) de los préstamos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@XmlRootElement
public class Loan {

	private LoanId loanId;
	private Date endDate;
	private Date returnDate;
	private String status;

	public Loan() {
		
	}
	/**
	 * @param loanId
	 * @param endDate
	 * @param returnDate
	 * @param status
	 */
	public Loan(LoanId loanId, Date endDate, Date returnDate, String status) {
		this.loanId = loanId;
		this.endDate = endDate;
		this.returnDate = returnDate;
		this.status = status;
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
