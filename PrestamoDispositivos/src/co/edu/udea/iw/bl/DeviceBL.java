package co.edu.udea.iw.bl;

import java.util.List;

import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.exception.MyException;

/**
 * Interfaz para definir los métodos de la lógica
 * de negocio para los dispositivos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.1
 *
 */
public interface DeviceBL {
	/**
	 * Obtiene la lista de todos los dispositivos que existan en la base de datos
	 * @return Lista de Dispositivos
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public List<Device> getDevices() throws MyException;
	
	/**
	 * Actualiza la información de un dispositvo en la base de datos
	 * @param code
	 * @param copy
	 * @param name
	 * @param type
	 * @param status
	 * @param details
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public void updateDevice(String code,
							 String copy,
							 String name,
							 String type,
							 String status,
							 String details) throws MyException;
	/**
	 * Obtiene el dispositivo que corresponde con código y copia ingresados como parámetros  
	 * @param code
	 * @param copy
	 * @return Un Dispositivo
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public Device getDevice(String code, String copy) throws MyException;
	
	/**
	 * Inserta un dispositivo en la base de datos con la información ingresada como parámetro
	 * @param code
	 * @param copy
	 * @param name
	 * @param type
	 * @param status
	 * @param details
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public void registerDevice(String code,
    						   String copy,
							   String name,
							   String type,
							   String status,
							   String details) throws MyException;
	
	/**
	 * Obtiene los dispositivos en los que el código, el nombre o el tipo sea igual a los datos enviados como parámetros 
	 * @param code
	 * @param name
	 * @param type
	 * @return Lista de Dispositivos
	 * @throws MyException
	 * Lanzamos nuestra propia exception para manejarla en una capa superior
	 */
	public List<Device> searchDevice(String code, String name, String type) throws MyException;
	
}
