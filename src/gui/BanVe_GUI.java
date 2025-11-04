package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import components.ChuyenTau_JPanel;
import components.ScrollBarUI;
import components.RoundedButton;
import components.Toa_JPanel;
import components.Ve_JPanel;
import dao.ChuyenTau_DAO;
import dao.Ga_DAO;
import dao.Ghe_DAO;
import dao.Toa_DAO;
import dao.Ve_DAO;
import entity.ChuyenTau;
import entity.Ga;
import entity.Ghe;
import entity.Toa;
import entity.Ve;

import javax.swing.JRadioButton;
import javax.swing.JScrollBar;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;

public class BanVe_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jp_title;
	private JLabel downIconLabel_2;
	private JLabel lbl_Chieu_1;
	private JLabel lbl_NgayDi_1;
	private JLabel lbl_Ga_1;
	private JLabel lblMaToa;
	private JTextField txt_GaDi;
	private JTextField txt_GaDen;
	private JDateChooser chooserNgayDi;
	private JDateChooser chooserNgayVe;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtn_MotChieu;
	private JRadioButton rdbtn_KhuHoi;
	private JPanel jp_ThongTinChuyenTau;
	private JPanel jp_TinhTrangToa;
	private JPanel jp_TinhTrangGhe;
	public ArrayList<Ve> dsVeDatTam = new ArrayList<Ve>();
	public JPanel jp_VeMua;
	private JButton btnTiep;
	private JButton btnQuayLai;
	protected ChuyenTau chuyenTauCu;
	public Toa toaCu;
	protected Rectangle boundsPanel;
	public Rectangle boundsPanelToa;
	private ChuyenTau_JPanel chuyenTauTruocDo = null;
	private int soVeChieuDi = 0;
	
	private JLabel userIconLabel;

	// Khai bao DAO
	Ga_DAO ga_dao = new Ga_DAO();
	ArrayList<Ga> dsGa = ga_dao.docTuBang();
	ChuyenTau_DAO chuyenTau_dao = new ChuyenTau_DAO();
	ArrayList<ChuyenTau> dsChuyenTau = chuyenTau_dao.docTuBang();
	Toa_DAO toa_DAO = new Toa_DAO();
	Ve_DAO ve_DAO = new Ve_DAO();
	Ghe_DAO ghe_DAO = new Ghe_DAO();
	private RoundedButton btnTim;
	private RoundedButton btnMua;
	private JScrollPane scrollPaneTinhTrangToa;
	private JLabel lblTongTien;

	/**
	 * Create the panel.
	 */
	public BanVe_GUI(TrangChu_GUI trangChu) {
		setBackground(SystemColor.window);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);

		jp_title = new JPanel();
		jp_title.setLayout(null);
		jp_title.setBackground(new Color(51, 102, 153));
		jp_title.setBounds(264, 10, 686, 36);
		add(jp_title);

		downIconLabel_2 = new JLabel((Icon) null);
		downIconLabel_2.setBounds(0, 0, 30, 35);
		jp_title.add(downIconLabel_2);

		lbl_Chieu_1 = new JLabel();
		lbl_Chieu_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_Chieu_1.setForeground(Color.WHITE);
		lbl_Chieu_1.setBounds(40, 0, 152, 35);
		jp_title.add(lbl_Chieu_1);

		lbl_NgayDi_1 = new JLabel();
		lbl_NgayDi_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_NgayDi_1.setForeground(Color.WHITE);
		lbl_NgayDi_1.setBounds(192, 0, 152, 35);
		jp_title.add(lbl_NgayDi_1);

		lbl_Ga_1 = new JLabel();
		lbl_Ga_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_Ga_1.setForeground(Color.WHITE);
		lbl_Ga_1.setBounds(342, 0, 334, 35);
		jp_title.add(lbl_Ga_1);

		jp_ThongTinChuyenTau = new JPanel();
		jp_ThongTinChuyenTau.setBackground(Color.WHITE);
		jp_ThongTinChuyenTau.setBounds(264, 56, 907, 118);
		add(jp_ThongTinChuyenTau);
		jp_ThongTinChuyenTau.setLayout(null);

