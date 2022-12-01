package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView.TableCell;

import org.bson.types.ObjectId;

import com.toedter.calendar.JDateChooser;

import dao.LoaiSachDao;
import dao.SachDao;
import entity.LoaiSach;
import entity.Sach;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmQuanLySach extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1486775462401966507L;
	private JFrame frame;
	private JTextField txttimkiemsach;
	private JTable tbl;
	private JTextField txttensach;
	private JTextField txtsoluong;
	private JTextField txtdongia;
	private JTextField txttacgia;
	private JTextField txtnxb;
	private JPanel p;
	private JButton btnthem;
	private JButton btnxoa;
	private JButton btnsua;
	private JButton btnlammoi;
	private DefaultTableModel modelsach;
	private JDateChooser datengaysx;
	private JButton btntim;

	private LoaiSachDao loaiSachDao;

	private SachDao sachDao;

	private JComboBox<String> cboloaisach;

	private LoaiSach loaisach3=null;
//	private NuocSX nuocSX3=null;
//	private NhaCungCap nhaCungCap2=null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws NotBoundException
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws HeadlessException
	 */
	public FrmQuanLySach() throws HeadlessException, MalformedURLException, RemoteException, NotBoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NotBoundException
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws HeadlessException
	 */
	private void initialize() throws HeadlessException, MalformedURLException, RemoteException, NotBoundException {
		frame = new JFrame();
		frame.setBounds(0, 0, 1031, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
	
		setLayout(null);
		
//		String ip ="";
//		try {
//			ip = InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e1) {
//			e1.printStackTrace();
//		}
		
		loaiSachDao =  (LoaiSachDao) Naming.lookup("rmi://localhost:9999/loaiSachDao");
		sachDao =  (SachDao) Naming.lookup("rmi://localhost:9999/sachDao");

		p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setBounds(0, 0, 1370, 700);
		add(p);
		p.setLayout(null);

		btntim = new JButton("Tìm");
		btntim.setToolTipText("Tìm sách theo tên");
		btntim.setFont(new Font("SansSerif", Font.BOLD, 15));
		btntim.setBackground(new Color(41, 242, 255));
		btntim.setBounds(893, 42, 107, 33);
		p.add(btntim);

		btnthem = new JButton("Thêm");
		btnthem.setToolTipText("Thêm sách mới");
		btnthem.setBackground(Color.CYAN);
		btnthem.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnthem.setForeground(Color.BLACK);
		btnthem.setBounds(143, 229, 172, 33);
		p.add(btnthem);

		btnxoa = new JButton("Xóa");
		btnxoa.setToolTipText("Xóa sách");
		btnxoa.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnxoa.setBackground(Color.CYAN);
		btnxoa.setBounds(325, 229, 172, 33);
		p.add(btnxoa);

		btnsua = new JButton("Sửa");
		btnsua.setToolTipText("Sửa thông tin sách");
		btnsua.setBackground(Color.CYAN);
		btnsua.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnsua.setBounds(507, 229, 172, 33);
		p.add(btnsua);

		btnlammoi = new JButton("Làm mới");
		btnlammoi.setToolTipText("Làm mới thông tin như ban đầu");
		btnlammoi.setBackground(Color.CYAN);
		btnlammoi.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnlammoi.setBounds(690, 229, 172, 33);
		p.add(btnlammoi);

		JLabel lbltimsach = new JLabel("Tìm kiếm:");
		lbltimsach.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbltimsach.setBounds(552, 41, 80, 32);
		p.add(lbltimsach);

		JLabel lbltieudequanlysach = new JLabel("Quản lý sách");
		lbltieudequanlysach.setFont(new Font("SansSerif", Font.BOLD, 25));
		lbltieudequanlysach.setBounds(10, 29, 178, 48);
		p.add(lbltieudequanlysach);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 273, 977, 397);
		String column[] = { "Tên sách", "Loại sách", "Ngày XB", "Đơn giá", "Số lượng", "Tác giả", "NXB"};
		modelsach = new DefaultTableModel(column, 0);

		JLabel lbltenthuong = new JLabel("Tên sách:");
		lbltenthuong.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbltenthuong.setBounds(31, 88, 107, 32);
		p.add(lbltenthuong);

		JLabel lblloaisach = new JLabel("Loại sách:");
		lblloaisach.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblloaisach.setBounds(359, 88, 114, 32);
		p.add(lblloaisach);

		JLabel lblsoluong = new JLabel("Số lượng:");
		lblsoluong.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblsoluong.setBounds(720, 87, 120, 26);
		p.add(lblsoluong);

		JLabel lbldongia = new JLabel("Đơn giá:");
		lbldongia.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbldongia.setBounds(31, 131, 93, 32);
		p.add(lbldongia);

		JLabel lbltenncc = new JLabel("Tác giả:");
		lbltenncc.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbltenncc.setBounds(31, 174, 102, 27);
		p.add(lbltenncc);

		JLabel lblngaysx = new JLabel("Ngày XB:");
		lblngaysx.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblngaysx.setBounds(359, 131, 93, 32);
		p.add(lblngaysx);

		JLabel lbldiachi = new JLabel("NXB:");
		lbldiachi.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbldiachi.setBounds(359, 171, 80, 32);
		p.add(lbldiachi);


		datengaysx = new JDateChooser();
		datengaysx.setBounds(473, 131, 190, 32);
		datengaysx.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datengaysx.setBorder(new LineBorder(new Color(91, 155, 213)));
		datengaysx.setDateFormatString("dd/MM/yyyy");
		p.add(datengaysx);

		txttensach = new JTextField();
		txttensach.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txttensach.setBounds(143, 88, 172, 32);
		txttensach.setBorder(new LineBorder(new Color(91, 155, 213)));
		p.add(txttensach);
		txttensach.setColumns(10);

		cboloaisach = new JComboBox<String>();
		cboloaisach.setEditable(true);
		cboloaisach.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboloaisach.setBounds(473, 90, 190, 32);
		cboloaisach.setBorder(new LineBorder(new Color(91, 155, 213)));
		p.add(cboloaisach);

		txtsoluong = new JTextField();
		txtsoluong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtsoluong.setBounds(822, 86, 178, 32);
		txtsoluong.setBorder(new LineBorder(new Color(91, 155, 213)));
		p.add(txtsoluong);
		txtsoluong.setColumns(10);

		txtdongia = new JTextField();
		txtdongia.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtdongia.setBounds(143, 133, 172, 32);
		txtdongia.setBorder(new LineBorder(new Color(91, 155, 213)));
		p.add(txtdongia);
		txtdongia.setColumns(10);

		txttacgia = new JTextField();
		txttacgia.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txttacgia.setEditable(true);
		txttacgia.setBounds(143, 176, 172, 32);
		txttacgia.setBorder(new LineBorder(new Color(91, 155, 213)));
		p.add(txttacgia);

		txtnxb = new JTextField();
		txtnxb.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtnxb.setBounds(474, 173, 189, 32);
		txtnxb.setBorder(new LineBorder(new Color(91, 155, 213)));
		p.add(txtnxb);
		txtnxb.setColumns(10);


		tbl = new JTable(modelsach);
		tbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tbl.setBackground(Color.WHITE);
		scrollPane.setViewportView(tbl);

		JTableHeader tbHeader = tbl.getTableHeader();
		tbHeader.setBackground(new Color(131, 179, 226));
		tbHeader.setForeground(Color.CYAN);
		tbHeader.setFont(new Font("SansSerif", Font.BOLD, 20));

		tbl.setSelectionBackground(new Color(236, 243, 250));
		tbl.setSelectionForeground(new Color(91, 155, 213));
		tbl.setRowHeight(30);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tbl.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tbl.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tbl.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(200);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(160);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(70);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(60);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(200);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(200);
		
		
		JScrollPane spMatHang = new JScrollPane(tbl);
		spMatHang.setViewportBorder(null);
		spMatHang.setBounds(13, 273, 987, 407);
		spMatHang.setBorder(new LineBorder(new Color(91, 155, 213), 1, true));
		spMatHang.setBackground(Color.white);
		p.add(spMatHang);

		txttimkiemsach = new JTextField();
		txttimkiemsach.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txttimkiemsach.setBounds(642, 41, 228, 32);
		txttimkiemsach.setBorder(new LineBorder(new Color(91, 155, 213)));
		p.add(txttimkiemsach);
		txttimkiemsach.setColumns(10);

		JLabel jlbbg = new JLabel("");
		jlbbg.setIcon(new ImageIcon("data\\img\\bgbutton2.jpg"));
		jlbbg.setBounds(0, 0, 1025, 700);
		//
		btntim.addActionListener(this);
		btnlammoi.addActionListener(this);
		btnsua.addActionListener(this);
		btnthem.addActionListener(this);
		btnxoa.addActionListener(this);
		tbl.addMouseListener(this);
		// loadthongtin
		loaddata();
		loadloaisachcbo();
		//loadtenncccbo();
		//loadnuocsxcbo();
		//
		IconFontSwing.register(FontAwesome.getIconFont());
		Icon icThem = IconFontSwing.buildIcon(FontAwesome.PLUS, 20, new Color(0, 176, 80));
		Icon icTim = IconFontSwing.buildIcon(FontAwesome.SEARCH, 20, Color.black);
		Icon icLamMoi = IconFontSwing.buildIcon(FontAwesome.REFRESH, 20, Color.blue);
		Icon icXoa = IconFontSwing.buildIcon(FontAwesome.TIMES, 20, Color.red);
		Icon icSua = IconFontSwing.buildIcon(FontAwesome.WRENCH, 20, Color.darkGray);
		btnthem.setIcon(icThem);
		btnsua.setIcon(icSua);
		btnxoa.setIcon(icXoa);
		btntim.setIcon(icTim);
		btnlammoi.setIcon(icLamMoi);
		p.add(jlbbg);

	}

	private void loaddata() throws RemoteException {
		DecimalFormat df = new DecimalFormat("###,###,###.####");
		DateFormat dfd = new SimpleDateFormat("dd/MM/yyyy");
		List<Sach> dssach = sachDao.getAllSach();
		
		for (Sach t : dssach) {
			modelsach.addRow(new Object[] { t.getTenSach(), t.getLoaiSach().getTenLoai(), dfd.format(t.getNgayXB()),
					df.format(t.getDonGia()), t.getSoLuongTon(), t.getAuthor(), t.getManufacturer() });
		}
	}
	

	private void loadloaisachcbo() throws RemoteException {
		List<LoaiSach> loaisachs = loaiSachDao.getAllLoaiSach();
		for (LoaiSach lt : loaisachs) {
			cboloaisach.addItem(lt.getTenLoai());
		}
	}


	private void lammoi() throws RemoteException {
		modelsach.getDataVector().removeAllElements();
		loaddata();
	}

	private Sach reversach() throws RemoteException {
		String tensach = txttensach.getText().trim();
		String loaisach = cboloaisach.getSelectedItem().toString();
		Date ngaysx = (Date) datengaysx.getDate();
		double dongia = Double.parseDouble(txtdongia.getText());
		int soluong = Integer.parseInt(txtsoluong.getText());
		String trangthai = "Còn bán";
		String tacgia = txttacgia.getText().trim();
		String nxb = txtnxb.getText().trim();
		LoaiSach loaisach2=loaiSachDao.getLoaiSachTheoTen(loaisach);
		if(loaisach.equals(loaisach2.getTenLoai())) {
			 loaisach3 =new LoaiSach(loaisach2.getId(), loaisach2.getTenLoai());
		}else {
			 loaisach3=new LoaiSach(loaisach);
		}

		Sach sach = new Sach(tensach, dongia, soluong, ngaysx, trangthai, tacgia, nxb, loaisach3);
		
		return sach;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl.getSelectedRow();
		txttensach.setText(modelsach.getValueAt(row, 0).toString());
		cboloaisach.setSelectedItem(modelsach.getValueAt(row, 1));
		datengaysx.setDate(new Date(modelsach.getValueAt(row, 2).toString()));
		txtdongia.setText(modelsach.getValueAt(row, 3).toString().replaceAll("[-+.^:,]", ""));
		txtsoluong.setText(modelsach.getValueAt(row, 4).toString());
		txttacgia.setText(modelsach.getValueAt(row, 5).toString());
		txtnxb.setText(modelsach.getValueAt(row, 6).toString());
//		cbonuocsx.setSelectedItem(modelsach.getValueAt(row, 8).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnthem)) {
			if (txtdongia.getText().equals("") || txtsoluong.getText().equals("")
					|| txttensach.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin đầy đủ");
			} else {
				try {
					if (ktThongTinsach()) {
						Sach sach = null;
						try {
							sach = reversach();
						} catch (RemoteException e2) {
							e2.printStackTrace();
						}
						try {
							sachDao.addSach(sach);
							lammoi();
							JOptionPane.showMessageDialog(null, "Thêm thành công");
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnlammoi)) {

			txtdongia.setText("");
			txtsoluong.setText("");
			txttensach.setText("");
			txttimkiemsach.setText("");
			cboloaisach.setSelectedIndex(0);
			try {
				lammoi();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
		}
		if (o.equals(btnsua)) {
			int row = tbl.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin cần sửa");
			} else {
				int n = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa dữ liệu này không ?", null,
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					try {
						ObjectId id = loaiSachDao.getLoaiSachTheoTen(modelsach.getValueAt(row, 1).toString())
								.getId();
						Sach sach = sachDao.getSachTheoTenVaMaLoai(modelsach.getValueAt(row, 0).toString(), id);

						LoaiSach lt = loaiSachDao.getLoaiSachTheoTen(cboloaisach.getSelectedItem().toString());
						sach.setLoaiSach(lt);

						sach.setAuthor(txttacgia.getText());
						sach.setManufacturer(txtnxb.getText());
						sach.setDonGia(Double.parseDouble(txtdongia.getText()));
						sach.setNgayXB(datengaysx.getDate());
						sach.setTenSach(txttensach.getText());
						sach.setSoLuongTon(Integer.parseInt(txtsoluong.getText()));
						Sach sach2 = sach;
						sachDao.updateSach(sach2);
						JOptionPane.showMessageDialog(null, "Sửa thành công");
						lammoi();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		}
		if (o.equals(btntim)) {
			String key = txttimkiemsach.getText();
			DecimalFormat df = new DecimalFormat("###,###,###.####");
			DateFormat dfd = new SimpleDateFormat("yyyy/MM/dd");
			List<Sach> dssach = null;
			try {
				dssach = sachDao.timkiemSach(key);
				if(dssach.size()>0) {
				modelsach.getDataVector().removeAllElements();
				for (Sach t : dssach) {
					modelsach.addRow(
						new Object[] { t.getTenSach(), t.getLoaiSach().getTenLoai(), dfd.format(t.getNgayXB()),
							df.format(t.getDonGia()), t.getSoLuongTon(), t.getAuthor(), t.getManufacturer() });
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy sách");
					txttimkiemsach.requestFocus();
					
				}
	
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (o.equals(btnxoa)) {
			int row = tbl.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin cần xóa");
			} else {
				int n = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dữ liệu này không ?", null,
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					try {
						ObjectId id = loaiSachDao.getLoaiSachTheoTen(modelsach.getValueAt(row, 1).toString())
								.getId();
						Sach sach = sachDao.getSachTheoTenVaMaLoai(modelsach.getValueAt(row, 0).toString(), id);
						sach.setTrangThaiSach("Ngưng kinh doanh");
						Sach sach2 = sach;
						sachDao.updateSach(sach2);
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						lammoi();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		}

	}

	public boolean ktThongTinsach() throws ParseException {
		String tensach = txttensach.getText();
		String soluong = txtsoluong.getText();
		String dongia = txtdongia.getText();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date ngaysx = new SimpleDateFormat("dd/MM/yyyy").parse(df.format(datengaysx.getDate()));

		if (tensach.length() > 0) {
			String regex = "^([\\.\\(\\)0-9 A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷýỹ]*(\\s?))+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(tensach);
			if (!matcher.find()) {
				JOptionPane.showMessageDialog(null, "Tên sách không hợp lệ");
				txttensach.requestFocus();
				txttensach.selectAll();
				return false;
			}
		}
		if (soluong.length() > 0) {
			String regexs = "^[0-9]+$";
			if (!soluong.matches(regexs)) {
				JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên và lớn hơn 0 (ví dụ nhập: 1000)");
				txtsoluong.requestFocus();
				txtsoluong.selectAll();
				return false;
			}
		}
		if (dongia.length() > 0) {
			String regexs = "^[\\.0-9]+$";
			if (!dongia.matches(regexs)) {
				JOptionPane.showMessageDialog(this, "Giá bán phải là số ");
				txtdongia.requestFocus();
				txtdongia.selectAll();
				return false;
			}
		}
		if (dongia.length() > 0) {
			double giaban2 = Double.parseDouble(dongia);
			if (giaban2 <= 0) {
				JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0");
				txtdongia.requestFocus();
				txtdongia.selectAll();
				return false;
			}
		}
		return true;

	}
}