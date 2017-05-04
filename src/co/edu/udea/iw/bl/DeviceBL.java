package co.edu.udea.iw.bl;

import java.util.List;

import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public interface DeviceBL {
	
	public List<Device> getDevices() throws MyException;
	
	public void updateDevice(String code,
							 String copy,
							 String name,
							 String type,
							 String status,
							 String details) throws MyException;
	
	public Device getDevice(String code, String copy) throws MyException;
	
	public void registerDevice(String code,
    						   String copy,
							   String name,
							   String type,
							   String status,
							   String details) throws MyException;
	
	public List<Device> searchDevice(String code, String name, String type) throws MyException;
	
}