//		jp_TinhTrangToa = new JPanel();
//		jp_TinhTrangToa.setBackground(Color.WHITE);
//		jp_TinhTrangToa.setBounds(264, 184, 907, 76);
//		add(jp_TinhTrangToa);
//		jp_TinhTrangToa.setLayout(null);
		
		// Bỏ `jp_TinhTrangToa` trực tiếp vào container
		jp_TinhTrangToa = new JPanel();
		jp_TinhTrangToa.setBackground(Color.WHITE);
		jp_TinhTrangToa.setLayout(null); // Layout null để tự định nghĩa vị trí

		// Tạo JScrollPane bọc quanh jp_TinhTrangToa
		scrollPaneTinhTrangToa = new JScrollPane(jp_TinhTrangToa);
		scrollPaneTinhTrangToa.setBounds(264, 184, 907, 83);
		scrollPaneTinhTrangToa.setBorder(null);
		scrollPaneTinhTrangToa.setBackground(Color.WHITE);

		// Áp dụng thanh cuộn tùy chỉnh
		JScrollBar horizontalScrollBar = scrollPaneTinhTrangToa.getHorizontalScrollBar();
		horizontalScrollBar.setUI(new ScrollBarUI());
		horizontalScrollBar.setPreferredSize(new Dimension(0, 8)); // Độ dày thanh cuộn ngang

		// Tùy chọn cuộn chỉ hiển thị khi cần
		scrollPaneTinhTrangToa.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTinhTrangToa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		add(scrollPaneTinhTrangToa);

		// Thêm JScrollPane vào container thay vì jp_TinhTrangToa trực tiếp
		add(scrollPaneTinhTrangToa);

		jp_TinhTrangGhe = new JPanel();
		jp_TinhTrangGhe.setBackground(Color.WHITE);
		jp_TinhTrangGhe.setBounds(264, 310, 907, 250);
		add(jp_TinhTrangGhe);
		jp_TinhTrangGhe.setLayout(null);

		JPanel jp_timKiem = new JPanel();
		jp_timKiem.setLayout(null);
		jp_timKiem.setBackground(Color.WHITE);
		jp_timKiem.setBounds(10, 10, 244, 351);
		add(jp_timKiem);

		JPanel jp_Content_ThongTin = new JPanel();
		jp_Content_ThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
		jp_Content_ThongTin.setLayout(null);
		jp_Content_ThongTin.setBackground(Color.WHITE);
		jp_Content_ThongTin.setBounds(0, 32, 244, 319);
		jp_timKiem.add(jp_Content_ThongTin);
		btnTim = new RoundedButton("Tìm", 15);
		btnTim.setText("Tìm kiếm");
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(51, 102, 153));

		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isValidatedTxtField()) {
					lbl_Chieu_1.setText("Chiều đi: ");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					lbl_NgayDi_1.setText(sdf.format(chooserNgayDi.getDate()));
					lbl_Ga_1.setText(txt_GaDi.getText() + " - " + txt_GaDen.getText());
					LocalDate ngayDi = chooserNgayDi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

					jp_TinhTrangToa.removeAll();
					jp_TinhTrangToa.revalidate();
					jp_TinhTrangToa.repaint();
					
					jp_TinhTrangToa.setPreferredSize(new Dimension(0, 76));
					scrollPaneTinhTrangToa.revalidate();
				    scrollPaneTinhTrangToa.repaint();

					jp_TinhTrangGhe.removeAll();
					jp_TinhTrangGhe.revalidate();
					jp_TinhTrangGhe.repaint();

					suKienBatDauChon(txt_GaDi.getText(), txt_GaDen.getText(), ngayDi);
				}
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBounds(73, 278, 85, 27);
		jp_Content_ThongTin.add(btnTim);

		txt_GaDi = new JTextField();
		txt_GaDi.setText("Nhập ga đi");
		txt_GaDi.setHorizontalAlignment(SwingConstants.LEFT);
		txt_GaDi.setForeground(SystemColor.textInactiveText);
		txt_GaDi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_GaDi.setColumns(10);
		txt_GaDi.setBounds(21, 26, 202, 27);
		jp_Content_ThongTin.add(txt_GaDi);
		chonGa(txt_GaDi);
		focusTxtField(txt_GaDi, "Nhập ga đi");

		txt_GaDen = new JTextField();
		txt_GaDen.setText("Nhập ga đến");
		txt_GaDen.setHorizontalAlignment(SwingConstants.LEFT);
		txt_GaDen.setForeground(SystemColor.textInactiveText);
		txt_GaDen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_GaDen.setColumns(10);
		txt_GaDen.setBounds(21, 78, 202, 27);
		jp_Content_ThongTin.add(txt_GaDen);
		chonGa(txt_GaDen);
		focusTxtField(txt_GaDen, "Nhập ga đến");

		rdbtn_MotChieu = new JRadioButton("Một Chiều");
		rdbtn_MotChieu.setForeground(new Color(0, 0, 0));
		buttonGroup.add(rdbtn_MotChieu);
		rdbtn_MotChieu.setBounds(21, 130, 85, 21);
		jp_Content_ThongTin.add(rdbtn_MotChieu);

		rdbtn_KhuHoi = new JRadioButton("Khứ Hồi");
		buttonGroup.add(rdbtn_KhuHoi);
		rdbtn_KhuHoi.setBounds(108, 130, 85, 21);
		jp_Content_ThongTin.add(rdbtn_KhuHoi);

		rdbtn_MotChieu.setSelected(true);

		// Thêm listener để thay đổi trạng thái của chooser ngày về
		rdbtn_KhuHoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooserNgayVe.setEnabled(true); // Kích hoạt chooser ngày về khi chọn Khứ Hồi
				btnTiep.setVisible(true);
				btnQuayLai.setVisible(true);

			}
		});

		rdbtn_MotChieu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooserNgayVe.setEnabled(false); // Vô hiệu hóa chooser ngày về khi chọn Một Chiều
				chooserNgayVe.setDate(null); // Xóa ngày đã chọn
				btnTiep.setVisible(false);
				btnQuayLai.setVisible(false);
			}
		});

		chooserNgayDi = new JDateChooser();
		chooserNgayDi.setBounds(21, 180, 202, 27);
		chooserNgayDi.setDateFormatString("dd/MM/yyyy");
		jp_Content_ThongTin.add(chooserNgayDi);
		((JTextField) chooserNgayDi.getDateEditor().getUiComponent()).setEditable(false);

		// Thêm listener để kiểm tra ngày chọn đi
		chooserNgayDi.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// Lấy ngày hôm nay với thời gian thiết lập là 00:00:00
				Calendar todayCal = Calendar.getInstance();
				todayCal.set(Calendar.HOUR_OF_DAY, 0);
				todayCal.set(Calendar.MINUTE, 0);
				todayCal.set(Calendar.SECOND, 0);
				todayCal.set(Calendar.MILLISECOND, 0);
				Date today = todayCal.getTime();

				if (chooserNgayDi.getDate() != null && chooserNgayDi.getDate().before(today)) {
					JOptionPane.showMessageDialog(null,
							"Ngày không hợp lệ! Vui lòng chọn ngày không trước ngày hôm nay.", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
					chooserNgayDi.setDate(null); // Xóa ngày đã chọn
				}
			}
		});

		chooserNgayVe = new JDateChooser();
		chooserNgayVe.setBounds(21, 240, 202, 27);
		chooserNgayVe.setDateFormatString("dd/MM/yyyy");
		chooserNgayVe.setEnabled(false);
		jp_Content_ThongTin.add(chooserNgayVe);
		((JTextField) chooserNgayVe.getDateEditor().getUiComponent()).setEditable(false);

		// Thêm listener để kiểm tra ngày chọn về
		chooserNgayVe.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date ngayDi = chooserNgayDi.getDate();

				// Kiểm tra nếu chọn ngày về khi khứ hồi được chọn
				if (chooserNgayVe.getDate() != null) {
					// Kiểm tra nếu ngày đi đã được chọn
					if (ngayDi == null) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đi trước khi chọn ngày về.",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						chooserNgayVe.setDate(null); // Xóa ngày đã chọn
						return; // Thoát khỏi phương thức
					}

					// Kiểm tra nếu ngày về trước ngày đi
					if (chooserNgayVe.getDate().before(ngayDi)) {
						JOptionPane.showMessageDialog(null, "Ngày không hợp lệ! Vui lòng chọn ngày sau ngày đi.",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						chooserNgayVe.setDate(null); // Xóa ngày đã chọn
					}
				}
			}
		});

		JLabel lbl_GaDi = new JLabel("Ga Đi");
		lbl_GaDi.setForeground(new Color(30, 144, 255));
		lbl_GaDi.setBounds(24, 10, 202, 13);
		jp_Content_ThongTin.add(lbl_GaDi);

		JLabel lbl_GaDen = new JLabel("Ga Đến");
		lbl_GaDen.setForeground(new Color(30, 144, 255));
		lbl_GaDen.setBounds(21, 63, 202, 13);
		jp_Content_ThongTin.add(lbl_GaDen);

		JLabel lbl_LuaChon = new JLabel("Lựa chọn");
		lbl_LuaChon.setForeground(new Color(30, 144, 255));
		lbl_LuaChon.setBounds(24, 111, 202, 13);
		jp_Content_ThongTin.add(lbl_LuaChon);

		JLabel lbl_ChonNgayDi = new JLabel("Ngày đi");
		lbl_ChonNgayDi.setForeground(new Color(30, 144, 255));
		lbl_ChonNgayDi.setBounds(21, 157, 202, 13);
		jp_Content_ThongTin.add(lbl_ChonNgayDi);

		JLabel lbl_ChonNgayVe = new JLabel("Ngày về");
		lbl_ChonNgayVe.setForeground(new Color(30, 144, 255));
		lbl_ChonNgayVe.setBounds(21, 217, 202, 13);
		jp_Content_ThongTin.add(lbl_ChonNgayVe);

		JPanel jp_Header_ThongTin = new JPanel();
		jp_Header_ThongTin.setLayout(null);
		jp_Header_ThongTin.setBackground(Color.LIGHT_GRAY);
		jp_Header_ThongTin.setBounds(0, 0, 244, 32);
		jp_timKiem.add(jp_Header_ThongTin);

		ImageIcon userIcon = new ImageIcon(getClass().getResource("/img/iconmenu.png"));
	    Image scaledUser = userIcon.getImage().getScaledInstance(30,32, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
	    jp_Header_ThongTin.setLayout(null);
	    userIconLabel = new JLabel(new ImageIcon(scaledUser));
	    userIconLabel.setBounds(0 ,0 , 30 ,32); // Cập nhật kích thước trên JLabel
	    jp_Header_ThongTin.add(userIconLabel);
//		JLabel downIconLabel = new JLabel((Icon) null);
//		downIconLabel.setBounds(0, 0, 30, 32);
//		jp_Header_ThongTin.add(downIconLabel);

		JLabel lbl_tieuDeTK = new JLabel("Thông tin hành trình");
		lbl_tieuDeTK.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tieuDeTK.setForeground(new Color(30, 144, 255));
		lbl_tieuDeTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_tieuDeTK.setBounds(30, 0, 214, 32);
		jp_Header_ThongTin.add(lbl_tieuDeTK);

		JPanel jp_GioVe = new JPanel();
		jp_GioVe.setLayout(null);
		jp_GioVe.setBackground(Color.WHITE);
		jp_GioVe.setBounds(10, 369, 244, 191);
		add(jp_GioVe);

		JPanel jp_Content_GioVe = new JPanel();
		jp_Content_GioVe.setBorder(new LineBorder(new Color(0, 0, 0)));
		jp_Content_GioVe.setLayout(null);
		jp_Content_GioVe.setBackground(Color.WHITE);
		jp_Content_GioVe.setBounds(0, 31, 244, 160);
		jp_GioVe.add(jp_Content_GioVe);
		
		btnMua = new RoundedButton("Mua", 15);
		btnMua.setForeground(new Color(255, 255, 255));
		btnMua.setBackground(new Color(51, 102, 153));

		// Chuyển trang banVeNhapThongTIn_Gui bằng button Mua
		btnMua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dsVeDatTam.size() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa có vé được đặt");
					return;
				}
				
				BanVeNhapThongTin_Gui banVeNhapThongTin_Gui = new BanVeNhapThongTin_Gui(BanVe_GUI.this, trangChu);
				trangChu.content.removeAll();
				trangChu.content.add(banVeNhapThongTin_Gui);
				trangChu.content.revalidate();
				trangChu.content.repaint();
			}
		});

		btnMua.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMua.setBounds(78, 126, 85, 27);
		jp_Content_GioVe.add(btnMua);

		// Copy vào DoiVe_GUI . Lưu ý bỏ nút
		JPanel jp_DanhSachVe = new JPanel();
		jp_DanhSachVe.setBorder(new LineBorder(new Color(0, 0, 0)));
		jp_DanhSachVe.setBackground(Color.WHITE);
		jp_DanhSachVe.setLayout(null);
		jp_DanhSachVe.setBounds(0, 0, 244, 122);
		jp_Content_GioVe.add(jp_DanhSachVe);
		
		lblTongTien = new JLabel("Tổng tiền: 0 đ");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setForeground(new Color(51, 102, 153));
		lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongTien.setBounds(10, 95, 224, 25);
		jp_Content_GioVe.add(lblTongTien);


		// JPanel chứa các vé
		jp_VeMua = new JPanel();
		jp_VeMua.setBackground(Color.WHITE);
		jp_VeMua.setLayout(new BoxLayout(jp_VeMua, BoxLayout.Y_AXIS));

		// Tạo JScrollPane cho jp_VeMua
		JScrollPane scrollPane = new JScrollPane(jp_VeMua);
		scrollPane.setBounds(0, 0, 244, 122);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		jp_DanhSachVe.add(scrollPane);

		JPanel jp_Header_GioVe = new JPanel();
		jp_Header_GioVe.setLayout(null);
		jp_Header_GioVe.setBackground(Color.LIGHT_GRAY);
		jp_Header_GioVe.setBounds(0, 0, 244, 32);
		jp_GioVe.add(jp_Header_GioVe);

		JLabel downIconLabel_1 = new JLabel((Icon) null);
		downIconLabel_1.setBounds(0, 0, 30, 32);
		jp_Header_GioVe.add(downIconLabel_1);

		JLabel lbl_tieuDeTK_GioVe = new JLabel("Giỏ Vé");
		lbl_tieuDeTK_GioVe.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tieuDeTK_GioVe.setForeground(new Color(30, 144, 255));
		lbl_tieuDeTK_GioVe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_tieuDeTK_GioVe.setBounds(30, 0, 214, 32);
		jp_Header_GioVe.add(lbl_tieuDeTK_GioVe);

		lblMaToa = new JLabel();
		lblMaToa.setBounds(532, 277, 272, 23);
		add(lblMaToa);
		lblMaToa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaToa.setHorizontalAlignment(SwingConstants.CENTER);

		btnTiep = new RoundedButton("Tiếp", 10);
		btnTiep.setForeground(new Color(255, 255, 255));
		btnTiep.setBackground(new Color(51, 102, 153));
		
		btnTiep.setBounds(1365, 25, 85, 21);
		add(btnTiep);
		btnTiep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_Chieu_1.setText("Chiều về: ");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				lbl_NgayDi_1.setText(sdf.format(chooserNgayVe.getDate()));
				lbl_Ga_1.setText(txt_GaDen.getText() + " - " + txt_GaDi.getText());
				LocalDate ngayDi = chooserNgayVe.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				jp_ThongTinChuyenTau.removeAll();
				jp_ThongTinChuyenTau.revalidate();
				jp_ThongTinChuyenTau.repaint();

				jp_TinhTrangToa.removeAll();
				jp_TinhTrangToa.revalidate();
				jp_TinhTrangToa.repaint();
				
				jp_TinhTrangToa.setPreferredSize(new Dimension(0, 76));
				scrollPaneTinhTrangToa.revalidate();
			    scrollPaneTinhTrangToa.repaint();

				jp_TinhTrangGhe.removeAll();
				jp_TinhTrangGhe.revalidate();
				jp_TinhTrangGhe.repaint();

				suKienBatDauChon(txt_GaDen.getText(), txt_GaDi.getText(),ngayDi);
			}
		});

		btnQuayLai = new RoundedButton("Quay lại", 10);
		btnQuayLai.setForeground(new Color(255, 255, 255));
		btnQuayLai.setBackground(new Color(51, 102, 153));
		
		btnQuayLai.setBounds(1270, 25, 85, 21);
		add(btnQuayLai);
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_Chieu_1.setText("Chiều đi: ");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				lbl_NgayDi_1.setText(sdf.format(chooserNgayDi.getDate()));
				lbl_Ga_1.setText(txt_GaDi.getText() + " - " + txt_GaDen.getText());
				LocalDate ngayDi = chooserNgayDi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

