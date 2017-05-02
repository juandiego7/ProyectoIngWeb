package co.edu.udea.iw.dto;

/**
 * @author Juan Diego
 *
 */

public class Device {

	private DeviceId deviceId;
	private String name;
	private String type;
	private String status;
	private String details;
	
	public Device() {

	}
	/**
	 * @param deviceId
	 * @param name
	 * @param type
	 * @param status
	 * @param details
	 */
	public Device(DeviceId deviceId, String name, String type, String status, String details) {
		this.deviceId = deviceId;
		this.name = name;
		this.type = type;
		this.status = status;
		this.details = details;
	}
	public DeviceId getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(DeviceId deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
