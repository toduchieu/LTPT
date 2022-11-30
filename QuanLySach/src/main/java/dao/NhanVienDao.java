package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;

public interface NhanVienDao extends Remote {
	public boolean addNhanVien(NhanVien nhanVien) throws RemoteException;

	public List<NhanVien> getDSNhanVien() throws RemoteException;
	public int soNhanVien() throws RemoteException;
	public boolean updateNhanVien(NhanVien nhanVien) throws RemoteException;
	public NhanVien getNhanVienTheoSoNV(String maNV) throws RemoteException;
	public List<NhanVien> getTim(String text) throws RemoteException;
	public NhanVien getNVTheoSDT(String sdt) throws RemoteException;
	
}
