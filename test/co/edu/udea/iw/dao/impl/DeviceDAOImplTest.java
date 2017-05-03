/**
 * 
 */
package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

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
 * @author Juan Diego
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class DeviceDAOImplTest {
	@Autowired//Inyectar datos desde la base de datos
	DeviceDAO deviceDAO;
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.DeviceDAOImpl#getDevices()}.
	 */
	@Test
	public void testGetDevices() {
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
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStatusDevice() {
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
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateDevice() {
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
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetDevice() {
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
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegisterDevice() {
		Device device = null;		
		DeviceId deviceId = null;
		try {
			deviceId = new DeviceId("0003","1");
			device = new Device(deviceId,"Mouse","I/O","DISPONIBLE","Inalambrico");
			deviceDAO.registerDevice(device);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchDevice() {
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
			e.printStackTrace();
		}
	}
}
