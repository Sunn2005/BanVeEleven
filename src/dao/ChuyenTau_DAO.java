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
           
    public boolean delete(String maTau) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        int n = 0; 
        try { 
            stmt = con.prepareStatement("delete from ChuyenTau where maTau = ?"); 
            stmt.setString(1, maTau); 
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
    public void reset() {
        dsChuyenTau.removeAll(dsChuyenTau);
    }
}
