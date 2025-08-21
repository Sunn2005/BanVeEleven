package gui;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import components.RoundedButton;
import components.RoundedTextField;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Ve;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class QuanLyHoaDon_GUI extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaHD;
	private JTextField txtNhanVien;
	private JTextField txtKH;
	private JTable table;
	private JDateChooser dateChooserTu;
	private JDateChooser dateChooserDen;
	private DefaultTableModel model;
	private JCheckBox chckbxDaHoanTien;
	private JCheckBox chckbxDaHoanVe;
	private JCheckBox chckbxTatCa;
	private TableRowSorter<TableModel> sorter;
	private JTextField txtTu;
	private JTextField txtDen;
	private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
	private JButton btnTraVe_1;
	public HoaDon hoaDonTraVe;
	public HoaDon hoaDonTXemCT;
	private RoundedButton btnXemChiTiet;
	private RoundedButton btnXuatHoaDon;
	@SuppressWarnings("serial")
	public QuanLyHoaDon_GUI(TrangChu_GUI trangChu) {
		setBackground(Color.white);
		setBounds(0, 170, 1460, 610);
		setLayout(null);

		JPanel panelTimKiem_Tong = new JPanel();
		panelTimKiem_Tong.setBounds(10, 10, 300, 229);
		add(panelTimKiem_Tong);
		panelTimKiem_Tong.setLayout(null);

		JPanel panel_TimKiem = new JPanel();
		panel_TimKiem.setBackground(new Color(51, 102, 153));
		panel_TimKiem.setBounds(0, 0, 300, 34);
		panelTimKiem_Tong.add(panel_TimKiem);
		panel_TimKiem.setLayout(null);

		JLabel lblTimKiem = new JLabel("Tìm kiếm");
		lblTimKiem.setForeground(new Color(255, 255, 255));
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTimKiem.setBounds(10, 0, 95, 34);
		panel_TimKiem.add(lblTimKiem);

		JLabel lblMaHoaDon = new JLabel("Mã hóa đơn:");
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaHoaDon.setBounds(10, 38, 104, 34);
		panelTimKiem_Tong.add(lblMaHoaDon);

		JLabel lblNhanVien = new JLabel("Mã nhân viên:");
		lblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhanVien.setBounds(10, 104, 136, 27);
		panelTimKiem_Tong.add(lblNhanVien);

		JLabel lblKhachHang = new JLabel("Tên khách hàng:");
		lblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKhachHang.setBounds(10, 166, 136, 27);
		panelTimKiem_Tong.add(lblKhachHang);

		txtMaHD = new RoundedTextField(15);
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(11, 70, 266, 27);
		panelTimKiem_Tong.add(txtMaHD);

		txtNhanVien = new RoundedTextField(15);
		txtNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNhanVien.setColumns(10);
		txtNhanVien.setBounds(11, 129, 266, 27);
		panelTimKiem_Tong.add(txtNhanVien);

		txtKH = new RoundedTextField(15);
		txtKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtKH.setBounds(11, 190, 266, 27);
		panelTimKiem_Tong.add(txtKH);
		txtKH.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 249, 300, 121);
		add(panel_2);

		JPanel panelThoiGian = new JPanel();
		panelThoiGian.setLayout(null);
		panelThoiGian.setBackground(new Color(51, 102, 153));
		panelThoiGian.setBounds(0, 0, 300, 34);
		panel_2.add(panelThoiGian);

		JLabel lblThiGian = new JLabel("Thời gian");
		lblThiGian.setForeground(Color.WHITE);
		lblThiGian.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThiGian.setBounds(10, 0, 95, 34);
		panelThoiGian.add(lblThiGian);

		dateChooserTu = new JDateChooser();
		dateChooserTu.getCalendarButton().setBounds(245, 0, 21, 27);
		dateChooserTu.setDateFormatString("dd/MM/yyyy");
		dateChooserTu.setBounds(10, 44, 266, 27);
		panel_2.add(dateChooserTu);
		dateChooserTu.setLayout(null);

		txtTu = new JTextField();
		txtTu.setBounds(0, 0, 245, 27);
		dateChooserTu.add(txtTu);
		txtTu.setColumns(10);
		txtTu.setEditable(false);

		// Thêm sự kiện PropertyChangeListener cho dateChooserTu
		dateChooserTu.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = dateChooserTu.getDate();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				txtTu.setText(dateFormat.format(dateChooserTu.getDate()));
			} else {
				txtTu.setText(""); // Nếu không có ngày nào được chọn, làm rỗng JTextField
			}
		});

		dateChooserDen = new JDateChooser();
		dateChooserDen.getCalendarButton().setBounds(245, 0, 21, 27);
		dateChooserDen.setDateFormatString("dd/MM/yyyy");
		dateChooserDen.setBounds(10, 81, 266, 27);
		panel_2.add(dateChooserDen);
		dateChooserDen.setLayout(null);

		txtDen = new JTextField();
		txtDen.setBounds(0, 0, 245, 27);
		dateChooserDen.add(txtDen);
		txtDen.setColumns(10);
		txtDen.setEditable(false);

		// Thêm sự kiện PropertyChangeListener cho dateChooserDen
		dateChooserDen.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = dateChooserDen.getDate();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				txtDen.setText(dateFormat.format(dateChooserDen.getDate()));
			} else {
				txtDen.setText(""); // Nếu không có ngày nào được chọn, làm rỗng JTextField
			}
		});

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(10, 380, 300, 137);
		add(panel_2_1);

		JPanel panelTrangThai = new JPanel();
		panelTrangThai.setLayout(null);
		panelTrangThai.setBackground(new Color(51, 102, 153));
		panelTrangThai.setBounds(0, 0, 300, 34);
		panel_2_1.add(panelTrangThai);

		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setBounds(10, 0, 95, 34);
		panelTrangThai.add(lblTrngThi);
		lblTrngThi.setForeground(Color.WHITE);
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblTatCa = new JLabel("Tất cả");
		lblTatCa.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTatCa.setBounds(79, 44, 78, 19);
		panel_2_1.add(lblTatCa);

		JLabel lblDaHoanVe = new JLabel("Đã hoàn vé");
		lblDaHoanVe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDaHoanVe.setBounds(79, 62, 96, 34);
		panel_2_1.add(lblDaHoanVe);

		JLabel lblDaHoanTien = new JLabel("Đã hoàn tiền");
		lblDaHoanTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDaHoanTien.setBounds(79, 93, 118, 34);
		panel_2_1.add(lblDaHoanTien);

		chckbxTatCa = new JCheckBox("");
		chckbxTatCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxTatCa.setBounds(23, 34, 21, 34);
		panel_2_1.add(chckbxTatCa);
		chckbxTatCa.setSelected(true);

		chckbxDaHoanVe = new JCheckBox("");
		chckbxDaHoanVe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxDaHoanVe.setBounds(23, 62, 40, 28);
		panel_2_1.add(chckbxDaHoanVe);

		chckbxDaHoanTien = new JCheckBox("");
		chckbxDaHoanTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxDaHoanTien.setBounds(23, 93, 27, 28);
		panel_2_1.add(chckbxDaHoanTien);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(320, 10, 1119, 547);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1119, 547);
		panel_1.add(scrollPane);

		table = new JTable();
		model = new DefaultTableModel(new Object[] { "STT", "Mã hóa đơn", "Ngày lập hóa đơn", "Nhân viên lập",
				"Khách hàng", "Đã hoàn vé", "Đã hoàn tiền" }, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// Xác định kiểu dữ liệu cho từng cột
				switch (columnIndex) {
				case 5:
				case 6:
					return Boolean.class; // Các cột 5 và 6 là checkbox
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 5 && column != 6; // Không cho chỉnh sửa các cột checkbox
			}
		};
		// Tạo một TableRowSorter để sử dụng cho việc lọc dữ liệu
		sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnXemChiTiet = new RoundedButton("Xem chi tiết", 15);
		btnXemChiTiet.setBackground(new Color(51, 102, 153));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXemChiTiet.setBounds(10, 527, 100, 30);
		btnXemChiTiet.setForeground(new Color(255, 255, 255));
		add(btnXemChiTiet);

		btnXuatHoaDon = new RoundedButton("Xuất hóa đơn", 15);
		btnXuatHoaDon.setForeground(new Color(255, 255, 255));
		btnXuatHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXuatHoaDon.setBounds(120, 527, 100, 30);
		btnXuatHoaDon.setBackground(new Color(51, 102, 153));
		add(btnXuatHoaDon);

		btnXemChiTiet.addActionListener(this);
		btnXuatHoaDon.addActionListener(this);

		// Lắng nghe sự kiện nhập liệu cho các ô tìm kiếm
		txtMaHD.getDocument().addDocumentListener(new FilterListener());
		txtNhanVien.getDocument().addDocumentListener(new FilterListener());
		txtKH.getDocument().addDocumentListener(new FilterListener());
		txtTu.getDocument().addDocumentListener(new FilterListener());
		txtDen.getDocument().addDocumentListener(new FilterListener());
		chckbxDaHoanVe.addActionListener(this);
		chckbxDaHoanTien.addActionListener(this);
		chckbxTatCa.addActionListener(this);
		
		
		btnTraVe_1 = new RoundedButton("Trả vé", 15);
		btnTraVe_1.setForeground(new Color(255, 255, 255));
		btnTraVe_1.setForeground(new Color(255, 255, 255));
		btnTraVe_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTraVe_1.setBounds(230, 527, 85, 30);
		btnTraVe_1.setBackground(new Color(51, 102, 153));
		add(btnTraVe_1);
		btnTraVe_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) { // Kiểm tra nếu có dòng nào được chọn
					hoaDonTraVe = hoaDon_DAO.getHoaDonTheoMaHoaDon(table.getValueAt(row, 1).toString());
					ChiTietHoaDon cthd = chiTietHoaDon_DAO.getCTHDTheoMaChiTiet(hoaDonTraVe.getChiTiet().getMaChiTiet());
					boolean tapThe = cthd.getDsVe().size() > 1;
					for(Ve ve: cthd.getDsVe()) {
						if(!ve.kiemTraHoanTien() || ve.isTrangThai()) {
							JOptionPane.showMessageDialog(null, "Hóa đơn không khả dụng", "Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}else {
							if(tapThe && !ve.hoanVe(tapThe)) {
								JOptionPane.showMessageDialog(null, "Chỉ có thể hoàn vé (tập thể) từ 24-72 tiếng trước giờ đi", "Thông báo", JOptionPane.WARNING_MESSAGE);
								return;
							}
							if(!tapThe && !ve.hoanVe(tapThe)) {
								JOptionPane.showMessageDialog(null, "Chỉ có thể hoàn vé (cá nhân) từ 4-24 tiếng trước giờ đi", "Thông báo", JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
					}
					if (hoaDonTraVe != null) {
						if(!hoaDonTraVe.getDaHoanVe()) {
							TraVe_GUI traVe_GUI = new TraVe_GUI(QuanLyHoaDon_GUI.this, trangChu);
							trangChu.content.removeAll();
							trangChu.content.add(traVe_GUI);
							trangChu.content.revalidate();
							trangChu.content.repaint();
						}
						if(hoaDonTraVe.getDaHoanVe()) {
							JOptionPane.showMessageDialog(null, "Hóa đơn đã hoàn vé", "Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn muốn trả", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnXemChiTiet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row != -1) { // Kiểm tra nếu có dòng nào được chọn
					hoaDonTXemCT = hoaDon_DAO.getHoaDonTheoMaHoaDon(table.getValueAt(row, 1).toString());
					if (hoaDonTXemCT != null) {
						ChiTietHoaDon_GUI chiTietHoaDon_GUI = new ChiTietHoaDon_GUI(QuanLyHoaDon_GUI.this,trangChu);
						trangChu.content.removeAll();
						trangChu.content.add(chiTietHoaDon_GUI);
						trangChu.content.revalidate();
						trangChu.content.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn muốn xem chi tiết hóa đơn", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		datatoTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXuatHoaDon)) {
			int row = table.getSelectedRow();
			HoaDon hoaDon = hoaDon_DAO.getHoaDonTheoMaHoaDon(table.getValueAt(row, 1).toString());
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
		}
		if (o.equals(chckbxTatCa)) {
			chckbxDaHoanTien.setSelected(false);
			chckbxDaHoanVe.setSelected(false);
			filterRows();
		}
		if (o.equals(chckbxDaHoanVe)) {
			chckbxTatCa.setSelected(false);
			filterRows();
		}
		if (o.equals(chckbxDaHoanTien)) {
			chckbxTatCa.setSelected(false);
			filterRows();
		}
	}

	// Hàm tải dữ liệu vào bảng
	public void datatoTable() {
		hoaDon_DAO.reset();
		KhachHang_DAO dsKH = new KhachHang_DAO();
		ArrayList<HoaDon> list = hoaDon_DAO.docTuBang();
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Xóa tất cả hàng trong bảng
		int count = 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");
		for (HoaDon hd : list) {
			model.addRow(new Object[] { count++, hd.getMaHoaDon(), hd.getNgayLapHoaDon().format(formatter),
					hd.getNhanVien().getMaNV(), dsKH.getKhachHangTheoMaKH(hd.getKhachHang().getMaKH()).getTenKH(),
					hd.getDaHoanVe(), hd.getDaHoanTien() });
		}
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

	// Hàm để thực hiện lọc
	private void filterRows() {
		String textMaHD = txtMaHD.getText().trim();
		String textNhanVien = txtNhanVien.getText().trim();
		String textKH = txtKH.getText().trim();
		String textTu = txtTu.getText().trim();
		String textDen = txtDen.getText().trim();

		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

		// Thêm bộ lọc nếu các ô không trống
		if (!textMaHD.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + textMaHD, 1)); // Lọc theo cột "Mã HD"
		}
		if (!textNhanVien.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + textNhanVien, 3)); // Lọc theo cột "Mã NV"
		}
		if (!textKH.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + textKH, 4)); // Lọc theo cột "Tên KH"
		}
		// Lọc theo ngày từ
		if (!textTu.isEmpty()) {
			Date dateTu = parseDate(textTu);
			if (dateTu != null) {
				filters.add(new RowFilter<Object, Object>() {
					@Override
					public boolean include(Entry<? extends Object, ? extends Object> entry) {
						Date entryDate = parseDate(entry.getStringValue(2).substring(8)); // Giả sử cột 2 là cột ngày
						return entryDate != null && !entryDate.before(dateTu);
					}
				});
			}
		}

		// Lọc theo ngày đến
		if (!textDen.isEmpty()) {
			Date dateDen = parseDate(textDen);
			if (dateDen != null) {
				filters.add(new RowFilter<Object, Object>() {
					@Override
					public boolean include(Entry<? extends Object, ? extends Object> entry) {
						Date entryDate = parseDate(entry.getStringValue(2).substring(8)); // Giả sử cột 2 là cột ngày
						return entryDate != null && !entryDate.after(dateDen);
					}
				});
			}
		}

		// Lọc theo checkbox "Đã hoàn vé" và "Đã hoàn tiền"
		if (chckbxDaHoanVe.isSelected() && chckbxDaHoanTien.isSelected()) {
			// Khi cả hai checkbox đều được chọn
			filters.add(new RowFilter<Object, Object>() {
				@Override
				public boolean include(Entry<? extends Object, ? extends Object> entry) {
					boolean daHoanVe = Boolean.parseBoolean(entry.getStringValue(5)); // Cột 5 là checkbox "Đã hoàn vé"
					boolean daHoanTien = Boolean.parseBoolean(entry.getStringValue(6)); // Cột 6 là checkbox "Đã hoàn tiền"
					return daHoanVe && daHoanTien; // Hiển thị khi cả hai đều true
				}
			});
		} else if (chckbxDaHoanVe.isSelected()) {
			// Khi chỉ checkbox "Đã hoàn vé" được chọn
			filters.add(new RowFilter<Object, Object>() {
				@Override
				public boolean include(Entry<? extends Object, ? extends Object> entry) {
					return Boolean.parseBoolean(entry.getStringValue(5)); // Cột 5 là checkbox "Đã hoàn vé"
				}
			});
		} else if (chckbxDaHoanTien.isSelected()) {
			// Khi chỉ checkbox "Đã hoàn tiền" được chọn
			filters.add(new RowFilter<Object, Object>() {
				@Override
				public boolean include(Entry<? extends Object, ? extends Object> entry) {
					return Boolean.parseBoolean(entry.getStringValue(6)); // Cột 6 là checkbox "Đã hoàn tiền"
				}
			});
		}

		// Nếu không có checkbox nào được chọn
		if (!chckbxDaHoanVe.isSelected() && !chckbxDaHoanTien.isSelected() && !chckbxTatCa.isSelected()) {
			filters.add(new RowFilter<Object, Object>() {
				@Override
				public boolean include(Entry<? extends Object, ? extends Object> entry) {
					boolean daHoanVe = Boolean.parseBoolean(entry.getStringValue(5)); // Cột 5 là checkbox "Đã hoàn vé"
					boolean daHoanTien = Boolean.parseBoolean(entry.getStringValue(6)); // Cột 6 là checkbox "Đã hoàn tiền"
					return !daHoanVe && !daHoanTien; // Hiển thị khi cả hai đều false
				}
			});
		}

		// Nếu checkbox "Tất cả" được chọn
		if (chckbxTatCa.isSelected()) {
			sorter.setRowFilter(null);
		}

		if (filters.isEmpty()) {
			sorter.setRowFilter(null); // Loại bỏ bộ lọc nếu không có điều kiện nào
		} else {
			sorter.setRowFilter(RowFilter.andFilter(filters)); // Kết hợp các bộ lọc
		}
	}

	private Date parseDate(String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			// Chuyển đổi chuỗi thành đối tượng Date
			Date date = dateFormat.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	private void focusTxtField(final JTextField txtField, final String placeholder) {
	    // Đặt màu chữ mờ khi chưa có focus
	    txtField.setForeground(SystemColor.textInactiveText);
	    txtField.setText(placeholder); // Đặt chữ mặc định cho trường văn bản

	    // Thêm listener để xử lý sự kiện focus
	    txtField.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            // Khi có focus, xóa văn bản nếu đang là placeholder
	            if (txtField.getText().equals(placeholder)) {
	                txtField.setText(""); // Xóa văn bản mặc định
	                txtField.setForeground(Color.BLACK); // Đổi màu chữ thành đen
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            // Khi mất focus, hiển thị lại placeholder nếu trường trống
	            if (txtField.getText().isEmpty()) {
	                txtField.setForeground(SystemColor.textInactiveText); // Màu mờ
	                txtField.setText(placeholder); // Thiết lập lại placeholder
	            }
	        }
	    });
	}
}
