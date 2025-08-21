package gui;

import java.awt.Color;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import components.ConTent_JPanel;
import components.RoundedButton;
import components.RoundedTextField;
import dao.Ve_DAO;
import entity.Ve;
import dao.Ga_DAO;
import dao.KhachHang_DAO;
import entity.ChiTietHoaDon;
import entity.Ga;
import entity.KhachHang;

public class QuanLyVe_Gui extends JPanel implements ActionListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoundedTextField txt_TenKH;
	private RoundedTextField txt_GaDi;
	private RoundedTextField txt_GaDen;
	private RoundedTextField txt_MaToa;
	private RoundedTextField txt_MaGhe;
	private RoundedTextField txt_MaVe;
	private RoundedTextField txt_MaCT;
	private RoundedTextField txt_NgayDi;
	private RoundedTextField txt_ChiTiet;
	//	private RoundedTextField txt_TrangThai;
	private JComboBox<String> comboBox_Hang;
	private JComboBox<String> comboBox_KhuyenMai;
	private JTable table;
	private RoundedButton btn_DoiVe;
	private RoundedButton btn_Tim;
	private DefaultTableModel model;
	private TableRowSorter<TableModel> sorter;
	private JRadioButton cb_TTTrue;
	private JRadioButton cb_TTFalse;
	private JPanel jp_TTV;
	private Ve_DAO dsVe = new Ve_DAO();
	private JDateChooser dateChooser_NgayDi;
	private KhachHang_DAO dsKh = new KhachHang_DAO();
	private Ga_DAO dsGa = new Ga_DAO();
	private JButton btnXuatVe;
	public Ve veDoi;
	private RoundedButton btn_LamMoi;
	private ButtonGroup group;

	public QuanLyVe_Gui(TrangChu_GUI trangChu) {
		setBackground(SystemColor.window);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1460, 570);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 10, 124, 28);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lb_quaylai = new JLabel("Quay lại");
		lb_quaylai.setBounds(45, 0, 69, 27);
		lb_quaylai.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lb_quaylai);

		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JLabel goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
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
		panel_1.add(goBackIconLabel);

		jp_TTV = new JPanel();
		jp_TTV.setBounds(35, 65, 356, 428);
		panel.add(jp_TTV);
		jp_TTV.setLayout(null);

		JPanel jp_HeaderTTV = new JPanel();
		jp_HeaderTTV.setBounds(0, 0, 356, 33);
		jp_HeaderTTV.setBackground(new Color(51, 102, 153));
		jp_TTV.add(jp_HeaderTTV);
		jp_HeaderTTV.setLayout(null);

		JLabel lb_TTV = new JLabel("Thông tin vé");
		lb_TTV.setForeground(new Color(255, 255, 255));
		lb_TTV.setBounds(0, 0, 356, 33);
		lb_TTV.setFont(new Font("Tahoma", Font.BOLD, 16));
		jp_HeaderTTV.add(lb_TTV);

		JLabel lb_TenKH = new JLabel("Tên khách hàng:");
		lb_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_TenKH.setBounds(10, 43, 97, 22);
		jp_TTV.add(lb_TenKH);

		JLabel lblGan = new JLabel("Ga đi:");
		lblGan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGan.setBounds(10, 75, 97, 22);
		jp_TTV.add(lblGan);

		JLabel lblNgyi = new JLabel("Ga đến:");
		lblNgyi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgyi.setBounds(10, 107, 97, 22);
		jp_TTV.add(lblNgyi);

		JLabel lblLoiV = new JLabel("Hạng:");
		lblLoiV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLoiV.setBounds(10, 139, 97, 22);
		jp_TTV.add(lblLoiV);

		JLabel lblMToa = new JLabel("Khuyến mãi:");
		lblMToa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMToa.setBounds(10, 171, 97, 22);
		jp_TTV.add(lblMToa);

		JLabel lblMSGh = new JLabel("Mã toa:");
		lblMSGh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMSGh.setBounds(10, 203, 97, 22);
		jp_TTV.add(lblMSGh);

		JLabel lblMV = new JLabel("Mã ghế:");
		lblMV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMV.setBounds(10, 235, 97, 22);
		jp_TTV.add(lblMV);

		JLabel lblMChuynTu = new JLabel("Mã vé:");
		lblMChuynTu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMChuynTu.setBounds(10, 267, 97, 22);
		jp_TTV.add(lblMChuynTu);

		JLabel lblGii = new JLabel("Mã chuyến tàu:");
		lblGii.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGii.setBounds(10, 299, 97, 22);
		jp_TTV.add(lblGii);

		JLabel lblGi = new JLabel("Ngày đi:");
		lblGi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGi.setBounds(10, 331, 97, 22);
		jp_TTV.add(lblGi);

		txt_TenKH = new RoundedTextField(10);
		txt_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_TenKH.setBounds(130, 43, 216, 22);
		jp_TTV.add(txt_TenKH);
		txt_TenKH.setColumns(10);

		txt_GaDi = new RoundedTextField(10);
		txt_GaDi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_GaDi.setColumns(10);
		txt_GaDi.setBounds(130, 75, 216, 22);
		jp_TTV.add(txt_GaDi);

		txt_GaDen = new RoundedTextField(10);
		txt_GaDen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_GaDen.setColumns(10);
		txt_GaDen.setBounds(130, 107, 216, 22);
		jp_TTV.add(txt_GaDen);

		comboBox_Hang = new JComboBox<String>();
		comboBox_Hang.setBounds(130, 139, 216, 22);
		comboBox_Hang.addItem("Giường nằm");
		comboBox_Hang.addItem("Ghế mềm");
		comboBox_Hang.addItem("VIP");
		jp_TTV.add(comboBox_Hang);

		comboBox_KhuyenMai = new JComboBox<String>();
		comboBox_KhuyenMai.setBounds(130, 171, 216, 22);
		comboBox_KhuyenMai.addItem("Trẻ em dưới 6 tuổi");
		comboBox_KhuyenMai.addItem("Trẻ em từ 6 đến 10 tuổi");
		comboBox_KhuyenMai.addItem("Người lớn");
		comboBox_KhuyenMai.addItem("Người lớn tuổi");
		comboBox_KhuyenMai.addItem("Sinh viên");
		jp_TTV.add(comboBox_KhuyenMai);

		txt_MaToa = new RoundedTextField(10);
		txt_MaToa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_MaToa.setColumns(10);
		txt_MaToa.setBounds(130, 203, 216, 22);
		jp_TTV.add(txt_MaToa);

		txt_MaGhe = new RoundedTextField(10);
		txt_MaGhe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_MaGhe.setColumns(10);
		txt_MaGhe.setBounds(130, 235, 216, 22);
		jp_TTV.add(txt_MaGhe);

		txt_MaVe = new RoundedTextField(10);
		txt_MaVe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_MaVe.setColumns(10);
		txt_MaVe.setBounds(130, 267, 216, 22);
		jp_TTV.add(txt_MaVe);

		txt_MaCT = new RoundedTextField(10);
		txt_MaCT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_MaCT.setColumns(10);
		txt_MaCT.setBounds(130, 299, 216, 22);
		jp_TTV.add(txt_MaCT);

		dateChooser_NgayDi = new JDateChooser();
		dateChooser_NgayDi.setBounds(130, 331, 216, 22);
		jp_TTV.add(dateChooser_NgayDi);
		dateChooser_NgayDi.setDateFormatString("dd/MM/yyyy");

		txt_NgayDi = new RoundedTextField(10);
		txt_NgayDi.setBounds(0, 0, 195, 22);
		dateChooser_NgayDi.add(txt_NgayDi);
		txt_NgayDi.setColumns(10);

		// Thêm sự kiện PropertyChangeListener
		dateChooser_NgayDi.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = dateChooser_NgayDi.getDate();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				txt_NgayDi.setText(dateFormat.format(selectedDate)); // Gán ngày vào JTextField
			} else {
				txt_NgayDi.setText(""); // Nếu không có ngày nào được chọn, làm rỗng JTextField
			}
		});


		JLabel lblGi_1 = new JLabel("Chi tiết:");
		lblGi_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGi_1.setBounds(10, 363, 97, 22);
		jp_TTV.add(lblGi_1);

		txt_ChiTiet = new RoundedTextField(10);
		txt_ChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_ChiTiet.setColumns(10);
		txt_ChiTiet.setBounds(130, 363, 216, 22);
		jp_TTV.add(txt_ChiTiet);

		JLabel lblGi_1_1 = new JLabel("Trạng thái:");
		lblGi_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGi_1_1.setBounds(10, 395, 97, 22);
		jp_TTV.add(lblGi_1_1);

		cb_TTTrue = new JRadioButton("Không khả dụng");
		cb_TTTrue.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cb_TTTrue.setToolTipText("");
		cb_TTTrue.setBounds(130, 395, 108, 22);
		jp_TTV.add(cb_TTTrue);

		cb_TTFalse = new JRadioButton("Khả dụng");
		cb_TTFalse.setToolTipText("");
		cb_TTFalse.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cb_TTFalse.setBounds(240, 395, 106, 22);
		jp_TTV.add(cb_TTFalse);

		// Tạo nhóm button để chỉ cho phép chọn một trong hai trạng thái
		group = new ButtonGroup();
		group.add(cb_TTTrue);
		group.add(cb_TTFalse);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(401, 65, 1050, 477);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1050, 477);
		panel_3.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"STT", "Tên khách hàng", "Ga đi", "Ga đến", "Hạng", "Khuyến mãi", "Toa", "Ghế", "Mã vé", "Mã chuyến tàu", "Ngày đi", "Giờ đi", "Trạng thái", "Chi tiết"
				}
				);
		sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30); // Cột "STT"
		table.getColumnModel().getColumn(1).setPreferredWidth(100); // Cột "Tên khách hàng"
		table.getColumnModel().getColumn(2).setPreferredWidth(70); // Cột "Ga đi"
		table.getColumnModel().getColumn(3).setPreferredWidth(70); // Cột "Ga đến"
		table.getColumnModel().getColumn(4).setPreferredWidth(90); // Cột "Hạng"
		table.getColumnModel().getColumn(5).setPreferredWidth(100); // Cột "Khuyến mãi"
		table.getColumnModel().getColumn(6).setPreferredWidth(60); // Cột "Toa"
		table.getColumnModel().getColumn(7).setPreferredWidth(30); // Cột "Ghế"
		table.getColumnModel().getColumn(8).setPreferredWidth(100); // Cột "Mã vé"
		table.getColumnModel().getColumn(9).setPreferredWidth(80); // Cột "Mã Chuyến tàu"
		table.getColumnModel().getColumn(10).setPreferredWidth(70); // Cột "Ngày đi"
		table.getColumnModel().getColumn(11).setPreferredWidth(40); // Cột "Giờ đi"
		table.getColumnModel().getColumn(12).setPreferredWidth(100); // Cột "Trạng thái"
		table.getColumnModel().getColumn(13).setPreferredWidth(110); // Cột "Chi tiết"
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Áp dụng cho cột "STT"
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
		
		table.setRowHeight(20);
		
		scrollPane.setViewportView(table);
		table.setRowHeight(25); // Set chiều cao hàng

		//Thêm sự kiện table listener
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
				int column =e.getColumn();
				if(column == 12) {
					String trangThaiValue = (String) table.getValueAt(row, column);
					if(trangThaiValue.equalsIgnoreCase("Không khả dụng")) {
						cb_TTTrue.setSelected(true);
					}else {
						cb_TTFalse.setSelected(true);
					}
				}
				if(column == 4) {
					String hangValue = (String) table.getValueAt(row, column);
					if(hangValue.equalsIgnoreCase("Giường nằm")) {
						comboBox_Hang.setSelectedIndex(0);
					}
					if(hangValue.equalsIgnoreCase("Ghế mềm"))
					{
						comboBox_Hang.setSelectedIndex(1);
					}
					if(hangValue.equalsIgnoreCase("VIP")) {
						comboBox_Hang.setSelectedIndex(2);
					}
				}
				if(column == 5) {
					String KMValue = (String) table.getValueAt(row, column);
					if(KMValue.equalsIgnoreCase("Trẻ em dưới 6 tuổi")) {
						comboBox_KhuyenMai.setSelectedIndex(0);
					}
					if(KMValue.equalsIgnoreCase("Trẻ em từ 6 đến 10 tuổi")) {
						comboBox_KhuyenMai.setSelectedIndex(1);
					}
					if(KMValue.equalsIgnoreCase("Người lớn")) {
						comboBox_KhuyenMai.setSelectedIndex(2);
					}
					if(KMValue.equalsIgnoreCase("Người lớn tuổi")) {
						comboBox_KhuyenMai.setSelectedIndex(3);
					}
					if(KMValue.equalsIgnoreCase("Sinh viên")) {
						comboBox_KhuyenMai.setSelectedIndex(4);
					}
				}
			}
		});

		btn_DoiVe = new RoundedButton("Đổi vé", 10);
		btn_DoiVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					veDoi = dsVe.getVeTheoMaVe(table.getValueAt(row, 8).toString());
					if (veDoi.isTrangThai()) {
						JOptionPane.showMessageDialog(null, "Vé quá hạn đổi hoặc không còn khả dụng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
						return;
					}
					DoiVe_GUI doiVe = new DoiVe_GUI(QuanLyVe_Gui.this,trangChu);
					trangChu.content.removeAll();
					trangChu.content.add(doiVe);
					trangChu.content.revalidate();
					trangChu.content.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn vé muốn đổi", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}	
		});
		btn_DoiVe.setForeground(new Color(255, 255, 255));
		btn_DoiVe.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_DoiVe.setBackground(new Color(51, 102, 153));
		btn_DoiVe.setBounds(76, 503, 110, 38);
		panel.add(btn_DoiVe);

		btn_Tim = new RoundedButton("Tìm", 15);
		btn_Tim.setBounds(168, 5, 82, 23);
		btn_Tim.setForeground(SystemColor.desktop);
		btn_Tim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_Tim.setBackground(SystemColor.activeCaptionBorder);
		jp_HeaderTTV.add(btn_Tim);
		
		btn_LamMoi = new RoundedButton("Làm mới", 15);
		btn_LamMoi.setText("Làm mới");
		btn_LamMoi.setForeground(SystemColor.desktop);
		btn_LamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_LamMoi.setBackground(SystemColor.activeCaptionBorder);
		btn_LamMoi.setBounds(260, 5, 82, 23);
		jp_HeaderTTV.add(btn_LamMoi);

		btnXuatVe = new RoundedButton("Xuất vé", 10);
		btnXuatVe.setForeground(Color.WHITE);
		btnXuatVe.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuatVe.setBackground(new Color(51, 102, 153));
		btnXuatVe.setBounds(219, 503, 110, 38);
		panel.add(btnXuatVe);

		table.addMouseListener(this);
		btn_Tim.addActionListener(this);
		btn_LamMoi.addActionListener(this);
		btnXuatVe.addActionListener(this);
		updateVe();
		datatoTable();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		int row = table.getSelectedRow();

		System.out.println(row);
		if (row != -1) {
			// Lấy mã vé từ cột thích hợp (cột 8 ở đây, cần đảm bảo đúng với cấu trúc bảng)
			String maVe = table.getValueAt(row, 8).toString(); 
			Ve ve = dsVe.getVeTheoMaVe(maVe);

			System.out.println(table.getValueAt(row, 1).toString());
			KhachHang kh = dsKh.getKhachHangTheoMaKH(ve.getKhachHang().getMaKH());
			Ga gaDi = dsGa.getGaTheoMaGa(ve.getGaDi().getMaGa());
			Ga gaDen = dsGa.getGaTheoMaGa(ve.getGaDen().getMaGa());
			String soGheString = String.valueOf(ve.getSoGhe().getSoGhe());

			// Cập nhật thông tin vào các trường nhập
			txt_MaVe.setText(ve.getMaVe());
			txt_TenKH.setText(kh.getTenKH());
			txt_GaDi.setText(gaDi.getTenGa());
			txt_GaDen.setText(gaDen.getTenGa());

			// Cập nhật trạng thái cho radio button
			cb_TTTrue.setSelected(ve.isTrangThai());
			cb_TTFalse.setSelected(!ve.isTrangThai());

			// Cập nhật ngày đi
			dateChooser_NgayDi.setDate(Date.from(ve.getNgayDi().atStartOfDay(ZoneId.systemDefault()).toInstant()));

			// Cập nhật thông tin toa và chuyến tàu
			txt_MaToa.setText(ve.getToa().getMaToa());
			txt_MaGhe.setText(soGheString);
			txt_MaCT.setText(ve.getChuyenTau().getMaTau());
			txt_ChiTiet.setText(ve.getChiTiet().getMaChiTiet());

			if(ve.getHang().equalsIgnoreCase("Giường nằm")) {
				comboBox_Hang.setSelectedIndex(0);
			}else if(ve.getHang().equalsIgnoreCase("Ghế mềm")) {
				comboBox_Hang.setSelectedIndex(1);
			}else {
				comboBox_Hang.setSelectedIndex(2);
			}
			if(ve.getKhuyenMai().equalsIgnoreCase("Trẻ em dưới 6 tuổi")) {
				comboBox_KhuyenMai.setSelectedIndex(0);
			}else if(ve.getKhuyenMai().equalsIgnoreCase("Trẻ em từ 6 đến 10 tuổi")) {
				comboBox_KhuyenMai.setSelectedIndex(1);
			}else if(ve.getKhuyenMai().equalsIgnoreCase("Người lớn")) {
				comboBox_KhuyenMai.setSelectedIndex(2);
			}else if(ve.getKhuyenMai().equalsIgnoreCase("Người lớn tuổi")) {
				comboBox_KhuyenMai.setSelectedIndex(3);
			}else {
				comboBox_KhuyenMai.setSelectedIndex(4);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object o = e.getSource();

		// Xóa các bộ lọc cũ trước khi áp dụng bộ lọc mới
		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

		// Lọc theo trạng thái hoàn thành
		if (cb_TTTrue.isSelected() || cb_TTFalse.isSelected()) {
			String status = cb_TTTrue.isSelected() ? "Không khả dụng" : "Khả dụng";

			filters.add(new RowFilter<Object, Object>() {
				@Override
				public boolean include(Entry<? extends Object, ? extends Object> entry) {
					String entryStatus = entry.getStringValue(12); // Giả sử cột 11 chứa trạng thái
					return entryStatus.equals(status);
				}
			});
		}
		if(o.equals(btn_Tim)) {
			// Áp dụng các bộ lọc vào TableRowSorter của bảng
			TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
			sorter.setRowFilter(RowFilter.andFilter(filters));
			if (txt_TenKH.getText() != null) {
				filterRows();
			}
			if (txt_GaDi.getText() != null) {
				filterRows();
			}
			if (txt_GaDen.getText() != null) {
				filterRows();
			}
			if (cb_TTFalse.isSelected()) {
				filterRows();
			}
			if (cb_TTTrue.isSelected()) {
				filterRows();
			}
			if (comboBox_Hang.getSelectedItem() != null) {
				filterRows();
			}
			if (comboBox_KhuyenMai.getSelectedItem() != null) {
				filterRows();
			}
			if (txt_MaToa.getText() != null) {
				filterRows();
			}
			if (txt_MaGhe.getText() != null) {
				filterRows();
			}
			if (txt_MaVe.getText() != null) {
				filterRows();
			}
			if (txt_MaCT.getText() != null) {
				filterRows();
			}
			String ngayDi = txt_NgayDi.getText().trim();
			if(!ngayDi.isEmpty()) {
				filterRows();
			}
			if(cb_TTFalse.isSelected() || cb_TTTrue.isSelected()) {
				filterRows();
			}
		}
		if (o.equals(btn_LamMoi)) {
			if (o.equals(btn_LamMoi)) {
			    // Xóa bộ lọc trước khi làm mới bảng
			    sorter.setRowFilter(null);

			    // Xóa dữ liệu cũ trong bảng
			    model.setRowCount(0);

			    // Tải lại dữ liệu vào bảng
			    datatoTable();

			    // Cập nhật giao diện bảng
			    model.fireTableDataChanged();
			}

		    
		}
		if (o.equals(btnXuatVe)) {	
			int row = table.getSelectedRow();
			//			HoaDon hoaDon = hoaDon_DAO.getHoaDonTheoMaHoaDon(table.getValueAt(row, 1).toString());
			Ve ve = dsVe.getVeTheoMaVe(table.getValueAt(row, 8).toString());
			if (ve.isTrangThai()) {
				JOptionPane.showMessageDialog(null, "Vé quá hạn đổi hoặc không còn khả dụng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String pdfPath = "Ve/" +ve.getMaVe() + ".pdf";
			ve.xuatVe(pdfPath);
			
			// Kiểm tra xem Desktop có được hỗ trợ không
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					File pdfFile = new File(pdfPath);  // Tạo đối tượng File từ đường dẫn
					desktop.open(pdfFile);  // Mở file bằng ứng dụng mặc định

					// Hiển thị hộp thoại xác nhận sau khi mở file
					int confirm = JOptionPane.showConfirmDialog(
							null, 
							"Bạn có muốn xóa file vé vừa tạo không?", 
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
	}
	//Hàm tải dữ liệu vào bảng
	public void datatoTable() {
		dsKh.reset();
		dsGa.reset();
		dsVe.reset();
		ArrayList<Ve> list = dsVe.docTuBangTheoNgayLap();
		int stt = 1; // Biến đếm bắt đầu từ 1 cho STT
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Định dạng cho giờ
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng cho ngày
		for (Ve ve : list) {
			KhachHang kh = dsKh.getKhachHangTheoMaKH(ve.getKhachHang().getMaKH());
			Ga gaDi = dsGa.getGaTheoMaGa(ve.getGaDi().getMaGa());
			Ga gaDen = dsGa.getGaTheoMaGa(ve.getGaDen().getMaGa());
			model.addRow(new Object[] {
					stt++, 
					kh.getTenKH(), 
					gaDi.getTenGa(),
					gaDen.getTenGa(),
					ve.getHang().equalsIgnoreCase("Ghế mềm") ? "Ghế mềm" : ve.getHang().equalsIgnoreCase("Giường nằm") ? "Giường nằm" : "VIP",
							ve.getKhuyenMai().equalsIgnoreCase("Trẻ em dưới 6 tuổi") ? "Trẻ em dưới 6 tuổi" : ve.getKhuyenMai().equalsIgnoreCase("Trẻ em từ 6 đến 10 tuổi") ? "Trẻ em từ 6 đến 10 tuổi" : ve.getKhuyenMai().equalsIgnoreCase("Người lớn") ? "Người lớn" : ve.getKhuyenMai().equalsIgnoreCase("Người lớn tuổi") ? "Người lớn tuổi" : "Sinh viên",
									ve.getToa().getMaToa(),
									ve.getSoGhe().getSoGhe(),
									ve.getMaVe(),
									ve.getChuyenTau().getMaTau(),
									ve.getNgayDi().format(dateFormatter),
									ve.getGioDi().format(timeFormatter),
									ve.isTrangThai() ? "Không khả dụng" : "Khả dụng",
											ve.getChiTiet().getMaChiTiet()
			});
		}
		//Mặc định các button và combobox trong thông tin nhân viên
		deleteField();	
	}
	//Hàm xóa thông tin 
	public void deleteField() {
		txt_TenKH.setText("");
		txt_GaDi.setText("");
		txt_GaDen.setText("");
		txt_MaVe.setText("");
		dateChooser_NgayDi.setDate(null);  // Đặt lại giá trị ngày thành null
		txt_MaToa.setText("");
		txt_MaGhe.setText("");
		txt_MaCT.setText("");
		txt_ChiTiet.setText("");
		comboBox_Hang.setSelectedItem(null);
		comboBox_KhuyenMai.setSelectedItem(null);
		group.clearSelection();
	}
	// Lớp FilterListener để lắng nghe các thay đổi trong các ô tìm kiếm
	@SuppressWarnings("unused")
	private class FilterListener implements DocumentListener{
		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			filterRows();
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			filterRows();
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			filterRows();
		}
	}
	// Hàm để thực hiện lọc
	private void filterRows() {
		String tenKH = txt_TenKH.getText().trim();
		String gaDi = txt_GaDi.getText().trim();
		String gaDen = txt_GaDen.getText().trim();
		String maToa = txt_MaToa.getText().trim();
		String maGhe = txt_MaGhe.getText().trim();
		String maVe = txt_MaVe.getText().trim();
		String maCT = txt_MaCT.getText().trim();
		String ngayDi = txt_NgayDi.getText().trim();
		String hang = comboBox_Hang.getSelectedItem() != null ? comboBox_Hang.getSelectedItem().toString().trim() : "";
		String khuyenMai = comboBox_KhuyenMai.getSelectedItem() != null ? comboBox_KhuyenMai.getSelectedItem().toString().trim() : "";
		String maChiTiet = txt_ChiTiet.getText().trim();

		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();

		// Thêm bộ lọc nếu các ô không trống
		if (!tenKH.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + tenKH, 1)); 
		}
		if (!gaDi.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + gaDi, 2)); 
		}
		if (!gaDen.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + gaDen, 3)); 
		}
		if (!maToa.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maToa, 6)); 
		}
		if (!maGhe.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maGhe, 7)); 
		}
		if (!maVe.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maVe, 8)); 
		}
		if (!maCT.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maCT, 9));
		}
		// Lọc theo ngày đi
		if (!ngayDi.isEmpty()) {
			Date dateTu = parseDate(ngayDi);
			if (dateTu != null) {
				filters.add(new RowFilter<Object, Object>() {
					@Override
					public boolean include(Entry<? extends Object, ? extends Object> entry) {
						Date entryDate = parseDate(entry.getStringValue(10)); // Giả sử cột 2 là cột ngày
						return entryDate != null && entryDate.equals(dateTu);
					}
				});
			}
		}

		if (!hang.isEmpty()) {
			filters.add(RowFilter.regexFilter(hang, 4));
		}

		if (!khuyenMai.isEmpty()) {
			filters.add(RowFilter.regexFilter(khuyenMai, 5));
		}

		if (cb_TTTrue.isSelected()) {
			filters.add(RowFilter.regexFilter("Không khả dụng", 12)); // Giả sử cột 11 là cột trạng thái
		} else if (cb_TTFalse.isSelected()) {
			filters.add(RowFilter.regexFilter("Khả dụng", 12));
		}

		if (!maChiTiet.isEmpty()) {
			filters.add(RowFilter.regexFilter("(?i)" + maChiTiet, 13));
		}

		if (filters.isEmpty()) {
			sorter.setRowFilter(null); // Loại bỏ bộ lọc nếu không có điều kiện nào
		} else {
			sorter.setRowFilter(RowFilter.andFilter(filters)); // Kết hợp các bộ lọc
		}
	}
	
	//Hàm kiểm tra vé hoàn thành hay chưa
	public void updateVe() {
		ArrayList<Ve> list = dsVe.docTuBang();
		LocalTime now = LocalTime.now(); 
		LocalDate ngayHienTai = LocalDate.now(); // Lấy ngày hiện tại
		for(Ve ve : list) {
			if (ve.getNgayDen().isBefore(ngayHienTai) || 
					(ve.getNgayDen().isEqual(ngayHienTai) && ve.getGioDen().isBefore(now))) {
				Ve veCapNhat = new Ve(ve.getMaVe(),ve.getChuyenTau(),ve.getToa(),ve.getSoGhe(),ve.getKhachHang(),ve.getNgayDi(),ve.getGioDi(),ve.getNgayDen(),ve.getGioDen(),ve.getGaDi(),ve.getGaDen(),ve.getHang(),ve.getKhuyenMai(),
						true, new ChiTietHoaDon(ve.getChiTiet().getMaChiTiet()));
				dsVe.update(veCapNhat);
			}
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
}
