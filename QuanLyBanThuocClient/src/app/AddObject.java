package app;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
//import java.util.List;

import dao.LoaiSachDao;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import dao.SachDao;
import entity.LoaiSach;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.Sach;

public class AddObject {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy","rmi/quanLyBanSach.policy");
			System.setSecurityManager(new SecurityManager());
		}
//		String ip ="";
//		try {
//			ip = InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e1) {
//			e1.printStackTrace();
//		}

		LoaiSachDao loaiSachDao =  (LoaiSachDao) Naming.lookup("rmi://localhost:9999/loaiSachDao");
		NhanVienDao nhanVienDao =  (NhanVienDao) Naming.lookup("rmi://localhost:9999/nhanVienDao");
		TaiKhoanDao tkDao =  (TaiKhoanDao) Naming.lookup("rmi://localhost:9999/taiKhoanDao");
		SachDao SachDao =  (SachDao) Naming.lookup("rmi://localhost:9999/sachDao");
	
		NhanVien nv1 = new NhanVien("NV1", "Phạm Vũ Hoài An", "Nữ", new Date(2000-1900,4-1,20), "0966105479", "60 Thống Nhất, p10, Q.Gò Vấp","Quản lý", 5000000, "Đang làm việc", new TaiKhoan("NV1", "123"));
		nhanVienDao.addNhanVien(nv1);
		NhanVien nv2 = new NhanVien("NV2", "Nguyễn Tuấn Thanh", "Nam", new Date(2001-1900,5-1,20), "0374779028", "56 Đường số 4, p Hiệp Bình Phước, Q.Thủ Đức","Nhân viên bán hàng", 5000000, "Đang làm việc", new TaiKhoan("NV2", "123"));
		nhanVienDao.addNhanVien(nv2);
		
		NhanVien nv3 = new NhanVien("NV3", "Phạm Thị Hoa", "Nữ", new Date(2000-1900,6-1,20), "0966712345", "170 Thống Nhất, p10, Q.Gò Vấp","Nhân viên bán hàng", 5000000, "Đã nghỉ việc", new TaiKhoan("NV3", "123"));
		nhanVienDao.addNhanVien(nv3);

//		//loaiSach

		LoaiSach t1=new LoaiSach("Sách Truyện, tiểu thuyết");
		LoaiSach t2=new LoaiSach("Sách Giáo trình");
		LoaiSach t3=new LoaiSach("Sách Văn học nghệ thuật");
		LoaiSach t4=new LoaiSach("Sách Tâm lý, tâm linh, tôn giáo");
		LoaiSach t5=new LoaiSach("Sách Khoa học công nghệ – Kinh tế");
		LoaiSach t6=new LoaiSach("Sách Chính trị – pháp luật");
		LoaiSach t7=new LoaiSach("Sách Văn hóa xã hội – Lịch sử");
		LoaiSach t8=new LoaiSach("Sách thiếu nhi");

//		//Sach
		Sach s1 = new Sach("Có Một Ngày, Bố Mẹ Sẽ Già Đi", 75000.00, 500, new Date(2020-9-9),"Còn bán", "Nhiều tác giả", "NXB Thế Giới", t1);
		Sach s2 = new Sach("Giáo trình Hình học vi phân", 85000.00, 30, new Date(2020-06-02),"Còn bán","Nguyễn Doãn Tuấn", "NXB Đại học sư phạm", t2);
		Sach s3 = new Sach("HẸN GẶP NHAU TRONG VŨ TRỤ", 33000.00, 130, new Date(2021-01-9),"Còn bán","Jack Cheng", "NXB Trẻ", t3);
		SachDao.addSach(s1);
		SachDao.addSach(s2);
		SachDao.addSach(s3);
		
		loaiSachDao.addLoaiSach(t4);
		loaiSachDao.addLoaiSach(t5);
		loaiSachDao.addLoaiSach(t6);
		loaiSachDao.addLoaiSach(t7);
		loaiSachDao.addLoaiSach(t8);	
	}
}
