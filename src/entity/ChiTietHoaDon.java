package entity;

import java.util.ArrayList;

public class ChiTietHoaDon {
	private String maChiTiet;
	private HoaDon hoaDon;
	private int soLuong;
	private ArrayList<Ve> dsVe;
	private float thue;
	
	public ChiTietHoaDon(String maChiTiet, HoaDon hoaDon, int soLuong, ArrayList<Ve> dsVe, float thue) {
		super();
		this.setMaChiTiet(maChiTiet);
		this.setHoaDon(hoaDon);
		this.setSoLuong(soLuong);
		this.setDsVe(dsVe);
		this.setThue(thue);
	}

	public ChiTietHoaDon(String maChiTiet) {
		super();
		this.maChiTiet = maChiTiet;
	}

	public String getMaChiTiet() {
		return maChiTiet;
	}

	public void setMaChiTiet(String maChiTiet) {
		String regexCthd = "^CT\\d{2}\\d{2}\\d{2}NV\\d{3}\\d{5}$";
		if(maChiTiet.matches(regexCthd))
			this.maChiTiet = maChiTiet;
		else
			throw new IllegalArgumentException("Mã chi tiết hóa đơn không hợp lệ!");
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		if((soLuong > 0) && (soLuong<= 4))
			this.soLuong = soLuong;
		else
			throw new IllegalArgumentException("Số lượng không hợp lệ!");
	}

	public ArrayList<Ve> getDsVe() {
		return dsVe;
	}

	public void setDsVe(ArrayList<Ve> dsVe) {
		this.dsVe = dsVe;
	}

	public float getThue() {
		return thue;
	}

	public void setThue(float thue1) {
		thue = thue1;
	}
	
	@Override
	public String toString() {
		return "ChiTietHoaDon [maChiTiet=" + maChiTiet + ", hoaDon=" + hoaDon + ", soLuong=" + soLuong + ", dsVe="
				+ dsVe + ", thue=" + thue + "]";
	}

	public float tinhTien() {
		float tongTien = 0;
		for (Ve ve : dsVe) {
			tongTien += ve.tinhGiaVe();
		}
		return tongTien*(1 + thue);
	}
}
