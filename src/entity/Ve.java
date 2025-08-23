package entity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import dao.KhachHang_DAO;

public class Ve {
	private String maVe;
	private ChuyenTau chuyenTau;
	private Toa toa;
	private Ghe soGhe;
	private KhachHang khachHang;
	private LocalDate ngayDi;
	private LocalTime gioDi;
	private LocalDate ngayDen;
	private LocalTime gioDen;
	private Ga gaDi;
	private Ga gaDen;
	private String hang;
	private String khuyenMai;
	private boolean trangThai;
	private ChiTietHoaDon chiTiet;
	
	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm");
	DecimalFormat df = new DecimalFormat("##,###");
	
	public Ve(String maVe, ChuyenTau chuyenTau, Toa toa, Ghe soGhe, KhachHang khachHang, LocalDate ngayDi, LocalTime gioDi,
			LocalDate ngayDen, LocalTime gioDen,Ga gaDi,Ga gaDen, String hang, String khuyenMai,boolean trangThai, ChiTietHoaDon chiTiet) {
		super();
		this.setMaVe(maVe);
		this.setChuyenTau(chuyenTau);
		this.setToa(toa);
		this.setSoGhe(soGhe);
		this.setKhachHang(khachHang);
		this.ngayDi = ngayDi;
		this.gioDi = gioDi;
		this.ngayDen = ngayDen;
		this.gioDen = gioDen;
		this.setGaDen(gaDen);
		this.setGaDi(gaDi);
		this.setHang(hang);
		this.setKhuyenMai(khuyenMai);
		this.setTrangThai(trangThai);
		this.setChiTiet(chiTiet);
	}

	public Ve(String maVe) {
		super();
		this.maVe = maVe;
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		String ktMaVe = "^VE\\d{2}\\d{2}\\d{2}\\d{4}$";
		if (maVe.matches(ktMaVe))
			this.maVe = maVe;
		else
			throw new IllegalArgumentException("Mã vé không hợp lệ! Mã vé có dạng VE + 6 số chỉ ngày tháng năm + 4 số chỉ số thứ tự ");
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
		//Kiểm tra tồn tại
	}

	public Toa getToa() {
		return toa;
	}

	public void setToa(Toa toa) {
		this.toa = toa;
		//Kiểm tra tồn tại
	}

	public Ghe getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(Ghe soGhe) {
		this.soGhe = soGhe;
		//Kiểm tra tồn tại
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
		//Kiểm tra tồn tại
	}

	public LocalDate getNgayDi() {
		return ngayDi;
	}

	public LocalTime getGioDi() {
		return gioDi;
	}
	public LocalDate getNgayDen() {
		return ngayDen;
	}

	public LocalTime getGioDen() {
		return gioDen;
	}
	public Ga getGaDi() {
		return gaDi;
	}

	public void setGaDi(Ga gaDi) {
		this.gaDi = gaDi;
		//Kiểm tra tồn tại
	}
	public String getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(String khuyenMai) {
		this.khuyenMai = khuyenMai;
		//Kiểm tra tồn tại
	}
	public String getHang() {
		return hang;
	}
	public void setHang(String hang) {
		this.hang = hang;
		//Kiểm tra tồn tại
	}
	public Ga getGaDen() {
		return gaDen;
	}

