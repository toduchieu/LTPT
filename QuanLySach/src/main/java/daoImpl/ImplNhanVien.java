package daoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.NhanVienDao;
import entity.NhanVien;
import util.HibernateUtil;

public class ImplNhanVien extends UnicastRemoteObject implements NhanVienDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -208819529374076682L;
	private EntityManager em;

	public ImplNhanVien() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean addNhanVien(NhanVien nhanVien) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		
		try {
			tr.begin();
				em.persist(nhanVien);
			
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return false;
	}

	@Override
	public List<NhanVien> getDSNhanVien() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			@SuppressWarnings("unchecked")
			List<NhanVien> nv =  em.createNativeQuery("db.dsNhanVien.find({'trang_Thai_Lam_Viec' : 'Đang làm việc'})",NhanVien.class).getResultList();
			
			tr.commit();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public int soNhanVien() throws RemoteException {
		int i=0;
		EntityTransaction tr = em.getTransaction();
		try {
			
			tr.begin();
			@SuppressWarnings("unchecked")
			List<NhanVien> nv =  em.createNativeQuery("db.dsNhanVien.find({})",NhanVien.class).getResultList();
		    i= nv.size();
			tr.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return i;
	}

	@Override
	public boolean updateNhanVien(NhanVien nhanVien) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		
		try {
			tr.begin();
				em.merge(nhanVien);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public NhanVien getNhanVienTheoSoNV(String maNV) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		
		try {
			tr.begin();
			String query = "db.dsNhanVien.find({'so_NhanVien' : '"+maNV+"' })";
				NhanVien nv = (NhanVien) em.createNativeQuery(query,NhanVien.class).getSingleResult();
			tr.commit();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return null;
	}

	@Override
	public List<NhanVien> getTim(String text) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {	
			tr.begin();
			String query = "db.dsNhanVien.find({ $text: { $search: '"+text+"' } } )";
			@SuppressWarnings("unchecked")
			List<NhanVien> nv =  em.createNativeQuery(query,NhanVien.class).getResultList();
			tr.commit();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	

	@Override
	public NhanVien getNVTheoSDT(String sdt) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			NhanVien nv  = (NhanVien) em.createNativeQuery("db.dsNhanVien.find({'sdt' : '"+sdt+"'})",NhanVien.class).getSingleResult();		
			
			tr.commit();
			if(nv != null)
				return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}


}
