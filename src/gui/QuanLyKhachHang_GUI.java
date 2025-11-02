package gui;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.Image;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import components.ConTent_JPanel;
import components.RoundedButton;
import components.RoundedTextField;
import dao.KhachHang_DAO;
import entity.KhachHang;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class QuanLyKhachHang_GUI extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel tableModel;
	private RoundedTextField txtMaKH;

	private RoundedTextField txtCCCD;

	private RoundedTextField txtSDT;

	private RoundedTextField txtEmail;

	private RoundedTextField txtTenKH;

	private RoundedButton btnTim;

	private RoundedButton btnSua;

	private RoundedButton btnThem;

	private RoundedButton btnHuy; // Nút Hủy

	// Khai báo DAO
	private KhachHang_DAO dskh = new KhachHang_DAO();

	private TableRowSorter<TableModel> sorter;

	private RoundedTextField txtTimTen;

	private RoundedTextField txtTimMa;

	private RoundedTextField txtTimSDT;

	KhachHang tempKhachHang = new KhachHang("");

	// Biến để theo dõi trạng thái của nút
	private boolean isDangChonKhachHang = false;

	/**
	 * Create the panel.
	 */
	public QuanLyKhachHang_GUI(TrangChu_GUI trangChu) {
		setBackground(SystemColor.text);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1440, 570);
		setLayout(null);

		JPanel jp_QL = new JPanel();
		jp_QL.setBackground(new Color(255, 255, 255));
		jp_QL.setLayout(null);
		jp_QL.setBounds(10, 10, 124, 28);
		add(jp_QL);

		// Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JLabel goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		jp_QL.add(goBackIconLabel);
		goBackIconLabel.setBounds(10, 0, 39, 27);
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ConTent_JPanel jpct = new ConTent_JPanel();
				trangChu.content.removeAll();
				trangChu.content.add(jpct);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});
		JLabel lb_quaylai = new JLabel("Quay lại");
		lb_quaylai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_quaylai.setBounds(45, 0, 69, 27);
		jp_QL.add(lb_quaylai);

		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setLayout(null);
		panelTimKiem.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTimKiem.setBounds(10, 47, 1400, 86);
		add(panelTimKiem);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 102, 153));
		panel_1.setBounds(0, 0, 1400, 36);
		panelTimKiem.add(panel_1);

		JLabel lbTimKiem = new JLabel("Tìm kiếm");
		lbTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lbTimKiem.setForeground(Color.WHITE);
		lbTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTimKiem.setBackground(new Color(0, 191, 255));
		lbTimKiem.setBounds(0, 0, 293, 36);
		panel_1.add(lbTimKiem);

		btnTim = new RoundedButton("Tìm", 15);
		btnTim.setBackground(SystemColor.activeCaptionBorder);
		btnTim.setForeground(SystemColor.desktop);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTim.setBounds(1283, 8, 85, 25);
		panel_1.add(btnTim);

		txtTimMa = new RoundedTextField(15);
		txtTimMa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimMa.setColumns(10);
		txtTimMa.setBounds(215, 49, 250, 30);
		panelTimKiem.add(txtTimMa);

		JLabel lblNewLabel_5 = new JLabel("Mã khách hàng");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(74, 46, 120, 30);
		panelTimKiem.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Tên khách hàng");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(501, 46, 126, 30);
		panelTimKiem.add(lblNewLabel_5_1);

		txtTimTen = new RoundedTextField(15);
		txtTimTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimTen.setColumns(10);
		txtTimTen.setBounds(647, 49, 250, 30);
		panelTimKiem.add(txtTimTen);

		JLabel lblNewLabel_5_2 = new JLabel("Số điện thoại");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2.setBounds(966, 46, 116, 30);
		panelTimKiem.add(lblNewLabel_5_2);

		txtTimSDT = new RoundedTextField(15);
		txtTimSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimSDT.setColumns(10);
		txtTimSDT.setBounds(1089, 49, 250, 30);
		panelTimKiem.add(txtTimSDT);

		JPanel panelThongTinKhachHang = new JPanel();
		panelThongTinKhachHang.setLayout(null);
		panelThongTinKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelThongTinKhachHang.setBounds(10, 143, 337, 407);
		add(panelThongTinKhachHang);

		JLabel lblNewLabel = new JLabel("Tên Khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(35, 113, 150, 20);
		panelThongTinKhachHang.add(lblNewLabel);

		txtTenKH = new RoundedTextField(15);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(35, 143, 250, 27);
		panelThongTinKhachHang.add(txtTenKH);

		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(35, 174, 150, 20);
		panelThongTinKhachHang.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(35, 243, 150, 20);
		panelThongTinKhachHang.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Căn cước công dân:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(35, 302, 150, 20);
		panelThongTinKhachHang.add(lblNewLabel_3);

		txtEmail = new RoundedTextField(15);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(35, 204, 250, 30);
		panelThongTinKhachHang.add(txtEmail);

		txtSDT = new RoundedTextField(15);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSDT.setColumns(10);
		txtSDT.setBounds(35, 268, 250, 30);
		panelThongTinKhachHang.add(txtSDT);

		txtCCCD = new RoundedTextField(15);
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(35, 321, 250, 30);
		panelThongTinKhachHang.add(txtCCCD);

		// Nút Thêm (sẽ chuyển thành "Xem thông tin" khi chọn khách hàng)
		btnThem = new RoundedButton("Thêm", 15);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThem.setBackground(new Color(51, 102, 153));
		btnThem.setBounds(20, 361, 90, 36);
		panelThongTinKhachHang.add(btnThem);

		btnSua = new RoundedButton("Sửa", 15);
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSua.setBackground(new Color(51, 102, 153));
		btnSua.setBounds(123, 361, 90, 36);
		panelThongTinKhachHang.add(btnSua);

		// Nút Hủy
		btnHuy = new RoundedButton("Hủy", 15);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHuy.setBackground(new Color(220, 53, 69)); // Màu đỏ
		btnHuy.setBounds(226, 361, 90, 36);
		btnHuy.setVisible(false); // Ẩn mặc định
		panelThongTinKhachHang.add(btnHuy);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(51, 102, 153));
		panel_2.setBounds(0, 0, 337, 36);
		panelThongTinKhachHang.add(panel_2);

		JLabel lblNewLabel_4 = new JLabel("Thông tin khách hàng");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(0, 0, 337, 36);
		panel_2.add(lblNewLabel_4);

		txtMaKH = new RoundedTextField(15);
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(35, 73, 250, 30);
		panelThongTinKhachHang.add(txtMaKH);

		JLabel lblMaKhachHang = new JLabel("Mã Khách hàng:");
		lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaKhachHang.setBounds(35, 46, 150, 20);
		panelThongTinKhachHang.add(lblMaKhachHang);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(357, 143, 1053, 407);
		add(scrollPane);

		table = new JTable();
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã khách hàng", "Tên khách hàng",
				"Email", "Số điện thoại", "Căn cước công dân" });
		sorter = new TableRowSorter<>(tableModel);

		table.setRowSorter(sorter);

		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		table.setRowHeight(25);

		// Thêm action listeners
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
		table.addMouseListener(this);

		datatoTable();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			// (Giữ nguyên code của btnThem)
			if (isDangChonKhachHang) {
				xemThongTinKhachHang();
			} else {
				themKhachHangMoi();
			}
		}

		if (o.equals(btnSua)) {
			if (isDangChonKhachHang) {
				// 1. Nếu đang ở chế độ xem thông tin => cho phép chỉnh sửa
				moCheDoChinhSua();
			} else {
				// 2. Đây là lúc nhấn nút "Lưu"
				int viewRow = table.getSelectedRow(); // Lấy view index
				if (viewRow == -1) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa!",
							"Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 3. Kiểm tra dữ liệu
				if (validData()) {
					// 4. Lấy dữ liệu từ form (Mã KH là không đổi)
					String maKH = txtMaKH.getText();
					String hoTen = txtTenKH.getText();
					String email = txtEmail.getText();
					String sdt = txtSDT.getText();
					String cccd = txtCCCD.getText();
					KhachHang kh = new KhachHang(maKH, hoTen, email, sdt, cccd);

					// 5. Gọi hàm update CSDL (sẽ được sửa ở bước 3)
					if (update(kh)) { // Gọi hàm update mới
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thành công!");

						// 6. Cập nhật model mà không tải lại
						int modelRow = table.convertRowIndexToModel(viewRow);
						tableModel.setValueAt(kh.getTenKH(), modelRow, 2);
						tableModel.setValueAt(kh.getEmail(), modelRow, 3);
						tableModel.setValueAt(kh.getSdt(), modelRow, 4);
						tableModel.setValueAt(kh.getCccd(), modelRow, 5);

						// 7. Quay lại chế độ thêm (hàm này có xóa field)
						chuyenVeCheThoThem();

						// KHÔNG gọi datatoTable() để giữ bộ lọc
					}
					// Nếu update() lỗi, nó đã tự show message
				}
			}
		}

		if (o.equals(btnTim)) {
			filterRows();
		}

		if (o.equals(btnHuy)) {
			// Quay lại chế độ thêm mới
			chuyenVeCheThoThem();
			table.clearSelection(); // Bỏ chọn dòng trong bảng
		}
	}

	private void moCheDoChinhSua() {
		isDangChonKhachHang = false; // Đổi về chế độ không còn “chỉ xem”

		txtTenKH.setEditable(true);
		txtEmail.setEditable(true);
		txtSDT.setEditable(true);
		txtCCCD.setEditable(true);

		btnSua.setText("Lưu"); // Đổi chữ "Sửa" -> "Lưu" cho dễ hiểu
		btnHuy.setVisible(true);
	}

	/**
	 * Phương thức thêm khách hàng mới
	 */
	private void themKhachHangMoi() {
		if (validData()) {
			KhachHang kh = revertKH();
			if (kh != null) {
				// Kiểm tra xem khách hàng đã tồn tại hay chưa
				KhachHang existingKH = dskh.getKhachHangTheoMaKH(kh.getMaKH());
				if (existingKH != null) {
					JOptionPane.showMessageDialog(this,
							"Khách hàng đã tồn tại trong cơ sở dữ liệu.",
							"Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						dskh.create(kh);
						tableModel.setRowCount(0);
						datatoTable();
						JOptionPane.showMessageDialog(this,
								"Thêm khách hàng thành công!",
								"Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this,
								"Lỗi khi thêm khách hàng vào cơ sở dữ liệu: " + e1.getMessage(),
								"Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			deleteField();
			chuyenVeCheThoThem();
		}
	}

	/**
	 * Phương thức xem thông tin khách hàng đã chọn
	 */
	private void xemThongTinKhachHang() {
		String thongTin = " 		THÔNG TIN KHÁCH HÀNG \n\n"
				 + "------------------------------------------------\n\n";
		thongTin += "Mã khách hàng: " + txtMaKH.getText() + "\n";
		thongTin += "Tên khách hàng: " + txtTenKH.getText() + "\n";
		thongTin += "Email: " + txtEmail.getText() + "\n";
		thongTin += "Số điện thoại: " + txtSDT.getText() + "\n";
		thongTin += "CCCD: " + txtCCCD.getText() + "\n";

		JOptionPane.showMessageDialog(this,
				thongTin,
				"Thông tin khách hàng",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Chuyển nút sang chế độ "Xem thông tin"
	 * Màu giống nút Sửa (51, 102, 153)
	 */
	private void chuyenVeCheDoXemThongTin() {
		isDangChonKhachHang = true;
		btnThem.setText("Xem");
		btnThem.setBackground(new Color(51, 102, 153)); // Màu giống nút Sửa

		// Hiển thị nút Hủy
		btnHuy.setVisible(true);

		// Vô hiệu hóa các trường nhập để tránh sửa nhầm
		txtTenKH.setEditable(false);
		txtEmail.setEditable(false);
		txtSDT.setEditable(false);
		txtCCCD.setEditable(false);
	}

	/**
	 * Chuyển nút về chế độ "Thêm"
	 */
	private void chuyenVeCheThoThem() {
		isDangChonKhachHang = false;
		btnThem.setText("Thêm");
		btnSua.setText("Sửa"); // Khôi phục lại nút Sửa

		btnThem.setBackground(new Color(51, 102, 153));
		btnHuy.setVisible(false);

		txtTenKH.setEditable(true);
		txtEmail.setEditable(true);
		txtSDT.setEditable(true);
		txtCCCD.setEditable(true);

		deleteField();
	}

	private void filterRows() {
		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
		String maKH = txtTimMa.getText().trim();
		String hoTen = txtTimTen.getText().trim();
		String sdt = txtTimSDT.getText().trim();

		// Lọc theo các điều kiện
		if (!maKH.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maKH, 1));
		}
		if (!hoTen.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + hoTen, 2));
		}
		if (!sdt.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + sdt, 4));
		}

		// Cập nhật bộ lọc
		if (filters.isEmpty()) {
			sorter.setRowFilter(null); // Nếu không có bộ lọc nào, xóa bộ lọc
		} else {
			sorter.setRowFilter(RowFilter.andFilter(filters));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int viewRow = table.getSelectedRow(); // Đây là view index
		if (viewRow != -1) {
			// Phải chuyển đổi sang model index để lấy đúng dữ liệu khi bảng đã lọc/sắp xếp
			int modelRow = table.convertRowIndexToModel(viewRow);

			// Lấy thông tin khách hàng từ model bằng model index
			String maKH = table.getModel().getValueAt(modelRow, 1).toString();
			KhachHang kh = dskh.getKhachHangTheoMaKH(maKH);

			// Hiển thị thông tin lên form
			if (kh != null) { // Đảm bảo khách hàng tồn tại
				txtMaKH.setText(kh.getMaKH());
				txtTenKH.setText(kh.getTenKH());
				txtEmail.setText(kh.getEmail());
				txtSDT.setText(kh.getSdt());
				txtCCCD.setText(kh.getCccd());

				// Chuyển nút "Thêm" thành "Xem thông tin"
				chuyenVeCheDoXemThongTin();
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với mã: " + maKH);
			}
		}
	}

	public boolean validData() {
		// Kiểm tra họ tên
		if (txtTenKH.getText().trim().isEmpty()) {
			showValidationError("Họ tên không được để trống",
					"Họ tên phải:\n" +
							"- Bắt đầu bằng chữ cái viết hoa có dấu\n" +
							"- Các chữ còn lại viết thường có dấu\n" +
							"- Mỗi từ cách nhau bởi 1 khoảng trắng\n" +
							"Ví dụ: Nguyễn Văn An, Trần Thị Bình");
			txtTenKH.requestFocus();
			return false;
		}
		try {
			tempKhachHang.setTenKH(txtTenKH.getText().trim());
		} catch (IllegalArgumentException e) {
			showValidationError(e.getMessage(),
					"Tên khách hàng phải:\n" +
							"- Bắt đầu bằng chữ cái viết hoa có dấu\n" +
							"- Các chữ còn lại viết thường có dấu\n" +
							"- Mỗi từ cách nhau bởi 1 khoảng trắng\n" +
							"Ví dụ: Nguyễn Văn An, Trần Thị Bình");
			txtTenKH.requestFocus();
			return false;
		}

		// Kiểm tra email
		if (txtEmail.getText().trim().isEmpty()) {
			showValidationError("Email không được để trống",
					"Email phải có định dạng:\n" +
							"- Bắt đầu bằng chữ cái hoặc số\n" +
							"- Có thể chứa dấu chấm (.)\n" +
							"- Kết thúc bằng @gmail.com\n" +
							"Ví dụ: nguyen.van.a@gmail.com, user123@gmail.com");
			txtEmail.requestFocus();
			return false;
		}
		try {
			tempKhachHang.setEmail(txtEmail.getText().trim());
		} catch (IllegalArgumentException e) {
			showValidationError(e.getMessage(),
					"Email phải có định dạng:\n" +
							"- Bắt đầu bằng chữ cái hoặc số\n" +
							"- Có thể chứa dấu chấm (.)\n" +
							"- Kết thúc bằng @gmail.com\n" +
							"Ví dụ: nguyen.van.a@gmail.com, user123@gmail.com");
			txtEmail.requestFocus();
			return false;
		}

		// Kiểm tra số điện thoại
		if (txtSDT.getText().trim().isEmpty()) {
			showValidationError("Số điện thoại không được để trống",
					"Số điện thoại phải:\n" +
							"- Có 10 chữ số\n" +
							"- Bắt đầu bằng 03, 05, 07, 08 hoặc 09\n" +
							"Ví dụ: 0123456789, 0987654321");
			txtSDT.requestFocus();
			return false;
		}
		try {
			tempKhachHang.setSdt(txtSDT.getText().trim());
		} catch (IllegalArgumentException e) {
			showValidationError(e.getMessage(),
					"Số điện thoại phải:\n" +
							"- Có 10 chữ số\n" +
							"- Bắt đầu bằng 03, 05, 07, 08 hoặc 09\n" +
							"Ví dụ: 0123456789, 0987654321");
			txtSDT.requestFocus();
			return false;
		}

		// Kiểm tra căn cước công dân
		if (txtCCCD.getText().trim().isEmpty()) {
			showValidationError("Căn cước công dân không được để trống",
					"CCCD phải:\n" +
							"- Có 12 chữ số\n" +
							"- Bắt đầu bằng 0\n" +
							"- 3 số tiếp theo từ 001 đến 096 (mã tỉnh/thành phố)\n" +
							"- Số thứ 4-5 từ 00 đến 03 (giới tính và thế kỷ sinh)\n" +
							"Ví dụ: 001203012345, 079301123456");
			txtCCCD.requestFocus();
			return false;
		}
		try {
			tempKhachHang.setCccd(txtCCCD.getText().trim());
		} catch (IllegalArgumentException e) {
			showValidationError(e.getMessage(),
					"CCCD phải:\n" +
							"- Có 12 chữ số\n" +
							"- Bắt đầu bằng 0\n" +
							"- 3 số tiếp theo từ 001 đến 096 (mã tỉnh/thành phố)\n" +
							"- Số thứ 4-5 từ 00 đến 03 (giới tính và thế kỷ sinh)\n" +
							"Ví dụ: 001203012345, 079301123456");
			txtCCCD.requestFocus();
			return false;
		}

		return true;
	}

	/**
	 * Hiển thị thông báo lỗi validation kèm hướng dẫn
	 */
	private void showValidationError(String errorMessage, String guideMessage) {
		String fullMessage = errorMessage + "\n\n" + guideMessage;
		JOptionPane.showMessageDialog(this, fullMessage, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
	}

	public KhachHang revertKH() {
		String maNV = generateMaKH();
		String hoTen = txtTenKH.getText();
		String email = txtEmail.getText();
		String sdt = txtSDT.getText();
		String cccd = txtCCCD.getText();

		KhachHang kh = new KhachHang(maNV, hoTen, email, sdt, cccd);

		return kh;
	}

	public boolean update(KhachHang kh) {
		try {
			dskh.update(kh);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật CSDL: " + e.getMessage(),
					"Lỗi CSDL", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public void datatoTable() {
		dskh.reset();
		ArrayList<KhachHang> list = dskh.docTuBang();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		int stt = 1;
		for (KhachHang kh : list) {
			tableModel.addRow(
					new Object[] { stt++, kh.getMaKH(), kh.getTenKH(), kh.getEmail(), kh.getSdt(), kh.getCccd() });
		}
	}

	public String generateMaKH() {
		dskh.reset();
		ArrayList<KhachHang> list = dskh.docTuBang();
		System.err.println(list.size());
		int sl = list.size() + 1;
		String maKH = String.format("KH%04d", sl);
		return maKH;
	}

	public void deleteField() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Khi nhấn vào vùng trống (không phải table), chuyển về chế độ thêm
		if (e.getSource() != table) {
			chuyenVeCheThoThem();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
