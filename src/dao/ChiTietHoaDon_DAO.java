package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.Ve;
import entity.ChiTietHoaDon;

public class ChiTietHoaDon_DAO {
	ArrayList<ChiTietHoaDon> dsCTHD;
	Ve_DAO ve_Dao = new Ve_DAO();
	
	public ChiTietHoaDon_DAO(){ 
		dsCTHD = new ArrayList<ChiTietHoaDon>();  
	}
	public ArrayList<ChiTietHoaDon> docTuBang()  { 
	    try { 
	    	ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection(); 
	    	String sql = "Select * from ChiTietHoaDon"; 
	    	Statement statement = con.createStatement(); 
	    	// Thực thi câu lệnh SQL trả về đối tượng ResultSet. 
	    	ResultSet rs = statement.executeQuery(sql); 
	    	// Duyệt trên kết quả trả về. 
	    	while (rs.next()) {
                String maChiTiet = rs.getString("maChiTiet");
                String maHD = rs.getString("hoaDon");
                int soLuong=rs.getInt("soLuong");
                float thue=rs.getFloat("thue");
                
                HoaDon hoaDon = new HoaDon(maHD);
                ArrayList<Ve> danhSachVe = ve_Dao.getDsVeTheoMaChiTiet(maChiTiet);
                
                ChiTietHoaDon cthd= new ChiTietHoaDon(maChiTiet, hoaDon, soLuong, danhSachVe, thue);
                dsCTHD.add(cthd);
            }	
	    } catch (SQLException e) { 
	    	e.printStackTrace(); 
	       // Đóng kết nối 
	    } 
	    return dsCTHD; 
	}
	
	public boolean create(ChiTietHoaDon cthd) {
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO ChiTietHoaDon VALUES(?, ?, ?, ?)");
            stmt.setString(1, cthd.getMaChiTiet());
            stmt.setString(2, cthd.getHoaDon().getMaHoaDon());
            stmt.setInt(3, cthd.getSoLuong());
            stmt.setFloat(4, cthd.getThue());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
	
	public ChiTietHoaDon getCTHDTheoMaChiTiet(String maChiTiet) { 
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt =null; 
		ChiTietHoaDon cthd = null;
		try {       
			String sql = "Select * from ChiTietHoaDon where maChiTiet = ?"; 
			//String sql = "Select * from KhachHang"; 
			stmt = con.prepareStatement(sql); 
			stmt.setString(1, maChiTiet); 
			ResultSet rs = stmt.executeQuery(); 
			while (rs.next()) {
                String maHD = rs.getString("hoaDon");
                int soLuong=rs.getInt("soLuong");
                float thue=rs.getFloat("thue");
                
                HoaDon hoaDon = new HoaDon(maHD);
                
                ArrayList<Ve> danhSachVe = ve_Dao.getDsVeTheoMaChiTiet(maChiTiet);
                
                cthd = new ChiTietHoaDon(maChiTiet, hoaDon, soLuong, danhSachVe, thue);
			} 
		} catch (SQLException e) { 
			e.printStackTrace();     
		} 
		
		return cthd; 
	}
	
	// Phương thức cập nhật thông tin số lượng của chi tiết
	public boolean updateSoLuongVe(ChiTietHoaDon chiTiet) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"UPDATE ChiTietHoaDon SET soLuong = ? WHERE maChiTiet = ?");
			stmt.setString(2, chiTiet.getMaChiTiet());
			stmt.setString(1, chiTiet.getSoLuong()+"");
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
    
	public void reset() {
        dsCTHD.removeAll(dsCTHD);
    }

	
}
