package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.bson.types.ObjectId;

import dao.SachDao;
import entity.Sach;
import util.HibernateUtil;

public class ImplSach  extends UnicastRemoteObject implements SachDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3692853915209401270L;
	private EntityManager em;
	public ImplSach() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	public boolean addSach(Sach Sach) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			em.merge(Sach);

			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	

	public List<Sach> getSachTheoMaLoai(ObjectId maLoai) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			String query = "db.dsSach.aggregate([{"
					+ "    '$match': {"
					+ "        'LoaiSach_Id': ObjectId('"+maLoai+"'),"
					+ "        'trangThaiSach': 'Còn bán'"
					+ "    }"
					+ "}])";
			@SuppressWarnings("unchecked")
			List<Sach> ls = em.createNativeQuery(query,Sach.class).getResultList();
			

			tr.commit();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return null;
	}

	public Sach getSachTheoTenVaMaLoai(String tenSach, ObjectId maLoai) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			String query = "db.dsSach.aggregate([{"
					+ "    '$match': {"
					+ "        'tenSach': '"+tenSach+"' ,'LoaiSach_Id': ObjectId('"+maLoai+"'),"
					+ "        'trangThaiSach': 'Còn bán'"
					+ "    }\r\n"
					+ "}])";
			
			Sach t = (Sach) em.createNativeQuery(query,Sach.class).getSingleResult();
			

			tr.commit();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	public List<Sach> getAllSach() throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tr=em.getTransaction();
		try {
			tr.begin();
			String query="db.dsSach.find({'trangThaiSach':'Còn bán'})";
			@SuppressWarnings("unchecked")
			List<Sach>ls=em.createNativeQuery(query,Sach.class).getResultList();
			tr.commit();
			return ls;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public boolean updateSach(Sach Sach) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			em.merge(Sach);

			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	public List<Sach> timkiemSach(String key) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			
			String query="db.dsSach.find({ $text: { $search: '"+key+"' } } )";
			
			@SuppressWarnings("unchecked")
			List<Sach> ls=em.createNativeQuery(query,Sach.class).getResultList();

			tr.commit();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return null;
	}
	

}
