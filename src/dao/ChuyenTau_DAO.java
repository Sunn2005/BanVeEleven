package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.Ga;
import entity.Toa;

public class ChuyenTau_DAO {
	ArrayList<ChuyenTau> dsChuyenTau;
	Ga_DAO ga_Dao = new Ga_DAO();
	Toa_DAO toa_Dao = new Toa_DAO();
    
    public ChuyenTau_DAO(){ 
        dsChuyenTau = new ArrayList<ChuyenTau>();  
    }
    
    public ArrayList<ChuyenTau> docTuBang()  { 
        try { 
            ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection(); 
            String sql = "Select * from ChuyenTau"; 
            Statement statement = con.createStatement(); 
            ResultSet rs = statement.executeQuery(sql); 
            while (rs.next()) { 
                String maTau = rs.getString("maTau");
                String gaDi = rs.getString("gaDi");
                String gaDen = rs.getString("gaDen");
                LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
                LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
                LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
                LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
                Ga gaDi1 = ga_Dao.getGaTheoMaGa(gaDi);
                Ga gaDen1 = ga_Dao.getGaTheoMaGa(gaDen);
                ArrayList<Toa> dsToa = toa_Dao.getDsToaTheoMaTau(maTau);
                ArrayList<Ga> tramDung = ga_Dao.getDsTramDung(maTau);
                ChuyenTau chuyenTau = new ChuyenTau(maTau, gaDi1,gaDen1, tramDung, ngayDi,gioDi,ngayDen,gioDen ,dsToa);
                dsChuyenTau.add(chuyenTau);
           } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        return dsChuyenTau; 
    }
        
    public boolean create(ChuyenTau chuyenTau) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null; 
        int n = 0; 
        try { 
            stmt = con.prepareStatement("insert into ChuyenTau values(?, ?, ?, ?, ?, ?, ?)"); 
            stmt.setString(1, chuyenTau.getMaTau());
            stmt.setString(2, chuyenTau.getGaDi().getMaGa());
            stmt.setString(3, chuyenTau.getGaDen().getMaGa());
            stmt.setObject(4, chuyenTau.getNgayDi());
            stmt.setObject(5, chuyenTau.getGioDi()); 
            stmt.setObject(6, chuyenTau.getNgayDen());
            stmt.setObject(7, chuyenTau.getGioDen());    
            n = stmt.executeUpdate();
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        
        return n > 0;
    } 
    
    public boolean update(ChuyenTau chuyenTau) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        int n = 0; 
        try { 
            stmt = con.prepareStatement("update ChuyenTau set gaDi = ?, gaDen = ?, ngayDi = ?, gioDi = ?, ngayDen = ?, gioDen = ? where maTau = ?"); 
            stmt.setString(1, chuyenTau.getGaDi().getMaGa());
            stmt.setString(2, chuyenTau.getGaDen().getMaGa());
            stmt.setObject(3, chuyenTau.getNgayDi());
            stmt.setObject(4, chuyenTau.getGioDi()); 
            stmt.setObject(5, chuyenTau.getNgayDen());
            stmt.setObject(6, chuyenTau.getGioDen()); 
            stmt.setString(7, chuyenTau.getMaTau());
            n = stmt.executeUpdate(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        
        return n > 0;
    } 
           

    public ChuyenTau getChuyenTauTheoMaTau(String maTau) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        ChuyenTau chuyenTau = null;
        try {       
            String sql = "Select * from ChuyenTau where maTau = ?"; 
            stmt = con.prepareStatement(sql); 
            stmt.setString(1, maTau); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
//            	String maTau1 = rs.getString("maTau");
                String gaDi = rs.getString("gaDi");
                String gaDen = rs.getString("gaDen");
                LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
                LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
                LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
                LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
                Ga gaDi1 = ga_Dao.getGaTheoMaGa(gaDi);
                Ga gaDen1 = ga_Dao.getGaTheoMaGa(gaDen);
                ArrayList<Toa> dsToa = toa_Dao.getDsToaTheoMaTau(maTau);
                ArrayList<Ga> tramDung = ga_Dao.getDsTramDung(maTau);
                chuyenTau = new ChuyenTau(maTau, gaDi1,gaDen1, tramDung, ngayDi,gioDi,ngayDen,gioDen ,dsToa);
              
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return chuyenTau; 
    } 
    public ArrayList<ChuyenTau> getChuyenTau_Ga(String maTau) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        ChuyenTau chuyenTau = null;
        try {       
            String sql = "SELECT * FROM ChuyenTau_Ga WHERE maTau = ?"; 
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTau);
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
                String gaDi = rs.getString("maGa");
                LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
                LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
                LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
                LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
                Ga ga = new Ga(gaDi);
                chuyenTau = new ChuyenTau(maTau, ga,null,null, ngayDi,gioDi,ngayDen,gioDen ,null);
                dsChuyenTau.add(chuyenTau);
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return dsChuyenTau; 
    }
    public ArrayList<ChuyenTau> getChuyenTauTheoThoiGian(String ngayBatDau, String ngayKetThuc) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        ChuyenTau chuyenTau = null;
        try {       
            String sql = "SELECT * FROM ChuyenTau WHERE ngayDi BETWEEN ? AND ?"; 
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ngayBatDau);
            stmt.setString(2, ngayKetThuc); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
            	String maTau = rs.getString("maTau");
                String gaDi = rs.getString("gaDi");
                String gaDen = rs.getString("gaDen");
                LocalDate ngayDi = rs.getDate("ngayDi").toLocalDate();
                LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
                LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
                LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
                Ga gaDi1 = ga_Dao.getGaTheoMaGa(gaDi);
                Ga gaDen1 = ga_Dao.getGaTheoMaGa(gaDen);
                ArrayList<Toa> dsToa = toa_Dao.getDsToaTheoMaTau(maTau);
                ArrayList<Ga> tramDung = ga_Dao.getDsTramDung(maTau);
                chuyenTau = new ChuyenTau(maTau, gaDi1,gaDen1, tramDung, ngayDi,gioDi,ngayDen,gioDen ,dsToa);
                dsChuyenTau.add(chuyenTau);
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return dsChuyenTau; 
    }
    
