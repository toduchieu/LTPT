package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.LoaiSachDao;
import entity.LoaiSach;
import util.HibernateUtil;

public  class ImplLoaiSach extends UnicastRemoteObject implements LoaiSachDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7629041601287634790L;
	private EntityManager em;
	public ImplLoaiSach() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}
	@Override
	public boolean addLoaiSach(LoaiSach loaiSach) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(loaiSach);
			
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return false;
	}
	
	@Override
	public List<LoaiSach> getAllLoaiSach() throws RemoteException {

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			@SuppressWarnings("unchecked")
			List<LoaiSach> ls =  em.createNativeQuery("db.dsLoaiSach.find({})",LoaiSach.class).getResultList();
			
		
			tr.commit();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return null;
	}
	@Override
	public LoaiSach getLoaiSachTheoTen(String tenLoai) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			
			LoaiSach lt =  (LoaiSach) em.createNativeQuery("db.dsLoaiSach.find({'ten_Loai_Sach': '"+tenLoai+"'})",LoaiSach.class).getSingleResult();
			
		
			tr.commit();
			return lt;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}




}
