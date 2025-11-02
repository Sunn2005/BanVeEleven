package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.ChiTietHoaDon;
import entity.Ga;
import entity.Ghe;
import entity.KhachHang;
import entity.Toa;
import entity.Ve;

public class Ve_DAO {

	ArrayList<Ve> dsVe;
	private Ga_DAO dsGa;
	public Ve_DAO() {
		dsVe = new ArrayList<>();
		dsGa = new Ga_DAO();
	}

	// Phương thức đọc tất cả các vé từ bảng Ve
	public ArrayList<Ve> docTuBang() {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM Ve";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maVe = rs.getString("maVe");
				String maTau = rs.getString("tau");
				String maToa = rs.getString("toa");
				int soGhe = rs.getInt("soGhe");
				String maKH = rs.getString("khachHang");
				LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
				LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
				LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
				LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
				String maGaDi = rs.getString("gaDi");
				String maGaDen = rs.getString("gaDen");
				String hang = rs.getString("hang");
				String khuyenMai = rs.getString("khuyenMai");
				boolean trangThai = rs.getBoolean("trangThai");
				String maChiTiet = rs.getString("chiTiet");

				// Sử dụng constructor copy để tạo đối tượng
				ChuyenTau tau = new ChuyenTau(maTau);
				Toa toa = new Toa(maToa);
				Ghe ghe = new Ghe(soGhe, toa);
				KhachHang khachHang = new KhachHang(maKH);
				Ga gaDi = dsGa.getGaTheoMaGa(maGaDi);
				Ga gaDen = dsGa.getGaTheoMaGa(maGaDen);
				ChiTietHoaDon chiTiet = new ChiTietHoaDon(maChiTiet);

				// Tạo đối tượng Ve
				Ve ve = new Ve(maVe, tau, toa, ghe, khachHang, ngayDi, gioDi,ngayDen, gioDen, gaDi, gaDen, hang, khuyenMai, trangThai,
						chiTiet);
				dsVe.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVe;
	}

