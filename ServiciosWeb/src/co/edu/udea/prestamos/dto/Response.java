package co.edu.udea.prestamos.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	
	private String type;
	private String message;
	/**
	 * 
	 */
	public Response() {
		
	}
	/**
	 * @param type
	 * @param message
	 */
	public Response(String type, String message) {
		this.type = type;
		this.message = message;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	} 
	
	
	
}
