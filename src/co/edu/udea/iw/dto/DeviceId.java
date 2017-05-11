package co.edu.udea.iw.dto;

import java.io.Serializable;

/**
 * Modelo relacional(pojo) para la clave primaria de un dispositivo 
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
public class DeviceId implements Serializable{
	private String code;
	private String copy;
	
	public DeviceId() {

	}
	/**
	 * @param code
	 * @param copy
	 */
	public DeviceId(String code, String copy) {
		this.code = code;
		this.copy = copy;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCopy() {
		return copy;
	}
	public void setCopy(String copy) {
		this.copy = copy;
	}
	
}
