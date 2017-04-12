package co.edu.udea.iw.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Juan Diego
 *
 */
public class LoanId implements Serializable {
	private User username;
	private Device device;
	private Date startDate;
	
	public User getUsername() {
		return username;
	}
	public void setUsername(User username) {
		this.username = username;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
