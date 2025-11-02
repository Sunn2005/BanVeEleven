package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import components.ConTent_JPanel;
import components.RoundedButton;
import dao.Ca_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import dao.Ve_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.Ve;

import javax.swing.JTabbedPane;

import com.itextpdf.io.IOException;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ThongKe_GUI extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel lbl_quayLai;
	private JLabel goBackIconLabel;
	private JTabbedPane tabbedPane;
	private JPanel jp_tkdttheoca;
	private JPanel jp_ketQuaTheoCa;
	private JLabel caIconLabel;
	private JLabel lbl_ca;
	private JLabel lbl_titleC;
	private JLabel doanhThuTheoCaIconLabel;
	private JLabel lbl_doanhThuTheoCa;
	private JLabel lbl_titleDTTC;
	private JLabel sltvtcIconLabel;
	private JLabel lbl_sltvtc;
	private JLabel lbl_titleSLTVTC;
	private JLabel slvbtcIconLabel;
	private JLabel lbl_slvbtc;
	private JLabel lbl_titleSLVBTC;
	private ChartPanel chartPanelTheoCa_DTKM;
	private JPanel jp_tkdt;
	private JPanel jp_thoiGian;
	private JPanel jp_header;
	private JPanel jp_content;
	private JLabel lbl_doanhThu;
	private JLabel lbl_sltv;
	private JLabel lbl_slvb;
	private ChartPanel chartPanel;
	private JLabel lbl_doanhThuCT;
	private JLabel lbl_sltvct;
	private JLabel lbl_slvbct;
	private ChartPanel chartPanelCT;
	private JPanel jp_thoiGian1;
	private JPanel jp_header1;
	private JPanel jp_content1;
	private Color hoverLabelColor = new Color(0, 153, 255);
	private HoaDon_DAO dsHD = new HoaDon_DAO();
	private String tkdt_ngayBatDau;
	private String tkdt_ngayKetThuc;
	private JDateChooser dateChooser_TKDT_batDau;
	private JDateChooser dateChooser_TKDT_ketThuc;
	private ChiTietHoaDon_DAO dsCTHD = new ChiTietHoaDon_DAO();
	private Ve_DAO dsVe = new Ve_DAO();
	private DefaultCategoryDataset dataset;
	private JPanel jp_thongKe;
	private JDateChooser dateChooser_TKCT_batDau;
	private JDateChooser dateChooser_TKCT_ketThuc;
	private String tkct_ngayBatDau;
	private String tkct_ngayKetThuc;
	private JPanel jp_thongKeCT;
	private NhanVien_DAO dsNV = new NhanVien_DAO();
	private TaiKhoan_DAO dsTK = new TaiKhoan_DAO();
	private Ca_DAO dsCa = new Ca_DAO();
	private KhachHang_DAO dsKH = new KhachHang_DAO();
	private JButton btnXem_TKDT;
	private JButton btnXem_TKCT;
	private JPanel jp_thongKeTheoKhuyenMai_TheoCa;
	private JPanel jp_thongKeTheoHang_TheoCa;
	private ChartPanel chartPanelTheoCa_Hang_SLKM;
	private ChartPanel chartPanelTheoCa_Hang_TKDTH;
	private JButton btn_XuatFile_TheoCa;
	private JButton btn_XuatFile_TheoDoanhThu;
	private JButton btn_XuatFile_TheoChuyenTau;
	
	public ThongKe_GUI(TrangChu_GUI trangChu)  {
//		this.trangChu = trangChu;
		setBackground(SystemColor.text);
		setForeground(new Color(255, 255, 255));
		setBounds(0, 170, 1460, 570);
		setLayout(null);

		//JPanel quay lại
		JPanel jp_quayLai = new JPanel();
		jp_quayLai.setBackground(SystemColor.text);
		jp_quayLai.setBounds(10, 0, 124, 47);
		add(jp_quayLai);
		jp_quayLai.setLayout(null);

		//Icon Quay lại
		ImageIcon goBackIcon = new ImageIcon(getClass().getResource("/img/9054423_bx_arrow_back_icon.png"));
		Image scaledGoBack = goBackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		goBackIconLabel = new JLabel(new ImageIcon(scaledGoBack));
		jp_quayLai.add(goBackIconLabel);
		goBackIconLabel.setBounds(10, 10, 39, 27);
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
		lbl_quayLai.setBounds(45, 10, 69, 27);
		jp_quayLai.add(lbl_quayLai);

		// Khởi tạo tabbedPane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(46, 61, 1373, 486);
		add(tabbedPane);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 15));

		//TabbPane thống kê doanh thu theo ca
		jp_tkdttheoca = new JPanel();
		jp_tkdttheoca.setBackground(SystemColor.textHighlightText);
		tabbedPane.addTab("Thống kê doanh thu theo ca", null, jp_tkdttheoca, null);
		jp_tkdttheoca.setLayout(null);

		//JPane kết quả theo ca
		jp_ketQuaTheoCa = new JPanel();
		jp_ketQuaTheoCa.setBackground(SystemColor.controlHighlight);
		jp_ketQuaTheoCa.setBounds(29, 10, 1311, 115);
		jp_tkdttheoca.add(jp_ketQuaTheoCa);

		//Icon ca
		ImageIcon caIcon = new ImageIcon(getClass().getResource("/img/work-schedule.png"));
		Image scaledCa = caIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQuaTheoCa.setLayout(null);
		caIconLabel = new JLabel(new ImageIcon(scaledCa));
		jp_ketQuaTheoCa.add(caIconLabel);
		caIconLabel.setBounds(66, 41, 40, 40);

		lbl_ca = new JLabel("");
		lbl_ca.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_ca.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ca.setBounds(111, 41, 114, 20);
		jp_ketQuaTheoCa.add(lbl_ca);

		lbl_titleC = new JLabel("Doanh thu");
		lbl_titleC.setBackground(SystemColor.windowBorder);
		lbl_titleC.setForeground(new Color(105, 105, 105));
		lbl_titleC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleC.setBounds(134, 65, 71, 13);
		jp_ketQuaTheoCa.add(lbl_titleC);

		//Icon Doanh Thu theo ca
		ImageIcon doanhThuTheoCaIcon = new ImageIcon(getClass().getResource("/img/coin_1.png"));
		Image scaledDoanhThuTheoCa = doanhThuTheoCaIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQuaTheoCa.setLayout(null);
		doanhThuTheoCaIconLabel = new JLabel(new ImageIcon(scaledDoanhThuTheoCa));
		jp_ketQuaTheoCa.add(doanhThuTheoCaIconLabel);
		doanhThuTheoCaIconLabel.setBounds(334, 41, 40, 40);

		lbl_doanhThuTheoCa = new JLabel();
		lbl_doanhThuTheoCa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_doanhThuTheoCa.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_doanhThuTheoCa.setBounds(384, 41, 114, 20);
		jp_ketQuaTheoCa.add(lbl_doanhThuTheoCa);

		lbl_titleDTTC = new JLabel("Doanh thu");
		lbl_titleDTTC.setBackground(SystemColor.windowBorder);
		lbl_titleDTTC.setForeground(new Color(105, 105, 105));
		lbl_titleDTTC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleDTTC.setBounds(406, 68, 71, 13);
		jp_ketQuaTheoCa.add(lbl_titleDTTC);

		//Icon số lượng trả vé theo ca
		ImageIcon sltvtcIcon = new ImageIcon(getClass().getResource("/img/money-back_1.png"));
		Image scaledSLTVTC = sltvtcIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQuaTheoCa.setLayout(null);
		sltvtcIconLabel = new JLabel(new ImageIcon(scaledSLTVTC));
		jp_ketQuaTheoCa.add(sltvtcIconLabel);
		sltvtcIconLabel.setBounds(615, 41, 40, 40);

		lbl_sltvtc = new JLabel();
		lbl_sltvtc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_sltvtc.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sltvtc.setBounds(665, 41, 114, 20);
		jp_ketQuaTheoCa.add(lbl_sltvtc);

		lbl_titleSLTVTC = new JLabel("Số lượng trả vé");
		lbl_titleSLTVTC.setBackground(SystemColor.windowBorder);
		lbl_titleSLTVTC.setForeground(new Color(105, 105, 105));
		lbl_titleSLTVTC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleSLTVTC.setBounds(675, 61, 104, 20);
		jp_ketQuaTheoCa.add(lbl_titleSLTVTC);

		//Icon số lượng vé bán theo ca
		ImageIcon slvbtcIcon = new ImageIcon(getClass().getResource("/img/loan_1.png"));
		Image scaledSLVBTC = slvbtcIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQuaTheoCa.setLayout(null);
		slvbtcIconLabel = new JLabel(new ImageIcon(scaledSLVBTC));
		jp_ketQuaTheoCa.add(slvbtcIconLabel);
		slvbtcIconLabel.setBounds(888, 41, 40, 40);

		lbl_slvbtc = new JLabel();
		lbl_slvbtc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_slvbtc.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_slvbtc.setBounds(938, 41, 114, 20);
		jp_ketQuaTheoCa.add(lbl_slvbtc);

		lbl_titleSLVBTC = new JLabel("Số lượng vé bán");
		lbl_titleSLVBTC.setBackground(SystemColor.windowBorder);
		lbl_titleSLVBTC.setForeground(new Color(105, 105, 105));
		lbl_titleSLVBTC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleSLVBTC.setBounds(948, 61, 126, 20);
		jp_ketQuaTheoCa.add(lbl_titleSLVBTC);

		//JPane chứa thống kê theo ca
		JPanel jp_thongKeTheoCa = new JPanel();
		jp_thongKeTheoCa.setBounds(28, 133, 1311, 308);
		jp_tkdttheoca.add(jp_thongKeTheoCa);
		
		
		//JFreeChat thống kê theo đối tượng khuyến mãi
		//Khởi tạo dữ liệu
		DefaultCategoryDataset datasetTheoDoiTuongKhuyenMai = createDatasetTKTheoCa(trangChu);
		// Create chart
		JFreeChart chartDoiTuongKhuyenMai = ChartFactory.createBarChart(
				"Thống kê doanh thu theo đối tượng khuyến mãi",
				"Đối tượng bán",
				"Doanh thu",
				datasetTheoDoiTuongKhuyenMai
				);
		// Tạo ChartPanel và thiết lập kích thước
		chartPanelTheoCa_DTKM = new ChartPanel(chartDoiTuongKhuyenMai);
		jp_thongKeTheoCa.setLayout(null);
		// Giảm kích cỡ chữ cho tiêu đề
		chartDoiTuongKhuyenMai.getTitle().setFont(new Font("Arial", Font.PLAIN, 16));
		
		//JFreeChat Hiển thị số lượng khách mua theo PieChart
		//Khởi tạo dữ liệu
		DefaultPieDataset<String> dataset = 
				//createDatasetTKTheoCa();
				createDatasetPieChart(trangChu);
		// Create chart
		// Tạo biểu đồ bánh
		JFreeChart chartTheoHang_SLKM = ChartFactory.createPieChart(
		        "Số lượng khách mua", // Tiêu đề biểu đồ
		        dataset,                                     // Dataset
		        true,                                             // Hiển thị legend?
		        true,                                             // Hiển thị tooltips?
		        false);                                           // Hiển thị URLs?
		chartPanelTheoCa_Hang_SLKM = new ChartPanel(chartTheoHang_SLKM);
		// Giảm kích cỡ chữ cho tiêu đề
		chartTheoHang_SLKM.getTitle().setFont(new Font("Arial", Font.PLAIN, 16));
		// Tạo ChartPanel và thiết lập kích thước
		
		DefaultCategoryDataset datasetTKDTTheoHang = 
				//createDataset();
				createDatasetTKHangTheoCa(trangChu);
		// Create chart
		JFreeChart chartDoanhThuTheoHang = ChartFactory.createBarChart(
				"Thống kê doanh thu theo hạng",
				"Đối tượng bán",
				"Doanh thu",
				datasetTKDTTheoHang,
				PlotOrientation.HORIZONTAL,
				true,                     // Hiển thị legend?
	            true,                     // Hiển thị tooltips?
	            false                     // Hiển thị URLs?// Chỉ định chiều ngang cho biểu đồ
				);
		// Tạo ChartPanel và thiết lập kích thước
		chartPanelTheoCa_Hang_TKDTH= new ChartPanel(chartDoanhThuTheoHang);
		
		chartDoanhThuTheoHang.getTitle().setFont(new Font("Arial", Font.PLAIN, 16));
		JTabbedPane tabbedPane_theoCa = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane_theoCa.setBounds(10, 10, 1290, 288);
		jp_thongKeTheoCa.add(tabbedPane_theoCa);
		
		
		jp_thongKeTheoKhuyenMai_TheoCa = new JPanel();
		tabbedPane_theoCa.addTab("Đối tượng", null, jp_thongKeTheoKhuyenMai_TheoCa, null);
		jp_thongKeTheoKhuyenMai_TheoCa.setLayout(null);
		chartPanelTheoCa_DTKM.setBounds(0, 0,  1205, 280);
		// Thêm ChartPanel vào jp_thongKe
		jp_thongKeTheoKhuyenMai_TheoCa.add(chartPanelTheoCa_DTKM);
		
		jp_thongKeTheoHang_TheoCa = new JPanel();
		tabbedPane_theoCa.addTab("Hạng", null, jp_thongKeTheoHang_TheoCa, null);
		jp_thongKeTheoHang_TheoCa.setLayout(null);
		//add PieChart số lượng khách mua
		chartPanelTheoCa_Hang_SLKM.setBounds(0, 0, 500, 280);
		// Thêm ChartPanel vào jp_thongKe
		jp_thongKeTheoHang_TheoCa.add(chartPanelTheoCa_Hang_SLKM);
		
		//add Barchart thống kê doanh thu theo hạng
		chartPanelTheoCa_Hang_TKDTH.setBounds(510, 0 ,700, 280);
		jp_thongKeTheoHang_TheoCa.add(chartPanelTheoCa_Hang_TKDTH);
		//TabbPane thống kê doanh thu
		jp_tkdt = new JPanel();
		jp_tkdt.setBackground(SystemColor.text);
		tabbedPane.addTab("Thống kê doanh thu", null, jp_tkdt, null);
		jp_tkdt.setLayout(null);

		//JPane thời gian 
		jp_thoiGian = new JPanel();
		jp_thoiGian.setBackground(Color.WHITE);
		jp_thoiGian.setBounds(28, 10, 374, 115);
		jp_tkdt.add(jp_thoiGian);
		jp_thoiGian.setLayout(null);

		//JPane header chưa tiêu đề 
		jp_header = new JPanel();
		jp_header.setBackground(new Color(51, 102, 153));
		jp_header.setBounds(0, 0, 374, 35);
		jp_thoiGian.add(jp_header);
		jp_header.setLayout(null);

		//JLabel tiêu đề 
		JLabel lblNewLabel_2 = new JLabel("Thời gian");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 0, 95, 35);
		jp_header.add(lblNewLabel_2);
		
			
		btnXem_TKDT = new RoundedButton("Xem", 15);
		btnXem_TKDT.setBackground(SystemColor.activeCaptionBorder);
		btnXem_TKDT.setForeground(SystemColor.desktop);
