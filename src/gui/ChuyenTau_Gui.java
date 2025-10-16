package gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import components.RoundedTextField;

import dao.ChuyenTau_DAO;
import dao.Ga_DAO;
import dao.Toa_DAO;
import dao.Ve_DAO;
import entity.ChuyenTau;
import entity.Ga;
import entity.Toa;
import entity.Ve;

import javax.swing.JComboBox;

public class ChuyenTau_Gui extends JPanel implements MouseListener,DocumentListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table_Ga;
	private JTable table_GiaVe;
	private JLabel muiTenIconLabel;
	private JLabel dauTauIconLabel;
	private JDateChooser dateChooser_Ngay;
	private RoundedTextField textField_NgayChon;
	private Ga_DAO dsGa= new Ga_DAO();
	private ChuyenTau_DAO dsCT= new ChuyenTau_DAO();
	private Toa_DAO dsToa = new Toa_DAO();
	private Ve_DAO dsVe = new Ve_DAO();
	ArrayList<Ga> danhSachGa =dsGa.docTuBang();
	private RoundedTextField txtGaDen;
	private RoundedTextField txtGaDi;
	private Date selectedDate;
	private String tenGaDi;
	private String tenGaDen;
	private String tauChon;
	private JLabel lbl_MaTau;
	private JLabel lbl_GaDen;
	private JLabel lbl_NgayDi;
	private JLabel lbl_NgayDen;
	private JLabel lbl_ThoiGianHanhTrinh;
	private JLabel lbl_GaDi;
	private JLabel lbl_GioDi;
	private JLabel lbl_GioDen;
	private DefaultTableModel model;
	private JLabel lblNewLabel_3_2_1;
	private JComboBox<String> comboBox_Tau;
	private int dem = 0;
	public ChuyenTau_Gui(TrangChu_GUI trangChu) {
		setBackground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 102, 153));
		panel.setBounds(56, 10, 1347, 58);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/img/clock.png")));
		lblNewLabel.setBounds(28, 0, 53, 58);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("GIỜ TÀU - GIÁ VÉ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(80, 0, 221, 58);
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(56, 322, 693, 238);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Các ga trong hành trình");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(0, 0, 693, 38);
		panel_1.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 37, 693, 201);
		panel_1.add(scrollPane);

		table_Ga = new JTable();
		table_Ga.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"STT", "Ga", "Cự ly(km)", "Ngày đi","Giờ đi","Ngày đến","Giờ đến"
				}
				));
		table_Ga.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_Ga.setRowHeight(30);
		scrollPane.setViewportView(table_Ga);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(124, 78, 451, 38);
		add(panel_2);
		panel_2.setLayout(null);

		//		txtGaDi = new JTextField();
		txtGaDi = new RoundedTextField(15);
		txtGaDi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGaDi.setBounds(85, 0, 366, 38);
		panel_2.add(txtGaDi);
		txtGaDi.setColumns(10);
		// Thêm MouseListener để lưu giá trị khi chuột rời khỏi trường txtGaDi
		txtGaDi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Cập nhật giá trị mỗi khi trường có focus
				tenGaDi = txtGaDi.getText();
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Cập nhật giá trị nếu cần thiết khi trường mất focus
				tenGaDi = txtGaDi.getText(); // Lưu giá trị vào biến khi mất focus
			}
		});
		chonGa(txtGaDi);

		lblNewLabel_3_2_1 = new JLabel("Ga đi");
		lblNewLabel_3_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3_2_1.setBounds(0, 0, 85, 38);
		panel_2.add(lblNewLabel_3_2_1);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(876, 78, 451, 38);
		add(panel_2_1);

		JLabel lblNewLabel_3_1 = new JLabel("Ga đến");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(0, 0, 85, 38);
		panel_2_1.add(lblNewLabel_3_1);

		//		txtGaDi_top = new JTextField();
		txtGaDen = new RoundedTextField(15);
		txtGaDen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGaDen.setBounds(85, 0, 366, 38);
		panel_2_1.add(txtGaDen);
		txtGaDen.setColumns(10);
		// Thêm MouseListener để lưu giá trị khi chuột rời khỏi trường txtGaDi
		// Thêm FocusListener để lưu giá trị khi trường có focus
		txtGaDen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Lưu giá trị vào biến khi trường có focus
				tenGaDen = txtGaDen.getText();
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Cập nhật giá trị nếu cần thiết khi trường mất focus
				tenGaDen = txtGaDen.getText(); // Lưu giá trị vào biến khi mất focus
				// Gọi chonChuyenTau ở đây hoặc nơi thích hợp
			}
		});
		chonGa(txtGaDen);

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBounds(124, 120, 451, 38);
		add(panel_2_2);

		JLabel lblNewLabel_3_2 = new JLabel("Ngày");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(0, 0, 85, 38);
		panel_2_2.add(lblNewLabel_3_2);

		dateChooser_Ngay = new JDateChooser();
		dateChooser_Ngay.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 10));
		dateChooser_Ngay.getCalendarButton().setBounds(315, 0, 50, 38);
		dateChooser_Ngay.setBounds(86, 0, 365, 38);
		panel_2_2.add(dateChooser_Ngay);
		dateChooser_Ngay.setLayout(null);
		
		textField_NgayChon = new RoundedTextField(15);
		textField_NgayChon.setBounds(0, 0, 324, 38);
		textField_NgayChon.setEditable(false);
		dateChooser_Ngay.add(textField_NgayChon);
		
		// Thêm sự kiện PropertyChangeListener cho dateChooserTu
		dateChooser_Ngay.getDateEditor().addPropertyChangeListener("date", evt -> {
		    selectedDate = dateChooser_Ngay.getDate();
		    if (selectedDate != null) {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        
		        // Lấy ngày hiện tại mà không có thời gian
		        Calendar currentCalendar = Calendar.getInstance();
		        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
		        currentCalendar.set(Calendar.MINUTE, 0);
		        currentCalendar.set(Calendar.SECOND, 0);
		        currentCalendar.set(Calendar.MILLISECOND, 0);
		        Date currentDateWithoutTime = currentCalendar.getTime();
		        
		        // Kiểm tra ngày đã chọn có trước ngày hiện tại không (không tính đến thời gian)
		        if (selectedDate.before(currentDateWithoutTime)) {
		        	dateChooser_Ngay.setDate(null);
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đi từ ngày hiện tại.",
		                    "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if(txtGaDi.getText().equals("") && txtGaDen.getText().equals("")) {
		        	 dateChooser_Ngay.setDate(null);
		        	 txtGaDi.requestFocusInWindow(); // Chuyển focus đến txtGaDi
		        	JOptionPane.showMessageDialog(null, "Vui lòng chọn ga đi và ga đến",
		                    "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }else {
			        if(txtGaDi.getText().equals("")) {
			        	dateChooser_Ngay.setDate(null);
			        	txtGaDi.requestFocusInWindow(); // Chuyển focus đến txtGaDi
			        	JOptionPane.showMessageDialog(null, "Vui lòng chọn ga đi",
			                    "Thông báo", JOptionPane.WARNING_MESSAGE);
			            return;
			        }if(txtGaDen.getText().equals("")) {
			        	dateChooser_Ngay.setDate(null);
			        	txtGaDen.requestFocusInWindow(); // Chuyển focus đến txtGaDi
			        	JOptionPane.showMessageDialog(null, "Vui lòng chọn ga đến",
			                    "Thông báo", JOptionPane.WARNING_MESSAGE);
			            return;
			        }
		        }
		        textField_NgayChon.setText(dateFormat.format(selectedDate)); // Gán ngày vào JTextField
		        // Gọi chonChuyenTau mỗi khi ngày được cập nhật
		        chonChuyenTau(comboBox_Tau, tenGaDi, tenGaDen, textField_NgayChon.getText());
		    } else {
		        textField_NgayChon.setText(""); // Nếu không có ngày nào được chọn, làm rỗng JTextField
		    }
		});


		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBounds(876, 120, 451, 38);
		add(panel_2_1_1);

		JLabel lblTau = new JLabel("Tàu");
		lblTau.setHorizontalAlignment(SwingConstants.CENTER);

		lblTau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTau.setBounds(0, 0, 85, 38);
		panel_2_1_1.add(lblTau);

		comboBox_Tau = new JComboBox<>();
		comboBox_Tau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Tau.setBounds(84, 0, 367, 38);
		panel_2_1_1.add(comboBox_Tau);
		chonChuyenTau(comboBox_Tau, tenGaDi, tenGaDen, tauChon);
		JPanel panel_ThongTinTau = 	new JPanel();
		panel_ThongTinTau.setBackground(new Color(159, 214, 239));
		panel_ThongTinTau.setBounds(56, 168, 1347, 144);
		add(panel_ThongTinTau);
		panel_ThongTinTau.setLayout(null);

		JLabel lblGaDen_CenTer = new JLabel("Ga đến:");
		lblGaDen_CenTer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGaDen_CenTer.setForeground(new Color(1, 111, 162));

		lblGaDen_CenTer.setBounds(914, 44, 78, 21);
		panel_ThongTinTau.add(lblGaDen_CenTer);

		JLabel lblNgayDen = new JLabel("Ngày đến:");
		lblNgayDen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgayDen.setForeground(new Color(1, 111, 162));
		lblNgayDen.setBounds(913, 75, 94, 22);
		panel_ThongTinTau.add(lblNgayDen);

		JLabel lblGaDi = new JLabel("Ga đi:");
		lblGaDi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGaDi.setForeground(new Color(1, 111, 162));
		lblGaDi.setBounds(153, 44, 72, 23);
		panel_ThongTinTau.add(lblGaDi);

		JLabel lblNgayDi = new JLabel("Ngày đi:");
		lblNgayDi.setForeground(new Color(1, 111, 162));
		lblNgayDi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgayDi.setBounds(153, 76, 78, 21);
		panel_ThongTinTau.add(lblNgayDi);

		// Load hình ảnh gốc
		ImageIcon muiTenIcon = new ImageIcon(getClass().getResource("/img/arrow_back_icon -.png"));
		Image scaledMuiTenIcon = muiTenIcon.getImage().getScaledInstance(100, 43, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		muiTenIconLabel = new JLabel(new ImageIcon(scaledMuiTenIcon));
		muiTenIconLabel.setBounds(613, 50, 90, 43);
		panel_ThongTinTau.add(muiTenIconLabel);


		ImageIcon dauTauIcon = new ImageIcon(getClass().getResource("/img/icontau.png"));
		Image scaledDauTauIcon =dauTauIcon.getImage().getScaledInstance(100, 43, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		dauTauIconLabel = new JLabel(new ImageIcon(scaledDauTauIcon));
		dauTauIconLabel.setBounds(538, 10, 100, 43);
		panel_ThongTinTau.add(dauTauIconLabel);

		lbl_MaTau = new JLabel("");
		lbl_MaTau.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MaTau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_MaTau.setBounds(667, 10, 86, 43);
		panel_ThongTinTau.add(lbl_MaTau);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(523, 101, 300, 33);
		panel_3.setBackground(new Color(51, 102, 153));
		panel_ThongTinTau.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Thời gian hành trình:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(5, 8, 150, 17);
		panel_3.add(lblNewLabel_4);

		lbl_ThoiGianHanhTrinh = new JLabel("");
		lbl_ThoiGianHanhTrinh.setForeground(new Color(255, 255, 255));
		lbl_ThoiGianHanhTrinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ThoiGianHanhTrinh.setBounds(165, 6, 135, 21);
		panel_3.add(lbl_ThoiGianHanhTrinh);

		lbl_GaDi = new JLabel("");
		lbl_GaDi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_GaDi.setBounds(241, 44, 78, 21);
		panel_ThongTinTau.add(lbl_GaDi);

		lbl_GaDen = new JLabel("");
		lbl_GaDen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_GaDen.setBounds(1015, 43, 78, 23);
		panel_ThongTinTau.add(lbl_GaDen);

		lbl_NgayDi = new JLabel("");
		lbl_NgayDi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_NgayDi.setBounds(241, 77, 114, 21);
		panel_ThongTinTau.add(lbl_NgayDi);

		lbl_NgayDen = new JLabel("");
		lbl_NgayDen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_NgayDen.setBounds(1017, 77, 114, 21);
		panel_ThongTinTau.add(lbl_NgayDen);

		lbl_GioDi = new JLabel("");
		lbl_GioDi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_GioDi.setBounds(365, 77, 78, 21);
		panel_ThongTinTau.add(lbl_GioDi);

		lbl_GioDen = new JLabel("");
		lbl_GioDen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_GioDen.setBounds(1143, 76, 78, 21);
		panel_ThongTinTau.add(lbl_GioDen);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(770, 322, 633, 238);
		add(panel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Bảng giá vé");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2_1.setBounds(0, 0, 633, 36);
		panel_1_1.add(lblNewLabel_2_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 34, 633, 204);
		panel_1_1.add(scrollPane_1);

		table_GiaVe = new JTable();
		table_GiaVe.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"STT", "Mã toa", "Hạng","Khuyến mãi", "Giá vé"
				}
				));
		table_GiaVe.setRowHeight(30);
		table_GiaVe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(table_GiaVe);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(681, 78, 102, 78);
		add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(getClass().getResource("/img/right.png")));
		lblNewLabel_8.setBounds(25, 0, 30, 32);
		panel_4.add(lblNewLabel_8);

		JLabel lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setIcon(new ImageIcon(getClass().getResource("/img/right.png")));
		lblNewLabel_8_1.setBounds(25, 42, 30, 32);
		panel_4.add(lblNewLabel_8_1);
	}
	public void dataToTableGa(String maTau) {
		dsCT.reset();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//		ChuyenTau ct = dsCT.getChuyenTauTheoMaTau(maTau);
		ArrayList<ChuyenTau> listCT = dsCT.getChuyenTau_Ga(maTau);
//		ArrayList<Ga> danhSachGa = ct.getTramDung();
		model = (DefaultTableModel) table_Ga.getModel();
		model.setRowCount(0); // Xóa tất cả hàng trong bảng
		int count = 1;
		for (ChuyenTau tau: listCT) {
			Ga ga = dsGa.getGaTheoMaGa(tau.getGaDi().getMaGa());
			model.addRow(new Object[] { count++, ga.getTenGa(), ga.getChiSoKm(),
					tau.getNgayDi().format(formatter),tau.getGioDi().format(timeFormatter),
					tau.getNgayDen().format(formatter),tau.getGioDen().format(timeFormatter)});
		}
	}
	public void dataToTableVe(String maTau) {
		dsCT.reset();
		dsVe.reset();
		dsToa.reset();

		// Lấy thông tin chuyến tàu theo mã tàu
		ChuyenTau ct = dsCT.getChuyenTauTheoMaTau(maTau);
		// Lấy danh sách toa theo mã tàu
		ArrayList<Toa> toaList = dsToa.getDsToaTheoMaTau(maTau);
		// Lấy mô hình của bảng
		model = (DefaultTableModel) table_GiaVe.getModel();
		model.setRowCount(0); // Xóa tất cả hàng trong bảng

		// Danh sách khuyến mãi
		String[] khuyenMais = {"Người lớn", "Sinh viên", "Người lớn tuổi", "Trẻ em dưới 6 tuổi", "Trẻ em từ 6 đến 10 tuổi"};

		// Duyệt qua từng toa để tạo vé ảo
		for (Toa toa : toaList) {
			// Duyệt qua các loại khuyến mãi
			for (String khuyenMai : khuyenMais) {
				// Tạo mã vé
				String maVe = generateMaVe();
				// Giả sử loại ghế được xác định theo loại toa
				String hang = toa.getLoaiToa();
				// Tạo vé ảo
				Ve ve = new Ve(maVe, ct, toa, null, null, 
						LocalDate.now(), LocalTime.now(), 
						LocalDate.now(), LocalTime.now(), 
						ct.getGaDi(), ct.getGaDen(), 
						hang, khuyenMai, true, null);
				// Tính giá vé
				float giaVe = ve.tinhGiaVe();

				// Thêm dữ liệu vào bảng
				model.addRow(new Object[]{
						model.getRowCount() + 1, // Số thứ tự
						toa.getMaToa(),          // Mã toa
						hang,                    // Hạng
						khuyenMai,               // Khuyến mãi
						dinhDangTienTe(giaVe)                   // Giá vé
				});
			}
		}
	}

	// Phương thức giả lập để sinh mã vé
	private String generateMaVe() {
		// Sinh mã vé theo định dạng bạn muốn
		dem++;
		return "VE" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%04d", dem);
	}
	
	private void chonGa(JTextField txt_Ga) {
		// Tạo JPopupMenu để hiển thị gợi ý
		JPopupMenu suggestionMenu = new JPopupMenu();

		// Hàm cập nhật gợi ý khi người dùng nhập vào text field
		txt_Ga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String input = txt_Ga.getText();
				suggestionMenu.removeAll(); // Xóa các gợi ý cũ

				if (!input.isEmpty()) {
					int count = 0; // Biến đếm số gợi ý đã thêm
					// Lọc danh sách ga theo từ khóa người dùng nhập
					for (Ga ga : danhSachGa) {
						if (ga.getTenGaRaw().toLowerCase().startsWith(input.toLowerCase())) {
							JMenuItem item = new JMenuItem(ga.getTenGaRaw());
							item.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									txt_Ga.setText(item.getText());
									suggestionMenu.setVisible(false); // Ẩn gợi ý sau khi chọn
								}
							});
							suggestionMenu.add(item);
							count++; // Tăng biến đếm
						}
						if (count >= 5) { // Kiểm tra nếu đã có 5 gợi ý
							break; // Thoát vòng lặp nếu đã đủ 5 gợi ý
						}
					}
				}

				// Hiển thị danh sách gợi ý nếu có ít nhất một gợi ý
				if (suggestionMenu.getComponentCount() > 0) {
					suggestionMenu.show(txt_Ga, 0, txt_Ga.getHeight());
					txt_Ga.requestFocus(); // Đặt lại focus cho JTextField
				} else {
					suggestionMenu.setVisible(false); // Ẩn nếu không có gợi ý
				}
			}
		});
	}
	private void chonChuyenTau(JComboBox<String> comboBox_Tau, String tenGaDi, String tenGaDen, String ngayChon) {
		comboBox_Tau.removeAllItems();
		if(tenGaDi == null || tenGaDen == null || ngayChon == null) {
			comboBox_Tau.setSelectedItem(null);
			dateChooser_Ngay.setDate(null);
			return; 
		}else {
			// Tìm các ga theo tên ga đi và ga đến
            Ga gaDi = dsGa.getGaTheoTenGa(tenGaDi);
            Ga gaDen = dsGa.getGaTheoTenGa(tenGaDen);
			
            ArrayList<ChuyenTau> danhSachChuyenTau = dsCT.getChuyenTauTheoGaVaNgayDi(gaDi.getMaGa(), gaDen.getMaGa(), ngayChon);
            // Sử dụng Set để kiểm tra trùng lặp
            Set<String> uniqueChuyenTau = new HashSet<>();

            // Duyệt qua các chuyến tàu và thêm vào ComboBox nếu mã tàu khớp với input
            if (danhSachChuyenTau != null && !danhSachChuyenTau.isEmpty()) {
                // Thêm các item mới vào ComboBox nếu mã tàu khớp với input
                for (ChuyenTau chuyenTau : danhSachChuyenTau) {
                    String maTau = chuyenTau.getMaTau();
                    // Kiểm tra xem mã tàu đã tồn tại trong Set chưa và có bắt đầu bằng input
                    if (uniqueChuyenTau.add(maTau)) {
                        // Thêm mã tàu vào ComboBox nếu chưa có
                        if (!contains(comboBox_Tau, maTau)) {
                            comboBox_Tau.addItem(maTau); // Thêm mã tàu vào ComboBox
                        }
                    }
                }
             // Thêm ActionListener để tự động cập nhật label khi chọn item
                comboBox_Tau.addActionListener(e -> {
                    String selectedMaTau = (String) comboBox_Tau.getSelectedItem();
                    if (selectedMaTau != null) {
                        updateLaBel(selectedMaTau);  // Gọi updateLabel với mã tàu được chọn
                        dataToTableGa(selectedMaTau);
                        dataToTableVe(selectedMaTau);
                    }
                });   
            }else {
            	dateChooser_Ngay.setDate(null);
            	JOptionPane.showMessageDialog(null, "Không có tàu phù hợp.Vui lòng chọn ngày khác",
	                    "Thông báo", JOptionPane.WARNING_MESSAGE);
	            return;
            }
		}
	}

	// Kiểm tra xem ComboBox có chứa item cần thêm không
	private boolean contains(JComboBox<String> comboBox_Tau, String item) {
	    for (int i = 0; i < comboBox_Tau.getItemCount(); i++) {
	        if (comboBox_Tau.getItemAt(i).equalsIgnoreCase(item)) {
	            return true;
	        }
	    }
	    return false;
	}

	private void updateLaBel(String maTau) {
		dsCT.reset();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		ChuyenTau ct = dsCT.getChuyenTauTheoMaTau(maTau);
		lbl_MaTau.setText(ct.getMaTau());
		lbl_GaDi.setText(ct.getGaDi().getTenGaRaw());
		lbl_GaDen.setText(ct.getGaDen().getTenGaRaw());
		lbl_NgayDi.setText(ct.getNgayDi().format(formatter));  // Đảm bảo ct.getNgayDi() trả về LocalDate
		lbl_NgayDen.setText(ct.getNgayDen().format(formatter)); // Đảm bảo ct.getNgayDen() trả về LocalDate
		lbl_GioDi.setText(ct.getGioDi().format(timeFormatter));
		lbl_GioDen.setText(ct.getGioDen().format(timeFormatter));

		// Tính toán thời gian hành trình
		LocalDateTime ngayDiGioDi = LocalDateTime.of(ct.getNgayDi(), ct.getGioDi());
		LocalDateTime ngayDenGioDen = LocalDateTime.of(ct.getNgayDen(), ct.getGioDen());

		Duration duration = Duration.between(ngayDiGioDi, ngayDenGioDen);

		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;

		lbl_ThoiGianHanhTrinh.setText(String.format("%d giờ %d phút", hours, minutes));
	}

	public String dinhDangTienTe(double soTien) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return formatter.format(soTien);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

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
}
