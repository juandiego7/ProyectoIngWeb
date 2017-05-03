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
			for(Loan loan: lista){
				System.out.println("status: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
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
		calendar.add(Calendar.HOUR, 2);  // numero de horas a añadir, o restar en caso de horas<0
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
		calendar.add(Calendar.HOUR, 2);  // numero de horas a añadir, o restar en caso de horas<0
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
	
	@Test
	public void testGetLoansDevice() {
		List<Loan> lista = null;//Lista donde se almacenan las ciudades
		DeviceId deviceId = null;
		Calendar calendar = null;
		Date date = null;
		try {
			deviceId = new DeviceId("0001", "1");
			date = new Date();
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2017);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 11);
			date = calendar.getTime();	
			lista = loanDAO.getLoans(deviceId,date);
			for(Loan loan: lista){
				System.out.println("status222: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateLoan() {
		Loan loan = null;
		LoanId loanId = null;
		User user = null;
		DeviceId deviceId = null;
		Device device = null;
		Calendar calendar = null;
		Date date = null;
		try {
			date = new Date();
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2017);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 11);
			calendar.set(Calendar.HOUR,22);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			date = calendar.getTime();
			System.out.println("fecha 23: "+date);
			user = new User();
			user.setUsername("juan.goez");
			
			deviceId = new DeviceId("0001","1");
			device = new Device();
			device.setDeviceId(deviceId);
			loanId = new LoanId(user,device,date);
			
			loan = loanDAO.getLoan(loanId);
			loan.setStatus("PRESTADO");
			loanDAO.updateLoan(loan);
			
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateLoanStatus() {
		Loan loan = null;
		LoanId loanId = null;
		User user = null;
		DeviceId deviceId = null;
		Device device = null;
		Calendar calendar = null;
		Date date = null;
		try {
			date = new Date();
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2017);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 11);
			calendar.set(Calendar.HOUR,22);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			date = calendar.getTime();
			
			user = new User();
			user.setUsername("juan.goez");
			
			deviceId = new DeviceId("0001","1");
			device = new Device();
			device.setDeviceId(deviceId);
			loanId = new LoanId(user,device,date);
			
			loan = loanDAO.getLoan(loanId);
			loan.setStatus("ENTREGADO");
			loan.setReturnDate(new Date());
			loanDAO.updateLoan(loan);
			
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetLoansUser() {
		List<Loan> lista = null;//Lista donde se almacenan las ciudades
		User user = null;
		try {
			user = new User();
			user.setUsername("raulio");
			lista = loanDAO.getLoans(user,"PRESTADO");
			for(Loan loan: lista){
				System.out.println("statusUser: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetLoansUserHistory() {
		List<Loan> lista = null;//Lista donde se almacenan las ciudades
		User user = null;
		try {
			user = new User();
			user.setUsername("raulio");
			lista = loanDAO.getLoans(user,"ENTREGADO");
			for(Loan loan: lista){
				System.out.println("statusUserHistory: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLoansNumberId() {
		List<Loan> lista = null;//Lista donde se almacenan las ciudades

		try {
			lista = loanDAO.getLoans("CC","12345");
			for(Loan loan: lista){
				System.out.println("statusNumber: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteLoan() {
		Loan loan = null;
		LoanId loanId = null;
		User user = null;
		DeviceId deviceId = null;
		Device device = null;
		Calendar calendar = null;
		Date date = null;
		try {
			date = new Date();
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2017);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a añadir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 11);
			calendar.set(Calendar.HOUR,22);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			user = new User();
			user.setUsername("juan.goez");
			deviceId = new DeviceId("0001","1");
			device = new Device();
			device.setDeviceId(deviceId);
			loanId = new LoanId(user,device,date);
			loan = new Loan();
			loan.setLoanId(loanId);
			loanDAO.deleteLoan(loan);
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
}