//		btnXem_TKDT = new JButton("Xem");
		btnXem_TKDT.setBounds(279, 10, 85, 21);
		jp_header.add(btnXem_TKDT);


		//JPane chứa content
		jp_content = new JPanel();
		jp_content.setBackground(SystemColor.controlHighlight);
		jp_content.setBounds(0, 36, 374, 79);
		jp_thoiGian.add(jp_content);
		jp_content.setLayout(null);

		// Ngày bắt đầu 
		dateChooser_TKDT_batDau = new JDateChooser();
		dateChooser_TKDT_batDau.setDateFormatString("dd/MM/yyyy");
		dateChooser_TKDT_batDau.setBounds(32, 5, 301, 29);
		jp_content.add(dateChooser_TKDT_batDau);
		// Thêm sự kiện khi người dùng chọn ngày để xóa chữ gợi ý
		dateChooser_TKDT_batDau.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = dateChooser_TKDT_batDau.getDate();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				tkdt_ngayBatDau= dateFormat.format(selectedDate);
			} else {
				tkdt_ngayBatDau = "";  // Đặt lại chữ gợi ý nếu không có ngày nào được chọn
			}
		});

		//Ngày kết thúc
		dateChooser_TKDT_ketThuc = new JDateChooser();
		dateChooser_TKDT_ketThuc.setDateFormatString("dd/MM/yyyy");
		dateChooser_TKDT_ketThuc.setBounds(32, 40, 301, 29);
		jp_content.add(dateChooser_TKDT_ketThuc);
		// Thêm sự kiện khi người dùng chọn ngày để xóa chữ gợi ý
		dateChooser_TKDT_ketThuc.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = dateChooser_TKDT_ketThuc.getDate();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				tkdt_ngayKetThuc= dateFormat.format(selectedDate);
			} else {
				tkdt_ngayBatDau = "";  // Đặt lại chữ gợi ý nếu không có ngày nào được chọn
			}
		});
		//JPane chứa kết quả thống kê
		JPanel jp_ketQua = new JPanel();
		jp_ketQua.setBackground(SystemColor.controlHighlight);
		jp_ketQua.setBounds(412, 10, 928, 115);
		jp_tkdt.add(jp_ketQua);

		//Icon Doanh Thu
		ImageIcon doanhThuIcon = new ImageIcon(getClass().getResource("/img/coin_1.png"));
		Image scaledDoanhThu = doanhThuIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQua.setLayout(null);
		JLabel doanhThuIconLabel = new JLabel(new ImageIcon(scaledDoanhThu));
		jp_ketQua.add(doanhThuIconLabel);
		doanhThuIconLabel.setBounds(66, 41, 40, 40);

		lbl_doanhThu = new JLabel();
		lbl_doanhThu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_doanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_doanhThu.setBounds(111, 41, 114, 20);
		jp_ketQua.add(lbl_doanhThu);

		JLabel lbl_titleDT = new JLabel("Doanh thu");
		lbl_titleDT.setBackground(SystemColor.windowBorder);
		lbl_titleDT.setForeground(new Color(105, 105, 105));
		lbl_titleDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleDT.setBounds(134, 65, 114, 13);
		jp_ketQua.add(lbl_titleDT);

		//Icon số lượng trả vé
		ImageIcon sltvIcon = new ImageIcon(getClass().getResource("/img/money-back_1.png"));
		Image scaledSLTV = sltvIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQua.setLayout(null);
		JLabel sltvIconLabel = new JLabel(new ImageIcon(scaledSLTV));
		jp_ketQua.add(sltvIconLabel);
		sltvIconLabel.setBounds(292, 41, 40, 40);

		lbl_sltv = new JLabel();
		lbl_sltv.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_sltv.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sltv.setBounds(342, 41, 114, 20);
		jp_ketQua.add(lbl_sltv);

		JLabel lbl_titleSLTV = new JLabel("Số lượng trả vé");
		lbl_titleSLTV.setBackground(SystemColor.windowBorder);
		lbl_titleSLTV.setForeground(new Color(105, 105, 105));
		lbl_titleSLTV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleSLTV.setBounds(352, 61, 104, 20);
		jp_ketQua.add(lbl_titleSLTV);


		//Icon số lượng vé bán
		ImageIcon slvbIcon = new ImageIcon(getClass().getResource("/img/loan_1.png"));
		Image scaledSLVB = slvbIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQua.setLayout(null);
		JLabel slvbIconLabel = new JLabel(new ImageIcon(scaledSLVB));
		jp_ketQua.add(slvbIconLabel);
		slvbIconLabel.setBounds(519, 41, 40, 40);

		lbl_slvb = new JLabel();
		lbl_slvb.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_slvb.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_slvb.setBounds(569, 41, 114, 20);
		jp_ketQua.add(lbl_slvb);

		JLabel lbl_titleSLVB = new JLabel("Số lượng vé bán");
		lbl_titleSLVB.setBackground(SystemColor.windowBorder);
		lbl_titleSLVB.setForeground(new Color(105, 105, 105));
		lbl_titleSLVB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleSLVB.setBounds(579, 61, 126, 20);
		jp_ketQua.add(lbl_titleSLVB);
		
		btn_XuatFile_TheoDoanhThu = new RoundedButton("Xuất File", 15);
		btn_XuatFile_TheoDoanhThu.setForeground(new Color(255, 255, 255));
		btn_XuatFile_TheoDoanhThu.setBackground(new Color(51, 102, 153));
		btn_XuatFile_TheoDoanhThu.setBounds(809, 10, 96, 33);
		jp_ketQua.add(btn_XuatFile_TheoDoanhThu);
		btn_XuatFile_TheoDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_XuatFile_TheoDoanhThu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// Kiểm tra ngày bắt đầu và ngày kết thúc
		        if (dateChooser_TKDT_batDau.getDate() == null || dateChooser_TKDT_ketThuc.getDate() == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu và ngày kết thúc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				String formattedNgayBatDau = sqlDateFormat.format(dateChooser_TKDT_batDau.getDate());
				String formattedNgayKetThuc = sqlDateFormat.format(dateChooser_TKDT_ketThuc.getDate());
				NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
				// Lấy danh sách hóa đơn cần xuất
		        ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoNgayLapHD(formattedNgayBatDau, formattedNgayKetThuc);
		        ArrayList<Ve> data_TheoDoanhThu = new ArrayList<Ve>();
		        if (listHD == null) {
					return;
				}
				// Duyệt qua từng hóa đơn và kiểm tra thời gian
				for (HoaDon hd : listHD) {
					if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
						 continue; // Bỏ qua hóa đơn đã hoàn tiền
					}
					ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
					if(cthd.getSoLuong() == 0) {
						continue;
					}
					if (cthd != null) {
						ArrayList<Ve> dsVeTheoChiTiet = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
				        if (dsVeTheoChiTiet != null) {
				            data_TheoDoanhThu.addAll(dsVeTheoChiTiet);
				        }	
					}
				}
		        if (data_TheoDoanhThu == null || data_TheoDoanhThu.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất Excel!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				// Gọi phương thức xuất file Excel
		        try {
		            xuatExcelDoanhThu(data_TheoDoanhThu,nv,lbl_doanhThu,lbl_slvb,lbl_sltv);
		            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } catch (IOException | java.io.IOException ex) {
		            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
			}
		});
		
		//JPane chứa JFreeChat
		jp_thongKe = new JPanel();
		jp_thongKe.setBounds(28, 133, 1311, 308);
