package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ghe;
import entity.Toa;

public class Ghe_DAO {

    ArrayList<Ghe> dsGhe;

    public Ghe_DAO() {
        dsGhe = new ArrayList<Ghe>();
    }

    // Phương thức đọc tất cả các ghế từ bảng Ghe
    public ArrayList<Ghe> docTuBang() {
        try {
            ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Ghe";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int soGhe = rs.getInt("soGhe");
                String maToaStr = rs.getString("maToa");
                boolean trangThai = rs.getBoolean("trangThai");

                // Sử dụng constructor copy
                Toa maToa = new Toa(maToaStr);

                Ghe ghe = new Ghe(soGhe, maToa, trangThai);
                dsGhe.add(ghe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsGhe;
    }

    // Phương thức tạo mới một ghế trong bảng Ghe
    public boolean create(Ghe ghe) {
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO Ghe VALUES(?, ?, ?)");
            stmt.setInt(1, ghe.getSoGhe());
            stmt.setString(2, ghe.getToa().getMaToa());
            stmt.setBoolean(3, ghe.isTrangThai());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức cập nhật thông tin của một ghế
    public boolean update(Ghe ghe) {
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE Ghe SET trangThai = ? WHERE soGhe = ? AND maToa = ?");
            stmt.setBoolean(1, ghe.isTrangThai());
            stmt.setInt(2, ghe.getSoGhe());
            stmt.setString(3, ghe.getToa().getMaToa());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức xóa một ghế dựa trên số ghế và mã toa
    public boolean delete(int soGhe, String maToaStr) {
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM Ghe WHERE soGhe = ? AND maToa = ?");
            stmt.setInt(1, soGhe);
            stmt.setString(2, maToaStr);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
	public boolean deleteByMaToa(String maToa) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM Ghe WHERE maToa = ?");
            stmt.setString(1, maToa);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
		
	}

    // Phương thức lấy danh sách ghế theo mã toa
    public ArrayList<Ghe> getDsGheTheoMaToa(String maToaStr) {
        ArrayList<Ghe> dsGheTheoToa = new ArrayList<Ghe>();
        try {
            ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Ghe WHERE maToa = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maToaStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int soGhe = rs.getInt("soGhe");
                boolean trangThai = rs.getBoolean("trangThai");

                // Sử dụng getToaByMa để lấy dữ liệu từ CSDL
                Toa maToa = new Toa(maToaStr);

                Ghe ghe = new Ghe(soGhe, maToa, trangThai);
                dsGheTheoToa.add(ghe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsGheTheoToa;
    }
	
	// Phương thức lấy danh sách ghế theo mã toa
    public Ghe getGheTheoMaToaVaSoGhe(String maToaStr, int soGhe) {
        ArrayList<Ghe> dsGheTheoToa = new ArrayList<Ghe>();
        Ghe ghe = null;
        try {
            ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Ghe WHERE (maToa = ? AND soGhe = ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maToaStr);
            stmt.setInt(2, soGhe);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                boolean trangThai = rs.getBoolean("trangThai");

                // Sử dụng getToaByMa để lấy dữ liệu từ CSDL
                Toa maToa = new Toa(maToaStr);

                ghe = new Ghe(soGhe, maToa, trangThai);
                dsGheTheoToa.add(ghe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ghe;
    }
	
	public void reset() {
		dsGhe.removeAll(dsGhe);
	}
}
