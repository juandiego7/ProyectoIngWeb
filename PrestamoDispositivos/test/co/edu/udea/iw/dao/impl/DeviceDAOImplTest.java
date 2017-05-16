/**
 * 
 */
package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DeviceDAO;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.exception.MyException;

/**
 * Pruebas de los métodos del DAO de los dispositivos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class DeviceDAOImplTest {
	@Autowired//Inyectar datos desde la base de datos
	DeviceDAO deviceDAO;
	Logger logger = Logger.getLogger(MyException.class);//Para manejar los errores
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.DeviceDAOImpl#getDevices()}.
	 */
	@Test
	public void testGetDevices() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Device> lista = null;//Lista donde se almacenan las ciudades
		
		try {
			lista = deviceDAO.getDevices();
			for(Device device: lista){
				System.out.println("Name: "+device.getName() + "\n" +
									"Details: "+device.getDetails() + "\n" +
									"Code: "+device.getDeviceId().getCode());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.DeviceDAOImpl#updateDevice(Device)}.
	 */
	@Test
	public void testUpdateStatusDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Device device = null;
		DeviceId deviceId = null;
		
		try {
			deviceId = new DeviceId();
			deviceId.setCode("0002");
			deviceId.setCopy("1");
			device = deviceDAO.getDevice(deviceId);
			device.setStatus("INHABILITADO");
			deviceDAO.updateDevice(device);

		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.DeviceDAOImpl#updateDevice(Device)}.
	 */
	@Test
	public void testUpdateDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Device device = null;
		DeviceId deviceId = null;
		
		try {
			deviceId = new DeviceId();
			deviceId.setCode("0002");
			deviceId.setCopy("1");
			device = deviceDAO.getDevice(deviceId);
			device.setDetails("procesador corei 5 intel");
			deviceDAO.updateDevice(device);

		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.DeviceDAOImpl#getDevice(DeviceId)}.
	 */
	@Test
	public void testGetDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Device device = null;
		DeviceId deviceId = null;
		
		try {
			deviceId = new DeviceId();
			deviceId.setCode("0002");
			deviceId.setCopy("1");
			device = deviceDAO.getDevice(deviceId);
			System.out.println("1231231231231-Name: "+device.getName());
			assertTrue(device != null);

		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.DeviceDAOImpl#registerDevice(Device)}.
	 */
	@Test
	public void testRegisterDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Device device = null;		
		DeviceId deviceId = null;
		try {
			deviceId = new DeviceId("0003","1");
			device = new Device(deviceId,"Mouse","I/O","DISPONIBLE","Inalambrico");
			deviceDAO.registerDevice(device);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.DeviceDAOImpl#searchDevice(String, String, String)}.
	 */
	@Test
	public void testSearchDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Device> lista = null;//Lista donde se almacenan las ciudades
		
		try {
			lista = deviceDAO.searchDevice("0001", null, null);
			for(Device device: lista){
				System.out.println("Name: "+device.getName() + "\n" +
									"Details: "+device.getDetails() + "\n" +
									"Code: "+device.getDeviceId().getCode());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}
}