//				if (chuyenTauCu != null) {
//					ChuyenTau_JPanel pChuyenTau = new ChuyenTau_JPanel(chuyenTauCu);
//					pChuyenTau.setBounds(boundsPanel);
//					// su kien
//					loadToa(pChuyenTau, chuyenTauCu);
//					if (toaCu != null) {
//						Toa_JPanel pToa;
//						if (toaCu.getLoaiToa().equals("VIP")) {
//							pToa = new Toa_JPanel("", 2);
//						} else if (toaCu.getLoaiToa().equals("Giường nằm")) {
//							pToa = new Toa_JPanel("", 3);
//						} else {
//							pToa = new Toa_JPanel("", 4);
//						}
//						pToa.setBounds(boundsPanelToa);
//						// su kien
//						loadGhe(pToa, toaCu);
//					}
//				}
				
				jp_ThongTinChuyenTau.removeAll();
				jp_ThongTinChuyenTau.revalidate();
				jp_ThongTinChuyenTau.repaint();

				jp_TinhTrangToa.removeAll();
				jp_TinhTrangToa.revalidate();
				jp_TinhTrangToa.repaint();
				
				jp_TinhTrangToa.setPreferredSize(new Dimension(0, 76));
				scrollPaneTinhTrangToa.revalidate();
			    scrollPaneTinhTrangToa.repaint();

				jp_TinhTrangGhe.removeAll();
				jp_TinhTrangGhe.revalidate();
				jp_TinhTrangGhe.repaint();

				suKienBatDauChon(txt_GaDi.getText(), txt_GaDen.getText(), ngayDi);
			}
		});

		btnTiep.setVisible(false);
		btnQuayLai.setVisible(false);
		
		// Tạo viền bo góc với màu RGB (221, 221, 221)
        LineBorder lineBorder = new LineBorder(new Color(221, 221, 221), 2, true); // Viền bo góc
        TitledBorder titledBorder = new TitledBorder(lineBorder, "Chú thích"); // Thêm tiêu đề
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14)); // Font tiêu đề
        titledBorder.setTitleColor(Color.BLACK); // Màu chữ tiêu đề
		
		JPanel panel_ChuThich = new JPanel();
		panel_ChuThich.setBounds(1195, 56, 255, 504);
		panel_ChuThich.setBackground(Color.WHITE);
		panel_ChuThich.setBorder(titledBorder);;
		add(panel_ChuThich);
		panel_ChuThich.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setBounds(10, 14, 100, 49);
		
		ImageIcon containerIcon_GM;
		containerIcon_GM = new ImageIcon(getClass().getResource("/img/Toa_Ghe_Mem.png"));
		Image scaledContainerIcon_GM = containerIcon_GM.getImage().getScaledInstance(100, 49, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		lblNewLabel = new JLabel(new ImageIcon(scaledContainerIcon_GM));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 35, 100, 49);
		panel_ChuThich.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Toa ghế mềm");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(120, 44, 125, 31);
		panel_ChuThich.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