	// Phương thức tạo mới một vé trong bảng Ve
	public boolean create(Ve ve) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT INTO Ve VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, ve.getMaVe());
			stmt.setString(2, ve.getChuyenTau().getMaTau());
			stmt.setString(3, ve.getToa().getMaToa());
			stmt.setInt(4, ve.getSoGhe().getSoGhe());
			stmt.setString(5, ve.getKhachHang().getMaKH());
			stmt.setDate(6, java.sql.Date.valueOf(ve.getNgayDi()));
			stmt.setTime(7, java.sql.Time.valueOf(ve.getGioDi()));
			stmt.setDate(8, java.sql.Date.valueOf(ve.getNgayDen()));
			stmt.setTime(9, java.sql.Time.valueOf(ve.getGioDen()));
			stmt.setString(10, ve.getGaDi().getMaGa());
			stmt.setString(11, ve.getGaDen().getMaGa());
			stmt.setString(12, ve.getHang());
			stmt.setString(13, ve.getKhuyenMai());
			stmt.setBoolean(14, ve.isTrangThai());
			stmt.setString(15, ve.getChiTiet().getMaChiTiet());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// Phương thức cập nhật thông tin của một vé
	public boolean update(Ve ve) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"UPDATE Ve SET tau = ?, toa = ?, soGhe = ?, khachHang = ?, ngayDi = ?, gioDi = ?,ngayDen = ?,gioDen = ?, gaDi = ?, gaDen = ?, hang = ?, khuyenMai = ?,trangThai = ?, chiTiet = ? WHERE maVe = ?");
			stmt.setString(15, ve.getMaVe());
			stmt.setString(1, ve.getChuyenTau().getMaTau());
			stmt.setString(2, ve.getToa().getMaToa());
			stmt.setInt(3, ve.getSoGhe().getSoGhe());
			stmt.setString(4, ve.getKhachHang().getMaKH());
			stmt.setDate(5, java.sql.Date.valueOf(ve.getNgayDi()));
			stmt.setTime(6, java.sql.Time.valueOf(ve.getGioDi()));
			stmt.setDate(7, java.sql.Date.valueOf(ve.getNgayDen()));
			stmt.setTime(8, java.sql.Time.valueOf(ve.getGioDen()));
			stmt.setString(9, ve.getGaDi().getMaGa());
			stmt.setString(10, ve.getGaDen().getMaGa());
			stmt.setString(11, ve.getHang());
			stmt.setString(12, ve.getKhuyenMai());
			stmt.setBoolean(13, ve.isTrangThai());
			stmt.setString(14, ve.getChiTiet().getMaChiTiet());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// Phương thức xóa một vé
	public boolean delete(String maVe) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM Ve WHERE maVe = ?");
			stmt.setString(1, maVe);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// Phương thức lấy vé theo mã vé
	public Ve getVeTheoMaVe(String maVe) {
		Ve ve = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM Ve WHERE maVe = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maVe);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String maVe1 = rs.getString("maVe");
				String maTau = rs.getString("tau");
				String maToa = rs.getString("toa");
				int soGhe = rs.getInt("soGhe");
				String maKH = rs.getString("khachHang");
				LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
				LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
				LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
				LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
				String maGaDi = rs.getString("gaDi");
				String maGaDen = rs.getString("gaDen");
				String hang = rs.getString("hang");
				String khuyenMai = rs.getString("khuyenMai");
				boolean trangThai = rs.getBoolean("trangThai");
				String maChiTiet = rs.getString("chiTiet");

				// Sử dụng constructor copy để tạo đối tượng
				ChuyenTau tau = new ChuyenTau(maTau);
				Toa toa = new Toa(maToa);
				Ghe ghe = new Ghe(soGhe, toa);
				KhachHang khachHang = new KhachHang(maKH);
				Ga gaDi = dsGa.getGaTheoMaGa(maGaDi);
				Ga gaDen = dsGa.getGaTheoMaGa(maGaDen);
				ChiTietHoaDon chiTiet = new ChiTietHoaDon(maChiTiet);
				// Tạo đối tượng Ve
				ve = new Ve(maVe1, tau, toa, ghe, khachHang, ngayDi, gioDi,ngayDen, gioDen, gaDi, gaDen, hang, khuyenMai, trangThai,
						chiTiet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ve;
	}

	// Phương thức lấy vé theo mã vé
	public ArrayList<Ve> getDsVeTheoMaChiTiet(String maChiTiet) {
		ArrayList<Ve> ds = new ArrayList<Ve>();
        KhachHang_DAO dao= new KhachHang_DAO();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM Ve  WHERE chiTiet = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maChiTiet);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maVe1 = rs.getString("maVe");
				String maTau = rs.getString("tau");
				String maToa = rs.getString("toa");
				int soGhe = rs.getInt("soGhe");
				String maKH = rs.getString("khachHang");
                KhachHang kh = dao.getKhachHangTheoMaKH(maKH);

				LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
				LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
				LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
				LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
				String maGaDi = rs.getString("gaDi");
				String maGaDen = rs.getString("gaDen");
				String hang = rs.getString("hang");
				String khuyenMai = rs.getString("khuyenMai");
				boolean trangThai = rs.getBoolean("trangThai");

				// Sử dụng constructor copy để tạo đối tượng
				ChuyenTau tau = new ChuyenTau(maTau);
				Toa toa = new Toa(maToa);
				Ghe ghe = new Ghe(soGhe, toa);
				Ga gaDi = dsGa.getGaTheoMaGa(maGaDi);
				Ga gaDen = dsGa.getGaTheoMaGa(maGaDen);
				ChiTietHoaDon chiTiet = new ChiTietHoaDon(maChiTiet);
				
				// Tạo đối tượng Ve
				Ve ve = new Ve(maVe1, tau, toa, ghe, kh, ngayDi, gioDi,ngayDen, gioDen, gaDi, gaDen, hang, khuyenMai, trangThai,
						chiTiet);
				ds.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public String generateMaVe() {
	    LocalDate currentDate = LocalDate.now();
	    String datePart = String.format("%02d%02d%02d", currentDate.getDayOfMonth(),
	                                       currentDate.getMonthValue(),
	                                       currentDate.getYear() % 100); // Hai số cuối của năm

	    // Lấy số thứ tự lập vé
	    int lastVeNumber = getLastVeNumber(currentDate);
	    String sequencePart = String.format("%04d", lastVeNumber + 1); // Tăng số thứ tự lên 1 và định dạng với 4 chữ số

	    return "VE" + datePart + sequencePart;
	}

	// Phương thức này để lấy số thứ tự lập vé cuối cùng trong ngày từ bảng Ve
	private int getLastVeNumber(LocalDate currentDate) {
	    int lastNumber = 0;
	    try {
	        ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
	        String sql = "SELECT TOP 1 maVe FROM Ve WHERE maVe LIKE 'VE%' AND CAST(SUBSTRING(maVe, 3, 6) AS DATE) = ? ORDER BY maVe DESC"; 
	        // Lọc theo ngày trong mã vé (phần ngày, tháng, năm trong mã vé)
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setDate(1, java.sql.Date.valueOf(currentDate));
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            String lastMaVe = rs.getString("maVe");
	            lastNumber = Integer.parseInt(lastMaVe.substring(8)); // Lấy phần số từ mã vé
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lastNumber;
	}
	
	// Phương thức lấy số thứ tự vé cuối cùng trong cùng ngày
	public int getLastVeNumber(String datePart) {
	    int lastNumber = 0;
	    try {
	        ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
	        String sql = "SELECT TOP 1 maVe FROM Ve WHERE maVe LIKE 'VE" + datePart + "%' ORDER BY maVe DESC"; // Lọc theo ngày
	        PreparedStatement stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            String lastMaVe = rs.getString("maVe");
	            lastNumber = Integer.parseInt(lastMaVe.substring(8)); // Lấy phần số từ mã vé
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lastNumber;
	}
	
	//
	public ArrayList<Ve> getVetheoKhuyenMai(String km){
		ArrayList<Ve> ds = new ArrayList<Ve>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM Ve WHERE khuyenMai = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, km);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maVe1 = rs.getString("maVe");
				String maTau = rs.getString("tau");
				String maToa = rs.getString("toa");
				int soGhe = rs.getInt("soGhe");
				String maKH = rs.getString("khachHang");
				LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
				LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
				LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
				LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
				String maGaDi = rs.getString("gaDi");
				String maGaDen = rs.getString("gaDen");
				String hang = rs.getString("hang");
				String khuyenMai = rs.getString("khuyenMai");
				boolean trangThai = rs.getBoolean("trangThai");
				String maChiTiet = rs.getString("chiTiet");

				// Sử dụng constructor copy để tạo đối tượng
				ChuyenTau tau = new ChuyenTau(maTau);
				Toa toa = new Toa(maToa);
				Ghe ghe = new Ghe(soGhe, toa);
				KhachHang khachHang = new KhachHang(maKH);
				Ga gaDi = dsGa.getGaTheoMaGa(maGaDi);
				Ga gaDen = dsGa.getGaTheoMaGa(maGaDen);
				ChiTietHoaDon chiTiet = new ChiTietHoaDon(maChiTiet);
				
				// Tạo đối tượng Ve
				Ve ve = new Ve(maVe1, tau, toa, ghe, khachHang, ngayDi, gioDi,ngayDen, gioDen, gaDi, gaDen, hang, khuyenMai, trangThai,
						chiTiet);
				ds.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
		
	}

	//Khuyến mãi
	public ArrayList<String> getKhuyenMai(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select khuyenMai From Ve";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String km = rs.getString("khuyenMai");
				list.add(km);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// Phương thức đọc tất cả các vé từ bảng Ve
		public ArrayList<Ve> docTuBangTheoNgayLap() {
			try {
				ConnectDB.getInstance();
				Connection con = ConnectDB.getConnection();
				String sql = "SELECT v.* FROM Ve v JOIN ChiTietHoaDon cthd ON v.chiTiet = cthd.maChiTiet JOIN HoaDon hd ON cthd.hoaDon = hd.maHoaDon ORDER BY hd.ngayLapHoaDon DESC";
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					String maVe = rs.getString("maVe");
					String maTau = rs.getString("tau");
					String maToa = rs.getString("toa");
					int soGhe = rs.getInt("soGhe");
					String maKH = rs.getString("khachHang");
					LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
					LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
					LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
					LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
					String maGaDi = rs.getString("gaDi");
					String maGaDen = rs.getString("gaDen");
					String hang = rs.getString("hang");
					String khuyenMai = rs.getString("khuyenMai");
					boolean trangThai = rs.getBoolean("trangThai");
					String maChiTiet = rs.getString("chiTiet");

					// Sử dụng constructor copy để tạo đối tượng
					ChuyenTau tau = new ChuyenTau(maTau);
					Toa toa = new Toa(maToa);
					Ghe ghe = new Ghe(soGhe, toa);
					KhachHang khachHang = new KhachHang(maKH);
					Ga gaDi = dsGa.getGaTheoMaGa(maGaDi);
					Ga gaDen = dsGa.getGaTheoMaGa(maGaDen);
					ChiTietHoaDon chiTiet = new ChiTietHoaDon(maChiTiet);

					// Tạo đối tượng Ve
					Ve ve = new Ve(maVe, tau, toa, ghe, khachHang, ngayDi, gioDi,ngayDen, gioDen, gaDi, gaDen, hang, khuyenMai, trangThai,
							chiTiet);
					dsVe.add(ve);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dsVe;
		}
	
	public void reset() {
		dsVe.removeAll(dsVe);
	}
	
}
