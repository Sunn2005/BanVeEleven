package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import components.TextAreaRenderer;
import dao.ChiTietHoaDon_DAO;
import dao.Ghe_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.Ve_DAO;
import entity.ChiTietHoaDon;
import entity.Ghe;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Ve;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class BanVeThanhToan_GUI extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField_TKD;
	private JTextField textField_TTL;
	private JTable table_2;
	private JTable table_1;
	private JButton btn500;
	private JButton btn200;
	private JButton btn100;
	private JButton btn50;
	private JButton btn20;
	private JButton btn10;
	private JButton btn5;
	private JButton btn2;
	private JButton btn1;
	private JButton btnGoiY1;
	private JButton btnGoiY2;
	private JButton btnGoiY3;
	private JButton btn_XacNhan;
	private JButton btnXoa;
	float tienKhachDua = 0;
	float tienTraLai = 0;
	private float tongTienCoThue=0;
	private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
	private ArrayList<KhachHang> dsKH = khachHang_DAO.docTuBang();
	private Ve_DAO ve_DAO = new Ve_DAO();
	private Ghe_DAO ghe_DAO = new Ghe_DAO();
	float[] goiY = new float[3];
	
	private static final String[] CHU_SO = { "không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín" };
	private static final String[] DON_VI = { "", "nghìn", "triệu", "tỷ" };
	
	public BanVeThanhToan_GUI(BanVeNhapThongTin_Gui banVeNhapThongTin_GUI, TrangChu_GUI trangChu, BanVe_GUI banVe_GUI) {
		setBackground(SystemColor.text);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1440, 570);
		setLayout(null);
		
		JPanel jp_QL = new JPanel();
		jp_QL.setBounds(10, 10, 124, 28);
		jp_QL.setBackground(new Color(255, 255, 255));
		jp_QL.setLayout(null);
		add(jp_QL);
		
		//Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		JLabel goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		jp_QL.add(goBackIconLabel);
		goBackIconLabel.setBounds(10, 0, 39, 27);
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					trangChu.content.removeAll();
					trangChu.content.add(banVeNhapThongTin_GUI);
					trangChu.content.revalidate();
					trangChu.content.repaint();
				}
		});
		JLabel lb_quaylai = new JLabel("Quay lại");
		lb_quaylai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_quaylai.setBounds(45, 0, 69, 27);
		jp_QL.add(lb_quaylai);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 48, 941, 512);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đơn vị bán vé:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 125, 24);
		panel.add(lblNewLabel);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên:");
		lblMNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMNhnVin.setBounds(10, 44, 125, 24);
		panel.add(lblMNhnVin);
		
		JLabel lblMKhchHng = new JLabel("Mã khách hàng:");
		lblMKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMKhchHng.setBounds(10, 78, 125, 24);
		panel.add(lblMKhchHng);
		
		JLabel lblSdtKhchHng = new JLabel("SDT khách hàng:");
		lblSdtKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSdtKhchHng.setBounds(10, 112, 125, 24);
		panel.add(lblSdtKhchHng);
		
		JLabel lb_SDT = new JLabel(banVeNhapThongTin_GUI.khachHangMua.getSdt());
		lb_SDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_SDT.setBounds(154, 112, 125, 24);
		panel.add(lb_SDT);
		
		JLabel lb_MKH = new JLabel(banVeNhapThongTin_GUI.khachHangMua.getMaKH());
		lb_MKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_MKH.setBounds(154, 78, 125, 24);
		panel.add(lb_MKH);
		
		JLabel lb_MNV = new JLabel(trangChu.dangNhap.taiKhoanLogined.getNhanVien().getMaNV());
		lb_MNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_MNV.setBounds(154, 44, 125, 24);
		panel.add(lb_MNV);
		
		JLabel lb_DVBV = new JLabel("Nhà ga Eleven");
		lb_DVBV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_DVBV.setBounds(154, 10, 125, 24);
		panel.add(lb_DVBV);
		
		JLabel lblinThoi = new JLabel("Điện thoại:");
		lblinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblinThoi.setBounds(410, 10, 125, 24);
		panel.add(lblinThoi);
		
		JLabel lb_DT = new JLabel("0123456789");
		lb_DT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_DT.setBounds(554, 10, 125, 24);
		panel.add(lb_DT);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnKhchHng.setBounds(410, 78, 125, 24);
		panel.add(lblTnKhchHng);
		
		JLabel lb_TKH = new JLabel(banVeNhapThongTin_GUI.khachHangMua.getTenKH());
		lb_TKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_TKH.setBounds(554, 78, 125, 24);
		panel.add(lb_TKH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 906, 293);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Khách hàng", "Thông tin vé", "Giá gốc", "Đối tượng", "Khuyến mãi", "Thành tiền"
			}
		));
		
		table.setRowHeight(45);
		table.setRowHeight(0,12);
		
		//Điều chính kích thước ô
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(206);
		table.getColumnModel().getColumn(3).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setPreferredWidth(206);
		table.getColumnModel().getColumn(5).setPreferredWidth(98);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		
		// Tạo DefaultTableCellRenderer và căn giữa
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Áp dụng renderer cho tất cả các cột
		table.setDefaultRenderer(Object.class, centerRenderer);

		// Áp dụng TextAreaRenderer cho cột "Thông tin vé" để xuống dòng
        table.getColumnModel().getColumn(2).setCellRenderer(new TextAreaRenderer());
		
		scrollPane.setViewportView(table);
		
		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Thuế giá trị gia tăng (10%):", "140,448 VND"},
				{"Tổng tiền bằng số:", "140,448 VND"},
				{"Tổng tiền bằng chữ:", "MỘT TRIỆU NĂM TRĂM BỐN MƯƠI BỐN NGHÌN CHÍN TRĂM HAI MƯƠI TÁM ĐỒNG"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200); // Cột đầu tiên rộng 200px
		table_2.getColumnModel().getColumn(1).setPreferredWidth(706); // Cột thứ hai rộng 300px
		
		// Áp dụng căn giữa cho cột thứ hai
		table_2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		// Tạo DefaultTableCellRenderer và thiết lập font in đậm cho cột thứ nhất
		DefaultTableCellRenderer boldRenderer = new DefaultTableCellRenderer() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setFont(c.getFont().deriveFont(Font.BOLD)); // Thiết lập font in đậm
		        return c;
		    }
		};

		// Áp dụng boldRenderer cho cột thứ nhất
		table_2.getColumnModel().getColumn(0).setCellRenderer(boldRenderer);
		
		table_2.setBounds(10, 454, 906, 48);
		panel.add(table_2);
		
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tổng cộng (VND):", null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		
		//Điều chính kích thước ô
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(206);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(98);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(206);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(98);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(98);
		
		// Áp dụng căn giữa cho cột thứ hai
		table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		
		// Áp dụng boldRenderer cho cột thứ nhất
		table_1.getColumnModel().getColumn(0).setCellRenderer(boldRenderer);
		
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_1.setBounds(10, 439, 906, 15);
		panel.add(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(986, 48, 444, 512);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tiền khách đưa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(53, 10, 327, 30);
		panel_1.add(lblNewLabel_1);
		
		textField_TKD = new JTextField("0 đ");
		textField_TKD.setBackground(Color.CYAN);
		textField_TKD.setBounds(53, 44, 327, 30);
		panel_1.add(textField_TKD);
		textField_TKD.setColumns(10);
		textField_TKD.setEditable(false);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nhập số tiền theo mệnh giá");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(53, 97, 327, 30);
		panel_1.add(lblNewLabel_1_1);
		
		btn500 = new JButton("500.000");
		btn500.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn500.setBounds(53, 137, 100, 30);
		panel_1.add(btn500);
		
		btn200 = new JButton("200.000");
		btn200.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn200.setBounds(163, 137, 100, 30);
		panel_1.add(btn200);
		
		btn100 = new JButton("100.000");
		btn100.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn100.setBounds(273, 137, 100, 30);
		panel_1.add(btn100);
		
		btn50 = new JButton("50.000");
		btn50.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn50.setBounds(53, 184, 100, 30);
		panel_1.add(btn50);
		
		btn20 = new JButton("20.000");
		btn20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn20.setBounds(163, 184, 100, 30);
		panel_1.add(btn20);
		
		btn10 = new JButton("10.000");
		btn10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn10.setBounds(273, 184, 100, 30);
		panel_1.add(btn10);
		
		btn5 = new JButton("5.000");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn5.setBounds(53, 231, 100, 30);
		panel_1.add(btn5);
		
		btn2 = new JButton("2.000");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.setBounds(163, 231, 100, 30);
		panel_1.add(btn2);
		
		btn1 = new JButton("1.000");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn1.setBounds(273, 231, 100, 30);
		panel_1.add(btn1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Gợi ý tiền mặt");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(53, 282, 327, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		btnGoiY1 = new JButton();
		btnGoiY1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoiY1.setBounds(53, 325, 100, 30);
		panel_1.add(btnGoiY1);

		btnGoiY2 = new JButton();
		btnGoiY2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoiY2.setBounds(163, 325, 100, 30);
		panel_1.add(btnGoiY2);

		btnGoiY3 = new JButton();
		btnGoiY3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoiY3.setBounds(273, 325, 100, 30);
		panel_1.add(btnGoiY3);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tiền trả lại");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(53, 382, 327, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		textField_TTL = new JTextField("0 đ");
		textField_TTL.setColumns(10);
		textField_TTL.setBackground(Color.CYAN);
		textField_TTL.setBounds(53, 419, 327, 30);
		panel_1.add(textField_TTL);
		textField_TTL.setEditable(false);
		btn_XacNhan = new JButton("Xác nhận");
		// ==================== CODE CHO CONSTRUCTOR 1: BanVe_GUI ====================
		btn_XacNhan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // 1. Kiểm tra danh sách vé có rỗng không
		        if (banVe_GUI.dsVeDatTam == null || banVe_GUI.dsVeDatTam.isEmpty()) {
		            JOptionPane.showMessageDialog(null, 
		                "Không có vé nào để thanh toán!", 
		                "Thông báo", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // 2. Kiểm tra tổng tiền có hợp lệ không
		        if (tongTienCoThue <= 0) {
		            JOptionPane.showMessageDialog(null, 
		                "Tổng tiền thanh toán không hợp lệ!", 
		                "Thông báo", 
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 3. Kiểm tra tiền khách đưa
		        if (tienKhachDua <= 0) {
		            JOptionPane.showMessageDialog(null, 
		                "Vui lòng nhập số tiền khách đưa!", 
		                "Thông báo", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // 4. Kiểm tra tiền khách đưa có đủ không
		        if (tienKhachDua < tongTienCoThue) {
		            JOptionPane.showMessageDialog(null, 
		                "Số tiền khách đưa chưa đủ!\n" +
		                "Tổng tiền cần thanh toán: " + dinhDangTienTe(tongTienCoThue) + "\n" +
		                "Số tiền khách đưa: " + dinhDangTienTe(tienKhachDua) + "\n" +
		                "Còn thiếu: " + dinhDangTienTe(tongTienCoThue - tienKhachDua), 
		                "Thông báo", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // 5. Kiểm tra số tiền trả lại có âm không (double check)
		        if (tienTraLai < 0) {
		            JOptionPane.showMessageDialog(null, 
		                "Số tiền trả lại không hợp lệ!", 
		                "Thông báo", 
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 6. Kiểm tra thông tin khách hàng
		        if (banVeNhapThongTin_GUI.khachHangMua == null) {
		            JOptionPane.showMessageDialog(null, 
		                "Thông tin khách hàng không hợp lệ!", 
		                "Thông báo", 
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 7. Xác nhận lại với người dùng trước khi thanh toán
		        int confirm = JOptionPane.showConfirmDialog(null, 
		            "Xác nhận thanh toán?\n\n" +
		            "Tổng tiền: " + dinhDangTienTe(tongTienCoThue) + "\n" +
		            "Tiền khách đưa: " + dinhDangTienTe(tienKhachDua) + "\n" +
		            "Tiền trả lại: " + dinhDangTienTe(tienTraLai) + "\n" +
		            "Số lượng vé: " + banVe_GUI.dsVeDatTam.size(),
		            "Xác nhận thanh toán",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE);
		        
		        if (confirm != JOptionPane.YES_OPTION) {
		            return;
		        }
		        
		        // 8. Thực hiện thanh toán
		        try {
		            // Tạo khách hàng
		            Boolean khachHangExist = dsKH.contains(banVeNhapThongTin_GUI.khachHangMua);
		            if (!khachHangExist) {
		                khachHang_DAO.create(banVeNhapThongTin_GUI.khachHangMua);
		            }
		            khachHang_DAO.reset();
		            dsKH.removeAll(dsKH);
		            dsKH = khachHang_DAO.docTuBang();
		            
		            for (int key: banVeNhapThongTin_GUI.map.keySet()) {
		                KhachHang khachHangMoi = banVeNhapThongTin_GUI.map.get(key);
		                khachHangExist = (dsKH.contains(khachHangMoi) || 
		                                 khachHangMoi.getSdt().equals(banVeNhapThongTin_GUI.khachHangMua.getSdt()));
		                if (!khachHangExist) {
		                    banVeNhapThongTin_GUI.map.get(key).setMaKH(generateMaKH());
		                    khachHang_DAO.create(banVeNhapThongTin_GUI.map.get(key));
		                } else {
		                    banVeNhapThongTin_GUI.map.get(key).setMaKH(banVeNhapThongTin_GUI.khachHangMua.getMaKH());
		                }
		            }
		            
		            // Tạo hóa đơn
		            hoaDon_DAO.reset();
		            ArrayList<HoaDon> dsHD = hoaDon_DAO.docTuBang();
		            String maHD = generateMaHD(dsHD, trangChu.dangNhap.taiKhoanLogined.getNhanVien().getMaNV());
		            LocalDateTime ngayLapHoaDon = LocalDateTime.now();
		            NhanVien nhanVien = trangChu.dangNhap.taiKhoanLogined.getNhanVien();
		            KhachHang khachHang = banVeNhapThongTin_GUI.khachHangMua;
		            ChiTietHoaDon chiTiet = null;
		            HoaDon hoaDon = new HoaDon(maHD, ngayLapHoaDon, nhanVien, khachHang, chiTiet, false, false);
		            hoaDon_DAO.create(hoaDon);
		            
		            // Tạo chi tiết hóa đơn
		            String maCT = "CT" + maHD;
		            int soLuong = banVe_GUI.dsVeDatTam.size();
		            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maCT, hoaDon, soLuong, banVe_GUI.dsVeDatTam, 0.1f);
		            chiTietHoaDon_DAO.create(chiTietHoaDon);
		            
		            // Tạo danh sách vé
		            String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy"));
		            int currentVeNumber = ve_DAO.getLastVeNumber(datePart);

		            for (Ve ve: banVe_GUI.dsVeDatTam) {
		                String maVe = "VE" + datePart + String.format("%04d", currentVeNumber + 1);
		                ve.setMaVe(maVe);
		                ve.setKhachHang(banVeNhapThongTin_GUI.map.get(banVe_GUI.dsVeDatTam.indexOf(ve)));
		                ve.setChiTiet(chiTietHoaDon);
		                ve_DAO.create(ve);

		                Ghe ghe = ve.getSoGhe();
		                ghe.setTrangThai(false);
		                ghe_DAO.update(ghe);
		                currentVeNumber++;
		            }
		            
		            // Thông báo thành công
		            JOptionPane.showMessageDialog(null,
		                "Đặt vé thành công!\nLập hóa đơn thành công!\n" +
		                "Mã hóa đơn: " + maHD + "\n" +
		                "Tiền trả lại khách: " + dinhDangTienTe(tienTraLai), 
		                "Thông báo", 
		                JOptionPane.INFORMATION_MESSAGE, 
		                new ImageIcon(getClass().getResource("/img/299110_check_sign_icon.png")));
		            
		            // Chuyển sang màn hình quản lý vé
		            QuanLyVe_Gui quanLyVe_gui = new QuanLyVe_Gui(trangChu);
		            trangChu.content.removeAll();
		            trangChu.content.add(quanLyVe_gui);
		            trangChu.content.revalidate();
		            trangChu.content.repaint();
		            
		        } catch (Exception ex) {
		            // Xử lý lỗi trong quá trình thanh toán
		            JOptionPane.showMessageDialog(null, 
		                "Có lỗi xảy ra trong quá trình thanh toán!\n" + ex.getMessage(), 
		                "Lỗi", 
		                JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		btn_XacNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_XacNhan.setBounds(301, 472, 106, 30);
		panel_1.add(btn_XacNhan);
		
		btnXoa = new JButton("Nhập lại");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(180, 472, 106, 30);
		panel_1.add(btnXoa);
		
		//Sự kiện nút
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn5.addActionListener(this);
		btn10.addActionListener(this);
		btn20.addActionListener(this);
		btn50.addActionListener(this);
		btn100.addActionListener(this);
		btn200.addActionListener(this);
		btn500.addActionListener(this);

		btnGoiY1.addActionListener(this);
		btnGoiY2.addActionListener(this);
		btnGoiY3.addActionListener(this);
		
		btnXoa.addActionListener(this);
		
		loadThongTin(banVe_GUI.dsVeDatTam, banVeNhapThongTin_GUI.map);
		
		tienGoiY(tongTienCoThue, goiY);
		btnGoiY1.setText((long) goiY[0]+"");
		btnGoiY2.setText((long) goiY[1]+"");
		btnGoiY3.setText((long) goiY[2]+"");
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public BanVeThanhToan_GUI(BanVeNhapThongTin_Gui banVeNhapThongTin_GUI, TrangChu_GUI trangChu, DoiVe_GUI doiVe_GUI) {
		setBackground(SystemColor.text);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1440, 570);
		setLayout(null);
		
		JPanel jp_QL = new JPanel();
		jp_QL.setBounds(10, 10, 124, 28);
		jp_QL.setBackground(new Color(255, 255, 255));
		jp_QL.setLayout(null);
		add(jp_QL);
		
		//Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		JLabel goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		jp_QL.add(goBackIconLabel);
		goBackIconLabel.setBounds(10, 0, 39, 27);
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					trangChu.content.removeAll();
					trangChu.content.add(banVeNhapThongTin_GUI);
					trangChu.content.revalidate();
					trangChu.content.repaint();
				}
		});
		JLabel lb_quaylai = new JLabel("Quay lại");
		lb_quaylai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_quaylai.setBounds(45, 0, 69, 27);
		jp_QL.add(lb_quaylai);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 48, 941, 512);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đơn vị bán vé:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 125, 24);
		panel.add(lblNewLabel);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên:");
		lblMNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMNhnVin.setBounds(10, 44, 125, 24);
		panel.add(lblMNhnVin);
		
		JLabel lblMKhchHng = new JLabel("Mã khách hàng:");
		lblMKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMKhchHng.setBounds(10, 78, 125, 24);
		panel.add(lblMKhchHng);
		
		JLabel lblSdtKhchHng = new JLabel("SDT khách hàng:");
		lblSdtKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSdtKhchHng.setBounds(10, 112, 125, 24);
		panel.add(lblSdtKhchHng);
		
		JLabel lb_SDT = new JLabel(banVeNhapThongTin_GUI.khachHangMua.getSdt());
		lb_SDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_SDT.setBounds(154, 112, 125, 24);
		panel.add(lb_SDT);
		
		JLabel lb_MKH = new JLabel(banVeNhapThongTin_GUI.khachHangMua.getMaKH());
		lb_MKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_MKH.setBounds(154, 78, 125, 24);
		panel.add(lb_MKH);
		
		JLabel lb_MNV = new JLabel(trangChu.dangNhap.taiKhoanLogined.getNhanVien().getMaNV());
		lb_MNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_MNV.setBounds(154, 44, 125, 24);
		panel.add(lb_MNV);
		
		JLabel lb_DVBV = new JLabel("Nhà ga Eleven");
		lb_DVBV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_DVBV.setBounds(154, 10, 125, 24);
		panel.add(lb_DVBV);
		
		JLabel lblinThoi = new JLabel("Điện thoại:");
		lblinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblinThoi.setBounds(410, 10, 125, 24);
		panel.add(lblinThoi);
		
		JLabel lb_DT = new JLabel("0123456789");
		lb_DT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_DT.setBounds(554, 10, 125, 24);
		panel.add(lb_DT);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnKhchHng.setBounds(410, 78, 125, 24);
		panel.add(lblTnKhchHng);
		
		JLabel lb_TKH = new JLabel(banVeNhapThongTin_GUI.khachHangMua.getTenKH());
		lb_TKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_TKH.setBounds(554, 78, 200, 24);
		panel.add(lb_TKH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 906, 254);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Khách hàng", "Thông tin vé", "Giá gốc", "Đối tượng", "Khuyến mãi", "Thành tiền"
			}
		));
		
		table.setRowHeight(45);
		table.setRowHeight(0,12);
		
		//Điều chính kích thước ô
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(206);
		table.getColumnModel().getColumn(3).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setPreferredWidth(206);
		table.getColumnModel().getColumn(5).setPreferredWidth(98);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		
		// Tạo DefaultTableCellRenderer và căn giữa
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// Áp dụng renderer cho tất cả các cột
		table.setDefaultRenderer(Object.class, centerRenderer);

		// Áp dụng TextAreaRenderer cho cột "Thông tin vé" để xuống dòng
        table.getColumnModel().getColumn(2).setCellRenderer(new TextAreaRenderer());
		
		scrollPane.setViewportView(table);
		
		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Thuế giá trị gia tăng (10%):", "140,448 VND"},
				{"Tổng tiền bằng số:", "140,448 VND"},
				{"Tổng tiền bằng chữ:", "MỘT TRIỆU NĂM TRĂM BỐN MƯƠI BỐN NGHÌN CHÍN TRĂM HAI MƯƠI TÁM ĐỒNG"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200); // Cột đầu tiên rộng 200px
		table_2.getColumnModel().getColumn(1).setPreferredWidth(706); // Cột thứ hai rộng 300px
		
		// Áp dụng căn giữa cho cột thứ hai
		table_2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		// Tạo DefaultTableCellRenderer và thiết lập font in đậm cho cột thứ nhất
		DefaultTableCellRenderer boldRenderer = new DefaultTableCellRenderer() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setFont(c.getFont().deriveFont(Font.BOLD)); // Thiết lập font in đậm
		        return c;
		    }
		};

		// Áp dụng boldRenderer cho cột thứ nhất
		table_2.getColumnModel().getColumn(0).setCellRenderer(boldRenderer);
		
		table_2.setBounds(10, 414, 906, 48);
		panel.add(table_2);
		
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tổng cộng (VND):", null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		
		//Điều chính kích thước ô
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(206);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(98);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(206);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(98);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(98);
		
		// Áp dụng căn giữa cho cột thứ hai
		table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		
		// Áp dụng boldRenderer cho cột thứ nhất
		table_1.getColumnModel().getColumn(0).setCellRenderer(boldRenderer);
		
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_1.setBounds(10, 399, 906, 15);
		panel.add(table_1);
		
		JLabel lblGiVC = new JLabel("Giá vé cũ:");
		lblGiVC.setBounds(10, 478, 125, 24);
		panel.add(lblGiVC);
		lblGiVC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lb_GiaVeCu = new JLabel();
		lb_GiaVeCu.setBounds(154, 478, 125, 24);
		panel.add(lb_GiaVeCu);
		lb_GiaVeCu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblGCL = new JLabel("Giá chênh lệch:");
		lblGCL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGCL.setBounds(509, 478, 125, 24);
		panel.add(lblGCL);
		
		JLabel lb_GiaChenhLech = new JLabel();
		lb_GiaChenhLech.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_GiaChenhLech.setBounds(653, 478, 125, 24);
		panel.add(lb_GiaChenhLech);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(986, 48, 444, 512);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tiền khách đưa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(53, 10, 327, 30);
		panel_1.add(lblNewLabel_1);
		
		textField_TKD = new JTextField("0 đ");
		textField_TKD.setBackground(Color.CYAN);
		textField_TKD.setBounds(53, 44, 327, 30);
		panel_1.add(textField_TKD);
		textField_TKD.setColumns(10);
		textField_TKD.setEditable(false);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nhập số tiền theo mệnh giá");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(53, 97, 327, 30);
		panel_1.add(lblNewLabel_1_1);
		
		btn500 = new JButton("500.000");
		btn500.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn500.setBounds(53, 137, 100, 30);
		panel_1.add(btn500);
		
		btn200 = new JButton("200.000");
		btn200.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn200.setBounds(163, 137, 100, 30);
		panel_1.add(btn200);
		
		btn100 = new JButton("100.000");
		btn100.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn100.setBounds(273, 137, 100, 30);
		panel_1.add(btn100);
		
		btn50 = new JButton("50.000");
		btn50.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn50.setBounds(53, 184, 100, 30);
		panel_1.add(btn50);
		
		btn20 = new JButton("20.000");
		btn20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn20.setBounds(163, 184, 100, 30);
		panel_1.add(btn20);
		
		btn10 = new JButton("10.000");
		btn10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn10.setBounds(273, 184, 100, 30);
		panel_1.add(btn10);
		
		btn5 = new JButton("5.000");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn5.setBounds(53, 231, 100, 30);
		panel_1.add(btn5);
		
		btn2 = new JButton("2.000");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.setBounds(163, 231, 100, 30);
		panel_1.add(btn2);
		
		btn1 = new JButton("1.000");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn1.setBounds(273, 231, 100, 30);
		panel_1.add(btn1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Gợi ý tiền mặt");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(53, 282, 327, 30);
		panel_1.add(lblNewLabel_1_1_1);

		btnGoiY1 = new JButton();
		btnGoiY1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoiY1.setBounds(53, 325, 100, 30);
		panel_1.add(btnGoiY1);

		btnGoiY2 = new JButton();
		btnGoiY2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoiY2.setBounds(163, 325, 100, 30);
		panel_1.add(btnGoiY2);

		btnGoiY3 = new JButton();
		btnGoiY3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoiY3.setBounds(273, 325, 100, 30);
		panel_1.add(btnGoiY3);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tiền trả lại");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(53, 382, 327, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		textField_TTL = new JTextField("0 đ");
		textField_TTL.setColumns(10);
		textField_TTL.setBackground(Color.CYAN);
		textField_TTL.setBounds(53, 419, 327, 30);
		panel_1.add(textField_TTL);
		textField_TTL.setEditable(false);
		btn_XacNhan = new JButton("Xác nhận");
		// ==================== CODE CHO CONSTRUCTOR 2: DoiVe_GUI ====================
		btn_XacNhan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // 1. Kiểm tra danh sách vé có rỗng không
		        if (doiVe_GUI.dsVeDatTam == null || doiVe_GUI.dsVeDatTam.isEmpty()) {
		            JOptionPane.showMessageDialog(null, 
		                "Không có vé nào để đổi!", 
		                "Thông báo", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // 2. Kiểm tra vé cũ
		        if (doiVe_GUI.veCu == null) {
		            JOptionPane.showMessageDialog(null, 
		                "Không tìm thấy thông tin vé cũ!", 
		                "Thông báo", 
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 3. Tính toán giá chênh lệch
		        float giaVeCu = doiVe_GUI.veCu.tinhGiaVe() * 1.1f;
		        float giaVeMoi = doiVe_GUI.dsVeDatTam.get(0).tinhGiaVe() * 1.1f;
		        float tienChenhLech = giaVeCu - giaVeMoi;
		        
		        // 4. Kiểm tra tiền khách đưa nếu vé mới đắt hơn
		        if (tienChenhLech < 0) {
		            float tienCanThu = Math.abs(tienChenhLech);
		            
		            if (tienKhachDua <= 0) {
		                JOptionPane.showMessageDialog(null, 
		                    "Vé mới đắt hơn vé cũ!\nVui lòng nhập số tiền khách đưa!", 
		                    "Thông báo", 
		                    JOptionPane.WARNING_MESSAGE);
		                return;
		            }
		            
		            if (tienKhachDua < tienCanThu) {
		                JOptionPane.showMessageDialog(null, 
		                    "Số tiền khách đưa chưa đủ!\n" +
		                    "Giá vé cũ: " + dinhDangTienTe(giaVeCu) + "\n" +
		                    "Giá vé mới: " + dinhDangTienTe(giaVeMoi) + "\n" +
		                    "Cần thu thêm: " + dinhDangTienTe(tienCanThu) + "\n" +
		                    "Số tiền khách đưa: " + dinhDangTienTe(tienKhachDua) + "\n" +
		                    "Còn thiếu: " + dinhDangTienTe(tienCanThu - tienKhachDua), 
		                    "Thông báo", 
		                    JOptionPane.WARNING_MESSAGE);
		                return;
		            }
		            
		            if (tienTraLai < 0) {
		                JOptionPane.showMessageDialog(null, 
		                    "Số tiền trả lại không hợp lệ!", 
		                    "Thông báo", 
		                    JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		        }
		        
		        // 5. Kiểm tra thông tin khách hàng
		        if (banVeNhapThongTin_GUI.khachHangMua == null) {
		            JOptionPane.showMessageDialog(null, 
		                "Thông tin khách hàng không hợp lệ!", 
		                "Thông báo", 
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 6. Xác nhận lại với người dùng
		        String message = "Xác nhận đổi vé?\n\n" +
		                        "Giá vé cũ: " + dinhDangTienTe(giaVeCu) + "\n" +
		                        "Giá vé mới: " + dinhDangTienTe(giaVeMoi) + "\n";
		        
		        if (tienChenhLech > 0) {
		            message += "Tiền hoàn trả khách: " + dinhDangTienTe(tienChenhLech);
		        } else if (tienChenhLech < 0) {
		            message += "Cần thu thêm: " + dinhDangTienTe(Math.abs(tienChenhLech)) + "\n" +
		                      "Tiền khách đưa: " + dinhDangTienTe(tienKhachDua) + "\n" +
		                      "Tiền trả lại: " + dinhDangTienTe(tienTraLai);
		        } else {
		            message += "Không có chênh lệch giá";
		        }
		        
		        int confirm = JOptionPane.showConfirmDialog(null, 
		            message,
		            "Xác nhận đổi vé",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE);
		        
		        if (confirm != JOptionPane.YES_OPTION) {
		            return;
		        }
		        
		        // 7. Thực hiện đổi vé
		        try {
		            // Cập nhật trạng thái ghế cũ
		            Ghe gheCu = doiVe_GUI.veCu.getSoGhe();
		            gheCu.setTrangThai(true);
		            ghe_DAO.update(gheCu);
		            doiVe_GUI.veCu.setTrangThai(true);
		            
		            ChiTietHoaDon chiTietVeCu = chiTietHoaDon_DAO.getCTHDTheoMaChiTiet(doiVe_GUI.veCu.getChiTiet().getMaChiTiet());
		            ve_DAO.update(doiVe_GUI.veCu);
		            
		            if (chiTietVeCu.getSoLuong() == 1) {
		                HoaDon hdCu = hoaDon_DAO.getHoaDonTheoMaHoaDon(chiTietVeCu.getHoaDon().getMaHoaDon());
		                hdCu.setDaHoanTien(true);
		                hdCu.setDaHoanVe(true);
		                hoaDon_DAO.update(hdCu);
		            } else {
		                doiVe_GUI.veCu.getChiTiet().setSoLuong(chiTietVeCu.getSoLuong()-1);					
		                chiTietHoaDon_DAO.updateSoLuongVe(chiTietVeCu);
		            }
		            
		            // Tạo khách hàng
		            Boolean khachHangExist = dsKH.contains(banVeNhapThongTin_GUI.khachHangMua);
		            if (!khachHangExist) {
		                khachHang_DAO.create(banVeNhapThongTin_GUI.khachHangMua);
		            }
		            khachHang_DAO.reset();
		            dsKH.removeAll(dsKH);
		            dsKH = khachHang_DAO.docTuBang();
		            
		            for (int key: banVeNhapThongTin_GUI.map.keySet()) {
		                KhachHang khachHangMoi = banVeNhapThongTin_GUI.map.get(key);
		                khachHangExist = (dsKH.contains(khachHangMoi) || 
		                                 khachHangMoi.getSdt().equals(banVeNhapThongTin_GUI.khachHangMua.getSdt()));
		                if (!khachHangExist) {
		                    banVeNhapThongTin_GUI.map.get(key).setMaKH(generateMaKH());
		                    khachHang_DAO.create(banVeNhapThongTin_GUI.map.get(key));
		                } else {
		                    banVeNhapThongTin_GUI.map.get(key).setMaKH(banVeNhapThongTin_GUI.khachHangMua.getMaKH());
		                }
		            }
		            
		            // Tạo hóa đơn
		            hoaDon_DAO.reset();
		            ArrayList<HoaDon> dsHD = hoaDon_DAO.docTuBang();
		            String maHD = generateMaHD(dsHD, trangChu.dangNhap.taiKhoanLogined.getNhanVien().getMaNV());
		            LocalDateTime ngayLapHoaDon = LocalDateTime.now();
		            NhanVien nhanVien = trangChu.dangNhap.taiKhoanLogined.getNhanVien();
		            KhachHang khachHang = banVeNhapThongTin_GUI.khachHangMua;
		            ChiTietHoaDon chiTiet = null;
		            HoaDon hoaDon = new HoaDon(maHD, ngayLapHoaDon, nhanVien, khachHang, chiTiet, false, false);
		            hoaDon_DAO.create(hoaDon);
		            
		            // Tạo chi tiết hóa đơn
		            String maCT = "CT" + maHD;
		            int soLuong = doiVe_GUI.dsVeDatTam.size();
		            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maCT, hoaDon, soLuong, doiVe_GUI.dsVeDatTam, 0.1f);
		            chiTietHoaDon_DAO.create(chiTietHoaDon);
		            
		            // Tạo danh sách vé
		            String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy"));
		            int currentVeNumber = ve_DAO.getLastVeNumber(datePart);

		            for (Ve ve: doiVe_GUI.dsVeDatTam) {
		                String maVe = "VE" + datePart + String.format("%04d", currentVeNumber + 1);
		                ve.setMaVe(maVe);
		                ve.setKhachHang(banVeNhapThongTin_GUI.map.get(doiVe_GUI.dsVeDatTam.indexOf(ve)));
		                ve.setChiTiet(chiTietHoaDon);
		                ve_DAO.create(ve);

		                Ghe ghe = ve.getSoGhe();
		                ghe.setTrangThai(false);
		                ghe_DAO.update(ghe);
		                currentVeNumber++;
		            }
		            
		            // Thông báo thành công
		            String successMsg = "Đã đổi vé thành công!\nLập hóa đơn mới thành công!\n" +
		                               "Mã hóa đơn: " + maHD + "\n";
		            
		            if (tienChenhLech > 0) {
		                successMsg += "Tiền hoàn trả khách: " + dinhDangTienTe(tienChenhLech);
		            } else if (tienChenhLech < 0) {
		                successMsg += "Tiền trả lại khách: " + dinhDangTienTe(tienTraLai);
		            }
		            
		            JOptionPane.showMessageDialog(null,
		                successMsg, 
		                "Thông báo", 
		                JOptionPane.INFORMATION_MESSAGE, 
		                new ImageIcon(getClass().getResource("/img/299110_check_sign_icon.png")));
		            
		            // Chuyển sang màn hình quản lý vé
		            QuanLyVe_Gui quanLyVe_gui = new QuanLyVe_Gui(trangChu);
		            trangChu.content.removeAll();
		            trangChu.content.add(quanLyVe_gui);
		            trangChu.content.revalidate();
		            trangChu.content.repaint();
		            
		        } catch (Exception ex) {
		            // Xử lý lỗi trong quá trình đổi vé
		            JOptionPane.showMessageDialog(null, 
		                "Có lỗi xảy ra trong quá trình đổi vé!\n" + ex.getMessage(), 
		                "Lỗi", 
		                JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		btn_XacNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_XacNhan.setBounds(301, 472, 106, 30);
		panel_1.add(btn_XacNhan);
		
		btnXoa = new JButton("Nhập lại");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(180, 472, 106, 30);
		panel_1.add(btnXoa);
		
		//Sự kiện nút
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn5.addActionListener(this);
		btn10.addActionListener(this);
		btn20.addActionListener(this);
		btn50.addActionListener(this);
		btn100.addActionListener(this);
		btn200.addActionListener(this);
		btn500.addActionListener(this);

		btnGoiY1.addActionListener(this);
		btnGoiY2.addActionListener(this);
		btnGoiY3.addActionListener(this);
		
		btnXoa.addActionListener(this);

		tienGoiY(tongTienCoThue, goiY);
		btnGoiY1.setText((long) goiY[0]+"");
		btnGoiY2.setText((long) goiY[1]+"");
		btnGoiY3.setText((long) goiY[2]+"");
		
		loadThongTin(doiVe_GUI.dsVeDatTam, banVeNhapThongTin_GUI.map);
		
		lb_GiaVeCu.setText(dinhDangTienTe(doiVe_GUI.veCu.tinhGiaVe()*1.1f));
		float tienChenhLech = doiVe_GUI.veCu.tinhGiaVe()*1.1f - doiVe_GUI.dsVeDatTam.get(0).tinhGiaVe()*1.1f;
		lb_GiaChenhLech.setText(dinhDangTienTe(Math.abs(tienChenhLech)));
		if (tienChenhLech < 0) {
			 // Vé mới đắt hơn - cần thu thêm tiền
		    tongTienCoThue = Math.abs(tienChenhLech);
		    textField_TTL.setText(dinhDangTienTe(0));
		    
		    tienGoiY(tongTienCoThue, goiY);
		    btnGoiY1.setText((long) goiY[0]+"");
		    btnGoiY2.setText((long) goiY[1]+"");
		    btnGoiY3.setText((long) goiY[2]+"");
		}
		else {
		    // Vé mới rẻ hơn - hoàn tiền cho khách
		    tongTienCoThue = 0;
		    textField_TTL.setText(dinhDangTienTe(tienChenhLech));
		    textField_TKD.setText("Không cần thu thêm");
		    
		    // Vô hiệu hóa các nút nhập tiền
		    btn1.setEnabled(false);
		    btn2.setEnabled(false);
		    btn5.setEnabled(false);
		    btn10.setEnabled(false);
		    btn20.setEnabled(false);
		    btn50.setEnabled(false);
		    btn100.setEnabled(false);
		    btn200.setEnabled(false);
		    btn500.setEnabled(false);
		    btnGoiY1.setEnabled(false);
		    btnGoiY2.setEnabled(false);
		    btnGoiY3.setEnabled(false);
			
		}
	}
	
	// Renderer tùy chỉnh để gộp hai cột đầu tiên (STT & Mã vé)
    static class CombinedColumnRenderer extends DefaultTableCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Lấy giá trị từ cột 0 và cột 1 (STT & Mã vé)
            String stt = (String) table.getModel().getValueAt(row, 0);
            String maVe = (String) table.getModel().getValueAt(row, 1);

            // Hiển thị gộp dữ liệu của hai cột
            JLabel label = new JLabel("<html><b>" + stt + "</b><br>" + maVe + "</html>");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            if (isSelected) {
                label.setBackground(table.getSelectionBackground());
                label.setForeground(table.getSelectionForeground());
            } else {
                label.setBackground(table.getBackground());
                label.setForeground(table.getForeground());
            }
            return label;
        }
    }

    public String dinhDangTienTe(double soTien) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return formatter.format(soTien);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnXoa)) {
			tienKhachDua = 0;
			// Hiển thị số tiền đã định dạng
			tienTraLai = 0;
			textField_TKD.setText(dinhDangTienTe(tienKhachDua));
			textField_TTL.setText(dinhDangTienTe(tienTraLai));
		} else {
			float soTienThem = 0;

			// Xác định số tiền cần thêm dựa trên nút bấm
			if (o.equals(btn1)) soTienThem = 1000;
			if (o.equals(btn2)) soTienThem = 2000;
			if (o.equals(btn5)) soTienThem = 5000;
			if (o.equals(btn10)) soTienThem = 10000;
			if (o.equals(btn20)) soTienThem = 20000;
			if (o.equals(btn50)) soTienThem = 50000;
			if (o.equals(btn100)) soTienThem = 100000;
			if (o.equals(btn200)) soTienThem = 200000;
			if (o.equals(btn500)) soTienThem = 500000;
			if (o.equals(btnGoiY1)) soTienThem = goiY[0];
			if (o.equals(btnGoiY2)) soTienThem = goiY[1];
			if (o.equals(btnGoiY3)) soTienThem = goiY[2];

			// Kiểm tra nếu text hiện tại là "0 đ" thì đặt `tienKhachDua` thành `soTienThem`
			if (textField_TKD.getText().equalsIgnoreCase("0 đ")) {
				tienKhachDua = soTienThem;
				textField_TKD.setText(dinhDangTienTe(tienKhachDua)); // Cập nhật textField
			} else {
				tienKhachDua += soTienThem;
				textField_TKD.setText(dinhDangTienTe(tienKhachDua));
			}
			tienTraLai = tienKhachDua - tongTienCoThue;
			textField_TTL.setText(dinhDangTienTe(tienTraLai));
		}
	}
	
	private void loadThongTin(ArrayList<Ve> dsVeDatTam,  Map<Integer, KhachHang> map) {
		DefaultTableModel defaultModel = ((DefaultTableModel) table.getModel());
		defaultModel.setRowCount(0);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		int count = 1;
		float tongGiaGoc = 0;
		float tongKhuyenMai = 0;
		for (Ve ve: dsVeDatTam) {
			// Thông tin vé + giá gốc
			String thongTinVe = ve.getChuyenTau().getMaTau() + ": Từ " + ve.getGaDi().getTenGa() + " đến "
					+ ve.getGaDen().getTenGa() + "\nNgày: "
					+ ve.getNgayDi().format(formatter) + "   Lúc: " + ve.getGioDi().toString();
			if (ve.getHang().equalsIgnoreCase("VIP")) {
				thongTinVe = thongTinVe + "\nHạng VIP Toa "
						+ ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số "
						+ ve.getSoGhe().getSoGhe();
			} else if (ve.getHang().equalsIgnoreCase("Giường nằm")) {
				thongTinVe = thongTinVe + "\nGiường nằm Toa "
						+ ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số "
						+ ve.getSoGhe().getSoGhe();
			} else {
				thongTinVe = thongTinVe + "\nGhế mềm Toa "
						+ ve.getToa().getMaToa().substring(ve.getToa().getMaToa().length() - 2) + " Ghế số "
						+ ve.getSoGhe().getSoGhe();
			}
			
			// Khuyến mãi
			float giaGoc = ve.tinhGiaVeGoc();
			float khuyenMai = 0;
			if (ve.getKhuyenMai().equalsIgnoreCase("Sinh viên"))
				khuyenMai = giaGoc * 0.1f;
			else if (ve.getKhuyenMai().equalsIgnoreCase("Người lớn"))
				khuyenMai = giaGoc * 0;
			else if (ve.getKhuyenMai().equalsIgnoreCase("Người lớn tuổi"))
				khuyenMai = giaGoc * 0.15f;
			else if (ve.getKhuyenMai().equalsIgnoreCase("Trẻ em từ 6 đến 10 tuổi"))
				khuyenMai = giaGoc * 0.25f;
			else
				khuyenMai = giaGoc * 1;
			
			KhachHang khachHang = map.get(count-1);
			
			defaultModel.addRow(new Object[] {count++, khachHang.getTenKH(), thongTinVe, dinhDangTienTe(giaGoc), ve.getKhuyenMai(), dinhDangTienTe(khuyenMai), dinhDangTienTe(giaGoc-khuyenMai)});
			
			tongGiaGoc += giaGoc;
			tongKhuyenMai += khuyenMai;
		}
		
		defaultModel = ((DefaultTableModel) table_1.getModel());
		defaultModel.setValueAt(dinhDangTienTe(tongGiaGoc), 0, 2);
		defaultModel.setValueAt(dinhDangTienTe(tongKhuyenMai), 0, 4);
		defaultModel.setValueAt(dinhDangTienTe(tongGiaGoc - tongKhuyenMai), 0, 5);
		
		defaultModel = ((DefaultTableModel) table_2.getModel());
		defaultModel.setValueAt(dinhDangTienTe((tongGiaGoc - tongKhuyenMai)*0.1), 0, 1);
		tongTienCoThue = (tongGiaGoc - tongKhuyenMai)*1.1f;
		defaultModel.setValueAt(dinhDangTienTe(tongTienCoThue), 1, 1);
		defaultModel.setValueAt(convertToWords((long)tongTienCoThue), 2, 1);
	}
	
	// Hàm chính để chuyển đổi số sang chữ
		public static String convertToWords(long soTien) {
			if (soTien == 0) {
				return "không đồng";
			}

			StringBuilder result = new StringBuilder();
			int donViIndex = 0;

			while (soTien > 0) {
				int group = (int) (soTien % 1000); // Nhóm ba chữ số
				if (group > 0) {
					String groupText = convertThreeDigitNumber(group);
					result.insert(0, groupText + " " + DON_VI[donViIndex] + " ");
				}
				soTien /= 1000;
				donViIndex++;
			}

			result.append("đồng");
			return result.toString().trim().replaceAll("\\s+", " ");
		}

		// Hàm chuyển đổi một nhóm ba chữ số thành chữ
		private static String convertThreeDigitNumber(int number) {
			StringBuilder groupText = new StringBuilder();

			int tram = number / 100;
			int chuc = (number % 100) / 10;
			int donVi = number % 10;

			if (tram > 0) {
				groupText.append(CHU_SO[tram]).append(" trăm ");
				if (chuc == 0 && donVi > 0) {
					groupText.append("lẻ ");
				}
			}

			if (chuc > 1) {
				groupText.append(CHU_SO[chuc]).append(" mươi ");
				if (donVi == 1) {
					groupText.append("mốt");
				} else if (donVi == 5) {
					groupText.append("lăm");
				} else if (donVi > 0) {
					groupText.append(CHU_SO[donVi]);
				}
			} else if (chuc == 1) {
				groupText.append("mười ");
				if (donVi == 1) {
					groupText.append("một");
				} else if (donVi == 5) {
					groupText.append("lăm");
				} else if (donVi > 0) {
					groupText.append(CHU_SO[donVi]);
				}
			} else if (donVi > 0) {
				groupText.append(CHU_SO[donVi]);
			}

			return groupText.toString().trim();
		}
		
		public static String generateMaHD(ArrayList<HoaDon> danhSachHD, String maNhanVien) {
		    // 1. Lấy ngày hiện tại và định dạng theo "ddMMyy"
		    LocalDate today = LocalDate.now();
		    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
		    String datePart = today.format(dateFormatter);

		    // 2. Đảm bảo mã nhân viên có dạng "NVXXX"
		    String formattedMaNV = formatMaNhanVien(maNhanVien);

		    // 3. Lọc danh sách hóa đơn theo ngày và mã nhân viên
		    ArrayList<HoaDon> filteredHD = (ArrayList<HoaDon>) danhSachHD.stream()
		        .filter(hd -> hd.getMaHoaDon().startsWith(datePart + formattedMaNV))
		        .collect(Collectors.toList());

		    // 4. Lấy hóa đơn cuối cùng từ danh sách đã lọc
		    HoaDon lastHD = filteredHD.isEmpty() ? null : filteredHD.get(filteredHD.size() - 1);

		    // 5. Xử lý dãy số tự tăng
		    int newCounter = 1;
		    if (lastHD != null) {
		        String lastCounterStr = lastHD.getMaHoaDon().substring(lastHD.getMaHoaDon().length() - 5);
		        newCounter = Integer.parseInt(lastCounterStr) + 1;
		    }

		    String counterPart = String.format("%05d", newCounter);

		    // 6. Tạo mã hóa đơn mới
		    return datePart + formattedMaNV + counterPart;
		}


	    // Phương thức định dạng mã nhân viên
	    private static String formatMaNhanVien(String maNhanVien) {
	        // Đảm bảo mã nhân viên có dạng "NVXXX"
	        if (!maNhanVien.startsWith("NV")) {
	            maNhanVien = "NV" + maNhanVien;
	        }
	        return maNhanVien;
	    }
	    
	    public String generateMaKH() {
			khachHang_DAO.reset();
			ArrayList<KhachHang> list = khachHang_DAO.docTuBang();
			int sl = list.size() + 1;
			String maKH = String.format("KH%04d", sl);
			return maKH;
		}
	    
	    // Hàm tính toán số tiền gợi ý
	    public static void tienGoiY(float tienCanDua, float[] goiY) {
	    	// Gợi ý 1: Số tiền tròn lớn hơn hoặc bằng số tiền cần đưa
	    	goiY[0] = (float) (Math.ceil(tienCanDua / 1000) * 1000);

	    	// Gợi ý 2: Số tiền tròn lớn hơn tiếp theo, như 50.000 hoặc bội số của nó
	    	goiY[1] = (float) (Math.ceil(tienCanDua / 50000) * 50000);

	    	// Gợi ý 3: Một số tiền tròn phổ biến hơn, ví dụ 200.000 hoặc 500.000, tùy ngữ cảnh
	    	if (goiY[1] <= 200000) {
	    		goiY[2] = 200000;
	    	} else if (goiY[1] <= 500000) {
	    		goiY[2] = 500000;
	    	} else {
	    		goiY[2] = (float) (Math.ceil(tienCanDua / 100000) * 100000);
	    	}
	    }
}