//		lblNewLabel_2.setBounds(10, 75, 100, 49);
		
		ImageIcon containerIcon_GN;
		containerIcon_GN = new ImageIcon(getClass().getResource("/img/Toa_Giuong_Nam.png"));
		Image scaledContainerIcon_GN = containerIcon_GN.getImage().getScaledInstance(100, 49, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		lblNewLabel_2 = new JLabel(new ImageIcon(scaledContainerIcon_GN));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 98, 100, 49);
		
		panel_ChuThich.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Toa giường nằm");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(120, 105, 125, 31);
		panel_ChuThich.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
//		lblNewLabel_3.setBounds(10, 138, 100, 49);
		
		ImageIcon containerIcon_VIP;
		containerIcon_VIP = new ImageIcon(getClass().getResource("/img/Toa_VIP.png"));
		Image scaledContainerIcon_VIP = containerIcon_VIP.getImage().getScaledInstance(100, 49, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		lblNewLabel_3 = new JLabel(new ImageIcon(scaledContainerIcon_VIP));
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(10, 161, 100, 49);
		
		panel_ChuThich.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Toa VIP");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(120, 168, 125, 31);
		panel_ChuThich.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
//		lblNewLabel_4.setBounds(10, 197, 100, 49);
		
		ImageIcon containerIcon_HC;
		containerIcon_HC = new ImageIcon(getClass().getResource("/img/Toa_Het_Slot.png"));
		Image scaledContainerIcon_HC = containerIcon_HC.getImage().getScaledInstance(100, 49, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		lblNewLabel_4 = new JLabel(new ImageIcon(scaledContainerIcon_HC));
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setBounds(10, 220, 100, 49);
		
		panel_ChuThich.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1_3 = new JLabel("Toa hết chỗ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(120, 227, 125, 31);
		panel_ChuThich.add(lblNewLabel_1_3);
		
		ImageIcon containerIcon_Ghe1;
		containerIcon_Ghe1 = new ImageIcon(getClass().getResource("/img/Ghe_1.png"));
		Image scaledContainerIcon_Ghe1 = containerIcon_Ghe1.getImage().getScaledInstance(26, 42, Image.SCALE_SMOOTH);
		// JLabel cho hình ảnh
		JLabel containerIconLabel_Ghe1 = new JLabel(new ImageIcon(scaledContainerIcon_Ghe1));
		containerIconLabel_Ghe1.setBounds(24, 311, 26, 42); // Đặt kích thước cho hình ảnh
		panel_ChuThich.add(containerIconLabel_Ghe1);
					
		JLabel lblNewLabel_1_3_1 = new JLabel("Ghế chưa đặt");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(86, 316, 159, 31);
		panel_ChuThich.add(lblNewLabel_1_3_1);
		
		ImageIcon containerIcon_Ghe2;
		containerIcon_Ghe2 = new ImageIcon(getClass().getResource("/img/Ghe_2.png"));
		Image scaledContainerIcon_Ghe2 = containerIcon_Ghe2.getImage().getScaledInstance(26, 42, Image.SCALE_SMOOTH);
		// JLabel cho hình ảnh
		JLabel containerIconLabel_Ghe2 = new JLabel(new ImageIcon(scaledContainerIcon_Ghe2));
		containerIconLabel_Ghe2.setBounds(24, 383, 26, 42); // Đặt kích thước cho hình ảnh
		panel_ChuThich.add(containerIconLabel_Ghe2);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Ghế đang chọn");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1_1.setBounds(86, 388, 159, 31);
		panel_ChuThich.add(lblNewLabel_1_3_1_1);
		
		ImageIcon containerIcon_Ghe0;
		containerIcon_Ghe0 = new ImageIcon(getClass().getResource("/img/Ghe_0.png"));
		Image scaledContainerIcon_Ghe0 = containerIcon_Ghe0.getImage().getScaledInstance(26, 42, Image.SCALE_SMOOTH);
		// JLabel cho hình ảnh
		JLabel containerIconLabel_Ghe0 = new JLabel(new ImageIcon(scaledContainerIcon_Ghe0));
		containerIconLabel_Ghe0.setBounds(24, 452, 26, 42); // Đặt kích thước cho hình ảnh
		panel_ChuThich.add(containerIconLabel_Ghe0);
		
		JLabel lblNewLabel_1_3_1_2 = new JLabel("Ghế đã đặt");
		lblNewLabel_1_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1_2.setBounds(86, 457, 159, 31);
		panel_ChuThich.add(lblNewLabel_1_3_1_2);
		
		// Thêm MouseListener vào contentPane
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Mất focus của txt_GaDi và txt_GaDen khi nhấp chuột ra ngoài
				jp_TinhTrangGhe.requestFocusInWindow();
			}
		});

	}

	private void suKienBatDauChon(String gaDi, String gaDen, LocalDate ngayDi) {

		lblMaToa.setText("");
		jp_ThongTinChuyenTau.removeAll();
		boolean isKhuHoi = rdbtn_KhuHoi.isSelected();
		if (isKhuHoi) {
			chooserNgayVe.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		ArrayList<ChuyenTau> dsTauHienThi = new ArrayList<ChuyenTau>();
		for (ChuyenTau chuyenTau : dsChuyenTau) {
			if ((ga_dao.getGaTheoMaGa(chuyenTau.getGaDi().getMaGa()).getTenGaRaw().equals(gaDi))
					&& (chuyenTau.getNgayDi().equals(ngayDi))) {
				for (Ga gaDung : chuyenTau.getTramDung()) {
					if (gaDen.equals(gaDung.getTenGaRaw()))
						dsTauHienThi.add(chuyenTau);
				}
				if (gaDen.equals(ga_dao.getGaTheoMaGa(chuyenTau.getGaDen().getMaGa()).getTenGaRaw()))
					dsTauHienThi.add(chuyenTau);
			}
		}
		
		if (dsTauHienThi.size() < 1) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy tàu đi cho tuyến của bạn.", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int x = 0, y = 0;
		for (ChuyenTau chuyenTau : dsTauHienThi) {
			// Tạo ChuyenTau_JPanel ban đầu với tham số false
			ChuyenTau_JPanel pChuyenTau = new ChuyenTau_JPanel(chuyenTau);
			pChuyenTau.setBounds(x, y, 123, 122);
			// su kien
			suKienTrenChuyen(pChuyenTau, chuyenTau);
			jp_ThongTinChuyenTau.add(pChuyenTau);
			x += 127; // Điều chỉnh vị trí cho panel tiếp theo
		}

		jp_ThongTinChuyenTau.revalidate();
		jp_ThongTinChuyenTau.repaint();
	}

	private void suKienTrenChuyen(ChuyenTau_JPanel pChuyenTau, ChuyenTau chuyenTau) {
		// Thêm MouseListener để thay đổi con trỏ khi di chuột vào panel
		pChuyenTau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pChuyenTau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pChuyenTau.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (pChuyenTau == chuyenTauTruocDo)
					return;
				// Đổi icon của chuyến tàu trước đó về trạng thái không được chọn
				if (chuyenTauTruocDo != null) {
					ImageIcon iconCu = new ImageIcon(getClass().getResource("/img/Chuyen_0.png"));
					Image scaledIconCu = iconCu.getImage().getScaledInstance(122, 140, Image.SCALE_SMOOTH);
					chuyenTauTruocDo.trainIconLabel.setIcon(new ImageIcon(scaledIconCu));
				}

				// Đổi icon của chuyến tàu được chọn
				ImageIcon iconMoi = new ImageIcon(getClass().getResource("/img/Chuyen_1.png"));
				Image scaledIconMoi = iconMoi.getImage().getScaledInstance(122, 140, Image.SCALE_SMOOTH);
				pChuyenTau.trainIconLabel.setIcon(new ImageIcon(scaledIconMoi));

				// Lưu lại panel hiện tại làm panel trước đó
				chuyenTauTruocDo = pChuyenTau;

				jp_TinhTrangGhe.removeAll();
				jp_TinhTrangGhe.revalidate();
				jp_TinhTrangGhe.repaint();
				lblMaToa.setText("");

				// Gọi loadToa để cập nhật thông tin toa
				loadToa(pChuyenTau, chuyenTau);
			}
		});
	}

	private void loadToa(ChuyenTau_JPanel pChuyenTau, ChuyenTau chuyenTau) {
		chuyenTauCu = chuyenTau;
		boundsPanel = pChuyenTau.getBounds();
		int chieuDaiPanel = 102;

		ImageIcon trainIcon;
		// Logo chương trình
		trainIcon = new ImageIcon(getClass().getResource("/img/Chuyen_1.png"));
		Image scaledTrainIcon = trainIcon.getImage().getScaledInstance(122, 140, Image.SCALE_SMOOTH);
		pChuyenTau.trainIconLabel.setIcon(new ImageIcon(scaledTrainIcon));

		Toa_JPanel toaDau = new Toa_JPanel(chuyenTau.getMaTau(), 1);
		toaDau.setBounds(0, 0, 100, 72);

		// Loại bỏ panel cũ và thêm panel mới vào container
		jp_TinhTrangToa.removeAll();
		jp_TinhTrangToa.add(toaDau);

		// Cập nhật giao diện
		jp_TinhTrangToa.revalidate();
		jp_TinhTrangToa.repaint();

		ChuyenTau chuyenTau1 = chuyenTau_dao.getChuyenTauTheoMaTau(chuyenTau.getMaTau());
		int x = 102, y = 0, count = 1;
		for (Toa toa : chuyenTau1.getDsToa()) {
			Toa_JPanel pToa;
			boolean isHetSlot = !toa.getDsGhe().stream().anyMatch(ghe -> ghe.isTrangThai());
			// Tạo Toa_JPanel
			if (isHetSlot) {
				pToa = new Toa_JPanel(count++ + "", 5);
			} else if (toa.getLoaiToa().equals("VIP")) {
				pToa = new Toa_JPanel(count++ + "", 2);
			} else if (toa.getLoaiToa().equals("Giường nằm")) {
				pToa = new Toa_JPanel(count++ + "", 3);
			} else {
				pToa = new Toa_JPanel(count++ + "", 4);
			}
			pToa.setBounds(x, y, 100, 72);
			// su kien
			suKienTrenToa(pToa, toa);
			jp_TinhTrangToa.add(pToa);
			x += 102; // Điều chỉnh vị trí cho panel tiếp theo
			chieuDaiPanel += 102;
		}
		
		jp_TinhTrangToa.setPreferredSize(new Dimension(chieuDaiPanel, 76));

		jp_ThongTinChuyenTau.revalidate();
		jp_ThongTinChuyenTau.repaint();
		
		jp_TinhTrangToa.revalidate();
	    jp_TinhTrangToa.repaint();
	    
	    scrollPaneTinhTrangToa.revalidate();
	    scrollPaneTinhTrangToa.repaint();
	}

	private void suKienTrenToa(Toa_JPanel pToa, Toa toa) {
		pToa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pToa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pToa.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				loadGhe(pToa, toa);
			}
		});
	}

	public void loadGhe(Toa_JPanel pToa, Toa toa) {
		toaCu = toa;
		boundsPanelToa = pToa.getBounds();

		toaCu = toa;

		lblMaToa.setText("Toa số " + pToa.getStrLabel() + ": " + toa.getLoaiToa());

		jp_TinhTrangGhe.removeAll();

		int x = 35, y = 10, count = 1;
		for (Ghe ghe : toa.getDsGhe()) {
			ImageIcon containerIcon;
			if (!ghe.isTrangThai()) {
				containerIcon = new ImageIcon(getClass().getResource("/img/Ghe_0.png"));
			} else if (ktDaDatTam(ghe)) {
				containerIcon = new ImageIcon(getClass().getResource("/img/Ghe_2.png"));
			} else {
				containerIcon = new ImageIcon(getClass().getResource("/img/Ghe_1.png"));
			}
			Image scaledContainerIcon = containerIcon.getImage().getScaledInstance(26, 42, Image.SCALE_SMOOTH);

			// Tạo một JPanel để chứa cả hình ảnh và số ghế
			JPanel pGhe = new JPanel();
			pGhe.setLayout(null);
			pGhe.setBounds(x, y, 26, 42);

			// JLabel cho hình ảnh
			JLabel containerIconLabel = new JLabel(new ImageIcon(scaledContainerIcon));
			containerIconLabel.setBounds(0, 0, 26, 42); // Đặt kích thước cho hình ảnh

			// JLabel cho số ghế
			JLabel countLabel = new JLabel(String.valueOf(count));
			countLabel.setForeground(Color.BLACK);
			countLabel.setFont(new Font("Arial", Font.BOLD, 12));
			countLabel.setHorizontalAlignment(SwingConstants.CENTER);
			countLabel.setVerticalAlignment(SwingConstants.CENTER);
			countLabel.setBounds(2, 0, 26, 42);

			pGhe.add(countLabel);
			pGhe.add(containerIconLabel);

			suKienTrenGhe(countLabel, containerIconLabel, ghe);

			// Thêm panel vào jp_TinhTrangGhe
			jp_TinhTrangGhe.add(pGhe);
			count++;

			// Điều chỉnh vị trí cho panel tiếp theo
			if (count % 4 == 1) {
				x += 48;
				y -= 171;
			} else if (count % 4 == 3) {
				x += 0;
				y += 73;
			} else {
				x += 0;
				y += 49;
			}
			jp_TinhTrangGhe.add(pGhe);
		}

		// Cập nhật giao diện
		jp_TinhTrangGhe.revalidate();
		jp_TinhTrangGhe.repaint();

		// Xử lý sự kiện khi click vào panel: thay thế bằng panel mới
		Toa_JPanel pToaMoi = new Toa_JPanel(pToa.getStrLabel(), pToa.getLoaiToa());
		pToaMoi.setBounds(pToa.getBounds()); // Giữ nguyên vị trí và kích thước của panel cũ

		// Gán lại sự kiện cho panel mới
		suKienTrenToa(pToaMoi, toa);

		// Loại bỏ panel cũ và thêm panel mới vào container
		jp_TinhTrangToa.remove(pToa);
		jp_TinhTrangToa.add(pToaMoi);

		// Cập nhật giao diện
		jp_TinhTrangToa.revalidate();
		jp_TinhTrangToa.repaint();
	}

	private void suKienTrenGhe(JLabel countLabel, JLabel gheLabel, Ghe ghe) {
		// Ban đầu gán hình ảnh cho ghế (có thể là Ghe_0 hoặc Ghe_1)
		countLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				if (ghe.isTrangThai()) {
					countLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				countLabel.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Chỉ cho phép tương tác nếu trạng thái ban đầu của ghế là true
				if (ghe.isTrangThai()) {
					// Thay đổi trạng thái hình ảnh giữa Ghe_1 và Ghe_2
					ImageIcon newIcon;
					if (ktDaDatTam(ghe)) {
						newIcon = new ImageIcon(getClass().getResource("/img/Ghe_1.png"));
						// Xóa đánh dấu
						if (lbl_Chieu_1.getText().equals("Chiều đi: ")) {
							soVeChieuDi--;
						}
						// Nếu đang chuyển về trạng thái chưa đặt ghế, hãy loại bỏ vé vừa thêm
						dsVeDatTam.removeIf(v -> (v.getSoGhe().getSoGhe() == ghe.getSoGhe())
								&& v.getToa().getMaToa().equals(ghe.getToa().getMaToa()));
					} else {
						if (dsVeDatTam.size() == 4) {
							JOptionPane.showMessageDialog(null,"Đã đạt tối đa số lượng vé có thể đặt trong một hóa đơn!");
							return;
						}
						if (lbl_Chieu_1.getText().equals("Chiều đi: ")) {
							if (rdbtn_KhuHoi.isSelected() && soVeChieuDi >= 2) {
								JOptionPane.showMessageDialog(null,"Đã đạt tối đa số lượng vé chiều đi có thể đặt trong một hóa đơn có khứ hồi!");
								return;
							}
							soVeChieuDi++;
						}			
						newIcon = new ImageIcon(getClass().getResource("/img/Ghe_2.png"));
						// Tạo vé mới
						String maVe = ve_DAO.generateMaVe();
						Toa toa = toa_DAO.getToaTheoMaToa(ghe.getToa().getMaToa());
						ChuyenTau chuyenTau = chuyenTau_dao.getChuyenTauTheoMaTau(toa.getMaTau().getMaTau());
						LocalDate ngayDi = chuyenTau.getNgayDi();
						LocalTime gioDi = chuyenTau.getGioDi();
						LocalDate ngayDen = chuyenTau.getNgayDen();
						LocalTime gioDen = chuyenTau.getGioDen();
						Ga gaDi = chuyenTau.getGaDi();
						Ga gaDen = chuyenTau.getTramDung().stream()
								.filter(ga -> ga.getTenGaRaw().equals(txt_GaDen.getText())).findFirst()
								.orElse(chuyenTau.getGaDen());
						String hang = toa.getLoaiToa();
						boolean trangThai = false;
						Ve ve = new Ve(maVe, chuyenTau, toa, ghe, null, ngayDi, gioDi, ngayDen, gioDen, gaDi, gaDen,
								hang, null, trangThai, null);
						dsVeDatTam.add(ve);
					}

					// Cập nhật icon mới cho JLabel
					Image scaledIcon = newIcon.getImage().getScaledInstance(26, 42, Image.SCALE_SMOOTH);
					gheLabel.setIcon(new ImageIcon(scaledIcon));

					// Cập nhật giao diện
					jp_ThongTinChuyenTau.revalidate();
					jp_ThongTinChuyenTau.repaint();

					// Tạo panel Vé
					jp_VeMua.removeAll();
					for (Ve ve : dsVeDatTam) {
						Ve_JPanel pVe = new Ve_JPanel(ve, dsVeDatTam, jp_VeMua);
						jp_VeMua.add(pVe);
					}
					
					// Cập nhật tổng tiền
					capNhatTongTien();
					// Điều chỉnh kích thước jp_VeMua dựa trên số lượng vé
					jp_VeMua.setPreferredSize(new Dimension(jp_VeMua.getWidth(), dsVeDatTam.size() * 70)); // Điều chỉnh chiều cao tùy vào kích thước vé

					// Làm mới giao diện
					jp_VeMua.revalidate();
					jp_VeMua.repaint();

				} else {
					// Nếu ghế không có trạng thái true ban đầu, không làm gì
					gheLabel.setCursor(Cursor.getDefaultCursor());
				}
			}
		});
	}
	// Thêm method này vào cuối class BanVe_GUI
	private void capNhatTongTien() {
	    float tongTien = 0;
	    for (Ve ve : dsVeDatTam) {
	        tongTien += ve.tinhGiaVe();
	    }
	    
	    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	    lblTongTien.setText("Tổng tiền: " + formatter.format(tongTien));
	}
	private boolean isValidatedTxtField() {
		if (!txt_GaDi.getText().equals("Nhập ga đi")) {
			if (dsGa.stream().anyMatch(ga -> (ga.getTenGaRaw().equals(txt_GaDi.getText())))) {
				if (!txt_GaDen.getText().equals("Nhập ga đến")) {
					if (dsGa.stream().anyMatch(ga -> (ga.getTenGaRaw().equals(txt_GaDen.getText())))) {
						if (!txt_GaDi.getText().equals(txt_GaDen.getText())) {
							if (chooserNgayDi.getDate() != null) {
								if (rdbtn_KhuHoi.isSelected()) {
									if (chooserNgayVe.getDate() != null) {
										return true;
									}
									JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày về!", "Thông báo", JOptionPane.ERROR_MESSAGE);
									return false;
								}
								return true;
							}
							JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đi!", "Thông báo", JOptionPane.ERROR_MESSAGE);
							return false;							
						}
						JOptionPane.showMessageDialog(null, "Ga đi và ga đến không được trùng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						return false;
					}
					JOptionPane.showMessageDialog(null, "Ga đến không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
					return false;					
				}
				JOptionPane.showMessageDialog(null, "Vui lòng nhập ga đến!", "Thông báo", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			JOptionPane.showMessageDialog(null, "Ga đi không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null, "Vui lòng nhập ga đi!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private void focusTxtField(JTextField txtField, String str) {
		txtField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtField.getText().equals(str)) {
					txtField.setText("");
					txtField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtField.getText().isEmpty()) {
					txtField.setForeground(SystemColor.textInactiveText);
					txtField.setText(str);
				}
			}
		});
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
					for (Ga ga : dsGa) {
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

	private boolean ktDaDatTam(Ghe ghe) {
		for (Ve ve : dsVeDatTam) {
			if (ve.getChuyenTau().getMaTau()
					.equals(toa_DAO.getToaTheoMaToa(ghe.getToa().getMaToa()).getMaTau().getMaTau()))
				if (ve.getToa().getMaToa().equals(ghe.getToa().getMaToa()))
					if (ve.getSoGhe().getSoGhe() == ghe.getSoGhe())
						return true;
		}
		return false;
	}
}
