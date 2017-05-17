package co.edu.udea.iw.bl.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.DeviceBL;
import co.edu.udea.iw.dao.DeviceDAO;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.exception.MyException;

/**
 * Implementación de la lógica de negocio para los dispositivos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@Transactional
public class DeviceBLImpl implements DeviceBL {
	
	private DeviceDAO deviceDAO;
	
	/**
	 * Obtiene la lista de todos los dispositivos que existan en la base de datos
	 * @return Lista de Dispositivos
	 * @see co.edu.udea.iw.bl.DeviceBL#getDevices()
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	@Override
	public List<Device> getDevices() throws MyException {
		return deviceDAO.getDevices();
	}
	
	/**
	 * Actualiza la información de un dispositvo en la base de datos
	 * @param code
	 * @param copy
	 * @param name
	 * @param type
	 * @param status
	 * @param details
	 * @see co.edu.udea.iw.bl.DeviceBL#updateDevice(String, String, String, String, String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
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
		Device device = getDevice(code, copy);
		if (device == null) {
			throw new MyException("El dispositivo no se encuentra registrado");
		}
		deviceDAO.updateDevice(device);
	}
	
	/**
	 * Obtiene el dispositivo que corresponde con código y copia ingresados como parámetros  
	 * @param code
	 * @param copy
	 * @return Un Dispositivo
	 * @see co.edu.udea.iw.bl.DeviceBL#getDevice(String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
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

	/**
	 * Inserta un dispositivo en la base de datos con la información ingresada como parámetro
	 * @param code
	 * @param copy
	 * @param name
	 * @param type
	 * @param status
	 * @param details
	 * @see co.edu.udea.iw.bl.DeviceBL#registerDevice(String, String, String, String, String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
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
	
	/**
	 * Obtiene los dispositivos en los que el código, el nombre o el tipo sea igual a los datos enviados como parámetros 
	 * @param code
	 * @param name
	 * @param type
	 * @return Lista de Dispositivos
	 * @see co.edu.udea.iw.bl.DeviceBL#searchDevice(String, String, String)
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
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
