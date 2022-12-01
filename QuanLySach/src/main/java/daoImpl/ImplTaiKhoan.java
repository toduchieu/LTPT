package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import util.HibernateUtil;

public class ImplTaiKhoan extends UnicastRemoteObject implements TaiKhoanDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5680460319162083507L;
	private EntityManager em;

	public ImplTaiKhoan() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}


	public boolean addTaiKhoan(TaiKhoan taiKhoan) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			em.persist(taiKhoan);

			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	public boolean updateTaiKhoan(TaiKhoan taiKhoan) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			em.merge(taiKhoan);

			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	public TaiKhoan timtaikhoangtheoten(String ten) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			String query="db.dsTaiKhoan.find({'tai_Khoan':'"+ten+"'})";
			TaiKhoan taiKhoan=(TaiKhoan) em.createNativeQuery(query,TaiKhoan.class).getSingleResult();

			tr.commit();
			return taiKhoan;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
