/**
 * 
 */
package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.ast.tree.RestrictableStatement;

import co.edu.udea.iw.dao.DeviceDAO;
import co.edu.udea.iw.dto.Device;
import co.edu.udea.iw.dto.DeviceId;
import co.edu.udea.iw.dto.User;
import co.edu.udea.iw.exception.MyException;

/**
 * @author Juan Diego
 *
 */
public class DeviceDAOImpl implements DeviceDAO{
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Device> getDevices() throws MyException {
		List<Device> lista = new ArrayList<Device>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();//session provista por spring
			Criteria criteria = session.createCriteria(Device.class);
			lista = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Error consultando devices",e);
		}
		return lista;
	}

	@Override
	public void updateDevice(Device device) throws MyException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.update(device);
		} catch (HibernateException e) {
			throw new MyException("Error actualizando device",e);
		}
		
	}

	@Override
	public Device getDevice(DeviceId deviceId) throws MyException {
		Device device = null;
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			device = (Device) session.get(Device.class,deviceId);
		}catch (HibernateException e) {
			throw new MyException("Error consultando device", e);
		}
		return device;
	}

	@Override
	public void registerDevice(Device device) throws MyException {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.save(device);
		} catch (HibernateException e) {
			throw new MyException("Error guardando device", e);
		}		
	}

	@Override
	public List<Device> searchDevice(String code, String name, String type) throws MyException {
		List<Device> lista = new ArrayList<Device>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();//session provista por spring
			Criteria criteria = session.createCriteria(Device.class);
			if (code != null && !"".equals(code)) {
				criteria.add(Restrictions.eq("deviceId.code", code));
			}
			if (name != null && !"".equals(name)) {
				criteria.add(Restrictions.eq("name", name));
			}
			if (type != null && !"".equals(type)) {
				criteria.add(Restrictions.eq("type", type));
			}
			lista = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Error consultando devices",e);
		}
		return lista;
	}

}
