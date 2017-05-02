/**
 * 
 */
package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.LoanDAO;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.LoanId;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class LoanDAOImplTest {

	@Autowired//Inyectar datos desde la base de datos
	LoanDAO loanDAO;
	/**
	 * Test method for {@link co.edu.udea.iw.dao.impl.LoanDAOImpl#getLoans()}.
	 */
	@Test
	public void testGetLoans() {
		List<Loan> lista = null;//Lista donde se almacenan las ciudades
		
		try {
			lista = loanDAO.getLoans();
			for(Loan ciudad: lista){
				System.out.println("status: "+ciudad.getStatus() +
									"-1-"+ciudad.getEndDate()+
									//"2-"+ciudad.getLoanId().getDevice().getName()+
									"3-"+ciudad.getLoanId().getUsername().getName()+
									//"-4-"+ciudad.getLoanId().getStartDate()+
									"-5-"+ciudad.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegisterLoan() {
		Loan loan = null;
		LoanId loanId = null;
		User user = null;
		DeviceId deviceId = null;
		Device device = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Configuramos la fecha que se recibe
		calendar.add(Calendar.HOUR, 2);  // numero de horas a a�adir, o restar en caso de horas<0
		Date endDate = calendar.getTime();
		try {
			user = new User();
			user.setUsername("raulio");
			deviceId = new DeviceId("0001","1");
			device = new Device();
			device.setDeviceId(deviceId);
			loanId = new LoanId(user,device,new Date());
			loan = new Loan(loanId, endDate, null, "PRESTADO");
			loanDAO.registerLoan(loan);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRequestLoan() {
		Loan loan = null;
		LoanId loanId = null;
		User user = null;
		DeviceId deviceId = null;
		Device device = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Configuramos la fecha que se recibe
		calendar.add(Calendar.HOUR, 2);  // numero de horas a a�adir, o restar en caso de horas<0
		Date endDate = calendar.getTime();
		try {
			user = new User();
			user.setUsername("raulio");
			deviceId = new DeviceId("0001","1");
			device = new Device();
			device.setDeviceId(deviceId);
			loanId = new LoanId(user,device,new Date());
			loan = new Loan(loanId, endDate, null, "RESERVADO");
			loanDAO.registerLoan(loan);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

}
