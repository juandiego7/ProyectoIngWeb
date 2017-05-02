package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.exception.MyException;

public interface DeviceDAO {
	
	public List<Device> getDevices() throws MyException;
	
	public void updateDevice(Device device) throws MyException;
	
	public Device getDevice(DeviceId deviceId) throws MyException;
	
	public void registerDevice(Device device) throws MyException;
	
	public List<Device> searchDevice(String code, String name, String type) throws MyException;
	
}
