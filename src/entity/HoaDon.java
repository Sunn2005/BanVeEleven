package entity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import dao.ChiTietHoaDon_DAO;
import dao.Ga_DAO;
import dao.KhachHang_DAO;
import dao.Ve_DAO;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime ngayLapHoaDon;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private ChiTietHoaDon chiTiet;
	private Boolean daHoanVe;
	private Boolean daHoanTien;

	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
	private Ve_DAO ve_DAO = new Ve_DAO();
	private Ga_DAO ga_DAO = new Ga_DAO();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm");
	DecimalFormat df = new DecimalFormat("##,###");
	private static final String[] CHU_SO = { "không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín" };
	private static final String[] DON_VI = { "", "nghìn", "triệu", "tỷ" };

	public HoaDon(String maHoaDon, LocalDateTime ngayLapHoaDon, NhanVien nhanVien, KhachHang khachHang,
			ChiTietHoaDon chiTiet, Boolean daHoanVe, Boolean daHoanTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.chiTiet = chiTiet;
		this.daHoanVe = daHoanVe;
		this.daHoanTien = daHoanTien;
	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		String ktMaHoaDon = "^\\d{2}\\d{2}\\d{2}NV\\d{3}\\d{5}$";
		if (ktMaHoaDon.matches(ktMaHoaDon))
			this.maHoaDon = maHoaDon;
		else
			throw new IllegalArgumentException("Mã hóa đơn không hợp lệ!");
	}

	public LocalDateTime getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDateTime ngayLapHoaDon) {
		if (ngayLapHoaDon.toLocalDate().isEqual(LocalDate.now()))
			this.ngayLapHoaDon = ngayLapHoaDon;
		else
			throw new IllegalArgumentException("Ngày lập hóa đơn phải là ngày hiện tại!");
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
		// Kiểm tra tồn tại
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
		// Kiểm tra tồn tại
	}

	public ChiTietHoaDon getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(ChiTietHoaDon chiTiet) {
		this.chiTiet = chiTiet;
		// Kiểm tra tồn tại
	}

	public Boolean getDaHoanVe() {
		return daHoanVe;
	}

	public void setDaHoanVe(Boolean daHoanVe) {
		this.daHoanVe = daHoanVe;
	}

	public Boolean getDaHoanTien() {
		return daHoanTien;
	}

	public void setDaHoanTien(Boolean daHoanTien) {
		this.daHoanTien = daHoanTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLapHoaDon=" + ngayLapHoaDon + ", nhanVien=" + nhanVien
				+ ", khachHang=" + khachHang + ", chiTiet=" + chiTiet + ", daHoanVe=" + daHoanVe + ", daHoanTien="
				+ daHoanTien + "]";
	}

	public float tongTien() {
		return this.chiTiet.tinhTien();
	}

	public float tinhTienHoan() {
		int size = this.chiTiet.getDsVe().size();
		long thoiGian = Duration.between(chiTiet.getDsVe().get(0).getGioDi(), LocalTime.now()).toHours();
		if (size == 1) {
			if (thoiGian >= 24)
				return chiTiet.tinhTien() * 0.9f;
			else if (thoiGian >= 4)
				return chiTiet.tinhTien() * 0.8f;
		} else {
			if (thoiGian >= 72)
				return chiTiet.tinhTien() * 0.9f;
			else if (thoiGian >= 24)
				return chiTiet.tinhTien() * 0.8f;
		}
		return 0;
	}

	public void xuatHoaDon(String pdfPath) {
		try {
			// Tạo file PDF
			PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document document = new Document(pdfDoc);

			document.setMargins(10, 40, 30, 10);

			// Tải font Unicode hỗ trợ tiếng Việt
			String fontPathRegular = "font/TIMES.TTF"; // Đường dẫn đến font thường
			String fontPathBold = "font/TIMESBD.TTF"; // Đường dẫn đến font in đậm
			String fontPathItalic = "font/TIMESI.TTF"; // Đường dẫn đến font in nghiêng

			PdfFont fontRegular = PdfFontFactory.createFont(fontPathRegular, PdfEncodings.IDENTITY_H, true);
			PdfFont fontBold = PdfFontFactory.createFont(fontPathBold, PdfEncodings.IDENTITY_H, true);
			PdfFont fontItalic = PdfFontFactory.createFont(fontPathItalic, PdfEncodings.IDENTITY_H, true);

			// Thêm hình ảnh vào đầu tài liệu
			String imagePath = getClass().getResource("/img/Logo_eleven.png").getPath(); // Đường dẫn đến hình ảnh
			Image img = new Image(ImageDataFactory.create(imagePath));
			img.setWidth(220); // Đặt chiều rộng cho hình ảnh

			// Thêm thông tin khách hàng và địa chỉ
			UnitValue[] columnWidths = { UnitValue.createPercentValue(3), UnitValue.createPercentValue(5),
					UnitValue.createPercentValue(3) };

			// Tạo bảng tiêu đề chiếm toàn bộ chiều rộng của trang
			Table headerTable = new Table(columnWidths);
			headerTable.setWidth(580); // Chiều rộng cụ thể của bảng tiêu đề

			// Thêm các ô vào bảng mà không có border
			headerTable.addCell(new Cell(2, 1).add(img).setBorder(Border.NO_BORDER));
			headerTable.addCell(new Cell(1, 1).add(
					new Paragraph("HÓA ĐƠN").setFont(fontBold).setFontSize(20).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));
			Paragraph thongTinHoaDon = new Paragraph().add(new Text("Mã Hóa Đơn: ").setFont(fontBold).setFontSize(10))
					.add(new Text(maHoaDon).setFont(fontRegular).setFontSize(10))
					.add(new Text("\nNgày lập: ").setFont(fontBold).setFontSize(10))
					.add(new Text(ngayLapHoaDon.format(formatter)).setFont(fontRegular).setFontSize(10))
					.add(new Text("\nThời gian lập: ").setFont(fontBold).setFontSize(10))
					.add(new Text(ngayLapHoaDon.format(timeFormater)).setFont(fontRegular).setFontSize(10));
			headerTable.addCell(new Cell(2, 1).add(thongTinHoaDon).setBorder(Border.NO_BORDER));
			headerTable
					.addCell(new Cell(1, 1)
							.add(new Paragraph("Ngày " + ngayLapHoaDon.getDayOfMonth() + ", tháng "
									+ ngayLapHoaDon.getMonthValue() + ", năm " + ngayLapHoaDon.getYear())
									.setFont(fontRegular).setTextAlignment(TextAlignment.CENTER))
							.setBorder(Border.NO_BORDER));

			// Thêm bảng vào tài liệu
			document.add(headerTable);

			// Thêm thông tin khách hàng và địa chỉ
			UnitValue[] columnWidths1 = { UnitValue.createPercentValue(20), UnitValue.createPercentValue(30),
					UnitValue.createPercentValue(20), UnitValue.createPercentValue(30) };

			Table tableKH = new Table(columnWidths1);
			tableKH.setWidth(580);
			tableKH.addCell(
					new Cell().add(new Paragraph("Đơn vị bán vé: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Nhà ga Đoàn tàu Eleven").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Điện thoại: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("0123456789").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Mã nhân viên: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1, 3).add(new Paragraph(nhanVien.getMaNV()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));

			KhachHang khachHangNew = khachHang_DAO.getKhachHangTheoMaKH(khachHang.getMaKH());
			tableKH.addCell(
					new Cell().add(new Paragraph("Mã khách hàng: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(khachHangNew.getMaKH()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Tên khách hàng: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(khachHangNew.getTenKH()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("SĐT khách hàng: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1, 3).add(new Paragraph(khachHangNew.getSdt()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));

			document.add(tableKH);

			Table table = new Table(7);
			table.setWidth(580);

			// Tiêu đề
			table.addCell(new Cell().add(
					new Paragraph("STT").setFont(fontBold).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("Mã vé").setFont(fontBold).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Thông tin vé").setFont(fontBold).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Giá gốc\n(VND)").setFont(fontBold).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Đối tượng").setFont(fontBold).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Khuyến mãi\n(VND)").setFont(fontBold).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Thành tiền chưa thuế\n(VND)").setFont(fontBold).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));

			// Cách tính
			table.addCell(new Cell().add(
					new Paragraph("a").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("b").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("c").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("d").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("e").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("e → f").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("g = d - f").setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));

			// Thêm các dòng trong bảng
			ChiTietHoaDon chiTietNew = chiTietHoaDon_DAO.getCTHDTheoMaChiTiet(chiTiet.getMaChiTiet());
			int tongGiaGoc = 0;
			int tongKhuyenMai = 0;
			int tongChuaThue = 0;
			int tongThue = 0;
			int tongCoThue = 0;
			int count = 1;
			for (Ve i : chiTietNew.getDsVe()) {
				Ve ve = ve_DAO.getVeTheoMaVe(i.getMaVe());
				// STT
				table.addCell(new Cell().add(new Paragraph(String.valueOf(count++)).setFont(fontRegular).setFontSize(10)
						.setTextAlignment(TextAlignment.CENTER)));

				// Mã vé
				table.addCell(new Cell().add(new Paragraph(ve.getMaVe()).setFont(fontRegular).setFontSize(10)
						.setTextAlignment(TextAlignment.CENTER)));

				// Thông tin vé
				String thongTinVe = "Từ " + ga_DAO.getGaTheoMaGa(ve.getGaDi().getMaGa()).getTenGa() + " đến "
						+ ga_DAO.getGaTheoMaGa(ve.getGaDen().getMaGa()).getTenGa() + "\nNgày: "
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
				table.addCell(new Cell().add(new Paragraph(thongTinVe).setFont(fontRegular).setFontSize(10)));

				// Giá gốc
				float giaGoc = ve.tinhGiaVeGoc();
				table.addCell(new Cell().add(new Paragraph(df.format(giaGoc)).setFont(fontRegular).setFontSize(10)
						.setTextAlignment(TextAlignment.CENTER)));

				// Đối tượng
				table.addCell(new Cell().add(new Paragraph(ve.getKhuyenMai()).setFont(fontRegular).setFontSize(10)));

				// Khuyến mãi
				float khuyenMai;
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
				table.addCell(new Cell().add(new Paragraph(df.format(khuyenMai)).setFont(fontRegular).setFontSize(10)
						.setTextAlignment(TextAlignment.CENTER)));

				// Thành tiền chưa thuế
				float tienChuaThue = giaGoc - khuyenMai;
				table.addCell(new Cell().add(new Paragraph(df.format(tienChuaThue)).setFont(fontRegular).setFontSize(10)
						.setTextAlignment(TextAlignment.CENTER)));

				tongGiaGoc += giaGoc;
				tongKhuyenMai += khuyenMai;
				tongChuaThue += tienChuaThue;
				tongThue += (giaGoc - khuyenMai) * 0.1f;
				tongCoThue += (giaGoc - khuyenMai) * 1.1f;
			}

			// Thêm tổng cộng
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng cộng (VND):").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell().add(new Paragraph("").setFont(fontRegular)));
			table.addCell(new Cell().add(new Paragraph(df.format(tongGiaGoc)).setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("").setFont(fontRegular)));
			table.addCell(new Cell().add(new Paragraph(df.format(tongKhuyenMai)).setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph(df.format(tongChuaThue)).setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));

			table.addCell(new Cell(1, 2)
					.add(new Paragraph("Thuế giá trị gia tăng (10%): ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 5).add(new Paragraph(df.format(tongThue) + " VND").setFont(fontRegular)
					.setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng tiền bằng số: ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 5).add(new Paragraph(df.format(tongCoThue) + " VND").setFont(fontRegular)
					.setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng tiền bằng chữ: ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 5).add(new Paragraph(convertToWords((long) tongCoThue).toUpperCase())
					.setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));

			// Thêm ghi chú
			table.addCell(new Cell(1, 7).add(new Paragraph(
					"\nGhi chú:......................................................................................................................................................................................................................."))
					.setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)
					.setBorder(Border.NO_BORDER));

			document.add(table);

			// Thêm phần ký tên
			Table tableKyTen = new Table(2);
			tableKyTen.setWidth(580);
			tableKyTen.addCell(
					new Cell().add(new Paragraph("Khách hàng").setFont(fontBold).setTextAlignment(TextAlignment.CENTER))
							.setBorder(Border.NO_BORDER));
			tableKyTen.addCell(new Cell()
					.add(new Paragraph("Người bán hàng").setFont(fontBold).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));
			tableKyTen.addCell(new Cell().add(
					new Paragraph("(Ký, ghi rõ họ tên)").setFont(fontItalic).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));
			tableKyTen.addCell(new Cell().add(
					new Paragraph("(Ký, ghi rõ họ tên)").setFont(fontItalic).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));

			document.add(tableKyTen);

			document.add(new Paragraph("\n\n\n\n\n"));

			document.add(new Paragraph("*Kiểm tra đối chiếu ký trước khi nhận hóa đơn").setFont(fontItalic)
					.setFontSize(10).setTextAlignment(TextAlignment.LEFT));

			// Đóng tài liệu
			document.close();
			System.out.println("Đã tạo hóa đơn và lưu vào file PDF: " + pdfPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void xuatHoaDonHoanTien(String pdfPath) {
		try {
			// Tạo file PDF
			PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));
			PdfDocument pdfDoc = new PdfDocument(writer);
//			pdfDoc.setDefaultPageSize(new PageSize(650,720));
			
			Document document = new Document(pdfDoc);
			document.setMargins(10, 40, 30, 10);

			// Tải font Unicode hỗ trợ tiếng Việt
			String fontPathRegular = "font/TIMES.TTF"; // Đường dẫn đến font thường
			String fontPathBold = "font/TIMESBD.TTF"; // Đường dẫn đến font in đậm
			String fontPathItalic = "font/TIMESI.TTF"; // Đường dẫn đến font in nghiêng

			PdfFont fontRegular = PdfFontFactory.createFont(fontPathRegular, PdfEncodings.IDENTITY_H, true);
			PdfFont fontBold = PdfFontFactory.createFont(fontPathBold, PdfEncodings.IDENTITY_H, true);
			PdfFont fontItalic = PdfFontFactory.createFont(fontPathItalic, PdfEncodings.IDENTITY_H, true);

			// Thêm hình ảnh vào đầu tài liệu
			String imagePath = getClass().getResource("/img/Logo_eleven.png").getPath(); // Đường dẫn đến hình ảnh
			Image img = new Image(ImageDataFactory.create(imagePath));
			img.setWidth(120); // Đặt chiều rộng cho hình ảnh

			// Thêm thông tin khách hàng và địa chỉ
			UnitValue[] columnWidths = { UnitValue.createPercentValue(3), UnitValue.createPercentValue(5),
					UnitValue.createPercentValue(3) };

			// Tạo bảng tiêu đề chiếm toàn bộ chiều rộng của trang
			Table headerTable = new Table(columnWidths);
			headerTable.setWidth(650); // Chiều rộng cụ thể của bảng tiêu đề

			// Thêm các ô vào bảng mà không có border
			headerTable.addCell(new Cell(2, 1).add(img).setBorder(Border.NO_BORDER));
			headerTable.addCell(new Cell(1, 1).add(
					new Paragraph("HÓA ĐƠN").setFont(fontBold).setFontSize(20).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));
			Paragraph thongTinHoaDon = new Paragraph().add(new Text("Mã Hóa Đơn: ").setFont(fontBold).setFontSize(10))
					.add(new Text(maHoaDon).setFont(fontRegular).setFontSize(10))
					.add(new Text("\nTrạng thái:"+ " ").setFont(fontBold).setFontSize(10))
					.add(new Text(daHoanTien == true?"Đã hoàn tiền":"").setFont(fontRegular).setFontSize(10))
					.add(new Text("\nNgày lập: ").setFont(fontBold).setFontSize(10))
					.add(new Text(ngayLapHoaDon.format(formatter)).setFont(fontRegular).setFontSize(10))
					.add(new Text("\nThời gian lập: ").setFont(fontBold).setFontSize(10))
					.add(new Text(ngayLapHoaDon.format(timeFormater)).setFont(fontRegular).setFontSize(10));
			headerTable.addCell(new Cell(2, 1).add(thongTinHoaDon).setBorder(Border.NO_BORDER));
			headerTable
					.addCell(new Cell(1, 1)
							.add(new Paragraph("Ngày " + ngayLapHoaDon.getDayOfMonth() + ", tháng "
									+ ngayLapHoaDon.getMonthValue() + ", năm " + ngayLapHoaDon.getYear())
									.setFont(fontRegular).setTextAlignment(TextAlignment.CENTER))
							.setBorder(Border.NO_BORDER));

			// Thêm bảng vào tài liệu
			document.add(headerTable);

			// Thêm thông tin khách hàng và địa chỉ
			UnitValue[] columnWidths1 = { UnitValue.createPercentValue(20), UnitValue.createPercentValue(30),
					UnitValue.createPercentValue(20), UnitValue.createPercentValue(30) };

			Table tableKH = new Table(columnWidths1);
			tableKH.setWidth(580);
			tableKH.addCell(
					new Cell().add(new Paragraph("Đơn vị bán vé: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Nhà ga Eleven").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Điện thoại: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("0123456789").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Mã nhân viên: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1, 3).add(new Paragraph(nhanVien.getMaNV()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));

			KhachHang khachHangNew = khachHang_DAO.getKhachHangTheoMaKH(khachHang.getMaKH());
			tableKH.addCell(
					new Cell().add(new Paragraph("Mã khách hàng: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(khachHangNew.getMaKH()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Tên khách hàng: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(khachHangNew.getTenKH()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("SĐT khách hàng: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1, 3).add(new Paragraph(khachHangNew.getSdt()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));

			document.add(tableKH);

			UnitValue[] columnWidths2 = {
					UnitValue.createPercentValue(5),  // STT
				    UnitValue.createPercentValue(15), // Mã vé
				    UnitValue.createPercentValue(30), // Thông tin vé
				    UnitValue.createPercentValue(10), // Giá gốc
				    UnitValue.createPercentValue(10), // Đối tượng
				    UnitValue.createPercentValue(10), // Khuyến mãi
				    UnitValue.createPercentValue(10), // Thành tiền chưa thuế
				    UnitValue.createPercentValue(10)  // Phí hoàn tiền
			};
			Table table = new Table(columnWidths2);
			table.setWidth(580);

			// Tiêu đề
			// Tiêu đề bảng (8 cột)
			table.addCell(new Cell().add(
			    new Paragraph("STT").setFont(fontBold).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
			    new Paragraph("Mã vé").setFont(fontBold).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Thông tin vé").setFont(fontBold).setFontSize(10)
			    .setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Giá gốc\n(VND)").setFont(fontBold).setFontSize(10)
			    .setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Đối tượng").setFont(fontBold).setFontSize(10)
			    .setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Khuyến mãi\n(VND)").setFont(fontBold).setFontSize(10)
			    .setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Thành tiền chưa thuế\n(VND)").setFont(fontBold).setFontSize(10)
			    .setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("Phí hoàn tiền\n(VND)").setFont(fontBold).setFontSize(10)
			    .setTextAlignment(TextAlignment.CENTER)));

			// Cách tính
			table.addCell(new Cell().add(
					new Paragraph("a").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("b").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("c").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("d").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("e").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(
					new Paragraph("e → f").setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("g = d - f").setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("").setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));

			// Thêm các dòng trong bảng
			ChiTietHoaDon chiTietNew = chiTietHoaDon_DAO.getCTHDTheoMaChiTiet(chiTiet.getMaChiTiet());
			int tongGiaGoc = 0;
			int tongKhuyenMai = 0;
			int tongChuaThue = 0;
			int tongThue = 0;
			int tongCoThue = 0;
			int tongPhiHoan = 0;
			int count = 1;
			boolean tapThe = chiTietNew.getDsVe().size() > 1;
			for (Ve i : chiTietNew.getDsVe()) {
				Ve ve = ve_DAO.getVeTheoMaVe(i.getMaVe());
				table.addCell(new Cell().add(new Paragraph(String.valueOf(count++)).setFont(fontRegular).setFontSize(10)
					    .setTextAlignment(TextAlignment.CENTER))); // STT

				table.addCell(new Cell().add(new Paragraph(ve.getMaVe()).setFont(fontRegular).setFontSize(10)
						.setTextAlignment(TextAlignment.CENTER))); // Mã vé
				// Thông tin vé
				String thongTinVe = "Từ " + ga_DAO.getGaTheoMaGa(ve.getGaDi().getMaGa()).getTenGa() + " đến "
						+ ga_DAO.getGaTheoMaGa(ve.getGaDen().getMaGa()).getTenGa() + "\nNgày: "
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
				table.addCell(new Cell().add(new Paragraph(thongTinVe).setFont(fontRegular).setFontSize(10))); // Thông tin vé

				// Giá gốc
				float giaGoc = ve.tinhGiaVeGoc();
				table.addCell(new Cell().add(new Paragraph(df.format(giaGoc)).setFont(fontRegular).setFontSize(10)
					    .setTextAlignment(TextAlignment.CENTER))); // Giá gốc

				// Đối tượng
				table.addCell(new Cell().add(new Paragraph(ve.getKhuyenMai()).setFont(fontRegular).setFontSize(10))); // Đối tượng

				// Khuyến mãi
				float khuyenMai;
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
				table.addCell(new Cell().add(new Paragraph(df.format(khuyenMai)).setFont(fontRegular).setFontSize(10)
					    .setTextAlignment(TextAlignment.CENTER))); // Khuyến mãi

				// Thành tiền chưa thuế
				float tienChuaThue = giaGoc - khuyenMai;
				table.addCell(new Cell().add(new Paragraph(df.format(tienChuaThue)).setFont(fontRegular).setFontSize(10)
					    .setTextAlignment(TextAlignment.CENTER))); // Thành tiền chưa thuế
				
				//Phí hoàn tiền
				float phiHoanTien = ve.tinhPhiHoanVe(tapThe);
				table.addCell(new Cell().add(new Paragraph(df.format(phiHoanTien)).setFont(fontRegular).setFontSize(10)
					    .setTextAlignment(TextAlignment.CENTER))); // Phí hoàn tiền

				tongGiaGoc += giaGoc;
				tongKhuyenMai += khuyenMai;
				tongChuaThue += tienChuaThue;
				tongThue += (giaGoc - khuyenMai) * 0.1f;
				tongCoThue += (giaGoc - khuyenMai) * 1.1f;
				tongPhiHoan +=phiHoanTien;
			}

			// Thêm tổng cộng
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng cộng (VND):").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell().add(new Paragraph("").setFont(fontRegular)));
			table.addCell(new Cell().add(new Paragraph(df.format(tongGiaGoc)).setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph("").setFont(fontRegular)));
			table.addCell(new Cell().add(new Paragraph(df.format(tongKhuyenMai)).setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph(df.format(tongChuaThue)).setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell().add(new Paragraph(df.format(tongPhiHoan)).setFont(fontRegular).setFontSize(10)
					.setTextAlignment(TextAlignment.CENTER)));
			
			table.addCell(new Cell(1, 2)
					.add(new Paragraph("Thuế giá trị gia tăng (10%): ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 6).add(new Paragraph(df.format(tongThue) + " VND").setFont(fontRegular)
					.setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng tiền bằng số: ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 6).add(new Paragraph(df.format(tongCoThue) + " VND").setFont(fontRegular)
					.setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng phí hoàn vé: ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 6).add(new Paragraph(df.format(tongPhiHoan) + " VND").setFont(fontRegular)
					.setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng tiền trả lại bằng số: ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 6).add(new Paragraph(df.format(tongCoThue- tongPhiHoan) + " VND").setFont(fontRegular)
					.setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
			table.addCell(new Cell(1, 2).add(new Paragraph("Tổng tiền trả lại bằng chữ: ").setFont(fontBold).setFontSize(10)));
			table.addCell(new Cell(1, 6).add(new Paragraph(convertToWords((long) tongCoThue- tongPhiHoan).toUpperCase())
					.setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)));

			// Thêm ghi chú
			table.addCell(new Cell(1, 8).add(new Paragraph(
					"\nGhi chú:......................................................................................................................................................................................................................."))
					.setFont(fontRegular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)
					.setBorder(Border.NO_BORDER));

			document.add(table);

			// Thêm phần ký tên
			Table tableKyTen = new Table(2);
			tableKyTen.setWidth(580);
			tableKyTen.addCell(
					new Cell().add(new Paragraph("Khách hàng").setFont(fontBold).setTextAlignment(TextAlignment.CENTER))
							.setBorder(Border.NO_BORDER));
			tableKyTen.addCell(new Cell()
					.add(new Paragraph("Người bán hàng").setFont(fontBold).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));
			tableKyTen.addCell(new Cell().add(
					new Paragraph("(Ký, ghi rõ họ tên)").setFont(fontItalic).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));
			tableKyTen.addCell(new Cell().add(
					new Paragraph("(Ký, ghi rõ họ tên)").setFont(fontItalic).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));

			document.add(tableKyTen);

			document.add(new Paragraph("\n\n\n\n\n"));

			document.add(new Paragraph("*Kiểm tra đối chiếu ký trước khi nhận hóa đơn").setFont(fontItalic)
					.setFontSize(10).setTextAlignment(TextAlignment.LEFT));

			// Đóng tài liệu
			document.close();
			pdfDoc.setDefaultPageSize(PageSize.A4);
			System.out.println("Đã tạo hóa đơn và lưu vào file PDF: " + pdfPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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
}