	public void setGaDen(Ga gaDen) {
		this.gaDen = gaDen;
		//Kiểm tra tồn tại
}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public ChiTietHoaDon getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(ChiTietHoaDon chiTiet) {
		this.chiTiet = chiTiet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maVe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		return Objects.equals(maVe, other.maVe);
	}
	

	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", chuyenTau=" + chuyenTau + ", toa=" + toa + ", soGhe=" + soGhe + ", khachHang="
				+ khachHang + ", ngayDi=" + ngayDi + ", gioDi=" + gioDi + ", gaDi=" + gaDi + ", gaDen=" + gaDen
				+ ", khuyenMai=" + khuyenMai + ", hang=" + hang + ", trangThai=" + trangThai + ", chiTiet=" + chiTiet
				+ "]";
	}

	public float tinhTiGia() {
		float tiGia = 1;
		if (khuyenMai.equalsIgnoreCase("Sinh viên"))
			tiGia += -0.1;
		else if (khuyenMai.equalsIgnoreCase("Người lớn"))
			tiGia += 0;
		else if (khuyenMai.equalsIgnoreCase("Người lớn tuổi"))
			tiGia += -0.15;
		else if (khuyenMai.equalsIgnoreCase("Trẻ em từ 6 đến 10 tuổi"))
			tiGia += -0.25;
		else tiGia = 0;
		return tiGia;
	}
	
	public float tinhGiaVeGoc() {
		int quangDuong = Math.abs(gaDen.getChiSoKm() - gaDi.getChiSoKm());
		float tiGia = 0;
		if (hang.equalsIgnoreCase("Ghế mềm"))
			tiGia += 1;
		else if (hang.equalsIgnoreCase("Giường nằm"))
			tiGia += 1.2;
		else
			tiGia += 1.8;
		if (quangDuong <= 50)
			return quangDuong * 2000 * tiGia;
		else if (quangDuong <= 400)
			return quangDuong * 800 * tiGia;
		return quangDuong * 600 * tiGia;
	}
	
	public float tinhGiaVe() {
		return tinhGiaVeGoc() * tinhTiGia();
	}
	
	public boolean xuatVe() {
		LocalDate ngayHienTai = LocalDate.now();
		LocalTime gioHienTai = LocalTime.now();
		
		if (ngayHienTai.isBefore(ngayDi) && gioHienTai.isBefore(gioDi)) {
			setTrangThai(false);
			return true;
		}
		return false;
	}
	
	public boolean datVe() {
		if (gaDen.isTrangThai())
			return true;
		return false;
	}
	
	public boolean doiVe() {
	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, gioDi);
	    
	    if (now.isBefore(thoiGianDi.minusHours(24))) {
	        setTrangThai(false);
	        return true;
	    }
	    return false;
	}
	
	public boolean hoanVe(Boolean isTapThe) {
	    // Kiểm tra ngày và giờ có hợp lệ
	    if (ngayDi == null || gioDi == null) {
	        return false; // Không đủ điều kiện hoàn vé
	    }

	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, gioDi);
	    long hoursDiff = java.time.Duration.between(now, thoiGianDi).toHours();

	    // Trường hợp thời gian đi đã qua
	    if (hoursDiff < 0) {
	        return false; // Không hoàn vé nếu thời gian đi đã qua
	    }

	    // Áp dụng logic hoàn vé theo loại vé
	    if (isTapThe) {
	        // Vé tập thể: hoàn vé nếu còn ít nhất 24 giờ
	        return hoursDiff >= 24;
	    } else {
	        // Vé cá nhân: hoàn vé nếu còn ít nhất 4 giờ
	        return hoursDiff >= 4;
	    }
	}
	
	//tính phí hoàn vé
	public float tinhPhiHoanVe(Boolean isTapThe) {
	    // Kiểm tra ngày và giờ có hợp lệ hay không
	    if (ngayDi == null || gioDi == null) {
	    	 return 0.0f; // Không hoàn phí
	    }

	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, gioDi);
	    long hoursDiff = java.time.Duration.between(now, thoiGianDi).toHours();
	    double phiHoan = 0.0;

	    // Trường hợp thời gian đi đã qua
	    if (hoursDiff < 0) {
	        return 0.0f; // Không hoàn phí
	    }

	    // Logic tính phí hoàn
	    if (isTapThe) {
	        if (hoursDiff >= 72) {
	            phiHoan = 0.1; // 10% phí
	        } else if (hoursDiff >= 24) {
	            phiHoan = 0.2; // 20% phí
	        }
	    } else { // Vé cá nhân
	        if (hoursDiff >= 24) {
	            phiHoan = 0.1; // 10% phí
	        } else if (hoursDiff >= 4) {
	            phiHoan = 0.2; // 20% phí
	        }
	    }

	    // Tính phí hoàn dựa trên giá vé
	    float giaVe = tinhGiaVe();
	    if (giaVe <= 0) {
	        return 0.0f;
	    }
	    
	    return (float) (phiHoan * giaVe);
	}

	public boolean kiemTraHoanTien() {
	    // Kiểm tra ngày và giờ có hợp lệ
	    if (ngayDi == null || gioDi == null) {
	        return false; // Không hợp lệ để hoàn tiền
	    }

	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime thoiGianDi = LocalDateTime.of(ngayDi, gioDi);
	    long hoursDiff = java.time.Duration.between(now, thoiGianDi).toHours();

	    // Không được hoàn nếu thời gian đi đã qua
	    if (hoursDiff < 0) {
	        return false;
	    }

	    // Kiểm tra giá vé có hợp lệ
	    float giaVe = tinhGiaVe();
	    if (giaVe <= 0) {
	        return false;
	    }

	    // Nếu tất cả điều kiện đều hợp lệ, trả về true
	    return true;
	}
	
	
	public void xuatVe(String pdfPath) {
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
			String imagePath = getClass().getResource("/img/Logo_eleven_trang.png").getPath(); // Đường dẫn đến hình ảnh
			Image img = new Image(ImageDataFactory.create(imagePath));
			img.setWidth(120); // Đặt chiều rộng cho hình ảnh

			// Thêm thông tin khách hàng và địa chỉ
			UnitValue[] columnWidths = { UnitValue.createPercentValue(3), UnitValue.createPercentValue(5),
					UnitValue.createPercentValue(3) };

			// Tạo bảng tiêu đề chiếm toàn bộ chiều rộng của trang
			Table headerTable = new Table(columnWidths);
			headerTable.setWidth(580); // Chiều rộng cụ thể của bảng tiêu đề

			// Thêm các ô vào bảng mà không có border
			headerTable.addCell(new Cell(2, 1).add(img).setBorder(Border.NO_BORDER));
			headerTable.addCell(new Cell(1, 1).add(
					new Paragraph("VÉ LÊN TÀU").setFont(fontBold).setFontSize(20).setTextAlignment(TextAlignment.CENTER))
					.setBorder(Border.NO_BORDER));
			// Thêm bảng vào tài liệu
			document.add(headerTable);

			// Thêm thông tin khách hàng và địa chỉ
			UnitValue[] columnWidths1 = { UnitValue.createPercentValue(20), UnitValue.createPercentValue(30),
					UnitValue.createPercentValue(20), UnitValue.createPercentValue(30) };

			Table tableKH = new Table(columnWidths1);
			tableKH.setWidth(580);
			
			
			tableKH.addCell(
					new Cell(1,4).add(new Paragraph("Mã vé/TicketID: "+" "+ maVe).setFont(fontRegular).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Ga đi/Train station: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(gaDi.getDiaChi()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Ga đến/Train station: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(gaDen.getDiaChi()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));
			
			tableKH.addCell(
					new Cell().add(new Paragraph("Tàu/Train: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1, 3).add(new Paragraph(chuyenTau.getMaTau()).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Ngày đi/Date: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(ngayDi.format(formatter)).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Ngày đến/Date: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(ngayDen.format(formatter)).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Giờ đi/Time: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(gioDi.format(timeFormater)).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Giờ đến/Time: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(gioDen.format(timeFormater)).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Toa/Coach: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(toa.getMaToa()).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Ghế/Train seats: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell().add(new Paragraph(String.valueOf(soGhe.getSoGhe())).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Hạng ghế/Class: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1,3).add(new Paragraph(hang).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Đối tượng/Object: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1,3).add(new Paragraph(khuyenMai).setFont(fontRegular))
				.setBorder(Border.NO_BORDER));
			
			KhachHang khachHangNew = khachHang_DAO.getKhachHangTheoMaKH(khachHang.getMaKH());
			tableKH.addCell(
					new Cell().add(new Paragraph("Họ tên/Name: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1,3).add(new Paragraph(khachHangNew.getTenKH()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));
			tableKH.addCell(
					new Cell().add(new Paragraph("Giấy tờ/Passport: ").setFont(fontRegular)).setBorder(Border.NO_BORDER));
			tableKH.addCell(new Cell(1,3).add(new Paragraph(khachHangNew.getCccd()).setFont(fontRegular))
					.setBorder(Border.NO_BORDER));

			document.add(tableKH);

			Table table = new Table(7);
			table.setWidth(580);

			document.add(new Paragraph("\n\n\n\n\n"));

			document.add(new Paragraph("*Kiểm tra thông tin vé trước khi nhận vé và sau khi nhận vé.\n").setFont(fontItalic)
					.setFontSize(10).setTextAlignment(TextAlignment.LEFT));
			document.add(new Paragraph("*Please check ticket information before receiving tickets and after receiving tickets.").setFont(fontItalic)
					.setFontSize(10).setTextAlignment(TextAlignment.LEFT));
			// Đóng tài nguyên
	        document.close();
			System.out.println("Đã tạo vé và lưu vào file PDF: " + pdfPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
