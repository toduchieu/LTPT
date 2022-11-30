package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.LoaiSach;

public interface LoaiSachDao extends Remote {
	public boolean addLoaiSach(LoaiSach loaiSach) throws RemoteException;
	public List<LoaiSach>	 getAllLoaiSach() throws RemoteException;
	public LoaiSach getLoaiSachTheoTen(String tenLoai) throws RemoteException;

	
}
