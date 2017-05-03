package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.LoanDAO;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.Loan;
import co.edu.udea.iw.dto.LoanId;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public class LoanDAOImpl implements LoanDAO {
	
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Loan> getLoans() throws MyException {
		List<Loan> lista = new ArrayList<Loan>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();//session provista por spring
			Criteria criteria = session.createCriteria(Loan.class);
			lista = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Error consultando loans",e);
		}
		return lista;
	}

	@Override
	public void registerLoan(Loan loan) throws MyException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.save(loan);
		} catch (HibernateException e) {
			throw new MyException("Error guardando loan", e);
		}	
	}

	@Override
	public List<Loan> getLoans(DeviceId deviceId, Date date) throws MyException {
		List<Loan> lista = new ArrayList<Loan>();
		Device device = null;
		Session session = null;
		Date startDateToday = null;
		Date endDateToday = null;
		Calendar calendar = null;
		try {		
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR, 1);
			startDateToday = calendar.getTime();
			calendar.set(Calendar.HOUR, 23);
			endDateToday = calendar.getTime();
			device = new Device();
			device.setDeviceId(deviceId);
			session = sessionFactory.getCurrentSession();//session provista por spring
			Criteria criteria = session.createCriteria(Loan.class);
			criteria.add(Restrictions.eq("loanId.device", device));
			criteria.add(Restrictions.between("loanId.startDate", startDateToday,endDateToday));
			lista = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Error consultando loans-device",e);
		}
		return lista;
	}

	@Override
	public void updateLoan(Loan loan) throws MyException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(loan);
		} catch (HibernateException e) {
			throw new MyException("Error actualizando loan",e);
		}		
	}

	@Override
	public Loan getLoan(LoanId loanId) throws MyException {
		Loan loan = null;
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			loan = (Loan) session.get(Loan.class,loanId);
		}catch (HibernateException e) {
			throw new MyException("Error consultando loan", e);
		}
		return loan;
	}

}