//		System.out.println("đúng");
		jp_thongKe.setBackground(SystemColor.controlHighlight);
		jp_tkdt.add(jp_thongKe);
		jp_thongKe.setLayout(null);


		//TabbPane thống kê chuyến tàu
		JPanel jp_tkct = new JPanel();
		jp_tkct.setBackground(SystemColor.textHighlightText);
		tabbedPane.addTab("Thống kê chuyến tàu", null, jp_tkct, null);
		jp_tkct.setLayout(null);

		//JPane thời gian 
		jp_thoiGian1 = new JPanel();
		jp_thoiGian1.setBackground(Color.WHITE);
		jp_thoiGian1.setBounds(28, 10, 374, 115);
		jp_tkct.add(jp_thoiGian1);
		jp_thoiGian1.setLayout(null);

		//JPane header chưa tiêu đề 
		jp_header1 = new JPanel();
		jp_header1.setBackground(new Color(51, 102, 153));
		jp_header1.setBounds(0, 0, 374, 35);
		jp_thoiGian1.add(jp_header1);
		jp_header1.setLayout(null);
		//JLabel tiêu đề 
		JLabel lblNewLabel_3 = new JLabel("Thời gian");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 0, 95, 35);
		jp_header1.add(lblNewLabel_3);

		//Icon xổ xuống
		ImageIcon downIcon1 = new ImageIcon(getClass().getResource("/img/Polygon_20.png"));
		Image scaledDown1 = downIcon1.getImage().getScaledInstance(20 ,20, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		JLabel downIconLabel1 = new JLabel(new ImageIcon(scaledDown1));
		jp_header1.add(downIconLabel1);
		
		btnXem_TKCT = new RoundedButton("Xem", 15);
		btnXem_TKCT.setBackground(SystemColor.activeCaptionBorder);
		btnXem_TKCT.setForeground(SystemColor.desktop);

		btnXem_TKCT.setBounds(279, 10, 85, 21);
		jp_header1.add(btnXem_TKCT);
		
		//JPane chứa content
		jp_content1 = new JPanel();
		jp_content1.setBackground(SystemColor.controlHighlight);
		jp_content1.setBounds(0, 36, 374, 79);
		jp_thoiGian1.add(jp_content1);
		jp_content1.setLayout(null);

		dateChooser_TKCT_batDau = new JDateChooser();
		dateChooser_TKCT_batDau.setDateFormatString("dd/MM/yyyy");
		dateChooser_TKCT_batDau.setBounds(32, 5, 301, 29);
		jp_content1.add(dateChooser_TKCT_batDau);
		dateChooser_TKCT_batDau.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = dateChooser_TKCT_batDau.getDate();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				tkct_ngayBatDau= dateFormat.format(selectedDate);
			} else {
				tkct_ngayBatDau = "";  // Đặt lại chữ gợi ý nếu không có ngày nào được chọn
			}
		});

		dateChooser_TKCT_ketThuc = new JDateChooser();
		dateChooser_TKCT_ketThuc.setDateFormatString("dd/MM/yyyy");
		dateChooser_TKCT_ketThuc.setBounds(32, 40, 301, 29);
		jp_content1.add(dateChooser_TKCT_ketThuc);
		dateChooser_TKCT_ketThuc.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = dateChooser_TKCT_ketThuc.getDate();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				tkct_ngayKetThuc= dateFormat.format(selectedDate);
			} else {
				tkct_ngayKetThuc = "";  // Đặt lại chữ gợi ý nếu không có ngày nào được chọn
			}
		});

		//JPane kết quả chuyến tàu
		JPanel jp_ketQuaCT = new JPanel();
		jp_ketQuaCT.setBackground(SystemColor.controlHighlight);
		jp_ketQuaCT.setBounds(412, 10, 928, 115);
		jp_tkct.add(jp_ketQuaCT);

		//Icon Doanh Thu chuyến tàu
		ImageIcon doanhThuCTIcon = new ImageIcon(getClass().getResource("/img/coin_1.png"));
		Image scaledDoanhThuCT = doanhThuCTIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQuaTheoCa.setLayout(null);
		
		btn_XuatFile_TheoCa = new RoundedButton("Xuất File", 15);
		btn_XuatFile_TheoCa.setForeground(new Color(255, 255, 255));
		btn_XuatFile_TheoCa.setBackground(new Color(51, 102, 153));
		btn_XuatFile_TheoCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_XuatFile_TheoCa.setBounds(1205, 10, 96, 33);
		jp_ketQuaTheoCa.add(btn_XuatFile_TheoCa);
		btn_XuatFile_TheoCa.addActionListener(new ActionListener() {
			
			private ArrayList<Ve> data_TheoCa;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//////////////////////////////Kiểm tra VaoCa và ketCa có null hay không///////////////
				LocalDateTime thoiGianBatDauCa;
				if (trangChu.vaoCa != null) {
					thoiGianBatDauCa = trangChu.vaoCa; // Gán đúng giá trị của `trangChu.vaoCa`
				} else {
					thoiGianBatDauCa = LocalDateTime.now();
				}
				LocalDateTime thoiGianKetThucCa;
				if (trangChu.ketCa != null) {
					thoiGianKetThucCa = trangChu.ketCa;
				} else {
					thoiGianKetThucCa = LocalDateTime.now();
				}
				/////////////////////////////
				
				NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
				// Lấy danh sách hóa đơn cần xuất
		        ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoMaNV(nv.getMaNV()); // Thay thế `dsHD.getHoaDonTheoCa()` bằng cách lấy danh sách hóa đơn phù hợp
		        if (listHD == null) {
					return;
				}
				
				// Duyệt qua từng hóa đơn và kiểm tra thời gian
				for (HoaDon hd : listHD) {
					LocalDateTime thoiGianHoaDon = hd.getNgayLapHoaDon();
					if (thoiGianHoaDon.isBefore(thoiGianBatDauCa) || thoiGianHoaDon.isAfter(thoiGianKetThucCa)) {
						continue; // Bỏ qua hóa đơn ngoài khoảng thời gian ca
					}
					if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
						 continue; // Bỏ qua hóa đơn đã hoàn tiền
					}
					ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
					if (cthd != null) {
						if(cthd.getSoLuong() == 0) {
							continue;
						}
						data_TheoCa = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
					} 
					else {
						System.out.println("Chi tiết hóa đơn không tồn tại cho mã chi tiết: " + hd.getChiTiet().getMaChiTiet());
					}
				}
		        if (data_TheoCa == null || data_TheoCa.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất Excel!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				// Gọi phương thức xuất file Excel
		        try {
		            xuatExcelTheoCa(data_TheoCa,nv,thoiGianBatDauCa,thoiGianKetThucCa,lbl_doanhThuTheoCa,lbl_slvbtc,lbl_sltvtc);
		            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } catch (IOException | java.io.IOException ex) {
		            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
			}
		});
		jp_ketQuaCT.setLayout(null);
		JLabel doanhThuCTIconLabel = new JLabel(new ImageIcon(scaledDoanhThuCT));
		jp_ketQuaCT.add(doanhThuCTIconLabel);
		doanhThuCTIconLabel.setBounds(66, 41, 40, 40);

		lbl_doanhThuCT = new JLabel("");
		lbl_doanhThuCT.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_doanhThuCT.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_doanhThuCT.setBounds(111, 41, 114, 20);
		jp_ketQuaCT.add(lbl_doanhThuCT);

		JLabel lbl_titleDTCT = new JLabel("Doanh thu");
		lbl_titleDTCT.setBackground(SystemColor.windowBorder);
		lbl_titleDTCT.setForeground(new Color(105, 105, 105));
		lbl_titleDTCT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleDTCT.setBounds(134, 65, 71, 13);
		jp_ketQuaCT.add(lbl_titleDTCT);

		//Icon số lượng trả vé chuyến tàu
		ImageIcon sltvctIcon = new ImageIcon(getClass().getResource("/img/money-back_1.png"));
		Image scaledSLTVCT = sltvctIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQuaCT.setLayout(null);
		JLabel sltvctIconLabel = new JLabel(new ImageIcon(scaledSLTVCT));
		jp_ketQuaCT.add(sltvctIconLabel);
		sltvctIconLabel.setBounds(290, 41, 40, 40);

		lbl_sltvct = new JLabel("");
		lbl_sltvct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_sltvct.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sltvct.setBounds(340, 41, 114, 20);
		jp_ketQuaCT.add(lbl_sltvct);

		JLabel lbl_titleSLTVCT = new JLabel("Số lượng trả vé");
		lbl_titleSLTVCT.setBackground(SystemColor.windowBorder);
		lbl_titleSLTVCT.setForeground(new Color(105, 105, 105));
		lbl_titleSLTVCT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleSLTVCT.setBounds(351, 61, 103, 20);
		jp_ketQuaCT.add(lbl_titleSLTVCT);

		//Icon số lượng vé bán chuyến tàu
		ImageIcon slvbctIcon = new ImageIcon(getClass().getResource("/img/loan_1.png"));
		Image scaledSLVBCT = slvbctIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Thay đổi kích thước logo
		jp_ketQuaCT.setLayout(null);
		JLabel slvbctIconLabel = new JLabel(new ImageIcon(scaledSLVBCT));
		jp_ketQuaCT.add(slvbctIconLabel);
		slvbctIconLabel.setBounds(538, 41, 40, 40);

		lbl_slvbct = new JLabel("");
		lbl_slvbct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_slvbct.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_slvbct.setBounds(588, 41, 114, 20);
		jp_ketQuaCT.add(lbl_slvbct);

		JLabel lbl_titleSLVBCT = new JLabel("Số lượng vé bán");
		lbl_titleSLVBCT.setBackground(SystemColor.windowBorder);
		lbl_titleSLVBCT.setForeground(new Color(105, 105, 105));
		lbl_titleSLVBCT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_titleSLVBCT.setBounds(598, 61, 114, 20);
		jp_ketQuaCT.add(lbl_titleSLVBCT);
		
		btn_XuatFile_TheoChuyenTau = new RoundedButton("Xuất File", 15);
		btn_XuatFile_TheoChuyenTau.setBackground(new Color(51, 102, 153));
		btn_XuatFile_TheoChuyenTau.setForeground(Color.WHITE);
		btn_XuatFile_TheoChuyenTau.setBounds(822, 10, 96, 33);
		jp_ketQuaCT.add(btn_XuatFile_TheoChuyenTau);
		btn_XuatFile_TheoChuyenTau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_XuatFile_TheoChuyenTau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateChooser_TKCT_batDau.getDate() == null || dateChooser_TKCT_ketThuc.getDate() == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu và ngày kết thúc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				String formattedNgayBatDau = sqlDateFormat.format(dateChooser_TKCT_batDau.getDate());
				String formattedNgayKetThuc = sqlDateFormat.format(dateChooser_TKCT_ketThuc.getDate());
				NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
				// Lấy danh sách hóa đơn cần xuất
		        ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoNgayLapHD(formattedNgayBatDau, formattedNgayKetThuc);
		        ArrayList<Ve> data_TheoChuyenTau = new ArrayList<Ve>();
		        if (listHD == null) {
					return;
				}
				// Duyệt qua từng hóa đơn và kiểm tra thời gian
				for (HoaDon hd : listHD) {
					if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
						 continue; // Bỏ qua hóa đơn đã hoàn tiền
					}
					ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
					if(cthd.getSoLuong() == 0) {
						continue;
					}
					if (cthd != null) {
						if(cthd.getSoLuong() == 0) {
							continue;
						}
						ArrayList<Ve> dsVeTheoChiTiet = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
				        if (dsVeTheoChiTiet != null) {
				            data_TheoChuyenTau.addAll(dsVeTheoChiTiet);
				        }
					}
				}
		        if (data_TheoChuyenTau == null || data_TheoChuyenTau.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất Excel!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				// Gọi phương thức xuất file Excel
		        try {
		            xuatExcelChuyenTau(data_TheoChuyenTau,nv,lbl_doanhThuCT,lbl_slvbct,lbl_sltvct);
		            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } catch (IOException | java.io.IOException ex) {
		            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
			}
		});

		//JPane chứa thống kê theo chuyến tàu
		jp_thongKeCT = new JPanel();
		jp_thongKeCT.setBackground(SystemColor.controlHighlight);
		jp_thongKeCT.setBounds(28, 133, 1311, 308);
		jp_tkct.add(jp_thongKeCT);
		
		btnXem_TKDT.addActionListener(this);
		btnXem_TKCT.addActionListener(this);
		// Thiết lập màu nền cho tab hiện tại
		//        tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		//        tabbedPane.setBackgroundAt(1, Color.LIGHT_GRAY);
		//        tabbedPane.setBackgroundAt(2, Color.LIGHT_GRAY);
		updateKetQuaThongKeTheoCa(trangChu);
		kiemTraQuyen(trangChu);
		
	}

	//Chọn 1 radio để thực hiện
	@Override
	public void actionPerformed(ActionEvent e) {
		// Kiểm tra nút radio nào được chọn
		Object o = e.getSource();
		if (o.equals(btnXem_TKDT)) {
			updateKetQuaThongKeDoanhThu();
			createJFreeChartThongKeDoanhThu();
		}
		if(o.equals(btnXem_TKCT)) {
			updateKetQuaThongKeChuyenTau();
			createJFreeChartThongKeChuyenTau();
		}
	}
	//Hàm truy vấn thông tin gán vào label của thống kê theo ca
	public void updateKetQuaThongKeTheoCa(TrangChu_GUI trangChu) {
		dsTK.reset();
		dsNV.reset(); 
		dsHD.reset();
		dsVe.reset();
		dsCTHD.reset();
		dsCa.reset();
		float doanhThu = 0;
		int slvb = 0;
		int sltv = 0;
		Map<String, Float> doanhThuTheoKhuyenMai = new HashMap<>();

		// Lấy thông tin nhân viên theo tên
		NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
		if (nv == null) {
			return;
		}
		//////////////////////////////Kiểm tra VaoCa và ketCa có null hay không///////////////
		LocalDateTime thoiGianBatDauCa;
		if (trangChu.vaoCa != null) {
		    thoiGianBatDauCa = trangChu.vaoCa; // Gán đúng giá trị của `trangChu.vaoCa`
		} else {
		    thoiGianBatDauCa = LocalDateTime.now();
		}
		LocalDateTime thoiGianKetThucCa;
		if (trangChu.ketCa != null) {
		    thoiGianKetThucCa = trangChu.ketCa;
		} else {
		    thoiGianKetThucCa = LocalDateTime.now();
		}
		/////////////////////////////

		// Lấy danh sách hóa đơn của nhân viên
		ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoMaNV(nv.getMaNV());
		if (listHD == null) {
			return;
		}
		
		// Duyệt qua từng hóa đơn và kiểm tra thời gian
		for (HoaDon hd : listHD) {
			LocalDateTime thoiGianHoaDon = hd.getNgayLapHoaDon();
			if (thoiGianHoaDon.isBefore(thoiGianBatDauCa) || thoiGianHoaDon.isAfter(thoiGianKetThucCa)) {
				continue; // Bỏ qua hóa đơn ngoài khoảng thời gian ca
			}
			if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
				continue; // Bỏ qua hóa đơn đã hoàn tiền
			}
			ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
			if (cthd != null) {
				if(cthd.getSoLuong() == 0) {
					continue;
				}
				ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());

				for (Ve ve : listVe) {
					slvb++;
					String khuyenMai = ve.getKhuyenMai();
					doanhThuTheoKhuyenMai.put(khuyenMai,
							doanhThuTheoKhuyenMai.getOrDefault(khuyenMai, 0f) + ve.tinhGiaVe());
					doanhThu +=ve.tinhGiaVe();
				}
			} 
		}

		// Tính số lượng vé tồn tại chưa hoàn vé
		for (HoaDon hd : listHD) {
			LocalDateTime thoiGianHoaDon = hd.getNgayLapHoaDon();
			if (hd.getDaHoanVe() && !thoiGianHoaDon.isBefore(thoiGianBatDauCa) && !thoiGianHoaDon.isAfter(thoiGianKetThucCa)) {
				ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
				if (cthd != null) {
					sltv += cthd.getSoLuong();
				}
			}
		}


		// Gán giá trị cho các label
		lbl_ca.setText(nv.getCa().getMaCa());
		lbl_doanhThuTheoCa.setText(dinhDangTienTe(doanhThu));
		lbl_slvbtc.setText(String.valueOf(slvb));
		lbl_sltvtc.setText(String.valueOf(sltv));
	}

	//Hàm truy vấn thông tin gán vào label 
	public void updateKetQuaThongKeDoanhThu() {
		dsHD.reset();
		dsVe.reset();
		dsCTHD.reset();
		float doanhThu = 0; 
		SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedNgayBatDau = sqlDateFormat.format(dateChooser_TKDT_batDau.getDate());
		String formattedNgayKetThuc = sqlDateFormat.format(dateChooser_TKDT_ketThuc.getDate());
		if (!formattedNgayBatDau.isEmpty() && !formattedNgayKetThuc.isEmpty()) {
			// Lấy danh sách hóa đơn theo khoảng thời gian
			ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoNgayLapHD(formattedNgayBatDau, formattedNgayKetThuc);
			Map<String, Float> doanhThuTheoKhuyenMai = new HashMap<>();
			int slvb=0;
			int sltv=0;
			// Duyệt qua từng hóa đơn
			for (HoaDon hd : listHD) {
				if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
					continue; // Bỏ qua hóa đơn đã hoàn tiền
				}
				ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
				// Kiểm tra cthd có null hay không
				if (cthd != null) {
					if(cthd.getSoLuong() == 0) {
						continue;
					}
					ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
					for (Ve ve : listVe) {
						slvb++;
						String khuyenMai = ve.getKhuyenMai(); // Lấy mã khuyến mãi của vé
						// Cộng doanh thu vào khuyến mãi tương ứng
						doanhThuTheoKhuyenMai.put(khuyenMai, 
								doanhThuTheoKhuyenMai.getOrDefault(khuyenMai, 0f) +ve.tinhGiaVe());
						doanhThu +=ve.tinhGiaVe();
					}
				} 
			}
			for(HoaDon hd: listHD) {
				if(hd.getDaHoanVe()) {
					ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
					sltv += cthd.getSoLuong();
				}
			}

			lbl_doanhThu.setText(dinhDangTienTe(doanhThu));
			lbl_slvb.setText(String.valueOf(slvb));
			lbl_sltv.setText(String.valueOf(sltv));
		}else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu và ngày kết thúc.");
		} 
	}

	//Hàm truy vấn thông tin gán vào JLabel của thống kê chuyến tàu
	public void updateKetQuaThongKeChuyenTau() {
		dsHD.reset();
		dsVe.reset();
		dsCTHD.reset();
		float doanhThu = 0; 
		SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedNgayBatDau = sqlDateFormat.format(dateChooser_TKCT_batDau.getDate());
		String formattedNgayKetThuc = sqlDateFormat.format(dateChooser_TKCT_ketThuc.getDate());
		if (!formattedNgayBatDau.isEmpty() && !formattedNgayKetThuc.isEmpty()) {
			// Lấy danh sách hóa đơn theo khoảng thời gian
			ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoNgayLapHD(formattedNgayBatDau, formattedNgayKetThuc);
			Map<String, Float> doanhThuTheoChuyenTau = new HashMap<>();
			int slvb=0;
			int sltv=0;
			// Duyệt qua từng hóa đơn
			for (HoaDon hd : listHD) {
				if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
					continue; // Bỏ qua hóa đơn đã hoàn tiền
				}
				ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
				// Kiểm tra cthd có null hay không
				if (cthd != null) {
					if(cthd.getSoLuong() == 0) {
						continue;
					}
					ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
					Set<String> processedChuyenTau = new HashSet<>();

					for (Ve ve : listVe) {
						slvb++;
						String chuyenTau = ve.getChuyenTau().getMaTau(); // Lấy mã chuyến tàu
						doanhThu +=ve.tinhGiaVe();
						// Kiểm tra xem mã chuyến tàu đã được xử lý chưa
						if (!processedChuyenTau.contains(chuyenTau)) {
							// Cộng doanh thu vào chuyến tàu tương ứng
							doanhThuTheoChuyenTau.put(chuyenTau, doanhThuTheoChuyenTau.getOrDefault(chuyenTau, 0f) + doanhThu);
							processedChuyenTau.add(chuyenTau); // Đánh dấu là đã xử lý
						}
					}
				}
			}
			for(HoaDon hd: listHD) {
				if(hd.getDaHoanVe()) {
					ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
					sltv += cthd.getSoLuong();
				}
			}

			lbl_doanhThuCT.setText(dinhDangTienTe(doanhThu));
			lbl_slvbct.setText(String.valueOf(slvb));
			lbl_sltvct.setText(String.valueOf(sltv));
		}else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu và ngày kết thúc.");
		} 
	}
	//Hàm tạo và thêm jfreechart vào jp_thongKe(Thống kê doanh thu)
	public void createJFreeChartThongKeDoanhThu() {
		//JFreeChat thống kê theo khuyến mãi
		//Khởi tạo dữ liệu
		DefaultCategoryDataset dataset = createDatasetDoanhThu();
		// Create chart
		JFreeChart chart = ChartFactory.createBarChart(
				"Thống kê doanh thu",
				"Đối tượng bán",
				"Doanh thu",
				dataset
				);

		// Giảm kích cỡ chữ cho tiêu đề
		chart.getTitle().setFont(new Font("Arial", Font.PLAIN, 16));
		// Tạo ChartPanel và thiết lập kích thước
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0,0, 1311, 308);
		jp_thongKe.setLayout(null);
		jp_thongKe.removeAll(); // Đảm bảo xóa các thành phần cũ nếu có
		jp_thongKe.revalidate();
	    jp_thongKe.repaint();
		// Thêm ChartPanel vào jp_thongKe
		jp_thongKe.add(chartPanel);
	}


	//Hàm tạo và thêm jfreechart vào jp_thongKe(Thống kê chuyến tàu)
	public void createJFreeChartThongKeChuyenTau() {

		//JFreeChat thống kê theo chuyến tàu
		//Khởi tạo dữ liệu
		DefaultCategoryDataset datasetCT = createDatasetChuyenTau();  // dsCTHD là danh sách chi tiết hóa đơn
		JFreeChart chartCT = ChartFactory.createBarChart(
				"Thống kê chuyến tàu",
				"Chuyến tàu",
				"Doanh thu",
				datasetCT
				);

		// Giảm kích cỡ chữ cho tiêu đề
		chartCT.getTitle().setFont(new Font("Arial", Font.PLAIN, 16));
		// Tạo ChartPanel và thiết lập kích thước
		chartPanelCT = new ChartPanel(chartCT);
		chartPanelCT.setBounds(0, 0, 1313, 308);
		jp_thongKeCT.setLayout(null);
		jp_thongKeCT.removeAll(); // Đảm bảo xóa các thành phần cũ nếu có
		jp_thongKeCT.revalidate();
	    jp_thongKeCT.repaint();
		// Thêm ChartPanel vào jp_thongKe
		jp_thongKeCT.add(chartPanelCT);

		Color[] colors = {Color.ORANGE}; // Thêm màu theo nhu cầu
		// Tùy chỉnh màu cho các cột
		CategoryPlot plot = chartCT.getCategoryPlot(); // Lấy CategoryPlot
		BarRenderer renderer = (BarRenderer) plot.getRenderer(); // Lấy renderer
		// Thiết lập màu cho tất cả các series
		for (int i = 0; i < datasetCT.getRowCount(); i++) {
			if (i < colors.length) { // Đảm bảo không vượt quá kích thước mảng màu
				renderer.setSeriesPaint(i, colors[i]);
			} else {
				renderer.setSeriesPaint(i, Color.GRAY); // Màu mặc định cho các series không được định nghĩa
			}
		}
	}

	//Hàm truy vấn dữ liệu thống kê theo ca hiện tại 
	private DefaultCategoryDataset createDatasetTKTheoCa(TrangChu_GUI trangChu) {
		dataset = new DefaultCategoryDataset();
		dsTK.reset();
		dsNV.reset();
		dsHD.reset();
		dsVe.reset();
		dsCTHD.reset();
		dsCa.reset();

		Map<String, Float> doanhThuTheoKhuyenMai = new HashMap<>();

		// Lấy thông tin nhân viên theo tên
		NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
		if (nv == null) {
			return dataset;
		}

		//////////////////////////////Kiểm tra VaoCa và ketCa có null hay không///////////////
		LocalDateTime thoiGianBatDauCa;
		if (trangChu.vaoCa != null) {
			thoiGianBatDauCa = trangChu.vaoCa; // Gán đúng giá trị của `trangChu.vaoCa`
		} else {
			thoiGianBatDauCa = LocalDateTime.now();
		}

		LocalDateTime thoiGianKetThucCa;
		if (trangChu.ketCa != null) {
			thoiGianKetThucCa = trangChu.ketCa;
		} else {
			thoiGianKetThucCa = LocalDateTime.now();
		}
		////////////////////////////////////////////
		// Lấy danh sách hóa đơn của nhân viên
		ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoMaNV(nv.getMaNV());
		if (listHD == null) {
			return dataset ;
		}

		// Duyệt qua từng hóa đơn và kiểm tra thời gian
		for (HoaDon hd : listHD) {
			LocalDateTime thoiGianHoaDon = hd.getNgayLapHoaDon();
			if (thoiGianHoaDon.isBefore(thoiGianBatDauCa) || thoiGianHoaDon.isAfter(thoiGianKetThucCa)) {
				continue; // Bỏ qua hóa đơn ngoài khoảng thời gian ca
			}
			if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
				 continue; // Bỏ qua hóa đơn đã hoàn tiền
			}
			ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
			if (cthd != null) {
				if(cthd.getSoLuong() == 0) {
					continue;
				}
				ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());

				for (Ve ve : listVe) {
					String khuyenMai = ve.getKhuyenMai();
					doanhThuTheoKhuyenMai.put(khuyenMai,
							doanhThuTheoKhuyenMai.getOrDefault(khuyenMai, 0f) + ve.tinhGiaVe());
				}
			}
		}
		// Duyệt qua từng mục trong doanh thu theo khuyến mãi
		for (Map.Entry<String, Float> entry : doanhThuTheoKhuyenMai.entrySet()) {
			String khuyenMai = entry.getKey();
			Float doanhThu = entry.getValue();

			// Thêm dữ liệu vào dataset
			dataset.addValue(doanhThu, "Doanh Thu", khuyenMai);
		}
		if (doanhThuTheoKhuyenMai.isEmpty()) {
		    dataset.addValue(0, "Doanh Thu", "Không có hóa đơn");
		}
		return dataset;
	}
	
	//Hàm truy vấn dữ liệu thống kê doanh thu theo hạng của thống kê ca
	private DefaultCategoryDataset createDatasetTKHangTheoCa(TrangChu_GUI trangChu) {
		dataset = new DefaultCategoryDataset();
		dsTK.reset();
		dsNV.reset();
		dsHD.reset();
		dsVe.reset();
		dsCTHD.reset();
		dsCa.reset();

		Map<String, Float> doanhThuTheoHang = new HashMap<>();

		// Lấy thông tin nhân viên theo tên
		NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
		if (nv == null) {
			return dataset;
		}

		//////////////////////////////Kiểm tra VaoCa và ketCa có null hay không///////////////
		LocalDateTime thoiGianBatDauCa;
		if (trangChu.vaoCa != null) {
			thoiGianBatDauCa = trangChu.vaoCa; // Gán đúng giá trị của `trangChu.vaoCa`
		} else {
			thoiGianBatDauCa = LocalDateTime.now();
		}

		LocalDateTime thoiGianKetThucCa;
		if (trangChu.ketCa != null) {
			thoiGianKetThucCa = trangChu.ketCa;
		} else {
			thoiGianKetThucCa = LocalDateTime.now();
		}
		////////////////////////////////////////////

		// Lấy danh sách hóa đơn của nhân viên
		ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoMaNV(nv.getMaNV());
		if (listHD == null) {
			return dataset ;
		}

		// Duyệt qua từng hóa đơn và kiểm tra thời gian
		for (HoaDon hd : listHD) {
			LocalDateTime thoiGianHoaDon = hd.getNgayLapHoaDon();
			if (thoiGianHoaDon.isBefore(thoiGianBatDauCa) || thoiGianHoaDon.isAfter(thoiGianKetThucCa)) {
				continue; // Bỏ qua hóa đơn ngoài khoảng thời gian ca
			}
			if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
				 continue; // Bỏ qua hóa đơn đã hoàn tiền
			}
			ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
			if (cthd != null) {
				if(cthd.getSoLuong() == 0) {
					continue;
				}
				ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());

				for (Ve ve : listVe) {
					String hang = ve.getHang();
					// Sử dụng một Set để theo dõi mã chuyến tàu đã được xử lý
					Set<String> processedHang = new HashSet<>();
					if (!processedHang.contains(hang)) {
						// Cộng doanh thu vào chuyến tàu tương ứng
						doanhThuTheoHang.put(hang, doanhThuTheoHang.getOrDefault(hang, 0f) + ve.tinhGiaVe());
						processedHang.add(hang); // Đánh dấu là đã xử lý
					}
				}
			}
		}
		// Duyệt qua từng mục trong doanh thu theo khuyến mãi
		for (Map.Entry<String, Float> entry : doanhThuTheoHang.entrySet()) {
			String hang = entry.getKey();
			Float doanhThu = entry.getValue();

			// Thêm dữ liệu vào dataset
			dataset.addValue(doanhThu, "Doanh Thu", hang);
		}
		if (doanhThuTheoHang.isEmpty()) {
		    dataset.addValue(0, "Doanh Thu", "");
		}
		return dataset;
	}
	
	//Hàm truy vấn dữ liệu thống kê PieChart số lượng bán 
	private DefaultPieDataset<String> createDatasetPieChart(TrangChu_GUI trangChu) {
	    DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
	    dsTK.reset();
	    dsNV.reset();
	    dsHD.reset();
	    dsVe.reset();
	    dsCTHD.reset();
	    dsCa.reset();

	    Map<String, Float> doanhThuTheoHang = new HashMap<>();

	    // Lấy thông tin nhân viên theo tên
	    NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
	    if (nv == null) {
	        return dataset;
	    }

	    //////////////////////////////Kiểm tra VaoCa và ketCa có null hay không///////////////
	    LocalDateTime thoiGianBatDauCa;
	    if (trangChu.vaoCa != null) {
	    	thoiGianBatDauCa = trangChu.vaoCa; // Gán đúng giá trị của `trangChu.vaoCa`
	    } else {
	    	thoiGianBatDauCa = LocalDateTime.now();
	    }

	    LocalDateTime thoiGianKetThucCa;
	    if (trangChu.ketCa != null) {
	    	thoiGianKetThucCa = trangChu.ketCa;
	    } else {
	    	thoiGianKetThucCa = LocalDateTime.now();
	    }
	    ////////////////////////////////////////////

	    // Lấy danh sách hóa đơn của nhân viên
	    ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoMaNV(nv.getMaNV());
	    if (listHD == null) {
	        return dataset;
	    }

	    // Duyệt qua từng hóa đơn và kiểm tra thời gian
	    for (HoaDon hd : listHD) {
	    	LocalDateTime thoiGianHoaDon = hd.getNgayLapHoaDon();
	        if (thoiGianHoaDon.isBefore(thoiGianBatDauCa) || thoiGianHoaDon.isAfter(thoiGianKetThucCa)) {
	            continue; // Bỏ qua hóa đơn ngoài khoảng thời gian ca
	        }
	        if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
				 continue; // Bỏ qua hóa đơn đã hoàn tiền
			}
	        ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
	        if (cthd != null) {
	        	if(cthd.getSoLuong() == 0) {
					continue;
				}
	            ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
	            Set<String> processedHang = new HashSet<>(); // Set để theo dõi mã chuyến tàu đã được xử lý

	            for (Ve ve : listVe) {
	                String hang = ve.getHang();
	                if (!processedHang.contains(hang)) {
	                    // Cộng doanh thu vào chuyến tàu tương ứng
	                    doanhThuTheoHang.put(hang, doanhThuTheoHang.getOrDefault(hang, 0f) + ve.tinhGiaVe());
	                    processedHang.add(hang); // Đánh dấu là đã xử lý
	                }
	            }
	        }
	    }

	    // Duyệt qua từng mục trong doanh thu theo khuyến mãi
	    for (Map.Entry<String, Float> entry : doanhThuTheoHang.entrySet()) {
	        String hang = entry.getKey();
	        Float doanhThu = entry.getValue();
	        // Thêm dữ liệu vào dataset
	        dataset.setValue(hang, doanhThu); // Sử dụng setValue cho PieDataset
	    }

	    if (doanhThuTheoHang.isEmpty()) {
	        dataset.setValue("Rỗng", 0); // Thêm giá trị cho trường hợp không có dữ liệu
	    }

	    return dataset;
	}

	//Hàm truy vấn dữ liệu thống kê doanh thu
	private DefaultCategoryDataset createDatasetDoanhThu() {
		dataset = new DefaultCategoryDataset();
		SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedNgayBatDau = sqlDateFormat.format(dateChooser_TKDT_batDau.getDate());
		String formattedNgayKetThuc = sqlDateFormat.format(dateChooser_TKDT_ketThuc.getDate());
		dsHD.reset();
		dsVe.reset();
		dsCTHD.reset();
		Map<String, Float> doanhThuTheoKhuyenMai = new HashMap<>();
		ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoNgayLapHD(formattedNgayBatDau, formattedNgayKetThuc);
		// Duyệt qua từng hóa đơn
		for (HoaDon hd : listHD) {
			if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
				 continue; // Bỏ qua hóa đơn đã hoàn tiền
			}
			ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());
			// Kiểm tra cthd có null hay không
			if (cthd != null) {
				if(cthd.getSoLuong() == 0) {
					continue;
				}
				ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
				for (Ve ve : listVe) {
					String khuyenMai = ve.getKhuyenMai(); // Lấy mã khuyến mãi của vé
					// Cộng doanh thu vào khuyến mãi tương ứng
					doanhThuTheoKhuyenMai.put(khuyenMai, 
							doanhThuTheoKhuyenMai.getOrDefault(khuyenMai, 0f) +	ve.tinhGiaVe());
				} // Mảng chứa khuyến mãi và doanh thu của mỗi lại
			}
		}
		// Duyệt qua từng mục trong doanh thu theo khuyến mãi
		for (Map.Entry<String, Float> entry : doanhThuTheoKhuyenMai.entrySet()) {
			String khuyenMai = entry.getKey();
			Float doanhThu = entry.getValue();

			// Thêm dữ liệu vào dataset
			dataset.addValue(doanhThu, "Doanh Thu", khuyenMai);
		}

		return dataset;
	}


	//Hàm truy vấn dữ liệu thống kê chuyến tàu
	private DefaultCategoryDataset createDatasetChuyenTau() {
		dataset = new DefaultCategoryDataset();
		dsHD.reset();
		dsVe.reset();
		dsCTHD.reset();
		SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedNgayBatDau = sqlDateFormat.format(dateChooser_TKCT_batDau.getDate());
		String formattedNgayKetThuc = sqlDateFormat.format(dateChooser_TKCT_ketThuc.getDate());
	    
		Map<String, Float> doanhThuTheoChuyenTau = new HashMap<>();
		ArrayList<HoaDon> listHD = dsHD.getHoaDonTheoNgayLapHD(formattedNgayBatDau, formattedNgayKetThuc);

		// Duyệt qua từng hóa đơn
		for (HoaDon hd : listHD) {
			if(hd.getDaHoanTien() && hd.getDaHoanVe()) {
				 continue; // Bỏ qua hóa đơn đã hoàn tiền
			}
			ChiTietHoaDon cthd = dsCTHD.getCTHDTheoMaChiTiet(hd.getChiTiet().getMaChiTiet());

			// Kiểm tra cthd có null hay không
			if (cthd != null) {
				if(cthd.getSoLuong() == 0) {
					continue;
				}
				ArrayList<Ve> listVe = dsVe.getDsVeTheoMaChiTiet(cthd.getMaChiTiet());
				for (Ve ve : listVe) {
					String chuyenTau = ve.getChuyenTau().getMaTau(); // Lấy mã chuyến tàu
					doanhThuTheoChuyenTau.put(chuyenTau, doanhThuTheoChuyenTau.getOrDefault(chuyenTau, 0f) + ve.tinhGiaVe());
				}
			}
		}

		// Duyệt qua từng mục trong doanh thu theo chuyến tàu
		for (Map.Entry<String, Float> entry : doanhThuTheoChuyenTau.entrySet()) {
			String chuyenTau = entry.getKey();
			Float doanhThu = entry.getValue();
			// Thêm dữ liệu vào dataset
			dataset.addValue(doanhThu, "Doanh Thu", chuyenTau);
		}

		return dataset;
	}

	
	//Hàm định dạng tiền việt
	public String dinhDangTienTe(double soTien) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return formatter.format(soTien);
	}

	//Hàm kiểm tra quyền Nhân viên
	public void kiemTraQuyen(TrangChu_GUI trangChu) {
	    dsTK.reset();
	    dsNV.reset();
	    NhanVien nv = dsNV.getNhanVienTheoTenNV(trangChu.lbl_ThongTinNV.getText());
	    
	    // Kiểm tra nếu nhân viên tồn tại
	    if (nv != null) {
	        TaiKhoan tk = dsTK.getTaiKhoanTheoMaNV(nv.getMaNV());

	        // Kiểm tra nếu tài khoản tồn tại
	        if (tk != null) {
	            if (tk.getPhanQuyen() == 2) {
	                tabbedPane.setSelectedIndex(0); // Chọn tab 0 nếu quyền là 2
	                tabbedPane.setEnabledAt(1, false); // Vô hiệu hóa tab 1
	                tabbedPane.setEnabledAt(2, false); // Vô hiệu hóa tab 2
	            } else {
	            	tabbedPane.setSelectedIndex(0);
	            	tabbedPane.setEnabledAt(1, true); // Bật tab 1
	                tabbedPane.setEnabledAt(2, true); // Bật tab 2
	            }
	        }
	    } 
	}
	
	/////////////////XUẤT FILE EXCEL THỐNG KÊ THEO CA//////////////////////
	public void xuatExcelTheoCa(ArrayList<Ve> data, NhanVien nv, LocalDateTime thoiGianBatDauCa,LocalDateTime thoiGianKetThucCa,JLabel label1,JLabel label2, JLabel label3) throws FileNotFoundException, java.io.IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("VÉ TÀU");

	    // ===== Tạo bảng thông tin nhân viên =====
	    String[] headerData_ThongTinNV = {"Mã nhân viên", "Họ Tên", "Ca làm", "Thời gian vào ca", "Thời gian kết thúc ca"};
	    CellStyle headerStyle_ThongTinNV = workbook.createCellStyle();
	    org.apache.poi.ss.usermodel.Font headerFont_ThongTinNV = workbook.createFont();
	    headerFont_ThongTinNV.setBold(true);
	    headerFont_ThongTinNV.setColor(IndexedColors.WHITE.getIndex());
	    headerStyle_ThongTinNV.setFont(headerFont_ThongTinNV);
	    headerStyle_ThongTinNV.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
	    headerStyle_ThongTinNV.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // Tiêu đề bảng nhân viên
	    Row headerRow_ThongTinNV = sheet.createRow(0);
	    for (int i = 0; i < headerData_ThongTinNV.length; i++) {
	        Cell cell = headerRow_ThongTinNV.createCell(i);
	        cell.setCellValue(headerData_ThongTinNV[i]);
	        cell.setCellStyle(headerStyle_ThongTinNV);
	    }

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    // Dữ liệu bảng nhân viên
	    Row dataRow = sheet.createRow(1);
	    dataRow.createCell(0).setCellValue(nv.getMaNV());
	    dataRow.createCell(1).setCellValue(nv.getTenNV());
	    dataRow.createCell(2).setCellValue(nv.getCa().getMaCa());
	    dataRow.createCell(3).setCellValue(thoiGianBatDauCa.format(formatter));
	    dataRow.createCell(4).setCellValue(thoiGianKetThucCa.format(formatter));

	    // Tự động điều chỉnh kích thước cột
	    for (int i = 0; i < headerData_ThongTinNV.length; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    // ===== Tạo bảng danh sách vé =====
	    int startRow = 3; // Dòng bắt đầu cho bảng vé (sau bảng nhân viên)
	    String[] headerData = {"STT", "Tên khách hàng", "Ga đi", "Ga đến", "Hạng", "Khuyến mãi", "Toa", "Ghế", "Mã vé", "Mã chuyến tàu", "Ngày đi", "Giờ đi", "Trạng thái", "Chi tiết"};
	    CellStyle headerStyle = workbook.createCellStyle();
	    org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.WHITE.getIndex());
	    headerStyle.setFont(headerFont);
	    headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
	    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    Row headerRow = sheet.createRow(startRow);
	    for (int i = 0; i < headerData.length; i++) {
	        Cell cell = headerRow.createCell(i);
	        cell.setCellValue(headerData[i]);
	        cell.setCellStyle(headerStyle);
	    }

	    // Dữ liệu bảng vé
	    int stt = 1;
	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    for (int i = 0; i < data.size(); i++) {
	        Ve ve = data.get(i);
	        Row row = sheet.createRow(startRow + 1 + i); // Dòng tiếp theo
	        KhachHang kh = dsKH.getKhachHangTheoMaKH(ve.getKhachHang().getMaKH());
	        String[] cellData = {
	            String.valueOf(stt++),
	            kh.getTenKH(),
	            ve.getGaDi().getTenGa(),
	            ve.getGaDen().getTenGa(),
	            ve.getHang(),
	            ve.getKhuyenMai(),
	            ve.getToa().getMaToa(),
	            String.valueOf(ve.getSoGhe().getSoGhe()),
	            ve.getMaVe(),
	            ve.getChuyenTau().getMaTau(),
	            ve.getNgayDi().format(dateFormatter),
	            ve.getGioDi().format(timeFormatter),
	            ve.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành",
	            ve.getChiTiet().getMaChiTiet()
	        };

	        for (int j = 0; j < cellData.length; j++) {
	            row.createCell(j).setCellValue(cellData[j]);
	        }
	    }

	    // Tự động điều chỉnh kích thước cột
	    for (int i = 0; i < headerData.length; i++) {
	        sheet.autoSizeColumn(i);
	    }
	    
	    ////========= Tổng hợp thống kê==========///
	    int tongHopRow = sheet.getLastRowNum() + 2; // Bắt đầu từ dòng trống sau cùng
	    String[] headerData_tongHop = {"Tổng doanh thu","Số lượng vé bán","Số lượng vé trả"};
	    CellStyle headerStyle_tongHop = workbook.createCellStyle();
	    org.apache.poi.ss.usermodel.Font headerFont_tongHop = workbook.createFont();
	    headerFont_tongHop.setBold(true);
	    headerFont_tongHop.setColor(IndexedColors.WHITE.getIndex());
	    headerStyle_tongHop.setFont(headerFont_tongHop);
	    headerStyle_tongHop.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
	    headerStyle_tongHop.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // Tiêu đề bảng nhân viên
	    Row headerRow_tongHop = sheet.createRow(tongHopRow);
	    for (int i = 0; i < headerData_tongHop.length; i++) {
	        Cell cell = headerRow_tongHop.createCell(i);
	        cell.setCellValue(headerData_tongHop[i]);
	        cell.setCellStyle(headerStyle_tongHop);
	    }
	    // Dữ liệu bảng nhân viên
	    Row dataRow_tongHop = sheet.createRow(tongHopRow+1);
	    dataRow_tongHop.createCell(0).setCellValue(label1.getText().toString());
	    dataRow_tongHop.createCell(1).setCellValue(label2.getText().toString());
	    dataRow_tongHop.createCell(2).setCellValue(label3.getText().toString());
	    // Tự động điều chỉnh kích thước cột
	    for (int i = 0; i < headerData_tongHop.length; i++) {
	        sheet.autoSizeColumn(i);
	    }
	    
	    
	    // Ghi và mở file
	    String fileName = "excel/" + UUID.randomUUID().toString() + ".xlsx";
	    try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
	        workbook.write(fileOut);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    try {
	        File file = new File(fileName);
	        if (file.exists() && Desktop.isDesktopSupported()) {
	            Desktop.getDesktop().open(file);
	        }
	    } catch (IOException e) {
	        System.err.println("Lỗi khi mở file Excel:");
	        e.printStackTrace();
	    }
	}	

	/////////////////XUẤT FILE EXCEL THỐNG KÊ THEO DOANH THU//////////////////////
	public void xuatExcelDoanhThu(ArrayList<Ve> data, NhanVien nv,JLabel label1,JLabel label2, JLabel label3) throws FileNotFoundException, java.io.IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Thống kê doanh thu");
		Set<String> maVeTrung = new HashSet<>();
		// ===== Tạo bảng thông tin nhân viên =====
		String[] headerData_ThongTinNV = {"Mã nhân viên", "Họ Tên", "Thời gian bắt đầu", "Thời gian kết thúc"};
		CellStyle headerStyle_ThongTinNV = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font headerFont_ThongTinNV = workbook.createFont();
		headerFont_ThongTinNV.setBold(true);
		headerFont_ThongTinNV.setColor(IndexedColors.WHITE.getIndex());
		headerStyle_ThongTinNV.setFont(headerFont_ThongTinNV);
		headerStyle_ThongTinNV.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle_ThongTinNV.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Tiêu đề bảng nhân viên
		Row headerRow_ThongTinNV = sheet.createRow(0);
		for (int i = 0; i < headerData_ThongTinNV.length; i++) {
			Cell cell = headerRow_ThongTinNV.createCell(i);
			cell.setCellValue(headerData_ThongTinNV[i]);
			cell.setCellStyle(headerStyle_ThongTinNV);
		}

		// Dữ liệu bảng nhân viên
		Row dataRow = sheet.createRow(1);
		dataRow.createCell(0).setCellValue(nv.getMaNV());
		dataRow.createCell(1).setCellValue(nv.getTenNV());
		dataRow.createCell(2).setCellValue(tkdt_ngayBatDau);
		dataRow.createCell(3).setCellValue(tkdt_ngayKetThuc);

		// Tự động điều chỉnh kích thước cột
		for (int i = 0; i < headerData_ThongTinNV.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// ===== Tạo bảng danh sách vé =====
		int startRow = 3; // Dòng bắt đầu cho bảng vé (sau bảng nhân viên)
		String[] headerData = {"STT", "Tên khách hàng", "Ga đi", "Ga đến", "Hạng", "Khuyến mãi", "Toa", "Ghế", "Mã vé", "Mã chuyến tàu", "Ngày đi", "Giờ đi", "Trạng thái", "Chi tiết"};
		CellStyle headerStyle = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(headerFont);
		headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Row headerRow = sheet.createRow(startRow);
		for (int i = 0; i < headerData.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headerData[i]);
			cell.setCellStyle(headerStyle);
		}

		// Dữ liệu bảng vé
		int stt = 1;
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (int i = 0; i < data.size(); i++) {
			Ve ve = data.get(i);
			// Kiểm tra nếu mã vé đã tồn tại trong Set
		    if (maVeTrung.contains(ve.getMaVe())) {
		        continue; // Bỏ qua nếu trùng mã vé
		    }
		    
		    // Thêm mã vé vào Set
		    maVeTrung.add(ve.getMaVe());
		    
			Row row = sheet.createRow(startRow + 1 + i); // Dòng tiếp theo
			KhachHang kh = dsKH.getKhachHangTheoMaKH(ve.getKhachHang().getMaKH());
			String[] cellData = {
					String.valueOf(stt++),
					kh.getTenKH(),
					ve.getGaDi().getTenGa(),
					ve.getGaDen().getTenGa(),
					ve.getHang(),
					ve.getKhuyenMai(),
					ve.getToa().getMaToa(),
					String.valueOf(ve.getSoGhe().getSoGhe()),
					ve.getMaVe(),
					ve.getChuyenTau().getMaTau(),
					ve.getNgayDi().format(dateFormatter),
					ve.getGioDi().format(timeFormatter),
					ve.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành",
					ve.getChiTiet().getMaChiTiet()
			};

			for (int j = 0; j < cellData.length; j++) {
				row.createCell(j).setCellValue(cellData[j]);
			}
		}

		// Tự động điều chỉnh kích thước cột
		for (int i = 0; i < headerData.length; i++) {
			sheet.autoSizeColumn(i);
		}

		////========= Tổng hợp thống kê==========///
		int tongHopRow = sheet.getLastRowNum() + 2; // Bắt đầu từ dòng trống sau cùng
		String[] headerData_tongHop = {"Tổng doanh thu","Số lượng vé bán","Số lượng vé trả"};
		CellStyle headerStyle_tongHop = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font headerFont_tongHop = workbook.createFont();
		headerFont_tongHop.setBold(true);
		headerFont_tongHop.setColor(IndexedColors.WHITE.getIndex());
		headerStyle_tongHop.setFont(headerFont_tongHop);
		headerStyle_tongHop.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle_tongHop.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Tiêu đề bảng nhân viên
		Row headerRow_tongHop = sheet.createRow(tongHopRow);
		for (int i = 0; i < headerData_tongHop.length; i++) {
			Cell cell = headerRow_tongHop.createCell(i);
			cell.setCellValue(headerData_tongHop[i]);
			cell.setCellStyle(headerStyle_tongHop);
		}
		// Dữ liệu bảng nhân viên
		Row dataRow_tongHop = sheet.createRow(tongHopRow+1);
		dataRow_tongHop.createCell(0).setCellValue(label1.getText().toString());
		dataRow_tongHop.createCell(1).setCellValue(label2.getText().toString());
		dataRow_tongHop.createCell(2).setCellValue(label3.getText().toString());
		// Tự động điều chỉnh kích thước cột
		for (int i = 0; i < headerData_tongHop.length; i++) {
			sheet.autoSizeColumn(i);
		}


		// Ghi và mở file
		String fileName = "excel/" + UUID.randomUUID().toString() + ".xlsx";
		try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
			workbook.write(fileOut);
			System.out.println("File Excel đã được lưu tại: " + fileName);
		} catch (IOException e) {
			System.err.println("Lỗi khi ghi file Excel:");
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			File file = new File(fileName);
			if (file.exists() && Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		} catch (IOException e) {
			System.err.println("Lỗi khi mở file Excel:");
			e.printStackTrace();
		}
	}
	
	/////////////////XUẤT FILE EXCEL THỐNG KÊ THEO CHUYẾN TÀU//////////////////////
	public void xuatExcelChuyenTau(ArrayList<Ve> data, NhanVien nv,JLabel label1,JLabel label2, JLabel label3) throws FileNotFoundException, java.io.IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Thống kê chuyến tàu có doanh thu cao nhất");
		Set<String> maTauTrung = new HashSet<>();
		Map<String, Float> doanhThuTheoTau = new HashMap<>();
		// ===== Tạo bảng thông tin nhân viên =====
		String[] headerData_ThongTinNV = {"Mã nhân viên", "Họ Tên", "Thời gian bắt đầu", "Thời gian kết thúc"};
		CellStyle headerStyle_ThongTinNV = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font headerFont_ThongTinNV = workbook.createFont();
		headerFont_ThongTinNV.setBold(true);
		headerFont_ThongTinNV.setColor(IndexedColors.WHITE.getIndex());
		headerStyle_ThongTinNV.setFont(headerFont_ThongTinNV);
		headerStyle_ThongTinNV.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle_ThongTinNV.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Tiêu đề bảng nhân viên
		Row headerRow_ThongTinNV = sheet.createRow(0);
		for (int i = 0; i < headerData_ThongTinNV.length; i++) {
			Cell cell = headerRow_ThongTinNV.createCell(i);
			cell.setCellValue(headerData_ThongTinNV[i]);
			cell.setCellStyle(headerStyle_ThongTinNV);
		}

		// Dữ liệu bảng nhân viên
		Row dataRow = sheet.createRow(1);
		dataRow.createCell(0).setCellValue(nv.getMaNV());
		dataRow.createCell(1).setCellValue(nv.getTenNV());
		dataRow.createCell(2).setCellValue(tkct_ngayBatDau);
		dataRow.createCell(3).setCellValue(tkct_ngayKetThuc);

		// Tự động điều chỉnh kích thước cột
		for (int i = 0; i < headerData_ThongTinNV.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// ===== Tạo bảng danh sách vé =====
		int startRow = 3; // Dòng bắt đầu cho bảng vé (sau bảng nhân viên)
		String[] headerData = {"STT", "Mã tàu","Doanh thu"};
		CellStyle headerStyle = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(headerFont);
		headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Row headerRow = sheet.createRow(startRow);
		for (int i = 0; i < headerData.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headerData[i]);
			cell.setCellStyle(headerStyle);
		}
		
		// Dữ liệu bảng vé
		int stt = 1;
		for (Ve ve : data) {
		    String maTau = ve.getChuyenTau().getMaTau();
		    if (maTauTrung.contains(ve.getMaVe())) {
		        continue; // Bỏ qua nếu trùng mã vé
		    }
		    // Thêm mã vé vào Set
		    maTauTrung.add(ve.getMaVe());
		    doanhThuTheoTau.put(maTau, doanhThuTheoTau.getOrDefault(maTau, 0f) + ve.tinhGiaVe());
		}
		// Sắp xếp doanh thu giảm dần
		Map<String, Float> sortedDoanhThuTheoTau = doanhThuTheoTau.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Float>comparingByValue().reversed()) //So sánh gặp theo cặp key-value(so sánh doanh thu). reversed() Đảo ngược thứ tự sắp xếp (tăng -> giảm) 
				.collect(Collectors.toMap(
						Map.Entry::getKey,   // Lấy key từ mỗi entry (mã tàu)
						Map.Entry::getValue, // Lấy value từ mỗi entry (doanh thu)
						(e1, e2) -> e1,      // Xử lý xung đột (nếu xảy ra). Ở đây, chỉ giữ entry đầu tiên. Nếu có hai giá trị trùng key (không xảy ra trong trường hợp này), nó sẽ giữ lại giá trị đầu tiên.
						LinkedHashMap::new// Tạo một LinkedHashMap để duy trì thứ tự sắp xếp
						));
		
		//
		for (Map.Entry<String, Float> entry : sortedDoanhThuTheoTau.entrySet()) {
			String maTau = entry.getKey();
			float doanhThu = entry.getValue();

			Row row = sheet.createRow(startRow + stt);
			String[] cellData = {
					String.valueOf(stt++),
					maTau,
					String.valueOf(dinhDangTienTe(doanhThu))
			};

			for (int j = 0; j < cellData.length; j++) {
				row.createCell(j).setCellValue(cellData[j]);
			}
		}

		// Tự động điều chỉnh kích thước cột
		for (int i = 0; i < headerData.length; i++) {
			sheet.autoSizeColumn(i);
		}

		////========= Tổng hợp thống kê==========///
		int tongHopRow = sheet.getLastRowNum() + 2; // Bắt đầu từ dòng trống sau cùng
		String[] headerData_tongHop = {"Tổng doanh thu","Số lượng vé bán","Số lượng vé trả"};
		CellStyle headerStyle_tongHop = workbook.createCellStyle();
		org.apache.poi.ss.usermodel.Font headerFont_tongHop = workbook.createFont();
		headerFont_tongHop.setBold(true);
		headerFont_tongHop.setColor(IndexedColors.WHITE.getIndex());
		headerStyle_tongHop.setFont(headerFont_tongHop);
		headerStyle_tongHop.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle_tongHop.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Tiêu đề bảng nhân viên
		Row headerRow_tongHop = sheet.createRow(tongHopRow);
		for (int i = 0; i < headerData_tongHop.length; i++) {
			Cell cell = headerRow_tongHop.createCell(i);
			cell.setCellValue(headerData_tongHop[i]);
			cell.setCellStyle(headerStyle_tongHop);
		}
		// Dữ liệu bảng nhân viên
		Row dataRow_tongHop = sheet.createRow(tongHopRow+1);
		dataRow_tongHop.createCell(0).setCellValue(label1.getText().toString());
		dataRow_tongHop.createCell(1).setCellValue(label2.getText().toString());
		dataRow_tongHop.createCell(2).setCellValue(label3.getText().toString());
		// Tự động điều chỉnh kích thước cột
		for (int i = 0; i < headerData_tongHop.length; i++) {
			sheet.autoSizeColumn(i);
		}


		// Ghi và mở file
		String fileName = "excel/" + UUID.randomUUID().toString() + ".xlsx";
		try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
			workbook.write(fileOut);
			System.out.println("File Excel đã được lưu tại: " + fileName);
		} catch (IOException e) {
			System.err.println("Lỗi khi ghi file Excel:");
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			File file = new File(fileName);
			if (file.exists() && Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		} catch (IOException e) {
			System.err.println("Lỗi khi mở file Excel:");
			e.printStackTrace();
		}
	}

	//Hàm chỉ định tabbedPane
	public void hienThiThongKeChuyenTau() {
		tabbedPane.setSelectedIndex(2);
	}
	public void hienThiThongKeDoanhThu() {
		tabbedPane.setSelectedIndex(1);
	}
	public void hienThiThongKeDoanhThuTheoCa() {
		tabbedPane.setSelectedIndex(0);
	}
}