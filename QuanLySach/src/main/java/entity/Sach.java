package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bson.types.ObjectId;
import org.hibernate.ogm.options.shared.IndexOption;
import org.hibernate.ogm.options.shared.IndexOptions;


@Entity
@Table(name = "dsSach",indexes = {
		@Index(columnList = "tenSach",name="tenSach_Indexes")
		
})
@IndexOptions(
	    @IndexOption(forIndex = "tenSach_Indexes", options = "{ text: true}"))

public class Sach implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2101165558644982762L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ObjectId id;
	private String tenSach;
	private double donGia;
	private int soLuongTon;
	private Date ngayXB;
	private String trangThaiSach;
	private String author;
	private String manufacturer;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LoaiSach_Id")
	private LoaiSach loaiSach;
	
	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public Date getNgayXB() {
		return ngayXB;
	}

	public void setNgayXB(Date ngayXB) {
		this.ngayXB = ngayXB;
	}

	public String getTrangThaiSach() {
		return trangThaiSach;
	}

	public void setTrangThaiSach(String trangThaiSach) {
		this.trangThaiSach = trangThaiSach;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LoaiSach getLoaiSach() {
		return loaiSach;
	}

	public void setLoaiSach(LoaiSach loaiSach) {
		this.loaiSach = loaiSach;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ObjectId getId() {
		return id;
	}
	
	public Sach(ObjectId id, String tenSach, double donGia, int soLuongTon, Date ngayXB, String trangThaiSach,
			String author, String manufacturer, LoaiSach loaiSach) {
		super();
		this.id = id;
		this.tenSach = tenSach;
		this.donGia = donGia;
		this.soLuongTon = soLuongTon;
		this.ngayXB = ngayXB;
		this.trangThaiSach = trangThaiSach;
		this.author = author;
		this.manufacturer = manufacturer;
		this.loaiSach = loaiSach;
	}
	
	public Sach(String tenSach, double donGia, int soLuongTon, Date ngayXB, String trangThaiSach, String author,
			String manufacturer, LoaiSach loaiSach) {
		super();
		this.tenSach = tenSach;
		this.donGia = donGia;
		this.soLuongTon = soLuongTon;
		this.ngayXB = ngayXB;
		this.trangThaiSach = trangThaiSach;
		this.author = author;
		this.manufacturer = manufacturer;
		this.loaiSach = loaiSach;
	}

	public Sach() {
		super();
	}

	@Override
	public String toString() {
		return "Sach [id=" + id + ", tenSach=" + tenSach + ", donGia=" + donGia + ", soLuongTon=" + soLuongTon
				+ ", ngayXB=" + ngayXB + ", trangThaiSach=" + trangThaiSach + ", author=" + author + ", manufacturer="
				+ manufacturer + ", loaiSach=" + loaiSach + "]";
	}
	
}
