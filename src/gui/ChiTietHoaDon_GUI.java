package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import components.ConTent_JPanel;
import components.RoundedButton;
import components.RoundedTextField;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.Ve_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Ve;

import javax.swing.JButton;

public class ChiTietHoaDon_GUI extends JPanel implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaChiTiet;
	private JTextField txtMaHoaDon;
	private JTextField txtTu;
	private JTextField txtDen;
	private JTable table_CTHD;
	private JTable table_DSV;
	private ChiTietHoaDon_DAO dsCTHD = new ChiTietHoaDon_DAO();
	private HoaDon_DAO dsHD = new HoaDon_DAO();
	private DefaultTableModel model_CTHD;
	private Ve_DAO dsVe = new Ve_DAO();
	private DefaultTableModel model_DSV;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblTimKiem;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JLabel lblMaChiTiet;
	private JLabel lblMaHoaDon;
	private JPanel panel_1_1;
	private JPanel panel_2_1;
	private JLabel lblGia;
	private JLabel lblNewLabel_2;
	private JPanel panel_3_1;
	private JLabel lblTu;
	private JLabel lblDen;
	private JPanel panel_1_1_1;
	private JPanel panel_2_1_1;
	private JLabel lblGia_1;
	private JPanel panel_3_1_1;
	private JComboBox<String> comboBoxSL;
	private JScrollPane scrollPane_CTHD;
	private JLabel lblDSVe;
	private JScrollPane scrollPane_DSV;
	private JButton btn_XuatHD;
	private TableRowSorter<DefaultTableModel> sorter;
	private JLabel goBackIconLabel;
	private Color hoverLabelColor = new Color(0, 153, 255);
	private JLabel lbl_quayLai;

	public ChiTietHoaDon_GUI(QuanLyHoaDon_GUI quanLyHoaDon_GUI,TrangChu_GUI trangChu) {
		
		setBackground(SystemColor.window);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(30, 10, 146, 29);
		add(panel);
		panel.setLayout(null);

		//Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		panel.add(goBackIconLabel);
		goBackIconLabel.setBounds(10, 5, 39, 27);
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ConTent_JPanel jpct = new ConTent_JPanel();
				trangChu.content.removeAll();
				trangChu.content.add(jpct);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		goBackIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				goBackIconLabel.setForeground(hoverLabelColor); // Thay đổi màu khi đưa chuột vào

				// Đổi con trỏ chuột thành kiểu tay chỉ
				goBackIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				goBackIconLabel.setForeground(null); // Trở về màu mặc định khi chuột ra
				// Trả lại con trỏ chuột về mặc định
				goBackIconLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}}
				);

		//JLabel Quay lại
		lbl_quayLai = new JLabel("Quay lại");
		lbl_quayLai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_quayLai.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_quayLai.setBounds(45, 5, 69, 27);
		panel.add(lbl_quayLai);

		panel_1 = new JPanel();
		panel_1.setBounds(30, 45, 274, 167);
		add(panel_1);
		panel_1.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBounds(0, 34, 274, 133);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		lblMaChiTiet = new JLabel("Mã chi tiết:");
		lblMaChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaChiTiet.setBounds(10, 0, 93, 30);
		panel_3.add(lblMaChiTiet);

		txtMaChiTiet = new RoundedTextField(10);
		txtMaChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaChiTiet.setBounds(7, 27, 257, 30);
		panel_3.add(txtMaChiTiet);
		txtMaChiTiet.setColumns(10);

		lblMaHoaDon = new JLabel("Mã hóa đơn");
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaHoaDon.setBounds(10, 64, 110, 30);
		panel_3.add(lblMaHoaDon);

		txtMaHoaDon = new RoundedTextField(10);
		txtMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaHoaDon.setBounds(7, 92, 257, 30);
		panel_3.add(txtMaHoaDon);

		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 274, 32);
		panel_1.add(panel_2);
		panel_2.setBackground(new Color(51, 102, 153));
		panel_2.setLayout(null);

		lblTimKiem = new JLabel("Tìm kiếm");
		lblTimKiem.setForeground(new Color(255, 255, 255));
		lblTimKiem.setBounds(10, 0, 98, 32);
		panel_2.add(lblTimKiem);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\PTUD_BTLON\\TrainTicket-Project\\Code\\BanVeDTHP\\img\\Polygon_20.png"));
		lblNewLabel.setBounds(230, 0, 34, 30);
		panel_2.add(lblNewLabel);

		panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(30, 222, 274, 157);
		add(panel_1_1);

		panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(51, 102, 153));
		panel_2_1.setBounds(0, 0, 274, 32);
		panel_1_1.add(panel_2_1);

		lblGia = new JLabel("Giá");
		lblGia.setForeground(Color.WHITE);
		lblGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGia.setBounds(10, 0, 85, 29);
		panel_2_1.add(lblGia);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\PTUD_BTLON\\TrainTicket-Project\\Code\\BanVeDTHP\\img\\Polygon_20.png"));
		lblNewLabel_2.setBounds(230, 0, 34, 30);
		panel_2_1.add(lblNewLabel_2);

		panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBounds(0, 34, 274, 123);
		panel_1_1.add(panel_3_1);

		lblTu = new JLabel("Từ:");
		lblTu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTu.setBounds(10, 0, 93, 30);
		panel_3_1.add(lblTu);

		txtTu = new RoundedTextField(10);
		txtTu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTu.setBounds(7, 26, 257, 30);
		panel_3_1.add(txtTu);

		lblDen = new JLabel("Đến:");
		lblDen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDen.setBounds(10, 52, 110, 30);
		panel_3_1.add(lblDen);

		txtDen = new RoundedTextField(10);
		txtDen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDen.setBounds(7, 80, 257, 30);
		panel_3_1.add(txtDen);

		panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBounds(30, 389, 274, 95);
		add(panel_1_1_1);

		panel_3_1_1 = new JPanel();
		panel_3_1_1.setLayout(null);
		panel_3_1_1.setBounds(0, 34, 274, 61);
		panel_1_1_1.add(panel_3_1_1);

		comboBoxSL = new JComboBox<>();
		comboBoxSL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSL.setBounds(10, 10, 254, 31);
		comboBoxSL.addItem("1");
		comboBoxSL.addItem("2");
		comboBoxSL.addItem("3");
		comboBoxSL.addItem("4");
		comboBoxSL.setSelectedItem(null);
		panel_3_1_1.add(comboBoxSL);

		panel_2_1_1 = new JPanel();
		panel_2_1_1.setBounds(0, 0, 274, 32);
		panel_1_1_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(new Color(51, 102, 153));

		lblGia_1 = new JLabel("Số lượng");
		lblGia_1.setForeground(Color.WHITE);
		lblGia_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGia_1.setBounds(10, 0, 98, 32);
		panel_2_1_1.add(lblGia_1);


		JPanel panel_4 = new JPanel();
		panel_4.setBounds(314, 44, 1147, 288);
		add(panel_4);
		panel_4.setLayout(null);

		scrollPane_CTHD = new JScrollPane();
		scrollPane_CTHD.setBounds(0, 0, 1146, 288);
		panel_4.add(scrollPane_CTHD);

		table_CTHD = new JTable();
		table_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		model_CTHD = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"STT", "Mã chi tiết", "Mã hóa đơn", "Số lượng", "Thành tiền chưa thuế", "Thuế GTGT", "Tổng tiền"
				}
				);
		table_CTHD.setRowHeight(30);
		sorter = new TableRowSorter<>(model_CTHD);
		table_CTHD.setRowSorter(sorter);
		table_CTHD.setModel(model_CTHD);
		scrollPane_CTHD.setViewportView(table_CTHD);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(51, 102, 153));
		panel_5.setBounds(314, 350, 136, 29);
		add(panel_5);

		lblDSVe = new JLabel("Danh sách vé");
		panel_5.add(lblDSVe);
		lblDSVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDSVe.setForeground(new Color(255, 255, 255));

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(314, 389, 1147, 162);
		add(panel_6);
		panel_6.setLayout(null);

		scrollPane_DSV = new JScrollPane();
		scrollPane_DSV.setBounds(0, 0, 1147, 162);
		panel_6.add(scrollPane_DSV);

		table_DSV = new JTable();
		table_DSV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		model_DSV = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"STT", "Mã vé", "Mã Chi Tiết", "Hạng", "Khuyến mãi", "giá"
				}
				);
		table_DSV.setRowHeight(30);
		table_DSV.setModel(model_DSV);
		scrollPane_DSV.setViewportView(table_DSV);

		btn_XuatHD = new RoundedButton("Xuất hóa đơn", 15);
		btn_XuatHD.setForeground(new Color(255, 255, 255));
		btn_XuatHD.setBackground(new Color(51, 102, 153));
		btn_XuatHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_XuatHD.setBounds(95, 504, 151, 35);
		add(btn_XuatHD);
		btn_XuatHD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table_CTHD.getSelectedRow();
				if(row != -1) {
					ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(table_CTHD.getValueAt(row, 1).toString());
					HoaDon hoaDon = dsHD.getHoaDonTheoMaHoaDon(cthd.getHoaDon().getMaHoaDon());
					String pdfPath = "HoaDon/" + hoaDon.getMaHoaDon() + ".pdf";
					hoaDon.xuatHoaDon(pdfPath);
	
					// Kiểm tra xem Desktop có được hỗ trợ không
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							File pdfFile = new File(pdfPath);  // Tạo đối tượng File từ đường dẫn
							desktop.open(pdfFile);  // Mở file bằng ứng dụng mặc định
	
							// Hiển thị hộp thoại xác nhận sau khi mở file
							int confirm = JOptionPane.showConfirmDialog(
									null, 
									"Bạn có muốn xóa file hóa đơn vừa tạo không?", 
									"Xác nhận xóa file", 
									JOptionPane.YES_NO_OPTION
									);
	
							// Xóa file nếu người dùng chọn "Yes"
							if (confirm == JOptionPane.YES_OPTION) {
								if (pdfFile.delete()) {
									System.out.println("File PDF đã được xóa: " + pdfPath);
								} else {
									System.out.println("Không thể xóa file PDF.");
								}
							}
	
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						System.out.println("Mở file không được hỗ trợ trên hệ thống này.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn chi tiết hóa đơn muốn xuất", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		table_CTHD.addMouseListener(this);
		txtMaChiTiet.getDocument().addDocumentListener(new FilterListener());
		txtMaHoaDon.getDocument().addDocumentListener(new FilterListener());
		txtTu.getDocument().addDocumentListener(new FilterListener());
		txtDen.getDocument().addDocumentListener(new FilterListener());
		comboBoxSL.addActionListener(this);
		datatoTable_CTHD(quanLyHoaDon_GUI);

	}
	// Hàm tải dữ liệu vào bảng table_CTHD
	public void datatoTable_CTHD(QuanLyHoaDon_GUI qlhd) {
		dsVe.reset();
		dsCTHD.reset();
		if(qlhd.hoaDonTXemCT != null) {
			ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(qlhd.hoaDonTXemCT.getChiTiet().getMaChiTiet());
			float tongTienVe = 0;
			int stt = 1; // Biến đếm bắt đầu từ 1 cho STT
			for(Ve ve: cthd.getDsVe()) {
				tongTienVe+=ve.tinhGiaVe();
			}
			model_CTHD.addRow(new Object[] { 
					stt++, 
					cthd.getMaChiTiet(),
					cthd.getHoaDon().getMaHoaDon(),
					cthd.getSoLuong(), 
					dinhDangTienTe(tongTienVe),
					cthd.getThue(), // Hiển thị thuế dưới dạng %
					dinhDangTienTe(cthd.tinhTien()) // Định dạng tổng tiền bao gồm thuế
			});
		}
		else {
			ArrayList<ChiTietHoaDon> list = dsCTHD.docTuBang();
			ArrayList<Ve> danhSachVe = null;
	
			model_CTHD = (DefaultTableModel) table_CTHD.getModel();
			model_CTHD.setRowCount(0); // Xóa tất cả hàng trong bảng
			int stt = 1; // Biến đếm bắt đầu từ 1 cho STT
	
			for (ChiTietHoaDon cthd : list) {
				danhSachVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
				float tongTienVe = 0;
				for(Ve ve: danhSachVe) {
					tongTienVe+=ve.tinhGiaVe();
				}
				model_CTHD.addRow(new Object[] { 
						stt++, 
						cthd.getMaChiTiet(),
						cthd.getHoaDon().getMaHoaDon(),
						cthd.getSoLuong(), 
						dinhDangTienTe(tongTienVe),
						cthd.getThue(), // Hiển thị thuế dưới dạng %
						dinhDangTienTe(cthd.tinhTien()) // Định dạng tổng tiền bao gồm thuế
				});
	//			System.out.println(cthd);
	//			System.out.println(cthd.getMaChiTiet());
			}
		}
	}	
	public String dinhDangTienTe(double soTien) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return formatter.format(soTien);
	}
	// Hàm chuyển đổi từ định dạng tiền tệ về số
	private double parseCurrency(String formattedPrice) {
		try {
			// Xóa ký tự không cần thiết như dấu phẩy, khoảng trắng, v.v.
			formattedPrice = formattedPrice.replaceAll("[^\\d]", ""); // Chỉ giữ lại số
			return Double.parseDouble(formattedPrice); // Chuyển đổi về kiểu double
		} catch (NumberFormatException e) {
			return 0; // Nếu có lỗi, trả về 0 hoặc giá trị mặc định nào đó
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table_CTHD.getSelectedRow();
		if (row != -1) {
			model_DSV.setRowCount(0);
			ChiTietHoaDon cthd =dsCTHD.getCTHDTheoMaChiTiet(table_CTHD.getValueAt(row, 1).toString());
			ArrayList<Ve> danhSachVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
			int stt = 1; // Biến đếm bắt đầu từ 1 cho STT
			for (Ve ve : danhSachVe) {
				model_DSV.addRow(new Object[] { 
						stt++, 
						ve.getMaVe(),
						ve.getChiTiet().getMaChiTiet(),
						ve.getHang(), 
						ve.getKhuyenMai(),
						dinhDangTienTe(ve.tinhGiaVe())
				});
			}
		}

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

	// Lớp FilterListener để lắng nghe các thay đổi trong các ô tìm kiếm
	private class FilterListener implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			filterRows();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			filterRows();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			filterRows();
		}
	}
	// Hàm filter
	public void filterRows() {
		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
		float giaTu = 0;
		float giaDen = 0;
		String maChiTiet = txtMaChiTiet.getText().trim();
		String maHoaDon = txtMaHoaDon.getText().trim();
		String tu = txtTu.getText().trim();
		String den = txtDen.getText().trim();
		String soLuong = comboBoxSL.getSelectedItem() != null ? comboBoxSL.getSelectedItem().toString().trim() : "";
		try {
			if (!tu.isEmpty()) {
				giaTu = Float.parseFloat(tu);
			}
			if (!den.isEmpty()) {
				giaDen = Float.parseFloat(den);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị hợp lệ cho giá tiền.");
			return; // Dừng hàm nếu giá trị không hợp lệ
		}

		if (!maChiTiet.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maChiTiet, 1));
		}
		if (!maHoaDon.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maHoaDon, 2));
		}

		// Kiểm tra và lọc từ comboBoxSL
		if (!soLuong.isEmpty()) {
			try {
				int selectedQuantity = Integer.parseInt(soLuong);
				filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, selectedQuantity, 3));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn số lượng hợp lệ từ ComboBox.");
			}
		}
		// Lọc theo giá
		if (giaTu > 0 || giaDen > 0) {
			final float finalGiaTu = giaTu; // Tạo biến final từ giaTu
			final float finalGiaDen = giaDen; // Tạo biến final từ giaDen
			filters.add(new RowFilter<Object, Object>() {
				@Override
				public boolean include(Entry<? extends Object, ? extends Object> entry) {
					// Giả sử cột 6 chứa giá đã định dạng
					String formattedPrice = entry.getStringValue(6); // Lấy giá từ cột 6
					double price = parseCurrency(formattedPrice); // Chuyển đổi giá về dạng số

					boolean isWithinRange = true;
					if (finalGiaTu > 0) {
						isWithinRange = price >= finalGiaTu; // Lọc giá lớn hơn hoặc bằng `finalGiaTu`
					}
					if (finalGiaDen > 0) {
						isWithinRange = isWithinRange && (price <= finalGiaDen); // Lọc giá nhỏ hơn hoặc bằng `finalGiaDen`
					}

					return isWithinRange;
				}
			});
		}

		if (filters.isEmpty()) {
			sorter.setRowFilter(null); // Loại bỏ bộ lọc nếu không có điều kiện nào
		} else {
			sorter.setRowFilter(RowFilter.andFilter(filters)); // Kết hợp các bộ lọc
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!comboBoxSL.getSelectedItem().toString().isEmpty()) {
			filterRows();
		}
	}

}
