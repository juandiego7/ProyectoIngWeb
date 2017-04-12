package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.LoanDAO;
import co.edu.udea.iw.dto.Loan;
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

}
