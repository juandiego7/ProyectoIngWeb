package co.edu.udea.iw.bl.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.DeviceBL;
import co.edu.udea.iw.dao.DeviceDAO;
import co.edu.udea.iw.dao.UserDAO;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
@Transactional
public class DeviceBLImpl implements DeviceBL {

	private DeviceDAO deviceDAO;

	@Override
	public List<Device> getDevices() throws MyException {
		return deviceDAO.getDevices();
	}

	@Override
	public void updateDevice(String code, String copy, String name, String type, String status, String details)
			throws MyException {
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		if (name == null || "".equals(name)) {
			throw new MyException("El nombre no puede estar vacio");
		}
		if (type == null || "".equals(type)) {
			throw new MyException("El tipo no puede ser vacio");
		}
		if (status == null || "".equals(status)) {
			throw new MyException("El estado no puede ser vacio");
		}
		//DeviceId deviceId = new DeviceId(code,copy);
		
		Device device = getDevice(code, copy);
		//new Device(deviceId, name, type, status, details);
		if (device == null) {
			throw new MyException("El dispositivo no se encuentra registrado");
		}
		deviceDAO.updateDevice(device);
	}

	@Override
	public Device getDevice(String code, String copy) throws MyException {
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		DeviceId deviceId = new DeviceId(code, copy);
		return deviceDAO.getDevice(deviceId);
	}

	@Override
	public void registerDevice(String code, String copy, String name, String type, String status, String details)
			throws MyException {
		if (code == null || "".equals(code)) {
			throw new MyException("El codigo no puede estar vacio");
		}
		if (copy == null || "".equals(copy)) {
			throw new MyException("La copia no puede ser vacio");
		}
		if (name == null || "".equals(name)) {
			throw new MyException("El nombre no puede estar vacio");
		}
		if (type == null || "".equals(type)) {
			throw new MyException("El tipo no puede ser vacio");
		}
		if (status == null || "".equals(status)) {
			throw new MyException("El estado no puede ser vacio");
		}
		if (getDevice(code, copy) != null) {
			throw new MyException("El dispositovo ya se encuentra registrado");
		}
		DeviceId deviceId = new DeviceId(code,copy);			
		Device device = new Device(deviceId, name, type, status, details);
		deviceDAO.registerDevice(device);
	}

	@Override
	public List<Device> searchDevice(String code, String name, String type) throws MyException {
		return deviceDAO.searchDevice(code, name, type);
	}

	/**
	 * @return the deviceDAO
	 */
	public DeviceDAO getDeviceDAO() {
		return deviceDAO;
	}

	/**
	 * @param deviceDAO the deviceDAO to set
	 */
	public void setDeviceDAO(DeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}
}
