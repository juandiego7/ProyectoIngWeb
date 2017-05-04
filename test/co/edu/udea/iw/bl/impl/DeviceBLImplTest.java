/**
 * 
 */
package co.edu.udea.iw.bl.impl;

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

import co.edu.udea.iw.bl.DeviceBL;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class DeviceBLImplTest {

	@Autowired
	DeviceBL deviceBL;
	
	Logger logger = Logger.getLogger(MyException.class);//Para manejar los errores
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#getDevices()}.
	 */
	@Test
	public void testGetDevices() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Device> lista = null;//Lista donde se almacenan las ciudades
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		try {
			lista = deviceBL.getDevices();
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
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#updateDevice(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Device device = null;
		String code = null;
		String copy = null;
		try {
			device = deviceBL.getDevice("0001", "1");
			code = device.getDeviceId().getCode();
			copy = device.getDeviceId().getCopy();
			deviceBL.updateDevice(code, copy, device.getName(), device.getType(), "portatil", "procesador corei 5 intel");
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#getDevice(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Device device = null;
		try {
			device = deviceBL.getDevice("0002", "1");
			System.out.println("1231231231231-Name: "+device.getName());
			assertTrue(device != null);

		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#registerDevice(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegisterDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		try {
			deviceBL.registerDevice("0005","1", "Mouse", "I/O","DISPONIBLE","Inalambrico");
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#searchDevice(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSearchDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Device> lista = null;//Lista donde se almacenan las ciudades
		
		try {
			lista = deviceBL.searchDevice("0001", null, null);
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