    public ArrayList<ChuyenTau> getChuyenTauTheoGaVaNgayDi(String gaDi, String gaDen, String ngayDi){
    	ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        ChuyenTau chuyenTau = null;
        try {       
            String sql = "SELECT * FROM ChuyenTau WHERE gaDi = ? AND gaDen = ? AND ngayDi = ?"; 
            stmt = con.prepareStatement(sql);
            stmt.setString(1, gaDi);
            stmt.setString(2, gaDen);
            stmt.setString(3, ngayDi);
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
            	String maTau = rs.getString("maTau");
                LocalDate ngayDi1 = rs.getDate("ngayDi").toLocalDate();
                LocalTime gioDi = rs.getTime("gioDi").toLocalTime();
                LocalDate ngayDen = rs.getDate("ngayDen").toLocalDate();
                LocalTime gioDen = rs.getTime("gioDen").toLocalTime();
                Ga gaDi1 = ga_Dao.getGaTheoMaGa(gaDi);
                Ga gaDen1 = ga_Dao.getGaTheoMaGa(gaDen);
                ArrayList<Toa> dsToa = toa_Dao.getDsToaTheoMaTau(maTau);
                ArrayList<Ga> tramDung = ga_Dao.getDsTramDung(maTau);
                chuyenTau = new ChuyenTau(maTau, gaDi1,gaDen1, tramDung, ngayDi1,gioDi,ngayDen,gioDen ,dsToa);
                dsChuyenTau.add(chuyenTau);
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return dsChuyenTau; 
    }
    

 // Thêm vào ChuyenTau_DAO.java

 // Phương thức thêm toa
 public boolean themToa(String maToa, String loaiToa, String maTau) {
     ConnectDB.getInstance();
     Connection con = ConnectDB.getConnection();
     PreparedStatement stmt = null;
     int n = 0;
     
     try {
         String sql = "INSERT INTO Toa (maToa, loaiToa, maTau) VALUES (?, ?, ?)";
         stmt = con.prepareStatement(sql);
         stmt.setString(1, maToa);
         stmt.setString(2, loaiToa);
         stmt.setString(3, maTau);
         
         n = stmt.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         try {
             if (stmt != null) stmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     return n > 0;
 }

 // Phương thức thêm ghế cho toa
 public boolean themGheChoToa(String maToa, int soLuongGhe) {
     ConnectDB.getInstance();
     Connection con = ConnectDB.getConnection();
     PreparedStatement stmt = null;
     int totalInserted = 0;
     
     try {
         String sql = "INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (?, ?, 1)";
         stmt = con.prepareStatement(sql);
         
         for (int i = 1; i <= soLuongGhe; i++) {
             stmt.setInt(1, i);
             stmt.setString(2, maToa);
             totalInserted += stmt.executeUpdate();
         }
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         try {
             if (stmt != null) stmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     return totalInserted == soLuongGhe;
 }

 // Phương thức xóa CASCADE: Ghế -> Toa -> ChuyenTau
 public boolean delete(String maTau) {
     Connection con = ConnectDB.getConnection();
     PreparedStatement stmt = null;
     
     try {
         // Bắt đầu transaction
         con.setAutoCommit(false);
         
         // 1. Xóa tất cả ghế của các toa thuộc chuyến tàu
         stmt = con.prepareStatement(
             "DELETE FROM Ghe WHERE maToa IN (SELECT maToa FROM Toa WHERE maTau = ?)"
         );
         stmt.setString(1, maTau);
         stmt.executeUpdate();
         stmt.close();
         
         // 2. Xóa tất cả toa của chuyến tàu
         stmt = con.prepareStatement("DELETE FROM Toa WHERE maTau = ?");
         stmt.setString(1, maTau);
         stmt.executeUpdate();
         stmt.close();
         
         // 3. Xóa các trạm dừng trong ChuyenTau_Ga
         stmt = con.prepareStatement("DELETE FROM ChuyenTau_Ga WHERE maTau = ?");
         stmt.setString(1, maTau);
         stmt.executeUpdate();
         stmt.close();
         
         // 4. Xóa chuyến tàu
         stmt = con.prepareStatement("DELETE FROM ChuyenTau WHERE maTau = ?");
         stmt.setString(1, maTau);
         int n = stmt.executeUpdate();
         
         // Commit transaction
         con.commit();
         return n > 0;
         
     } catch (SQLException e) {
         try {
             con.rollback(); // Rollback nếu có lỗi
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         e.printStackTrace();
         return false;
     } finally {
         try {
             con.setAutoCommit(true);
             if (stmt != null) stmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
 }
    public void reset() {
        dsChuyenTau.removeAll(dsChuyenTau);
    }
}
