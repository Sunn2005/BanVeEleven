package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.Ghe;
import entity.Toa;

public class Toa_DAO {

    ArrayList<Toa> dsToa;
    Ghe_DAO gheDAO = new Ghe_DAO();

    public Toa_DAO() {
        dsToa = new ArrayList<Toa>();
    }

    // Phương thức đọc tất cả các toa từ bảng Toa
    public ArrayList<Toa> docTuBang() {
        try {
            ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Toa";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maToa = rs.getString("maToa");
                String loaiToa = rs.getString("loaiToa");
                String maTauStr = rs.getString("maTau");

                // Sử dụng constructor copy để tạo đối tượng ChuyenTau
                ChuyenTau maTau = new ChuyenTau(maTauStr);

                // Tạo danh sách ghế dựa vào loại toa
                ArrayList<Ghe> dsGhe = gheDAO.getDsGheTheoMaToa(maToa);

                Toa toa = new Toa(maToa, loaiToa, maTau, dsGhe);
                dsToa.add(toa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsToa;
    }

    // Phương thức tạo danh sách ghế dựa trên loại toa
    private ArrayList<Ghe> taoDsGhe(String maToa, String loaiToa) {
        ArrayList<Ghe> dsGhe = new ArrayList<>();
        int soLuongGhe = 0;

        // Xác định số lượng ghế theo loại toa
        switch (loaiToa) {
            case "Ghế mềm":
                soLuongGhe = 64;
                break;
            case "Giường nằm":
                soLuongGhe = 32;
                break;
            case "VIP":
                soLuongGhe = 20;
                break;
            default:
                throw new IllegalArgumentException("Loại toa không hợp lệ!");
        }

        // Tạo danh sách ghế cho toa
        for (int i = 1; i <= soLuongGhe; i++) {
            Ghe ghe = new Ghe(i, new Toa(maToa), false); // Mặc định trạng thái ghế là 'trống' (false)
            dsGhe.add(ghe);
        }

        return dsGhe;
    }

    // Phương thức tạo mới một toa trong bảng Toa
    public boolean create(Toa toa) {
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO Toa VALUES(?, ?, ?)");
            stmt.setString(1, toa.getMaToa());
            stmt.setString(2, toa.getLoaiToa());
            stmt.setString(3, toa.getMaTau().getMaTau());

            n = stmt.executeUpdate();

            // Sau khi tạo toa, tạo danh sách ghế cho toa đó trong bảng Ghe
            ArrayList<Ghe> dsGhe = taoDsGhe(toa.getMaToa(), toa.getLoaiToa());
            if (n > 0 && dsGhe != null) {
                for (Ghe ghe : dsGhe) {
                    gheDAO.create(ghe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức cập nhật thông tin của một toa
    public boolean update(Toa toa) {
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE Toa SET loaiToa = ?, maTau = ? WHERE maToa = ?");
            stmt.setString(1, toa.getLoaiToa());
            stmt.setString(2, toa.getMaTau().getMaTau());
            stmt.setString(3, toa.getMaToa());

            n = stmt.executeUpdate();

            // Cập nhật danh sách ghế cho toa sau khi cập nhật toa
            if (n > 0 && toa.getDsGhe() != null) {
                for (Ghe ghe : toa.getDsGhe()) {
                    gheDAO.update(ghe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức xóa một toa và danh sách ghế của nó
    public boolean delete(String maToa) {
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            // Xóa danh sách ghế trước khi xóa toa
            gheDAO.deleteByMaToa(maToa);

            stmt = con.prepareStatement("DELETE FROM Toa WHERE maToa = ?");
            stmt.setString(1, maToa);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Phương thức lấy toa theo mã
    public Toa getToaTheoMaToa(String maToa) {
        Toa toa = null;
        try {
            ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Toa WHERE maToa = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maToa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String loaiToa = rs.getString("loaiToa");
                String maTauStr = rs.getString("maTau");

                // Sử dụng constructor copy để tạo đối tượng ChuyenTau
                ChuyenTau maTau = new ChuyenTau(maTauStr);

                // Tạo danh sách ghế dựa trên loại toa
                ArrayList<Ghe> dsGhe = taoDsGhe(maToa, loaiToa);

                toa = new Toa(maToa, loaiToa, maTau, dsGhe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toa;
    }
    
    // Phương thức lấy danh sách toa theo mã chuyến tàu
    public ArrayList<Toa> getDsToaTheoMaTau(String maTauStr) {
    	ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null; 
        ArrayList<Toa> ds = new ArrayList<Toa>();
        try {
            String sql = "SELECT * FROM Toa WHERE maTau = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTauStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maToa = rs.getString("maToa");
                String loaiToa = rs.getString("loaiToa");

                // Tạo danh sách ghế dựa trên loại toa
                ArrayList<Ghe> dsGhe = gheDAO.getDsGheTheoMaToa(maToa);
                
                ChuyenTau maTau = new ChuyenTau(maTauStr);

                Toa toa = new Toa(maToa, loaiToa, maTau, dsGhe);
                
                ds.add(toa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
	public void reset() {
		dsToa.removeAll(dsToa);
	}
}
