package co.edu.udea.prestamos.dto;

import java.util.Date;

import co.edu.udea.iw.dto.Device;


public class LoanId {
	private User username;
	private Device device;
	private Date startDate;
	/**
	 * @param username
	 * @param device
	 * @param startDate
	 */
	public LoanId(User username, Device device, Date startDate) {
		super();
		this.username = username;
		this.device = device;
		this.startDate = startDate;
	}
	
	public LoanId() {
	
	}

	/**
	 * @return the username
	 */
	public User getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(User username) {
		this.username = username;
	}

	/**
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
}
