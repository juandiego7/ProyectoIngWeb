package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.exception.MyException;

/**
 * Interfaz para declarar todo los métodos asociados a los dispositivos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */

public interface DeviceDAO {
	
	/**
	 * Metodo para retonar todos los dipositivos
	 * @return List<Device> 
	 * @throws MyException
	 */
	public List<Device> getDevices() throws MyException;
	
	/**
	 * Metodo para actualizar los datos de un dispositivo
	 * @param device
	 * @throws MyException
	 */
	public void updateDevice(Device device) throws MyException;
	
	/**
	 * Metodo para obtener el dispositivo asociado a una clave primaria
	 * @param deviceId
	 * @return Device
	 * @throws MyException
	 */
	public Device getDevice(DeviceId deviceId) throws MyException;
	
	/**
	 * Metodo para registrar un dispositivo
	 * @param device
	 * @throws MyException
	 */
	public void registerDevice(Device device) throws MyException;
	
	/**
	 * Metodo para buscar dispositivos por medio de su codigo, nombre o tipo
	 * @param code
	 * @param name
	 * @param type
	 * @return List<Device>
	 * @throws MyException
	 */
	public List<Device> searchDevice(String code, String name, String type) throws MyException;
	
}
