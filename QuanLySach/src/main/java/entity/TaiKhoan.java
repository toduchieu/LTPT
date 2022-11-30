package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.bson.types.ObjectId;

@Entity
@Table(name = "dsTaiKhoan")
public class TaiKhoan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5231787976699307772L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ObjectId id;
	@Column(name = "tai_Khoan")
	private String maTK;
	@Column(name = "mat_Khau")
	private String matKhau;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@JoinColumn(name = "NhanVien_Id")
	private NhanVien nhanVien;

	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public ObjectId getId() {
		return id;
	}

	public TaiKhoan(ObjectId id, String maTK, String matKhau, NhanVien nhanVien) {
		super();
		this.id = id;
		this.maTK = maTK;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}

	public TaiKhoan(String maTK, String matKhau) {
		super();
		this.maTK = maTK;
		this.matKhau = matKhau;
	
	}

	public TaiKhoan() {
		super();
	}

	@Override
	public String toString() {
		return "TaiKhoan [id=" + id + ", maTK=" + maTK + ", matKhau=" + matKhau  + "]";
	}
	
	
	

}
