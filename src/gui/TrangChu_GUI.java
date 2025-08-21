package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import components.ConTent_JPanel;
import components.RoundedButton;
import dao.Ca_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.Ca;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

public class TrangChu_GUI extends JFrame implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel header;
	private JPanel title;
	private JLabel logoLabel;
	private JLabel titleLabel;
	private JPanel jp_menu;
	private JMenuBar menuBar;
	private JMenu khachHang;
	private JMenu ve;
	private JMenuItem qlve;
	private JMenuItem datVe;
	private JMenu hoaDon;
	private JMenuItem qlhd;
	private JMenuItem xemcthd;
	private JMenu traCuuKH;
	private JMenuItem traCuuVCT;
	private JMenuItem traCuuKhachHang;
	private JMenuItem traCuuNV;
	private JMenu thongKe;
	private JMenuItem thongKeDT;
	private JMenuItem thongKeTheoCa;
	private JMenuItem thongKeCT;
	private JMenu nhanVien;
	private JMenu taiKhoan;
	private JPanel jp_nhanVien;
	private JLabel userIconLabel;
	public JLabel lbl_ThongTinNV;
	public JLabel lbl_ThoiGian;
	private JLabel exitIconLabel;
	public JPanel content;
	private Color hoverLabelColor = new Color(0, 153, 255);
	public DangNhap_GUI dangNhap;
	
	private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
	private TaiKhoan_DAO taiKhoan_DAO = new TaiKhoan_DAO();
	private Ca_DAO ca_DAO= new Ca_DAO();
	private JMenu mnTrGip;
	private JButton btn_VaoCa;
	private JButton btn_KetCa;
	public LocalDateTime vaoCa;
	public LocalDateTime ketCa;
	private Boolean click = false;
	private Color hoverColor = new Color(0, 102, 204); // Màu khi di chuột qua
	private Color defaultColor = SystemColor.menu; // Màu mặc định
	
	private JPanel contentPane1;
	private JLabel lb_TenNV,lb_MaNV,lb_NgaySinh,lb_CCCD,lb_Email,lb_SDT,lb_ChucVu,userIconLabel1;
	protected JDialog dialog;
	
	public TrangChu_GUI(DangNhap_GUI dangNhap) {
		this.dangNhap = dangNhap;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Ngăn đóng cửa sổ trực tiếp
		setBounds(100, 100, 1480, 810);
		setLocationRelativeTo(null);
		setTitle("Quản lý nhà ga ĐTHP");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Thêm WindowListener để kiểm tra trước khi đóng
        addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	if(click) {
	    			int confirm = JOptionPane.showConfirmDialog(
	    					null, 
	    					"Ban xác nhận kết thúc ca làm ?", 
	    					"Xác nhận", 
	    					JOptionPane.YES_NO_OPTION
	    					);

	    			// Xóa file nếu người dùng chọn "Yes"
	    			if (confirm == JOptionPane.YES_OPTION) {
	    				LocalDateTime currentTime = LocalDateTime.now();
	    				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    				String time= currentTime.format(formatter);
	    				ketCa= LocalDateTime.now();
	    				JOptionPane.showMessageDialog(null, "Kết thúc ca làm "+time, "Thông báo",confirm);
	    				System.exit(0);
	    			}else {
	    				return;
	    			}
	    		}else {
                    System.exit(0); // Thoát bình thường nếu ca đã kết thúc
                }
            }
        });
		
		header = new JPanel();
		header.setBounds(0, 0, 1470, 200);
		contentPane.add(header);
		header.setLayout(null);
		
		title = new JPanel();
		title.setBounds(0, 0, 1204, 200);
		title.setForeground(new Color(255, 0, 0));
		title.setBackground(new Color(51, 102, 153));
		header.add(title);
		title.setLayout(null);
		
		//Logo chương trình
		ImageIcon originalLogo = new ImageIcon(getClass().getResource("/img/LogoDepHonTrang.png"));
	    Image scaledLogo = originalLogo.getImage().getScaledInstance(300, 120, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
	    logoLabel = new JLabel(new ImageIcon(scaledLogo));
	    logoLabel.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
	    		logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logoLabel.setCursor(Cursor.getDefaultCursor());
			}

	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		ConTent_JPanel jpct = new ConTent_JPanel();
	    		content.removeAll();
	    		content.add(jpct);
				content.revalidate();
				content.repaint();
	    	}
	    });
	    logoLabel.setBounds(18, 18, 300, 108); // Cập nhật kích thước trên JLabel
	    title.add(logoLabel);
	    
	    // Tên Chương trình
	    titleLabel = new JLabel("Nhà ga ĐTHP");
	    titleLabel.setForeground(SystemColor.text);
	    titleLabel.setBounds(328, 0, 876, 145);
	    titleLabel.setHorizontalAlignment(JLabel.CENTER);
	    title.add(titleLabel);
	    
	    // Thiết lập font cho titleLabel
	    try {
	        InputStream fontStream = getClass().getResourceAsStream("/font/Italianno-Regular.tff");
	        if (fontStream != null) {
	            Font italiannoFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(100f);
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(italiannoFont);
	            
	            // Áp dụng font, bóng, và viền
	            applyTextEffectWithBorder(titleLabel, italiannoFont, Color.WHITE, Color.GRAY, Color.BLACK);
	        } else {
	            System.err.println("Không tìm thấy font: /font/Italianno-Regular.tff");
	        }
	    } catch (FontFormatException | IOException e) {
	        e.printStackTrace();
	    }
	    
		//Menu Chính
		jp_menu = new JPanel();
		jp_menu.setBounds(0, 145, 1204, 55);
		title.add(jp_menu);
		jp_menu.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		menuBar.setBounds(0, 0, 1204, 55);
		jp_menu.add(menuBar);
		
		khachHang = new JMenu("Khách hàng");
		khachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		khachHang.setPreferredSize(new Dimension(150, 30));
		menuBar.add(khachHang);
		
		ve = new JMenu("Vé tàu");
		ve.setBackground(SystemColor.menu);
		ve.setFont(new Font("Segoe UI", Font.BOLD, 20));
		ve.setPreferredSize(new Dimension(135, 30));
		menuBar.add(ve);
		
		qlve = new JMenuItem("Quản lý vé");
		qlve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(click) {
					QuanLyVe_Gui quanLyVe_gui = new QuanLyVe_Gui(TrangChu_GUI.this);
					content.removeAll();
					content.add(quanLyVe_gui); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		ve.add(qlve);
		
		datVe = new JMenuItem("Đặt vé");
		ve.add(datVe);
		
		hoaDon = new JMenu("Hóa đơn");
		hoaDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		hoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		hoaDon.setPreferredSize(new Dimension(146, 30));
		menuBar.add(hoaDon);
		
		qlhd = new JMenuItem("Quản lý hóa đơn");
		qlhd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(click) {
					QuanLyHoaDon_GUI QLHoaDon= new QuanLyHoaDon_GUI(TrangChu_GUI.this);
					content.removeAll();
					content.add(QLHoaDon); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		hoaDon.add(qlhd);
		
		xemcthd = new JMenuItem("Xem chi tiết hóa đơn");
		xemcthd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(click) {
					QuanLyHoaDon_GUI QLHoaDon= new QuanLyHoaDon_GUI(TrangChu_GUI.this);
					ChiTietHoaDon_GUI ChiTietHD= new ChiTietHoaDon_GUI(QLHoaDon,TrangChu_GUI.this);
					content.removeAll();
					content.add(ChiTietHD); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		hoaDon.add(xemcthd);
		
		traCuuKH = new JMenu("Tra cứu");
		traCuuKH.setFont(new Font("Segoe UI", Font.BOLD, 20));
		traCuuKH.setPreferredSize(new Dimension(146, 30));
		menuBar.add(traCuuKH);
		
		traCuuVCT = new JMenuItem("Tra cứu giá vé và chuyến tàu");
		traCuuKH.add(traCuuVCT);
		
		traCuuKhachHang = new JMenuItem("Tra cứu khách hàng");
		traCuuKH.add(traCuuKhachHang);
		
		traCuuNV = new JMenuItem("Tra cứu nhân viên ");
		traCuuKH.add(traCuuNV);
		
		thongKe = new JMenu("Thống kê");
		thongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		thongKe.setPreferredSize(new Dimension(146, 30));
		menuBar.add(thongKe);
		
		thongKeTheoCa = new JMenuItem("Thống kê doanh thu theo ca");
		thongKe.add(thongKeTheoCa);
		
		thongKeDT = new JMenuItem("Thống kê doanh thu");
		thongKe.add(thongKeDT);
		
		thongKeCT = new JMenuItem("Thống Kê Chuyến Tàu");
		thongKe.add(thongKeCT);
		
		nhanVien = new JMenu("Nhân Viên");
		nhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		nhanVien.setPreferredSize(new Dimension(146, 30));
		menuBar.add(nhanVien);
		 //Sự kiện chuyển trang giữa content và quản lý nhân viên
	 
		taiKhoan = new JMenu("Tài khoản");
		taiKhoan.setFont(new Font("Segoe UI", Font.BOLD, 20));
		taiKhoan.setPreferredSize(new Dimension(150, 30));
		menuBar.add(taiKhoan);
		
		mnTrGip = new JMenu("Trợ giúp");
		mnTrGip.setPreferredSize(new Dimension(150, 30));
		mnTrGip.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnTrGip);
		
		jp_nhanVien = new JPanel();
		jp_nhanVien.setBackground(SystemColor.menu);
		jp_nhanVien.setBounds(1203, 0, 267, 200);
		header.add(jp_nhanVien);
		jp_nhanVien.setLayout(null);
		
		ImageIcon userIcon = new ImageIcon(getClass().getResource("/img/user.png"));
	    Image scaledUser = userIcon.getImage().getScaledInstance(73 ,56, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
	    jp_nhanVien.setLayout(null);
	    userIconLabel = new JLabel(new ImageIcon(scaledUser));
	    userIconLabel.setBounds(98 ,10 , 73 ,56); // Cập nhật kích thước trên JLabel
	    userIconLabel.setOpaque(true);
	    userIconLabel.setBackground(defaultColor);
	    jp_nhanVien.add(userIconLabel);
	    
		// Thêm sự kiện cho nút
	    userIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
	    	@Override
	    	public void mouseEntered(java.awt.event.MouseEvent evt) {
	    		userIconLabel.setBackground(hoverColor); // Thay đổi màu nền khi đưa chuột vào
	    		userIconLabel.repaint(); // Vẽ lại nút

	    		// Đổi con trỏ chuột thành kiểu tay chỉ
	    		userIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	}

	    	@Override
	    	public void mouseExited(java.awt.event.MouseEvent evt) {
	    		userIconLabel.setBackground(defaultColor); // Trở về màu mặc định khi chuột ra
	    		userIconLabel.repaint(); // Vẽ lại nút

	    		// Trả lại con trỏ chuột về mặc định
	    		userIconLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    	}
	    });
	    userIconLabel.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	// Tạo một JDialog
	            dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(userIconLabel), "Thông tin tài khoản", true);
	            dialog.setBounds(300, 300, 720, 388); 
	            dialog.setLocationRelativeTo(null);
	            
	            contentPane1 = new JPanel();
	    		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));

	    		setContentPane(contentPane);
	    		contentPane1.setLayout(null);
	    		
	    		ImageIcon userIcon1 = new ImageIcon(getClass().getResource("/img/user.png"));
	    	    Image scaledUser1 = userIcon1.getImage().getScaledInstance(73 ,56, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
	    	    userIconLabel1 = new JLabel(new ImageIcon(scaledUser1));
	    	    userIconLabel1.setBounds(70, 84, 73, 56);
	    		contentPane1.add(userIconLabel1);
	    		
	    		lb_TenNV = new JLabel("");
	    		lb_TenNV.setText(nhanVien_DAO.getNhanVienTheoMaNV((taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan())).getNhanVien().getMaNV()).getTenNV());
	    		lb_TenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lb_TenNV.setBounds(10, 172, 200, 25);
	    		// Căn chỉnh text trong JLabel ở giữa
	    		lb_TenNV.setHorizontalAlignment(SwingConstants.CENTER);
	    		lb_TenNV.setVerticalAlignment(SwingConstants.CENTER);
	    		contentPane1.add(lb_TenNV);
	    		
	    		JLabel lblNewLabel_2 = new JLabel("Mã nhân viên:");
	    		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lblNewLabel_2.setBounds(227, 45, 105, 25);
	    		contentPane1.add(lblNewLabel_2);
	    		
	    		JLabel lblNewLabel_2_1 = new JLabel("Ngày sinh:");
	    		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lblNewLabel_2_1.setBounds(227, 80, 105, 25);
	    		contentPane1.add(lblNewLabel_2_1);
	    		
	    		JLabel lblNewLabel_2_1_1 = new JLabel("CCCD:");
	    		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lblNewLabel_2_1_1.setBounds(227, 115, 105, 25);
	    		contentPane1.add(lblNewLabel_2_1_1);
	    		
	    		JLabel lblNewLabel_2_1_1_1 = new JLabel("Email:");
	    		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lblNewLabel_2_1_1_1.setBounds(227, 150, 105, 25);
	    		contentPane1.add(lblNewLabel_2_1_1_1);
	    		
	    		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Số điện thoại:");
	    		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lblNewLabel_2_1_1_1_1.setBounds(227, 185, 105, 25);
	    		contentPane1.add(lblNewLabel_2_1_1_1_1);
	    		
	    		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Chức vụ:");
	    		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lblNewLabel_2_1_1_1_1_1.setBounds(227, 220, 105, 25);
	    		contentPane1.add(lblNewLabel_2_1_1_1_1_1);
	    		
	    		lb_MaNV = new JLabel();
	    		lb_MaNV.setText(nhanVien_DAO.getNhanVienTheoMaNV((taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan()))
	    				.getNhanVien().getMaNV()).getMaNV());
	    		lb_MaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lb_MaNV.setBounds(362, 45, 200, 25);
	    		contentPane1.add(lb_MaNV);
	    		
	    		lb_NgaySinh = new JLabel();
	    		LocalDate ngaySinh = nhanVien_DAO.getNhanVienTheoMaNV(taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan())
	    				.getNhanVien().getMaNV()).getNgaySinh();
	    		// Định dạng ngày sinh
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    		String ngaySinhFormatted = ngaySinh.format(formatter); // Chuyển LocalDate sang chuỗi theo định dạng
	    		
	    		lb_NgaySinh.setText(ngaySinhFormatted);
	    		lb_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lb_NgaySinh.setBounds(362, 80, 200, 25);
	    		contentPane1.add(lb_NgaySinh);
	    		
	    		lb_CCCD = new JLabel();
	    		lb_CCCD.setText(nhanVien_DAO.getNhanVienTheoMaNV((taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan()))
	    				.getNhanVien().getMaNV()).getCccd());
	    		lb_CCCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lb_CCCD.setBounds(362, 115, 200, 25);
	    		contentPane1.add(lb_CCCD);
	    		
	    		lb_Email = new JLabel();
	    		lb_Email.setText(nhanVien_DAO.getNhanVienTheoMaNV((taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan()))
	    				.getNhanVien().getMaNV()).getEmail());
	    		lb_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lb_Email.setBounds(362, 150, 200, 25);
	    		contentPane1.add(lb_Email);
	    		
	    		lb_SDT = new JLabel();
	    		lb_SDT.setText(nhanVien_DAO.getNhanVienTheoMaNV((taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan()))
	    				.getNhanVien().getMaNV()).getSdt());
	    		lb_SDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lb_SDT.setBounds(362, 185, 200, 25);
	    		contentPane1.add(lb_SDT);
	    		
	    		lb_ChucVu = new JLabel();
	    		int chucVu = nhanVien_DAO.getNhanVienTheoMaNV(taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan())
	    			        .getNhanVien().getMaNV()).getChucVu();
	    		String tenChucVu;
	    		switch (chucVu) {
	    		    case 1:
	    		        tenChucVu = "Quản lý";
	    		        break;
	    		    case 2:
	    		        tenChucVu = "Nhân viên";
	    		        break;
	    		    default:
	    		        tenChucVu = "Không xác định";
	    		}
	    		lb_ChucVu.setText(tenChucVu);
	    		lb_ChucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		lb_ChucVu.setBounds(362, 220, 200, 25);
	    		contentPane1.add(lb_ChucVu);
	    		
	    		btn_VaoCa = new RoundedButton("Vào ca", 10);
	    		btn_VaoCa.setForeground(new Color(255, 255, 255));
	    		btn_VaoCa.setBackground(new Color(51, 102, 153));
	    		btn_VaoCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		btn_VaoCa.setBounds(247, 271, 85, 21);
	    		contentPane1.add(btn_VaoCa);
	    		
	    		btn_VaoCa.addActionListener(new ActionListener() {
	    			
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				// TODO Auto-generated method stub
	    				// Hiển thị hộp thoại xác nhận sau khi mở file
	    				LocalDateTime currentTime = LocalDateTime.now();
	    				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    				String time= currentTime.format(formatter);
	                    int confirm = JOptionPane.showConfirmDialog(
	                        null, 
	                        "Bắt đầu ca làm :" + time, 
	                        "Xác nhận", 
	                        JOptionPane.YES_NO_OPTION
	                    );

	                    // Xóa file nếu người dùng chọn "Yes"
	                    if (confirm == JOptionPane.YES_OPTION) {
	                        if (kiemTraVaoCaLam(lbl_ThongTinNV, currentTime)) {
	                            vaoCa= LocalDateTime.now();;
	                            click = true;
	                            dialog.dispose();
	                        } else {
	                        	JOptionPane.showMessageDialog(null, "Chưa tới thời gian làm viêc", "Thông báo", JOptionPane.WARNING_MESSAGE);
	                        }
	                    }else {
	                    	return;
	                    }
	    			}
	    		});
	    		
	    		btn_KetCa = new RoundedButton("Kết ca", 10);
	    		btn_KetCa.setForeground(new Color(255, 255, 255));
	    		btn_KetCa.setBackground(new Color(51, 102, 153));
	    		btn_KetCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    		btn_KetCa.setBounds(382, 271, 85, 21);
	    		contentPane1.add(btn_KetCa);
	    		
	    		btn_KetCa.addActionListener(new ActionListener() {
	    			
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				// TODO Auto-generated method stub
	    				LocalDateTime currentTime = LocalDateTime.now();
	    				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    				String time= currentTime.format(formatter);
	                    int confirm = JOptionPane.showConfirmDialog(
	                        null, 
	                        "Kết thúc ca làm :" + time, 
	                        "Xác nhận", 
	                        JOptionPane.YES_NO_OPTION
	                    );

	                    // Xóa file nếu người dùng chọn "Yes"
	                    if (confirm == JOptionPane.YES_OPTION) {
	                        if (kiemTraKetCaLam(lbl_ThongTinNV, currentTime)) {
	                        	ketCa= LocalDateTime.now();
	                        	click = false;
	                        	
	                        	 // Quay về trang chủ
	                            dialog.dispose(); // Đóng JDialog hiện tại
	                            ConTent_JPanel jpct= new ConTent_JPanel();
	                            content.removeAll();
	        					content.add(jpct); // Sử dụng layout thích hợp
	        					content.revalidate();
	        					content.repaint();
	                        } else {
	                        	JOptionPane.showMessageDialog(null, "Chưa tới thời gian kết thúc ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE); 
	                        }
	                    }else {
	                    	return;
	                    }
	    			}
	    		});
	    		
	    		 dialog.setContentPane(contentPane1);
	            // Hiển thị dialog
	            dialog.setVisible(true);
	        }
	    });
	    
	    
	    lbl_ThongTinNV = new JLabel();
	    lbl_ThongTinNV.setText(nhanVien_DAO.getNhanVienTheoMaNV((taiKhoan_DAO.getTaiKhoanTheoMaTK(dangNhap.getTaiKhoanLogined().getMaTaiKhoan()))
	    															.getNhanVien().getMaNV()).getTenNV());
	    lbl_ThongTinNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lbl_ThongTinNV.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_ThongTinNV.setBounds(18, 91, 231, 21);
	    jp_nhanVien.add(lbl_ThongTinNV);
	    
	    lbl_ThoiGian = new JLabel();
	    lbl_ThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbl_ThoiGian.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_ThoiGian.setBounds(18, 122, 231, 21);
	    jp_nhanVien.add(lbl_ThoiGian);
	    
		ImageIcon exitIcon = new ImageIcon(getClass().getResource("/img/Exit.png"));
	    Image scaledExit = exitIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
	    exitIconLabel = new JLabel(new ImageIcon(scaledExit));
	    exitIconLabel.setBounds(111 ,153 , 40 ,37); // Cập nhật kích thước trên JLabel
	    jp_nhanVien.add(exitIconLabel);
	    
	    exitIconLabel.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		if(click) {
	    			int confirm = JOptionPane.showConfirmDialog(
	    					null, 
	    					"Ban xác nhận kết thúc ca làm ?", 
	    					"Xác nhận", 
	    					JOptionPane.YES_NO_OPTION
	    					);

	    			// Xóa file nếu người dùng chọn "Yes"
	    			if (confirm == JOptionPane.YES_OPTION) {
	    				LocalDateTime currentTime = LocalDateTime.now();
	    				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    				String time= currentTime.format(formatter);
	    				ketCa= LocalDateTime.now();
	    				JOptionPane.showMessageDialog(null, "Kết thúc ca làm "+time, "Thông báo",confirm); 
	    				DangNhap_GUI dn= new DangNhap_GUI();
	    				TrangChu_GUI.this.setVisible(false);
	    				dn.setVisible(true);
	    			}else {
	    				return;
	    			}
	    		}else {
	    			DangNhap_GUI dn= new DangNhap_GUI();
	    			TrangChu_GUI.this.setVisible(false);
	    			dn.setVisible(true);
	    		}
	    	}
	    });
	    exitIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
	    	@Override
	    	public void mouseEntered(java.awt.event.MouseEvent evt) {
	    		exitIconLabel.setForeground(hoverLabelColor); // Thay đổi màu khi đưa chuột vào

	    		// Đổi con trỏ chuột thành kiểu tay chỉ
	    		exitIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	}

	    	@Override
	    	public void mouseExited(java.awt.event.MouseEvent evt) {
	    		exitIconLabel.setForeground(null); // Trở về màu mặc định khi chuột ra
	    		// Trả lại con trỏ chuột về mặc định
	    		exitIconLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    	}}
	    		);
		content = new JPanel();
		content.setBounds(0, 200, 1470, 575);
		content.setLayout(new GridLayout());
		content.setForeground(new Color(255, 255, 255));
		contentPane.add(content);
		
		ConTent_JPanel jpct = new ConTent_JPanel();
		content.add(jpct);
	    
		traCuuKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(click) {
					TraCuuKhachHang_GUI tckh= new TraCuuKhachHang_GUI(TrangChu_GUI.this);
					content.removeAll();
					content.add(tckh); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		traCuuNV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TraCuuNhanVien_GUI tcnv = new TraCuuNhanVien_GUI(TrangChu_GUI.this);
				content.removeAll();
				content.add(tcnv); // Sử dụng layout thích hợp
				content.revalidate();
				content.repaint();

			}
		});
		traCuuVCT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					TraCuuChuyenTauGiaVe_Gui tcvct = new TraCuuChuyenTauGiaVe_Gui(TrangChu_GUI.this);
					content.removeAll();;
					content.add(tcvct); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
			}
		});
		datVe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(click) {
					BanVe_GUI banVe= new BanVe_GUI(TrangChu_GUI.this);
					content.removeAll();
					content.add(banVe); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		thongKeCT.addActionListener(new ActionListener() {

			private TaiKhoan tk;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tk= dangNhap.taiKhoanLogined;
				if(tk.getPhanQuyen() ==1) {
					ThongKe_GUI jptkct= new ThongKe_GUI(TrangChu_GUI.this);
					jptkct.hienThiThongKeChuyenTau();
					content.removeAll();
					content.add(jptkct); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(TrangChu_GUI.this,"Tài khoản của bạn không có quyền truy cập");
				}
			}
		});
		thongKeDT.addActionListener(new ActionListener() {
			private TaiKhoan tk;
			@Override
			public void actionPerformed(ActionEvent e) {
				tk= dangNhap.taiKhoanLogined;
				// TODO Auto-generated method stub 
				if(tk.getPhanQuyen() == 1) {
					ThongKe_GUI jptkct= new ThongKe_GUI(TrangChu_GUI.this);
					jptkct.hienThiThongKeDoanhThu();;
					content.removeAll();
					content.add(jptkct); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(TrangChu_GUI.this,"Tài khoản của bạn không có quyền truy cập");
				}
			}
		});
	    thongKeTheoCa.addActionListener(new ActionListener() {

	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO Auto-generated method stub
	    		if(click) {
		    		ThongKe_GUI jptkct= new ThongKe_GUI(TrangChu_GUI.this);
		    		jptkct.hienThiThongKeDoanhThuTheoCa();
		    		content.removeAll();
		    		content.add(jptkct); // Sử dụng layout thích hợp
		    		content.revalidate();
		    		content.repaint();
	    		}else {
	    			JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    		}
	    	}
	    });
	    khachHang.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				if(click) {
					QuanLyKhachHang_GUI jpkh= new QuanLyKhachHang_GUI(TrangChu_GUI.this);
					content.removeAll();
					content.add(jpkh); // Sử dụng layout thích hợp
					content.revalidate();
					content.repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}
		});
	    nhanVien.addMenuListener(new MenuListener() {
	    	private TaiKhoan tk;

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
					tk= dangNhap.taiKhoanLogined;
					if(tk.getPhanQuyen() ==1) {
						QuanLyNhanVien_GUI jpnv= new QuanLyNhanVien_GUI(TrangChu_GUI.this);
						content.removeAll();
						content.add(jpnv); // Sử dụng layout thích hợp
						content.revalidate();
						content.repaint();
					}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}
		});
	    
	    taiKhoan.addMenuListener(new MenuListener() {

			private TaiKhoan tk;

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				tk= dangNhap.taiKhoanLogined;
//				if(click == 1) {
					if(tk.getPhanQuyen() ==1) {
						QuanLyTaiKhoan_GUI jptk = new QuanLyTaiKhoan_GUI(TrangChu_GUI.this);
						content.removeAll();
						content.add(jptk); // Sử dụng layout thích hợp
						content.revalidate();
						content.repaint();
					}else {
							JOptionPane.showMessageDialog(TrangChu_GUI.this,"Tài khoản của bạn không có quyền truy cập");
					}
//				}else {
//					JOptionPane.showMessageDialog(null, "Vui lòng nhấn vào ca làm", "Thông báo", JOptionPane.WARNING_MESSAGE);
//				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}
		});
	    
	    mnTrGip.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            OpenHtmlFile(); // Gọi phương thức khi nhấn vào menu
	        }
	    });
	    
	    updateTime(); // hàm lấy thời gian thực
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTime();
			}
		});
		timer.start();
		
	}
	
	//Hàm lấy thời gian thực
	public void updateTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		lbl_ThoiGian.setText(formattedTime);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		
	}
	public boolean kiemTraVaoCaLam(JLabel tenNV, LocalDateTime currentTime) {
	    nhanVien_DAO.reset();
	    ca_DAO.reset();

	    // Lấy phần thời gian (giờ, phút, giây) từ LocalDateTime
	    LocalTime time = currentTime.toLocalTime();
	    String ten = tenNV.getText();

	    // Lấy nhân viên theo tên
	    NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoTenNV(ten);
	    if (nhanVien == null || nhanVien.getCa() == null) {
	        return false; // Nhân viên không tồn tại hoặc không có ca làm
	    }

	    // Lấy thông tin ca làm
	    Ca ca = ca_DAO.getCaTheoMaCa(nhanVien.getCa().getMaCa());
	    if (ca == null) {
	        return false; // Ca làm không tồn tại
	    }

	    // Lấy thời gian bắt đầu và kết thúc của ca
	    LocalTime thoiGianBatDau = ca.getThoiGianBatDau();
	    LocalTime thoiGianKetThuc = ca.getThoiGianKetThuc();

	    // Kiểm tra ca làm qua ngày hay không
	    if (thoiGianKetThuc.isBefore(thoiGianBatDau)) {
	        // Ca làm qua ngày
	        boolean isValid = (time.isAfter(thoiGianBatDau.minusMinutes(5)) && time.isBefore(LocalTime.MAX)) // Trước 23:59:59
	                || (time.isAfter(LocalTime.MIN) && time.isBefore(thoiGianKetThuc.plusMinutes(0))); // Sau 00:00:00
	        return isValid;
	    } else {
	        // Ca làm không qua ngày
	        return time.isAfter(thoiGianBatDau.minusMinutes(5)) && time.isBefore(thoiGianKetThuc.plusMinutes(0));
	    }
	}

	
	public boolean kiemTraKetCaLam(JLabel tenNV, LocalDateTime currentTime) {
	    nhanVien_DAO.reset();
	    ca_DAO.reset();

	    // Lấy phần thời gian (giờ, phút, giây) từ LocalDateTime
	    LocalTime time = currentTime.toLocalTime();
	    String ten = tenNV.getText();

	    // Lấy nhân viên theo tên
	    NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoTenNV(ten);
	    if (nhanVien == null || nhanVien.getCa() == null) {
	        return false; // Nhân viên không tồn tại hoặc không có ca làm
	    }

	    // Lấy thông tin ca làm
	    Ca ca = ca_DAO.getCaTheoMaCa(nhanVien.getCa().getMaCa());
	    if (ca == null) {
	        return false; // Ca làm không tồn tại
	    }

	    // Lấy thời gian bắt đầu và kết thúc của ca
	    LocalTime thoiGianBatDau = ca.getThoiGianBatDau();
	    LocalTime thoiGianKetThuc = ca.getThoiGianKetThuc();

	    // Kiểm tra ca làm qua ngày hay không
	    if (thoiGianKetThuc.isBefore(thoiGianBatDau)) {
	        // Ca làm qua ngày
	        boolean isValid = (time.isAfter(LocalTime.MIN) && time.isBefore(thoiGianKetThuc.plusMinutes(5))) // Sau 00:00
	                || (time.isAfter(thoiGianBatDau.minusMinutes(0)) && time.isBefore(LocalTime.MAX)); // Trước 23:59
	        return isValid;
	    } else {
	        // Ca làm không qua ngày
	        return time.isAfter(thoiGianKetThuc.minusMinutes(5)) && time.isBefore(thoiGianKetThuc.plusMinutes(5));
	    }
	}
	
	public DangNhap_GUI getDangNhap() {
		return dangNhap;
	}

	// Hàm áp dụng hiệu ứng bóng và viền
    private void applyTextEffectWithBorder(JLabel label, Font font, Color textColor, Color shadowColor, Color borderColor) {
        label.setFont(font);
        label.setForeground(textColor); // Màu chữ chính

        // Ghi đè UI của JLabel để vẽ bóng, viền, và căn giữa
        label.setUI(new javax.swing.plaf.basic.BasicLabelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;

                // Bật khử răng cưa
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Tính toán kích thước chữ
                FontMetrics metrics = g2d.getFontMetrics(label.getFont());
                int textWidth = metrics.stringWidth(label.getText());
                int textHeight = metrics.getHeight();

                // Tính toán vị trí để chữ nằm giữa JLabel
                int x = (label.getWidth() - textWidth) / 2;
                int y = (label.getHeight() - textHeight) / 2 + metrics.getAscent();

                // Vẽ bóng nghiêng
                g2d.setColor(shadowColor); // Màu bóng
                int xOffset = 8; // Dịch sang phải
                int yOffset = 8; // Dịch xuống dưới
                for (int dx = 0; dx <= xOffset; dx++) {
                    for (int dy = 0; dy <= yOffset; dy++) {
                        if (Math.sqrt(dx * dx + dy * dy) <= Math.max(xOffset, yOffset)) {
                            g2d.drawString(label.getText(), x + dx, y + dy);
                        }
                    }
                }

                // Vẽ viền bằng cách vẽ chữ nhiều lần quanh biên
                g2d.setColor(borderColor); // Màu viền
                for (int i = -1; i <= 3; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i != 0 || j != 0) { // Không vẽ lên chính giữa
                            g2d.drawString(label.getText(), x + i, y + j);
                        }
                    }
                }

                // Vẽ chữ chính
                g2d.setColor(label.getForeground());
                g2d.drawString(label.getText(), x, y);

                super.paint(g, c);
            }
        });
    }
    
    private void OpenHtmlFile() {
        try {
            // Đường dẫn đến file index.html trong thư mục TrangHoTro
            File htmlFile = new File("TrangHoTro/html/index.html");

            // Kiểm tra xem file có tồn tại hay không
            if (!htmlFile.exists()) {
                System.out.println("File không tồn tại: " + htmlFile.getAbsolutePath());
                return;
            }

            // Kiểm tra Desktop API có khả dụng không
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(htmlFile.toURI()); // Mở file trong trình duyệt mặc định
            } else {
                System.out.println("Desktop API không được hỗ trợ trên hệ thống này.");
            }
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi mở file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}