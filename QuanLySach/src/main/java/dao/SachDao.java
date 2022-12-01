package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import entity.Sach;

public interface SachDao extends Remote {
	public boolean addSach(Sach Sach) throws RemoteException;
	public List<Sach> getSachTheoMaLoai(ObjectId maLoai) throws RemoteException;
	public Sach getSachTheoTenVaMaLoai(String tenSach,ObjectId maLoai) throws RemoteException;
	public List<Sach> getAllSach ()throws RemoteException;
	public boolean updateSach(Sach Sach) throws RemoteException;
	public List<Sach> timkiemSach(String key) throws RemoteException;

	
	
	
}
