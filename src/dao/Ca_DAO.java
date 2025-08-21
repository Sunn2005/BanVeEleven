package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ca;

public class Ca_DAO {
	ArrayList<Ca> dsCa;
		
	public Ca_DAO(){ 
		dsCa = new ArrayList<Ca>();  
	}
		
	public ArrayList<Ca> docTuBang()  { 
	    try { 
	    	ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection(); 
	    	String sql = "Select * from Ca"; 
	    	Statement statement = con.createStatement(); 
	    	ResultSet rs = statement.executeQuery(sql); 
	    	while (rs.next()) {
	    		String maCa = rs.getString(1); 
	    		String tenCa = rs.getNString(2);  
	    		LocalTime thoiGianBatDau = rs.getTime(3).toLocalTime();
	    		LocalTime thoiGianKetThuc = rs.getTime(4).toLocalTime();
	    		Ca ca = new Ca(maCa, tenCa, thoiGianBatDau,thoiGianKetThuc);
	    		dsCa.add(ca);
	       } 
	    } catch (SQLException e) { 
	    	e.printStackTrace(); 
	    } 
	    return dsCa; 
	}

	public boolean create(Ca ca) { 
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null; 
		int n = 0; 
		try { 
			stmt = con.prepareStatement("insert into KhachHang values(?, ?, ?, ?)"); 
			stmt.setString(1,ca.getMaCa());
			stmt.setNString(2,ca.getTenCa());
			stmt.setObject(3,ca.getThoiGianBatDau());
			stmt.setObject(4,ca.getThoiGianKetThuc());
			n = stmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace(); 
		}
		return n > 0;
	} 
	//U: Update 
	public boolean update(Ca ca) { 
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null; 
		int n = 0; 
		try { 
			stmt = con.prepareStatement("update Ca set maCa = ?, tenCa = ?, thoiGianBatDau = ?, thoiGianKetThuc = ? where maCa = ?"); 
			stmt.setString(1,ca.getMaCa());
			stmt.setNString(2,ca.getTenCa());
			stmt.setObject(3,ca.getThoiGianBatDau());
			stmt.setObject(4,ca.getThoiGianKetThuc());
		     
		    n = stmt.executeUpdate(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		
		return n > 0;
	} 
	
	public Ca getCaTheoMaCa(String maCa){
		Ca ca = null;
		Connection con = null;
		PreparedStatement stmt = null;
			try {
				con = ConnectDB.getConnection();
				String sql = "SELECT * FROM Ca WHERE [maCa] = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, maCa);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					String maCA = rs.getString(1); 
		    		String tenCA = rs.getNString(2);  
		    		LocalTime thoiGianBatDau = rs.getTime(3).toLocalTime();
		    		LocalTime thoiGianKetThuc = rs.getTime(4).toLocalTime();
		    		ca = new Ca(maCA, tenCA, thoiGianBatDau,thoiGianKetThuc);
							
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return ca;
	}
		
	public void reset() {
		dsCa.removeAll(dsCa);
	}
}
