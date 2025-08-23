package gui;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import components.ComboBoxRenderer;
import components.ConTent_JPanel;
import components.RoundedButton;
import components.RoundedTextField;

import javax.swing.JRadioButton;

import dao.NhanVien_DAO;
import entity.Ca;
import entity.NhanVien;

import com.toedter.calendar.JDateChooser;

public class QuanLyNhanVien_GUI extends JPanel implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel jp_quayLai;
	private JPanel jp_headerThongTin;
	private JPanel jp_thongTinNV;
	private JPanel jp_contentThongTin;
	private JLabel goBackIconLabel;
	private JLabel lbl_quayLai;
	private JLabel lbl_MaNV;
	private JLabel lbl_HoTen;
	private JLabel lbl_GioiTinh;
	private JLabel lbl_NgaySinh;
	private JLabel lbl_CCCD;
	private JLabel lbl_SDT;
	private JLabel lbl_Email;
	private JLabel lbl_ChucVu;
	private JLabel lbl_Ca;
	private JLabel lbl_TrangThai;
	private JLabel lbl_tieuDeTT;
	private JLabel downIconLabel1;
	private JTextField textField_MaNV;
	private JTextField textField_HoTen;
	private JTextField textField_CCCD;
	private JTextField textField_SDT;
	private JTextField textField_Email;

	private JTable table_NV;
	private JScrollPane scrollPane;
	private JRadioButton cb_nam;
	private JRadioButton cb_nu;
	private JRadioButton cb_dangLam;
	private JRadioButton cb_nghiLam;
	private NhanVien_DAO dsnv = new NhanVien_DAO();
	private Color hoverLabelColor = new Color(0, 153, 255);
	private DefaultTableModel model;
	private JComboBox<String> comboBox_Ca;
	private JDateChooser dateChooser_NgaySinh;
	private JComboBox<String> comboBox_TimTheoMaNV;
	private JComboBox<String> comboBox_ChucVu;
	private TableRowSorter<TableModel> sorter;
	private ButtonGroup group;
	private ButtonGroup group1;
	private RoundedButton btnThem;
	private RoundedButton btnSua;
	private RoundedButton btnTim;

	NhanVien tempNhanVien = new NhanVien(""); // Đối tượng tạm thời
	
	public QuanLyNhanVien_GUI(TrangChu_GUI trangChu) {
		setBackground(SystemColor.text);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);

		//JPanel quay lại
		jp_quayLai = new JPanel();
		jp_quayLai.setBackground(SystemColor.text);
		jp_quayLai.setBounds(10, 10, 124, 28);
		add(jp_quayLai);
		jp_quayLai.setLayout(null);

		//Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		jp_quayLai.add(goBackIconLabel);
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
		lbl_quayLai.setBounds(45, 0, 69, 27);
		jp_quayLai.add(lbl_quayLai);


		//JPane thông tin nhân viên
		jp_thongTinNV = new JPanel();
		jp_thongTinNV.setBounds(10, 39, 327, 526);
		add(jp_thongTinNV);
		jp_thongTinNV.setLayout(null);

		//Icon xổ xuống
		ImageIcon downIcon1 = new ImageIcon(getClass().getResource("/img/Polygon_20.png"));
		Image scaledDown1 = downIcon1.getImage().getScaledInstance(20 ,20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo

		jp_contentThongTin = new JPanel();
		jp_contentThongTin.setBounds(0, 31, 327, 495);
		jp_thongTinNV.add(jp_contentThongTin);
		jp_contentThongTin.setLayout(null);

		textField_MaNV = new RoundedTextField(10);
		textField_MaNV.setBounds(129, 18, 188, 25);
		textField_MaNV.setEditable(false);
		jp_contentThongTin.add(textField_MaNV);
		textField_MaNV.setColumns(10);

		textField_HoTen = new RoundedTextField(10);
		textField_HoTen.setColumns(10);
		textField_HoTen.setBounds(129, 66, 188, 25);
		jp_contentThongTin.add(textField_HoTen);

		textField_CCCD = new RoundedTextField(10);
		textField_CCCD.setColumns(10);
		textField_CCCD.setBounds(129, 194, 188, 25);
		jp_contentThongTin.add(textField_CCCD);

		textField_SDT = new RoundedTextField(10);
		textField_SDT.setColumns(10);
		textField_SDT.setBounds(129, 244, 188, 25);
		jp_contentThongTin.add(textField_SDT);

		textField_Email = new RoundedTextField(10);
		textField_Email.setColumns(10);
		textField_Email.setBounds(129, 290, 188, 25);
		jp_contentThongTin.add(textField_Email);

		btnThem = new RoundedButton("Thêm", 15);
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(51, 102, 153));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.setBounds(26, 458, 85, 27);
		jp_contentThongTin.add(btnThem);

		btnSua = new RoundedButton("Sửa", 15);
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setBackground(new Color(51, 102, 153));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setBounds(126, 458, 85, 27);
		jp_contentThongTin.add(btnSua);

		lbl_MaNV = new JLabel("Mã nhân viên");
		lbl_MaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_MaNV.setBounds(10, 18, 101, 25);
		jp_contentThongTin.add(lbl_MaNV);

		lbl_HoTen = new JLabel("Họ tên");
		lbl_HoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_HoTen.setBounds(10, 64, 101, 25);
		jp_contentThongTin.add(lbl_HoTen);

		lbl_GioiTinh = new JLabel("Giới tính");
		lbl_GioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_GioiTinh.setBounds(10, 105, 101, 25);
		jp_contentThongTin.add(lbl_GioiTinh);

		lbl_NgaySinh = new JLabel("Ngày sinh");
		lbl_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_NgaySinh.setBounds(10, 149, 101, 25);
		jp_contentThongTin.add(lbl_NgaySinh);

		lbl_CCCD = new JLabel("CCCD");
		lbl_CCCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_CCCD.setBounds(10, 194, 101, 25);
		jp_contentThongTin.add(lbl_CCCD);

		lbl_SDT = new JLabel("Số điện thoại");
		lbl_SDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_SDT.setBounds(10, 244, 101, 25);
		jp_contentThongTin.add(lbl_SDT);

		lbl_Email = new JLabel("Email");
		lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Email.setBounds(10, 290, 101, 25);
		jp_contentThongTin.add(lbl_Email);

		lbl_ChucVu = new JLabel("Chức vụ");
		lbl_ChucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ChucVu.setBounds(10, 336, 101, 25);
		jp_contentThongTin.add(lbl_ChucVu);

		lbl_Ca = new JLabel("Ca");
		lbl_Ca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Ca.setBounds(10, 382, 101, 25);
		jp_contentThongTin.add(lbl_Ca);

		lbl_TrangThai = new JLabel("Trạng thái");
		lbl_TrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_TrangThai.setBounds(10, 417, 101, 25);
		jp_contentThongTin.add(lbl_TrangThai);

		cb_nam = new JRadioButton("Nam");
		cb_nam.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cb_nam.setToolTipText("");
		cb_nam.setBounds(129, 109, 52, 21);
		jp_contentThongTin.add(cb_nam);

		cb_nu = new JRadioButton("Nữ");
		cb_nu.setToolTipText("");
		cb_nu.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cb_nu.setBounds(217, 109, 52, 21);
		jp_contentThongTin.add(cb_nu);

		// Tạo ButtonGroup để nhóm hai JRadioButton
		group = new ButtonGroup();
		group.add(cb_nam);
		group.add(cb_nu);

		cb_dangLam = new JRadioButton("Đang làm");
		cb_dangLam.setToolTipText("");
		cb_dangLam.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cb_dangLam.setBounds(129, 421, 70, 21);
		jp_contentThongTin.add(cb_dangLam);

		cb_nghiLam = new JRadioButton("Nghỉ làm");
		cb_nghiLam.setToolTipText("");
		cb_nghiLam.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cb_nghiLam.setBounds(217, 421, 69, 21);
		jp_contentThongTin.add(cb_nghiLam);

		// Tạo ButtonGroup để nhóm hai JRadioButton
		group1 = new ButtonGroup();
		group1.add(cb_dangLam);
		group1.add(cb_nghiLam);
	
		btnTim = new RoundedButton("Tìm", 15);
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(51, 102, 153));
		btnTim.setBounds(232, 458, 85, 27);
		jp_contentThongTin.add(btnTim);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));

		//JPane header tiêu đề của thông tin nhân viên
		jp_headerThongTin = new JPanel();
		jp_headerThongTin.setBounds(0, 0, 327, 32);
		jp_thongTinNV.add(jp_headerThongTin);
		jp_headerThongTin.setBackground(new Color(51, 102, 153));
		jp_headerThongTin.setLayout(null);
		//JLabel tiêu đề 
		lbl_tieuDeTT = new JLabel("Thông tin nhân viên");
		lbl_tieuDeTT.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tieuDeTT.setForeground(new Color(255, 255, 255));
		lbl_tieuDeTT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_tieuDeTT.setBounds(10, 0, 153, 35);
		jp_headerThongTin.add(lbl_tieuDeTT);
		downIconLabel1 = new JLabel(new ImageIcon(scaledDown1));
		downIconLabel1.setBounds(287, 0, 30, 35);
		jp_headerThongTin.add(downIconLabel1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 78, 1113, 487);
		add(scrollPane);

		table_NV = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"STT", "Mã nhân viên", "Họ tên", "Ngày sinh","Giới tính", "Ca", "CCCD", "Email", "SĐT", "Trạng thái", "Chức vụ"
				}
				);
		sorter = new TableRowSorter<>(model);
		table_NV.setRowSorter(sorter);
		table_NV.setModel(model);
		scrollPane.setViewportView(table_NV);
		table_NV.setRowHeight(25); // Set chiều cao hàng

		// Tạo JComboBox Ca trong JPanel thông tin nhân viên
		comboBox_Ca = new JComboBox<>();
		comboBox_Ca.setBounds(130, 382, 187, 25);
		comboBox_Ca.addItem("CA01");
		comboBox_Ca.addItem("CA02");
		comboBox_Ca.addItem("CA03");
		jp_contentThongTin.add(comboBox_Ca);

		dateChooser_NgaySinh = new JDateChooser();
		dateChooser_NgaySinh.setBounds(129, 149, 188, 25);
		jp_contentThongTin.add(dateChooser_NgaySinh);
		dateChooser_NgaySinh.setDateFormatString("dd/MM/yyyy");

		comboBox_ChucVu = new JComboBox<String>();
		comboBox_ChucVu.setBounds(129, 340, 187, 25);
		comboBox_ChucVu.addItem("Quản lý");
		comboBox_ChucVu.addItem("Nhân viên");
		jp_contentThongTin.add(comboBox_ChucVu);

		// Tạo JComboBox cho cột "Giới tính"
		JComboBox<String> comboBoxGioiTinh = new JComboBox<>();
		comboBoxGioiTinh.addItem("Nam");
		comboBoxGioiTinh.addItem("Nữ");

		// Lấy cột "Giới tính" từ bảng
		TableColumn gioiTinhColumn = table_NV.getColumnModel().getColumn(4); // 4 là chỉ số của cột "Gioi tính"
		// Thiết lập JComboBox làm editor cho cột "Giới tính"
		gioiTinhColumn.setCellEditor(new DefaultCellEditor(comboBoxGioiTinh));
		// Thiết lập renderer cho cột để hiển thị JComboBox
		gioiTinhColumn.setCellRenderer(new ComboBoxRenderer(comboBoxGioiTinh));

		// Tạo JComboBox cho cột "Ca"
		JComboBox<String> comboBoxCa = new JComboBox<>();
		comboBoxCa.addItem("CA01");
		comboBoxCa.addItem("CA02");
		comboBoxCa.addItem("CA03");

		// Lấy cột "Ca" từ bảng
		TableColumn caColumn = table_NV.getColumnModel().getColumn(5); // 5 là chỉ số của cột "Ca"
		// Thiết lập JComboBox làm editor cho cột "Giới tính"
		caColumn.setCellEditor(new DefaultCellEditor(comboBoxCa));
		// Thiết lập renderer cho cột để hiển thị JComboBox
		caColumn.setCellRenderer(new ComboBoxRenderer(comboBoxCa));

		// Tạo JComboBox cho cột "Trạng Thái"
		JComboBox<String> comboBoxTrangThai = new JComboBox<>();
		comboBoxTrangThai.addItem("Đang làm");
		comboBoxTrangThai.addItem("Nghỉ làm");

		// Lấy cột "Trạng Thái" từ bảng
		TableColumn trangThaiColumn = table_NV.getColumnModel().getColumn(9); // 9 là chỉ số của cột "Trạng Thái"
		// Thiết lập JComboBox làm editor cho cột "Trạng Thái"
		trangThaiColumn.setCellEditor(new DefaultCellEditor(comboBoxTrangThai));
		// Thiết lập renderer cho cột để hiển thị JComboBox
		trangThaiColumn.setCellRenderer(new ComboBoxRenderer(comboBoxTrangThai));

		// Tạo JComboBox cho cột "Chức vụ"
		JComboBox<String> comboBoxChucVu = new JComboBox<>();
		comboBoxChucVu.addItem("Quản lý");
		comboBoxChucVu.addItem("Nhân viên");

		// Lấy cột "Chức vụ" từ bảng
		TableColumn chucVuColumn = table_NV.getColumnModel().getColumn(10); // 10 là chỉ số của cột "Chức vụ"
		// Thiết lập JComboBox làm editor cho cột "Trạng Thái"
		chucVuColumn.setCellEditor(new DefaultCellEditor(comboBoxChucVu));
		// Thiết lập renderer cho cột để hiển thị JComboBox
		chucVuColumn.setCellRenderer(new ComboBoxRenderer(comboBoxChucVu));


		//Thêm sự kiện table listener
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
				int column =e.getColumn();
				if(column == 4) {
					String newValue = (String) table_NV.getValueAt(row, column);
					if(newValue.equalsIgnoreCase("Nam")) {
						cb_nam.setSelected(true);
					}else {
						cb_nu.setSelected(true);
					}
				}
				if(column == 5) {
					String newValue = (String) table_NV.getValueAt(row, column);
					if(newValue.equalsIgnoreCase("CA01")) {
						comboBox_Ca.setSelectedIndex(0);
					}
					if(newValue.equalsIgnoreCase("CA02"))
					{
						comboBox_Ca.setSelectedIndex(1);
					}
					if(newValue.equalsIgnoreCase("CA03")) {
						comboBox_Ca.setSelectedIndex(2);
					}
				}
				if(column == 9) {
					String newValue1 = (String) table_NV.getValueAt(row, column);
					if(newValue1.equalsIgnoreCase("Đang Làm")) {
						cb_dangLam.setSelected(true);
					}else if (newValue1.equalsIgnoreCase("Nghỉ Làm")) {
						cb_nghiLam.setSelected(true);
					}
				}
				if(column == 10) {
					String newValue = (String) table_NV.getValueAt(row, column);
					if(newValue.equalsIgnoreCase("Quản lý")) {
						comboBox_ChucVu.setSelectedIndex(0);
					}
					else
					{
						comboBox_ChucVu.setSelectedIndex(1);
					}
				}
			}
		});

		// Tạo JComboBox Hiển thị mã nhân viên 
		comboBox_TimTheoMaNV = new JComboBox<String>();
		comboBox_TimTheoMaNV.setBounds(347, 39, 155, 29);
		add(comboBox_TimTheoMaNV);
		// Thêm các mã nhân viên thực tế vào JComboBox
		comboBox_TimTheoMaNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String selectedObj = cb.getSelectedItem() != null ? cb.getSelectedItem().toString() : null;
				NhanVien nv = dsnv.getNhanVienTheoMaNV(selectedObj);
				if (nv != null) {
					int rowIndex = -1;
					for (int i = 0; i < table_NV.getRowCount(); i++) {
						// Kiểm tra cột Mã nhân viên tại chỉ mục 1
						if (table_NV.getValueAt(i, 1).equals(nv.getMaNV())) {
							rowIndex = i;
							break;
						}
					}
					if (rowIndex != -1) {
						table_NV.setRowSelectionInterval(rowIndex, rowIndex);
						textField_MaNV.setText(nv.getMaNV());
						textField_HoTen.setText(nv.getTenNV());
						// Kiểm tra giới tính
						if (nv.isGioiTinh()) {
							cb_nam.setSelected(true);
							cb_nu.setSelected(false);
						} else {
							cb_nam.setSelected(false);
							cb_nu.setSelected(true);
						}

						// Cập nhật ngày sinh
						dateChooser_NgaySinh.setDate(Date.from(nv.getNgaySinh().atStartOfDay(ZoneId.systemDefault()).toInstant()));

						textField_CCCD.setText(nv.getCccd());
						textField_SDT.setText(nv.getSdt());
						textField_Email.setText(nv.getEmail());
						comboBox_Ca.setSelectedItem(nv.getCa().getMaCa());
						if (nv.isTrangThai()) {
							cb_dangLam.setSelected(true);
							cb_nghiLam.setSelected(false);
						} else {
							cb_dangLam.setSelected(false);
							cb_nghiLam.setSelected(true);
						}
						if(nv.getChucVu() == 1) {
							comboBox_ChucVu.setSelectedIndex(0);
						}
						else {
							comboBox_ChucVu.setSelectedIndex(1);
						}

					}
				}
			}
		});

		//Thêm sự kiện cho các nút và bảng
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		table_NV.addMouseListener(this);
		cb_nam.addActionListener(this);
		cb_nu.addActionListener(this);
		cb_dangLam.addActionListener(this);
		cb_nghiLam.addActionListener(this);	
		btnTim.addActionListener(this);
		datatoTable();
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btnThem)) {
			if (validData()) {
				NhanVien nv = revertNV();
				if (nv != null) {
					// Kiểm tra xem nhân viên đã tồn tại hay chưa
					NhanVien existingNV = dsnv.getNhanVienTheoMaNV(nv.getMaNV());
					if (existingNV != null) {
						JOptionPane.showMessageDialog(this, "Nhân viên đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							dsnv.create(nv);	
							model.setRowCount(0);
							datatoTable();
							updateComboBoxTimTheoMaNV();
						} catch (Exception e1) {
							// Log lỗi chi tiết
						    e1.printStackTrace(); // In ra thông báo lỗi để kiểm tra trong console
						    JOptionPane.showMessageDialog(this, "Lỗi khi thêm nhân viên vào cơ sở dữ liệu: " + e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				deleteField();
			}
		}
		if(o.equals(btnTim)) {
			if(textField_HoTen.getText() != null) {
				filterRows();

			}

			if(textField_Email.getText() != null) {
				filterRows();

			}
			if(textField_CCCD.getText() != null) {
				filterRows();

			}

			if(cb_nam.isSelected() ) {
				filterRows();

			}else if(cb_nu.isSelected()) {
				filterRows();
		
			}

			if(cb_dangLam.isSelected() ) {
				filterRows();

			}else if(cb_nghiLam.isSelected()) {
				System.out.println(cb_nghiLam);
				filterRows();

			}
			// Cập nhật lại comboBox_TimTheoMaNV
			updateComboBoxTimTheoMaNV();
			deleteField();
		}
		if(o.equals(btnSua)) {
			if(validData()) {
				update();
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {     
	    int row = table_NV.getSelectedRow();
	    if (row != -1) { 
	        // Lấy giá trị từ từng cột của bảng
	        String maNV = table_NV.getValueAt(row, 1).toString();
	        String hoTen = table_NV.getValueAt(row, 2).toString();
	        boolean gioiTinh = table_NV.getValueAt(row, 4).toString().equals("Nữ") ? true: false;
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate ngaySinh = LocalDate.parse(table_NV.getValueAt(row, 3).toString(), formatter);
	        String cccd = table_NV.getValueAt(row, 6).toString();
	        String sdt = table_NV.getValueAt(row, 8).toString();
	        String email = table_NV.getValueAt(row, 7).toString();
	        String maCa = table_NV.getValueAt(row, 5).toString();
	        boolean trangThai = table_NV.getValueAt(row, 9).toString().equals("Đang làm");
	        int chucVu = table_NV.getValueAt(row, 10).toString().equals("Quản lý") ? 1 : 0;

	        // Gán giá trị vào các trường trên giao diện
	        textField_MaNV.setText(maNV);
	        textField_HoTen.setText(hoTen);
	        cb_nam.setSelected(!gioiTinh);
	        cb_nu.setSelected(gioiTinh);
	        dateChooser_NgaySinh.setDate(Date.from(ngaySinh.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	        textField_CCCD.setText(cccd);
	        textField_SDT.setText(sdt);
	        textField_Email.setText(email);
	        comboBox_Ca.setSelectedItem(maCa);
	        cb_dangLam.setSelected(trangThai);
	        cb_nghiLam.setSelected(!trangThai);
	        comboBox_ChucVu.setSelectedIndex(chucVu == 1 ? 0 : 1);
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

	//Hàm kiểm tra regex
	public boolean validData() {
		// Kiểm tra họ tên
	    if (textField_HoTen.getText().equals("")) {
	        JOptionPane.showMessageDialog(this, "Họ tên không được để trống");
	        return false;
	    }
	    try {
	        tempNhanVien.setTenNV(textField_HoTen.getText().trim());
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Kiểm tra giới tính
	    if (!cb_nam.isSelected() && !cb_nu.isSelected()) {
	        JOptionPane.showMessageDialog(this, "Chọn giới tính");
	        return false;
	    }
	    
	    // Kiểm tra ngày sinh từ JDateChooser
	    if (dateChooser_NgaySinh.getDate() == null) {
	        JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
	        return false;
	    }

	    try {
	        // Lấy ngày sinh từ JDateChooser và chuyển đổi thành LocalDate
	        LocalDate ngaySinh = new java.sql.Date(dateChooser_NgaySinh.getDate().getTime()).toLocalDate();
	        tempNhanVien.setGioiTinh(cb_nam.isSelected()); // Lấy giới tính từ checkbox
	        tempNhanVien.setNgaySinh(ngaySinh); // Kiểm tra ngày sinh
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Kiểm tra CCCD
	    if (textField_CCCD.getText().equals("")) {
	        JOptionPane.showMessageDialog(this, "CCCD không được bỏ trống");
	        return false;
	    }
	    try {
	        tempNhanVien.setCccd(textField_CCCD.getText().trim());
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	 // Kiểm tra số điện thoại
	    if (textField_SDT.getText().equals("")) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại không được bỏ trống");
	        return false;
	    }
	    try {
	        tempNhanVien.setSdt(textField_SDT.getText().trim());
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Kiểm tra email
	    if (textField_Email.getText().equals("")) {
	        JOptionPane.showMessageDialog(this, "Email không được bỏ trống");
	        return false;
	    }
	    try {
	        tempNhanVien.setEmail(textField_Email.getText().trim());
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	 // Kiểm tra chức vụ
	    if (comboBox_ChucVu.getSelectedItem() == null || comboBox_ChucVu.getSelectedIndex() == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ");
	        return false;
	    }
	    
	 // Kiểm tra ca làm việc
	    if (comboBox_Ca.getSelectedItem() == null || comboBox_Ca.getSelectedIndex() == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn ca làm việc");
	        return false;
	    }
	    
	    // Kiểm tra trạng thái
	    if (!cb_dangLam.isSelected() && !cb_nghiLam.isSelected()) {
	        JOptionPane.showMessageDialog(this, "Chọn trạng thái");
	        return false;
	    }
	    
	    return true;
	}

	//Hàm lấy dữ liệu từ JPane thông tin nhân viên
	public NhanVien revertNV() {
	    String maNV = generateMaNV();
	    String hoTen = textField_HoTen.getText();
	    boolean gioiTinh = cb_nam.isSelected() ? false : (cb_nu.isSelected() ? true : true); // Sử dụng trực tiếp giá trị của checkbox
	    String ca = comboBox_Ca.getSelectedItem().toString();
	    String cccd = textField_CCCD.getText();

	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate ngaySinh = null;

	    try {
	        // Lấy ngày sinh từ JDateChooser
	        Date date = dateChooser_NgaySinh.getDate(); // Sử dụng getDate() để lấy giá trị ngày
	        if (date != null) {
	            // Chuyển đổi từ Date sang LocalDate
	            ngaySinh = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        } else {
	            throw new IllegalArgumentException("Ngày sinh không được để trống.");
	        }

	        // Chuyển đổi LocalDate sang chuỗi định dạng SQL (yyyy-MM-dd)
	        String ngaySinhSQL = ngaySinh.format(outputFormatter);
	        // In ra kết quả
	        System.out.println("Ngày sinh SQL dạng chuỗi: " + ngaySinhSQL);

	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi xảy ra khi chuyển đổi ngày sinh. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }

	    String email = textField_Email.getText();
	    String sdt = textField_SDT.getText();
	    boolean trangThai = cb_dangLam.isSelected() ? true : (cb_nghiLam.isSelected() ? false : false);
	    int chucVu = comboBox_ChucVu.getSelectedIndex() == 0 ? 1 : 2;

	    try {
	        // Tạo đối tượng NhanVien và kiểm tra các điều kiện trong entity
	        NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, gioiTinh, new Ca(ca), cccd, email, sdt, trangThai, chucVu);
	        return nv;
	    } catch (IllegalArgumentException e) {
	        // Bắt lỗi từ entity (ví dụ lỗi độ tuổi không hợp lệ)
	        JOptionPane.showMessageDialog(this,e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
	}


	//Hàm sửa thông tin nhân viên
	public void update() {
		int index = table_NV.getSelectedRow();
		if (index != -1) {
			String maNV= textField_MaNV.getText();
			String hoTen = textField_HoTen.getText();
			boolean gioiTinh = cb_nam.isSelected() ? false : (cb_nu.isSelected() ? true : true); // Sử dụng trực tiếp giá trị của checkbox
			String ca = comboBox_Ca.getSelectedItem().toString();
			String cccd = textField_CCCD.getText();

			// Lấy ngày sinh từ dateChooser
			Date date = dateChooser_NgaySinh.getDate();
			LocalDate ngaySinh = null;
			if (date != null) {
				ngaySinh = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			} else {
				JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ.");
				return; // Thoát khỏi phương thức nếu ngày sinh không hợp lệ
			}        
			String email = textField_Email.getText();
			String sdt = textField_SDT.getText();
			boolean trangThai = cb_dangLam.isSelected() ? true : (cb_nghiLam.isSelected() ? false : false);
			int chucVu =  comboBox_ChucVu.getSelectedIndex() == 0 ? 1 : 2;
			NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, gioiTinh, new Ca(ca), cccd, email, sdt, trangThai, chucVu);
			try {
				dsnv.update(nv);
				model.setRowCount(0);
				// Load lại dữ liệu từ cơ sở dữ liệu vào bảng
				datatoTable();
				updateComboBoxTimTheoMaNV();
				deleteField();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Không tìm thấy.");
			}
		}else {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần sửa.");
		}
	}
	//Hàm tải dữ liệu vào bảng
	public void datatoTable() {
		dsnv.reset();
		ArrayList<NhanVien> list = dsnv.docTuBang();
		model = (DefaultTableModel) table_NV.getModel();
		model.setRowCount(0); // Xóa tất cả hàng trong bảng
		int stt = 1; // Biến đếm bắt đầu từ 1 cho STT
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (NhanVien nv : list) {
			comboBox_TimTheoMaNV.addItem(nv.getMaNV());
			model.addRow(new Object[] { stt++, nv.getMaNV(), nv.getTenNV(), nv.getNgaySinh().format(formatter),nv.isGioiTinh()==true ?"Nữ" : "Nam",
					nv.getCa().getMaCa(), nv.getCccd(), nv.getEmail(), nv.getSdt(),  nv.isTrangThai() ? "Đang làm" : "Nghỉ làm", nv.getChucVu() == 1 ? "Quản lý" : "Nhân viên"

			});
		}
		//Mặc định các button và combobox trong thông tin nhân viên
		deleteField();

	}	

	//Hàm tạo mã nhân viên tự động
	public String generateMaNV() {
		dsnv.reset();
		ArrayList<NhanVien> list = dsnv.docTuBang();
		String maNV = String.format("NV%03d", list.size()+1);// Tạo mã với định dạng "NV" + 3 chữ số, ví dụ "NV001"
		return maNV;
	}

	//Hàm xóa thông tin 
	public void deleteField() {
		textField_MaNV.setText("");
		textField_HoTen.setText("");
		dateChooser_NgaySinh.setDate(null);  // Đặt lại giá trị ngày thành null
		textField_CCCD.setText("");
		textField_Email.setText("");
		textField_SDT.setText("");
		comboBox_ChucVu.setSelectedItem(null);
		comboBox_Ca.setSelectedItem(null);
		group.clearSelection();
		group1.clearSelection();
	}

	//	// Lớp FilterListener để lắng nghe các thay đổi trong các ô tìm kiếm
	private void filterRows() {
		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
		String hoTen = textField_HoTen.getText().trim();
		String email = textField_Email.getText().trim();
		String cccd = textField_CCCD.getText().trim();
		String ca = comboBox_Ca.getSelectedItem() != null ? comboBox_Ca.getSelectedItem().toString().trim() : "";

		// Để `gioiTinh` không có giá trị null
		boolean gioiTinh = cb_nam.isSelected(); // true nếu chọn nam, false nếu chọn nữ
		// Trạng thái cũng không có giá trị null
		Boolean trangThai = null; // khởi tạo biến trangThai là null
		if (cb_dangLam.isSelected()) {
			trangThai = true; // Đang làm
		} else if (cb_nghiLam.isSelected()) {
			trangThai = false; // Nghỉ làm
		}

		// Lọc theo các điều kiện
		if (!hoTen.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + hoTen, 2));
		}
		if (!email.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + email, 7));
		}
		if (!ca.isEmpty()) {
			filters.add(RowFilter.regexFilter(ca, 5));
		}
		if (!cccd.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + cccd, 6));
		}

		// Giới tính
		if (cb_nam.isSelected() || cb_nu.isSelected()) {
			String gioiTinhText = gioiTinh ? "Nam" : "Nữ";
			filters.add(RowFilter.regexFilter(gioiTinhText, 4));
		}

		// Trạng thái
		if (trangThai != null) {
			String trangThaiText = trangThai ? "Đang làm" : "Nghỉ làm";
			filters.add(RowFilter.regexFilter(trangThaiText, 9));
		}
		// Ngày Sinh
		Date date = dateChooser_NgaySinh.getDate(); // Lấy giá trị ngày từ JDateChooser
		if (date != null) { // Kiểm tra date không phải null
			// Định dạng lại ngày sinh thành chuỗi dd/MM/yyyy
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = dateFormat.format(date); // Chuyển đổi đối tượng Date thành chuỗi
			System.out.println(date);

			if (!formattedDate.isEmpty()) {
				Date ngaySinh = parseDate(formattedDate);
				filters.add(new RowFilter<Object, Object>() {
					@Override
					public boolean include(Entry<? extends Object, ? extends Object> entry) {
						String entryDateStr = entry.getStringValue(3); // Giả sử cột 3 là cột ngày
						Date entryDate = parseDate(entryDateStr); // Chỉ lấy phần ngày
						return entryDate != null && !entryDate.before(ngaySinh);	                
					}
				});
			}
		} 
		// Cập nhật bộ lọc
		if (filters.isEmpty()) {
			sorter.setRowFilter(null); // Nếu không có bộ lọc nào, xóa bộ lọc
		} else {
			sorter.setRowFilter(RowFilter.andFilter(filters));
		}
	}
	private void updateComboBoxTimTheoMaNV() {
		// Xóa tất cả các mục hiện tại trong comboBox_TimTheoMaNV
		comboBox_TimTheoMaNV.removeAllItems();
		// Lặp qua các hàng còn lại trong bảng và thêm vào comboBox
		for (int i = 0; i < table_NV.getRowCount(); i++) {
			String maNV = table_NV.getValueAt(i, 1).toString(); // Giả định cột mã NV là cột 1
			comboBox_TimTheoMaNV.addItem(maNV);
		}
		deleteField();
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
}