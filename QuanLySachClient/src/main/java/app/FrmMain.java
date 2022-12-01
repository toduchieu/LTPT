package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmMain extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7754060787865635332L;
	private JLabel jlbgetngay;
	FrmQuanLySach QuanLySach=new FrmQuanLySach();
	FrmQuanLySach frmQuanLyKhachHang=new FrmQuanLySach();
	FrmQuanLyNhanVien frmQuanLyNhanVien=new FrmQuanLyNhanVien();
	
	private JButton btnquanlynhanvien;
	private JTabbedPane tabbedPane1;
	private FrmQuanLySach quanLySach;
	

	

	/**
	 * Initialize the contents of the frame.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public FrmMain(String manv,String chucvu) throws MalformedURLException, RemoteException, NotBoundException {
	
		setResizable(false);
		setBounds(0, 0, 1285, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Nhà Sách T3");
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBounds(0, 0, 254, 671);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		JLabel jlbmenu = new JLabel("MENU");
		jlbmenu.setBounds(60, 11, 130, 60);
		jlbmenu.setFont(new Font("Tahoma", Font.PLAIN, 49));
		jlbmenu.setForeground(new Color(255, 255, 204));
		panel_1.add(jlbmenu);
		
		JButton btnQLSach = new JButton("Quản lý Sách");
		btnQLSach.setToolTipText("Giao diện quản lý Sách");
		btnQLSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane1.setSelectedIndex(0);
			}
		});
		btnQLSach.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnQLSach.setBackground(new Color(51, 204, 204));
		btnQLSach.setForeground(new Color(255, 255, 255));
		btnQLSach.setBounds(0, 92, 254, 80);
		panel_1.add(btnQLSach);
		
		JButton btnHoaDon = new JButton("Quản lý nhân viên");
		btnHoaDon.setToolTipText("Giao diện quản lý nhân viên");
		btnHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane1.setSelectedIndex(1);
			}
		});
		
		btnHoaDon.setForeground(new Color(255, 255, 255));
		btnHoaDon.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnHoaDon.setBackground(new Color(51, 204, 204));
		btnHoaDon.setBounds(0, 173, 254, 80);
		panel_1.add(btnHoaDon);
		
		btnquanlynhanvien = new JButton("Quản lý bán hàng");
		btnquanlynhanvien.setToolTipText("Giao diện quản lý bán hàng");
		btnquanlynhanvien.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnquanlynhanvien.setForeground(new Color(255, 255, 255));
		btnquanlynhanvien.setBackground(new Color(51, 204, 204));
		btnquanlynhanvien.setBounds(0, 254, 254, 80);
		panel_1.add(btnquanlynhanvien);
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setToolTipText("Trở lại giao diện đăng nhập");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					FrmDangNhap frmDangNhap=new FrmDangNhap();
					frmDangNhap.setVisible(true);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDangXuat.setForeground(new Color(255, 255, 255));
		btnDangXuat.setBackground(new Color(51, 204, 204));
		btnDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnDangXuat.setBounds(0, 600, 254, 66);
		panel_1.add(btnDangXuat);
		
		JLabel jlbmnv = new JLabel("Mã nhân viên:");
		jlbmnv.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jlbmnv.setForeground(new Color(255, 255, 255));
		jlbmnv.setBounds(10, 556, 121, 33);
		panel_1.add(jlbmnv);
		
		JLabel jlbgetmanv = new JLabel(manv);
		jlbgetmanv.setForeground(new Color(255, 255, 255));
		jlbgetmanv.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jlbgetmanv.setBounds(141, 565, 113, 14);
		panel_1.add(jlbgetmanv);
		
		JLabel jlbngay = new JLabel("Ngày:");
		jlbngay.setForeground(new Color(255, 255, 255));
		jlbngay.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jlbngay.setBounds(10, 520, 59, 23);
		panel_1.add(jlbngay);
		
		jlbgetngay = new JLabel("");
		jlbgetngay.setForeground(new Color(255, 255, 255));
		jlbgetngay.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jlbgetngay.setBounds(70, 520, 184, 27);
		panel_1.add(jlbgetngay);
		
		 tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.setBounds(253, -49, 1026, 724);
		panel.add(tabbedPane1);
		
		quanLySach = new FrmQuanLySach();
		tabbedPane1.addTab("New tab", null, quanLySach, null);

		
		tabbedPane1.addTab("New tab", null,frmQuanLyNhanVien , null);
		
		if(chucvu.equalsIgnoreCase("Nhân viên bán hàng")) {
			remove(btnquanlynhanvien);
		}
		getTime();
		
		btnquanlynhanvien.addActionListener(this);
	
		IconFontSwing.register(FontAwesome.getIconFont());
		Icon icSL = IconFontSwing.buildIcon(FontAwesome.BOOK, 50, new Color(91, 155, 213));
		Icon icQLBH = IconFontSwing.buildIcon(FontAwesome.USERS, 50, new Color(0, 176, 80));
		Icon icNV = IconFontSwing.buildIcon(FontAwesome.CALCULATOR, 50, Color.orange);
		
		Icon icDX = IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 30, Color.red);
		
		Icon icBarchar = IconFontSwing.buildIcon(FontAwesome.BAR_CHART, 40, Color.red);
		Icon icLogo = IconFontSwing.buildIcon(FontAwesome.HOSPITAL_O, 17, Color.orange);
		ImageIcon imgImage = (ImageIcon) icLogo;
		setIconImage(imgImage.getImage());
		btnQLSach.setIcon(icSL);
		btnHoaDon.setIcon(icQLBH);
		btnquanlynhanvien.setIcon(icNV);
		btnDangXuat.setIcon(icDX);
		
		
	}
	

	public void getTime() {
		LocalDate now=LocalDate.now();
		String s= now.toString();
		jlbgetngay.setText(s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnquanlynhanvien)) {
			tabbedPane1.setSelectedIndex(2);
		

		}
	
	}
}