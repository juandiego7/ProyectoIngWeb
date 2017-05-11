/**
 * 
 */
package co.edu.udea.iw.bl.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
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

import co.edu.udea.iw.bl.LoanBL;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.LoanId;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * Pruebas de los métodos de la lógica de negocio de los préstamos
 * @author Raul Antonio Martinez Silgado - rantonio.martinez@udea.edu.co
 * @author Juan Diego Goez Durango - diego.goez@udea.edu.co
 * @version 2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)//Correr con otro running
@Transactional//transaccional
@ContextConfiguration(locations="classpath:config.xml")
public class LoanBLImplTest {

	@Autowired
	LoanBL loanBL;
	
	Logger logger = Logger.getLogger(MyException.class);//Para manejar los errores
	
	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#getLoans()}.
	 */
	@Test
	public void testGetLoans() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Loan> lista = null;//Lista donde se almacenan los prestamos
		
		try {
			lista = loanBL.getLoans();
			for(Loan loan: lista){
				System.out.println("status: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#registerLoan(java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegisterLoan() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Configuramos la fecha que se recibe
		calendar.add(Calendar.HOUR, 2);  // numero de horas a a�adir, o restar en caso de horas<0
		Date endDate = calendar.getTime();
		try {
			loanBL.registerLoan("raulio2",new Date(),endDate,null,"RESERVADO","0004","1");
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#getLoans(java.lang.String, java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testGetLoansDeviceId() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Loan> lista = null;//Lista donde se almacenan los prestamos
		Calendar calendar = null;
		Date date = null;
		try {
			date = new Date();
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2017);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 11);
			calendar.set(Calendar.HOUR,22);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			date = calendar.getTime();
			lista = loanBL.getLoans("0001", "1",date);
			for(Loan loan: lista){
				System.out.println("status222: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#getLoan(java.lang.String, java.lang.String, java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testGetLoan() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Loan loan = null;//Lista donde se almacenan las ciudades
		Calendar calendar = null;
		Date date = null;
		try {
			date = new Date();
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2017);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 11);
			calendar.set(Calendar.HOUR,22);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			date = calendar.getTime();
			loan = loanBL.getLoan("juan.goez", "0001", "1", date);
			System.out.println("statusLoan: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			assertTrue(loan != null);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#updateLoan(java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateLoan() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
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
			calendar.set(Calendar.YEAR, 2017);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 11);
			calendar.set(Calendar.HOUR,22);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			date = calendar.getTime();			
			loan = loanBL.getLoan("juan.goez", "0001", "1", date);
			loanBL.updateLoan("juan.goez",
							  loan.getLoanId().getStartDate(),
							  loan.getEndDate(),
							  new Date(),
							  "ENTREGADO",
							  "0001","1");
			
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#getLoansUser(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetLoansUser() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Loan> lista = null;//Lista donde se almacenan los prestamos
		try {
			lista = loanBL.getLoansUser("juan.goez", "RESERVADO");
			for(Loan loan: lista){
				System.out.println("statusUser: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#getLoansDevice(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetLoansDevice() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		List<Loan> lista = null;//Lista donde se almacenan los prestamos
		try {
			lista = loanBL.getLoansDevice("CC", "12345");
			for(Loan loan: lista){
				System.out.println("statusUser: "+loan.getStatus() +
									"-1-"+loan.getEndDate()+
									"-3-"+loan.getLoanId().getUsername().getName()+
									"-5-"+loan.getReturnDate());
			}
			assertTrue(lista.size()>0);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.udea.iw.bl.impl.LoanBLImpl#deleteLoan(java.lang.String, java.lang.String, java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testDeleteLoan() {
		PropertyConfigurator.configure("src/log4j.properties");//propiedades para configurar log4j
		Calendar calendar = null;
		Date date = null;
		try {
			date = new Date();
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2017);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.MONTH, 3);// numero de horas a a�adir, o restar en caso de horas<0
			calendar.set(Calendar.DATE, 12);
			calendar.set(Calendar.HOUR,10);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			date = calendar.getTime();
			System.out.println(date);
			loanBL.deleteLoan("juan.goez", "0001", "1", date);
		} catch (MyException e) {
			logger.log(Level.ERROR,"Error consultando: "+ e.getMessage());
		}
	}

}
