package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ga;


public class Ga_DAO {
	ArrayList<Ga> dsGa;
    
    public Ga_DAO(){ 
        dsGa = new ArrayList<Ga>();  
    }
    
    public ArrayList<Ga> docTuBang()  { 
        try { 
            ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection(); 
            String sql = "Select * from Ga"; 
            Statement statement = con.createStatement(); 
            ResultSet rs = statement.executeQuery(sql); 
            while (rs.next()) { 
                String maGa = rs.getString("maGa");
                String tenGa = rs.getNString("tenGa");
                String diaChi = rs.getNString("diaChi");
                boolean trangThai = rs.getBoolean("trangThai");
                int chiSoKm= rs.getInt("chiSoKm");
                Ga Ga = new Ga(maGa, tenGa, diaChi, chiSoKm,trangThai);
                dsGa.add(Ga);
           } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        return dsGa; 
    }
    
    public boolean create(Ga ga) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null; 
        int n = 0; 
        try { 
            stmt = con.prepareStatement("insert into Ga values(?, ?, ?, ?, ?)"); 
            stmt.setString(1, ga.getMaGa());
            stmt.setNString(2, ga.getTenGa());
            stmt.setNString(3, ga.getDiaChi());
            stmt.setInt(4, ga.getChiSoKm());
            stmt.setBoolean(5, ga.isTrangThai());
            
            
            n = stmt.executeUpdate();
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        
        return n > 0;
    } 
    
    public boolean update(Ga ga) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        int n = 0; 
        try { 
            stmt = con.prepareStatement("update Ga set tenGa = ?, diaChi = ?, chiSoKm = ?, trangThai = ? where maGa = ?"); 
            stmt.setNString(1, ga.getTenGa());
            stmt.setNString(2, ga.getDiaChi());
            stmt.setInt(3, ga.getChiSoKm());
            stmt.setBoolean(4, ga.isTrangThai());
            stmt.setString(5, ga.getMaGa());
            n = stmt.executeUpdate(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        
        return n > 0;
    } 
           
    public boolean delete(String maGa) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        int n = 0; 
        try { 
            stmt = con.prepareStatement("delete from Ga where maGa = ?"); 
            stmt.setString(1, maGa); 
            n = stmt.executeUpdate(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        
        return n > 0;
    }
    
    public Ga getGaTheoMaGa(String maGa) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        Ga ga = null;
        try {       
            String sql = "Select * from Ga where maGa like ?"; 
            stmt = con.prepareStatement(sql); 
            stmt.setString(1, maGa); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
                String tenGa = rs.getNString("tenGa");
                String diaChi = rs.getNString("diaChi");
                boolean trangThai = rs.getBoolean("trangThai");
                int chiSoKm= rs.getInt("chiSoKm");
                ga = new Ga(maGa, tenGa, diaChi, chiSoKm,trangThai);
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return ga; 
    }
    public Ga getGaTheoDiaChi(String diaChi) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        Ga ga = null;
        try {       
            String sql = "Select * from Ga where diaChi = ?"; 
            stmt = con.prepareStatement(sql); 
            stmt.setString(1, diaChi); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
            	String maGa = rs.getString("maGa");
                String tenGa = rs.getNString("tenGa");
                boolean trangThai = rs.getBoolean("trangThai");
                int chiSoKm= rs.getInt("chiSoKm");
                ga = new Ga(maGa, tenGa, diaChi, chiSoKm,trangThai);
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return ga; 
    }
    public Ga getGaTheoTenGa(String tenGa) { 
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        Ga ga = null;
        try {       
        	String sql = "SELECT * FROM Ga WHERE tenGa LIKE N'%' + ? + '%'";
            stmt = con.prepareStatement(sql); 
            stmt.setString(1,tenGa); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
            	String maGa = rs.getString("maGa");
                String diaChi = rs.getNString("diaChi");
                boolean trangThai = rs.getBoolean("trangThai");
                int chiSoKm= rs.getInt("chiSoKm");
                ga = new Ga(maGa, tenGa, diaChi, chiSoKm,trangThai);
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return ga; 
    }
    
    public ArrayList<Ga> getDsTramDung(String maChuyenTau) {
    	ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
        PreparedStatement stmt = null; 
        ArrayList<Ga> ds = new ArrayList<Ga>();
        try {       
            String sql = "Select * from ChuyenTau_Ga where maTau = ?"; 
            stmt = con.prepareStatement(sql); 
            stmt.setString(1, maChuyenTau); 
            ResultSet rs = stmt.executeQuery(); 
            while (rs.next()) {
                String maGa = rs.getString("maGa");
                ds.add(getGaTheoMaGa(maGa));
            } 
        } catch (SQLException e) { 
            e.printStackTrace();     
        } 
        
        return ds; 
    }

    public void reset() {
        dsGa.removeAll(dsGa);
    }
}
