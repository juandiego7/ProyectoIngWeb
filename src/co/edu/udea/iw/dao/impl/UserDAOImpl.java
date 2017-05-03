package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.iw.dao.UserDAO;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public class UserDAOImpl implements UserDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<User> getUsers() throws MyException {
		List<User> lista = new ArrayList<User>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();//session provista por spring
			Criteria criteria = session.createCriteria(User.class);
			lista = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Error consultando usuarios",e);
		}
		return lista;
	}

	@Override
	public void registerUser(User user) throws MyException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.save(user);
		} catch (HibernateException e) {
			throw new MyException("Error guardando user", e);
		}		
	}

	@Override
	public User getUser(String username) throws MyException {
		User user = null;
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			user = (User) session.get(User.class,username);
		}catch (HibernateException e) {
			throw new MyException("Error consultando usuario", e);
		}
		return user;
	}
	
}
