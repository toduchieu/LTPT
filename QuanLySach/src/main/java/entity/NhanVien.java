package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bson.types.ObjectId;
import org.hibernate.ogm.options.shared.IndexOption;
import org.hibernate.ogm.options.shared.IndexOptions;

@Entity
@Table(name = "dsNhanVien",indexes = {
		@Index(columnList = "sdt,ten_Nhan_Vien",name="sdt_Ten_Indexes"),
		
})
@IndexOptions(
	    @IndexOption(forIndex = "sdt_Ten_Indexes", options = "{text: true}"))

public class NhanVien implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6735862733378529418L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ObjectId id;
	@Column(name = "so_NhanVien")
	private String maNhanVien;
	@Column(name = "ten_Nhan_Vien")
	private String tenNhanVien;
	@Column(name = "gioi_Tinh")
	private String gioiTinh;
	@Column(name = "ngay_Sinh")
	private Date ngaySinh;
	private String sdt;
	@Column(name = "dia_Chi")
	private String diaChi;
	private String chucVu;
	private double luong;
	@Column(name = "trang_Thai_Lam_Viec")
	private String trangThaiLamViec;
	
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "taiKhoan_Id")
	private TaiKhoan taiKhoan;

	
	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public String getTrangThaiLamViec() {
		return trangThaiLamViec;
	}

	public void setTrangThaiLamViec(String trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public ObjectId getId() {
		return id;
	}

	

	public NhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, Date ngaySinh, String sdt, String diaChi,
			String chucVu, double luong, String trangThaiLamViec, TaiKhoan taiKhoan) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.luong = luong;
		this.trangThaiLamViec = trangThaiLamViec;
		this.taiKhoan = taiKhoan;
	}

	public NhanVien(ObjectId id, String maNhanVien, String tenNhanVien, String gioiTinh, Date ngaySinh, String sdt,
			String diaChi, String chucVu, double luong, String trangThaiLamViec, TaiKhoan taiKhoan) {
		super();
		this.id = id;
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.luong = luong;
		this.trangThaiLamViec = trangThaiLamViec;
		this.taiKhoan = taiKhoan;
	}

	public NhanVien() {
		super();
	}

	@Override
	public String toString() {
		return "NhanVien [id=" + id + ", maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinh="
				+ gioiTinh + ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + ", diaChi=" + diaChi + ", luong=" + luong
				+ ", trangThaiLamViec=" + trangThaiLamViec + "]";
	}
	
	

}
