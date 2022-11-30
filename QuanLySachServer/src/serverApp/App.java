package serverApp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.NamingException;

import dao.LoaiSachDao;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import dao.SachDao;
import daoImpl.ImplLoaiSach;
import daoImpl.ImplNhanVien;
import daoImpl.ImplTaiKhoan;
import daoImpl.ImplSach;



public class App {
	public static void main(String[] args) throws RemoteException, NamingException {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy","rmi/quanLyBanSach.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
		
		
		try {
			LocateRegistry.createRegistry(9999);
			LoaiSachDao loaiSachDao = new ImplLoaiSach();
			NhanVienDao nhanVienDao = new ImplNhanVien();
			TaiKhoanDao taiKhoanDao = new ImplTaiKhoan();
			SachDao sachDao = new ImplSach();
			

//			String ip ="";
//			try {
//				ip = InetAddress.getLocalHost().getHostAddress();
//			} catch (UnknownHostException e1) {
//				e1.printStackTrace();
//			}

			Naming.bind("rmi://localhost:9999/loaiSachDao", loaiSachDao);
			Naming.bind("rmi://localhost:9999/nhanVienDao", nhanVienDao);
			Naming.bind("rmi://localhost:9999/taiKhoanDao", taiKhoanDao);
			Naming.bind("rmi://localhost:9999/sachDao", sachDao);

			System.out.println("Ready...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
