/**
 * 
 */
package co.edu.udea.iw.bl.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.DeviceBL;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
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
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#getDevices()}.
	 */
	@Test
	public void testGetDevices() {
		List<Device> lista = null;//Lista donde se almacenan las ciudades
		
		try {
			lista = deviceBL.getDevices();
			for(Device device: lista){
				System.out.println("Name: "+device.getName() + "\n" +
									"Details: "+device.getDetails() + "\n" +
									"Code: "+device.getDeviceId().getCode());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#updateDevice(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateDevice() {
		Device device = null;
		String code = null;
		String copy = null;
		try {
			device = deviceBL.getDevice("0001", "1");
			code = device.getDeviceId().getCode();
			copy = device.getDeviceId().getCopy();
			deviceBL.updateDevice(code, copy, device.getName(), device.getType(), "portatil", "procesador corei 5 intel");
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#getDevice(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetDevice() {
		Device device = null;
		try {
			device = deviceBL.getDevice("0002", "1");
			System.out.println("1231231231231-Name: "+device.getName());
			assertTrue(device != null);

		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#registerDevice(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegisterDevice() {
		try {
			deviceBL.registerDevice("0005","1", "Mouse", "I/O","DISPONIBLE","Inalambrico");;
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.DeviceBLImpl#searchDevice(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSearchDevice() {
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
			e.printStackTrace();
		}
	}

}
